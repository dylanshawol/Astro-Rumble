package Panes;

import Game.Main;
import Music.MusicPlayer;
import Scenes.GameScene;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class OpeningPane extends BorderPane {
    public static Media media;
    public OpeningPane() throws FileNotFoundException {
        setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0))));
        setCursor(Cursor.NONE);

        /***** TOP OF PANE ********************************************************************************************/
        // Text and styling
        Font devFont = Font.loadFont(new FileInputStream(new File("src/Fonts/MajorMonoDisplayRegular.ttf")), 64);

        Text developersText = new Text("Dylan Shawol, Connor Cozad\n & Joshua Thompson");
        Text presentsText = new Text("present to you:");

        developersText.setTextAlignment(TextAlignment.CENTER);
        developersText.setFill(Color.WHITE);
        developersText.setFont(devFont);
        developersText.setTranslateY(50);
        developersText.setOpacity(0);

        presentsText.setFill(Color.WHITE);
        presentsText.setFont(devFont);
        presentsText.setOpacity(0);

        VBox textVbox = new VBox(150, developersText, presentsText);
        textVbox.setAlignment(Pos.CENTER);

        setTop(textVbox);

        /***** CENTER OF PANE *****************************************************************************************/
        // Astro Rumble title image and alignment
        ImageView astroRumbleTitle = new ImageView(new Image("file:src/Images/Misc/astroRumbleTitle.png"));
        BorderPane.setAlignment(astroRumbleTitle, Pos.CENTER);
        astroRumbleTitle.setOpacity(0);

        setCenter(astroRumbleTitle);

        // Fade in animation for names in group
        FadeTransition devTextFade = new FadeTransition(Duration.millis(1500), developersText);
        devTextFade.setFromValue(0);
        devTextFade.setToValue(1);

        // Fade in animation for the sentence and text object "presents to you:"
        FadeTransition presentsTextFade = new FadeTransition(Duration.millis(1500), presentsText);
        presentsTextFade.setFromValue(0);
        presentsTextFade.setToValue(1);

        // Fade in animation for the Astro Rumble title image
        FadeTransition gameTitleFade = new FadeTransition(Duration.millis(2000), astroRumbleTitle);
        gameTitleFade.setFromValue(0);
        gameTitleFade.setToValue(1);

        // Sequential animation that combines the 3 previous animations
        SequentialTransition contentFade = new SequentialTransition();


        // 3 of the same pause animations to be used as pauses in between the 3 previous animations
        // These will also be put in the sequential animation
        PauseTransition pauseTransition1 = new PauseTransition(Duration.millis(1000));
        PauseTransition pauseTransition2 = new PauseTransition(Duration.millis(1000));
        PauseTransition pauseTransition3 = new PauseTransition(Duration.millis(1000));

        // Adding all animations to the sequential animation gives us 6 in total
        contentFade.getChildren().addAll(devTextFade, pauseTransition1, presentsTextFade, pauseTransition2, gameTitleFade, pauseTransition3);
        contentFade.play();


        // A black but transparent rectangle the size of the stage used to make the entire screen black
        Rectangle overlay = new Rectangle(Main.WIDTH, Main.HEIGHT, Color.BLACK);
        overlay.setOpacity(0);
        getChildren().add(overlay);

        // A separate fade in animation that fades the entire screen to black using the rectangle
        FadeTransition overlayFade = new FadeTransition(Duration.millis(3000), overlay);
        overlayFade.setFromValue(0);
        overlayFade.setToValue(1);

        // An image of the game background. Used after the fade to black animation
        ImageView gameBg = new ImageView(new Image("file:src/Images/Backgrounds/gameBackground.png"));
        gameBg.setOpacity(0);
        gameBg.setFitWidth(Main.WIDTH);
        gameBg.setFitHeight(Main.HEIGHT);
        gameBg.setTranslateX(0);
        gameBg.setTranslateY(0);

        // When the contentFade animation is finished playing start the fade to black animation.
        contentFade.setOnFinished(e->{
            overlayFade.play();
        });

        // When the overlay animation is finished set the node to the game background so that will fade in and then
        // when that animation is finished, set the scene to a new GameScene
        overlayFade.setOnFinished(e->{
            overlayFade.setNode(gameBg);
            getChildren().add(gameBg);
            overlayFade.play();
            overlayFade.setOnFinished(e1->{
                setToGameScene();
            });
        });

        // Skips the opening scene, not that you would want to do such a thing.
        setOnMouseClicked(e->{
                contentFade.stop();
                overlayFade.stop();
                setToGameScene();
        });
    }

    /**
     * function to set the scene to a new GameScene()
     *
     * @author Dylan Shawol
     */
    public static void setToGameScene() {
        try {
            /** Main Game Song effect by:Connor Cozad**/
            MusicPlayer.musicPlayer.stop();
            media = new Media(new File("src/Music/wellPlayingMusic.mp3").toURI().toString());
            MusicPlayer.musicPlayer = new MediaPlayer(media);
            MusicPlayer.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            MusicPlayer.musicPlayer.setVolume(OptionsPane.newMusicVolume);
            MusicPlayer.musicPlayer.play();


            Main.stage.setScene(new GameScene());
            Main.player.setTranslateY(Main.HEIGHT / 2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
