package Denis;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfulSignUpController {

    @FXML
    private Button okayButton;

    @FXML
    void initialize(){
        okayButton.setOnAction(event -> {
            App.openNewScene("/Denis/primary.fxml",okayButton);
        });
    }

}
