package Backend;

public class Targy
{
    private String nev;
    private double suly;

    public Targy(String nev, double suly)
    {
        this.nev = nev;
        this.suly = suly;
    }

    public String getTargyNev()
    {
        return nev;
    }

    public double getTargySuly()
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
