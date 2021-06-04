package View;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
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
