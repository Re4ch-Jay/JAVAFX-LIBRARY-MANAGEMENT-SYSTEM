
package librarymanagementsystem.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    /**
     * @return Connection if success otherwise null
     */
    public static Connection connectDb(){
        // default username="root"; password="root"; port="3306"
        Env env = new Env("root", "root", "3306");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:"+env.getPort()+"/book", env.getUsername(), env.getPassword());
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }

}