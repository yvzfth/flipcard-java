package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LibraryController extends HomeController {

    @FXML
    private FlowPane flowPaneLibrary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUsernameMenuBar(username);
        try {
            setUserIcon();
        } catch (IOException e) {

            e.printStackTrace();
        }

        files = Read.getAllFilePaths(username);
        panes = new Pane[files.size()];

        for (int i = 0; i < panes.length; i++) {

            panes[i] = new LibraryItem().item();
            try {
                ((ImageView) ((Pane) panes[i].getChildren().get(1))
                        .getChildren().get(0))
                                .setImage(new Image(
                                        new FileInputStream(Read.getCategoryImagePath(files.get(i)))));

                ((Label) ((Pane) ((HBox) panes[i].getChildren().get(0))
                        .getChildren().get(0))
                                .getChildren().get(0))
                                        .setText(Read.getTitle(files.get(i)));

                (((Label) ((HBox) ((VBox) ((HBox) panes[i].getChildren().get(0))
                        .getChildren().get(1))
                                .getChildren().get(1))
                                        .getChildren().get(0)))
                                                .setText(String.valueOf(Read.countCards(files.get(i))));

                flowPaneLibrary.getChildren().add(panes[i]);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }

        }

    }

}