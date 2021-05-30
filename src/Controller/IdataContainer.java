package Controller;

import Model.FlashcardData;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IdataContainer {

    void addFlashcard(ActionEvent e);
    boolean deleteFlashcard(Integer id);
    String displayOneFlashcard(Integer id);
    void displayAllFlashcards();

}
