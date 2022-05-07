package Main;

import Exceptions.NevStringException;
import Exceptions.SulyStringException;
import GUI.JavaFXUI;
import GUI.UI;
import Backend.*;
import javafx.application.Application;

import javax.swing.*;

public class Main
{
    /**
     * Elindítja a programot, létrehoz egy UI grafikus felület példányt és lekéri a futtató operációs rendszer által használt grafikus témát
     *
     * @param args Nem használt
     */
    public static void main(String[] args)
    {

        Application.launch(JavaFXUI.class);
     /*   try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ignored)
        {}
        SwingUtilities.invokeLater(() -> new UI().setVisible(true)); */
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
