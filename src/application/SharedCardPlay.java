package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class SharedCardPlay extends SharedCardView {

    private int trueCounter;
    private int falseCounter;
    private int emptyCounter;

    @FXML
    private Button falseButton;

    @FXML
    private Pane previousPagePane;

    @FXML
    private Button stopButton;

    @FXML
    private Button trueButton;

    private Button previousPageButton;

    public Button initPreviousPageButton() throws FileNotFoundException {
        String leftArrowIcon = "lib/assets/icons/left-arrow.png";
        String style = "-fx-background-radius: 16;" + "-fx-background-color: #BBBBBB;" +
                "-fx-border-color: #000000;" + "-fx-border-radius: 10;" + "-fx-border-width: 3;";
        previousPageButton = new Button("");
        previousPageButton.setStyle(style);
        previousPageButton.setPrefSize(36, 30);
        previousPageButton.setMaxSize(36, 30);
        Image leftArrow = new Image(new FileInputStream(leftArrowIcon));
        ImageView leftArrowView = new ImageView(leftArrow);
        leftArrowView.setFitHeight(30);
        leftArrowView.setFitWidth(36);

        previousPageButton.setGraphic(leftArrowView);
        previousPageButton.setOnMouseClicked(e -> {
            try {
                SCENE_CHANGER.goToSharedCardView(cardSharerUsername.getText(), titleLabel.getText(),
                        SHARED_CARDVIEW_PATH, e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }

        });
        return previousPageButton;
    }

    @FXML
    public void knownCard(MouseEvent event) throws InterruptedException {
        trueCounter++;

        currentIndex = frontCards.indexOf(cardText.getText());

        card.setStyle("-fx-background-color: #13D49A;");
        cardText.setText(backCards.get(currentIndex));
        Thread.sleep(100);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                card.setStyle("-fx-background-color: #1C9CC7;");

                currentCardIntValue = Integer.parseInt(currentCard.getText());
                if (currentCardIntValue < Integer.parseInt(numberOfCards.getText()))
                    currentCard.setText(String.valueOf(currentCardIntValue + 1));

                // flip to front of the next card
                if (currentIndex < frontCards.size() - 1) {
                    currentIndex++;
                }
                cardText.setText(frontCards.get(currentIndex));
                if (isCardsFinished())
                    try {
                        stopPlaying(event);
                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                    }

            }
        });

    }

    @FXML
    public void stopPlaying(MouseEvent event) throws FileNotFoundException {
        cardViewPane.getChildren().clear();
        stopButton.setStyle("-fx-background-color: #BBBBBB;");
        stopButton.setText("REPLAY");
        stopButton.setOnMouseClicked(e -> {
            try {
                SCENE_CHANGER.goToSharedCardView(cardSharerUsername.getText(), titleLabel.getText(),
                        SHARED_CARDPLAY_PATH, e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });
        // buttonsHBox.getChildren().clear();
        previousPagePane.getChildren().add(initPreviousPageButton());
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(falseCounter + " Unknown Words", falseCounter),
                new PieChart.Data(calcEmptyCounters() + " Missed Words", calcEmptyCounters()),
                new PieChart.Data(trueCounter + " Known Words", trueCounter));

        final DoughnutChart chart = new DoughnutChart(pieChartData);
        chart.setPadding(new Insets(50, 0, 0, 300));
        chart.setTitle("Statistics");
        cardViewPane.getChildren().add(chart);
    }

    @FXML
    public void unKnownCard(MouseEvent event) throws InterruptedException {
        falseCounter++;
        currentIndex = frontCards.indexOf(cardText.getText());

        card.setStyle("-fx-background-color: #13D49A;");
        cardText.setText(backCards.get(currentIndex));
        Thread.sleep(100);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                card.setStyle("-fx-background-color: #1C9CC7;");

                currentCardIntValue = Integer.parseInt(currentCard.getText());
                if (currentCardIntValue < Integer.parseInt(numberOfCards.getText()))
                    currentCard.setText(String.valueOf(currentCardIntValue + 1));

                // flip to front of the next card
                if (currentIndex < frontCards.size() - 1) {
                    currentIndex++;
                }
                cardText.setText(frontCards.get(currentIndex));
                if (isCardsFinished())
                    try {
                        stopPlaying(event);
                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                    }

            }
        });

    }

    public boolean isCardsFinished() {
        int totalCards = Integer.parseInt(numberOfCards.getText());
        if (totalCards == trueCounter + falseCounter)
            return true;
        return false;
    }

    public int calcEmptyCounters() {
        int totalCards = Integer.parseInt(numberOfCards.getText());
        this.emptyCounter = totalCards - trueCounter - falseCounter;
        return this.emptyCounter;
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
