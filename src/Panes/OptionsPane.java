package Panes;

import Game.Main;
import Music.MusicPlayer;
import Scenes.CreditsScene;
import Scenes.MainMenuScene;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class for the OptionsPane. This will display all the content on the options menu.
 *
 *     Options Menu:
 *          Sfx Volume Slider
 *          Music Volume Slider
 *          Button for Credits
 *
 *
 * @author Dylan Shawol
 */
public class OptionsPane extends BorderPane {

    public static double newMusicVolume = 0.1;
    public static double newSfxVolume = 0.1;
    Media media;

    public OptionsPane() throws FileNotFoundException {
        // Set scene cursor and background
        setCursor(MainMenuPane.cursor);
        setBackground(MainMenuPane.bgImg);


        // Back Button - returns to the main menu
        ImageView backBtn = new ImageView(new Image("file:src/Images/Buttons/backBtn.png"));
        backBtn.setFitWidth(backBtn.getLayoutBounds().getWidth() / 2.5);
        backBtn.setFitHeight(backBtn.getLayoutBounds().getHeight() / 2.5);

        backBtn.setOnMouseClicked(e->{
            try {
                Main.stage.setScene(new MainMenuScene());
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        });

        backBtn.setOnMouseEntered(e->{
            backBtn.setEffect(new Glow(1));

            /**Blop sound effect by:Connor Cozad**/
            media = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(media);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        backBtn.setOnMouseExited(e->{
            backBtn.setEffect(null);
        });

        // Credits Button
        ImageView creditsBtn = new ImageView(new Image("file:src/Images/Buttons/creditsBtn.png"));

        // Sets button credits button size
        creditsBtn.setFitWidth(creditsBtn.getLayoutBounds().getWidth() / 2.5);
        creditsBtn.setFitHeight(creditsBtn.getLayoutBounds().getHeight() / 2.5);

        // Mouse Clicked
        creditsBtn.setOnMouseClicked(e->{
            try {
                Main.stage.setScene(new CreditsScene());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        // Mouse Entered
        creditsBtn.setOnMouseEntered(e->{
            creditsBtn.setEffect(new Glow(1));

            // Sets sound effect to button on mouse entered
            /**Blop sound effect by:Connor Cozad**/
            media = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(media);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        // Mouse Exited
        creditsBtn.setOnMouseExited(e->{
            creditsBtn.setEffect(null);
        });


        // Title, Labels and text styling/font
        Text title = new Text("OPTIONS");

        Font font = Font.loadFont(new FileInputStream(new File("src/Fonts/MajorMonoDisplayRegular.ttf")), 18);

        Font labelFont = Font.font(font.getFamily(), FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 24);
        Font titleFont = Font.font(font.getFamily(), FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 48);

        title.setFont(titleFont);
        title.setFill(Color.WHITE);

        Label sfxOptionsLbl = new Label("SFX: ");
        Label musicOptionsLbl = new Label("MUSIC: ");


        sfxOptionsLbl.setFont(labelFont);
        sfxOptionsLbl.setTextFill(Color.WHITE);

        musicOptionsLbl.setFont(labelFont);
        musicOptionsLbl.setTextFill(Color.WHITE);

        // Create volume sliders
        Slider sfxSlider = new Slider(0, 1, newSfxVolume);
        Slider musicSlider = new Slider(0, 1, newMusicVolume);


        // Shows the sliders tick marks and sets the preferred width
        sfxSlider.setShowTickLabels(true);
        sfxSlider.setShowTickMarks(true);
        sfxSlider.setPrefWidth(200);

        musicSlider.setShowTickLabels(true);
        musicSlider.setShowTickMarks(true);
        musicSlider.setPrefWidth(200);



        // Sets all the SFX media's volume to whatever the sliders new value is
        sfxSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            newSfxVolume = (double)newValue;
            MusicPlayer.getSoundPlayer().setVolume((double)newValue);
        });

        // Sets all music media's volume to whatever the sliders new value is
        musicSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            newMusicVolume = (double)newValue;
            MusicPlayer.musicPlayer.setVolume((double)newValue);
        });

        // Boxes for alignment
        HBox sfxBox = new HBox(5, sfxOptionsLbl, sfxSlider);
        HBox musicBox = new HBox(5, musicOptionsLbl, musicSlider);
        VBox optionsBox = new VBox(15, title, sfxBox, musicBox, creditsBtn, backBtn);

        sfxBox.setAlignment(Pos.CENTER);
        musicBox.setAlignment(Pos.CENTER);
        optionsBox.setAlignment(Pos.CENTER);

        setCenter(optionsBox);

    }
}
