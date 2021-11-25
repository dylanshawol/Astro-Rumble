package Panes;

import Game.Main;
import Music.MusicPlayer;
import Scenes.CharactersScene;
import Scenes.OpeningScene;
import Scenes.OptionsScene;
import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class for the MainMenuPane. This will display all the content on the main menu.
 *
 *  Directory:
 *      (1) Main Menu Images, Layout & Music
 *      (2) Button "Abduction" Animations
 *      (3) UFO Animations
 *      (4) Button Hover Animations
 *      (5) Button Events
 *
 * @author Dylan Shawol
 ***/
public class MainMenuPane extends Pane {
    public static ImageCursor cursor;
    public static Background bgImg;

    /**medias and mediaPlayers Connor Cozad**/
    Media media;

    public MainMenuPane() throws AWTException {
        cursor = new ImageCursor(new Image("file:src/Images/Misc/cursor.png"));
        setCursor(cursor);

        /****************************************
         * (1) MAIN MENU IMAGES, LAYOUT & MUSIC *
         ****************************************/


        // Creating a background
        bgImg = new Background(new BackgroundImage(new Image("file:src/Images/Backgrounds/mainMenuBackground.png", Main.WIDTH, Main.HEIGHT, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        setBackground(bgImg);


        /** BUTTONS **/
        // Main Menu Button Images
        ImageView startBtn = new ImageView("file:src/images/Buttons/startBtn.png");
        ImageView charactersBtn = new ImageView("file:src/images/Buttons/playersBtn.png");
        ImageView optionsBtn = new ImageView("file:src/images/Buttons/optionBtn.png");
        ImageView quitBtn = new ImageView("file:src/images/Buttons/exitGameBtn.png");

        // Start Button Properties
        startBtn.setFitWidth(390);
        startBtn.setFitHeight(85);
        startBtn.setRotate(10);
        startBtn.setScaleX(0.32);
        startBtn.setScaleY(0.32);

        // Characters Button Properties
        charactersBtn.setFitWidth(545);
        charactersBtn.setFitHeight(85);
        charactersBtn.setRotate(-7);
        charactersBtn.setScaleX(0.32);
        charactersBtn.setScaleY(0.32);


        // Options Button Properties
        optionsBtn.setFitWidth(550);
        optionsBtn.setFitHeight(85);
        optionsBtn.setRotate(7);
        optionsBtn.setScaleX(0.32);
        optionsBtn.setScaleY(0.32);


        // Quit Button Properties
        quitBtn.setFitWidth(705);
        quitBtn.setFitHeight(85);
        quitBtn.setRotate(-3);
        quitBtn.setScaleX(0.32);
        quitBtn.setScaleY(0.32);



        /** UFO & BEAM **/
        ImageView ufoBeam = new ImageView("file:src/images/UFO/beam.png");
        ImageView ufo = new ImageView("file:src/images/UFO/ufo.png");

        // UFO Beam Properties
        ufoBeam.setFitWidth(410);
        ufoBeam.setFitHeight(720);
        ufoBeam.setScaleX(0.75);
        ufoBeam.setScaleY(0.5);

        // UFO Properties
        ufo.setFitWidth(405);
        ufo.setFitHeight(260);
        ufo.setScaleX(0.7);
        ufo.setScaleY(0.7);


        // Positions the beam directly in the middle of the opening on the ufo
        ufoBeam.setTranslateX((Main.WIDTH / 2) - (ufoBeam.getFitWidth() / 2));
        ufoBeam.setTranslateY(200);
        Effect glow = new Glow(1);
        ufoBeam.setEffect(glow);

        // Sets the ufo in the middle above the beam
        ufo.setTranslateX(ufoBeam.getTranslateX() + 2);
        ufo.setTranslateY(ufoBeam.getTranslateY() - 4);

        // Sets the start button in the ufo beam based on the screen resolution
        startBtn.setTranslateX((Main.WIDTH / 2) - (startBtn.getFitWidth() / 2) + 5);
        startBtn.setTranslateY(Main.HEIGHT / 1.6);

        // Sets the characters button in the ufo beam based on the screen resolution
        charactersBtn.setTranslateX((Main.WIDTH / 2) - (charactersBtn.getFitWidth() / 2));
        charactersBtn.setTranslateY(Main.HEIGHT / 1.4);

        // Sets the options button in the ufo beam based on the screen resolution
        optionsBtn.setTranslateX((Main.WIDTH / 2) - (optionsBtn.getFitWidth() / 2));
        optionsBtn.setTranslateY(Main.HEIGHT / 1.25);

        // Sets the quit button in the ufo beam based on the screen resolution
        quitBtn.setTranslateX((Main.WIDTH / 2) - (quitBtn.getFitWidth() / 2));
        quitBtn.setTranslateY((Main.HEIGHT - quitBtn.getFitHeight() * 1.15));



        /***********************************
         * (2) Button Abduction Animations *
         ***********************************/
        // Start Button "Abduction" Transitions
        TranslateTransition startBtnAbduct = new TranslateTransition(Duration.millis(1250), startBtn);
        RotateTransition startBtnRotate = new RotateTransition(Duration.millis(1250), startBtn);
        ScaleTransition startBtnScale = new ScaleTransition(Duration.millis(1250), startBtn);

        // Characters Button "Abduction" Transitions
        TranslateTransition charactersBtnAbduct = new TranslateTransition(Duration.millis(1500), charactersBtn);
        RotateTransition charactersBtnRotate = new RotateTransition(Duration.millis(1500), charactersBtn);
        ScaleTransition charactersBtnScale = new ScaleTransition(Duration.millis(1500), charactersBtn);

        // Options Button "Abduction" Transitions
        TranslateTransition optionsBtnAbduct = new TranslateTransition(Duration.millis(1750), optionsBtn);
        RotateTransition optionsBtnRotate = new RotateTransition(Duration.millis(1750), optionsBtn);
        ScaleTransition optionsBtnScale = new ScaleTransition(Duration.millis(1750), optionsBtn);

        // Quit Button "Abduction" Transitions
        TranslateTransition quitBtnAbduct = new TranslateTransition(Duration.millis(2000), quitBtn);
        RotateTransition quitBtnRotate = new RotateTransition(Duration.millis(2000), quitBtn);
        ScaleTransition quitBtnScale = new ScaleTransition(Duration.millis(2000), quitBtn);


        /** ABDUCTION ANIMATION **/
        // UFO Beam Collapse & Fade
        FadeTransition ufoBeamFade = new FadeTransition(Duration.millis(750), ufoBeam);
        ScaleTransition ufoBeamCollapse = new ScaleTransition(Duration.millis(750), ufoBeam);

        ufoBeamFade.setFromValue(1);
        ufoBeamFade.setToValue(0);

        ufoBeamCollapse.setFromX(ufoBeam.getScaleX());
        ufoBeamCollapse.setFromX(ufoBeam.getScaleY());

        ufoBeamCollapse.setFromX(ufoBeam.getScaleX());
        ufoBeamCollapse.setToX(0);


        ParallelTransition ufoBeamFadeCollapse = new ParallelTransition();
        ufoBeamFadeCollapse.getChildren().addAll(ufoBeamFade, ufoBeamCollapse);


        // Start Button Abduct
        startBtnAbduct.setFromX(startBtn.getTranslateX());
        startBtnAbduct.setFromY(startBtn.getTranslateY());

        startBtnAbduct.setToX(startBtn.getTranslateX() - 5);
        startBtnAbduct.setToY(ufo.getTranslateY() + (ufo.getFitHeight() / 1.5));

        // Characters Button Abduct
        charactersBtnAbduct.setFromX(charactersBtn.getTranslateX());
        charactersBtnAbduct.setFromY(charactersBtn.getTranslateY());

        charactersBtnAbduct.setToX(charactersBtn.getTranslateX() - 5);
        charactersBtnAbduct.setToY(ufo.getTranslateY() + (ufo.getFitHeight() / 1.5));

        // Options Button Abduct
        optionsBtnAbduct.setFromX(optionsBtn.getTranslateX());
        optionsBtnAbduct.setFromY(optionsBtn.getTranslateY());

        optionsBtnAbduct.setToX(optionsBtn.getTranslateX() - 5);
        optionsBtnAbduct.setToY(ufo.getTranslateY() + (ufo.getFitHeight() / 1.5));

        quitBtnAbduct.setFromX(quitBtn.getTranslateX());
        quitBtnAbduct.setFromY(quitBtn.getTranslateY());

        quitBtnAbduct.setToX(quitBtn.getTranslateX() - 5);
        quitBtnAbduct.setToY(ufo.getTranslateY() + (ufo.getFitHeight() / 1.5));

        /** ROTATE ANIMATION **/
        startBtnRotate.setFromAngle(startBtn.getRotate());
        startBtnRotate.setToAngle(90);

        charactersBtnRotate.setFromAngle(charactersBtn.getRotate());
        charactersBtnRotate.setToAngle(180);

        optionsBtnRotate.setFromAngle(optionsBtn.getRotate());
        optionsBtnRotate.setToAngle(270);

        quitBtnRotate.setFromAngle(quitBtn.getRotate());
        quitBtnRotate.setToAngle(360);

        /** SCALE ANIMATION **/
        startBtnScale.setFromX(startBtn.getScaleX());
        startBtnScale.setFromY(startBtn.getScaleY());

        startBtnScale.setToX(0);
        startBtnScale.setToY(0);

        startBtnScale.setByX(0.8);
        startBtnScale.setByY(0.8);

        charactersBtnScale.setFromX(charactersBtn.getScaleX());
        charactersBtnScale.setFromY(charactersBtn.getScaleY());

        charactersBtnScale.setToX(0);
        charactersBtnScale.setToY(0);

        charactersBtnScale.setByX(0.6);
        charactersBtnScale.setByY(0.6);

        optionsBtnScale.setFromX(optionsBtn.getScaleX());
        optionsBtnScale.setFromY(optionsBtn.getScaleY());

        optionsBtnScale.setToX(0);
        optionsBtnScale.setToY(0);

        optionsBtnScale.setByX(0.4);
        optionsBtnScale.setByY(0.4);

        quitBtnScale.setFromX(quitBtn.getScaleX());
        quitBtnScale.setFromY(quitBtn.getScaleY());

        quitBtnScale.setToX(0);
        quitBtnScale.setToY(0);

        quitBtnScale.setByX(0.2);
        quitBtnScale.setByY(0.2);

        ParallelTransition startBtnParallelTransition = new ParallelTransition();
        ParallelTransition charactersBtnParallelTransition = new ParallelTransition();
        ParallelTransition optionsBtnParallelTransition = new ParallelTransition();
        ParallelTransition quitBtnParallelTransition = new ParallelTransition();

        startBtnParallelTransition.getChildren().addAll(startBtnAbduct, startBtnRotate, startBtnScale);
        charactersBtnParallelTransition.getChildren().addAll(charactersBtnAbduct, charactersBtnRotate, charactersBtnScale);
        optionsBtnParallelTransition.getChildren().addAll(optionsBtnAbduct, optionsBtnRotate, optionsBtnScale);
        quitBtnParallelTransition.getChildren().addAll(quitBtnAbduct, quitBtnRotate, quitBtnScale);


        ParallelTransition allTransitions = new ParallelTransition();
        allTransitions.getChildren().addAll(startBtnParallelTransition, charactersBtnParallelTransition, optionsBtnParallelTransition, quitBtnParallelTransition);
        SequentialTransition abductTransition = new SequentialTransition();
        SequentialTransition quitTransition = new SequentialTransition();
        PauseTransition pauseTransition1 = new PauseTransition(Duration.millis(1000));
        PauseTransition pauseTransition2 = new PauseTransition(Duration.millis(750));
        PauseTransition pauseTransition3 = new PauseTransition(Duration.millis(1000));

        /**********************
         * (3) UFO Animations *
         **********************/
        // Transparent Overlay
        Rectangle overlay = new Rectangle(Main.WIDTH, Main.HEIGHT);
        overlay.setFill(Color.TRANSPARENT);
        overlay.setTranslateX(0);
        overlay.setTranslateY(0);

        // Fade to black fill transition
        FillTransition sceneFill = new FillTransition(Duration.millis(1500), overlay);
        sceneFill.setFromValue(Color.TRANSPARENT);
        sceneFill.setToValue(Color.BLACK);


        ScaleTransition ufoScale = new ScaleTransition(Duration.millis(1000), ufo);
        TranslateTransition ufoTranslate = new TranslateTransition(Duration.millis(1000), ufo);
        ParallelTransition ufoTransition = new ParallelTransition();

        ufoTranslate.setFromX(ufo.getTranslateX());
        ufoTranslate.setFromY(ufo.getTranslateY());

        ufoTranslate.setToX(ufo.getTranslateX() + (Main.WIDTH / 2.5));
        ufoTranslate.setToY(ufo.getTranslateY() - (Main.HEIGHT / 3.5));

        ufoScale.setFromX(ufo.getScaleX());
        ufoScale.setFromY(ufo.getScaleY());

        ufoScale.setToX(0);
        ufoScale.setToY(0);

        ufoTransition.getChildren().addAll(ufoTranslate, ufoScale);
        abductTransition.getChildren().addAll(allTransitions, ufoBeamCollapse);
        quitTransition.getChildren().addAll(allTransitions, pauseTransition1, ufoBeamFadeCollapse, pauseTransition2, ufoTransition, pauseTransition3, sceneFill);

        /*******************************
         * (4) BUTTON HOVER ANIMATIONS *
         *******************************/

        /** TRANSLATE HOVER ANIMATIONS **/
        TranslateTransition startBtnHoverTranslate = new TranslateTransition(Duration.millis(2000), startBtn);
        TranslateTransition charactersBtnHoverTranslate = new TranslateTransition(Duration.millis(2000), charactersBtn);
        TranslateTransition optionsBtnHoverTranslate = new TranslateTransition(Duration.millis(2000), optionsBtn);
        TranslateTransition quitBtnHoverTranslate = new TranslateTransition(Duration.millis(2000), quitBtn);


        startBtnHoverTranslate.setFromX(startBtn.getTranslateX());
        startBtnHoverTranslate.setFromY(startBtn.getTranslateY());

        startBtnHoverTranslate.setToX(startBtn.getTranslateX() + 2);
        startBtnHoverTranslate.setToY(startBtn.getTranslateY() + 5);

        startBtnHoverTranslate.setCycleCount(Animation.INDEFINITE);
        startBtnHoverTranslate.setAutoReverse(true);


        charactersBtnHoverTranslate.setFromX(charactersBtn.getTranslateX());
        charactersBtnHoverTranslate.setFromY(charactersBtn.getTranslateY());

        charactersBtnHoverTranslate.setToX(charactersBtn.getTranslateX() + 2);
        charactersBtnHoverTranslate.setToY(charactersBtn.getTranslateY() + 5);

        charactersBtnHoverTranslate.setCycleCount(Animation.INDEFINITE);
        charactersBtnHoverTranslate.setAutoReverse(true);


        optionsBtnHoverTranslate.setFromX(optionsBtn.getTranslateX());
        optionsBtnHoverTranslate.setFromY(optionsBtn.getTranslateY());

        optionsBtnHoverTranslate.setToX(optionsBtn.getTranslateX() + 2);
        optionsBtnHoverTranslate.setToY(optionsBtn.getTranslateY() + 5);

        optionsBtnHoverTranslate.setCycleCount(Animation.INDEFINITE);
        optionsBtnHoverTranslate.setAutoReverse(true);

        quitBtnHoverTranslate.setFromX(quitBtn.getTranslateX());
        quitBtnHoverTranslate.setFromY(quitBtn.getTranslateY());

        quitBtnHoverTranslate.setToX(quitBtn.getTranslateX() + 2);
        quitBtnHoverTranslate.setToY(quitBtn.getTranslateY() + 5);

        quitBtnHoverTranslate.setCycleCount(Animation.INDEFINITE);
        quitBtnHoverTranslate.setAutoReverse(true);


        /** ROTATE HOVER ANIMATIONS **/
        RotateTransition startBtnHoverRotate = new RotateTransition(Duration.millis(1250), startBtn);
        RotateTransition charactersBtnHoverRotate = new RotateTransition(Duration.millis(1250), charactersBtn);
        RotateTransition optionsBtnHoverRotate = new RotateTransition(Duration.millis(1250), optionsBtn);
        RotateTransition quitBtnHoverRotate = new RotateTransition(Duration.millis(1250), quitBtn);

        startBtnHoverRotate.setFromAngle(startBtn.getRotate());
        startBtnHoverRotate.setToAngle(startBtn.getRotate() - 2);
        startBtnHoverRotate.setAutoReverse(true);
        startBtnHoverRotate.setCycleCount(Animation.INDEFINITE);

        charactersBtnHoverRotate.setFromAngle(charactersBtn.getRotate());
        charactersBtnHoverRotate.setToAngle(charactersBtn.getRotate() + 2);
        charactersBtnHoverRotate.setAutoReverse(true);
        charactersBtnHoverRotate.setCycleCount(Animation.INDEFINITE);

        optionsBtnHoverRotate.setFromAngle(optionsBtn.getRotate());
        optionsBtnHoverRotate.setToAngle(optionsBtn.getRotate() - 2);
        optionsBtnHoverRotate.setAutoReverse(true);
        optionsBtnHoverRotate.setCycleCount(Animation.INDEFINITE);


        quitBtnHoverRotate.setFromAngle(quitBtn.getRotate());
        quitBtnHoverRotate.setToAngle(quitBtn.getRotate() + 2);
        quitBtnHoverRotate.setAutoReverse(true);
        quitBtnHoverRotate.setCycleCount(Animation.INDEFINITE);


        ParallelTransition startHover = new ParallelTransition();
        ParallelTransition charactersHover = new ParallelTransition();
        ParallelTransition optionsHover = new ParallelTransition();
        ParallelTransition quitHover = new ParallelTransition();

        startHover.getChildren().addAll(startBtnHoverTranslate, startBtnHoverRotate);
        charactersHover.getChildren().addAll(charactersBtnHoverTranslate, charactersBtnHoverRotate);
        optionsHover.getChildren().addAll(optionsBtnHoverTranslate, optionsBtnHoverRotate);
        quitHover.getChildren().addAll(quitBtnHoverTranslate, quitBtnHoverRotate);


        /*********************
         * (5) BUTTON EVENTS *
         *********************/

        /**** START ******************************************************************************/
        /** START OnMouseClicked **/
        // Used for moving the cursor out of the way when you press start as to not affect the button animation
        // due to the onMouseEntered event. The event triggers when the cursor collides with the button on animation.
        Robot robot = new Robot();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int scrnWidth = gd.getDisplayMode().getWidth();
        int scrnHeight = gd.getDisplayMode().getHeight();

        startBtn.setOnMouseClicked(e->{
            // Plays the abduct animation
            abductTransition.play();
            // Hides the cursor
            setCursor(Cursor.NONE);
            // Moves the cursor out of the way
            robot.mouseMove(scrnWidth / 2, scrnHeight / 4);
            abductTransition.setOnFinished(event -> {
                    // Set scene to opening scene when animation is done
                    try {
                        Main.stage.setScene(new OpeningScene());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
            });
        });

        /** START OnMouseEntered **/
        startBtn.setOnMouseEntered(e->{
            // Plays hover animation
            startHover.play();
            // Sets button glow effect
            startBtn.setEffect(glow);

            // Plays the sound effect
            /**Blop sound effect by:Connor Cozad**/
            media = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(media);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        /** START OnMouseExit **/
        startBtn.setOnMouseExited(e->{
            // Remove glow effect
            startBtn.setEffect(null);
        });


        /**** CHARACTERS *****************************************************************************/
        /** CHARACTER OnMouseClicked **/
        charactersBtn.setOnMouseClicked(e->{
            // Plays the abduct animation
            abductTransition.play();
            // Hides the cursor
            setCursor(Cursor.NONE);
            // Moves the cursor out of the way
            robot.mouseMove(scrnWidth / 2, scrnHeight / 4);

            abductTransition.setOnFinished(event -> {
                try { // Set the scene to a new CharactersScene
                    Main.stage.setScene(new CharactersScene());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });

        /** CHARACTER OnMouseEntered **/
        charactersBtn.setOnMouseEntered(e->{
            // Plays hover animation
            charactersHover.play();
            // Sets button glow effect
            charactersBtn.setEffect(glow);

            // Plays the sound effect
            /**Blop sound effect by:Connor Cozad**/
            media = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(media);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        /** CHARACTER OnMouseExited **/
        charactersBtn.setOnMouseExited(e->{
            // Remove glow effect
            charactersBtn.setEffect(null);
        });


        /**** OPTIONS *******************************************************************************/
        /** OPTIONS OnMouseClicked **/
        optionsBtn.setOnMouseClicked(e->{
            // Plays the abduct animation
            abductTransition.play();
            // Hides the cursor
            setCursor(Cursor.NONE);
            // Moves the cursor out of the way
            robot.mouseMove(scrnWidth / 2, scrnHeight / 4);

            abductTransition.setOnFinished(event -> {
                try { // Sets the scene to a new OptionsScene
                    Main.stage.setScene(new OptionsScene());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });

        /** OPTIONS OnMouseEntered **/
        optionsBtn.setOnMouseEntered(e->{
            // Plays hover animation
            optionsHover.play();
            // Sets button glow effect
            optionsBtn.setEffect(glow);

            // Plays the sound effect
            /**Blop sound effect by:Connor Cozad**/
            media = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(media);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        /** OPTIONS OnMouseExited **/
        optionsBtn.setOnMouseExited(e->{
            // Remove glow effect
            optionsBtn.setEffect(null);
        });


        /****  QUIT  ************************************************************************************/
        /** QUIT OnMouseClicked **/
        quitBtn.setOnMouseClicked(e->{
            // Plays the quit animation
            quitTransition.play();
            // Hides the cursor
            setCursor(Cursor.NONE);
            // Moves the cursor out of the way
            robot.mouseMove(scrnWidth / 2, scrnHeight / 4);

            quitTransition.setOnFinished(event -> {
                // Closes the program
                System.exit(0);
            });

        });

        /** QUIT OnMouseEntered **/
        quitBtn.setOnMouseEntered(e->{
            // Plays hover animation
            quitHover.play();
            // Sets button glow effect
            quitBtn.setEffect(glow);

            // Plays the sound effect
            /**Blop sound effect by:Connor Cozad**/
            media = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(media);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        /** QUIT OnMouseExited **/
        quitBtn.setOnMouseExited(e->{
            // Remove glow effect
            quitBtn.setEffect(null);
        });

        getChildren().addAll(ufo, ufoBeam, overlay, startBtn, charactersBtn, optionsBtn, quitBtn);
    }
}
