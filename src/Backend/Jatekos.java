package Backend;

public class Jatekos extends Szereplo
{
    private double sebesseg;
    public Jatekos(String nev)
    {
        super(nev);
        sebesseg=30;
    }

    public void addItemToJatekos(Targy item)
    {
        if (getosszsuly()+item.getSuly()<sebesseg)
        {
            addToInventory(item);
            sebessegSzamol();
        }
    }

    private void sebessegSzamol()
    {
        sebesseg=sebesseg-getosszsuly();
    }

    public double getSebesseg()
    {
        return sebesseg;
    }
}
