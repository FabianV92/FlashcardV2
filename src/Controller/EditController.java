package Controller;

import Model.FlashcardData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class EditController extends DisplayFlashcards implements Initializable {

    private int currentFlashCard = 0;

    @FXML
    ChoiceBox<String> chooseFsChoice;
    @FXML
    TextArea textFieldContent;
    @FXML
    Button saveChanges;

    @FXML
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
        String currentFlashName = chooseFsChoice.getValue();
        String[] tmpArr;
        String tmpStr;
        for (int i = 0; i < FlashcardData.container.size(); i++) {
            tmpStr = FlashcardData.container.get(i).toString().trim().replaceAll(("^."), "");
            tmpArr = tmpStr.split("_");

            if (tmpArr[0].equals(currentFlashName)) {
                textFieldContent.setText(soutFlashName(i) + "\n" + soutFlashContent(i));
                currentFlashCard = i;
            }
        }
    }

    @FXML
    public void formatUpdateInput(ActionEvent e) {
        CreateController cs = new CreateController();
        ControlScenes cS = new ControlScenes();
        String changedText = textFieldContent.getText().trim();

        String flashName = changedText.replaceAll("[\r\n]+([^\n\r]*)", "").trim();
        String flashContent = changedText.replaceFirst(flashName, "").trim();


        // Need to do this do add the [, ] because other method needs this characters
        FlashcardData.container.set(currentFlashCard, "[" + flashName + "_, " + flashContent + "]");
        cs.saveToFile();

        textFieldContent.setText("Successful updated");
        currentFlashCard =0;

    }


    @FXML
    public void switchSceneMain(ActionEvent e) {
        ControlScenes cs = new ControlScenes();
        cs.switchSceneMain(e);
    }

    public void switchCreateSuccess(ActionEvent e) {
        ControlScenes cs = new ControlScenes();
        cs.switchSuccCreatePage(e);
    }
}
