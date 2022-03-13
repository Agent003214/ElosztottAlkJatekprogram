package FileInputOutput;
import Backend.Jatekos;
import Backend.NPC;
import Backend.Szereplo;
import Backend.Targy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FileInputOutput
{
    static File file=new File("mentes.txt");
    private static ArrayList<Jatekos> player;
    private static ArrayList<Targy> items;
    private static ArrayList<NPC> nonplayer;

    public static void mentes(ArrayList<ArrayList> mentendok)
    {
        byte[] data;
        player=mentendok.get(0);
        nonplayer=mentendok.get(1);
        items=mentendok.get(2);

        try (FileOutputStream fos = new FileOutputStream(file))
        {
            data="Tárgyak".getBytes(StandardCharsets.UTF_8);
            String seged;
            fos.write(data);
            for (int i = 0; i < items.size(); i++)
            {
                seged=items.get(i).getTargyNev()+";"+items.get(i).getTargySuly();
                data=seged.getBytes(StandardCharsets.UTF_8);
                fos.write(data);
            }
            fos.write("*".getBytes(StandardCharsets.UTF_8));
            data=player.get(0).getSzereploNev().getBytes(StandardCharsets.UTF_8);
            fos.write(data);
            for (int i = 0; i < player.size(); i++)
            {
                seged=player.get(0).getInventory().get(i).getTargyNev()+";"+
                        player.get(0).getInventory().get(i).getTargySuly();
                data=seged.getBytes(StandardCharsets.UTF_8);
                fos.write(data);
            }
            fos.write("$".getBytes(StandardCharsets.UTF_8));
            fos.write(nonplayer.get(0).getSzereploNev().getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < nonplayer.size(); i++)
            {
                seged=nonplayer.get(0).getInventory().get(i).getTargyNev()+";"+
                        nonplayer.get(0).getInventory().get(i).getTargySuly();
                data=seged.getBytes(StandardCharsets.UTF_8);
                fos.write(data);
            }
            System.out.println("Successfully written data to the file");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList> betolt()
    {
        try
        {
            player=new ArrayList<>();
            items=new ArrayList<>();
            nonplayer=new ArrayList<>();
            Scanner sc=new Scanner(file);
            if (sc.nextLine().equals("Tárgyak"))
            {
                while (sc.nextLine().equals("*"))
                {
                    String[] line=sc.nextLine().split(";");
                    items.add(new Targy(line[0],Double.parseDouble(line[1])));
                }
                if (sc.nextLine().equals("Játékos"))
                {
                    player.add(new Jatekos(sc.nextLine()));
                    while (sc.nextLine().equals("$"))
                    {
                        String[] line=sc.nextLine().split(";");
                        player.get(0).addToInventory(ItemKeres(line[0],items));
                    }
                }
                if (sc.nextLine().equals("NPC"))
                {
                    nonplayer.add(new NPC(sc.nextLine()));
                    while (sc.hasNextLine())
                    {
                        String[] line=sc.nextLine().split(";");
                        nonplayer.get(0).addToInventory(ItemKeres(line[0],items));
                    }
                }
            }
            else
            {
                System.out.println(":D");throw new IOException();
            }

            sc.close();
            ArrayList<ArrayList> result=new ArrayList<>();
            result.add(player);
            result.add(nonplayer);
            result.add(items);
            return result;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    private static Targy ItemKeres(String nev,ArrayList<Targy> targy)
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
}
