//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class dc extends JPanel {
    private JLabel sp_dc;
    private JTextArea jta_dc;
    private JTextArea jta_supdc;

    public dc() {
        //construct components
        sp_dc = new JLabel ("pic");
        jta_dc = new JTextArea (5, 5);
        jta_supdc = new JTextArea (5, 5);

        //adjust size and set layout
        setPreferredSize (new Dimension (560, 435));
        setLayout (null);

        //add components
        add (sp_dc);
        add (jta_dc);
        add (jta_supdc);

        //set component bounds (only needed by Absolute Positioning)
        sp_dc.setBounds (20, 185, 170, 145);
        jta_dc.setBounds (20, 15, 505, 165);
        jta_supdc.setBounds (350, 185, 175, 150);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("dc");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new dc());
        frame.pack();
        frame.setVisible (true);
    }
}
