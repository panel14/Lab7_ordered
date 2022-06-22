package itmo.commands;

import itmo.io.Printable;
import itmo.storage.Storage;

/**
 * command that saves collection in file
 */
public class SaveCommand implements Command {

    private final Printable printable;

    /**
     * @param printable
     */
    public SaveCommand( Printable printable) {
        this.printable = printable;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws Exception {
        Storage.saveCollection();
    }
}
