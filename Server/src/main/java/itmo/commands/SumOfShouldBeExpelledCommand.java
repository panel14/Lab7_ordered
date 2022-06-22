package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.interfaces.ShouldBeExpellable;
import itmo.io.Printable;
import itmo.service.User;
import itmo.storage.Storage;
import itmo.utility.CommandFormatHeader;

import java.io.IOException;

/**
 * command that displays the sum of shouldBeExpelled from all StudyGroups
 */
public class SumOfShouldBeExpelledCommand implements Command {

    private final Printable printable;

    Long sum = 0L;

    /**
     //* @param vectorCollection collection
     * @param printable
     */
    public SumOfShouldBeExpelledCommand(Printable printable) {
        this.printable = printable;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws IOException {
        printable.println(new CommandFormatHeader(50, this).toString());
        VectorCollection<StudyGroup> vectorCollection = Storage.storage.getAllCollection();
        vectorCollection.forEach(element -> sum += (element.getShouldBeExpelled() == null ? 0L : element.getShouldBeExpelled()));

        printable.println(sum.toString());
    }
}
