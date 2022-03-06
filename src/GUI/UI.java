package GUI;

import Backend.Targy;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame
{
    public UI()
    {
        setTitle("Játékprogram");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        setSize(new Dimension(700,500));
        GridBagLayout layout = new  GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        GridBagLayout jatekos_panel_layout =new GridBagLayout();

        GridBagLayout NPC_panel_layout =new GridBagLayout();

        GridBagLayout targy_panel_layout =new GridBagLayout();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = constraints.weighty = 1f;
        constraints.insets = new Insets(5,5,5,5);
        setLayout(layout);

        // Játékos panel és komponensei

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


        JPanel targy_box = new JPanel();
        targy_box.setBorder(BorderFactory.createTitledBorder("Tárgy"));
        addobjects(targy_box,this,layout,constraints,2,0,1,1);

    }
    private void addobjects(Component component, Container container, GridBagLayout layout, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheigth)
    {

        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheigth;

        layout.setConstraints(component, gbc);
        container.add(component);
    }
}
