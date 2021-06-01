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
    public static ArrayList<Object> container = new ArrayList<>();
    public int currentFlashcard = 0;
    public int storedScore = 0;
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    private TextArea flashContent;
    @FXML
    private TextField flashName;
    @FXML
    TextArea queryFlashContent;
    @FXML
    Label queryTotalQuestions;
    @FXML
    Label queryCorrScore;
    @FXML
    Label queryWroScore;

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
            this.container = (ArrayList<Object>) ois.readObject();
            bis.close();
            ois.close();
            container.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            System.out.println("Not found the file");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("empty folder :)");
        }
    }


    // Creating array which contains data
    @Override
    public void addFlashcard(ActionEvent e) {

        String flashcardName = flashName.getText();
        String flashcardContent = flashContent.getText();
        ArrayList<String> l = new ArrayList<>();
        l.add(flashcardName + "_");
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

    @FXML
    public void randomFlashcard() {
        displayTotalQuestions();
        displayFlashcardName();
    }

    public void displayTotalQuestions() {
        queryTotalQuestions.setText("Total Questions " + container.size());
    }

    /* Checking condition for start method. Method can only call randomFlashcard method once if
    the user decide to start query of the flashcards(Pressed start method) */
    @FXML
    public void startMethod() {
        if (queryTotalQuestions.getText().equals("Total Questions 0")) {
            randomFlashcard(); // display Total questions
        }
        // Setting restart condition and resetting all values and call the randomFlashcard method
        if (currentFlashcard == container.size()) {
            currentFlashcard = 0;
            storedScore = 0;
            queryTotalQuestions.setText("Total Questions 0");
            queryWroScore.setText("Wrong answer 0");
            queryCorrScore.setText("Correct answer 0");
            randomFlashcard();
        }
    }

    public void displayFlashcardName() {
        if (currentFlashcard != container.size()) {
            queryFlashContent.setText(soutFlashName());
        }
    }

    public String soutFlashName() {
        String[] strArr = new String[2];
        String str;

        str = container.get(currentFlashcard).toString().replaceAll("\\[|\\]", "").trim();
        strArr = str.split("_");
        return strArr[0];
    }

    public String soutFlashContent() {
        String flashCOntentTxt = "\n";
        return flashCOntentTxt + container.get(currentFlashcard).toString()
                .replaceAll("(^.)", "")
                .replaceAll("(.*?_)", "").replaceAll("^.|(.$)", "").trim();
    }

    public void displayCorrectAnswer() {
        if (!queryTotalQuestions.getText().equals("Total Questions 0")) {
            queryFlashContent.setText(soutFlashName() +
                    "\n" + soutFlashContent());
        }
    }

    public void dontKnowAnswer() {

        if (!queryTotalQuestions.getText().equals("Total Questions 0")) {
            if (storedScore < container.size()) {
                currentFlashcard++;
                displayFlashcardName();

                String currentScore = queryWroScore.getText().replaceAll("\\D","");
                int intCurStore = Integer.parseInt(currentScore);
                intCurStore ++;
                queryWroScore.setText("Wrong answer " + (intCurStore));
                storedScore++;
            }
            if (storedScore == container.size()) {
                queryFlashContent.setText("You finished all flashcards :) \nPRESS the Home button to come back to the " +
                        "main menu.");
            }
        }
    }

    public void knowAnswer() {

        if (!queryTotalQuestions.getText().equals("Total Questions 0")) {
            if (storedScore < container.size()) {
                currentFlashcard++;
                displayFlashcardName();

                String currentScore = queryCorrScore.getText().replaceAll("\\D","");
                int intCurStore = Integer.parseInt(currentScore);
                intCurStore++;
                queryCorrScore.setText("Correct answer " + (intCurStore));
                storedScore++;
            }
            if (storedScore == container.size()) {
                queryFlashContent.setText("You finished all flashcards :) \nPRESS the Home button to come back to the " +
                        "main menu.");
            }
        }
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
