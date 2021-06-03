package Controller;

import Model.FlashcardData;

public abstract class DisplayFlashcards {



    public String soutFlashName(int currentCount) {
        String[] strArr;
        String str;

        str = FlashcardData.container.get(currentCount).toString().replaceAll("\\[|\\]", "").trim();
        strArr = str.split("_");
        return strArr[0];
    }

    public String soutFlashContent(int currentCount) {
        String flashCOntentTxt = "\n";
        return flashCOntentTxt + FlashcardData.container.get(currentCount).toString()
                .replaceAll("(^.)", "")
                .replaceAll("(.*?_)", "").replaceAll("^.|(.$)", "").trim();
    }


}
