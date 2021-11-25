package Panes;


import Game.Main;
import Scenes.OptionsScene;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class for the CreditsPane. This will display all the content in the credits.
 *
 * @author Dylan Shawol
 */
public class CreditsPane extends BorderPane {
    public CreditsPane() throws FileNotFoundException {
        // Sets the scene background and cursor
        setBackground(new Background(new BackgroundImage(new Image("file:src/Images/Backgrounds/gameBackground.png", Main.WIDTH, Main.HEIGHT, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        setCursor(Cursor.NONE);

        // Fonts
        Font font = Font.loadFont(new FileInputStream(new File("src/Fonts/MajorMonoDisplayRegular.ttf")), 18);
        Font taskFont = Font.font(font.getFamily(), 26);
        Font titleFont = Font.font(font.getFamily(), 48);


        // Text and styling for our names in the credits
        Text dylan = new Text("dylan shawol");
        dylan.setFont(titleFont);
        dylan.setFill(Color.WHITE);

        Text connor = new Text("connor cozad");
        connor.setFont(titleFont);
        connor.setFill(Color.WHITE);

        Text joshua = new Text("joshua thompson");
        joshua.setFont(titleFont);
        joshua.setFill(Color.WHITE);


        // Text and styling for the things we contributed to the program
        Text dylanTask = new Text("Art & Graphics\n" +
                                  "Collision Detection\n" +
                                  "Main Menu & Animations\n" +
                                  "Opening & Credits\n" +
                                  "Players Menu\n" +
                                  "Options Menu\n" +
                                  "Death Menu");
        dylanTask.setTextAlignment(TextAlignment.CENTER);
        dylanTask.setFont(taskFont);
        dylanTask.setFill(Color.WHITE);

        Text connorTask = new Text("Audio & Music\n" +
                                   "Game Shop\n" +
                                   "Powerup Implementation\n" +
                                   "Coin Collision\n");
        connorTask.setTextAlignment(TextAlignment.CENTER);
        connorTask.setFont(taskFont);
        connorTask.setFill(Color.WHITE);

        Text joshuaTask = new Text("Collision Detetction\n" +
                                   "Gameplay Physics\n" +
                                   "Obstacle Implementation\n" +
                                   "Gameplay Animations\n" +
                                   "Score Tracking\n");
        joshuaTask.setTextAlignment(TextAlignment.CENTER);
        joshuaTask.setFont(taskFont);
        joshuaTask.setFill(Color.WHITE);

        // Underlines for our names in the credits
        Rectangle dylanLine = new Rectangle(dylan.getLayoutBounds().getWidth() + 10, 1, Color.WHITE);
        Rectangle connorLine = new Rectangle(connor.getLayoutBounds().getWidth() + 10, 1, Color.WHITE);
        Rectangle joshLine = new Rectangle(joshua.getLayoutBounds().getWidth() + 10, 1, Color.WHITE);

        // Boxes for the names, lines, and tasks
        VBox dylanVbox = new VBox(10, dylan, dylanLine, dylanTask);
        dylanVbox.setAlignment(Pos.CENTER);

        VBox connorVbox = new VBox(10, connor, connorLine, connorTask);
        connorVbox.setAlignment(Pos.CENTER);

        VBox joshuaVbox = new VBox(10, joshua, joshLine, joshuaTask);
        joshuaVbox.setAlignment(Pos.CENTER);

        // Scrolling animations for each member
        TranslateTransition dylanCreditScroll = new TranslateTransition(Duration.seconds(10), dylanVbox);
        TranslateTransition connorCreditScroll = new TranslateTransition(Duration.seconds(10), connorVbox);
        TranslateTransition joshuaCreditScroll = new TranslateTransition(Duration.seconds(10), joshuaVbox);

        // Creates an animation list and adds each animation
        ArrayList<TranslateTransition> scrollAnimationList = new ArrayList<>();
        scrollAnimationList.add(dylanCreditScroll);
        scrollAnimationList.add(connorCreditScroll);
        scrollAnimationList.add(joshuaCreditScroll);

        // Creates a parallel animation to run all 3 at the same time
        ParallelTransition creditScroll = new ParallelTransition();

        // for each animation in the list
        for (TranslateTransition animation : scrollAnimationList) {
            // Set it's starting X position to the middle of the screen (its current position)
            animation.setFromX(animation.getNode().getTranslateX());
            // Sets it's starting Y position to the bottom of the window (hidden)
            animation.setFromY(Main.HEIGHT);
            // Sets the ending Y position to the top of the window
            animation.setToY(0);
            creditScroll.getChildren().add(animation);
        }
        creditScroll.play();

        // Skips the CreditsScene, not that you would want to.
        setOnMouseClicked(e->{
            creditScroll.stop();
            try {
                Main.stage.setScene(new OptionsScene());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        setTop(joshuaVbox);
        setCenter(dylanVbox);
        setBottom(connorVbox);

    }
}
