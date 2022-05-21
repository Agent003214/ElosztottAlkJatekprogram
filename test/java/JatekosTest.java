import Backend.Jatekos;
import Backend.Targy;
import Exceptions.TargyNehezException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JatekosTest
{
    @Test
    public void jatekosNevTest()
    {
        Jatekos jatekos = new Jatekos("Tibor");
        Assert.assertEquals(jatekos.toString(),"Név: Tibor");
    }

    @Test
    public void jatekosSebessegTest()
    {
        Jatekos jatekos = new Jatekos("Tibor");
        Assert.assertEquals(jatekos.getSebesseg(),30);
    }

    @Test
    public void jatekosInventoryTestWithOneItem()
    {
        Targy item = new Targy("Név",5);
        Jatekos jatekos = new Jatekos("Tibor");
        jatekos.addToInventory(item);
        Assert.assertEquals(jatekos.getInventory().get(0),item);
        Assert.assertEquals(jatekos.getInventory().get(0).getTargyNev(),item.getTargyNev());
        Assert.assertEquals(jatekos.getInventory().get(0).getTargySuly(),item.getTargySuly());
    }
    @Test
    public void jatekosExceptionTest()
    {
        try
        {
            Targy item = new Targy("Név",31);
            Jatekos jatekos = new Jatekos("Tibor");
            jatekos.addToInventory(item);
            Assert.fail();
        }
        catch (TargyNehezException exception)
        {
            Assert.assertTrue(true);
        }
    }
}