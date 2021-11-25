package Game;

import Models.Spaceship;
import Music.MusicPlayer;
import Panes.OptionsPane;
import javafx.scene.image.Image;
import Scenes.MainMenuScene;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;



public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    public static Stage stage;
    final public static double WIDTH = 1336.0; // 1366
    final public static double HEIGHT = 768.0; // 768
    public static Spaceship player = new Spaceship();
    Media media;


    @Override
    public void start(Stage primaryStage) throws AWTException {
        // Makes stage global
        stage = primaryStage;
        MainMenuScene mainMenuScene = new MainMenuScene();

        // Set stage properties
        /**Main Menu Song effect by:Connor Cozad**/
         media = new Media(new File("src/Music/mainMenu.mp3").toURI().toString());

         //This will set the music for the main menu
        MusicPlayer.musicPlayer = new MediaPlayer(media);
        MusicPlayer.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MusicPlayer.musicPlayer.setVolume(OptionsPane.newMusicVolume);
        MusicPlayer.musicPlayer.play();

        stage.setScene(mainMenuScene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false);
        stage.setTitle("Astro Rumble");
        stage.getIcons().add(new Image("file:src/Images/Misc/astroRumbleIcon.png"));
        stage.show();
    }
}
