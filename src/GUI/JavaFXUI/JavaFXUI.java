package GUI.JavaFXUI;

import Backend.Jatekos;
import Backend.NPC;
import Backend.Szereplo;
import Backend.Targy;
import Exceptions.NevStringException;
import Exceptions.SulyStringException;
import Exceptions.TargyNehezException;
import FileInputOutput.*;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * JavaFX osztály ami elészíti a JavaFX grafikus felületet. Szükséges Maven függőséget leírja a pom.xml.
 */
public class JavaFXUI extends Application
{

    private ArrayList<Targy> targyak = new ArrayList<>();

    private ArrayList<Jatekos> jatekosok = new ArrayList<>();

    private ArrayList<NPC> NPC_k = new ArrayList<NPC>();

    private Main m = new Main();

    /**
     * A start metódus létrehozza a JavaFX grafikus felületet, felrakja a komponenseket és végrehajta a funkciókat.
     * @param javaFXUIStage Lényegében maga a form
     */
    @Override
    public void start(Stage javaFXUIStage)
    {
        Border black = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(2)));
        javaFXUIStage.setTitle("Játékprogram");
        javaFXUIStage.getIcons().add(new Image("file:Shield.png"));
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

        GridPane mentes_panel = new GridPane();
        mentes_panel.setVgap(15);
        mentes_panel.setHgap(15);
        mentes_panel.setBorder(black);
        mentes_panel.setPadding(new Insets(15));
        mentes_panel.setPrefSize(230,100);

        Button mentes_bt = new Button("Mentés");

        CheckBox txt_cb = new CheckBox("txt");
        txt_cb.setSelected(true);
        CheckBox json_cb = new CheckBox("JSON");
        json_cb.setSelected(true);
        CheckBox xml_cb = new CheckBox("XML");
        xml_cb.setSelected(true);

        mentes_panel.add(mentes_bt,1,0);
        mentes_panel.add(txt_cb,0,1);
        mentes_panel.add(json_cb,1,1);
        mentes_panel.add(xml_cb,2,1);




        GridPane betoltes_panel = new GridPane();
        betoltes_panel.setVgap(15);
        betoltes_panel.setHgap(15);
        betoltes_panel.setBorder(black);
        betoltes_panel.setPadding(new Insets(15));
        betoltes_panel.setPrefSize(230,100);

        Button betoltes_bt = new Button("Betöltés");

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton txt_rb = new RadioButton("txt");
        txt_rb.setToggleGroup(toggleGroup);
        RadioButton json_rb = new RadioButton("JSON");
        json_rb.setToggleGroup(toggleGroup);
        json_rb.setSelected(true);
        RadioButton xml_rb = new RadioButton("XML");
        xml_rb.setToggleGroup(toggleGroup);

        betoltes_panel.add(betoltes_bt,1,0);
        betoltes_panel.add(txt_rb,0,1);
        betoltes_panel.add(json_rb,1,1);
        betoltes_panel.add(xml_rb,2,1);

        utility_panel.add(mentes_panel,0,0);
        utility_panel.add(betoltes_panel,1,0);
        gridPane.add(utility_panel,1,4);

        //Exit panel

        GridPane kilepes_panel = new GridPane();
        Button kilepes_bt = new Button("Kilépés");
        kilepes_bt.setPrefSize(130,35);
        kilepes_bt.setFont(Font.font(Font.getDefault().toString(),FontWeight.BOLD,12));
        kilepes_panel.setVgap(15);
        kilepes_panel.setHgap(15);
        kilepes_panel.add(kilepes_bt,15,4);
        gridPane.add(kilepes_panel,2,4);

        kilepes_bt.setOnAction(event -> System.exit(0));

        //Clear panel

        GridPane clear_panel = new GridPane();
        Button clear_bt = new Button("Clear");
        clear_bt.setPrefSize(130,35);
        clear_bt.setFont(Font.font(Font.getDefault().toString(),FontWeight.BOLD,12));
        clear_panel.setVgap(15);
        clear_panel.setHgap(15);
        clear_panel.add(clear_bt,0,4);
        gridPane.add(clear_panel,0,4);

        clear_bt.setOnAction(event ->
        {
            jatekosok.clear();
            NPC_k.clear();
            targyak.clear();

            jatekos_targy_ddl.getItems().clear();
            NPC_targy_ddl.getItems().clear();

            jatekos_lista.getItems().clear();
            NPC_lista.getItems().clear();
            targy_lista.getItems().clear();

            jatekos_felvetel_bt.setDisable(false);
            NPC_felvetel_bt.setDisable(false);
        });

        //Mentés

        mentes_bt.setOnAction(event ->
        {
            ArrayList<ArrayList<?>> current = new ArrayList<>();
            current.add(jatekosok);
            current.add(NPC_k);
            current.add(targyak);

            if(txt_cb.isSelected())
            {
                TXT.mentes(current);
            }
            if(json_cb.isSelected())
            {
                JSON.mentes(current);
            }
            if (xml_cb.isSelected())
            {
                XML.mentes(current);
            }
        });

        //Betöltés

        betoltes_bt.setOnAction(event ->
        {
            ArrayList<ArrayList<?>> result = null;
            try
            {
                if(txt_rb.isSelected())
                {
                    result = TXT.betolt();
                }
                if(json_rb.isSelected())
                {
                    result = JSON.betolt();
                }
                if (xml_rb.isSelected())
                {
                    result = XML.betolt();
                }
                jatekosok = (ArrayList<Jatekos>) result.get(0);
                NPC_k = (ArrayList<NPC>) result.get(1);
                targyak = (ArrayList<Targy>) result.get(2);

                if(jatekosok.size() != 0)
                {
                    targyakKiir(jatekos_lista,jatekosok);
                }

                if(NPC_k.size() != 0)
                {
                    targyakKiir(NPC_lista,NPC_k);
                }
                targy_lista.getItems().clear();
                jatekos_targy_ddl.getItems().clear();
                NPC_targy_ddl.getItems().clear();

                for (Targy targy : targyak)
                {
                    targy_lista.getItems().add(targy.toString());

                    jatekos_targy_ddl.getItems().add(targy);

                    NPC_targy_ddl.getItems().add(targy);
                }
                if(targyak.size() != 0)
                {
                    jatekos_targy_ddl.setValue(targyak.get(0));
                    NPC_targy_ddl.setValue(targyak.get(0));
                }
            }
            catch (FileNotFoundException ex)
            {
                errorAlert("Hiba","Sikertelen betöltés","A betöltés sikertelen volt mivel a mentett fájl nem található!");
            }
        });

        //Játékos felvétele

        jatekos_felvetel_bt.setOnAction(event ->
        {
            try
            {
                jatekos_lista.getItems().clear();
                jatekosok.add(m.JatekosHozzaAd(jatekos_nev_tb.getText()));
                jatekos_lista.getItems().add(jatekosok.get(jatekosok.size()-1).toString());
                jatekos_lista.getItems().add("Sebesség: "+jatekosok.get(jatekosok.size()-1).getSebesseg());
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
                NPC_lista.getItems().add(NPC_k.get(NPC_k.size()-1).toString());
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
                jatekos_targy_ddl.getItems().clear();
                NPC_targy_ddl.getItems().clear();
                for (Targy targy : targyak)
                {
                    targy_lista.getItems().add(targy.toString());
                    jatekos_targy_ddl.getItems().add(targy);
                    NPC_targy_ddl.getItems().add(targy);
                }
                jatekos_targy_ddl.setValue(targyak.get(0));
                NPC_targy_ddl.setValue(targyak.get(0));
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
                if(jatekosok.size() > 0)
                {
                    jatekosok.get(jatekosok.size()-1).NevSzerintRendez();
                    targyakKiir(jatekos_lista,jatekosok);
                }
                if(NPC_k.size() > 0)
                {
                    NPC_k.get(NPC_k.size()-1).NevSzerintRendez();
                    targyakKiir(NPC_lista,NPC_k);
                }
            }
            else
            {
                if(jatekosok.size() > 0)
                {
                    jatekosok.get(jatekosok.size()-1).SulySzerintRendez();
                    targyakKiir(jatekos_lista,jatekosok);
                }
                if(NPC_k.size() >0)
                {
                    NPC_k.get(NPC_k.size()-1).SulySzerintRendez();
                    targyakKiir(NPC_lista,NPC_k);
                }
            }
        });



        Scene scene = new Scene(gridPane);
        javaFXUIStage.setScene(scene);
        javaFXUIStage.show();
    }

    /**
     * Kiírja a megadott ListView-ba a beadott Szereplo osztályt leszármazó típus tárgyait
     *
     * @param lista A ListView amibe írni akarunk
     * @param list Az ArrayList ami tartalmazza a Szereplő tárgyait
     */
    private void targyakKiir (ListView<String> lista, ArrayList<? extends Szereplo> list)
    {
        lista.getItems().clear();
        lista.getItems().add(list.get(list.size()-1).toString());
        if (list.get(list.size()-1).getClass().equals(jatekosok.get(jatekosok.size()-1).getClass()))
        {
            DecimalFormat df=new DecimalFormat("#.##");
            lista.getItems().add("Sebesség: "+df.format(jatekosok.get(jatekosok.size()-1).getSebesseg()));
        }
        lista.getItems().add("Tárgyak:");

        for (int i = 0; i < list.get(list.size()-1).getInventory().size(); i++)
        {
            lista.getItems().add("            "+list.get(list.size()-1).getInventory().get(i).getTargyNev());
            lista.getItems().add("                        "+list.get(list.size()-1).getInventory().get(i).getTargySuly());
        }
    }

    /**
     * Hibaüzenetet dobó ablak
     * @param title Az ablak címe
     * @param header Az ablak fejléce
     * @param content Az ablak tartalma
     */
    private void errorAlert(String title,String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
