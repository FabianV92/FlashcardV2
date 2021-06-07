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
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The ChooseCreateFolder class extends from ControlScenes and implements the Interface initializable.
 * The class is the controller class for the creation/choosing folder for the ChooseCreateFolder FXML file and contains
 * itself logic.
 *
 * @author Fabian Valerius
 * @version 2.0
 */
public class ChooseCreateFolder extends ControlScenes implements Initializable {

    // Fields
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    TextField createFolderTxt;


    /**
     * The displayFolders method reads the root path of the app folder via list() method and returns a String array
     * which contains the names of the folders of the flashcards.
     *
     * @return returns a String array which folder names of the flashcards.
     */
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


    // Initializing and filling the choice box with content implemented from Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            choiceBox.getItems().addAll(displayFolders());
            choiceBox.setOnAction(this::getFolderPath);
        } catch (Exception e) {
            System.out.println("Need to handle correctly");
        }
    }

    /**
     * If choice box value got chosen the value will be stored  into the currentFolder variable inside of the
     * FlashcardData class. The loadContainer method of the CreateController class gets called and the
     * Container from FlashcardData will be filled with the information from the chosen folder inside the data.dat file
     *
     * @param e is the Action event which happens when the user press the choice box with left click.
     */
    public void getFolderPath(ActionEvent e) {
        CreateController cc = new CreateController();
        String str = choiceBox.getValue();
        FlashcardData.currentFolder = str.trim();
        // Loads/fill the container with data(flashcard data) from the chosen folder
        cc.loadContainer();
    }

    /**
     * The method createFolder creates a new folder if the 'Create' button got pressed(if not already exist) and creates
     * a default empty data.dat file into the jus created folder.
     *
     * @param e the action even which the method gets from the user when 'create' button get pressed
     */
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

    /**
     * The deleteFolder method reads the current stored value of the choice box and deletes the file
     * Currently under windows not working and not tested under linux or macOS.
     *
     * @param e takes an action event of the 'Delete button'
     * @throws IOException
     */
    public void deleteFolder(ActionEvent e) throws IOException {
        File filePath = new File(".\\" + "-" + choiceBox.getValue().trim());
        if (filePath.delete()) {
            System.out.println("Successful deleted");
        } else
            System.out.println("You're using windows, could not delete the file");
    }
}

