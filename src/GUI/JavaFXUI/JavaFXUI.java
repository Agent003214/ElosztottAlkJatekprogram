package GUI.JavaFXUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        gridPane.setPadding(new Insets(15));
        gridPane.setVgap(15);
        gridPane.setHgap(15);
        gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        // Játékos panel és komponensei

        GridPane jatekos_panel = new GridPane();
        jatekos_panel.setBorder(black);
        jatekos_panel.setPadding(new Insets(15));
        jatekos_panel.setVgap(15);
        jatekos_panel.setHgap(15);

        Label jatekos_nev_label = new Label("Név:");
        TextField jatekos_nev_tb = new TextField();
        Button jatekos_felvetel_bt = new Button("Felvétel");
        Label jatekos_targy_felvetel_label = new Label("Tárgy felvétele:");
        ComboBox<String> jatekos_targy_ddl = new ComboBox<String>();
        Button jatekos_targy_felvetel_bt = new Button("Felvétel");

        jatekos_panel.add(jatekos_nev_label,0,0);
        jatekos_panel.add(jatekos_nev_tb,1,0);
        jatekos_panel.add(jatekos_felvetel_bt,1,1);
        jatekos_panel.add(jatekos_targy_felvetel_label,0,2);
        jatekos_panel.add(jatekos_targy_ddl,1,2);
        jatekos_panel.add(jatekos_targy_felvetel_bt,1,3);

        Label jatekos_label = new Label("Játékos");
        gridPane.add(jatekos_label,0,0);
        gridPane.add(jatekos_panel,0,1);

        // NPC panel és komponensei

        GridPane NPC_panel = new GridPane();
        NPC_panel.setBorder(black);
        NPC_panel.setPadding(new Insets(15));
        NPC_panel.setVgap(15);
        NPC_panel.setHgap(15);

        Label NPC_nev_label = new Label("Név:");
        TextField NPC_nev_tb = new TextField();
        Button NPC_felvetel_bt = new Button("Felvétel");
        Label NPC_targy_felvetel_label = new Label("Tárgy felvétele:");
        ComboBox<String> NPC_targy_ddl = new ComboBox<String>();
        Button NPC_targy_felvetel_bt = new Button("Felvétel");

        NPC_panel.add(NPC_nev_label,0,0);
        NPC_panel.add(NPC_nev_tb,1,0);
        NPC_panel.add(NPC_felvetel_bt,1,1);
        NPC_panel.add(NPC_targy_felvetel_label,0,2);
        NPC_panel.add(NPC_targy_ddl,1,2);
        NPC_panel.add(NPC_targy_felvetel_bt,1,3);

        Label NPC_label = new Label("NPC");
        gridPane.add(NPC_label,1,0);
        gridPane.add(NPC_panel,1,1);

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
