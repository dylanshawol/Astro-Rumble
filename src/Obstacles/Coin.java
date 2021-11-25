package Obstacles;

import Models.Obstacle;
import javafx.scene.image.Image;

public class Coin extends Obstacle {

    /**
     * This will set the coin to the image and the size
     */

    public Coin() {
        super(new Image("file:src/Images/Obstacles/coin.png"));
        setFitWidth(50);
        setFitHeight(50);
    }

}
