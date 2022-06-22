package itmo.requests;

import itmo.interfaces.SimpleRequest;

public class CommandInfoRequest implements SimpleRequest {
    public String commandName;
    public String[] userData;

    public CommandInfoRequest() {}

    public CommandInfoRequest(String commandName, String[] userData) {
        this.commandName = commandName;
        this.userData = userData;
    }
}
