import model.GameMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BridgeGame {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter map file (press enter for default.map) : ");
        String mapFileName = scanner.nextLine();
        if(mapFileName.length() == 0) {
            mapFileName = "default.map";
        }
        File file = new File(mapFileName);
        GameMap gameMap = new GameMap(file.getAbsolutePath());
    }
}
