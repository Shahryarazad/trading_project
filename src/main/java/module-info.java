module com.example.trading_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.opencsv;

    opens INFO to javafx.fxml,java.base;
    exports INFO;
    opens com.example.trading_project to javafx.fxml,java.base;
    exports com.example.trading_project;
}