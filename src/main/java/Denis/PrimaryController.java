package Denis;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Denis.animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController {


    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button authLogInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize(){
        authLogInButton.setOnAction(event -> {

            String loginText = login_field.getText().trim();
            String passwordText = password_field.getText().trim();

            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText,passwordText);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Error, Login or Password is empty");

        });


        loginSignUpButton.setOnAction(event -> {
            App.openNewScene("/Denis/registration.fxml",loginSignUpButton);


        });
    }

    private void loginUser(String loginText, String passwordText) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(passwordText);
        ResultSet result = dbHandler.getUser(user);
        ResultSet priorityResSet = dbHandler.getUser(user);



        int counter = 0;

       while (result.next()){
           counter++;
       }

        if (counter >= 1){
            String priority = null;
            while (priorityResSet.next()){
                priority = priorityResSet.getString("priority");
            }
            if (priority.equals("high")) {
                User.currentPriority = "high";
                App.openNewScene("superUser.fxml", authLogInButton);
            }
            else if (priority.equals("low")){
                User.currentPriority = "low";
                App.openNewScene("secondary.fxml",authLogInButton);

            }

        }
        else{
            Shake userLoginAnim = new Shake(login_field);
            Shake userPassAnim = new Shake(password_field);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }


    }



}
