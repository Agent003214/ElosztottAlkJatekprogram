import Backend.Jatekos;
import Backend.Targy;
import GUI.SwingUI.SwingUI;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.text.DecimalFormat;


public class SwingTest
{
    private SwingUI swingUI;
    private Robot robot = new Robot();
    private Jatekos jatekos;
    private Targy targy;

    public SwingTest() throws AWTException
    {

    }

    @BeforeClass
    public void setUp() throws Exception
    {
        jatekos = new Jatekos("Jani");
        targy = new Targy("Lándzsa",5);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        swingUI = new SwingUI();
        swingUI.setVisible(true);
    }

    @Test(groups = "JatekosLetrehoz")
    public void jatekosLetrehozTest() throws Exception
    {

        JPanel jatekos_panel = (JPanel) swingUI.getContentPane().getComponent(0);

        JTextField jatekos_tb = (JTextField) jatekos_panel.getComponent(1);
        jatekos_tb.setText(jatekos.getSzereploNev());

        JButton jatekos_bt = (JButton) jatekos_panel.getComponent(2);

        robot.mouseMove(jatekos_bt.getLocationOnScreen().x,jatekos_bt.getLocationOnScreen().y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        JScrollPane jatekos_list = (JScrollPane) swingUI.getContentPane().getComponent(4);
        JList<String> jatekos_lb = (JList<String>) jatekos_list.getViewport().getComponent(0);
        DefaultListModel<String> list = (DefaultListModel<String>) jatekos_lb.getModel();
        Thread.sleep(2000);
        Assert.assertEquals(list.getElementAt(0),"Név: "+jatekos.getSzereploNev());
        Assert.assertEquals(list.getElementAt(1),"Sebesség: "+jatekos.getSebesseg());
    }
    @Test(groups = "TargyLetrehoz")
    public void targyLetrehozTest() throws Exception
    {

        JPanel targy_panel = (JPanel) swingUI.getContentPane().getComponent(2);

        JTextField targy_nev_tb = (JTextField) targy_panel.getComponent(1);
        targy_nev_tb.setText(targy.getTargyNev());

        JTextField targy_suly_tb = (JTextField) targy_panel.getComponent(3);
        targy_suly_tb.setText(String.valueOf(targy.getTargySuly()));

        JButton targy_bt = (JButton) targy_panel.getComponent(4);

        robot.mouseMove(targy_bt.getLocationOnScreen().x,targy_bt.getLocationOnScreen().y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        JScrollPane jatekos_list = (JScrollPane) swingUI.getContentPane().getComponent(6);
        JList<String> jatekos_lb = (JList<String>) jatekos_list.getViewport().getComponent(0);
        DefaultListModel<String> list = (DefaultListModel<String>) jatekos_lb.getModel();
        Thread.sleep(2000);
        Assert.assertEquals(list.getElementAt(0),"Tárgy neve: "+targy.getTargyNev()+", Tárgy súlya: "+targy.getTargySuly());
    }

    @Test(dependsOnGroups = {"JatekosLetrehoz","TargyLetrehoz"})
    public void targyFelveteleJatekoshozTest() throws Exception
    {
        DecimalFormat df=new DecimalFormat("#.##");
        JPanel jatekos_panel = (JPanel) swingUI.getContentPane().getComponent(0);
        JButton jatekos_targy_bt = (JButton) jatekos_panel.getComponent(5);
        jatekos.addToInventory(targy);

        robot.mouseMove(jatekos_targy_bt.getLocationOnScreen().x,jatekos_targy_bt.getLocationOnScreen().y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        JScrollPane jatekos_list = (JScrollPane) swingUI.getContentPane().getComponent(4);
        JList<String> jatekos_lb = (JList<String>) jatekos_list.getViewport().getComponent(0);
        DefaultListModel<String> list = (DefaultListModel<String>) jatekos_lb.getModel();
        Thread.sleep(2000);

        Assert.assertEquals(list.getElementAt(0),"Név: "+jatekos.getSzereploNev());
        Assert.assertEquals(list.getElementAt(1),"Sebesség: "+df.format(jatekos.getSebesseg()));
        Assert.assertEquals(list.getElementAt(2),"Tárgyak:");
        Assert.assertEquals(list.getElementAt(3),"            "+jatekos.getInventory().get(0).getTargyNev());
        Assert.assertEquals(list.getElementAt(4),"                        "+jatekos.getInventory().get(0).getTargySuly());
    }
}
