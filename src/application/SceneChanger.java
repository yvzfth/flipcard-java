package application;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class SceneChanger {
    private Scene scene;
    private Stage stage;
    private Parent root;

    /** load menu pages and signup, login pages */
    public void loadScene(String fxmlPath, MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlPath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** load home page with key press */
    public void goToHomeKeyPressed(KeyEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(HomePageNavigation.HOME_PATH));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** load library card view if clicked category image */
    public void goToLibraryCardView(String username, String title, String fxmlPath, MouseEvent event)
            throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        root = loader.load();

        LibraryCardView libCardView = loader.getController();
        libCardView.setTitle(Read.getTitle(LibraryController.DATA_PATH + username + "/" + title + ".txt"));

        libCardView.frontCards = Read.getAllFrontCards(LibraryController.DATA_PATH + username + "/" + title + ".txt");
        libCardView.backCards = Read.getAllBackCards(LibraryController.DATA_PATH + username + "/" + title + ".txt");

        libCardView.setNumberOfCards(String.valueOf(libCardView.backCards.size()));
        libCardView.getCardText().setText(libCardView.frontCards.get(0));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** load card view page from home */
    public void goToSharedCardView(String sharerUsername, String title, String fxmlPath, MouseEvent event)
            throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        root = loader.load();

        SharedCardView sharedCardView = loader.getController();

        sharedCardView.setTitle(Read.getTitle(HomeController.PUBLIC_PATH + sharerUsername + "/" + title + ".txt"));
        sharedCardView.setCardSharerUsername(sharerUsername);
        sharedCardView.setCardSharerIcon(Read.getIconPath(sharerUsername));

        sharedCardView.frontCards = Read
                .getAllFrontCards(HomeController.PUBLIC_PATH + sharerUsername + "/" + title + ".txt");
        sharedCardView.backCards = Read
                .getAllBackCards(HomeController.PUBLIC_PATH + sharerUsername + "/" + title + ".txt");

        sharedCardView.setNumberOfCards(String.valueOf(sharedCardView.backCards.size()));
        sharedCardView.setCardText(sharedCardView.frontCards.get(0));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** load edit list page from library page */
    public void goToEditList(String username, String title, MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(EditListController.EDITLIST_PATH));
        root = loader.load();

        EditListController editList = loader.getController();

        editList.getTitleLabelEditPage().setText(title);
        editList.setEditModeTitle(title);
        editList.setCategory(Read.getCategory(EditListController.DATA_PATH + username + "/" + title + ".txt"));
        editList.setIsInEditMode(true);
        editList.getEnterTitleTextField().setText(title);
        editList.getComboBox().setValue(editList.getCategory());
        ArrayList<String> frontCards = Read
                .getAllFrontCards(EditListController.DATA_PATH + username + "/" + title + ".txt");
        ArrayList<String> backCards = Read
                .getAllBackCards(EditListController.DATA_PATH + username + "/" + title + ".txt");

        for (int i = 0; i < frontCards.size(); i++) {

            for (int j = 0; j < 2; j++) {

                if (j % 2 == 0) {
                    ((TextField) editList.getPanes()[i].getChildren().get(j)).setText(frontCards.get(i));
                } else {
                    ((TextField) editList.getPanes()[i].getChildren().get(j)).setText(backCards.get(i));
                }
            }
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
