package GUI.JavaFXUI;

import Backend.Jatekos;
import Backend.NPC;
import Backend.Szereplo;
import Backend.Targy;
import Exceptions.NevStringException;
import Exceptions.SulyStringException;
import Exceptions.TargyNehezException;
import Main.Main;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class JavaFXUI extends Application
{

    private ArrayList<Targy> targyak = new ArrayList<>();

    private ArrayList<Jatekos> jatekosok = new ArrayList<>();

    private ArrayList<NPC> NPC_k = new ArrayList<NPC>();

    private Main m = new Main();
    @Override
    public void start(Stage javaFXUIStage)
    {
        Border black = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(2)));
        javaFXUIStage.setTitle("Játékprogram");
        javaFXUIStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Shield.png"))));
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
        ComboBox<Targy> jatekos_targy_ddl = new ComboBox<Targy>();
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
        ComboBox<Targy> NPC_targy_ddl = new ComboBox<Targy>();
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

        //ListBox-ok

        ListView<String> jatekos_lista = new ListView<String>();
        jatekos_lista.setPrefSize(300,150);
        gridPane.add(jatekos_lista,0,3);

        ListView<String> NPC_lista = new ListView<String>();
        NPC_lista.setPrefSize(300,150);
        gridPane.add(NPC_lista,1,3);

        ListView<String> targy_lista = new ListView<String>();
        targy_lista.setPrefSize(300,150);
        gridPane.add(targy_lista,2,3);

        //Utility gombok

        GridPane utility_panel = new GridPane();
        utility_panel.setVgap(15);
        utility_panel.setHgap(15);

        Button mentes_bt = new Button("Mentés");
        Button betoltes_bt = new Button("Betöltés");
        Button kilepes_bt = new Button("Kilépés");

        utility_panel.add(mentes_bt,0,0);
        utility_panel.add(betoltes_bt,1,0);
        utility_panel.add(kilepes_bt,2,0);

        kilepes_bt.setOnAction(event -> System.exit(0));

        utility_panel.setAlignment(Pos.CENTER);
        gridPane.add(utility_panel,1,4);

        //Játékos felvétele

        jatekos_felvetel_bt.setOnAction(event ->
        {
            try
            {
                jatekos_lista.getItems().clear();
                jatekosok.add(m.JatekosHozzaAd(jatekos_nev_tb.getText()));
                jatekos_lista.setItems(FXCollections.observableArrayList(jatekosok.get(jatekosok.size()-1).toString()));
                jatekos_felvetel_bt.setDisable(true);
            }
            catch (NevStringException exp)
            {
                errorAlert("Hiba","A név nem megfelelően lett megadva!","A játékos megadásánál a név nem megfelelően lett megadva. Ellenőrizze hogy a név ki lett-e töltve és megfelel-e az elvárásoknak.");
            }
        });

        //Tárgy felvétele a játékoshoz

        jatekos_targy_felvetel_bt.setOnAction(event ->
        {
            try
            {
                jatekosok.get(jatekosok.size()-1).addToInventory(jatekos_targy_ddl.getValue());
                targyakKiir(jatekos_lista,jatekosok);
            }
            catch (TargyNehezException exp)
            {
                errorAlert("Nincs elég mozgási sebesség!","A tárgy túl nehéz, nem tudjuk elvinni!","A játékos már nem tudja ezt a tárgyat elvinni mivel a súlya meghaladja a mozgási sebességet!");
            }
            catch (IndexOutOfBoundsException exp)
            {
                errorAlert("Hiba","Tárgy vagy Játékos nem lett létrehozva!","Mielőtt felvehetne tárgyat a Játékoshoz, győződjön meg róla hogy Játékos és tárgy is létezik.");
            }
            catch (NullPointerException exp)
            {
                errorAlert("Hiba","Először egy Tárgy felvétele szükséges!","Mielőtt felvehetne egy tárgyat a játékoshoz, létre kell hoznia egy tárgyat.");
            }
        });

        //NPC felvétele

        NPC_felvetel_bt.setOnAction(event ->
        {
            try
            {
                NPC_lista.getItems().clear();
                NPC_k.add(m.NPCHozzaAd(NPC_nev_tb.getText()));
                NPC_lista.setItems(FXCollections.observableArrayList(NPC_k.get(NPC_k.size()-1).toString()));
                NPC_felvetel_bt.setDisable(true);
            }
            catch (NevStringException exp)
            {
                errorAlert("Hiba","A név nem megfelelően lett megadva!","Az NPC megadásánál a név nem megfelelően lett megadva. Ellenőrizze hogy a név ki lett-e töltve és megfelel-e az elvárásoknak.");
            }
        });

        //Tárgy felvétele az NPC-hez

        NPC_targy_felvetel_bt.setOnAction(event ->
        {
            try
            {
                System.out.println(NPC_targy_ddl.getValue());
                NPC_k.get(NPC_k.size()-1).addToInventory(NPC_targy_ddl.getValue());
                targyakKiir(NPC_lista,NPC_k);
            }
            catch (IndexOutOfBoundsException exp)
            {
                errorAlert("Hiba","Tárgy vagy NPC nem lett létrehozva!","Mielőtt felvehetne tárgyat az NPC-hez, győződjön meg róla hogy NPC és tárgy is létezik.");
            }
            catch (NullPointerException exp)
            {
                errorAlert("Hiba","Először egy Tárgy felvétele szükséges!","Mielőtt felvehetne egy tárgyat az NPC-hez, létre kell hoznia egy tárgyat.");
            }
        });

        //Tárgy felvétele

        targy_felvetel_bt.setOnAction(event ->
        {
            try
            {
                targyak.add(m.Targyletrehoz(targy_nev_tb.getText(),targy_suly_tb.getText()));
                targy_lista.getItems().clear();
                jatekos_targy_ddl.getItems().removeAll();
                NPC_targy_ddl.getItems().removeAll();
                for (Targy targy : targyak)
                {
                    targy_lista.setItems(FXCollections.observableArrayList((targy.toString())));
                    jatekos_targy_ddl.setItems(FXCollections.observableArrayList(targy));
                    jatekos_targy_ddl.setValue(targyak.get(0));
                    NPC_targy_ddl.setItems(FXCollections.observableArrayList(targy));
                    NPC_targy_ddl.setValue(targyak.get(0));
                }
            }
            catch (SulyStringException exp)
            {
                errorAlert("Hiba","A súly nem megfelelően lett megadva!","A tárgy megadásánál a súly nem megfelelően lett megadva. Ellenőrizze hogy a súly ki lett-e töltve és megfelel-e az elvárásoknak.");
            }
            catch (NevStringException exp)
            {
                errorAlert("Hiba","A név nem megfelelően lett megadva!","A tárgy megadásánál a név nem megfelelően lett megadva. Ellenőrizze hogy a név ki lett-e töltve és megfelel-e az elvárásoknak.");
            }
        });

        //Rendezés

        rendezes_ddl.setOnAction(event ->
        {
            if(rendezes_ddl.getValue().equals("Név szerint"))
            {
                jatekosok.get(jatekosok.size()-1).NevSzerintRendez();
                targyakKiir(jatekos_lista,jatekosok);

                NPC_k.get(NPC_k.size()-1).NevSzerintRendez();
                targyakKiir(NPC_lista,NPC_k);
            }
            else
            {
                jatekosok.get(jatekosok.size()-1).SulySzerintRendez();
                targyakKiir(jatekos_lista,jatekosok);

                NPC_k.get(NPC_k.size()-1).SulySzerintRendez();
                targyakKiir(NPC_lista,NPC_k);
            }
        });



        Scene scene = new Scene(gridPane);
        javaFXUIStage.setScene(scene);
        javaFXUIStage.show();
    }
    private void targyakKiir (ListView<String> lista, ArrayList<? extends Szereplo> list)
    {
        lista.getItems().clear();
        lista.setItems(FXCollections.observableArrayList(jatekosok.get(jatekosok.size()-1).toString()));
        lista.setItems(FXCollections.observableArrayList("Tárgyak:"));

        for (int i = 0; i < list.get(list.size()-1).getInventory().size(); i++)
        {
            lista.setItems(FXCollections.observableArrayList("            "+list.get(list.size()-1).getInventory().get(i).getTargyNev()));
            lista.setItems(FXCollections.observableArrayList("                        "+list.get(list.size()-1).getInventory().get(i).getTargySuly()));
        }
    }
    private void errorAlert(String title,String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
