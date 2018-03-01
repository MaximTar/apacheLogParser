package main.java.com.github.apachelogparser.controller;

import javafx.beans.NamedArg;
import javafx.scene.control.Alert;

/**
 * Created by maxtar on 01.03.18.
 */

class AlertHandler extends Alert {

    AlertHandler(@NamedArg("alertType") AlertType alertType, String alertText) {
        super(alertType, alertText);
        super.setHeaderText(null);
        super.setTitle(alertType.toString());
        super.showAndWait();
    }
}
