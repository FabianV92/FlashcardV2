package View;


import Controller.Controller;
import Model.FlashcardData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;




public class Main extends Application {



    public static void main(String[] args) {
        launch(args);
        Controller c = new Controller();
        FlashcardData f = new FlashcardData("tsets","sts");
        c.addFlashcard(f);
        System.out.println(c.displayAllFlashcards());

        FlashcardData f1 = new FlashcardData("affe","sts");
        c.addFlashcard(f1);
        System.out.println(c.displayAllFlashcards());

        FlashcardData f2 = new FlashcardData("Vogel","sts");
        c.addFlashcard(f2);
        System.out.println(c.displayAllFlashcards());

        FlashcardData f3 = new FlashcardData("Hans","sts");
        c.addFlashcard(f3);
        System.out.println(c.displayAllFlashcards());


    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        Scene scene = new Scene(root);
        // Adding css
        String css = this.getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);


        // Adding app icon and title
        Image icon = new Image("flashcardLogo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Flashcard-App");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
