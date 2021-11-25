package Scenes;

import Panes.OpeningPane;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

public class OpeningScene extends Scene {
    public OpeningScene() throws FileNotFoundException {
        super(new OpeningPane());
    }
}
