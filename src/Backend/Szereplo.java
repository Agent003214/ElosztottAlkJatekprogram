package Backend;

import java.util.ArrayList;

public abstract class Szereplo
{
    private String nev;
    private ArrayList<Targy> inventory=new ArrayList<>();

    public Szereplo(String nev)
    {
        this.nev = nev;
    }

    public void addToInventory(Targy item)
    {
        inventory.add(item);
    }

    public ArrayList<Targy> getInventory()
    {
        return inventory;
    }

    public double getosszsuly()
    {
        double seged=0;
        int meret= inventory.size();
        for (int i = 0; i < meret; i++)
        {
            seged+=inventory.get(i).getSuly();
        }
        return seged;
    }

    @Override
    public String toString()
    {
        String teszt="Név: "+nev+", Eszközlista: \n"+inventory;
        return teszt;
    }
}
