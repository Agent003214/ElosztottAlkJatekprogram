package Backend;

public class Jatekos extends Szereplo
{
    private double sebesseg;
    private final double maxsebesseg=30;
    public Jatekos(String nev)
    {
        super(nev);
        sebesseg=30;
    }

    public void addItemToJatekos(Targy item)
    {
        if (getosszsuly()+item.getTargySuly()<sebesseg)
        {
            addToInventory(item);
            sebessegSzamol();
        }
        else
        {
            throw new TargyNehezException();
        }
    }

    private void sebessegSzamol()
    {
        sebesseg=maxsebesseg-getosszsuly();
    }

    public double getSebesseg()
    {
        return sebesseg;
    }
}
