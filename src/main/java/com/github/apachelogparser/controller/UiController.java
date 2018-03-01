package main.java.com.github.apachelogparser.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import main.java.com.github.apachelogparser.parser.Reader;
import main.java.com.github.apachelogparser.ui.Ui;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UiController {
    @FXML
    private final FileChooser fileChooser = new FileChooser();
    @FXML
    private TextField newLogFormatTextField;
    @FXML
    private ComboBox<String> logFormatComboBox;
    @FXML
    private Label selectedFile;
    private String filePath;
    private String fileName;
    private String savedLogFormatsFileName = "savedLogFormats";
    private String commonLogFormat = "%h %l %u %t \"%r\" %>s %b";

    @FXML
    protected void handleSelectButtonAction() {
        File file = fileChooser.showOpenDialog(main.java.com.github.apachelogparser.ui.Ui.primaryStage);
        if (file != null) {
            filePath = file.getPath();
            fileName = file.getName();
            selectedFile.setText(filePath);
        } else {
            new AlertHandler(Alert.AlertType.WARNING, "Selected File Is Null!");
        }
    }

    public void handleRemoveButtonAction() {
        String selectedItem = logFormatComboBox.getSelectionModel().getSelectedItem();
        if (selectedItem.equals(commonLogFormat)) {
            new AlertHandler(Alert.AlertType.INFORMATION, "Could Not Delete Common Log Format.");
        } else {
            File inputFile = new File(savedLogFormatsFileName);
            File tempFile = new File("temp");
            try (FileReader fr = new FileReader(inputFile)) {
                BufferedReader reader = new BufferedReader(fr);
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                String currentLine;
                boolean deletedFlag = false;
                while ((currentLine = reader.readLine()) != null) {
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.equals(selectedItem) && !deletedFlag) {
                        deletedFlag = true;
                        continue;
                    }
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                //noinspection ResultOfMethodCallIgnored
                inputFile.delete();
                //noinspection ResultOfMethodCallIgnored
                tempFile.renameTo(inputFile);
                fillComboBox();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleAddButtonAction() throws IOException {
        if (MaskHandler.verifyMask(newLogFormatTextField.getText())) {
            // TODO CHECK IF NEW FORMAT ALREADY IN FILE
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(savedLogFormatsFileName, true)))) {
                out.println(newLogFormatTextField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            fillComboBox();
            newLogFormatTextField.clear();
        } else {
            new AlertHandler(Alert.AlertType.WARNING, "Your Log Format Is Not Valid!");
        }
    }

    public void handleParseButtonAction() throws IOException {
        if (Objects.equals(filePath, null)) {
            new AlertHandler(Alert.AlertType.WARNING, "Select Log File First!");
        } else {
            String selectedLogFormat = logFormatComboBox.getSelectionModel().getSelectedItem();
            if (MaskHandler.verifyMask(selectedLogFormat)) {
                MaskHandler.Parameters parameters = MaskHandler.getMaskParameters(selectedLogFormat);
                List<Character> delimiters = parameters.getDelimiters();
                Map<Integer, String> userParams = parameters.getUserParameters();
                List<List<String>> logData = Reader.readFile(filePath, delimiters, userParams);
                ObservableList<LogString> data = createObservableList(logData, parameters.getParameters());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/com/github/apachelogparser/ui/tableView.fxml"));
                GridPane gridPane = loader.load();
                TableViewController controller = loader.getController();
                controller.setLogData(data);
                controller.getNameLabel().setText("Log file: " + fileName);
                TableFiller.fillTable(controller, data, parameters);
                Ui.primaryStage.setScene(new Scene(gridPane));
            } else {
                new AlertHandler(Alert.AlertType.WARNING, "Selected Log Format Is Not Valid!");
            }
        }
    }

    public void fillComboBox() throws IOException {
        logFormatComboBox.getItems().clear();
        File savedLogFormatsFile = new File(savedLogFormatsFileName);
        boolean savedLogFormatsFileExists = savedLogFormatsFile.exists();
        if (!savedLogFormatsFileExists) {
            savedLogFormatsFileExists = savedLogFormatsFile.createNewFile();
        }
        if (savedLogFormatsFileExists) {
            List<String> logFormats = Reader.readLogFormats(savedLogFormatsFileName);
            logFormats.add(commonLogFormat);
            logFormatComboBox.getItems().addAll(logFormats);
            logFormatComboBox.getSelectionModel().selectFirst();
        } else {
            new AlertHandler(Alert.AlertType.ERROR,
                    "There Were Problems With Log Formats Download.\nTry To Restart The Program.");
        }
    }

    private static ObservableList<LogString> createObservableList(List<List<String>> data, Map<Integer, Character> maskParams) {
        ObservableList<LogString> observableArrayList = FXCollections.observableArrayList();
        observableArrayList.addAll(data.stream().map(element -> MaskHandler.buildLogString(maskParams, element)).collect(Collectors.toList()));
        return observableArrayList;
    }
}
