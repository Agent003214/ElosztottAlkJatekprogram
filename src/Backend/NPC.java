package Backend;

public class NPC extends Szereplo
{
    public NPC(String nev)
    {
        super(nev);
    }

    public void addItemToNPC(Targy item)
    {
        addToInventory(item);
    }
}
