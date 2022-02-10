package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class NewListController extends HomeController {

    private Pane[] panes = new Pane[20];
    private String title;
    private String category;
    private String front, back;
    private boolean isNewListCreated;
    private boolean isInEditMode;
    private String editModeTitle;
    private ArrayList<String> titleArray;
    private ObservableList<String> categories = FXCollections.observableArrayList(
            "Vocabulary", "Music",
            "Animals", "Cloathes", "Colors",
            "Food", "Fruits", "House", "Kitchen",
            "Professions", "School", "Travels", "Vegetables",
            "None");

    @FXML
    private VBox clipBoard;
    @FXML
    private Pane comboBoxParent;
    @FXML
    private TextField enterTitleTextField;
    @FXML
    private Button saveButton;

    private ComboBox<String> comboBox = new ComboBox<String>();

    public Pane[] getPanes() {
        return this.panes;
    }

    public void setEditModeTitle(String text) {
        this.editModeTitle = text;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String text) {
        category = text;
    }

    public void setIsInEditMode(boolean booleanValue) {
        this.isInEditMode = booleanValue;
    }

    public TextField getEnterTitleTextField() {
        return this.enterTitleTextField;
    }

    public ComboBox<String> getComboBox() {
        return this.comboBox;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUsernameMenuBar(username);
        titleArray = Read.getAllTitles(DATA_PATH + username + "/");
        try {
            setUserIcon();
        } catch (IOException e) {

            e.printStackTrace();
        }
        comboBox.getItems().addAll(categories);
        comboBox.setPromptText("Select Category");
        comboBox.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        comboBoxParent.getChildren().add(comboBox);

        for (int i = 0; i < panes.length; i++) {
            try {
                panes[i] = FXMLLoader.load(getClass().getResource("clipBoardItem.fxml"));
            } catch (IOException e) {

                e.printStackTrace();
            }
            clipBoard.getChildren().add(panes[i]);
        }
    }

    @FXML
    public void saveList(MouseEvent event) throws FileNotFoundException, IOException {
        // save the list

        if (enterTitleTextField.getText().trim().isEmpty()) {
            ErrorDialogWindow.showAlert("Title Error", "Title Missing", "Please enter a title");
        } else if (!isInEditMode && titleArray.contains(enterTitleTextField.getText())) {
            ErrorDialogWindow.showAlert("Title Error", "Title Already Used", "Please enter another title");
        } else if (comboBox.getValue() == null) {
            ErrorDialogWindow.showAlert("Category Error", "Category Missing", "Please select a category");
        } else {

            title = enterTitleTextField.getText();

            if (isInEditMode && !title.equals(editModeTitle)) {

                try {
                    DataManager.renameFileTo(username, editModeTitle, title);
                    DataManager.renamePublicFileTo(username, editModeTitle, title);
                } catch (IOException e) {

                    e.printStackTrace();
                }

            }

            category = comboBox.getValue();
            String categoryImagePath = category + ".jpg";
            isNewListCreated = DataManager.isFileExists(username, title);

            if (isNewListCreated)
                Write.deleteContent(DATA_PATH + username + "/" + title + ".txt");
            try {

                DataManager.createFile(username, title);
                Write.add(DATA_PATH + username + "/" + title + ".txt", title, categoryImagePath);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
            for (int i = 0; i < panes.length; i++) {

                for (int j = 0; j < 2; j++) {

                    String result = ((TextField) panes[i].getChildren().get(j)).getText().trim();
                    if (j % 2 == 0) {
                        front = result;
                    } else {
                        back = result;
                    }
                }
                if (front.isEmpty() ^ back.isEmpty()) {
                    int errorRow = i + 1;
                    ErrorDialogWindow.showAlert("List Error", "List Input Missing",
                            "Please fill both field in row " + String.valueOf(errorRow));

                } else if (!front.isEmpty() && !back.isEmpty()) {
                    try {

                        Write.add(DATA_PATH + username + "/" + title + ".txt", front, back);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            isNewListCreated = true;
        }
        try {
            if (isNewListCreated) {
                if (DataManager.isFileShared(username, title)) {
                    DataManager.deleteEditedFileInPublic(username, title);
                    DataManager.createPublicFile(username, title);
                    Write.shareFile(username, title);

                }
                if (Read.countCards(DATA_PATH + username + "/" + title + ".txt") == 0) {
                    DataManager.deleteFile(username, title);
                }
                goToLibrary(event);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
