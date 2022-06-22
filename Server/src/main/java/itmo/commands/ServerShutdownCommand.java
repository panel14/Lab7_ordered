package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.io.Printable;
import itmo.utility.CommandFormatHeader;

public class ServerShutdownCommand implements Command {
    private final Printable printable;

    public ServerShutdownCommand(Printable printable) {
        this.printable = printable;
    }

    @Override
    public void execute() throws Exception {
        printable.println(new CommandFormatHeader(50, this).toString());

        new SaveCommand(printable).execute();
        System.exit(0);
    }
}
