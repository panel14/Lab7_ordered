package itmo.commands;

import itmo.io.Printable;
import itmo.utility.CommandFormatHeader;

/**
 * kills the program without file saving
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
        System.exit(0);
    }
}
