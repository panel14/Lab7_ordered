package itmo.service;

import java.io.Serializable;

/**
 * class for storing data about the user
 */
public class User implements Serializable {
    private static final long serialVersionUID = 98L;

    public String login;
    public String password;

    public User() {}

    public User(String[] userData) {
        login = userData[0];
        password = userData[1];
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
