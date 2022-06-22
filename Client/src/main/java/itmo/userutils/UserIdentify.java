package itmo.userutils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import itmo.io.*;
import itmo.requests.AuthenticationRequest;
import itmo.requests.Request;

public class UserIdentify {
    private static final Scannable scanner = new ConsoleScan();
    private static final Printable printer = new ConsolePrint();

    public static User readUser(ClientPrint clientPrint, ClientScan clientScan) throws Exception {
        printer.println("Authorization. Enter help to learn more:");

        while (true) {
            printer.println("Command: ");
            String command = scanner.readLine();

            switch (command) {
                case "regis":
                    String[] newData = getInfo();
                    if(isCorrect(true, newData, clientPrint, clientScan))
                        return new User(newData[0], newData[1]);
                    return null;
                case "auth":
                    String[] oldData = getInfo();
                    if(isCorrect(false, oldData, clientPrint, clientScan))
                        return new User(oldData[0], oldData[1]);
                    return null;
                case "help":
                    printer.println("-regis -> register a new user\n-auth -> sign up to old users");
                default:
                    printer.println("Некорректный ввод.");
            }
        }
    }

    private static boolean isCorrect(boolean isReg, String[] user, ClientPrint cp, ClientScan cs)
            throws Exception {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(user, isReg);
        Request request = new Request(authenticationRequest);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String jsonRequest = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
        cp.println(jsonRequest);

        String answer = cs.readLine();
        printer.println(answer);
        return answer.equals("Success");
    }

    private static String[] getInfo() throws Exception {
        printer.println("Enter login: ");
        String login = scanner.readLine();

        printer.println("Enter password: ");
        String password = scanner.readLine();

        return new String[]{login, password};
    }
}
