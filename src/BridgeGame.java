import model.GameModel;
import view.GameFrame;

import java.io.IOException;

public class BridgeGame {
    public static void main(String[] args) throws IOException {

        GameModel gameModel = new GameModel();

        GameFrame gameFrame = new GameFrame(gameModel.gameMap);
    }
}
