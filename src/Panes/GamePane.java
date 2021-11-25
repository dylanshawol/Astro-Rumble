package Panes;

import Game.Main;
import Models.Spaceship;
import Music.MusicPlayer;
import Obstacles.Asteroid;
import Obstacles.BouncePad;
import Obstacles.Coin;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class for the GamePane. This will display all the content in a game.
 *
 * @author Joshua Thompson
 * @author Dylan Shawol
 * @author Conner Cozad
 */

public class GamePane extends Pane {
        public static BouncePad[] bouncePads;
        public static Asteroid[] asteroids;
        public static Coin[] coins;
        public static boolean hasJumped = false;
        public static Pane root;
        public static Spaceship player;
        public static Background bgImg;
        public static Text high_score;
        public static Text coin_amount;
        public static int score;
        public static int coin;
        public static boolean includeAsteroids = true;

    public GamePane() throws IOException {
            Random random = new Random();
            root = this;

            setCursor(MainMenuPane.cursor);


            Text prompt = new Text("LEFT CLICK TO START PLAYING");

            Font font = Font.loadFont(new FileInputStream(new File("src/Fonts/MajorMonoDisplayRegular.ttf")), 56);
            prompt.setFont(font);
            prompt.setFill(Color.valueOf("#33CC66"));
            prompt.setTranslateX(Main.WIDTH / 2 - (prompt.getLayoutBounds().getWidth() / 2));
            prompt.setTranslateY(Main.HEIGHT / 3.5);

            FadeTransition promptFade = new FadeTransition(Duration.millis(1000), prompt);
            promptFade.setCycleCount(Animation.INDEFINITE);
            promptFade.setFromValue(1);
            promptFade.setToValue(0);
            promptFade.setAutoReverse(true);
            promptFade.play();


            bgImg = new Background(new BackgroundImage(new Image("file:src/Images/Backgrounds/gameBackground.png", Main.WIDTH, Main.HEIGHT, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
            setBackground(bgImg);

            Font statsFont = new Font(font.getFamily(), 48);

            high_score = new Text((score) + " Ft.");
            high_score.setFont(statsFont);
            high_score.setFill(Color.WHITE);
            high_score.setTranslateX(10);
            high_score.setTranslateY(50);

            //This sets the coin text
            coin_amount = new Text("$" + (coin));
            coin_amount.setFont(statsFont);
            coin_amount.setFill(Color.WHITE);
            coin_amount.setTranslateX(10);
            coin_amount.setTranslateY(100);

            // Setup the player and the bounce pads
            // @author Joshua Thompson
            Main.player.setTranslateX(Main.WIDTH / 2 - Main.player.getFitWidth() / 2);
            Main.player.setTranslateY(Main.HEIGHT - Main.player.getFitHeight());

            bouncePads = new BouncePad[10];
            for (int i = 0; i < bouncePads.length; i++) {
                bouncePads[i] = new BouncePad();
                bouncePads[i].setTranslateX(random.nextInt((int)Main.WIDTH - 50));
                bouncePads[i].setTranslateY(random.nextInt(600));
            }

            // This makes 10 coins and sets the randomly
            coins = new Coin[10];
            for (int i = 0; i < coins.length; i++) {
                coins[i] = new Coin();
                coins[i].setTranslateX(random.nextInt((int)Main.WIDTH - 50));
                coins[i].setTranslateY(random.nextInt(600));
            }

            // Only create the asteroids to the game
            // if the player has not bought an upgrade
            // that removes them from the round.
            // @author Joshua Thompson
            if(includeAsteroids) {
                asteroids = new Asteroid[1];
                for (int i = 0; i < asteroids.length; i++) {
                    asteroids[i] = new Asteroid();
                    asteroids[i].setTranslateX(random.nextInt((int) Main.WIDTH - 50));
                    asteroids[i].setTranslateY(-random.nextInt(1500));
                }
            }


            root.setOnMouseMoved(e -> {
                Main.player.setTranslateX(e.getX() - (Main.player.getFitWidth() / 2));
            });


            setOnMouseClicked(e->{
                if (!hasJumped) {
                    //This plays The rocket boost sound on click
                    Media media = new Media(new File("src/Music/rocketSound.wav").toURI().toString());
                    MusicPlayer.soundPlayer = new MediaPlayer(media);
                    MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
                    MusicPlayer.soundPlayer.play();

                    hasJumped = true;
                    Main.player.animate();
                    // If the asteroids exist, make them fall
                    // @author Joshua Thompson
                    if(includeAsteroids) {
                        for (Asteroid asteroid : asteroids) {
                            asteroid.fall();
                        }
                    }
                    promptFade.stop();
                    root.getChildren().remove(prompt);
                    root.getChildren().addAll(bouncePads);
                    root.getChildren().addAll(coins);
                    if(includeAsteroids) {
                        root.getChildren().addAll(asteroids);
                    }
                    root.getChildren().add(high_score);
                    root.getChildren().add(coin_amount);
                }
            });


            root.getChildren().addAll(prompt, Main.player);
    }
}
