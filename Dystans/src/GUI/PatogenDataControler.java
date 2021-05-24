package GUI;

import MODEL.FileManagement;
import MODEL.Patogen;
import MODEL.SimData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class PatogenDataControler {

    FileManagement fileManagement;
    ArrayList<SimData> patogens;
    VBox PatogensBox;

    boolean[] flags = new boolean[2];
    @FXML
    TextField name;
    @FXML
    TextField value1;
    @FXML
    Label info1;
    @FXML
    Label info2;

    public void SetEverything(FileManagement fileManagement, ArrayList<SimData> patogens, VBox PatogensBox) {
        this.fileManagement = fileManagement;
        this.patogens = patogens;
        this.PatogensBox = PatogensBox;
        for (Boolean b: flags
        ) {
            b = false;
        }


value1.setTextFormatter(new TextFormatter<>(t -> {
    if (t.isReplaced())
        if(t.getText().matches("[^0-9]"))
            t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));


    if (t.isAdded()) {
        if (t.getControlText().contains(".")) {
            if (t.getText().matches("[^0-9]")) {
                t.setText("");
            }
        } else if (t.getText().matches("[^0-9.]")) {
            t.setText("");
        }
    }

    return t;
}));
    }

    @FXML
    public void SavePatogen()
    {
        System.out.println(flags[0]);
        System.out.println(flags[1]);
        boolean canSave = true;

        for (Boolean b: flags
        ) {
            canSave =  canSave && b;
        }

        if(canSave)
        {
            Patogen patogen = new Patogen(name.getText(), Double.parseDouble(value1.getText()));
            fileManagement.writeJson(new Patogen(patogen.name, patogen.propability));
            PatogensBox.getChildren().add(new Button(name.getText()));
            patogens.add(patogen);
            name.setText("");
            value1.setText("");

        }
    }

    @FXML
    public void checkText()
    {
        flags[0] = true;
        info1.setText("");
        info1.setTextFill(Color.RED);
        String compare = name.getText();
        for (SimData data:patogens
        ) {
            if(compare.equals(data.name))
            {
                flags[0] = false;
                info1.setText("Nazwa zajeta");
                break;
            }
        }

    }
    @FXML
    public void checkValue()
    {
        flags[1] = true;
        info2.setTextFill(Color.RED);
        info2.setText("");
        if(!value1.getText().equals("") && !value1.getText().equals("."))
        {
            double value = Double.parseDouble(value1.getText());
            if( value < 0 || value > 1)
            {
                flags[1] = false;
                info2.setText("Zla wartosc");
            }
        }

    }
}
