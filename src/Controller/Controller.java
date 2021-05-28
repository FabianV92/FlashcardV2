package Controller;

import Model.FlashcardData;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements IdataContainer{
    // Fields
    private Stage stage;
    private Scene scene;
    private Parent root;
    public Map<Integer, FlashcardData> mapContainer = new HashMap<Integer, FlashcardData>();
    public List<FlashcardData> list = new ArrayList<FlashcardData>();



    // ------Scene controls------
    public void switchSceneMain(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void switchCreate(ActionEvent e) {
        try {
            Controller s = new Controller();
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
            Controller s = new Controller();
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
            Controller s = new Controller();
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

    // Displaying Folders
    @FXML ChoiceBox choiceBox;

    public String displayFolders() {
        System.out.println("working");
        choiceBox.setItems(FXCollections.observableArrayList(
        "New Document", "Open " ));
        choiceBox.show();
        return "";
    }


    @Override
    public void addFlashcard(FlashcardData flashcardData) {
        System.out.println(FlashcardData.id);
        this.mapContainer.put(FlashcardData.id,flashcardData);
    }

    @Override
    public boolean deleteFlashcard(Integer id) {
        return false;
    }

    @Override
    public String displayOneFlashcard(Integer id) {
        return null;
    }

    @Override
    public List<FlashcardData> displayAllFlashcards() {
        List<FlashcardData> l = new ArrayList<>(this.mapContainer.values());

        return l;
    }
}
