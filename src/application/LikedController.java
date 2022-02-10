package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;

public class LikedController extends HomeController {

    @FXML
    private FlowPane flowPaneLiked;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUsernameMenuBar(username);

        try {
            setUserIcon();
        } catch (IOException e) {

            e.printStackTrace();
        }

        // titles = FXCollections.observableArrayList(READER.getAllPublicTitle());

        try {
            files = Read.getLikedLists(username);
        } catch (IOException e) {

            e.printStackTrace();
        }
        panes = new Pane[files.size()];

        initSharedPosts(files, panes, flowPaneLiked);

    }

}