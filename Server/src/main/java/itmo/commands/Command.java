package itmo.commands;

/**
 * Interface of commands
 */
public interface Command {

    /**
     * Execute the command
     */
    void execute() throws Exception;
}