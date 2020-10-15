package Denis;

import Denis.animations.Shake;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class UsersTableController {

    @FXML
    private Label TextWithChanges;

    @FXML
    private TableView<ObservableList> tableUsers;

    @FXML
    private TextField IdField;

    @FXML
    private Label superuserRoleTextLabel1111;

    @FXML
    private TextField UsernameField;

    @FXML
    private TextField FirstnameField;

    @FXML
    private Button findUserButton;

    @FXML
    private ImageView FindUser;

    @FXML
    private Button upRoleButton;

    @FXML
    private ImageView UpRole;

    @FXML
    private Button deleteUserButton;

    @FXML
    private ImageView DeleteUser;

    @FXML
    private Button addUserButton;

    @FXML
    private ImageView AddUser;

    @FXML
    private Button downRoleButton;

    @FXML
    private ImageView DownRole;

    @FXML
    private Button backButton;

    @FXML
    private ImageView BackIcon;

    @FXML
    private Button logOutButton;

    @FXML
    private Button rewindButton;



    @FXML
    void initialize() {

        String SQL = "SELECT * FROM " + Const.USER_TABLE;
        buildData(SQL);

        rewindButton.setOnAction(event -> {
            App.openNewScene("/Denis/usersTable.fxml",rewindButton);
        });

        backButton.setOnAction(event -> {
            App.openNewScene("/Denis/superUser.fxml", backButton);
        });
        logOutButton.setOnAction(event -> {
            App.openNewScene("/Denis/primary.fxml", logOutButton);
        });
        addUserButton.setOnAction(event -> {
            App.openNewScene("/Denis/addUser.fxml",addUserButton );
        });

        findUserButton.setOnAction(event -> {
            findUser(IdField.getText().trim(),FirstnameField.getText().trim(),UsernameField.getText().trim());
        });

        downRoleButton.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            dbHandler.downPriority(UsernameField.getText().trim());
        });

        upRoleButton.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            dbHandler.upPriority(UsernameField.getText().trim());
        });

        deleteUserButton.setOnAction(event -> {
            DatabaseHandler handler = new DatabaseHandler();
            handler.deleteUser(UsernameField.getText().trim(),IdField.getText().trim());
        });










    }

    public ObservableList<ObservableList> buildData(String SQL) {
        ObservableList<ObservableList> data;
        DatabaseHandler dbHandler = new DatabaseHandler();
        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = dbHandler.getDbConnection().createStatement().executeQuery(SQL);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableUsers.getColumns().addAll(col);
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableUsers.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return data;


    }

    public void findUser (String id, String name, String username)  {


        if (!id.isEmpty()){
            String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE idusers = '" + id + "';";
            buildData(select);
        }
        else if(!name.isEmpty()){
            String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE firstname = '" + name + "';";
            buildData(select);
        }
        else if (!username.isEmpty()){
            String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE username = '" + username + "';";
            buildData(select);
        }
        else if (name.isEmpty() && id.isEmpty() && username.isEmpty()){
            Shake nameFieldAnim = new Shake(FirstnameField);
            Shake usernameFieldAnim = new Shake(UsernameField);
            Shake idFieldAnim = new Shake(IdField);
            nameFieldAnim.playAnim();
            usernameFieldAnim.playAnim();
            idFieldAnim.playAnim();
        }


    }
}
