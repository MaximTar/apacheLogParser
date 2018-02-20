package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Ui extends Application {

    static Stage primaryStage;

    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Parent root = loader.load();
        UiController controller = loader.getController();

        stage.setTitle("Apache Log Parser");
        GraphicsDevice[] gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        stage.setScene(new Scene(root, gd[gd.length - 1].getDisplayMode().getWidth() / 2, 275));
//        stage.setScene(new Scene(root, Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2, 275));
        primaryStage = stage;
        stage.show();

        controller.fillComboBox();
    }
}
