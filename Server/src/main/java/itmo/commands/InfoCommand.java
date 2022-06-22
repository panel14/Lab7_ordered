package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.io.Printable;
import itmo.storage.Storage;
import itmo.utility.CommandFormatHeader;

import java.io.IOException;

/**
 * command that displays information about collection
 */
public class InfoCommand implements Command {
    private final Printable printable;

    /**
     * @param printable
     */
    public InfoCommand(Printable printable) {
        this.printable = printable;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws IOException {
        VectorCollection<StudyGroup> vectorCollection = Storage.storage.getAllCollection();
        printable.println(new CommandFormatHeader(50, this).toString());
        printable.println("Type: " + vectorCollection.getClass().getSuperclass().getName());
        printable.println("Creation date: " + vectorCollection.getCreationDate());
        printable.println("Number of elements: " + vectorCollection.size());
    }
}
