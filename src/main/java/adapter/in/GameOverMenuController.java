package adapter.in;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverMenuController {
    @FXML
    Text typingTimeText;

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

    public void initData(String typingTime) {
        this.typingTimeText.setText(typingTime);
    }
}
