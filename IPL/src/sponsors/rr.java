//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class rr extends JPanel {
    private JLabel sp_rr;
    private JTextArea jta_rr;
    private JTextArea jta_suprr;

    public rr() {
        //construct components
        sp_rr = new JLabel ("pic");
        jta_rr = new JTextArea (5, 5);
        jta_suprr = new JTextArea (5, 5);

        //adjust size and set layout
        setPreferredSize (new Dimension (560, 435));
        setLayout (null);

        //add components
        add (sp_rr);
        add (jta_rr);
        add (jta_suprr);

        //set component bounds (only needed by Absolute Positioning)
        sp_rr.setBounds (20, 185, 170, 145);
        jta_rr.setBounds (20, 15, 505, 165);
        jta_suprr.setBounds (350, 185, 175, 150);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("rr");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new rr());
        frame.pack();
        frame.setVisible (true);
    }
}
