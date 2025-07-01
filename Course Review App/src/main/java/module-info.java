module hw.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires java.persistence;
    requires org.xerial.sqlitejdbc;
    opens edu.virginia.cs.gui to javafx.fxml;
    exports edu.virginia.cs.gui;
}