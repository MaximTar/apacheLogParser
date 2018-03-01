package main.java.com.github.apachelogparser.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TableViewController {//} implements Initializable {

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
}
