
package librarymanagementsystem.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connectDb(){
        Env env = new Env(); // default port=3306; username="root"; password="root"

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:"+env.getPort()+"/book", env.getUsername(), env.getPassword()); // address, database username, database password
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }

}