package Main;

import Exceptions.NevStringException;
import Exceptions.SulyStringException;
import GUI.GUIChooser;
import Backend.*;

import javax.swing.*;

public class Main
{
    /**
     * Elindítja a programot, készít egy kis keződ ablakoz ahol a felhasználó választhat hogy milyen grafikus felület akar a program további futása alatt használni
     *
     */
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ignored)
        {}
        SwingUtilities.invokeLater(() -> new GUIChooser().setVisible(true));
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

    /**
     * Megnézi a bekért String-et, hogy nem üres.
     * @param nev A leellenőrizendő String.
     * @return Igaz, ha megfelelő a String. Hamis, ha üres.
     * @throws NevStringException
     */
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

    /**
     * Leellenőrzni, hogy String-ből át lehet konvertálni Double típusúra.
     * @param suly Ellenőrizendő súly String-ként.
     * @return Az átkonvertált súly.
     * @throws SulyStringException Ha a konverzió nem lehetséges.
     */
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
