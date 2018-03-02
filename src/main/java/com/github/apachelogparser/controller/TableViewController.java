package main.java.com.github.apachelogparser.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;

public class TableViewController {

    @FXML
    private TextField filterField;
    @FXML
    private Text nameLabel;
    @FXML
    private TableView<LogString> table;
    private ObservableList<LogString> logData = FXCollections.observableArrayList();

    public TableViewController() {
    }

    void setLogData(ObservableList<LogString> data) {
        logData = data;
    }

    Text getNameLabel() {
        return nameLabel;
    }

    TableView<LogString> getTable() {
        return table;
    }

    @FXML
    private void initialize() {
        filterField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                table.setItems(logData);
            }
            String value = newValue.toLowerCase();
            ObservableList<LogString> subentries = FXCollections.observableArrayList();

            long count = table.getColumns().stream().count();
            for (int i = 0; i < table.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + table.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(table.getItems().get(i));
                        break;
                    }
                }
            }
            table.setItems(subentries);
        });
    }

    public void handleReturnButtonAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.getFirstViewPath()));
        Parent root = loader.load();
        FirstViewController controller = loader.getController();
        Main.getPrimaryStage().setTitle("Apache Log Parser");
        GraphicsDevice[] gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        Main.getPrimaryStage().setScene(new Scene(root, gd[gd.length - 1].getDisplayMode().getWidth() / 2, 275));
        Main.getPrimaryStage().show();
        controller.fillComboBox();
    }
}