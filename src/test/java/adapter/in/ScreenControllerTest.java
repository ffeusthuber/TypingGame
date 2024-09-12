package adapter.in;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ScreenControllerTest {

    private Scene mockScene;

    @BeforeAll
    public static void initJFX() {
        new JFXPanel();
    }

    @BeforeEach
    void setUp() {
        mockScene = mock(Scene.class);
        ScreenController.setInstance(null);
    }

    @Test
    void getInstanceWithNewSceneCreatesNewInstance() {
        ScreenController firstInstance = ScreenController.getInstance(mockScene);

        assertThat(firstInstance).isNotNull();
    }

    @Test
    void getInstanceWithSameSceneReturnsSameInstance() {
        ScreenController firstInstance = ScreenController.getInstance(mockScene);
        ScreenController secondInstance = ScreenController.getInstance(mockScene);

        assertThat(firstInstance).isEqualTo(secondInstance);
    }

    @Test
    void activateTypingGameChangesSceneRoot() {
        ScreenController controller = ScreenController.getInstance(mockScene);

        controller.activateTypingGame();

        verify(mockScene, times(1)).setRoot(any(Pane.class));
    }

    @Test
    void activateGameOverChangesSceneRoot() {
        ScreenController controller = ScreenController.getInstance(mockScene);

        controller.activateGameOver();

        verify(mockScene, times(1)).setRoot(any(Pane.class));
    }
}