package application;

import adapter.out.TypingGameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TypingGameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/TypingGame.fxml"));
        Parent root = loader.load();

        TypingGameController controller = loader.getController();

        Scene scene = new Scene(root);

        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(controller::handleKeyPressed);

        stage.setScene(scene);
        stage.setTitle("TypingGame");

        //set an icon
        //Image icon = new Image("set image path here");
        //stage.getIcons().add(icon);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}