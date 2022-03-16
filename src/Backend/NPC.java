package Backend;
/**
 * Szereplő osztály gyereke.
 * Ekészít egy NPC-t.
 */
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
