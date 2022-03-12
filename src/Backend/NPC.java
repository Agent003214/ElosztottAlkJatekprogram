package Backend;

public class NPC extends Szereplo
{
    public NPC(String nev)
    {
        super(nev);
    }

    @Override
    public void addToInventory(Targy item)
    {
        inventory.add(item);
    }

}
