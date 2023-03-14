package librarymanagementsystem.config;

public class Env {
    /**
     * @defaul password, username, port
     */
    private String password = "root";
    private String username = "root";
    private String port = "3306";


    /**
     * @param username
     * @param password
     * @param port
     */
    public Env (String username, String password, String port) {
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public Env() {}

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port
     */
    public void setPort(String port) {
        this.port = port;
    }
}