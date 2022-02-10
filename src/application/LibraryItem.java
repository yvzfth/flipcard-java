package application;

import java.io.IOException;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LibraryItem extends LibraryController {

    private Button play;
    private Button edit;
    private Button share;
    private Button delete;
    private Label titleLabel;
    protected String title;

    // styling for library cards
    private String titleLabelStyle = "-fx-font-weight: 300;" + "-fx-font-size: 20px;";
    private String cardsLabelStyle = "-fx-font-size: 16px;" + "-fx-font-weight: 300;";
    private String titlePaneStyle = "-fx-background-radius: 10 10 0 0;" +
            "-fx-background-insets: 0, 1;" +
            "-fx-outer-border: black;" +
            " -fx-body-color: #F79CDF;" +
            "-fx-background-color: -fx-outer-border, -fx-body-color;";
    private String cardsHBoxStyle = "-fx-background-radius: 10 10 0 0;" +
            " -fx-background-insets: 0, 1;" +
            "-fx-outer-border: black;" +
            "-fx-body-color: #F4F79C;" +
            "-fx-background-color: -fx-outer-border, -fx-body-color;";
    private String bottomStyle = "-fx-background-radius: 0 0 10 10;" +
            " -fx-background-insets: 0, 1;" +
            "-fx-outer-border: black;" +
            "-fx-body-color: #FFFFFF;" +
            " -fx-background-color: -fx-outer-border, -fx-body-color;";
    private String playButtonStyle = "-fx-background-color:  #40C663;" +
            "-fx-border-width:  3;" +
            "-fx-border-color:  #000000;" +
            "-fx-border-insets:  -1;" +
            "-fx-border-radius: 7;" +
            "-fx-background-radius: 7;";
    private String editButtonStyle = "-fx-background-color: #E6D93E;" +
            "-fx-border-width:  3;" +
            "-fx-border-color:  #000000;" +
            "-fx-border-insets:  -1;" +
            "-fx-border-radius: 7;" +
            "-fx-background-radius: 7;";
    private String shareButtonStyle = "-fx-background-color:   #9CCAF7;" +
            "-fx-border-width:  3;" +
            "-fx-border-color:  #000000;" +
            "-fx-border-insets:  -1;" +
            "-fx-border-radius: 7;" +
            "-fx-background-radius: 7;";
    private String deleteButtonStyle = "-fx-background-color:   #CA1D56;" +
            "-fx-border-width:  3;" +
            "-fx-border-color:  #000000;" +
            "-fx-border-insets:  -1;" +
            "-fx-border-radius: 7;" +
            "-fx-background-radius: 7;";

    public HBox topBar() {
        HBox top = new HBox();
        Pane titlePane = new Pane();
        HBox cardsHBox = new HBox();
        titleLabel = new Label("");
        Label numberOfCards = new Label("0");
        Label cardsLabel = new Label("cards");
        VBox cardsVBox = new VBox();
        Pane empty = new Pane();
        titleLabel.setPrefSize(200, 50);
        numberOfCards.setPrefSize(25, 30);
        cardsLabel.setPrefSize(45, 30);
        titleLabel.setMaxSize(200, 50);
        numberOfCards.setMaxSize(25, 30);
        cardsLabel.setMaxSize(45, 30);
        titleLabel.setAlignment(Pos.CENTER);
        numberOfCards.setAlignment(Pos.CENTER);
        cardsLabel.setAlignment(Pos.CENTER_LEFT);

        empty.setPrefSize(70, 20);
        empty.setMaxSize(70, 20);
        cardsVBox.getChildren().addAll(empty, cardsHBox);
        titlePane.setPrefSize(200, 50);
        cardsHBox.setPrefSize(70, 30);
        cardsHBox.setMaxSize(70, 30);

        top.setSpacing(30);

        titlePane.setStyle(titlePaneStyle);
        cardsHBox.setStyle(cardsHBoxStyle);
        titleLabel.setStyle(titleLabelStyle);
        cardsLabel.setStyle(cardsLabelStyle);
        numberOfCards.setStyle(cardsLabelStyle);

        titlePane.getChildren().add(titleLabel);
        cardsHBox.getChildren().addAll(numberOfCards, cardsLabel);

        top.getChildren().addAll(titlePane, cardsVBox);
        return top;
    }

    public Pane imagePane() {
        Pane imagePane = new Pane();
        ImageView categoryImage = new ImageView(new Image("file:lib/assets/photos/None.jpg"));

        categoryImage.setFitHeight(200);
        categoryImage.setFitWidth(300);
        imagePane.setPrefSize(300, 200);
        imagePane.getChildren().add(categoryImage);

        categoryImage.setOnMouseClicked(e -> {
            try {
                imageClickEventHandler(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });
        return imagePane;
    }

    public HBox bottomBar() {
        HBox bottom = new HBox();

        bottom.setPadding(new Insets(2, 28, 2, 28));
        bottom.setMaxSize(300, 40);
        bottom.setSpacing(34);
        bottom.setStyle(bottomStyle);

        play = new Button("");
        edit = new Button("");
        share = new Button("");
        delete = new Button("");
        ImageView playIcon = new ImageView(new Image("file:lib/assets/icons/play-button.png"));
        ImageView editIcon = new ImageView(new Image("file:lib/assets/icons/edit.png"));
        ImageView shareIcon = new ImageView(new Image("file:lib/assets/icons/share.png"));
        ImageView deleteIcon = new ImageView(new Image("file:lib/assets/icons/bin.png"));

        play.setStyle(playButtonStyle);
        edit.setStyle(editButtonStyle);
        share.setStyle(shareButtonStyle);
        delete.setStyle(deleteButtonStyle);

        play.setPadding(new Insets(0, 0, 0, 0));
        edit.setPadding(new Insets(0, 0, 0, 0));
        share.setPadding(new Insets(0, 0, 0, 0));
        delete.setPadding(new Insets(0, 0, 0, 0));

        playIcon.setFitHeight(30);
        playIcon.setFitWidth(30);
        editIcon.setFitHeight(30);
        editIcon.setFitWidth(30);
        shareIcon.setFitHeight(30);
        shareIcon.setFitWidth(30);
        deleteIcon.setFitHeight(30);
        deleteIcon.setFitWidth(30);

        play.setGraphic(playIcon);
        edit.setGraphic(editIcon);
        share.setGraphic(shareIcon);
        delete.setGraphic(deleteIcon);

        play.setPrefSize(30, 30);
        edit.setPrefSize(30, 30);
        share.setPrefSize(30, 30);
        delete.setPrefSize(30, 30);
        play.setMaxSize(30, 30);
        edit.setMaxSize(30, 30);
        share.setMaxSize(30, 30);
        delete.setMaxSize(30, 30);

        play.setOnMouseClicked(e -> {
            try {
                playButtonClickEventHandler(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });
        edit.setOnMouseClicked(e -> {
            try {
                editButtonClickEventHandler(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });
        share.setOnMouseClicked(e -> {
            try {
                shareButtonClickEventHandler(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });
        delete.setOnMouseClicked(e -> {
            try {
                deleteButtonClickEventHandler(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });

        bottom.getChildren().addAll(play, edit, share, delete);

        return bottom;
    }

    public VBox item() {

        VBox parent = new VBox();
        parent.setPrefSize(300, 290);

        HBox top = topBar();
        Pane imagePane = imagePane();
        HBox bottom = bottomBar();
        // top.getChildren().addAll(titlePane, cardsVBox);
        // bottom.getChildren().addAll(play, edit, share, delete);
        parent.getChildren().addAll(top, imagePane, bottom);

        return parent;
    }

    public void imageClickEventHandler(MouseEvent event) throws IOException {
        try {

            SCENE_CHANGER.goToLibraryCardView(username, titleLabel.getText(), LIBRARY_CARDVIEW_PATH, event);
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    public void playButtonClickEventHandler(MouseEvent event) throws IOException {
        try {

            SCENE_CHANGER.goToLibraryCardView(username, titleLabel.getText(), LIBRARY_CARDPLAY_PATH, event);
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    public void editButtonClickEventHandler(MouseEvent event) throws IOException {
        try {

            SCENE_CHANGER.goToEditList(username, titleLabel.getText(), event);
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    public void shareButtonClickEventHandler(MouseEvent event) throws IOException {
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

    public void deleteButtonClickEventHandler(MouseEvent event) throws IOException {
        try {
            title = titleLabel.getText();
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
}
