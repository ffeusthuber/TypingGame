package adapter.in;

import domain.GameField;
import domain.Word;
import domain.WordTargeter;
import domain.port.in.KeyPressListener;

public class KeyPressHandler implements KeyPressListener {
    private final GameField gameField;
    private final WordTargeter wordTargeter;

    public KeyPressHandler(GameField gameField, WordTargeter wordTargeter) {
        this.gameField = gameField;
        this.wordTargeter = wordTargeter;
    }

    @Override
    public void onKeyPressed(String key) {
        if(!wordTargeter.hasTarget()){
            wordTargeter.targetByKey(key,gameField.getWords());
        }

        if(wordTargeter.hasTarget()){
            processKeyForTargetedWord(key);
        }

    }

    private void processKeyForTargetedWord(String key) {
        Word target = wordTargeter.getTarget();

        target.type(key);

        if(target.getRemainingWord().isEmpty()) {
            gameField.removeWord(target);
            wordTargeter.dropTarget();
        }
    }
}
