package itmo.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import itmo.deserializers.RequestDeserializer;
import itmo.exceptions.CollectionException;
import itmo.io.SelfPrint;
import itmo.io.ServerIO;
import itmo.requests.CommandExecutionRequest;
import itmo.requests.CommandInfoRequest;
import itmo.requests.InitialRequest;
import itmo.requests.Request;
import itmo.utility.CommandInfo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Controller {
    private final ServerIO serverIO;
    private final CommandManager commandManager;

    public Controller(ServerIO serverIO, CommandManager commandManager) {
        this.serverIO = serverIO;
        this.commandManager = commandManager;
    }

    public void handleRequest() throws Exception {
        String jsonRequest = serverIO.readLine();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule()
                .addDeserializer(Request.class, new RequestDeserializer()));

        Request request = objectMapper.readValue(jsonRequest, Request.class);

        if (request.requestType.equals(CommandInfoRequest.class)){
            commandInfoRequestHandler((CommandInfoRequest) request.request);
        }
        if (request.requestType.equals(CommandExecutionRequest.class)){
            commandExecutionHandler((CommandExecutionRequest) request.request);
        }
        if (request.requestType.equals(InitialRequest.class)){
            initialRequestHandler((InitialRequest) request.request);
        }
    }

    public void commandInfoRequestHandler(CommandInfoRequest commandInfoRequest) throws IOException {
        CommandInfo commandInfo = CommandManager.getCommandInfo(commandInfoRequest.commandName);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCommandInfo = objectMapper.writeValueAsString(commandInfo);
        serverIO.println(jsonCommandInfo);
    }

    public void commandExecutionHandler(CommandExecutionRequest commandExecutionRequest) throws Exception {
        SelfPrint selfPrint = new SelfPrint();

/*        commandManager.constructCommand(commandExecutionRequest.commandInfo, commandExecutionRequest.commandArguments, selfPrint).execute();
        String result = selfPrint.getString();
        serverIO.println(result);*/
    }
    public void initialRequestHandler(InitialRequest initialRequest) throws IOException {
        serverIO.println(String.valueOf(initialRequest.number));
    }
}
