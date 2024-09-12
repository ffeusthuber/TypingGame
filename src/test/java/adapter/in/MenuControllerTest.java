package adapter.in;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MenuControllerTest {

    private ActionEvent actionEventMock;
    private ScreenController mockScreenController;
    private Stage mockStage;

    @BeforeAll
    public static void initJFX() {
        new JFXPanel();
    }

    @BeforeEach
    void setUp() {
        actionEventMock = mock(ActionEvent.class);
        Button mockButton = mock(Button.class);
        Scene mockScene = mock(Scene.class);
        mockStage = mock(Stage.class);

        when(actionEventMock.getSource()).thenReturn(mockButton);
        when(mockButton.getScene()).thenReturn(mockScene);
        when(mockScene.getWindow()).thenReturn(mockStage);

        mockScreenController = mock(ScreenController.class);
        ScreenController.setInstance(mockScreenController);
    }

    @Test
    void pressingStartButtonInMainMenuStartsGame() {
        MainMenuController mainMenuController = new MainMenuController();

        mainMenuController.handleStartButton(actionEventMock);

        verify(mockScreenController).activateTypingGame();
    }

    @Test
    void pressingExitGameButtonInMainMenuClosesGame() {
        MainMenuController mainMenuController = new MainMenuController();

        mainMenuController.handleExitButton(actionEventMock);

        verify(mockStage).close();
    }

    @Test
    void pressingRestartButtonInGameOverMenuRestartsGame() {
        GameOverMenuController gameOverMenuController = new GameOverMenuController();

        gameOverMenuController.handleRestartButton(actionEventMock);

        verify(mockScreenController).activateTypingGame();
    }

    @Test
    void pressingExitGameButtonInGameOverMenuClosesGame() {
        GameOverMenuController gameOverMenuController = new GameOverMenuController();

        gameOverMenuController.handleExitButton(actionEventMock);

        verify(mockStage).close();
    }
}