module Denis{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Denis to javafx.fxml;
    exports Denis;
}