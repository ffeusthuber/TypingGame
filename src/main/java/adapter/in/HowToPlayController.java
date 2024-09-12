package adapter.in;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HowToPlayController {

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        Scene scene = getSceneFromEvent(actionEvent);
        ScreenController.getInstance(scene).activateMainMenu();
    }

    private Scene getSceneFromEvent(ActionEvent actionEvent) {
        return getStageFromEvent(actionEvent).getScene();
    }

    private Stage getStageFromEvent(ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }
}
