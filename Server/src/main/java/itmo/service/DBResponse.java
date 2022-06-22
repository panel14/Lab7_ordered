package itmo.service;

public class DBResponse {
    public boolean result;
    public String comment;

    public DBResponse(boolean result, String comment) {
        this.comment = comment;
        this.result = result;
    }
}
