package client.databaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private final String url;
    private final String login;
    private final String password;

    public ConnectionManager(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,login,password);
    }

    public boolean isConnected() throws SQLException {
        if (getConnection() == null||getConnection().isClosed()) {
            return false;
        }
        else return true;
    }
}
