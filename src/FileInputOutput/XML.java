package FileInputOutput;

import Backend.Jatekos;
import Backend.NPC;
import Backend.Targy;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static FileInputOutput.TXT.ItemKeres;

/**
 * Lekezeli a fájlba mentést és fájlból a betöltést egy XML fájlba.
 */
public class XML
{
    private static ArrayList<Jatekos> player;
    private static ArrayList<Targy> items;
    private static ArrayList<NPC> nonplayer;

    /**
     * Lementi a program jelenlegi állapotát egy XML fájlba
     * @param mentendok Egy ArrayList ami magába foglalja azokat az ArrayList-eket amik a Játékosokat, az NPC-ket és a Tárgyakat tartalmazzák.
     * @throws IOException
     */
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
            //Items
            if (items.size()>0)
            {
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
            }


            //Player
            if (player.size()>0)
            {
                Element playerXML=document.createElement("Player");
                root.appendChild(playerXML);
                Element playerName=document.createElement("PlayerName");
                playerName.appendChild(document.createTextNode(player.get(0).getSzereploNev()));
                playerXML.appendChild(playerName);
                for (int i = 0; i < player.get(0).getInventory().size(); i++)
                {
                    Element playerItems=document.createElement("PlayerItems");
                    playerXML.appendChild(playerItems);
                    Element itemName=document.createElement("ItemName");
                    itemName.appendChild(document.createTextNode(player.get(0).getInventory().get(i).getTargyNev()));
                    playerItems.appendChild(itemName);

                    Element itemWeight=document.createElement("ItemWeight");
                    itemWeight.appendChild(document.createTextNode(player.get(0).getInventory().get(i).getTargySuly()+""));
                    playerItems.appendChild(itemWeight);
                }
            }


            //NPC
            if (nonplayer.size()>0)
            {
                Element NPCXML=document.createElement("NPC");
                root.appendChild(NPCXML);
                Element NPCName=document.createElement("NPCName");
                NPCName.appendChild(document.createTextNode(nonplayer.get(0).getSzereploNev()));
                NPCXML.appendChild(NPCName);
                for (int i = 0; i < nonplayer.get(0).getInventory().size(); i++)
                {
                    Element NPCItems=document.createElement("NPCItems");
                    NPCXML.appendChild(NPCItems);
                    Element itemName=document.createElement("ItemName");
                    itemName.appendChild(document.createTextNode(nonplayer.get(0).getInventory().get(i).getTargyNev()));
                    NPCItems.appendChild(itemName);

                    Element itemWeight=document.createElement("ItemWeight");
                    itemWeight.appendChild(document.createTextNode(nonplayer.get(0).getInventory().get(i).getTargySuly()+""));
                    NPCItems.appendChild(itemWeight);
                }
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
        try
        {
            File inputFile=new File("mentes.xml");
            DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
            dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING,true);
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
                    double itemweight=Double.parseDouble(eElement.getElementsByTagName("ItemWeight").item(0).getTextContent());
                    items.add(new Targy(itemname, itemweight));
                }
            }

            //Player
            NodeList playerNode=doc.getElementsByTagName("Player");
            for (int i = 0; i < playerNode.getLength(); i++)
            {
                Node playerInnerNode=playerNode.item(i);
                if (playerInnerNode.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element element=(Element) playerInnerNode;
                    player.add(new Jatekos(element.getElementsByTagName("PlayerName").item(0).getTextContent()));
                    NodeList playerItems=doc.getElementsByTagName("PlayerItems");
                    for (int j = 0; j < playerItems.getLength(); j++)
                    {
                        Node playerItemsNode=playerItems.item(j);
                        if (playerItemsNode.getNodeType()==Node.ELEMENT_NODE)
                        {
                            Element playerItemElement=(Element) playerItemsNode;

                            String itemNev=playerItemElement.getElementsByTagName("ItemName").item(0).getTextContent();
                            Double itemSuly=Double.parseDouble(playerItemElement.getElementsByTagName("ItemWeight").item(0).getTextContent());
                            player.get(0).addToInventory(ItemKeres(itemNev,itemSuly,items));
                        }
                    }
                }
            }

            //NPC
            NodeList NPCNode=doc.getElementsByTagName("NPC");
            for (int i = 0; i < NPCNode.getLength(); i++)
            {
                Node NPCInnerNode=NPCNode.item(i);
                if (NPCInnerNode.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element element=(Element) NPCInnerNode;
                    nonplayer.add(new NPC(element.getElementsByTagName("NPCName").item(0).getTextContent()));
                    NodeList NPCItems=doc.getElementsByTagName("NPCItems");
                    for (int j = 0; j < NPCItems.getLength(); j++)
                    {
                        Node NPCItemsNode=NPCItems.item(j);
                        if (NPCItemsNode.getNodeType()==Node.ELEMENT_NODE)
                        {
                            Element NPCItemElement=(Element) NPCItemsNode;
                            String itemNev=NPCItemElement.getElementsByTagName("ItemName").item(0).getTextContent();
                            Double itemSuly=Double.parseDouble(NPCItemElement.getElementsByTagName("ItemWeight").item(0).getTextContent());
                            nonplayer.get(0).addToInventory(ItemKeres(itemNev,itemSuly,items));
                        }
                    }
                }
            }

        }
        catch (FileNotFoundException e)
        {
            throw new FileNotFoundException();
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
        ArrayList<ArrayList<?>> result=new ArrayList<>();
        result.add(player);
        result.add(nonplayer);
        result.add(items);
        return result;
    }
}
