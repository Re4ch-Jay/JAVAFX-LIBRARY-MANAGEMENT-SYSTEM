package librarymanagementsystem.config;

public class Env {
    private String password = "root";
    private String username = "root";
    private String port = "3306";

    public Env (String username, String password, String port) {
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public Env() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}