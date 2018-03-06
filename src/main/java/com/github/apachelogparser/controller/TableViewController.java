package main.java.com.github.apachelogparser.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableViewController {

    @FXML
    private TextField filterField;
    @FXML
    private Text nameLabel;
    @FXML
    private TableView<LogString> table;
    private ObservableList<LogString> logData = FXCollections.observableArrayList();
    private final static Logger LOGGER = Logger.getLogger(FirstViewController.class.getName());

    static {
        LOGGER.setUseParentHandlers(false);
        FileHandler fh = Main.getFileHandler();
        LOGGER.addHandler(fh);
    }

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

            long count = (long) table.getColumns().size();
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

    public void handleExportButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export log to CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));
        fileChooser.setInitialFileName("*.csv");
        File file = fileChooser.showSaveDialog(Main.getPrimaryStage());
        if (file != null) {
            if (!file.getName().contains(".")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }
            if (!file.getName().endsWith(".csv")) {
                new AlertHandler(Alert.AlertType.WARNING, file.getName() + " has no valid file-extension.");
            } else {
                try (FileWriter fw = new FileWriter(file)) {
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (List<String> logString : FirstViewController.getLogData()) {
                        StringBuilder sb = new StringBuilder();
                        for (String element : logString) {
                            sb.append(element).append(",");
                        }
                        sb.append("\n");
                        bw.write(sb.toString());
                    }
                    bw.close();
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "IOException while creating new FileWriter. StackTrace: "
                            + Arrays.toString(e.getStackTrace()));
                    new AlertHandler(Alert.AlertType.ERROR, "Program could not save file.\n" +
                            "Grant the rights to the program so that it can access the filesystem.");
                }
            }
        }
    }
}