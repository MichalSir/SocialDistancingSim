package GUI;

import MODEL.Patogen;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class PatogenBlock extends StackPane {
    PatogenBlock p;
    Patogen patogen;
    Text text;
    Region rectangle;
    BorderPane mainPane;
    HashMap<HBox, ArrayList<String>> patogens;

    public PatogenBlock(Patogen patogen, BorderPane mainPane,HashMap<HBox, ArrayList<String>> patogens) {
        this.patogen = patogen;
        this.mainPane = mainPane;
        this.patogens = patogens;
        this.text = new Text(patogen.name);
        this.text.setId("Text");
        this.text.setMouseTransparent(true);
        this.rectangle = new Region();
        this.rectangle.setId("rect");
        this.setMargin(rectangle, new Insets(2,0,2,0));
        this.getChildren().addAll(rectangle, text);
        this.setOnMousePressed(mouseEvent -> pressed(mouseEvent));
    }
    public PatogenBlock(Patogen patogen, HashMap<HBox, ArrayList<String>> patogens) {
        this.patogen = patogen;
        this.patogens = patogens;
        this.text = new Text(patogen.name);
        this.text.setMouseTransparent(true);
        this.text.setId("Text");
        this.rectangle = new Region();
        this.rectangle.setId("rect");
        this.getChildren().addAll(rectangle, text);
        this.setOpacity(0.8);
        this.setOnMousePressed(mouseEvent -> delete(mouseEvent));
    }

    private void pressed(MouseEvent event) {
        this.p = new PatogenBlock(this.patogen, patogens);
        mainPane.getChildren().add(this.p);
        this.p.setTranslateX(event.getSceneX());
        this.p.setTranslateY(event.getSceneY());
        mainPane.setOnMouseMoved(mouseEvent -> moved2(mouseEvent));
        mainPane.setOnMousePressed(mouseEvent -> pressed2(mouseEvent));
    }
    private void delete ( MouseEvent mouseEvent)
    {
        if(mouseEvent.getButton() == MouseButton.SECONDARY)
        {
            for (HBox hbox: patogens.keySet()) {
                Bounds hBounds = hbox.localToScene(hbox.getBoundsInLocal());
                Bounds pBounds = this.localToScene(this.getBoundsInLocal());
                if(hBounds.intersects(pBounds))
                {
                    patogens.get(hbox).remove(this.patogen.name);
                    hbox.getChildren().remove(this);

                }

            }
        }
    }
    private void pressed2(MouseEvent mouseEvent) {
        boolean duplicate = false;
        for (HBox hbox: patogens.keySet()) {
            Bounds hBounds = hbox.localToScene(hbox.getBoundsInLocal());
            Bounds pBounds = this.p.localToScene(this.p.getBoundsInLocal());
            if(hBounds.intersects(pBounds))
            {
                for(String s: patogens.get(hbox))
                {
                    if(s.equals(patogen.name))
                    {
                        duplicate = true;
                        break;
                    }
                }

                if(!duplicate)
                {
                    PatogenBlock add = new PatogenBlock(patogen, patogens);
                    patogens.get(hbox).add(patogen.name);
                    add.setOpacity(1);
                    hbox.getChildren().add(add);
                }

                break;
            }

        }
        mainPane.setOnMouseMoved(null);
        mainPane.setOnMousePressed(null);
        mainPane.getChildren().remove(this.p);
        this.p = null;



    }

    private void moved2(MouseEvent mouseEvent) {
        this.p.setTranslateX(mouseEvent.getSceneX());
        this.p.setTranslateY(mouseEvent.getSceneY());
    }

}
