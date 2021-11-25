package Scenes;

import javafx.scene.Scene;
import Panes.MainMenuPane;

import java.awt.*;


public class MainMenuScene extends Scene {
    public MainMenuScene() throws AWTException {
        super(new MainMenuPane());
    }
}
