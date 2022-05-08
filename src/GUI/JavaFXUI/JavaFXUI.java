package GUI.JavaFXUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        //Tárgy panel és komponensei

        GridPane targy_panel = new GridPane();
        targy_panel.setBorder(black);
        targy_panel.setPadding(new Insets(15));
        targy_panel.setVgap(15);
        targy_panel.setHgap(15);

        Label targy_nev_label = new Label("Név:");
        TextField targy_nev_tb = new TextField();
        Label targy_suly_label = new Label("Súly:");
        TextField targy_suly_tb = new TextField();
        Button targy_felvetel_bt = new Button("Felvétel");


        targy_panel.add(targy_nev_label,0,0);
        targy_panel.add(targy_nev_tb,1,0);
        targy_panel.add(targy_suly_label,0,1);
        targy_panel.add(targy_suly_tb,1,1);
        targy_panel.add(targy_felvetel_bt,1,2);

        Label targy_label = new Label("Tárgy");
        gridPane.add(targy_label,2,0);
        gridPane.add(targy_panel,2,1);

        //Rendezés panel és komponensei

        String [] rendezesi_opciok = new String [] {"Név szerint","Súly szerint"};
        GridPane rendezes_panel = new GridPane();
        rendezes_panel.setVgap(15);
        rendezes_panel.setHgap(15);

        Label rendez_label = new Label("Rendezés:");
        ComboBox<String> rendezes_ddl = new ComboBox<String>(FXCollections.observableArrayList(rendezesi_opciok));

        rendezes_panel.add(rendez_label,0,0);
        rendezes_panel.add(rendezes_ddl,1,0);

        gridPane.add(rendezes_panel,0,2);

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
