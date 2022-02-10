package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class LibraryCardView extends LibraryItem {

    protected ArrayList<String> frontCards;
    protected ArrayList<String> backCards;
    protected int currentIndex;
    protected int currentCardIntValue;
    protected boolean isFlipped;

    @FXML
    protected Pane card;

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
    protected Label titleLabel;

    public void setTitle(String text) {
        this.titleLabel.setText(text);
    }

    public void setNumberOfCards(String text) {
        this.numberOfCards.setText(text);
    }

    public Label getCardText() {
        return this.cardText;
    }

    @FXML
    public void delete(MouseEvent event) {
        try {
            String title = titleLabel.getText();
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setTitle("Delete Alert");
            confirmation.setHeaderText("Delete Confirmation");
            confirmation.setContentText("Are you sure you want to delete the '" + title + "' list");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (DataManager.isFileShared(username, title))
                    DataManager.deleteSharedFile(username, title);

                DataManager.deleteFile(username, title);

                SCENE_CHANGER.loadScene(LIBRARY_PATH, event);
            }
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    @FXML
    public void edit(MouseEvent event) {
        try {

            SCENE_CHANGER.goToEditList(username, titleLabel.getText(), event);
        } catch (IOException e1) {

            e1.printStackTrace();
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
    public void play(MouseEvent event) {
        try {

            // selectedListPath = titleLabel.getText() + ".txt";

            SCENE_CHANGER.goToLibraryCardView(username, titleLabel.getText(), LIBRARY_CARDPLAY_PATH, event);
        } catch (IOException e1) {

            e1.printStackTrace();
        }
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
    public void share(MouseEvent event) {
        try {

            title = titleLabel.getText();
            DataManager.createPublicFile(username, title);
            DataManager.createPublicFile(username, title + "-likes");
            Write.shareFile(username, title);
            SCENE_CHANGER.loadScene(HOME_PATH, event);
        } catch (IOException e1) {

            e1.printStackTrace();
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
