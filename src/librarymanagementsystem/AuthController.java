
package librarymanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import librarymanagementsystem.config.Database;
import librarymanagementsystem.helper.CustomAlert;
import librarymanagementsystem.helper.GetData;
import librarymanagementsystem.helper.PasswordEncryption;
import librarymanagementsystem.helper.Validation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

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

    private DialogPane dialog;

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
                CustomAlert customAlert = new CustomAlert();
                customAlert.errorAlert("Please fill in all the blank fields");
            } else {
                if (result.next()) {

                    String hashedPassword;
                    // IF USERNAME EXISTS

                    String storedEncryptedPass = result.getString("password");
                    hashedPassword = passwordEncryption.hashPassword(password.getText().trim());

                    if (storedEncryptedPass.equals(hashedPassword)) {
                        // IF CORRECT PASSWORD

                        GetData.username = username.getText();
                        CustomAlert customAlert = new CustomAlert();
                        customAlert.infoAlert("Successfully Login");

                        // TO HIDE YOUR LOGIN FORM
                        loginBtn.getScene().getWindow().hide();

                        switchToDashboard();

                    } else { // IF WRONG PASSWORD
                        CustomAlert customAlert = new CustomAlert();
                        customAlert.errorAlert("Wrong Password");
                    }
                } else { // IF WRONG USERNAME
                    CustomAlert customAlert = new CustomAlert();
                    customAlert.errorAlert("Wrong Username");
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
                CustomAlert customAlert = new CustomAlert();
                customAlert.errorAlert("Please fill all blank fields");
            } else {
                if (result.isBeforeFirst()) {
                    System.out.println("This username already exists");
                    CustomAlert customAlert = new CustomAlert();
                    customAlert.errorAlert("The username is already existed");
                } else {
                    Validation validation = new Validation();
                    boolean validPassword = validation.isValidPassword(password.getText());
                    if(!validPassword) {
                        CustomAlert customAlert = new CustomAlert();
                        customAlert.errorAlert("The password must be strong");
                        return;
                    }
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
                        CustomAlert customAlert = new CustomAlert();
                        customAlert.infoAlert("Successfully Sign Up");

                        switchToLogin();
                    }else{
                        CustomAlert customAlert = new CustomAlert();
                        customAlert.errorAlert("Password doesn't match");
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
