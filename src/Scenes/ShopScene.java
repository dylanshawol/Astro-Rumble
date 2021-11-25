package Scenes;

import Panes.ShopPane;
import javafx.scene.Scene;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ShopScene extends Scene {
    public ShopScene() throws IOException {
        super(new ShopPane());
    }
}
