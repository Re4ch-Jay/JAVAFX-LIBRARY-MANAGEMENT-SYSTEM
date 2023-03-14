
package librarymanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import librarymanagementsystem.config.Database;
import librarymanagementsystem.helper.GetData;
import librarymanagementsystem.helper.PasswordEncryption;
public class AuthController implements Initializable {
    PasswordEncryption passwordEncryption = new PasswordEncryption();

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm_password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signInBtn;

    @FXML
    private Button close;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;

    public void loginAdmin() {
        System.out.println("Login btn clicked");

        connect = Database.connectDb();

        String sql = "SELECT * FROM admin WHERE username = ?";

        try {
            Alert alert;

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText().trim());

            result = prepare.executeQuery();

            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (result.next()) {

                    String hashedPassword;
                    // IF USERNAME EXISTS

                    String storedEncryptedPass = result.getString("password");
                    hashedPassword = passwordEncryption.hashPassword(password.getText().trim());

                    if (storedEncryptedPass.equals(hashedPassword)) {
                        // IF CORRECT PASSWORD

                        GetData.username = username.getText();

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Login");
                        alert.showAndWait();

                        // TO HIDE YOUR LOGIN FORM
                        loginBtn.getScene().getWindow().hide();

                        switchToDashboard();

                    } else { // IF WRONG PASSWORD
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Password");
                        alert.showAndWait();
                    }
                } else { // IF WRONG USERNAME
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void signUpAdmin() throws IOException {
        System.out.println("Sign up btn clicked");

        connect = Database.connectDb();

        String sql = "SELECT * FROM admin WHERE username = ?";

        try {

            Alert alert;

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            result = prepare.executeQuery();

            if (username.getText().isEmpty() || password.getText().isEmpty() || confirm_password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all the fill");
                alert.show();
            } else {
                if (result.isBeforeFirst()) {
                    System.out.println("This username already exists");
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("This username already exists");
                    alert.show();
                } else {
                    // Hash the password using SHA-256
                    PasswordEncryption passwordEncryption = new PasswordEncryption();
                    String hashedPassword, confHashesPassword;
                    hashedPassword = passwordEncryption.hashPassword(password.getText().trim());
                    confHashesPassword = passwordEncryption.hashPassword(confirm_password.getText().trim());
                    if(hashedPassword.equals(confHashesPassword)){
                        prepare = connect.prepareStatement("INSERT INTO admin(username, password) VALUES(?, ?)");
                        prepare.setString(1, username.getText().trim());
                        prepare.setString(2, hashedPassword);
                        prepare.executeUpdate();

                        System.out.println("Sign up success");
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully signed up");
                        alert.showAndWait();

                        switchToLogin();
                    }else{
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password doesn't match");
                        alert.showAndWait();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void switchToDashboard () throws IOException {
        signInBtn.getScene().getWindow().hide();

        // LINK YOUR DASHBOARD FORM : )
        Parent root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.show();
    }
    public void switchToSignUp() throws IOException {

        signInBtn.getScene().getWindow().hide();

        // LINK YOUR DASHBOARD FORM : )
        Parent root = FXMLLoader.load(getClass().getResource("fxml/signup.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin() throws IOException {

        loginBtn.getScene().getWindow().hide();

        // LINK YOUR DASHBOARD FORM : )
        Parent root = FXMLLoader.load(getClass().getResource("fxml/AuthDesign.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.show();
    }



    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
