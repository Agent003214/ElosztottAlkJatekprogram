import java.util.ArrayList;

public abstract class Szereplo
{
    private float suly;
    private float sebesseg;
    private ArrayList<Targy> inventory=new ArrayList<>();

    public void addItem(Targy item)
    {
        inventory.add(item);
    }

    @Override
    public String toString() {
        return "Szereplo{" +
                "inventory=" + inventory +
                '}';
    }
}
