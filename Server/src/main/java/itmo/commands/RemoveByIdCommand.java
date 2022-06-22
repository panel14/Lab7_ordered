package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.io.Printable;
import itmo.service.User;
import itmo.storage.Storage;
import itmo.utility.CommandFormatHeader;

/**
 * command that removes StudyGroup by Id
 */
public class RemoveByIdCommand implements Command {
    private final Printable printable;
    private final Integer id;
    private final User user;

    /**
     * @param printable
     * @param id               id
     * @param user             data of current user
     */
    public RemoveByIdCommand(Printable printable, Integer id, User user) {
        this.printable = printable;
        this.id = id;
        this.user = user;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws Exception {
        printable.println(new CommandFormatHeader(50, this).toString());
        VectorCollection<StudyGroup> deleted = Storage.storage.getUserCollection(user);
        deleted.removeIf(studyGroup -> !(studyGroup.getId().equals(id)));
        Storage.removeAll(deleted);
    }
}
