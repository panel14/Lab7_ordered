package itmo.service;

/**
 * class for database response
 */
//Используется в методах аутентификации и регистрации
//Нужен, чтобы иметь логический результат операции и комментарий к нему
public class DBResponse {
    public boolean result;
    public String comment;

    public DBResponse(boolean result, String comment) {
        this.comment = comment;
        this.result = result;
    }
}
