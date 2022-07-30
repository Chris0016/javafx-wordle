package wordle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("wordlefx.fxml"));
        primaryStage.setTitle("Wordle");
        primaryStage.getIcons().add(new Image("file:wordle-icon.jpeg"));

        Scene scene = new Scene(root, 600, 800);
        scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}