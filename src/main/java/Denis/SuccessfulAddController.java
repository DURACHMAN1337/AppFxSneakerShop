package Denis;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfulAddController {

    @FXML
    private Button okayButton;

    @FXML
    void initialize(){
        okayButton.setOnAction(event -> {
            App.openNewScene("/Denis/usersTable.fxml",okayButton);
        });
    }

}