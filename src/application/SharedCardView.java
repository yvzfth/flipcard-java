package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class SharedCardView extends HomeItem {

    protected ArrayList<String> frontCards;
    protected ArrayList<String> backCards;

    protected int currentIndex;
    protected int currentCardIntValue;
    protected boolean isFlipped;

    @FXML
    protected Pane card;

    @FXML
    protected ImageView cardSharerIcon;

    @FXML
    protected Label cardSharerUsername;

    @FXML
    protected Label cardText;

    @FXML
    protected Pane cardViewPane;

    @FXML
    protected Label currentCard;

    @FXML
    private Button flipButton;

    @FXML
    private Button nextButton;

    @FXML
    protected Label numberOfCards;

    @FXML
    private Button previousButton;

    @FXML
    private Button startButton;

    @FXML
    protected Label titleLabel;

    public void setTitle(String text) {
        this.titleLabel.setText(text);
    }

    public void setNumberOfCards(String text) {
        this.numberOfCards.setText(text);
    }

    public void setCardText(String text) {
        this.cardText.setText(text);
    }

    public void setCardSharerUsername(String text) {
        this.cardSharerUsername.setText(text);
    }

    public void setCardSharerIcon(String iconPath) {
        try {
            this.cardSharerIcon.setImage(new Image(new FileInputStream(iconPath)));
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    @FXML
    public void flip(MouseEvent event) {
        if (!isFlipped) {
            card.setStyle("-fx-background-color: #13D49A;");
            cardText.setText(backCards.get(currentIndex));
            isFlipped = true;
        } else {
            card.setStyle("-fx-background-color: #1C9CC7;");
            cardText.setText(frontCards.get(currentIndex));
            isFlipped = false;
        }

    }

    @FXML
    public void next(MouseEvent event) {
        currentIndex = frontCards.indexOf(cardText.getText());
        currentCardIntValue = Integer.parseInt(currentCard.getText());
        if (currentCardIntValue < Integer.parseInt(numberOfCards.getText()))
            currentCard.setText(String.valueOf(currentCardIntValue + 1));
        if (isFlipped) {
            currentIndex = backCards.indexOf(cardText.getText());
            card.setStyle("-fx-background-color: #1C9CC7;");
            cardText.setText(frontCards.get(currentIndex));
            isFlipped = false;
        }
        if (currentIndex < frontCards.size() - 1) {
            currentIndex++;
        }
        cardText.setText(frontCards.get(currentIndex));

    }

    @FXML
    public void previous(MouseEvent event) {
        currentIndex = frontCards.indexOf(cardText.getText());
        currentCardIntValue = Integer.parseInt(currentCard.getText());
        if (currentCardIntValue > 1)
            currentCard.setText(String.valueOf(currentCardIntValue - 1));
        if (isFlipped) {
            currentIndex = backCards.indexOf(cardText.getText());
            card.setStyle("-fx-background-color: #1C9CC7;");
            cardText.setText(frontCards.get(currentIndex));
            isFlipped = false;
        }
        if (currentIndex > 0) {
            currentIndex--;
        }
        cardText.setText(frontCards.get(currentIndex));
    }

    @FXML
    public void start(MouseEvent event) {
        try {
            SCENE_CHANGER.goToSharedCardView(cardSharerUsername.getText(), titleLabel.getText(), SHARED_CARDPLAY_PATH,
                    event);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUsernameMenuBar(username);
        try {
            setUserIcon();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}