package application;

import java.io.IOException;

import javafx.scene.input.MouseEvent;

public interface HomePageNavigation {
    SceneChanger SCENE_CHANGER = new SceneChanger();

    String USERS_PATH = "lib/data/users.txt";
    String ACTIVE_USER_PATH = "lib/data/activeUser.txt";
    String USER_INFO_PATH = "userInfo.txt";
    String LIKED_PATH = "liked.txt";
    String DATA_PATH = "lib/data/";
    String PUBLIC_PATH = "lib/data/public/";

    String PHOTOS_PATH = "lib/assets/photos/";
    String DEFAULT_ICON = "lib/assets/avatars/hacker.png";

    String HOME_PATH = "home.fxml";
    String SHARED_CARDVIEW_PATH = "sharedCardView.fxml";
    String SHARED_CARDPLAY_PATH = "sharedCardPlay.fxml";

    public void goToHome(MouseEvent event) throws IOException;
}
