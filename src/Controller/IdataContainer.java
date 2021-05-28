package Controller;

import Model.FlashcardData;

import java.util.List;
import java.util.Map;

public interface IdataContainer {

    void addFlashcard(FlashcardData flashcardData);
    boolean deleteFlashcard(Integer id);
    String displayOneFlashcard(Integer id);
    List<FlashcardData> displayAllFlashcards();

}
