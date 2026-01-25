module sn.javafx_diti4_2026 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;

    opens sn.javafx_diti4_2026.Entity to org.hibernate.orm.core, javafx.base, javafx.controls;
    opens sn.javafx_diti4_2026 to javafx.fxml;
    exports sn.javafx_diti4_2026;
    exports sn.javafx_diti4_2026.controllers;
    opens sn.javafx_diti4_2026.controllers to javafx.fxml;
}