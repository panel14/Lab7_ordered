package itmo.commands;

import itmo.io.Printable;
import itmo.service.DataBase;
import itmo.utility.CommandFormatHeader;

/**
 * kills the program with saving collection in database
 */
public class ExitCommand implements Command {
    private final Printable printable;

    public ExitCommand(Printable printable) {
        this.printable = printable;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws Exception {
        printable.println(new CommandFormatHeader(50, this).toString());
        DataBase.close();
        System.exit(0);
    }
}
