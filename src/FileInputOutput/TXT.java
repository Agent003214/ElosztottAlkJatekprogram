package FileInputOutput;
import Backend.Jatekos;
import Backend.NPC;
import Backend.Targy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

/**
 * Lekezeli a fájlba mentést és fájlból a betöltést egy TXT fájlba.
 */
public class TXT
{
    static File file=new File("mentes.txt");
    private static ArrayList<Jatekos> player;
    private static ArrayList<Targy> items;
    private static ArrayList<NPC> nonplayer;

    /**
     *Elkészít egy szöveges fájlt és lementi a program jelenlegi állapotát egy TXT fájlba.
     * @param mentendok Egy ArrayList ami magába foglalja azokat az ArrayList-eket amik a Játékosokat, az NPC-ket és a Tárgyakat tartalmazzák.
     * @throws IOException Arraylist, amiben benne van a játékosok, NPC-k és a tárgyak listája.
     */
    public static void mentes(ArrayList<ArrayList<?>> mentendok)
    {
        byte[] data;
        player= (ArrayList<Jatekos>) mentendok.get(0);
        nonplayer= (ArrayList<NPC>) mentendok.get(1);
        items= (ArrayList<Targy>) mentendok.get(2);

        try (FileOutputStream filebair = new FileOutputStream(file))
        {
            data="Tárgyak\n".getBytes(StandardCharsets.UTF_8);//Tárgyak beleírása a fájlba
            String seged;
            filebair.write(data);
            if (items.size()>0)
            {
                for (int i = 0; i < items.size(); i++)//A tárgyak beleíársa a fájlba
                {
                    seged=items.get(i).getTargyNev()+";"+items.get(i).getTargySuly()+"\n";
                    data=seged.getBytes(StandardCharsets.UTF_8);
                    filebair.write(data);
                }
            }
            filebair.write("*\nJátékos\n".getBytes(StandardCharsets.UTF_8));//Játékos beleírása a fájlba
            if (player.size()>0)
            {
                seged=player.get(0).getSzereploNev()+"\n";
                data=seged.getBytes(StandardCharsets.UTF_8);
                filebair.write(data);
                for (int i = 0; i < player.get(0).getInventory().size(); i++)//A játékosnál lévő tágyak beleírása a fájlba
                {
                    seged=player.get(0).getInventory().get(i).getTargyNev()+";"+
                            player.get(0).getInventory().get(i).getTargySuly()+"\n";
                    data=seged.getBytes(StandardCharsets.UTF_8);
                    filebair.write(data);
                }
            }
            seged="$\nNPC\n";//NPC beleírása a fájlba
            filebair.write(seged.getBytes(StandardCharsets.UTF_8));
            if (nonplayer.size()>0)
            {
                seged=nonplayer.get(0).getSzereploNev()+"\n";
                filebair.write(seged.getBytes(StandardCharsets.UTF_8));
                for (int i = 0; i < nonplayer.get(0).getInventory().size(); i++)//Az NPC-nél lévő tárgyak beleírása a fájlba
                {
                    seged=nonplayer.get(0).getInventory().get(i).getTargyNev()+";"+
                            nonplayer.get(0).getInventory().get(i).getTargySuly()+"\n";
                    data=seged.getBytes(StandardCharsets.UTF_8);
                    filebair.write(data);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Egy TXT fájl tartalmát beolvassa.
     * @return Arraylist, amiben benne van a játékosok, NPC-k és a tárgyak listája.
     * @throws FileNotFoundException Ha a forrásfájl nem található
     */
    public static ArrayList<ArrayList<?>> betolt() throws FileNotFoundException
    {
            player=new ArrayList<>();
            items=new ArrayList<>();
            nonplayer=new ArrayList<>();
            Scanner sc=new Scanner(file);
            ArrayList<String> beolvas=new ArrayList<>();
            while (sc.hasNextLine())
            {
                beolvas.add(sc.nextLine());
            }
            int i=0;
            if (beolvas.get(i).equals("Tárgyak"))//Fájlból a tárgyak beolvasása
            {
                i++;
                while (!beolvas.get(i).equals("*"))
                {
                    String[] line=beolvas.get(i).split(";");
                    items.add(new Targy(line[0],Double.parseDouble(line[1])));
                    i++;
                }
                i++;
            }
            else
            {
                throw new RuntimeException("A beolvasandó fájl nem megfelelő struktúrájú volt");
            }
            if (beolvas.get(i).equals("Játékos"))//Fájlból a játékos beolvasása
            {
                i++;
                if (!beolvas.get(i).equals("$"))
                {
                    player.add(new Jatekos(beolvas.get(i)));
                    i++;
                    while (!beolvas.get(i).equals("$"))//játékos tágyai
                    {
                        String[] line=beolvas.get(i).split(";");
                        player.get(0).addToInventory(ItemKeres(line[0],items));
                        i++;
                    }
                }
                i++;
            }
            if (beolvas.get(i).equals("NPC"))//Fájlból a NPC beolvasása
            {
                i++;
                if (beolvas.size()>i)
                {
                    nonplayer.add(new NPC(beolvas.get(i)));
                    i++;
                    while (beolvas.size()>i)//NPC tárgyai
                    {
                        String[] line=beolvas.get(i).split(";");
                        nonplayer.get(0).addToInventory(ItemKeres(line[0],items));
                        i++;
                    }
                }

            }
            sc.close();
            ArrayList<ArrayList<?>> result=new ArrayList<>();
            result.add(player);
            result.add(nonplayer);
            result.add(items);
            return result;
        }

    /**
     * A beadott nevű tárgyat megkeresi az összes létező tárgy között.
     * @param nev A keresendő tárgy neve.
     * @param targy Az összes tárgy listája.
     * @return A megtalált tárgy.
     * @throws RuntimeException Ha nem létezik a keresett tárgy az összes tárgy között.
     */
    protected static Targy ItemKeres(String nev,ArrayList<Targy> targy)//Megkeresi az összes tárgy között az adott tárgyat
    {
        for (int i = 0; i < targy.size(); i++)
        {
            if (targy.get(i).getTargyNev().equals(nev))
            {
                return targy.get(i);
            }
        }
        throw new RuntimeException();
    }

    /**
     * A beadott nevű tárgyat megkeresi az összes létező tárgy között.
     * @param nev A keresendő tárgy neve.
     * @param suly A keresendő tárgy súlya.
     * @param targy Az összes tárgy listája.
     * @return A megtalált tárgy.
     * @throws RuntimeException Ha nem létezik a keresett tárgy az összes tárgy között.
     */
    protected static Targy ItemKeres(String nev,Double suly,ArrayList<Targy> targy)//Megkeresi az összes tárgy között az adott tárgyat
    {
        for (int i = 0; i < targy.size(); i++)
        {
            if (targy.get(i).getTargyNev().equals(nev) && suly==targy.get(i).getTargySuly())
            {
                return targy.get(i);
            }
        }
        throw new RuntimeException();
    }

}
