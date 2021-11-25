package Panes;

import Game.Main;
import Music.MusicPlayer;
import Scenes.MainMenuScene;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Class for the CharactersPane. This will display all content on the player's menu.
 *
 * Allows you to choose between 2 players in the game (Shawarma, Rocket)
 * as well as upload your own (<= 500x500) image resource to use.
 *
 * @author Dylan Shawol
 */
public class CharactersPane extends BorderPane {
    public static File rocketImg;
    public static File shawarmaImg;
    public static File userImg;
    public static String chosenFilePath = "";
    Media media;
    public CharactersPane() throws FileNotFoundException {
        // Set scene cursor and background
        setBackground(MainMenuPane.bgImg);
        setCursor(MainMenuPane.cursor);

        // Back button - returns to the main menu
        ImageView backBtn = new ImageView(new Image("file:src/Images/Buttons/backBtn.png"));
        backBtn.setFitWidth(backBtn.getLayoutBounds().getWidth() / 2.5);
        backBtn.setFitHeight(backBtn.getLayoutBounds().getHeight() / 2.5);




        // Mouse Clicked
        backBtn.setOnMouseClicked(e->{
            try {
                Main.stage.setScene(new MainMenuScene());
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        });

        // Mouse Entered
        backBtn.setOnMouseEntered(e->{
            backBtn.setEffect(new Glow(1));

            /**Blop sound effect by:Connor Cozad**/
            media = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(media);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        // Mouse Exited
        backBtn.setOnMouseExited(e->{
            backBtn.setEffect(null);
        });


        // Title, text, and font styling.
        Font font = Font.loadFont(new FileInputStream(new File("src/Fonts/MajorMonoDisplayRegular.ttf")), 18);

        Font titleFont = Font.font(font.getFamily(), FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 32);

        Text title = new Text("SELECT YOUR PLAYER!");
        title.setFont(titleFont);
        title.setFill(Color.WHITE);

        Text promptText = new Text();
        promptText.setFont(titleFont);
        promptText.setFill(Color.RED);

        // Upload Button - opens file browser/explorer
        ImageView uploadBtn = new ImageView(new Image("file:src/Images/Buttons/uploadImageBtn.png"));
        uploadBtn.setFitWidth(uploadBtn.getLayoutBounds().getWidth() / 4);
        uploadBtn.setFitHeight(uploadBtn.getLayoutBounds().getHeight() / 4);

        // Creates new FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image to be your player!");

        // Create 2 default player images to choose from
        shawarmaImg = new File("src/Images/Players/player.png");
        rocketImg = new File("src/Images/Players/rocket.png");

        // Sets the image sizes for the scene
        ImageView shawarmaPlayer = new ImageView(shawarmaImg.toURI().toString());
        shawarmaPlayer.setFitWidth(80);
        shawarmaPlayer.setFitHeight(160);

        ImageView rocketPlayer = new ImageView(rocketImg.toURI().toString());
        rocketPlayer.setFitWidth(80);
        rocketPlayer.setFitHeight(160);

        // Sets the size the user uploads to fit the player
        ImageView userUploadedImage = new ImageView();
        userUploadedImage.setFitWidth(80);
        userUploadedImage.setFitHeight(160);

        // Set up the scene with labels, boxes and align them
        Label uploadLbl = new Label("OR, UPLOAD YOUR OWN IMAGE");
        uploadLbl.setFont(font);
        uploadLbl.setTextFill(Color.WHITE);

        HBox playersHBox = new HBox(50, shawarmaPlayer, rocketPlayer);
        playersHBox.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(uploadLbl, Pos.CENTER);
        BorderPane.setAlignment(uploadBtn, Pos.CENTER);

        VBox layoutBox = new VBox(20, promptText, title, playersHBox, uploadLbl, uploadBtn, backBtn);
        layoutBox.setAlignment(Pos.CENTER);

        // Fading animation that displays the prompt text
        FadeTransition promptFade = new FadeTransition(Duration.millis(3000), promptText);
        promptFade.setFromValue(1);
        promptFade.setToValue(0);

      /***** UPLOAD BUTTON ********************************************************************************************/
        // When the user clicks upload
        uploadBtn.setOnMouseClicked(e->{
            // Opens the users operating system's file browser/explorer
            userImg = fileChooser.showOpenDialog(Main.stage);
            if (userImg != null) {
                // Sets the users file to an image
                Image img = new Image(userImg.toURI().toString());
                // If the image is less than 500x500 resolution than set it as the player
                if (img.getWidth() <= 500 && img.getHeight() <= 500) {
                    Main.player.setImage(new Image(userImg.toURI().toString()));
                    chosenFilePath = userImg.toURI().toString();
                    promptText.setText("IMAGE UPLOAD SUCCESSFUL");
                    // Else display error message
                } else {
                    promptText.setText("ERROR: IMAGE MUST BE LESS THAN 500x500");
                }
                // Play prompt animation
                promptFade.play();
            }
        });

        // Mouse Entered
        uploadBtn.setOnMouseEntered(e->{
            uploadBtn.setEffect(new Glow(1));

            /**Blop sound effect by:Connor Cozad**/
            media = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(media);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        // Mouse Exited
        uploadBtn.setOnMouseExited(e->{
            uploadBtn.setEffect(null);
        });

        /***** SHAWARMA PLAYER BUTTON *********************************************************************************/
        // Mouse Clicked
        shawarmaPlayer.setOnMouseClicked(e->{
            Main.player.setImage(new Image(shawarmaImg.toURI().toString()));

            promptText.setText("PLAYER CHANGED TO SHAWARMA");
            promptFade.play();
            chosenFilePath = shawarmaImg.toURI().toString();
        });

        // Mouse Entered
        shawarmaPlayer.setOnMouseEntered(e->{
            shawarmaPlayer.setEffect(new Glow(0.25));

            /**Blop sound effect by:Connor Cozad**/
            media = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(media);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        // Mouse Exited
        shawarmaPlayer.setOnMouseExited(e->{
            shawarmaPlayer.setEffect(null);
        });

        /***** ROCKET PLAYER BUTTON ***********************************************************************************/
        // Mouse Clicked
        rocketPlayer.setOnMouseClicked(e->{
            Main.player.setImage(new Image(rocketImg.toURI().toString()));
            promptText.setText("PLAYER CHANGED TO ROCKET");
            promptFade.play();
            chosenFilePath = rocketImg.toURI().toString();
        });

        // Mouse Entered
        rocketPlayer.setOnMouseEntered(e->{
            rocketPlayer.setEffect(new Glow(0.25));

            /**Blop sound effect by:Connor Cozad**/
            media = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(media);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });

        rocketPlayer.setOnMouseExited(e->{
            rocketPlayer.setEffect(null);
        });

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        setCenter(layoutBox);

    }
}
