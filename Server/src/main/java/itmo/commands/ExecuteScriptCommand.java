package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.exceptions.CollectionException;
import itmo.io.FileScan;
import itmo.io.Printable;
import itmo.io.Scannable;
import itmo.io.StringScan;
import itmo.manager.CommandManager;
import itmo.manager.FileHistory;
import itmo.service.User;
import itmo.utility.CommandArguments;
import itmo.utility.CommandFormatHeader;
import itmo.utility.CommandInfo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * command that executes the script
 */
public class ExecuteScriptCommand implements Command {

    private final Printable printable;
    private final User user;
    private final String filename;
    private final String fileContent;

    /**
     * @param printable
     * @param filename         file's name
     * @param fileContent
     */
    public ExecuteScriptCommand(Printable printable, String filename, String fileContent, User user) {
        this.printable = printable;
        this.filename = filename;
        this.fileContent = fileContent;
        this.user = user;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws Exception {
        printable.println(new CommandFormatHeader(50, this).toString());
        if (!Files.isReadable(Paths.get(filename))) {
            throw new CollectionException("Can't read " + filename);
        }
        File file = new File(filename);
        if (FileHistory.getInstance().Contains(file))
            throw new CollectionException("Oops, haha recursion");
        FileHistory.getInstance().addToHistory(file);
        try {
            Scannable scannable = new StringScan(fileContent);
            while (scannable.hasNextLine()){
                String commandLine = scannable.readLine();
                String[] words = commandLine.trim().replaceAll("\\s{2,}", " ").trim().split(" ");
                String commandName = words[0];

                CommandInfo commandInfo = CommandManager.getCommandInfo(commandName);
                CommandArguments commandArguments = CommandManager.readCommandArguments(words, commandInfo, scannable,
                        false, new String[] {user.login, user.password});
                CommandManager.constructCommand(commandInfo, commandArguments, printable).execute();
            }
        } catch (Exception e) {
            printable.println("ooops: " + e.getMessage());
        }
        FileHistory.getInstance().Remove(file);
    }
}
