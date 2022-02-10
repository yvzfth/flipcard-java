package application;

import java.io.IOException;

import javafx.scene.input.MouseEvent;

public interface MenuBarNavigation extends LoginPageNavigation {
    String LIBRARY_PATH = "library.fxml";
    String NEWLIST_PATH = "newlist.fxml";
    String LIKED_PATH = "liked.fxml";
    String LIBRARY_CARDVIEW_PATH = "libraryCardView.fxml";
    String LIBRARY_CARDPLAY_PATH = "libraryCardPlay.fxml";
    String EDITLIST_PATH = "editList.fxml";

    public void goToLibrary(MouseEvent event) throws IOException;

    public void goToNewList(MouseEvent event) throws IOException;

    public void goToLiked(MouseEvent event) throws IOException;

}
