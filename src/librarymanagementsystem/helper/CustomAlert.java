package librarymanagementsystem.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CustomAlert {
    Alert alert;
    DialogPane dialog;

    /**
     * @param contentText
     */
    public void errorAlert(String contentText) {
        alert = new Alert(Alert.AlertType.ERROR);
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(String.valueOf(getClass().getResource("../styles/alert.css")));
        dialog.getStyleClass().add("dialog");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(IconLogo.icon));
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * @param contentText
     */
    public void infoAlert(String contentText) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(String.valueOf(getClass().getResource("../styles/alert.css")));
        dialog.getStyleClass().add("dialog");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(IconLogo.icon));
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
