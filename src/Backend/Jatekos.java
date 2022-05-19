package Backend;

import Exceptions.TargyNehezException;

import java.util.Objects;

/**
 * Szereplő osztály gyereke.
 * Ekészít egy Játékost.
 */
public class Jatekos extends Szereplo
{
    private double sebesseg;
    private final double maxsebesseg=30;
    public Jatekos(String nev)
    {
        super(nev);
        sebesseg=30;
    }

    /**
     * Hozzáadja a Játékoshoz a beadott tárgyat.
     * Megnézi, hogy a feltételnek megfelelel, és csak utána adja hozzá a Játékoshoz.
     * @param item
     * @throws TargyNehezException
     */
    @Override
    public void addToInventory(Targy item)
    {
        if (getosszsuly()+item.getTargySuly()<=maxsebesseg)
        {
            inventory.add(Objects.requireNonNull(item));
            sebessegSzamol();
        }
        else
        {
            throw new TargyNehezException();
        }
    }

    /**
     * Kiszámolja és beállítja a játékos mozgási sebességét.
     */
    private void sebessegSzamol()
    {
        sebesseg=maxsebesseg-getosszsuly();
    }

    public double getSebesseg()
    {
        return sebesseg;
    }

    @Override
    public String toString()
    {
        return "Név: "+getSzereploNev();
    }
}
