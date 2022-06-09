import controller.GameController;
import model.GameModel;
import view.GameView;

import java.io.IOException;

public class BridgeGame {
    public static void main(String[] args) throws IOException {

        GameModel gameModel = new GameModel();
        GameController gameController = new GameController(gameModel);
    }
}
