module com.example.trading_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trading_project to javafx.fxml;
    exports com.example.trading_project;
}