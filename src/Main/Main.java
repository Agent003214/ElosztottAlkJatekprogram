package Main;

import GUI.UI;
import Backend.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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

    //Megnézi, hogy nincs-e baj a név TexBox-ból bekért adattal
    //Ha hiba van benne, exception-t bod
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

    //Megnézi, hogy nincs-e baj a súly TexBox-ból bekért adattal
    //Ha hiba van benne, exception-t bod
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

    //Név szerint rendezi a tárgyakat tároló listát
    public ArrayList<Targy> NevRendez(ArrayList<Targy> targy)
    {
        Collections.sort(targy,Targy.NevComparator);
        return targy;
    }
}
