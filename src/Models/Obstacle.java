package Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A class that manages every interactable object in
 * the game.  E.g. Player, Bounce Pads, Asteroids.  They
 * all have images and various sizes.
 * @author Joshua Thompson
 */
public class Obstacle extends ImageView {
    public Obstacle(Image img) {
        setImage(img);
        setFitWidth(120);
        setFitHeight(30);
    }
}