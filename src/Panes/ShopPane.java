package Panes;

import Game.Main;
import Models.Spaceship;
import Music.MusicPlayer;
import Scenes.GameScene;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the shop in the shop you are able to
 * set the start position of the game to a higher one then before
 * and increases the speed of the player
 *
 * @author Connor Cozad
 */


public class ShopPane extends BorderPane {
    Media mediaOfSounds;
    public static boolean hasBoost = false;


    public ShopPane() throws IOException {
        setBackground(GamePane.bgImg);
        Text coinsText = new Text("$" + GamePane.coin);


        /**
         * Shop Graphics setup & layout
         *
         * @author Dylan Shawol
         */
        Font font = Font.loadFont(new FileInputStream(new File("src/Fonts/MajorMonoDisplayRegular.ttf")), 42);

        Text prompt = new Text();
        prompt.setFill(Color.RED);
        prompt.setTranslateY(50);
        prompt.setFont(font);

        FadeTransition promptFade = new FadeTransition(Duration.millis(3000), prompt);
        promptFade.setFromValue(1);
        promptFade.setToValue(0);
        setTop(prompt);
        BorderPane.setAlignment(prompt, Pos.CENTER);

        ArrayList<ImageView> shopItemList = new ArrayList<>();

        ImageView removeAsteroidsFrame = new ImageView(new Image("file:src/Images/Shop/removeAsteroidsFrame.png"));
        ImageView speedBoostFrame = new ImageView(new Image("file:src/Images/Shop/speedBoostFrame.png"));
        ImageView distanceBoostFrame = new ImageView(new Image("file:src/Images/Shop/distanceBoostFrame.png"));

        ImageView removeAsteroidsItem = new ImageView(new Image("file:src/Images/Shop/removeAsteroidsItem.png"));
        ImageView speedBoostItem = new ImageView(new Image("file:src/Images/Shop/speedBoostItem.png"));
        ImageView distanceBoostItem = new ImageView(new Image("file:src/Images/Shop/distanceBoostItem.png"));

        shopItemList.add(removeAsteroidsItem);
        shopItemList.add(speedBoostItem);
        shopItemList.add(distanceBoostItem);

        ArrayList<ScaleTransition> scaleHoverList = new ArrayList<>();

        ScaleTransition asteroidItemScaleHover = new ScaleTransition(Duration.millis(50), removeAsteroidsItem);
        ScaleTransition speedItemScaleHover = new ScaleTransition(Duration.millis(50), speedBoostItem);
        ScaleTransition distanceItemScaleHover = new ScaleTransition(Duration.millis(50), distanceBoostItem);

        scaleHoverList.add(asteroidItemScaleHover);
        scaleHoverList.add(speedItemScaleHover);
        scaleHoverList.add(distanceItemScaleHover);

        for (ScaleTransition hover : scaleHoverList) {
            hover.setFromX(1);
            hover.setFromY(1);
            hover.setToX(1.15);
            hover.setToY(1.15);
        }

        ArrayList<ScaleTransition> scaleHoverResetList = new ArrayList<>();

        ScaleTransition asteroidItemScaleHoverReset = new ScaleTransition(Duration.millis(50), removeAsteroidsItem);
        ScaleTransition speedItemScaleHoverReset = new ScaleTransition(Duration.millis(50), speedBoostItem);
        ScaleTransition distanceItemScaleHoverReset = new ScaleTransition(Duration.millis(50), distanceBoostItem);

        scaleHoverResetList.add(asteroidItemScaleHoverReset);
        scaleHoverResetList.add(speedItemScaleHoverReset);
        scaleHoverResetList.add(distanceItemScaleHoverReset);

        for (ScaleTransition hoverReset : scaleHoverResetList) {
            hoverReset.setFromX(1.15);
            hoverReset.setFromY(1.15);
            hoverReset.setToX(1);
            hoverReset.setToY(1);
        }

        for (ImageView img : shopItemList) {
            img.setFitWidth(img.getLayoutBounds().getWidth() / 1.5);
            img.setFitHeight(img.getLayoutBounds().getHeight() / 1.5);

            // Sets all power-up images in the middle
            img.setTranslateX(Main.WIDTH / 2 - (img.getLayoutBounds().getWidth() / 2));
            img.setTranslateY(Main.HEIGHT / 2 - (img.getLayoutBounds().getHeight() / 2) + 10);
        }

        HBox frameBox = new HBox(50, removeAsteroidsFrame, speedBoostFrame, distanceBoostFrame);

        BorderPane.setAlignment(removeAsteroidsItem, Pos.CENTER);
        BorderPane.setAlignment(speedBoostItem, Pos.CENTER);
        BorderPane.setAlignment(distanceBoostItem, Pos.CENTER);

        // Sets the images in the middle of the item shop frame
        removeAsteroidsItem.setTranslateX(removeAsteroidsItem.getTranslateX() - removeAsteroidsFrame.getLayoutBounds().getWidth() - frameBox.getSpacing() - 5);
        distanceBoostItem.setTranslateX(distanceBoostItem.getTranslateX() + removeAsteroidsFrame.getLayoutBounds().getWidth() + frameBox.getSpacing());

        frameBox.setAlignment(Pos.CENTER);
        setCenter(frameBox);
        getChildren().addAll(removeAsteroidsItem, speedBoostItem, distanceBoostItem);


/**_______________________Bottom Buttons to go back to game _________________________________________________________**/

        //This shows the coin amount in the corner
        coinsText.setTranslateY(40);
        coinsText.setFont(font);
        coinsText.setFill(Color.WHITE);
        getChildren().add(coinsText);

        ImageView returnToGameBtn = new ImageView(new Image("file:src/Images/Buttons/returnToGameBtn.png"));


        /**
         * Sets the button's size and position to bottom center of the scene.
         * @author Dylan Shawol
         */
        returnToGameBtn.setFitWidth(returnToGameBtn.getLayoutBounds().getWidth() / 2);
        returnToGameBtn.setFitHeight(returnToGameBtn.getLayoutBounds().getHeight() / 2);
        returnToGameBtn.setTranslateY(Main.HEIGHT - (returnToGameBtn.getLayoutBounds().getHeight() * 2) - 20);
        returnToGameBtn.setTranslateX(Main.WIDTH / 2 - (returnToGameBtn.getLayoutBounds().getWidth() / 2));


        getChildren().add(returnToGameBtn);

        returnToGameBtn.setOnMouseClicked(e -> {
            /**This will make the shop go back to the game and change the audio
             * @author Connor Cozad**/
            MusicPlayer.getPlayer().stop();
            Media media = new Media(new File("src/Music/wellPlayingMusic.mp3").toURI().toString());
            MusicPlayer.musicPlayer = new MediaPlayer(media);
            MusicPlayer.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            MusicPlayer.musicPlayer.setVolume(OptionsPane.newMusicVolume);
            MusicPlayer.musicPlayer.play();
            try {
                Main.stage.setScene(new GameScene());
                Main.player.setTranslateY(Main.HEIGHT / 2);
                Main.player.setPower(7);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        returnToGameBtn.setOnMouseEntered(e -> {
            returnToGameBtn.setEffect(new Glow(1));
            //This plays the bloop sound By:Connor cozad
            mediaOfSounds = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        returnToGameBtn.setOnMouseExited(e -> {
            returnToGameBtn.setEffect(null);
        });


        //This plays the shop song
        MusicPlayer.getPlayer().stop();
        Media media = new Media(new File("src/Music/Bright_Eyed.mp3").toURI().toString());
        MusicPlayer.musicPlayer = new MediaPlayer(media);
        MusicPlayer.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MusicPlayer.musicPlayer.setVolume(OptionsPane.newMusicVolume);
        MusicPlayer.musicPlayer.play();

/**-----------------------------------------Boosts and Upgrades------------------------------------------------------**/


        // Mouse Clicked
        removeAsteroidsItem.setOnMouseClicked(e -> {
            int price = 50;
            //if you don't have the coin the text shows up saying you cant buy it
            if (GamePane.coin < price) {
                prompt.setText("SORRY YOU NEED " + price + " COINS TO BUY THIS.");
            } else {
                //else 50 coins are taken away and includeAsteroids are set to false
                GamePane.coin -= price;
                coinsText.setText("$" + GamePane.coin);
                mediaOfSounds = new Media(new File("src/Music/coin.wav").toURI().toString());
                prompt.setText("PURCHASED REMOVE ASTEROIDS FOR " + price + " COINS");
                GamePane.includeAsteroids = false;

                //this plays the coin sound
                MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
                MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
                MusicPlayer.soundPlayer.play();
            }
            promptFade.play();
        });
        // Mouse Entered
        removeAsteroidsItem.setOnMouseEntered(e -> {
            //This plays the bloop sound
            mediaOfSounds = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();

            asteroidItemScaleHover.play();
        });
        // Mouse Exited
        removeAsteroidsItem.setOnMouseExited(e -> {

            asteroidItemScaleHoverReset.play();
        });


        speedBoostItem.setOnMouseClicked(e -> {
            int price = 1;
            //if you dont have the coin the prompt says you cant buy it
            if (GamePane.coin < price) {
                prompt.setText("SORRY YOU NEED " + price + " COINS TO BUY THIS.");
            } else {
                // else 100 coins are taken and power is added to add speed
                GamePane.coin -= price;
                coinsText.setText("$" + GamePane.coin);
                mediaOfSounds = new Media(new File("src/Music/coin.wav").toURI().toString());
                prompt.setText("PURCHASED SPEED BOOST FOR " + price + " COINS");
                Spaceship.power = 13;
                hasBoost = true;
                //coin sound plays
                MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
                MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
                MusicPlayer.soundPlayer.play();
            }
            promptFade.play();

        });
        speedBoostItem.setOnMouseEntered(e -> {
            //bloop sound plays
            mediaOfSounds = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();

            speedItemScaleHover.play();
        });

        speedBoostItem.setOnMouseExited(e -> {
            speedItemScaleHoverReset.play();
        });


        distanceBoostItem.setOnMouseClicked(e -> {
            int price = 50;
            //if you don't have the coin them a prompt says you cant buy it
            if (GamePane.coin < price) {
                prompt.setText("SORRY YOU NEED " + price + " COINS TO BUY THIS.");
            } else {
                // 50 coins are taken and a boost of at least 1500 ft
                GamePane.coin -= price;
                Random r = new Random();
                GamePane.score = r.nextInt(1500) + 1000;
                coinsText.setText("$" + GamePane.coin);
                //sound for coin
                mediaOfSounds = new Media(new File("src/Music/coin.wav").toURI().toString());
                prompt.setText("PURCHASED DISTANCE BOOST FOR " + price + " COINS");
                MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
                MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
                MusicPlayer.soundPlayer.play();
            }
            promptFade.play();
        });
        distanceBoostItem.setOnMouseEntered(e -> {
            //bloop sound
            mediaOfSounds = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();

            distanceItemScaleHover.play();
        });

        distanceBoostItem.setOnMouseExited(e -> {
            distanceItemScaleHoverReset.play();
        });
    }

}
