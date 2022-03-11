package Backend;

import java.util.Comparator;

public class Targy
{
    private String nev;
    private double suly;

    public Targy(String nev, double suly)
    {
        this.nev = nev;
        this.suly = suly;
    }

    public static Comparator<Targy> NevComparator=new Comparator<Targy>()
    {
        @Override
        public int compare(Targy item1, Targy item2)
        {
            return item1.getNev().compareTo(item2.getNev());
        }
    };

    public String getNev()
    {
        return nev;
    }

    public double getSuly()
    {
        return suly;
    }

    @Override
    public String toString()
    {
        String teszt="Tárgy neve: "+nev+",\n Tárgy súlya: "+suly+"\n";
        return teszt;
    }
}
