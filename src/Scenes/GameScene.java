package Scenes;

import javafx.scene.Scene;
import Panes.GamePane;

import java.io.IOException;

public class GameScene extends Scene {
    public  GameScene() throws IOException {
        super(new GamePane());


    }
}
