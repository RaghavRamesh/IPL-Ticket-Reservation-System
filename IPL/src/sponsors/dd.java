//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class dd extends JPanel {
    private JLabel sp_dd;
    private JTextArea jta_dd;
    private JTextArea jta_supdd;

    public dd() {
        //construct components
        sp_dd = new JLabel ("pic");
        jta_dd = new JTextArea (5, 5);
        jta_supdd = new JTextArea (5, 5);

        //adjust size and set layout
        setPreferredSize (new Dimension (560, 435));
        setLayout (null);

        //add components
        add (sp_dd);
        add (jta_dd);
        add (jta_supdd);

        //set component bounds (only needed by Absolute Positioning)
        sp_dd.setBounds (20, 185, 170, 145);
        jta_dd.setBounds (20, 15, 505, 165);
        jta_supdd.setBounds (350, 185, 175, 150);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("dd");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new dd());
        frame.pack();
        frame.setVisible (true);
    }
}
