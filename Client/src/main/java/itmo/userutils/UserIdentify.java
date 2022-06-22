package itmo.userutils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import itmo.io.*;
import itmo.requests.AuthenticationRequest;
import itmo.requests.Request;

/**
 * class for user identification
 */
public class UserIdentify {
    private static final Scannable scanner = new ConsoleScan();
    private static final Printable printer = new ConsolePrint();

    /**
     *
     * @param clientPrint
     * @param clientScan
     * @return user - if he is identified
     * @throws Exception
     */
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

    /**
     * method sends a request to authenticate/register a user to the database
     * @param isReg - type of request
     * @param user - user's data
     * @param cp
     * @param cs
     * @return
     * @throws Exception
     */
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

    /**
     *
     * @param regex is a string pattern
     * @return valid data (login/password)
     * @throws Exception
     */
    private static String getValidString(String regex) throws Exception {
        String string = "";
        while (true) {
            string = scanner.readLine();
            if (string.equals("")) continue;
            if (string.matches(regex))
                break;
            printer.println("Incorrect input. Please, try again:");
        }
        return string;
    }

    /**
     * get user's data
     * @return user's data
     * @throws Exception
     */
    private static String[] getInfo() throws Exception {
        printer.println("Enter login (4 to 12 characters): ");
        String login = getValidString("^[a-zA-z0-9_]{5,17}$");

        printer.println("Enter password: (4 to 20 characters)");
        String password = getValidString("^.{5,20}$");

        return new String[]{login, password};
    }
}
