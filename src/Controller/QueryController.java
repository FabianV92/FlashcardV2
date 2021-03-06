package Controller;

import Model.FlashcardData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class QueryController extends DisplayFlashcards {

    // Fields
    public int currentFlashcard = 0;
    public int storedScore = 0;
    @FXML
    TextArea queryFlashContent;
    @FXML
    Label queryTotalQuestions;
    @FXML
    Label queryCorrScore;
    @FXML
    Label queryWroScore;

    @FXML
    TextArea queryFlahName;

    @FXML
    public void randomFlashcard() {
        displayTotalQuestions();
        displayFlashcardName();
    }

    public void displayTotalQuestions() {
        queryTotalQuestions.setText("Total Questions " + FlashcardData.container.size());
    }

    /* Checking condition for start method. Method can only call randomFlashcard method once if
    the user decide to start query of the flashcards(Pressed start method) */
    @FXML
    public void startMethod() {
        if (queryTotalQuestions.getText().equals("Total Questions 0")) {
            randomFlashcard(); // display Total questions
        }
        if (FlashcardData.container.size() == 0) {
            queryFlashContent.setText("No flashcards are available or no folder has been chosen");
        }
        // Setting restart condition and resetting all values and call the randomFlashcard method
        if (currentFlashcard == FlashcardData.container.size()) {
            currentFlashcard = 0;
            storedScore = 0;
            queryTotalQuestions.setText("Total Questions 0");
            queryWroScore.setText("Wrong answer 0");
            queryCorrScore.setText("Correct answer 0");
            queryFlashContent.setText("");
            randomFlashcard();
        }
    }

    public void displayFlashcardName() {
        if (currentFlashcard != FlashcardData.container.size()) {
            queryFlahName.setText(soutFlashName(currentFlashcard));
        }
    }


    public void displayCorrectAnswer() {
        if (!queryTotalQuestions.getText().equals("Total Questions 0")) {
            if (storedScore < FlashcardData.container.size()) {
                queryFlahName.setText(soutFlashName(currentFlashcard));
                queryFlashContent.setText(soutFlashContent(currentFlashcard));
            }
        }
    }

    public void dontKnowAnswer() {

        if (!queryTotalQuestions.getText().equals("Total Questions 0")) {
            if (storedScore < FlashcardData.container.size()) {
                currentFlashcard++;
                displayFlashcardName();

                String currentScore = queryWroScore.getText().replaceAll("\\D", "");
                int intCurStore = Integer.parseInt(currentScore);
                intCurStore++;
                queryWroScore.setText("Wrong answer " + (intCurStore));
                storedScore++;
                queryFlashContent.setText("");
            }
            if (storedScore == FlashcardData.container.size()) {
                queryFlahName.setText("");
                queryFlashContent.setText("You finished all flashcards :) \nPRESS the Home button to come back to the " +
                        "main menu or press the \"Press me to start\"\nbutton to start over again.");
            }
        }
    }

    public void knowAnswer() {

        if (!queryTotalQuestions.getText().equals("Total Questions 0")) {
            if (storedScore < FlashcardData.container.size()) {
                currentFlashcard++;
                displayFlashcardName();

                String currentScore = queryCorrScore.getText().replaceAll("\\D", "");
                int intCurStore = Integer.parseInt(currentScore);
                intCurStore++;
                queryCorrScore.setText("Correct answer " + (intCurStore));
                storedScore++;
                queryFlashContent.setText("");
            }
            if (storedScore == FlashcardData.container.size()) {
                queryFlahName.setText("");
                queryFlashContent.setText("You finished all flashcards :) \nPRESS the Home button to come back to the " +
                        "main menu or press the \"Press me to start\"\nbutton to start over again.");
            }
        }
    }
    public void switchSceneMain(ActionEvent e) {
        ControlScenes c = new ControlScenes();
        c.switchSceneMain(e);
    }

}
