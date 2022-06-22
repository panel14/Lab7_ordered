package itmo.requests;

import itmo.interfaces.SimpleRequest;
import itmo.utility.CommandArguments;
import itmo.utility.CommandInfo;

public class CommandExecutionRequest implements SimpleRequest {
    public CommandInfo commandInfo;
    public CommandArguments commandArguments;
    public String[] userData;

    public CommandExecutionRequest() {}

    public CommandExecutionRequest(CommandInfo commandInfo, CommandArguments commandArguments, String[] userData) {
        this.commandInfo = commandInfo;
        this.commandArguments = commandArguments;
        this.userData = userData;
    }
}
