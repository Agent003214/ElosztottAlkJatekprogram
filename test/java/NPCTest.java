import Backend.NPC;
import Backend.Targy;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NPCTest
{

    @Test
    public void NPCAddTest()
    {
        NPC teszt=new NPC("TesztNPC");
        assertEquals(teszt.getSzereploNev(),"TesztNPC");
    }

    @Test
    public void testAddToInventory()
    {
        NPC teszt=new NPC("TesztNPC");
        Targy targy=new Targy("TesztTárgy",2.4);
        teszt.addToInventory(targy);
        assertEquals(teszt.getInventory().get(0).toString(),"Tárgy neve: TesztTárgy, Tárgy súlya: 2.4");
    }
}