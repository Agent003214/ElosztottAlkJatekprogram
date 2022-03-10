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

    public String JatekosHozzaAd(String nev)
    {
        checkNevString(nev);
        return new Jatekos(nev).toString();
    }

    public String NPCHozzaAd(String nev)
    {
        checkNevString(nev);
        return new NPC(nev).toString();
    }

    public Targy Targyletrehoz(String nev, String suly)
    {
        checkNevString(nev);
        return new Targy(nev,checkSulyString(suly));
    }

    private boolean checkNevString(String nev)
    {
        if (nev.length()>0 && nev!=null)
        {
            return true;
        }
        else
        {
            throw new NevStringException();
        }
    }

    private double checkSulyString(String suly)
    {
        try
        {
            if (Double.parseDouble(suly)>0)
            {
                return Double.parseDouble(suly);
            }
        }
        catch (NumberFormatException e)
        {
            throw new SulyStringException();
        }
        throw new SulyStringException();
    }
}
