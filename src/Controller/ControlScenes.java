package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ControlScenes {

    Stage stage;
    Scene scene;
    Parent root;


    // ------Scene controls------
    public void switchSceneMain(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            //stage.show();// Need to fix ( Display the folder name in the main page)----------------------------------
            //choiceBox.show();// Need to fix
            //choiceBox.setValue(FlashcardData.currentFolder);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void switchCreate(ActionEvent e) {
        try {
            root = FXMLLoader.load(getClass().getResource("/View/Create.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);

            // Adding css
            String css = this.getClass().getResource("/style.css").toExternalForm();
            scene.getStylesheets().add(css);


            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void switchEdit(ActionEvent e) {
        try {
            root = FXMLLoader.load(getClass().getResource("/View/Edit.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);

            // Adding css
            String css = this.getClass().getResource("/style.css").toExternalForm();
            scene.getStylesheets().add(css);


            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    public void switchQueryCards(ActionEvent e) {
        try {
            root = FXMLLoader.load(getClass().getResource("/View/Query.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);

            // Adding css
            String css = this.getClass().getResource("/style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void switchSuccCreatePage(ActionEvent e) {
        try {
            root = FXMLLoader.load(getClass().getResource("/View/CreateSuccess.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);

            // Adding css
            String css = this.getClass().getResource("/style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public void switchChooseCreateFolder(ActionEvent e) {
        try {
            root = FXMLLoader.load(getClass().getResource("/View/ChooseCreateFolder.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);

            // Adding css
            String css = this.getClass().getResource("/style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
