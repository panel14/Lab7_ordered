package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.io.Printable;
import itmo.service.User;
import itmo.storage.Storage;
import itmo.utility.CommandFormatHeader;

import java.io.IOException;

/**
 * command that removes last element in collection
 */
public class RemoveLastCommand implements Command {

    private final Printable printable;
    private final User user;

    /**
     //* @param vectorCollection collection
     * @param printable
     */
    public RemoveLastCommand(Printable printable, User user) {
        this.printable = printable;
        this.user = user;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws IOException {
        printable.println(new CommandFormatHeader(50, this).toString());

        VectorCollection<StudyGroup> vectorCollection = Storage.storage.getUserCollection(user);
        if (vectorCollection.isEmpty())
            printable.println("Collection is empty");
        else {
            StudyGroup last = vectorCollection.lastElement();
            Storage.storage.getAllCollection().remove(last);

            printable.println("Last element has been successfully removed");
        }
    }
}
