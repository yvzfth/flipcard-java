package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HomeController implements MenuBarNavigation, Initializable {

    private String iconPath;
    protected ArrayList<String> files;
    protected Pane[] panes;
    protected String username = Read.getAllContent(ACTIVE_USER_PATH).trim();

    @FXML
    private ImageView userIcon;
    @FXML
    private Label usernameMenuBar;
    @FXML
    private FlowPane flowPaneHome;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;

    public void setUsernameMenuBar(String username) {
        this.usernameMenuBar.setText(username);
    }

    public void setUserIcon() throws IOException {

        this.iconPath = Read.getIconPath(username);
        userIcon.setImage(new Image(new FileInputStream(iconPath)));

    }

    @FXML
    public void search(MouseEvent event) {
        // search button
        String searchWords = searchTextField.getText();
        ArrayList<String> titles = Read.getAllPublicTitle();
        ArrayList<String> txtRelativePaths = Read.getAllPublicTextFilePaths();

        List<String> searchedTitles = searchTitle(searchWords, titles);

        // clear FlowPane
        flowPaneHome.getChildren().clear();

        files = getMatchedTxtPaths(searchedTitles, txtRelativePaths);
        panes = new Pane[files.size()];

        initSharedPosts(files, panes, flowPaneHome);

    }

    @FXML
    void searchEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            // search button
            String searchWords = searchTextField.getText();
            ArrayList<String> titles = Read.getAllPublicTitle();
            ArrayList<String> txtRelativePaths = Read.getAllPublicTextFilePaths();

            List<String> searchedTitles = searchTitle(searchWords, titles);

            // clear FlowPane
            flowPaneHome.getChildren().clear();

            files = getMatchedTxtPaths(searchedTitles, txtRelativePaths);
            panes = new Pane[files.size()];

            initSharedPosts(files, panes, flowPaneHome);

        }

    }

    private ArrayList<String> getMatchedTxtPaths(List<String> searchedTitles, List<String> txtRelativePaths) {
        ArrayList<String> matchedTxtPaths = new ArrayList<String>();
        for (String title : searchedTitles) {
            for (String txtPath : txtRelativePaths) {
                if (title.equals(txtPath.split("/")[4].split(".txt")[0]))
                    matchedTxtPaths.add(txtPath);
            }
        }
        return matchedTxtPaths;
    }

    private List<String> searchTitle(String searchWords, List<String> titles) {
        List<String> searchWordList = Arrays.asList(searchWords.trim().split(" "));
        return titles.stream().filter(input -> {
            return searchWordList.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    @Override
    public void goToLogin(MouseEvent event) throws IOException {

        SCENE_CHANGER.loadScene(LOGIN_PATH, event);
    }

    @Override
    public void goToHome(MouseEvent event) throws IOException {

        SCENE_CHANGER.loadScene(HOME_PATH, event);
    }

    @Override
    public void goToLibrary(MouseEvent event) throws IOException {

        SCENE_CHANGER.loadScene(LIBRARY_PATH, event);
    }

    @Override
    public void goToNewList(MouseEvent event) throws IOException {

        SCENE_CHANGER.loadScene(NEWLIST_PATH, event);
    }

    @Override
    public void goToLiked(MouseEvent event) throws IOException {

        SCENE_CHANGER.loadScene(LIKED_PATH, event);
    }

    @FXML
    public void openFileChooser(MouseEvent event) throws IOException {
        Pane pane = new Pane();
        Stage stage = new Stage();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        File initialDirectory = new File("lib/assets/avatars/");
        FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(initialDirectory);
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));
        File file = fileChooser.showOpenDialog(stage.getScene().getWindow());

        if (file != null) {
            iconPath = file.getPath();
            Write.add(DATA_PATH + username + "/" + "userInfo.txt",
                    Read.getAllContent(DATA_PATH + username + "/" + "userInfo.txt")
                            .replace(Read.getIconPath(username), iconPath));
            userIcon.setImage(new Image(iconPath));
        } else {
            setUserIcon();
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

        // titles = FXCollections.observableArrayList(READER.getAllPublicTitle());

        files = Read.getAllPublicTextFilePaths();
        panes = new Pane[files.size()];

        initSharedPosts(files, panes, flowPaneHome);

    }

    public void initSharedPosts(ArrayList<String> files, Pane[] panes, FlowPane parentPane) {
        for (int i = 0; i < panes.length; i++) {
            String txtRelativePath = files.get(i);

            panes[i] = new HomeItem().item();
            try {
                String title = Read.getTitle(txtRelativePath);
                String sharerUsername = Read.getUsername(txtRelativePath);
                boolean isActiveUserLiked = Read.isActiveUserLiked(username, title, sharerUsername);
                // ((Button)((Pane) panes[i].getChildren().get(1))
                // .getChildren().get(0))
                // .setGraphic(new ImageView(new Image( new FileInputStream(
                // READER.getCategoryImagePath(txtRelativePath)))));
                ((ImageView) ((Pane) panes[i].getChildren().get(1))
                        .getChildren().get(0))
                                .setImage(new Image(new FileInputStream(
                                        Read.getCategoryImagePath(txtRelativePath))));

                ((Label) ((Pane) ((HBox) panes[i].getChildren().get(0))
                        .getChildren().get(0))
                                .getChildren().get(0))
                                        .setText(title);

                (((Label) ((HBox) ((VBox) ((HBox) panes[i].getChildren().get(0))
                        .getChildren().get(1))
                                .getChildren().get(1))
                                        .getChildren().get(0)))
                                                .setText(String.valueOf(Read.countCards(txtRelativePath)));

                if (isActiveUserLiked) {

                    ((Button) ((HBox) ((HBox) panes[i].getChildren().get(2))
                            .getChildren().get(0))
                                    .getChildren().get(0))
                                            .setGraphic(new HomeItem().redHeartImageView());

                } else {

                    ((Button) ((HBox) ((HBox) panes[i].getChildren().get(2))
                            .getChildren().get(0))
                                    .getChildren().get(0))
                                            .setGraphic(new HomeItem().emptyHeartImageView());

                }
                ((Label) ((HBox) ((HBox) ((HBox) panes[i].getChildren().get(2))
                        .getChildren().get(0))
                                .getChildren().get(1))
                                        .getChildren().get(0))
                                                .setText(String.valueOf(Read.countLikes(sharerUsername, title)));

                ((ImageView) ((Pane) ((HBox) ((HBox) panes[i].getChildren().get(2))
                        .getChildren().get(1))
                                .getChildren().get(0))
                                        .getChildren().get(0))
                                                .setImage(new Image(new FileInputStream(
                                                        Read.getIconPath(sharerUsername))));

                ((Label) ((HBox) ((HBox) panes[i].getChildren().get(2))
                        .getChildren().get(1))
                                .getChildren().get(1))
                                        .setText(sharerUsername);

                parentPane.getChildren().add(panes[i]);

            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }

}
