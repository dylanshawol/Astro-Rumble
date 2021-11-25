package Music;

/**
 * This is the music player this controls all the music in the game making sure it does not loop
 *
 * @author Connor Cozad
 */

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

    public static MediaPlayer musicPlayer;
    public static Media media;
    public static MediaPlayer soundPlayer;
    public static Media soundMedia;

    public MusicPlayer() {
        soundPlayer = new MediaPlayer(soundMedia);
        musicPlayer = new MediaPlayer(media);
    }

    public static MediaPlayer getPlayer() { return musicPlayer; }

    public static MediaPlayer getSoundPlayer() {
        return soundPlayer;
    }


}
