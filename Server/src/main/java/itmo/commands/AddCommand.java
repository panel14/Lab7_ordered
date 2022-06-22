package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.io.Printable;
import itmo.service.User;
import itmo.storage.Storage;
import itmo.utility.CommandFormatHeader;

/**
 * command that adds new studyGroup
 */
public class AddCommand implements Command {
    private final Printable printable;
    private final StudyGroup studyGroup;
    private final User user;

    /**
     //* @param vectorCollection collection
     * @param printable
     * @param studyGroup       group to add
     */
    public AddCommand(Printable printable, StudyGroup studyGroup, User user) {
        this.printable = printable;
        this.studyGroup = studyGroup;
        this.user = user;
    }

    /**
     * executes the command
     */
    @Override
    public void execute() throws Exception {
        printable.println(new CommandFormatHeader(50, this).toString());
        Storage.storage.getAllCollection().add(studyGroup);
    }
}
