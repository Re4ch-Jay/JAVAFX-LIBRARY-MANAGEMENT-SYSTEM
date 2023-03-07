
package librarymanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AuthController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

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

        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";

        try {
            Alert alert;

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText().trim());
            prepare.setString(2, password.getText().trim());

            result = prepare.executeQuery();

            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    // IF CORRECT USERNAME AND PASSWORD

                    GetData.username = username.getText();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();

                    // TO HIDE YOUR LOGIN FORM
                    loginBtn.getScene().getWindow().hide();

                    switchToDashboard();

                } else { // IF WRONG USERNAME OR PASSWORD
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
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

            if (username.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all the fill");
                alert.show();
            } else {
                if (result.isBeforeFirst()) {
                    System.out.println("This username is already existed");
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("This username is already existed");
                    alert.show();
                } else {
                    prepare = connect.prepareStatement("INSERT INTO admin(username, password) VALUES(?, ?)");
                    prepare.setString(1, username.getText().trim());
                    prepare.setString(2, password.getText().trim());
                    prepare.executeUpdate();

                    System.out.println("Sign up success");
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully sign up");
                    alert.showAndWait();

                    switchToLogin();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void switchToDashboard () throws IOException {
        signInBtn.getScene().getWindow().hide();

        // LINK YOUR DASHBOARD FORM : )
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("AuthDesign.fxml"));
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
