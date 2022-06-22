package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.data.comparators.StudyGroupComparatorByAdmin;
import itmo.io.Printable;
import itmo.service.User;
import itmo.storage.Storage;
import itmo.utility.CommandFormatHeader;

import java.util.Optional;

/**
 * command that displays StudyGroup with the minimal GroupAdmin
 */
public class MinByGroupAdminCommand implements Command {
    private final Printable printable;
    private final User user;

    /**
     * @param printable
     * @param user             data of current user
     */
    public MinByGroupAdminCommand(Printable printable, User user) {
        this.printable = printable;
        this.user = user;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws Exception {
        printable.println(new CommandFormatHeader(50, this).toString());

        VectorCollection<StudyGroup> vectorCollection = Storage.storage.getUserCollection(user);
        Optional<StudyGroup> min = vectorCollection.stream().min(new StudyGroupComparatorByAdmin());
        if (!min.isPresent()) {
            printable.println("It's impossible to find min groupAdmin");
            return;
        }

        printable.println(min.get().toString());

    }
}
