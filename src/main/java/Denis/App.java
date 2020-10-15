package Denis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * JavaFX App
 */
public class App extends Application {

    Stage stage = new Stage();


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sneaker Shop");
        stage.show();

    }


    public static void openNewScene(String window, Button button){
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Sneaker Shop");
        stage.setScene(new Scene(root));
        stage.show();

    }





    public static void main(String[] args) throws SQLException, ClassNotFoundException {


//        DatabaseHandler dbHandler = new DatabaseHandler();
//       Connection connection = dbHandler.getDbConnection();
//       Statement statement = connection.createStatement();
//
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < 14; i++) {
//            String s = (int) (Math.random()*9) + "" + (int) (Math.random()*9) + "" + (int) (Math.random()*9) + "" + (int) (Math.random()*9);
//
//            set.add(Integer.parseInt(s));
//
//        }
//
//        for (Integer s : set) {
//            statement.executeUpdate("INSERT INTO chisla.numbers (number) VALUES('" + s + "');");
//        }






       launch();
    }



}