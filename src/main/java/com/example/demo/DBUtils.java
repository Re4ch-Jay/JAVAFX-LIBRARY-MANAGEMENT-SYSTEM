package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {

        Parent root = null;
        if(username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoginController loginController = loader.getController();
                loginController.setUserInformation(username);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));

        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password, String email) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "root");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM user where username = ? and email = ?");
            psCheckUserExists.setString(1, username);
            psCheckUserExists.setString(2, email);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already existed");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already existed");
                alert.show();
            }else {
                psInsert = connection.prepareStatement("INSERT INTO user (username, password, email) values(?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, email);
                psInsert.executeUpdate();

                changeScene(event, "dashboard.fxm", "Welcome to LMS", username);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT password, username FROM user where username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid credentials");
                alert.show();
            }else {
                System.out.println("User found");
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedUsername = resultSet.getString("username");
                    if(retrievedPassword.equals(password)) {
                        changeScene(event, "dashboard.fxml", "Welcome to LMS", username);
                    } else {
                        System.out.println("Password is not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The password is not correct");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
