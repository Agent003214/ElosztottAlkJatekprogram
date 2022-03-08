import GUI.UI;
import Backend.*;

import javax.swing.*;
import java.sql.Struct;
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

    ArrayList<Jatekos> jatekos=new ArrayList<>();
    public void JatekosHozzaAd(String nev)
    {
        if (checkNevString(nev))
        {
            jatekos.add(new Jatekos(nev));
        }

    }

    ArrayList<NPC> npc=new ArrayList<>();
    public void NPCHozzaAd(String nev)
    {
        if (checkNevString(nev))
        {
            npc.add(new NPC(nev));
        }

    }

    ArrayList<Targy> Targy=new ArrayList<>();
    public void Targyletrehoz(String nev, String suly)
    {
        if (checkNevString(nev)&&checkSulyString(suly))
        {
            Targy.add(new Targy(nev,Double.parseDouble(suly)));
        }
    }

    private boolean checkNevString(String nev)
    {
        if (nev.length()>0 && nev!=null)
        {
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                    "Kérem adjon meg egy nevet!",
                    "Hiba!",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    private boolean checkSulyString(String suly)
    {
        try
        {
            if (Double.parseDouble(suly)>0)
            {
                return true;
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,
                    "Kérem számot adjon meg!",
                    "Hiba!",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,
                    "Kérem adjon meg egy számot!",
                    "Hiba!",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return false;
    }
}
