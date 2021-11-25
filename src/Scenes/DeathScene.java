package Scenes;

import Panes.DeathPane;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

public class DeathScene extends Scene {
    public DeathScene() throws FileNotFoundException {
        super(new DeathPane());
    }
}
