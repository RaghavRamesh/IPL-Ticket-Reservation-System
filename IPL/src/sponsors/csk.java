//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class csk extends JPanel {
    private JLabel sp_csk;
    private JTextArea jta_csk;
    private JTextArea jta_supcsk;

    public csk() {
        //construct components
        sp_csk = new JLabel ("pic");
        jta_csk = new JTextArea (5, 5);
        jta_supcsk = new JTextArea (5, 5);

        //adjust size and set layout
        setPreferredSize (new Dimension (560, 435));
        setLayout (null);

        //add components
        add (sp_csk);
        add (jta_csk);
        add (jta_supcsk);

        //set component bounds (only needed by Absolute Positioning)
        sp_csk.setBounds (20, 185, 170, 145);
        jta_csk.setBounds (20, 15, 505, 165);
        jta_supcsk.setBounds (350, 185, 175, 150);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("csk");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new csk());
        frame.pack();
        frame.setVisible (true);
    }
}
