package Model;

public class FlashcardData {

    // Fields
    public String flashcardName;
    public String flashcardContent;
    public static Integer id = 0;
    public static String currentFolder = null;

    public FlashcardData(String flashcardName, String flashcardContent) {
        id++;
        this.flashcardName = flashcardName;
        this.flashcardContent = flashcardContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(flashcardName).append(" ").append(flashcardContent).toString();
    }
}
