module sn.javafx_diti4_2026 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens sn.javafx_diti4_2026.Entity;
    opens sn.javafx_diti4_2026 to javafx.fxml;
    exports sn.javafx_diti4_2026;
}