package GUI;
import MODEL.FileManagement;
import MODEL.Patogen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimControler {

    FileManagement fileManagement = new FileManagement();
    ArrayList<Patogen> patogens;
    HashMap<HBox, ArrayList<String>> patogenMap = new HashMap<>();
    @FXML
    VBox PatogensBox;
    @FXML
    VBox simulationPlan;
    @FXML
    BorderPane mainPane;
    @FXML
    Pane pitchPane;
    @FXML
    Pane background;

    @FXML
    public void initialize()
    {
        patogens = fileManagement.readJson();

        for (Patogen patogen: patogens
             ) {
            PatogensBox.getChildren().add(new PatogenBlock(patogen, mainPane, patogenMap) );

        }
        addSimulation();


    }

    @FXML
    public void reset()
    {

    }

    @FXML
    public void step() {

    }


    @FXML
    public void addPatogen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            PatogenDataControler p;
            fxmlLoader.setLocation(getClass().getResource("PatogenDataGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 550, 450);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
            stage.setTitle("Wprowadz Patogen");
            stage.setScene(scene);
            stage.show();
            p = fxmlLoader.getController();
            p.SetEverything(fileManagement, patogens, PatogensBox, mainPane, patogenMap);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

   public void addSimulation ()
   {
       VBox containter = new VBox();
       simulationPlan.getChildren().add(containter);
       GridPane gridPane = new GridPane();
       containter.setId("simulationContainter");
       gridPane.setId("gridPane");
       TextField repeat = new TextField();
       gridPane.add(new Label("Ilość powtórzeń: "), 0,0);
       gridPane.add(repeat, 1,0);
       gridPane.add(new Label("Plik konfiguracyjny: "), 0,1);
       Pane configPane = new Pane();
       configPane.setId("configPane");
       gridPane.add(configPane, 1,1);
       gridPane.add(new Label("Patogeny: "), 0,2);
       HBox patogens = new HBox();
       patogenMap.put(patogens, new ArrayList<String>());
       patogens.setId("patogens");
       containter.getChildren().add(gridPane);
       containter.getChildren().add(patogens);


   }



}

