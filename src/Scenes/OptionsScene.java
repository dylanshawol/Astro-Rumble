package Scenes;

import Panes.OptionsPane;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

public class OptionsScene extends Scene {
    public OptionsScene() throws FileNotFoundException {
        super(new OptionsPane());
    }
}
