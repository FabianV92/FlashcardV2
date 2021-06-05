package Controller;

import Model.FlashcardData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;


public class EditController extends DisplayFlashcards implements Initializable {

    private int currentFlashCard = -1;
    @FXML
    ChoiceBox<String> chooseFsChoice;
    @FXML
    TextArea textFieldContent;
    @FXML
    Button saveChanges;
    @FXML
    TextArea textFieldName;

    public ArrayList<String> displayFlashNames() {
        String[] tmpArr;
        String tmpStr;
        ArrayList<String> containerNames = new ArrayList<>();
        for (int i = 0; i < FlashcardData.container.size(); i++) {
            tmpStr = FlashcardData.container.get(i).toString().trim().replaceAll(("^."), "");
            tmpArr = tmpStr.split("_");
            containerNames.add(tmpArr[0]);
        }
        return containerNames;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseFsChoice.getItems().addAll(displayFlashNames());
        chooseFsChoice.setOnAction(this::getAndDisplayCard);
    }

    public void getAndDisplayCard(ActionEvent e) {
        System.out.println("pressed");
        String currentFlashName = chooseFsChoice.getValue();
        String[] tmpArr;
        String tmpStr;
        for (int i = 0; i < FlashcardData.container.size(); i++) {
            tmpStr = FlashcardData.container.get(i).toString().trim().replaceAll(("^."), "");
            tmpArr = tmpStr.split("_");

            if (tmpArr[0].equals(currentFlashName)) {
                System.out.println("pressed again");
                textFieldName.setText(soutFlashName(i));
                textFieldContent.setText(soutFlashContent(i));
                currentFlashCard = i;
            }
        }
    }

    public void formatUpdateInput(ActionEvent e) {
        boolean notTheSameName = true;
        if (!textFieldContent.getText().trim().equals("Successful deleted.") ||
                !(textFieldContent.getText().trim().equals("Successful updated."))) {
            CreateController cs = new CreateController();

            //String flashName = changedText.trim().replaceAll("[\r\n]+([^\n\r]*)", "");

            String flashContent = textFieldContent.getText();
            String flashName = textFieldName.getText();

            String[] strTmp;
            for (int i = 0; i < FlashcardData.container.size(); i ++) {
                String tmp = FlashcardData.container.get(i).toString();
                strTmp = tmp.split("_");

                if (strTmp[0].replaceAll("\\[","").equals(textFieldName.getText())){
                    System.out.println("equald");
                    notTheSameName = false;
                }
            }

            if (notTheSameName && !textFieldName.getText().equals("Flash name already exists!")) {
            // Need to do this do add the [, ] because other method needs this characters
            try {
                FlashcardData.container.set(currentFlashCard, "[" + flashName.trim() + "_, " + flashContent.trim() + "]");
                cs.saveToFile();
            } catch (IndexOutOfBoundsException exception) {
                currentFlashCard = 0;
            }

            textFieldName.setText("");
            textFieldContent.setText("Successful updated.");
            currentFlashCard = -1;
            chooseFsChoice.getItems().setAll(displayFlashNames());
            }
            else textFieldName.setText("Flash name already exists!");
            notTheSameName = true;
        }
    }

    public void switchSceneMain(ActionEvent e) {
        ControlScenes cs = new ControlScenes();
        cs.switchSceneMain(e);
    }

    public void delete(ActionEvent e) {
        if (currentFlashCard != -1) {
            CreateController cc = new CreateController();
            FlashcardData.container.remove(currentFlashCard);
            textFieldName.setText("");
            textFieldContent.setText("Successful deleted.");
            currentFlashCard = -1;
            chooseFsChoice.getItems().setAll(displayFlashNames());
            cc.saveToFile();
        }
    }
}

