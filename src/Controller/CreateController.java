package Controller;

import Model.FlashcardData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class CreateController extends ControlScenes {
    @FXML
    private TextArea flashContent;
    @FXML
    private TextField flashName;


    // Saves the container array into the data.dat of the chosen folder
    public void saveToFile() {
        File f = new File("-" + FlashcardData.currentFolder + "/data.dat");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(FlashcardData.container);

            bos.flush();
            os.flush();
            bos.close();
            os.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void loadContainer() {
        File f = new File("-" + FlashcardData.currentFolder + "/data.dat");
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            ObjectInputStream ois = new ObjectInputStream(bis);
            FlashcardData.container = (ArrayList<Object>) ois.readObject();
            bis.close();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Not found the file");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("empty folder :)");
        }
    }

    // Creating array which contains data
    public void addFlashcard(ActionEvent e) {

        boolean notTheSameName = true;
        String flashcardName = flashName.getText();
        String flashcardContent = flashContent.getText();

        // Safety loop to check if flahName already exists in the container or not
        for (int i = 0; i < FlashcardData.container.size(); i++) {
            String tmp = FlashcardData.container.get(i).toString().replaceAll("\\[", "");
            String[] tmpArr = tmp.split("_");
            if (tmpArr[0].equals(flashName.getText())) {
                notTheSameName = false;
            }
        }

        if (notTheSameName && !flashName.getText().equals("Flash card name already exist!")) {
            ArrayList<String> l = new ArrayList<>();
            l.add(flashcardName + "_");
            l.add(flashcardContent);

            // Adding the arraylist to the container
            FlashcardData.container.add(l);
            saveToFile();
            forwardUser(e);
        } else {
            flashName.setText("Flash card name already exist!");
            notTheSameName = true;
        }
    }

    // Forwarding the user to the user to the successful added flashcard page
    @FXML
    public void forwardUser(ActionEvent e) {
        switchSuccCreatePage(e);
    }

    @Override
    public void switchSceneMain(ActionEvent e) {
        super.switchSceneMain(e);
    }
}
