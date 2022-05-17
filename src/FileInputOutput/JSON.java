package FileInputOutput;

import Backend.Jatekos;
import Backend.NPC;
import Backend.Targy;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

import static FileInputOutput.TXT.ItemKeres;

/**
 * Lekezeli a fájlba mentést és fájlból a betöltést egy JSON fájlba.
 */
public class JSON
{
    private static ArrayList<Jatekos> player;
    private static ArrayList<Targy> items;
    private static ArrayList<NPC> nonplayer;

    /**
     * Lementi a program jelenlegi állapotát egy Json fájlba
     * @param mentendok Egy ArrayList ami magába foglalja azokat az ArrayList-eket amik a Játékosokat, az NPC-ket és a Tárgyakat tartalmazzák.
     * @throws IOException
     */
    public static void mentes(ArrayList<ArrayList<?>> mentendok)
    {
        JSONObject obj=new JSONObject();
        player= (ArrayList<Jatekos>) mentendok.get(0);
        nonplayer= (ArrayList<NPC>) mentendok.get(1);
        items= (ArrayList<Targy>) mentendok.get(2);

        //Items
        if (items.size()>0)
        {
            JSONObject itemJson=new JSONObject();
            for (int i = 0; i < items.size(); i++)
            {
                itemJson.put("Item"+i, itemJsonAdd(items.get(i).getTargyNev(),items.get(i).getTargySuly()));
            }
            obj.put("Items",itemJson);
        }

        //Players
        if (player.size()>0)
        {
            JSONObject playerJson=new JSONObject();
            playerJson.put("Name",player.get(0).getSzereploNev());
            JSONObject playerInventory=new JSONObject();
            for (int i = 0; i < player.get(0).getInventory().size(); i++)
            {
                playerInventory.put("inventoryItem"+i,itemJsonAdd(player.get(0).getInventory().get(i).getTargyNev(), player.get(0).getInventory().get(i).getTargySuly()));
            }
            playerJson.put("Inventory",playerInventory);
            obj.put("Player",playerJson);
        }

        //NPCs
        if (nonplayer.size()>0)
        {
            JSONObject npcJson=new JSONObject();
            npcJson.put("Name",nonplayer.get(0).getSzereploNev());
            JSONObject npcInventory=new JSONObject();
            for (int i = 0; i < nonplayer.get(0).getInventory().size(); i++)
            {
                npcInventory.put("inventoryItem"+i,itemJsonAdd(nonplayer.get(0).getInventory().get(i).getTargyNev(), nonplayer.get(0).getInventory().get(i).getTargySuly()));
            }
            npcJson.put("Inventory",npcInventory);
            obj.put("NPC",npcJson);
        }

        try(FileWriter file=new FileWriter("mentes.json"))
        {
            file.write(obj.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * JSONObject típusú objektumot hoz létre a beadott adatokból.
     * @param itemName
     * @param itemWeight
     * @return JSONObject
     */
    private static JSONObject itemJsonAdd(String itemName, Double itemWeight)
    {
        JSONObject item=new JSONObject();
        item.put("ItemName",itemName);
        item.put("ItemWeight",itemWeight);
        return item;
    }


    /**
     * Beolvassa és betölti egy Json fájlból az adatokat.
     * @return Arraylist, amiben benne van a játékosok, NPC-k és a tárgyak listája.
     * @throws FileNotFoundException Ha nem található a beolvasandó fájl
     */
    public static ArrayList<ArrayList<?>> betolt() throws FileNotFoundException
    {
        player=new ArrayList<>();
        items=new ArrayList<>();
        nonplayer=new ArrayList<>();
        JSONParser parser=new JSONParser();
        try(Reader reader=new FileReader("mentes.json"))
        {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            //Item-ek
            JSONObject readItem1= (JSONObject) jsonObject.get("Items");
            if(readItem1 != null)
            {
                for (int i = 0; i < readItem1.size(); i++)
                {
                    JSONObject readItem2 = (JSONObject) readItem1.get("Item"+i);
                    String name=(String) readItem2.get("ItemName");
                    Double weight=(Double) readItem2.get("ItemWeight");
                    items.add(new Targy(name,weight));
                }
            }

            //Player-ek
            JSONObject readPlayer1 = (JSONObject) jsonObject.get("Player");
            if (readPlayer1 != null)
            {
                player.add(new Jatekos((String) readPlayer1.get("Name")));
                JSONObject readPlayer2 = (JSONObject) readPlayer1.get("Inventory");
                for (int i = 0; i < readPlayer2.size(); i++)
                {
                    JSONObject readPlayer3 = (JSONObject) readPlayer2.get("inventoryItem"+i);
                    String name=(String) readPlayer3.get("ItemName");
                    Double weight=(Double) readPlayer3.get("ItemWeight");
                    player.get(0).addToInventory(ItemKeres(name,weight,items));
                }
            }

            //NPC-k
            JSONObject readNPC1 = (JSONObject) jsonObject.get("NPC");
            if (readNPC1 != null)
            {
                nonplayer.add(new NPC((String) readNPC1.get("Name")));
                JSONObject readNPC2 = (JSONObject) readNPC1.get("Inventory");
                for (int i = 0; i < readNPC2.size(); i++)
                {
                    JSONObject readNPC3 = (JSONObject) readNPC2.get("inventoryItem"+i);
                    String name=(String) readNPC3.get("ItemName");
                    Double weight=(Double) readNPC3.get("ItemWeight");
                    nonplayer.get(0).addToInventory(ItemKeres(name,weight,items));
                }
            }

        }
        catch (FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }
        catch (IOException | ParseException ignored)
        {

        }

        ArrayList<ArrayList<?>> result=new ArrayList<>();
        result.add(player);
        result.add(nonplayer);
        result.add(items);
        return result;
    }
}
