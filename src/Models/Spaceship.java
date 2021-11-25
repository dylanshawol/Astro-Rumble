package Models;

import Game.Main;
import Music.MusicPlayer;
import Obstacles.Coin;
import Panes.CharactersPane;
import Panes.GamePane;
import Panes.OptionsPane;
import Panes.ShopPane;
import Scenes.DeathScene;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Obstacles.Asteroid;
import Obstacles.BouncePad;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Spaceship extends ImageView {
    /**
     * A spaceship only needs an animation,
     * power to fly and the power to deplete so
     * the ship eventually falls, and collision
     * detection with other objects.
     *
     * @author Joshua Thompson
     */
    private static AnimationTimer animator;
    public static double power;

    private Random random = new Random();
    public static Double playerHeight;
    public static ImageView player;

    public Spaceship() {
        player = this;

        // Checks what image you chose in the players menu
        if (CharactersPane.chosenFilePath.equals("")) {
            setImage(new Image("file:src/Images/Players/player.png"));
        } else {
            setImage(new Image(CharactersPane.chosenFilePath));
        }

        setFitWidth(80);
        setFitHeight(160);
        power = 7.0;
        playerHeight = getFitHeight();
    }

    public double getPower() {
        return power;
    }
    public void setPower(double power) {
        Spaceship.power = power;
    }


    /**
     * Start the timer
     * The timer manages flight
     *
     * @author Joshua Thompson
     */
    public void animate() {
        animator = new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    fly();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        animator.start();
    }

    /**
     * Jump up and then start falling
     * Check for moving left and right
     * If player collides with a bounce pad,
     * boost the ship's power and drop the pad down
     * to simulate height
     *
     * @author Joshua Thompson
     */
    public void fly() throws IOException {
        setTranslateY(getTranslateY() - getPower());
        if (getTranslateY() <= -200) {
            setTranslateY(-200);
            setPower(-getPower());
        }
        depletePower();

        for (BouncePad pad : GamePane.bouncePads) {
            if (collidesWith(pad)) {
                Media media = new Media(new File("src/Music/teleport.wav").toURI().toString());
                MusicPlayer.soundPlayer = new MediaPlayer(media);
                MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
                MusicPlayer.soundPlayer.play();

                GamePane.score += 25;
                GamePane.high_score.setText(Integer.toString(GamePane.score) + "  FT.");

                if (!ShopPane.hasBoost) {
                    setPower(7);
                } else {
                    setPower(13);
                }

                for (BouncePad everyPlatform : GamePane.bouncePads) {
                    everyPlatform.setTranslateY(everyPlatform.getTranslateY() + 250);

                    if (everyPlatform.getTranslateY() > Main.HEIGHT) {
                        everyPlatform.setTranslateX(random.nextInt((int) Main.WIDTH - 20) + 20);
                        everyPlatform.setTranslateY(random.nextInt(100) + 50);
                    }
                }
                /**This is randomize the coins on every bounce pad hit By:Connor Cozad**/
                for (Coin everyCoin : GamePane.coins) {
                    everyCoin.setTranslateY(everyCoin.getTranslateY() + 250);

                    if (everyCoin.getTranslateY() > Main.HEIGHT) {
                        everyCoin.setTranslateX(random.nextInt((int) (Main.WIDTH - 50)));
                        everyCoin.setTranslateY(-random.nextInt(100));
                    }
                }
            }
        }

        /**This works for every coin you collect**/
        for (Coin coin : GamePane.coins) {
            if (collidesWith(coin)) {
                //Plays the coin sound
                Media media = new Media(new File("src/Music/coin.wav").toURI().toString());
                MusicPlayer.soundPlayer = new MediaPlayer(media);
                MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
                MusicPlayer.soundPlayer.play();
                //adds 1 to coin amount
                GamePane.coin += 1;
                GamePane.coin_amount.setText("$" + (GamePane.coin));
                //sets the coin to the top
                coin.setTranslateX(random.nextInt((int) (Main.WIDTH - 50)));
                coin.setTranslateY(-300);
            }
        }

        // If the player has no REMOVE ASTEROIDS upgrade,
        // then run the events for thr existing  asteroids.
        // @author Joshua Thompson
        if (GamePane.includeAsteroids) {
            for (Asteroid asteroid : GamePane.asteroids) {
                if (asteroid.getTranslateY() > Main.HEIGHT) {
                    asteroid.setTranslateX(random.nextInt((int) (Main.WIDTH - 20)) + 20);
                    asteroid.setTranslateY(-500);
                }

                if (collidesWith(asteroid)) {
                    die();
                }
            }
        }

        // If the player falls past the screen,
        // they have lost so trigger the die protocol.
        if (getTranslateY() >= Main.HEIGHT + getFitHeight()) {
            die();
        }
    }

    /**
     * Slows down the power of the ship
     * like negative velocity for falling
     *
     * @author Joshua Thompson
     */
    public void depletePower() {
        setPower(getPower() - 0.1);
    }

    public static void die() throws IOException {

        animator.stop();
        //This plays the death sound By: Connor Cozad
        Media media = new Media(new File("src/Music/boom.wav").toURI().toString());
        MusicPlayer.soundPlayer = new MediaPlayer(media);
        MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
        MusicPlayer.soundPlayer.play();
        GamePane.hasJumped = false;
        GamePane.high_score.setText("0 Ft.");
        Main.stage.setScene(new DeathScene());
        GamePane.score = 0;
        GamePane.includeAsteroids = true;
    }

    /**
     * Gets the boundaries of any type pf object
     * If the player hits the boundaries of the given object,
     * return true. by default, assume false.
     * Used for handling events on collision detection.
     *
     * @param object the object that the player is checking collision for
     * @return whether the boundaries of the player and the object have collided or not
     * @author Joshua Thompson
     */
    public boolean collidesWith(Obstacle object) {
        /**
         * Unfortunately the boundaries for
         * the asteroids are a bit more touchy.
          */

        return getTranslateX() > object.getTranslateX() &&
                    getTranslateX() < object.getTranslateX() + object.getFitWidth() &&
                    getTranslateY() > object.getTranslateY() - getFitHeight() &&
                    getTranslateY() < object.getTranslateY();
    }
}