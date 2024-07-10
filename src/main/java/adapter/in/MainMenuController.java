package adapter.in;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    public void handleStartButton(ActionEvent actionEvent) {
        Scene scene = getSceneFromEvent(actionEvent);
        ScreenController.getInstance(scene).activateTypingGame();
    }

    @FXML
    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = getStageFromEvent(actionEvent);
        stage.close();
    }

    private Stage getStageFromEvent(ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }

    private Scene getSceneFromEvent(ActionEvent actionEvent) {
        return getStageFromEvent(actionEvent).getScene();
    }
}