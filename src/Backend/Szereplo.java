package Backend;

import java.util.ArrayList;

public abstract class Szereplo
{
    protected String nev;
    protected ArrayList<Targy> inventory=new ArrayList<>();

    public Szereplo(String nev)
    {
        this.nev = nev;
    }

    public abstract void addToInventory(Targy item);

    public ArrayList<Targy> getInventory()
    {
        return inventory;
    }

    public double getosszsuly()
    {
        double seged=0;
        for (int i = 0; i < inventory.size(); i++)
        {
            seged+=inventory.get(i).getTargySuly();
        }
        return seged;
    }

    public void NevSzerintRendez()
    {
        int n = inventory.size();
        for (int i = 0; i < n-1; i++)
        {
            for (int j = 0; j < n-i-1; j++)
            {
                if (inventory.get(j).getTargyNev().compareTo(inventory.get(j+1).getTargyNev())>0)
                {
                    inventory.add(j,inventory.get(j+1));
                    inventory.remove(j+2);
                }
            }
        }
    }

    public void SulySzerintRendez()
    {
        int n = inventory.size();
        for (int i = 0; i < n-1; i++)
        {
            for (int j = 0; j < n-i-1; j++)
            {
                if (inventory.get(j).getTargySuly() > inventory.get(j+1).getTargySuly())
                {
                    inventory.add(j,inventory.get(j+1));
                    inventory.remove(j+2);
                }
            }
        }
    }

    public String getSzereploNev()
    {
        return nev;
    }

    @Override
    public String toString()
    {
        String teszt="Név: "+nev;
        return teszt;
    }
}
