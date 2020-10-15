package Denis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SecondaryController {

    @FXML
    private Button productsButton;

    @FXML
    private Button ordersButton;

    @FXML
    private Button customersButton;

    @FXML
    private Label TextWithChanges;

    @FXML
    private Button logOutButton;

    @FXML
    private PieChart pieChart;



    @FXML
    void initialize(){


        buildPieChart();
        logOutButton.setOnAction(event -> {
            App.openNewScene("/Denis/primary.fxml",logOutButton);
        });

        productsButton.setOnAction(event -> {
            App.openNewScene("/Denis/productsTable.fxml",productsButton);
        });

        ordersButton.setOnAction(event -> {
            App.openNewScene("/Denis/ordersTable.fxml",ordersButton);
        });

        customersButton.setOnAction(event -> {
            App.openNewScene("/Denis/customersTable.fxml",customersButton);
        });
    }

    public void buildPieChart() {
        DatabaseHandler dbhandler = new DatabaseHandler();
        String query = "SELECT brand, SUM(amount) AS amount FROM product GROUP BY brand";
        ObservableList<PieChart.Data> piechartdata;
        piechartdata = FXCollections.observableArrayList();

        ResultSet rs = null;
        try {
            rs = dbhandler.getDbConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                piechartdata.add(new PieChart.Data(rs.getString("brand"), rs.getDouble("amount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        pieChart.setData(piechartdata);
        pieChart.setVisible(true);
    }

}






