package main.java.com.github.apachelogparser.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@SuppressWarnings("WeakerAccess")
public class Main extends Application {

    private static Stage primaryStage;
    private static FileHandler fileHandler;
    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    //TODO MAYBE PUT IT INTO SEPARATE CLASS LOGGER?
    static {
        try {
            fileHandler = new FileHandler("log.log");
        } catch (IOException e) {
            new AlertHandler(Alert.AlertType.ERROR, "Program could not create log file.\n" +
                    "Grant the rights to the program so that it can access the filesystem.\n");
        }
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        LOGGER.setUseParentHandlers(false);
        FileHandler fh = Main.getFileHandler();
        LOGGER.addHandler(fh);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.getFirstViewPath()));
        Main.primaryStage = stage;
        setFirstView(loader, stage);
    }

    public static void setFirstView(FXMLLoader loader, Stage stage) {
        Parent root;
        try {
            root = loader.load();
            FirstViewController controller = loader.getController();
            stage.setTitle("Apache Log Parser");
            stage.setScene(new Scene(root, Main.getFirstViewWidth(), Main.getFirstViewHeight()));
            stage.show();
            controller.fillComboBox();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "IOException while loading FXMLLoader. StackTrace: "
                    + Arrays.toString(e.getStackTrace()));
            new AlertHandler(Alert.AlertType.ERROR, "Program could not load FXML.\n" +
                    "Try to restart the program.\n");
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static FileHandler getFileHandler() {
        return fileHandler;
    }

    // TODO MAYBE RELOCATE TO SPECIAL CLASS?
    public static String getFirstViewPath() {
        return "/main/resources/firstView.fxml";
    }

    public static String getTableViewPath() {
        return "/main/resources/tableView.fxml";
    }

    public static double getFirstViewWidth() {
        return Screen.getPrimary().getVisualBounds().getWidth() / 2;
    }

    public static double getFirstViewHeight() {
        return 275.;
    }
}
