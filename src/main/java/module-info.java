module mz.co.jmtecnologias.multigest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    
    
    opens mz.co.jmtecnologias.multigest to javafx.fxml;
    exports mz.co.jmtecnologias.multigest;
}
