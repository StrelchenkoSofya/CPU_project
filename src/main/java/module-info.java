module org.example.lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.graphics;

    requires java.sql;

    // Зависимости от Hibernate и Jakarta Persistence
    requires java.naming;
    requires org.hibernate.orm.core;
    requires org.slf4j;
    requires jakarta.persistence;

    opens org.example.lab4 to javafx.fxml, org.hibernate.orm.core;
    opens org.example.lab4.processor to javafx.fxml, org.hibernate.orm.core;

    exports org.example.lab4;
}