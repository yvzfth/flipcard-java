package application;

import java.io.IOException;

import javafx.scene.input.MouseEvent;

public interface SignupPageNavigation extends LoginPageNavigation {
    String SIGNUP_PATH = "signup.fxml";
    String APP_ICON = "file:lib/assets/icons/flash-cards.png";

    public void goToSignup(MouseEvent event) throws IOException;
}
