import model.GameMap;
import model.GameModel;
import model.Player;
import view.MapFrame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BridgeGame {
    public static void main(String[] args) throws IOException {

        GameModel gameModel = new GameModel();

        MapFrame mapFrame = new MapFrame(gameModel.gameMap);
    }
}
