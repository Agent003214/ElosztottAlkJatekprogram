import Backend.Jatekos;
import Backend.NPC;
import Backend.Targy;
import Exceptions.NevStringException;
import Exceptions.SulyStringException;
import Main.Main;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class MainTest
{

    @Test
    public void testJatekosHozzaAd()
    {
        Main test=new Main();
        Jatekos[] v=new Jatekos[1];
        v[0]=test.JatekosHozzaAd("CUPS");
        assertEquals(v[0].getSzereploNev(),"CUPS");
    }

    @Test
    public void testNPCHozzaAd()
    {
        try
        {
            Main test=new Main();
            NPC[] v=new NPC[1];
            v[0]=test.NPCHozzaAd("");
            Assert.fail();
        }
        catch (NevStringException e)
        {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void testTargyletrehoz()
    {
        try
        {
            Main test=new Main();
            Targy[] v=new Targy[1];
            v[0]=test.Targyletrehoz("Tárgynév","-5");
            Assert.fail();
        }
        catch (SulyStringException e)
        {
            Assert.assertTrue(true);
        }
    }
}