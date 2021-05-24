package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SimApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        System.out.println(javafx.scene.text.Font.getFamilies());
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane root =
                    (BorderPane)loader.load(getClass().getResource("SimGUI.fxml").openStream());
            root.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
            primaryStage.setScene(new Scene(root,1100,700));
            primaryStage.show();
            root.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}