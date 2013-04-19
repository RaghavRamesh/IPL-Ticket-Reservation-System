//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class startbooking1 extends JPanel {
    private JLabel jlbl_date;
    private JComboBox jcb_mm;
    private JComboBox jcb_dd;
    private JLabel jl_match;
    private JComboBox jcb_mat;
    private JLabel jl_stand;
    private JComboBox jcb_stand;
    private JLabel jlbl_tic;
    private JTextField jt_tic;
    private JLabel jl_price;
    private JTextField jt_price;
    private JLabel jl_rupee;
    private JButton jb_chkav;
    private JButton jb_booknow;
    private JButton jb_choose;
    private JButton jb_home;
    private JLabel jl_gilly;
    private JLabel jl_yuvi;
    private JLabel jl_yn;

    public startbooking1() {
        //construct preComponents
        String[] jcb_mmItems = {"3", "4"};
        String[] jcb_ddItems = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "13", "14", "15", "16", "17", "18", "20", "21", "22", "23", "24", "27", "28", "30"};
        String[] jcb_matItems = {"RCB vs RR", "CSK vs KXIP"};
        String[] jcb_standItems = {"A", "B", "Pavilion", "Terrace"};

        //construct components
        jlbl_date = new JLabel ("Date:");
        jcb_mm = new JComboBox (jcb_mmItems);
        jcb_dd = new JComboBox (jcb_ddItems);
        jl_match = new JLabel ("Match:");
        jcb_mat = new JComboBox (jcb_matItems);
        jl_stand = new JLabel ("Stand:");
        jcb_stand = new JComboBox (jcb_standItems);
        jlbl_tic = new JLabel ("No of Tickets:");
        jt_tic = new JTextField (1);
        jl_price = new JLabel ("Price:");
        jt_price = new JTextField (1);
        jl_rupee = new JLabel ("```");
        jb_chkav = new JButton ("Check Availability");
        jb_booknow = new JButton ("Book Now");
        jb_choose = new JButton ("Choose");
        jb_home = new JButton ("Home");
        jl_gilly = new JLabel ("Gilly");
        jl_yuvi = new JLabel ("Yuvi");
        jl_yn = new JLabel ("yes/no");

        //set components properties
        jcb_stand.setEnabled (false);
        jt_price.setEnabled (false);
        jb_booknow.setEnabled (false);
        jb_choose.setEnabled (false);

        //adjust size and set layout
        setPreferredSize (new Dimension (560, 435));
        setLayout (null);

        //add components
        add (jlbl_date);
        add (jcb_mm);
        add (jcb_dd);
        add (jl_match);
        add (jcb_mat);
        add (jl_stand);
        add (jcb_stand);
        add (jlbl_tic);
        add (jt_tic);
        add (jl_price);
        add (jt_price);
        add (jl_rupee);
        add (jb_chkav);
        add (jb_booknow);
        add (jb_choose);
        add (jb_home);
        add (jl_gilly);
        add (jl_yuvi);
        add (jl_yn);

        //set component bounds (only needed by Absolute Positioning)
        jlbl_date.setBounds (170, 80, 35, 25);
        jcb_mm.setBounds (250, 80, 35, 25);
        jcb_dd.setBounds (290, 80, 35, 25);
        jl_match.setBounds (170, 110, 50, 25);
        jcb_mat.setBounds (250, 110, 100, 25);
        jl_stand.setBounds (170, 140, 40, 25);
        jcb_stand.setBounds (250, 140, 60, 25);
        jlbl_tic.setBounds (260, 205, 85, 25);
        jt_tic.setBounds (340, 205, 45, 25);
        jl_price.setBounds (170, 235, 40, 25);
        jt_price.setBounds (265, 235, 100, 25);
        jl_rupee.setBounds (240, 235, 20, 25);
        jb_chkav.setBounds (170, 170, 135, 25);
        jb_booknow.setBounds (170, 265, 130, 25);
        jb_choose.setBounds (170, 205, 80, 25);
        jb_home.setBounds (305, 265, 80, 25);
        jl_gilly.setBounds (10, 80, 135, 210);
        jl_yuvi.setBounds (430, 80, 135, 210);
        jl_yn.setBounds (320, 170, 80, 25);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("startbooking1");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new startbooking1());
        frame.pack();
        frame.setVisible (true);
    }
}