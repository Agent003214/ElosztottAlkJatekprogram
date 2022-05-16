package FileInputOutput;
import Backend.Jatekos;
import Backend.NPC;
import Backend.Targy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sun.util.locale.provider.NumberFormatProviderImpl;

/**
 * Lekezeli a fájlba mentést és fájlból a betöltést.
 */
public class FileInputOutput
{
    static File file=new File("mentes.txt");
    private static ArrayList<Jatekos> player;
    private static ArrayList<Targy> items;
    private static ArrayList<NPC> nonplayer;

    public static void mentes(ArrayList<ArrayList<?>> mentendok)
    {
        player= (ArrayList<Jatekos>) mentendok.get(0);
        nonplayer= (ArrayList<NPC>) mentendok.get(1);
        items= (ArrayList<Targy>) mentendok.get(2);
        try
        {
            DocumentBuilderFactory documentFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentFactory.newDocumentBuilder();
            Document document=documentBuilder.newDocument();

            Element root=document.createElement("Save");
            document.appendChild(root);
            Element itemXML=document.createElement("Items");
            root.appendChild(itemXML);
            for (int i = 0; i < items.size(); i++)
            {
                Element itemToAdd=document.createElement("ItemList");
                Attr attr=document.createAttribute("id");
                attr.setValue(i+"");
                itemToAdd.setAttributeNode(attr);
                itemXML.appendChild(itemToAdd);
                Element itemName=document.createElement("ItemName");
                itemName.appendChild(document.createTextNode(items.get(i).getTargyNev()));
                itemToAdd.appendChild(itemName);

                Element itemWeight=document.createElement("ItemWeight");
                itemWeight.appendChild(document.createTextNode(items.get(i).getTargySuly()+""));
                itemToAdd.appendChild(itemWeight);

            }


            TransformerFactory transformerFactory=TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
            DOMSource domSource=new DOMSource(document);
            StreamResult streamResult=new StreamResult(new File("mentes.xml"));
            transformer.transform(domSource,streamResult);
        }
        catch (ParserConfigurationException e)
        {

        }
        catch (TransformerConfigurationException e)
        {

        }
        catch (TransformerException e)
        {

        }



    }

    public static ArrayList<ArrayList<?>> betolt() throws FileNotFoundException
    {
        player=new ArrayList<>();
        items=new ArrayList<>();
        nonplayer=new ArrayList<>();
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try
        {
            File inputFile=new File("mentes.xml");
            DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder=dbFactory.newDocumentBuilder();
            Document doc= dbBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList item=doc.getElementsByTagName("Items");
            NodeList itemList=doc.getElementsByTagName("ItemList");
            for (int i = 0; i < itemList.getLength(); i++)
            {
                Node nNode=itemList.item(i);
                if (nNode.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element eElement=(Element) nNode;
                    String itemname=eElement.getElementsByTagName("ItemName").item(0).getTextContent();
                    double itemweight=Double.parseDouble(eElement.getElementsByTagName("itemWeight").item(0).getTextContent());
                    items.add(new Targy(itemname, itemweight));
                    System.out.println(eElement.getElementsByTagName("ItemName").item(0).getTextContent());
                }
            }
        }
        catch (ParserConfigurationException e)
        {

        }
        catch (IOException e)
        {

        }
        catch (SAXException e)
        {

        }
        return null;
    }

    /**
     * Lementi a program jelenlegi állapotát egy Json fájlba
     * @param mentendok Egy ArrayList ami magába foglalja azokat az ArrayList-eket amik a Játékosokat, az NPC-ket és a Tárgyakat tartalmazzák.
     * @throws IOException
     */
    public static void mentesJson(ArrayList<ArrayList<?>> mentendok)
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
     * JSONObject típusú objektumot hoz létre a beadott adatokból
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
    public static ArrayList<ArrayList<?>> betoltJson() throws FileNotFoundException
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

    /**
     *Elkészít egy szöveges fájlt és lementi a program jelenlegi állapotát egy TXT fájlba.
     * @param mentendok Egy ArrayList ami magába foglalja azokat az ArrayList-eket amik a Játékosokat, az NPC-ket és a Tárgyakat tartalmazzák.
     * @throws IOException Arraylist, amiben benne van a játékosok, NPC-k és a tárgyak listája.
     */
    public static void mentesLegacy(ArrayList<ArrayList> mentendok)
    {
        byte[] data;
        player=mentendok.get(0);
        nonplayer=mentendok.get(1);
        items=mentendok.get(2);

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
    public static ArrayList<ArrayList> betoltLegacy() throws FileNotFoundException
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
            ArrayList<ArrayList> result=new ArrayList<>();
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
    private static Targy ItemKeres(String nev,ArrayList<Targy> targy)//Megkeresi az összes tárgy között az adott tárgyat
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
    private static Targy ItemKeres(String nev,Double suly,ArrayList<Targy> targy)//Megkeresi az összes tárgy között az adott tárgyat
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
