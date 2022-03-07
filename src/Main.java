import GUI.UI;
import Backend.*;

import javax.swing.*;
import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        Jatekos teszt = new Jatekos("Jani");
        Targy item=new Targy("Kurbli",5.1);
        Targy item2=new Targy("Kormany",3);
        /*teszt.addItem(item);
        teszt.addItem(item2);*/
        teszt.addItemToJatekos(item2);
        teszt.getosszsuly();
        System.out.println(teszt.getInventory());
        //System.out.println(teszt.toString());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        SwingUtilities.invokeLater(() -> new UI().setVisible(true));
    }

    public void JatekosHozzaAd(String nev)
    {
        Jatekos jatekos=new Jatekos(nev);
    }

    public void NPCHozzaAd(String nev)
    {
        NPC npc=new NPC(nev);
    }

    public void Targyletrehoz(String nev, double suly)
    {
        new Targy(nev,suly);
    }

}
