package itmo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.exceptions.ClientRuntimeException;
import itmo.io.*;
import itmo.manager.ClientCommandManager;
import itmo.requests.InitialRequest;
import itmo.requests.Request;
import itmo.userutils.User;
import itmo.userutils.UserIdentify;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.nio.channels.DatagramChannel;
import java.util.Random;

public class Client {

    public static final Random r = new Random();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String BLUE_BRIGHT = "\033[0;94m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW


    public static final String[] COLORS = {ANSI_RED, YELLOW_BRIGHT, ANSI_YELLOW, ANSI_GREEN, BLUE_BRIGHT, ANSI_BLUE, ANSI_PURPLE};
    public static int COLOR_PTR = 0;

    public static void main(String[] args) throws Exception {

        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);

        ClientScan clientScan = new ClientScan(datagramChannel);
        ClientPrint clientPrint = new ClientPrint(datagramChannel, 8181);


        waitForConnection(clientPrint, clientScan);

        ClientCommandManager clientCommandManager = new ClientCommandManager();

        User user;
        while ((user = UserIdentify.readUser(clientPrint, clientScan)) == null)
        System.out.println("Authorization fault.");

        while (true) {
            System.out.println("Enter the command:");
            try {
                clientCommandManager.executeCommand(clientPrint, clientScan, new ConsoleScan(), user);
            } catch (ClientRuntimeException clientRuntimeException){
                waitForConnection(clientPrint, clientScan);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    private static boolean sendInitRequest(ClientPrint clientPrint, ClientScan clientScan) throws Exception {
        Random random = new Random();
        int number = random.nextInt();
        InitialRequest initialRequest = new InitialRequest(number);
        Request request = new Request(initialRequest);

        clientPrint.println(new ObjectMapper().writeValueAsString(request));
        int num = Integer.parseInt(clientScan.readLine());

        return num == number;
    }

    private static void waitForConnection(ClientPrint clientPrint, ClientScan clientScan) throws InterruptedException {
        while (true){
            System.out.println("connecting to server...");
            try {
                sendInitRequest(clientPrint, clientScan);
            } catch (Throwable t) {
                continue;
            }

            break;
        }
        System.out.println("connection established!");
    }

}