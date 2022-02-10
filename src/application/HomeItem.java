package application;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HomeItem extends HomeController {

    // heart icon paths and default image path
    private String redHeartPath = "file:lib/assets/icons/heart-red.png";
    private String emptyHeartPath = "file:lib/assets/icons/heart.png";
    private String defaultCategoryImagePath = "file:lib/assets/photos/None.jpg";

    // styles for home card item
    private String usernameStyle = "-fx-font-weight: 300;" + "-fx-font-size: 16px;";
    private String titleLabelStyle = "-fx-font-weight: 300;" + "-fx-font-size: 20px;";
    private String cardsLabelStyle = "-fx-font-size: 16px;" + "-fx-font-weight: 300;";
    private String titlePaneStyle = "-fx-background-radius: 10 10 0 0;" + " -fx-background-insets: 0, 1;" +
            "-fx-outer-border: black;" + " -fx-body-color: #F79CDF;"
            + "-fx-background-color: -fx-outer-border, -fx-body-color;";
    private String cardsHBoxStyle = "-fx-background-radius: 10 10 0 0;" + " -fx-background-insets: 0, 1;"
            + "-fx-outer-border: black;" +
            "-fx-body-color: #F4F79C;" + "-fx-background-color: -fx-outer-border, -fx-body-color;";
    private String bottomStyle = "-fx-background-radius: 0 0 10 10;" + " -fx-background-insets: 0, 1;"
            + "-fx-outer-border: black;" +
            "-fx-body-color: #FFFFFF;" + " -fx-background-color: -fx-outer-border, -fx-body-color;";

    // heart icon image references
    private Image redHeartImage = new Image(redHeartPath);
    private Image emptyHeartImage = new Image(emptyHeartPath);

    private Button heartButton;
    private ImageView userPhoto;
    private Label usernameLabel;
    private Label titleLabel;
    private Label numberOfCards;
    private ImageView categoryImage;
    private ImageView heart;
    private Label numberOfLikes;

    public ImageView emptyHeartImageView() {
        heart = new ImageView(emptyHeartImage);
        heart.setFitHeight(30);
        heart.setFitWidth(36);
        return heart;
    }

    public ImageView redHeartImageView() {
        heart = new ImageView(redHeartImage);
        heart.setFitHeight(30);
        heart.setFitWidth(36);
        return heart;
    }

    public HBox topBar() {

        HBox top = new HBox();
        Pane titlePane = new Pane();
        HBox cardsHBox = new HBox();
        titleLabel = new Label("Title");
        numberOfCards = new Label("0");
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
        categoryImage = new ImageView(new Image(defaultCategoryImagePath));

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

    public HBox userHBox() {

        HBox userHBox = new HBox();
        userHBox.setSpacing(10);
        userHBox.setPrefSize(160, 40);
        userHBox.setAlignment(Pos.CENTER);
        Pane userPhotoPane = new Pane();

        try {
            userPhoto = new ImageView(new Image(new FileInputStream(
                    DEFAULT_ICON)));
        } catch (IOException e) {

            e.printStackTrace();
        }

        usernameLabel = new Label("");
        usernameLabel.setStyle(usernameStyle);
        usernameLabel.setPrefHeight(40);

        userPhotoPane.setPrefSize(30, 30);
        userPhotoPane.setMaxSize(30, 30);
        userPhoto.setFitWidth(30);
        userPhoto.setFitHeight(30);

        userPhotoPane.getChildren().add(userPhoto);
        userHBox.getChildren().addAll(userPhotoPane, usernameLabel);
        return userHBox;
    }

    public HBox bottomBar() {

        HBox bottom = new HBox();
        bottom.setStyle(bottomStyle);
        bottom.setPrefSize(300, 40);
        bottom.setAlignment(Pos.CENTER);

        heartButton = new Button("");
        heartButton.setStyle("-fx-background-color: transparent;");
        heartButton.setPrefSize(36, 30);
        heartButton.setMaxSize(36, 30);

        heart = emptyHeartImageView();

        heartButton.setOnMouseClicked(e -> {
            try {
                heartClickEventHandler(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });

        HBox likesHBox = new HBox();
        likesHBox.setSpacing(4);
        likesHBox.setPrefHeight(40);
        likesHBox.setAlignment(Pos.CENTER);
        HBox heartAndLikesHBox = new HBox();
        heartAndLikesHBox.setSpacing(10);
        heartAndLikesHBox.setPrefSize(160, 40);
        heartAndLikesHBox.setAlignment(Pos.CENTER);

        numberOfLikes = new Label("0");
        Label likesLabel = new Label("likes");

        heartButton.setGraphic(heart);

        likesHBox.getChildren().addAll(numberOfLikes, likesLabel);
        heartAndLikesHBox.getChildren().addAll(heartButton, likesHBox);

        HBox userHBox = userHBox();
        bottom.getChildren().addAll(heartAndLikesHBox, userHBox);

        return bottom;
    }

    public VBox item() {

        VBox parent = new VBox();
        parent.setPrefSize(300, 290);
        HBox top = topBar();
        Pane imagePane = imagePane();
        HBox bottom = bottomBar();

        parent.getChildren().addAll(top, imagePane, bottom);

        return parent;
    }

    public void imageClickEventHandler(MouseEvent event) throws IOException {
        try {

            SCENE_CHANGER.goToSharedCardView(usernameLabel.getText(), titleLabel.getText(), SHARED_CARDVIEW_PATH,
                    event);

        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    public void heartClickEventHandler(MouseEvent event) throws IOException {
        String title = titleLabel.getText();
        String senderUsername = usernameLabel.getText();
        // if(heart.getImage().getUrl().equals(emptyHeartPath)){
        if (!Read.isActiveUserLiked(username, title, senderUsername)) {
            heartButton.setGraphic(redHeartImageView());
            Write.addLike(username, title, senderUsername);
            Write.addToLiked(username, title, senderUsername);
            numberOfLikes.setText(String.valueOf(Read.countLikes(senderUsername, title)));
            // }else if(heart.getImage().getUrl().equals(redHeartPath)){
        } else {
            heartButton.setGraphic(emptyHeartImageView());
            Write.removeLike(username, title, senderUsername);
            Write.removeFromLiked(username, title, senderUsername);
            numberOfLikes.setText(String.valueOf(Read.countLikes(senderUsername, title)));
        }
    }

}
