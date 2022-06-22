package itmo.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import itmo.Client;
import itmo.exceptions.CollectionException;
import itmo.io.*;
import itmo.requests.CommandExecutionRequest;
import itmo.requests.CommandInfoRequest;
import itmo.requests.Request;
import itmo.userutils.User;
import itmo.utility.CommandArguments;
import itmo.utility.CommandInfo;

import java.util.ArrayList;

/**
 * class helps to read commands
 */
public class ClientCommandManager {

    public boolean executeCommand(ClientPrint clientPrint, ClientScan clientScan, Scannable scannable, User user) throws Exception {
        String commandString = scannable.readLine();
        String[] words = commandString.trim().replaceAll("\\s{2,}", " ").trim().split(" ");

        String commandName = words[0];
        if (commandName.equals("exit")){
            System.exit(1);
        }
        CommandInfo commandInfo = commandInfoRequest(clientPrint, clientScan, commandName, user);
        if (commandInfo.error){
            throw new CollectionException(commandInfo.errorMessage);
        }

        CommandArguments arguments = CommandManager.readCommandArguments(words, commandInfo, new ConsoleScan(),
                true, new String[] {user.login, user.password});

        return executeCommandRequest(clientPrint, clientScan, commandInfo, arguments, user);
    }

    private CommandInfo commandInfoRequest(ClientPrint clientPrint, ClientScan clientScan, String commandName,
                                           User user) throws Exception {
        CommandInfoRequest commandInfoRequest = new CommandInfoRequest(commandName, new String[] {user.login, user.password});
        Request request = new Request(commandInfoRequest);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
        clientPrint.println(jsonRequest);

        String jsonCommandInfo = clientScan.readLine();
        objectMapper = new ObjectMapper();
        CommandInfo commandInfo = objectMapper.readValue(jsonCommandInfo, CommandInfo.class);
        return commandInfo;
    }

    private boolean executeCommandRequest(ClientPrint clientPrint, ClientScan clientScan, CommandInfo commandInfo,
                                       CommandArguments commandArguments, User user) throws Exception {
        CommandExecutionRequest commandExecutionRequest = new CommandExecutionRequest(commandInfo, commandArguments,
                new String[] {user.login, user.password});
        Request request = new Request(commandExecutionRequest);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String jsonRequest = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);

        clientPrint.println(jsonRequest);
        String commandOutPut = clientScan.readLine();

        System.out.println(Client.COLORS[(Client.COLOR_PTR++) % 7] + commandOutPut);

        ArrayList<String> nonAuthResponses = new ArrayList<>();
        nonAuthResponses.add("Wrong password.");
        nonAuthResponses.add("There is no user with this login.");

        return nonAuthResponses.contains(commandOutPut);
    }
}
