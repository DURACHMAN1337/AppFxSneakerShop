package Denis;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddUserController {

    @FXML
    private TextField setSurnameField;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private Button addUserButton;

    @FXML
    private TextField setLoginField;

    @FXML
    private TextField setCountryField;

    @FXML
    private TextField setNameField;

    @FXML
    private CheckBox registrationCheckBoxFemale;

    @FXML
    private CheckBox registrationCheckBoxMale;

    @FXML
    private PasswordField setPriorityField;

    @FXML
    private Button backButton;


    @FXML
    void initialize(){
        addUserButton.setOnAction(event -> {
            addUser();
            App.openNewScene("/Denis/successfulAdd.fxml",addUserButton);
        });

        backButton.setOnAction(event -> {
            App.openNewScene("/Denis/superUser.fxml",backButton);
        });
    }

    private void addUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = setNameField.getText();
        String surname = setSurnameField.getText();
        String userName = setLoginField.getText();
        String password = setPasswordField.getText();
        String location = setCountryField.getText();
        String priority = setPriorityField.getText();
        String gender = "";
        if (registrationCheckBoxMale.isSelected())
            gender = "Мужской";
        else
            gender = "Женский";

        User user = new User(firstName,surname,userName,password,location,gender,priority);

        dbHandler.signUpUser(user);

    }

}
