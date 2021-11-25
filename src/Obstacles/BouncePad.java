package Obstacles;

import javafx.scene.image.Image;
import Models.Obstacle;

public class BouncePad extends Obstacle {
    public BouncePad() {
        /**
         * All the bounce pad obstacle needs os an image
         * to be shown.
         * @author Joshua Thompson
         */
        super(new Image("file:src/Images/Obstacles/bouncePad.png"));
    }
}