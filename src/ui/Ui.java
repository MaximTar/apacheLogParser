package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        stage.setScene(new Scene(root, 600, 275));
        primaryStage = stage;
        stage.show();

        controller.fillComboBox();
    }
}
