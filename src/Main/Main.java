package Main;

import GUI.UI;
import Backend.*;

import javax.swing.*;

public class Main
{

    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        SwingUtilities.invokeLater(() -> new UI().setVisible(true));
    }

    public Jatekos JatekosHozzaAd(String nev)
    {
        checkNevString(nev);
        return new Jatekos(nev);
    }

    public NPC NPCHozzaAd(String nev)
    {
        checkNevString(nev);
        return new NPC(nev);
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
