package application;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

public class SignupController implements SignupPageNavigation {

    @FXML
    private TextField emailSignupPage;

    @FXML
    private PasswordField passwordSignupPage;

    @FXML
    private Button submitButton;

    @FXML
    private TextField usernameSignupPage;

    protected String email;
    protected String username;
    protected String password;

    @FXML
    public void signupSubmit(MouseEvent event) throws IOException {
        email = emailSignupPage.getText();
        username = usernameSignupPage.getText();
        password = passwordSignupPage.getText();
        if (!SignupValidation.emailCheckSignup(email)) {
            ErrorDialogWindow.showAlert("Email Alert", "Typo", SignupValidation.getErrorMessageEmail());

        } else if (Read.isValidEmail(USERS_PATH, email)) {
            ErrorDialogWindow.showAlert("Email Alert", "Invalid Email", "The Email Alread Used");

        } else if (!SignupValidation.usernameCheckSignup(username)) {
            ErrorDialogWindow.showAlert("Username Alert", "Typo", SignupValidation.getErrorMessageUsername());

        } else if (Read.isValidUsername(USERS_PATH, username)) {
            ErrorDialogWindow.showAlert("Username Alert", "Invalid Username", "The Username Alread Used");

        } else if (!SignupValidation.passwordCheckSignup(password)) {
            ErrorDialogWindow.showAlert("Password Alert", "Typo", SignupValidation.getErrorMessagePassword());

        } else {
            DataManager.createFolder(username);
            DataManager.createFile(username, "userInfo");
            DataManager.createFile(username, "liked");
            DataManager.createPublicFolder(username);

            Write.add(DATA_PATH + username + "/" + "userInfo.txt", email, username, password, DEFAULT_ICON);
            Write.add(USERS_PATH, email, username, password);
            Write.add(ACTIVE_USER_PATH, username);
            goToHome(event);
        }
    }

    @FXML
    public void submitKeyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {

            email = emailSignupPage.getText();
            username = usernameSignupPage.getText();
            password = passwordSignupPage.getText();
            if (!SignupValidation.emailCheckSignup(email)) {
                ErrorDialogWindow.showAlert("Email Alert", "Typo", SignupValidation.getErrorMessageEmail());

            } else if (Read.isValidEmail(USERS_PATH, email)) {
                ErrorDialogWindow.showAlert("Email Alert", "Invalid Email", "The Email Alread Used");

            } else if (!SignupValidation.usernameCheckSignup(username)) {
                ErrorDialogWindow.showAlert("Username Alert", "Typo", SignupValidation.getErrorMessageUsername());

            } else if (Read.isValidUsername(USERS_PATH, username)) {
                ErrorDialogWindow.showAlert("Username Alert", "Invalid Username", "The Username Alread Used");

            } else if (!SignupValidation.passwordCheckSignup(password)) {
                ErrorDialogWindow.showAlert("Password Alert", "Typo", SignupValidation.getErrorMessagePassword());

            } else {
                DataManager.createFolder(username);
                DataManager.createFile(username, "userInfo");
                DataManager.createFile(username, "liked");
                DataManager.createPublicFolder(username);

                Write.add(DATA_PATH + username + "/" + "userInfo.txt", email, username, password, DEFAULT_ICON);
                Write.add(USERS_PATH, email, username, password);
                Write.add(ACTIVE_USER_PATH, username);
                SCENE_CHANGER.goToHomeKeyPressed(event);
            }
        }
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
    public void goToSignup(MouseEvent event) throws IOException {

        SCENE_CHANGER.loadScene(SIGNUP_PATH, event);
    }

}
