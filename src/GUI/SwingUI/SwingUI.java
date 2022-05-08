package GUI.SwingUI;

import Backend.*;
import Exceptions.NevStringException;
import Exceptions.SulyStringException;
import Exceptions.TargyNehezException;
import FileInputOutput.FileInputOutput;
import Main.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * SwingUI osztály ami elkészíti a Swing grafikus felületet
 */
public class SwingUI extends JFrame
{
    private ArrayList<Targy> targyak = new ArrayList<>();
    private ArrayList<Jatekos> jatekosok = new ArrayList<>();

    private ArrayList<NPC> NPC_k = new ArrayList<NPC>();

    private Main m = new Main();

    /**
     * A konstruktor létrehozza a grafikus felületetet, felrakja a komponenseket
     */
    public SwingUI()
    {
        setTitle("Játékprogram");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("Sword.jpg"));


        GridBagLayout layout = new  GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = constraints.weighty = 1f;
        constraints.insets = new Insets(5,5,5,5);

        setLayout(layout);

        // Játékos panel és komponensei


        GridBagLayout jatekos_panel_layout =new GridBagLayout();
        JPanel jatekos_box = new JPanel(jatekos_panel_layout);
        jatekos_box.setBorder(BorderFactory.createTitledBorder("Játékos"));
        addobjects(jatekos_box,this,layout,constraints,0,0,1,1);

        JLabel jatekos_nev_label = new JLabel("Név:");
        addobjects(jatekos_nev_label,jatekos_box,jatekos_panel_layout,constraints,0,0,1,1);

        JTextField jatekos_nev_tb = new JTextField();
        jatekos_nev_tb.setPreferredSize(new Dimension(140,25));
        addobjects(jatekos_nev_tb,jatekos_box,jatekos_panel_layout,constraints,1,0,1,1);

        JButton jatekos_felvesz_button = new JButton("Felvétel");
        jatekos_felvesz_button.setPreferredSize(new Dimension(70,35));
        addobjects(jatekos_felvesz_button,jatekos_box,jatekos_panel_layout,constraints,1,1,1,1);

        JLabel jatekos_targy_felvetel_label = new JLabel("Tárgy felvétele:");
        addobjects(jatekos_targy_felvetel_label,jatekos_box,jatekos_panel_layout,constraints,0,2,1,1);

        JComboBox<Targy> jatekos_targy_ddl = new JComboBox<>();
        addobjects(jatekos_targy_ddl,jatekos_box,jatekos_panel_layout,constraints,1,2,1,1);

        JButton targy_hozzadasa_jatekoshoz_button = new JButton("Felvétel");
        targy_hozzadasa_jatekoshoz_button.setPreferredSize(new Dimension(70,35));
        addobjects(targy_hozzadasa_jatekoshoz_button,jatekos_box,jatekos_panel_layout,constraints,1,3,1,1);

        // NPC panel és komponensei


        GridBagLayout NPC_panel_layout =new GridBagLayout();
        JPanel NPC_box = new JPanel(NPC_panel_layout);
        NPC_box.setBorder(BorderFactory.createTitledBorder("NPC"));
        addobjects(NPC_box,this,layout,constraints,1,0,1,1);

        JLabel NPC_nev_label = new JLabel("Név:");
        addobjects(NPC_nev_label,NPC_box,NPC_panel_layout,constraints,0,0,1,1);

        JTextField NPC_nev_tb = new JTextField();
        NPC_nev_tb.setPreferredSize(new Dimension(140,25));
        addobjects(NPC_nev_tb,NPC_box,NPC_panel_layout,constraints,1,0,1,1);

        JButton NPC_felvesz_button = new JButton("Felvétel");
        NPC_felvesz_button.setPreferredSize(new Dimension(70,35));
        addobjects(NPC_felvesz_button,NPC_box,NPC_panel_layout,constraints,1,1,1,1);

        JLabel NPC_targy_felvetel_label = new JLabel("Tárgy felvétele:");
        addobjects(NPC_targy_felvetel_label,NPC_box,NPC_panel_layout,constraints,0,2,1,1);

        JComboBox<Targy> NPC_targy_ddl = new JComboBox<>();
        addobjects(NPC_targy_ddl,NPC_box,NPC_panel_layout,constraints,1,2,1,1);

        JButton targy_hozzadasa_NPC_hez_button = new JButton("Felvétel");
        targy_hozzadasa_NPC_hez_button.setPreferredSize(new Dimension(70,35));
        addobjects(targy_hozzadasa_NPC_hez_button,NPC_box,NPC_panel_layout,constraints,1,3,1,1);

        //Tárgy panel és komponensei

        GridBagLayout targy_panel_layout =new GridBagLayout();
        JPanel targy_box = new JPanel(targy_panel_layout);
        targy_box.setBorder(BorderFactory.createTitledBorder("Tárgy"));
        addobjects(targy_box,this,layout,constraints,2,0,1,1);

        JLabel targy_nev_label = new JLabel("Név:");
        addobjects(targy_nev_label,targy_box,targy_panel_layout,constraints,0,0,1,1);

        JTextField targy_nev_tb = new JTextField();
        targy_nev_tb.setPreferredSize(new Dimension(140,25));
        addobjects(targy_nev_tb,targy_box,targy_panel_layout,constraints,1,0,1,1);

        JLabel targy_suly_label = new JLabel("Súly:");
        addobjects(targy_suly_label,targy_box,targy_panel_layout,constraints,0,1,1,1);

        JTextField targy_suly_tb = new JTextField();
        targy_suly_tb.setPreferredSize(new Dimension(140,25));
        addobjects(targy_suly_tb,targy_box,targy_panel_layout,constraints,1,1,1,1);

        JButton targy_felvetel_button = new JButton("Felvétel");
        targy_felvetel_button.setPreferredSize(new Dimension(70,35));
        addobjects(targy_felvetel_button,targy_box,targy_panel_layout,constraints,1,2,1,1);


        //Rendezés panel és komponensei

        String [] rendezesi_opciok = new String [] {"Név szerint","Súly szerint"};
        JPanel rendezes_box = new JPanel();
        addobjects(rendezes_box,this,layout,constraints,0,1,3,1);


        JLabel rendezes_label = new JLabel("Rendezés:");
        rendezes_box.add(rendezes_label);

        JComboBox<String> Jatekos_rendez_ddl = new JComboBox<>(rendezesi_opciok);
        rendezes_box.add(Jatekos_rendez_ddl);


        //ListBox-ok

        DefaultListModel<String> jatekos_lista = new DefaultListModel<>();
        JList<String> jatekos_LB = new JList<>(jatekos_lista);
        JScrollPane jatekos_LB_scroll = new JScrollPane(jatekos_LB);
        addobjects(jatekos_LB_scroll,this,layout,constraints,0,2,1,1);

        DefaultListModel<String> NPC_lista = new DefaultListModel<>();
        JList<String> NPC_LB = new JList<>(NPC_lista);
        JScrollPane NPC_LB_scroll = new JScrollPane(NPC_LB);
        addobjects(NPC_LB_scroll,this,layout,constraints,1,2,1,1);

        DefaultListModel<String> targy_lista = new DefaultListModel<>();
        JList<String> targy_LB = new JList<>(targy_lista);
        JScrollPane targy_LB_scroll = new JScrollPane(targy_LB);
        addobjects(targy_LB_scroll,this,layout,constraints,2,2,1,1);

        //Utility gombok

        JPanel utility_box = new JPanel();
        addobjects(utility_box,this,layout,constraints,0,3,3,1);

        JButton mentes_button = new JButton("Mentés");
        mentes_button.setPreferredSize(new Dimension(70,35));
        utility_box.add(mentes_button);
        mentes_button.addActionListener(e ->
        {
            ArrayList<ArrayList> current = new ArrayList<>();
            current.add(jatekosok);
            current.add(NPC_k);
            current.add(targyak);
            FileInputOutput.mentes(current);
        });

        JButton betolt_button = new JButton("Betöltés");
        betolt_button.setPreferredSize(new Dimension(90,35));
        utility_box.add(betolt_button);
        betolt_button.addActionListener(e ->
        {
            ArrayList<ArrayList> result;
            try
            {
                result = FileInputOutput.betolt();
                jatekosok = result.get(0);
                NPC_k = result.get(1);
                targyak = result.get(2);

                if(jatekosok.size() != 0)
                {
                    targyakKiir(jatekos_lista,jatekosok);
                }

                if(NPC_k.size() != 0)
                {
                    targyakKiir(NPC_lista,NPC_k);
                }
                targy_lista.clear();
                jatekos_targy_ddl.removeAllItems();
                NPC_targy_ddl.removeAllItems();
                for (Targy targy : targyak)
                {
                    targy_lista.addElement(targy.toString());   //Az hogy mi lesz kiírva a toString-től függ!

                    jatekos_targy_ddl.addItem(targy);           //Dropdow a játékosokhoz

                    NPC_targy_ddl.addItem(targy);               //Dropdow az NPC-khez
                }
            }
            catch (FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(null,"A böltés sikertelen volt, a mentett fájl nem található!","Hiba!",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton kilep_button = new JButton("Kilépés");
        kilep_button.setPreferredSize(new Dimension(70,35));
        utility_box.add(kilep_button);
        kilep_button.addActionListener(event -> System.exit(0));


        //Játékos felvétele
        jatekos_felvesz_button.addActionListener(e ->
        {
            try
            {
                jatekos_lista.clear();
                jatekosok.add(m.JatekosHozzaAd(jatekos_nev_tb.getText()));
                jatekos_lista.addElement(jatekosok.get(jatekosok.size()-1).toString());
                jatekos_felvesz_button.setEnabled(false);
            }
            catch (NevStringException exp)
            {
                JOptionPane.showMessageDialog(null,"A név nem megfelelően lett megadva!","Hiba!",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        //Tárgy felvétele a játékoshoz

        targy_hozzadasa_jatekoshoz_button.addActionListener(e ->
        {
            try
            {
                jatekosok.get(jatekosok.size()-1).addToInventory((Targy) Objects.requireNonNull(jatekos_targy_ddl.getSelectedItem()));
                targyakKiir(jatekos_lista,jatekosok);
            }
            catch (TargyNehezException exp)
            {
                JOptionPane.showMessageDialog(null,"A tárgy túl nehéz, nem tudjuk elvinni!","Nincs elég mozgási sebesség!",JOptionPane.INFORMATION_MESSAGE);
            }
            catch (ArrayIndexOutOfBoundsException exp)
            {
                JOptionPane.showMessageDialog(null,"Először egy Játékos felvétele szükséges!","Hiba!",JOptionPane.INFORMATION_MESSAGE);

            }
        });


        //NPC felvétele
        NPC_felvesz_button.addActionListener(e ->
        {
            try
            {
                NPC_lista.clear();
                NPC_k.add(m.NPCHozzaAd(NPC_nev_tb.getText()));
                NPC_lista.addElement(NPC_k.get(NPC_k.size()-1).toString());
                NPC_felvesz_button.setEnabled(false);
            }
            catch (NevStringException exp)
            {
                JOptionPane.showMessageDialog(null,"A név nem megfelelően lett megadva!","Hiba!",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //Tárgy felvétele az NPC-hez
        targy_hozzadasa_NPC_hez_button.addActionListener(e ->
        {
            try
            {
                NPC_k.get(NPC_k.size()-1).addToInventory((Targy) Objects.requireNonNull(NPC_targy_ddl.getSelectedItem()));
                targyakKiir(NPC_lista,NPC_k);
            }
            catch (TargyNehezException exp)
            {
                JOptionPane.showMessageDialog(null,"A tárgy túl nehéz, nem tudjuk elvinni!","Nincs elég mozgási sebesség!",JOptionPane.INFORMATION_MESSAGE);
            }
            catch (ArrayIndexOutOfBoundsException exp)
            {
                JOptionPane.showMessageDialog(null,"Először egy NPC felvétele szükséges!","Hiba!",JOptionPane.INFORMATION_MESSAGE);

            }
        });

        //Tárgy felvétele
        targy_felvetel_button.addActionListener(e ->
        {
            //Input teszt szükséges

            try
            {
                targyak.add(m.Targyletrehoz(targy_nev_tb.getText(),targy_suly_tb.getText()));
                targy_lista.clear();
                jatekos_targy_ddl.removeAllItems();
                NPC_targy_ddl.removeAllItems();
                for (Targy targy : targyak)
                {
                    targy_lista.addElement(targy.toString());   //Az hogy mi lesz kiírva a toString-től függ!

                    jatekos_targy_ddl.addItem(targy);           //Dropdow a játékosokhoz

                    NPC_targy_ddl.addItem(targy);               //Dropdow az NPC-khez
                }
            }
            catch (SulyStringException exp)
            {
                JOptionPane.showMessageDialog(null, "A súly nem megfelelően lett megadva!","Hiba!",JOptionPane.INFORMATION_MESSAGE);
            }
            catch (NevStringException exp)
            {
                JOptionPane.showMessageDialog(null,"A név nem megfelelően lett megadva!","Hiba!",JOptionPane.INFORMATION_MESSAGE);
            }
            pack();
        });

        Jatekos_rendez_ddl.addActionListener(e ->
        {
            if(Jatekos_rendez_ddl.getSelectedItem() == "Név szerint")
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


        pack();
        setLocation(SwingUI.calcualteTheMiddleOfTheScreen(getWidth(),getHeight()));
    }

    /**
     * Hozzáad egy komponenst a contaniner-hez a GridBagLayout használatával a megadott paraméterek alapján
     *
     * @param component A komponens amit fel akarunk rakni
     * @param container A container amibe bele akarjuk rakni a komponenst
     * @param layout    A GridBagLayout amit használni akarunk
     * @param gbc       A GridBagConstraints amit használunk a layout-hoz
     * @param gridx     Az x koordináta a gbc-hez
     * @param gridy     Az y koordináta a gbc-hez
     * @param gridwidth A szélessége a beadott komponensnek a többhez viszonyítva
     * @param gridheigth A magassága a beadaott komponensnek a többihez viszonyítva
     * */
    private void addobjects(Component component, Container container, GridBagLayout layout, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheigth)
    {

        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheigth;

        layout.setConstraints(component, gbc);
        container.add(component);
    }

    /**
     * Hozzáadja a beadott DefaultListModel-hez a beadott ArrayList Játékos vagy NPC tárgyait
     *
     * @param lista A DefaultListModel ami a JList mögött fut, amibe ki akarunk írni
     * @param list  Az ArrayList ami tartalmazza a Játékost vagy NPC-t aki tárgyait ki akarjuk írni
     */
    private void targyakKiir (DefaultListModel<String> lista, ArrayList<? extends Szereplo> list)
    {
        lista.clear();
        lista.addElement(list.get(list.size()-1).toString());
        lista.addElement("Tárgyak:");

        for (int i = 0; i < list.get(list.size()-1).getInventory().size(); i++)
        {
            lista.addElement("            "+list.get(list.size()-1).getInventory().get(i).getTargyNev());
            lista.addElement("                        "+list.get(list.size()-1).getInventory().get(i).getTargySuly());
        }
    }

    /**
     * Kiszámolja hogy hol van a jelenlegi szélesség és magasság szerint az ideális pont ahol az ablak a képernyő közepén fog megjelenni
     * @param width A Jframe szélessége
     * @param height A Jframe magassága
     * @return Egy pontot ami a képernyő közepére fogja rakni az ablakot
     */
    public static Point calcualteTheMiddleOfTheScreen(int width, int height)
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width/2 - width/2);
        int y = (dimension.height/2 - height/2);
        return new Point(x,y);
    }
}