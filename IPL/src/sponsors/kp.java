//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class kp extends JPanel {
    private JLabel sp_kp;
    private JTextArea jta_kp;
    private JTextArea jta_supkp;

    public kp() {
        //construct components
        sp_kp = new JLabel ("pic");
        jta_kp = new JTextArea (5, 5);
        jta_supkp = new JTextArea (5, 5);

        //adjust size and set layout
        setPreferredSize (new Dimension (560, 435));
        setLayout (null);

        //add components
        add (sp_kp);
        add (jta_kp);
        add (jta_supkp);

        //set component bounds (only needed by Absolute Positioning)
        sp_kp.setBounds (20, 185, 170, 145);
        jta_kp.setBounds (20, 15, 505, 165);
        jta_supkp.setBounds (350, 185, 175, 150);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("kp");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new kp());
        frame.pack();
        frame.setVisible (true);
    }
}