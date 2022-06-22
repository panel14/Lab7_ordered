package itmo.servers;

import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.io.SelfPrint;
import itmo.manager.CommandManager;
import itmo.requests.*;
import itmo.service.DBResponse;
import itmo.service.DataBase;
import itmo.service.User;
import itmo.utility.CommandInfo;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class TaskThread extends Thread {
    private final ThreadInfo info;
    private final ExecutorService writingThread;
    private final Request request;

    public TaskThread(ThreadInfo info, ExecutorService writingThread, Request request) {
        this.info = info;
        this.writingThread = writingThread;
        this.request = request;
    }

    @Override
    public void run(){
        String serverAnswer = "Unknown request.";
        try {
            if (request.requestType.equals(AuthenticationRequest.class)) {
                AuthenticationRequest auth = (AuthenticationRequest) request.request;
                User user = new User(auth.userData[0], auth.userData[1]);
                DBResponse response;
                if (auth.isRegistration)
                    response = DataBase.register(user);
                else
                    response = DataBase.isAuth(user);

                if (response.result) serverAnswer = "Success";
                else serverAnswer = response.comment;
            }
            if (request.requestType.equals(CommandInfoRequest.class)) {
                CommandInfoRequest commandInfoRequest = (CommandInfoRequest) request.request;
                DBResponse response = DataBase.isAuth(new User(commandInfoRequest.userData));
                if (response.result)
                    serverAnswer = commandInfoRequestHandler((commandInfoRequest));
                else serverAnswer = response.comment;
            }
            if (request.requestType.equals(CommandExecutionRequest.class)) {
                CommandExecutionRequest commandExecutionRequest = (CommandExecutionRequest) request.request;
                DBResponse response = DataBase.isAuth(new User(commandExecutionRequest.userData));
                if (response.result)
                    serverAnswer = commandExecutionHandler(commandExecutionRequest, new User(commandExecutionRequest.userData));
                else serverAnswer = response.comment;
            }
            if (request.requestType.equals(InitialRequest.class)) {
                serverAnswer = initialRequestHandler((InitialRequest) request.request);
            }
        } catch (Exception e) {
            serverAnswer = "Processing error:" + e.getMessage();
        }
        finally {
            writingThread.execute(new WritingThread(info, serverAnswer));
        }
    }

    private String commandInfoRequestHandler(CommandInfoRequest commandInfoRequest) throws IOException {
        CommandInfo commandInfo = CommandManager.getCommandInfo(commandInfoRequest.commandName);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(commandInfo);
    }

    private String commandExecutionHandler(CommandExecutionRequest commandExecutionRequest, User user) throws Exception {
        SelfPrint selfPrint = new SelfPrint();

        CommandManager.constructCommand(commandExecutionRequest.commandInfo, commandExecutionRequest.commandArguments, selfPrint).execute();
        return selfPrint.getString();
    }
    private String initialRequestHandler(InitialRequest initialRequest) {
        return String.valueOf(initialRequest.number);
    }
}
