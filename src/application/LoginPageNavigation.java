package application;

import java.io.IOException;

import javafx.scene.input.MouseEvent;

public interface LoginPageNavigation extends HomePageNavigation {
    String LOGIN_PATH = "login.fxml";

    public void goToLogin(MouseEvent event) throws IOException;
}
