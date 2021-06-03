package Model;

import java.util.ArrayList;

public class FlashcardData {

    // Fields
    public String flashcardName;
    public String flashcardContent;
    public static String currentFolder = null;
    public static ArrayList<Object> container = new ArrayList<>();

    public FlashcardData(String flashcardName, String flashcardContent) {
        this.flashcardName = flashcardName;
        this.flashcardContent = flashcardContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(flashcardName).append(" ").append(flashcardContent).toString();
    }
}
