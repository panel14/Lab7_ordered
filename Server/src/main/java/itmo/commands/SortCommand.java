package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.data.comparators.StudyGroupComparator;
import itmo.io.Printable;
import itmo.service.User;
import itmo.storage.Storage;
import itmo.utility.CommandFormatHeader;

/**
 * command that sorts collection
 */
public class SortCommand implements Command {
    private final Printable printable;

    /**
     * @param printable
     */
    public SortCommand( Printable printable) {
        this.printable = printable;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws Exception {
        printable.println(new CommandFormatHeader(50, this).toString());
        Storage.storage.getAllCollection().sort(new StudyGroupComparator());
    }
}
