package Obstacles;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import Models.Obstacle;

public class Asteroid extends Obstacle {
    private AnimationTimer animator;
    private int fallSpeed = 1;

    public Asteroid() {
        super(new Image("file:src/Images/Obstacles/asteroid.png"));
        setFitWidth(150);
        setFitHeight(150);
    }


    /**
     * Have the asteroid fall towards the ground.
     * @author Joshua Thompson
     */
    public void fall() {
        animator = new AnimationTimer() {
            @Override
            public void handle(long l) {
                setTranslateY(getTranslateY() + fallSpeed);
                if (getTranslateY() + getFitHeight() >= 700) {
                    fallSpeed = 2;
                } else if (getTranslateY() + getFitHeight() >= 400) {
                    fallSpeed = 4;
                }
            }
        };

        animator.start();
    }
}