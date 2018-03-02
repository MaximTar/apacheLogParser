package main.java.com.github.apachelogparser.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class Main extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.getFirstViewPath()));
        Main.primaryStage = stage;
        try {
            setFirstView(loader, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setFirstView(FXMLLoader loader, Stage stage) throws IOException {
        Parent root = loader.load();
        FirstViewController controller = loader.getController();
        stage.setTitle("Apache Log Parser");
        stage.setScene(new Scene(root, Main.getFirstViewWidth(), Main.getFirstViewHeight()));
        stage.show();
        controller.fillComboBox();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    // TODO MAYBE RELOCATE TO SPECIAL CLASS?
    public static String getFirstViewPath() {
        return "/main/resources/firstView.fxml";
    }

    public static String getTableViewPath() {
        return "/main/resources/tableView.fxml";
    }

    public static double getFirstViewWidth(){
        return Screen.getPrimary().getVisualBounds().getWidth() / 2;
    }

    public static double getFirstViewHeight() {
        return 275.;
    }
}
