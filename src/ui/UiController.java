package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import parser.Reader;
import tableCreator.MaskHandler;
import tableCreator.TableViewCreator;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class UiController {
    @FXML
    private final FileChooser fileChooser = new FileChooser();
    public ComboBox<String> logFormatComboBox;
    private String filePath = "";
    private String fileName;
    public Label selectedFile;
    private String savedLogFormatsFileName = "savedLogFormats";
    public TextField newLogFormatTextField;

    @FXML
    protected void handleSelectButtonAction() {
        File file = fileChooser.showOpenDialog(Ui.primaryStage);
        if (file != null) {
            filePath = file.getPath();
            fileName = file.getName();
            selectedFile.setText(filePath);
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
            logFormats.add("CLF (\"%h %l %u %t \"%r\" %>s %b\")");
            logFormatComboBox.getItems().addAll(logFormats);
            logFormatComboBox.getSelectionModel().selectFirst();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("There Were Problems With Log Formats Download.\nTry To Restart The Program.");
            alert.showAndWait();
        }
    }

    public void handleRemoveButtonAction() throws IOException {
        String selectedItem = logFormatComboBox.getSelectionModel().getSelectedItem();
        if (selectedItem.equals("CLF (\"%h %l %u %t \"%r\" %>s %b\")")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Could Not Delete Common Log Format");
            alert.showAndWait();
        } else {
            File inputFile = new File(savedLogFormatsFileName);
            File tempFile = new File("temp");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Your Log Format Is Not Valid!");
            alert.showAndWait();
        }
    }

    public void handleParseButtonAction() {
        if (Objects.equals(filePath, "")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Select Log File First!");
            alert.showAndWait();
        } else {
            String selectedLogFormat = logFormatComboBox.getSelectionModel().getSelectedItem();
            if (MaskHandler.verifyMask(selectedLogFormat)){
                MaskHandler.Parameters parameters = MaskHandler.getMaskParameters(selectedLogFormat);
                List<Character> delimiters = parameters.getDelimiters();
                List<List<String>> logData = Reader.readFile(filePath, delimiters);
                Ui.primaryStage.setScene(TableViewCreator.createScene(fileName, logData, parameters));
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Selected Log Format Is Not Valid!");
                alert.showAndWait();
            }

        }
    }
}
