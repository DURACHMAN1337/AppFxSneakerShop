package Denis;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.sql.ResultSet;

public class OrdersTableController {


    @FXML
    private Label TextWithChanges;

    @FXML
    private TableView<ObservableList> tableUsers;

    @FXML
    private Label superuserRoleTextLabel1111;

    @FXML
    private Button backButton;

    @FXML
    private ImageView BackIcon;

    @FXML
    private Button logOutButton;

    @FXML
    void initialize(){


        buildData();

        logOutButton.setOnAction(event -> {
            App.openNewScene("/Denis/primary.fxml", logOutButton);
        });

        backButton.setOnAction(event -> {
            if (User.currentPriority.equals("high"))
                App.openNewScene("/Denis/superUser.fxml",backButton);
            else
                App.openNewScene("/Denis/secondary.fxml",backButton);
        });
    }

    public ObservableList<ObservableList> buildData() {
        ObservableList<ObservableList> data;
        DatabaseHandler dbHandler = new DatabaseHandler();
        data = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM order" ;
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


}
