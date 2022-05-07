package GUI;

import GUI.JavaFXUI.JavaFXUI;
import GUI.SwingUI.SwingUI;
import javafx.application.Application;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUIChooser extends JFrame
{
    public GUIChooser()
    {
        setTitle("GUI választó");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Dimension dimension = new Dimension(200,30);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Kérlek válaszd ki hogy az alkalmazás melyik grafikus pack-ot használja a megjelenítéshez");
        JButton swingButton = new JButton("Java Swing");
        swingButton.setPreferredSize(dimension);
        JButton FXButton = new JButton("JavaFX");
        FXButton.setPreferredSize(dimension);

        JPanel panel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(label,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(swingButton,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(FXButton,gbc);

        panel.setBorder(new EmptyBorder(10,10,10,10));
        add(panel);

        swingButton.addActionListener(e ->
        {
            this.dispose();
            try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception ignored){}
            SwingUtilities.invokeLater(() -> new SwingUI().setVisible(true));
        });

        FXButton.addActionListener(e ->
        {
            this.dispose();
            Application.launch(JavaFXUI.class);
        });

        pack();
        setLocation(SwingUI.calcualteTheMiddleOfTheScreen(getWidth(),getHeight()));
    }
}
