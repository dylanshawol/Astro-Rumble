package Scenes;

import Panes.CreditsPane;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

public class CreditsScene extends Scene {
    public CreditsScene() throws FileNotFoundException {
        super(new CreditsPane());
    }
}
