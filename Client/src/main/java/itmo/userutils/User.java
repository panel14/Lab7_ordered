package itmo.userutils;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 99L;

    public String login;
    public String password;

    public User() {}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
