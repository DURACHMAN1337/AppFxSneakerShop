package Denis;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField registrationSurname;

    @FXML
    private PasswordField createPasswordField;

    @FXML
    private Button registrationButton;

    @FXML
    private TextField createLoginField;

    @FXML
    private TextField registrationCountry;

    @FXML
    private TextField registrationName;


    @FXML
    private CheckBox registrationCheckBoxFemale;

    @FXML
    private CheckBox registrationCheckBoxMale;

    @FXML
    void initialize() {


       registrationButton.setOnAction(event -> {

           signUpNewUser();
           App.openNewScene("/Denis/successfulSignUp.fxml",registrationButton);

       });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = registrationName.getText();
        String surname = registrationSurname.getText();
        String userName = createLoginField.getText();
        String password = createPasswordField.getText();
        String location = registrationCountry.getText();
        String gender = "";
        if (registrationCheckBoxMale.isSelected())
            gender = "Мужской";
        else
            gender = "Женский";

        User user = new User(firstName,surname,userName,password,location,gender);

        dbHandler.signUpUser(user);

    }
}

