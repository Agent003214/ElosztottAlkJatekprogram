package GUI;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame
{
    public UI()
    {
        setTitle("Játékprogram");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new GridBagLayout());

        JPanel jatekos_box = new JPanel();
        jatekos_box.setBorder(BorderFactory.createTitledBorder("Játékos"));
        add(jatekos_box,GBCmaker(0,0));

        JPanel NPC_box = new JPanel();
        NPC_box.setBorder(BorderFactory.createTitledBorder("NPC"));
        add(NPC_box,GBCmaker(1,0));


        JPanel targy_box = new JPanel();
        targy_box.setBorder(BorderFactory.createTitledBorder("Tárgy"));
        add(targy_box,GBCmaker(2,0));
    }
    private GridBagConstraints GBCmaker(int x, int y)
    {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = x;
        constraint.gridy = y;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.weightx = constraint.weighty = 1f;
        return constraint;
    }
}
