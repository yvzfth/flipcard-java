package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LoginController extends SignupController {

    @FXML
    private TextField usernameLoginPage;
    @FXML
    private PasswordField passwordLoginPage;

    @FXML
    public void loginSubmit(MouseEvent event) throws IOException {

        username = usernameLoginPage.getText();
        password = passwordLoginPage.getText();
        if (!Read.isValidUsername(USERS_PATH, username)) {
            ErrorDialogWindow.showAlert("Username Alert", "Username Invalid", "Username Invalid!\nTry Again");

        } else if (!Read.isValidPassword(USERS_PATH, username, password)) {
            ErrorDialogWindow.showAlert("Password Alert", "Password Invalid", "Password Invalid!\nTry Again");

        } else {

            Write.add(ACTIVE_USER_PATH, username);
            goToHome(event);
        }
    }

    @Override
    public void submitKeyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {

            username = usernameLoginPage.getText();
            password = passwordLoginPage.getText();
            if (!Read.isValidUsername(USERS_PATH, username)) {
                ErrorDialogWindow.showAlert("Username Alert", "Username Invalid", "Username Invalid!\nTry Again");

            } else if (!Read.isValidPassword(USERS_PATH, username, password)) {
                ErrorDialogWindow.showAlert("Password Alert", "Password Invalid", "Password Invalid!\nTry Again");

            } else {

                Write.add(ACTIVE_USER_PATH, username);
                SCENE_CHANGER.goToHomeKeyPressed(event);
            }
        }
    }

}
