import GUI.UI;
import Backend.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args)
    {
        Szereplo teszt=new Jatekos();
        Targy item=new Targy("Kurbli",5.1);
        teszt.addItem(item);
        System.out.println(teszt.toString());

        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ignored){}
        SwingUtilities.invokeLater(() -> new UI().setVisible(true));
    }
}
