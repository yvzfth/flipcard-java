package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(SignupPageNavigation.SIGNUP_PATH));
        Scene scene = new Scene(root);
        stage.setTitle("FlipCard");
        stage.getIcons().add(new Image(SignupPageNavigation.APP_ICON));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
