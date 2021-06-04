package Controller;

import Model.FlashcardData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseCreateFolder extends ControlScenes implements Initializable {

    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    TextField createFolderTxt;


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

    @FXML
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
    public void getFolderPath(ActionEvent e) {
        CreateController cc = new CreateController();
        String str = choiceBox.getValue();
        FlashcardData.currentFolder = str.trim();
        cc.loadContainer();
    }

    // Creates a folder
    public void createFolder(ActionEvent e) {
        String succCreated = "Successful created";
        if (!createFolderTxt.getText().equals(succCreated)) {
            String nameOfFolder = "-" + createFolderTxt.getText();
            File theDir = new File(nameOfFolder);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            File datPath = new File("-" + createFolderTxt.getText() + "/data.dat");
            try {
                FileWriter fw = new FileWriter(datPath);
                fw.flush();
                fw.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            createFolderTxt.setText(succCreated);
            choiceBox.getItems().setAll(displayFolders());
        }
    }

    public void deleteFolder(ActionEvent e) throws IOException {
        File filePath = new File(".\\" + "-" + choiceBox.getValue().trim());
        if (filePath.delete()) {
            System.out.println("Successful deleted");
        } else
            System.out.println("You're using windows, could not delete the file");

    }
}

