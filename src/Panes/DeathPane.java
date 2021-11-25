package Panes;

import Game.Main;
import Music.MusicPlayer;
import Scenes.GameScene;
import Scenes.MainMenuScene;
import Scenes.ShopScene;
import javafx.geometry.Pos;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class for the DeathPane. This will display all the content when you "die" or lose the game.
 *
 * @author Dylan Shawol
 */
public class DeathPane extends BorderPane {
        public static Text highScoreText;
        Media mediaOfSounds;
        MediaPlayer ofSounds;

    public DeathPane() throws FileNotFoundException {
        // Sets scene cursor and background
        setBackground(GamePane.bgImg);
        setCursor(MainMenuPane.cursor);

        // Text and font styling
        Font font = Font.loadFont(new FileInputStream(new File("src/Fonts/MajorMonoDisplayRegular.ttf")), 18);

        Font deathPromptFont = Font.font(font.getFamily(), 64);
        Font highScoreFont = Font.font(font.getFamily(), 56);
        Font coinAmountFont = Font.font(font.getFamily(), 56);
        Font decisionPromptFont = Font.font(font.getFamily(), 48);

        Text deathPrompt = new Text("Whoops, you died!");
        // sets the amount of distance traveled before you died
        highScoreText = new Text("Your score was: " + GamePane.score + " FT.");
        // displays the amount of coins you collected before you died
        Text coinAmount = new Text("Total Coins: " + GamePane.coin_amount.getText());
        Text decisionPrompt = new Text("What would you like to do?");


        // Styling and positioning of the prompts
        deathPrompt.setFont(deathPromptFont);
        deathPrompt.setFill(Color.WHITE);
        deathPrompt.setTranslateY(140);

        highScoreText.setFont(highScoreFont);
        highScoreText.setFill(Color.WHITE);
        highScoreText.setTranslateY(145);

        coinAmount.setFont(coinAmountFont);
        coinAmount.setFill(Color.WHITE);
        coinAmount.setTranslateY(150);

        decisionPrompt.setFont(decisionPromptFont);
        decisionPrompt.setFill(Color.WHITE);
        decisionPrompt.setTranslateY(185);

        // Creates 4 navigation buttons for the menu (retry, main menu, shop, exit game)
        ImageView retryBtn = new ImageView(new Image("file:src/Images/Buttons/retryBtn.png"));
        ImageView mainMenuBtn = new ImageView(new Image("file:src/Images/Buttons/mainMenuBtn.png"));
        ImageView shopBtn = new ImageView(new Image("file:src/Images/Buttons/shopBtn.png"));
        ImageView quitBtn = new ImageView(new Image("file:src/Images/Buttons/exitGameBtn.png"));

        // Simple glow effect
        Effect glow = new Glow(1);


        /******* RETRY BUTTON ****************************************************************************************/
        // Sets the size of the retry button
        retryBtn.setFitWidth(retryBtn.getLayoutBounds().getWidth() / 2);
        retryBtn.setFitHeight(retryBtn.getLayoutBounds().getHeight() / 2);
        // Mouse Clicked
        retryBtn.setOnMouseClicked(e->{
            try {
                // Sets to the GameScene
                Main.stage.setScene(new GameScene());
                // Sets the power to the default
                Main.player.setPower(7);
                // Sets the cursor
                GamePane.root.setCursor(MainMenuPane.cursor);
                // Positions the player in the middle
                Main.player.setTranslateY(Main.HEIGHT / 2);
                // Resets the distance to 0
                GamePane.high_score.setText("0 Ft.");
                // Resets the coin amount to 0
                GamePane.score = 0;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        // Mouse Entered
        retryBtn.setOnMouseEntered(e->{
            retryBtn.setEffect(glow);

            /**Bloop sound for button by:Connor Cozad**/
            mediaOfSounds = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });// Mouse Exited
        retryBtn.setOnMouseExited(e->{
            retryBtn.setEffect(null);
        });

        /******* MAIN MENU BUTTON ************************************************************************************/
        mainMenuBtn.setFitWidth(mainMenuBtn.getLayoutBounds().getWidth() / 2);
        mainMenuBtn.setFitHeight(mainMenuBtn.getLayoutBounds().getHeight() / 2);
        // Mouse Clicked
        mainMenuBtn.setOnMouseClicked(e->{
            try {
                /**This will play the main menu song by:Connor Cozad**/
                MusicPlayer.getPlayer().stop();
                Main.stage.setScene(new MainMenuScene());
                Media media = new Media(new File("src/Music/mainMenu.mp3").toURI().toString());
                MusicPlayer.musicPlayer = new MediaPlayer(media);
                MusicPlayer.musicPlayer.setVolume(OptionsPane.newMusicVolume);
                MusicPlayer.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                MusicPlayer.musicPlayer.play();
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        });
        // Mouse Entered
        mainMenuBtn.setOnMouseEntered(e->{
            mainMenuBtn.setEffect(glow);

             /**Bloop sound for button by:Connor Cozad**/
            mediaOfSounds = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });
        // Mouse Exited
        mainMenuBtn.setOnMouseExited(e->{
            mainMenuBtn.setEffect(null);
        });

        /******* SHOP BUTTON *****************************************************************************************/
        shopBtn.setFitWidth(shopBtn.getLayoutBounds().getWidth() / 2);
        shopBtn.setFitHeight(shopBtn.getLayoutBounds().getHeight() / 2);
        // Mouse Clicked
        shopBtn.setOnMouseClicked(e->{
            // Stop the current music and play shop music by:Connor Cozad
            MusicPlayer.musicPlayer.stop();
            Media media = new Media(new File("src/Music/Bright_Eyed.mp3").toURI().toString());
            MusicPlayer.musicPlayer = new MediaPlayer(media);
            MusicPlayer.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            MusicPlayer.musicPlayer.setVolume(OptionsPane.newMusicVolume);
            MusicPlayer.musicPlayer.play();
            try {
                // Set to new ShopScene
                Main.stage.setScene(new ShopScene());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        // Mouse Entered
        shopBtn.setOnMouseEntered(e->{
            // Set glow effect and play hover sound effect
            shopBtn.setEffect(glow);
            mediaOfSounds = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });
        // Mouse Exited
        shopBtn.setOnMouseExited(e->{
            // Remove glow effect
            shopBtn.setEffect(null);
        });


        /******* QUIT BUTTON ************************************************************************************************/
        quitBtn.setFitWidth(quitBtn.getLayoutBounds().getWidth() / 2);
        quitBtn.setFitHeight(quitBtn.getLayoutBounds().getHeight() / 2);
        // Mouse Clicked
        quitBtn.setOnMouseClicked(e->{
            System.exit(1);
        });
        // Mouse Entered
        quitBtn.setOnMouseEntered(e->{
            // Set glow effect and play hover sound effect
            quitBtn.setEffect(glow);
            mediaOfSounds = new Media(new File("src/Music/blop.wav").toURI().toString());
            MusicPlayer.soundPlayer = new MediaPlayer(mediaOfSounds);
            MusicPlayer.soundPlayer.setVolume(OptionsPane.newSfxVolume);
            MusicPlayer.soundPlayer.play();
        });
        // Mouse Exited
        quitBtn.setOnMouseExited(e->{
            // Remove glow effect
            quitBtn.setEffect(null);
        });

        // Lines to separate the buttons to make them easier to read
        Rectangle separator1 = new Rectangle(2, 80, Color.WHITE);
        Rectangle separator2 = new Rectangle(2, 80, Color.WHITE);
        Rectangle separator3 = new Rectangle(2, 80, Color.WHITE);

        // Boxes to align the scene
        HBox btnBox = new HBox(20, retryBtn, separator1, mainMenuBtn, separator2, shopBtn, separator3, quitBtn);
        VBox textBox = new VBox(20, deathPrompt, highScoreText, coinAmount, decisionPrompt);

        btnBox.setAlignment(Pos.CENTER);
        textBox.setAlignment(Pos.CENTER);

        setTop(textBox);
        setCenter(btnBox);
    }
}
