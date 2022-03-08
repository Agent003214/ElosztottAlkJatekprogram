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
        return "Tárgy neve: "+nev+", Tárgy súlya: "+suly;
    }
}
