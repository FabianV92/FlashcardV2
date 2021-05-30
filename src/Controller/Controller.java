package Controller;

import Model.FlashcardData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Controller implements IdataContainer, Initializable {

    // Fields
    public FlashcardData flashcardData;
    public ArrayList<Object> container = new ArrayList<>();
    public List<FlashcardData> list = new ArrayList<FlashcardData>();

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private TextArea flashContent;
    @FXML
    private TextField flashName;


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
        String str = choiceBox.getValue();
        FlashcardData.currentFolder = str.trim();
        loadContainer();
    }

    // Saves the container array into the data.dat of the chosen folder
    private void saveToFile() {
        String[] strArr;
        File f = new File("-" + FlashcardData.currentFolder + "/data.dat");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(container);

            bos.flush();
            os.flush();
            bos.close();
            os.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void loadContainer() {
        File f = new File("-" + FlashcardData.currentFolder + "/data.dat");
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            ObjectInputStream ois = new ObjectInputStream(bis);
            container = (ArrayList<Object>)  ois.readObject();
            bis.close();
            ois.close();
            container.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // Creating array which contains data
    @Override
    public void addFlashcard(ActionEvent e) {

        String flashcardName = flashName.getText();
        String flashcardContent = flashContent.getText();
        ArrayList<String> l = new ArrayList<>();
        l.add(flashcardName);
        l.add(flashcardContent);

        // Adding the arraylist to the container
        container.add(l);
        saveToFile();
        forwardUser(e);
    }

    // Forwarding the user to the user to the successful added flashcard page
    @FXML
    public void forwardUser(ActionEvent e) {
        ControlScenes c = new ControlScenes();
        c.switchSuccCreatePage(e);
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
    public void displayAllFlashcards() {
        ArrayList<String> al = new ArrayList();
        String str;
        for (int i = 0; i < container.size(); i++) {
            str = container.get(i).toString();
            System.out.println("Flashcard " + i + ": " + str);
        }
    }

}
