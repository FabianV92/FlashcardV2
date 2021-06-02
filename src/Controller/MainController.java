package Controller;

import Model.FlashcardData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    // Fields
    public static ArrayList<Object> container = new ArrayList<>();

    @FXML
    ChoiceBox<String> choiceBox;


    // Displaying Folders
    public String[] displayFolders() {
        File f = new File(".\\");
        String strArrContainer[];
        String str = "";
        strArrContainer = f.list();
        int countFlashcardFolders = 0;
        // Checking how many Folders and set Array length

        for (String i : strArrContainer) {
            if (i.substring(0, 1).equals("-")) {
                countFlashcardFolders++;
                str += (i) + (" ");
            } else continue;
        }
        str = str.trim().replaceAll("^.", "");
        String[] choiceBArr = str.split("-");
        return choiceBArr;
    }

    // Initializing and filling the choice box with content
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            choiceBox.getItems().addAll(displayFolders());
            choiceBox.setOnAction(this::getFolderPath);
        } catch (Exception e) {
            System.out.println("Need to handle correctly");
        }
    }


    // Saves value(current folder) in the static String in the Model
    private void getFolderPath(ActionEvent e) {
        CreateController cc = new CreateController();
        String str = choiceBox.getValue();
        FlashcardData.currentFolder = str.trim();
        cc.loadContainer();
    }



}

