package Scenes;

import Panes.CharactersPane;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

public class CharactersScene extends Scene {
    public CharactersScene() throws FileNotFoundException {
        super(new CharactersPane());
    }
}
