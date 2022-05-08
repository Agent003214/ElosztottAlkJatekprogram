package GUI.JavaFXUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaFXUI extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        Border black = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(2)));
        primaryStage.setTitle("Játékprogram");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(16));
        gridPane.setVgap(16);
        gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        GridPane jatekos_panel = new GridPane();
        jatekos_panel.setBorder(black);
        jatekos_panel.setPadding(new Insets(15));
        jatekos_panel.setVgap(15);
        jatekos_panel.setHgap(15);

        Label jatekos_nev_label = new Label("Név:");
        TextField jateoks_nev_tb = new TextField();
        Button jatekos_felvetel_bt = new Button("Felvétel");
        Label jatekos_targy_felvetel_label = new Label("Tárgy felvétele:");
        ComboBox<String> jatekos_targy_ddl = new ComboBox<String>();
        Button jatekos_targy_felvetel_bt = new Button("Felvétel");
        jatekos_panel.add(jatekos_nev_label,0,0);
        jatekos_panel.add(jateoks_nev_tb,1,0);
        jatekos_panel.add(jatekos_felvetel_bt,1,1);
        jatekos_panel.add(jatekos_targy_felvetel_label,0,2);
        jatekos_panel.add(jatekos_targy_ddl,1,2);
        jatekos_panel.add(jatekos_targy_felvetel_bt,1,3);

        gridPane.add(jatekos_panel,1,1);


        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
