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
import main.java.com.github.apachelogparser.parser.SplitterFileException;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class FirstViewController {
    private final static Logger LOGGER = Logger.getLogger(FirstViewController.class.getName());

    static {
        LOGGER.setUseParentHandlers(false);
        FileHandler fh = Main.getFileHandler();
        LOGGER.addHandler(fh);
    }

    @FXML
    private TextField newLogFormatTextField;
    @FXML
    private ComboBox<String> logFormatComboBox;
    @FXML
    private Label selectedFile;
    private String filePath;
    private String fileName;
    private final String savedLogFormatsFileName = "savedLogFormats";
    private final String commonLogFormat = "%h %l %u %t \"%r\" %>s %b";

    @FXML
    protected void handleSelectButtonAction() {
        File file = new FileChooser().showOpenDialog(Main.getPrimaryStage());
        if (file != null) {
            filePath = file.getPath();
            fileName = file.getName();
            selectedFile.setText(filePath);
        }
    }

    public void handleRemoveButtonAction() {
        String selectedItem = logFormatComboBox.getSelectionModel().getSelectedItem();
        if (selectedItem.equals(commonLogFormat)) {
            new AlertHandler(Alert.AlertType.INFORMATION, "Could not delete Common Log Format.");
        } else {
            File inputFile = new File(savedLogFormatsFileName);
            File tempFile = new File("temp");
            try (FileReader fr = new FileReader(inputFile)) {
                BufferedReader reader = new BufferedReader(fr);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
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
                    LOGGER.log(Level.WARNING, "IOException while creating new BufferedWriter. StackTrace: "
                            + Arrays.toString(e.getStackTrace()));
                    new AlertHandler(Alert.AlertType.ERROR, "Program could not write to the temporary file.\n" +
                            "Grant the rights to the program so that it can access the filesystem.");
                }
            } catch (FileNotFoundException e) {
                LOGGER.log(Level.WARNING, "FileNotFound while creating new FileReader. StackTrace: "
                        + Arrays.toString(e.getStackTrace()));
                new AlertHandler(Alert.AlertType.ERROR, "Program could not access the file with log formats.\n" +
                        "Grant the rights to the program so that it can access the filesystem.");
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "IOException from auto-closable resource. StackTrace: "
                        + Arrays.toString(e.getStackTrace()));
                new AlertHandler(Alert.AlertType.ERROR, "Program could not close resource.\n" +
                        "Grant the rights to the program so that it can access the filesystem.");
            }
        }
    }

    public void handleAddButtonAction() {
        if (MaskHandler.verifyMask(newLogFormatTextField.getText())) {
            String newLine = newLogFormatTextField.getText();
            try (FileWriter fw = new FileWriter(savedLogFormatsFileName, true)) {
                File file = new File(savedLogFormatsFileName);
                try (Scanner scanner = new Scanner(file)) {
                    boolean equalFlag = false;
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (Objects.equals(line, newLine)) {
                            equalFlag = true;
                        }
                    }
                    if (!equalFlag) {
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw);
                        out.println(newLine);
                        fillComboBox();
                        newLogFormatTextField.clear();
                    } else {
                        new AlertHandler(Alert.AlertType.INFORMATION,
                                "An added log format is already contained in the list.");
                    }
                } catch (FileNotFoundException e) {
                    LOGGER.log(Level.WARNING, "FileNotFoundException while creating new Scanner. StackTrace: "
                            + Arrays.toString(e.getStackTrace()));
                    new AlertHandler(Alert.AlertType.ERROR,
                            "Program could not read the file that contains saved log formats.\n" +
                            "Grant the rights to the program so that it can access the filesystem.");
                }
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "IOException while creating new FileWriter. StackTrace: "
                        + Arrays.toString(e.getStackTrace()));
                new AlertHandler(Alert.AlertType.ERROR,
                        "Program could not write to the file that contains saved log formats.\n" +
                        "Grant the rights to the program so that it can access the filesystem.");
            }
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
                List<List<String>> logData;
                try {
                    logData = Reader.readFile(filePath, delimiters, userParams);
                    ObservableList<LogString> data = createObservableList(logData, parameters.getParameters());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.getTableViewPath()));
                    GridPane gridPane = loader.load();
                    TableViewController controller = loader.getController();
                    controller.setLogData(data);
                    controller.getNameLabel().setText("Log file: " + fileName);
                    TableFiller.fillTable(controller, data, parameters);
                    double height = Main.getTableViewHeight();
                    double prefHeight = data.size() * 10 + 250;
                    if (prefHeight < height) {
                        height = prefHeight;
                    }
                    Main.getPrimaryStage().setScene(new Scene(gridPane, Main.getTableViewWidth(), height));
                } catch (SplitterFileException e) {
                    new AlertHandler(Alert.AlertType.ERROR, e.getMessage() + e.getStringNumber());
                }
            } else {
                new AlertHandler(Alert.AlertType.WARNING, "Selected Log Format Is Not Valid!");
            }
        }
    }

    public void fillComboBox() {
        logFormatComboBox.getItems().clear();
        File savedLogFormatsFile = new File(savedLogFormatsFileName);
        boolean savedLogFormatsFileExists = savedLogFormatsFile.exists();
        if (!savedLogFormatsFileExists) {
            try {
                savedLogFormatsFileExists = savedLogFormatsFile.createNewFile();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "IOException while creating new savedLogFormatsFile. StackTrace: "
                        + Arrays.toString(e.getStackTrace()));
                new AlertHandler(Alert.AlertType.ERROR,
                        "Program could not create file that will contain saved log formats.\n" +
                        "Grant the rights to the program so that it can access the filesystem.");
            }
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
