package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.io.Printable;
import itmo.service.User;
import itmo.storage.Storage;
import itmo.utility.CommandFormatHeader;

import java.io.IOException;

/**
 * command that clears collection completely
 */
public class ClearCommand implements Command {
    private final Printable printable;
    private final User user;
    /**
     * collection
     */

    /**
     * @param printable
     //* @param vectorCollection collection
     */
    public ClearCommand(Printable printable, User user) {
        this.printable = printable;
        this.user = user;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws IOException {
        printable.println(new CommandFormatHeader(50, this).toString());
        VectorCollection<StudyGroup> deleted = Storage.storage.getUserCollection(user);
        Storage.removeAll(deleted);
        System.out.println("Collection has been successfully cleared");
    }
}
