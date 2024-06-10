module com.example.trading_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.trading_project to javafx.fxml;
    exports com.example.trading_project;
}