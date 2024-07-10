package adapter.in;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOverMenuController {
    public void handleRestartButton(ActionEvent actionEvent) {
        Scene scene = getSceneFromEvent(actionEvent);
        ScreenController.getInstance(scene).activateTypingGame();
    }

    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = getStageFromEvent(actionEvent);
        stage.close();
    }

    private Scene getSceneFromEvent(ActionEvent actionEvent) {
        return getStageFromEvent(actionEvent).getScene();
    }

    private Stage getStageFromEvent(ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }
}
