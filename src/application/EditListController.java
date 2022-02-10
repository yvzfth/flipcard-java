package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EditListController extends NewListController {
    @FXML
    private Label titleLabelEditPage;

    public Label getTitleLabelEditPage() {
        return this.titleLabelEditPage;
    }
}