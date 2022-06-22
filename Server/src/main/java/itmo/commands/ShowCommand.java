package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.io.Printable;
import itmo.storage.Storage;
import itmo.utility.CommandFormatHeader;

import java.io.IOException;

/**
 * command that displays all elements in collection
 */
public class ShowCommand implements Command {

    private final Printable printable;

    /**
     //* @param vectorCollection collection
     * @param printable
     */
    public ShowCommand(Printable printable) {
        this.printable = printable;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws IOException {
        CommandFormatHeader commandFormatHeader = new CommandFormatHeader(50, this);
        printable.println(commandFormatHeader.toString());

        VectorCollection<StudyGroup> vectorCollection = Storage.storage.getAllCollection();

        if (vectorCollection.isEmpty())
            printable.println("Collection is empty");

        vectorCollection.forEach(element -> {
            try {
                printable.println(element + "\n" + commandFormatHeader.getElementDelimiter());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
