//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Failure{
    public JFrame jf_fail;
	 public JPanel jp_fail;
    public JLabel jl_icici;
    public JLabel jl_incomptran;
    public JLabel jl_refresh;
    public JLabel jl_sorry;
    public JLabel jl_error;
    public JButton jb_back;
    public JLabel jl_navi;

    public Failure() {
        jf_fail = new JFrame ("Failure"); 
		  jf_fail.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		  jp_fail = new JPanel();
		  jf_fail.add(jp_fail);
		  failure();
		  jf_fail.pack();
        jf_fail.setVisible (true);

    }
    void failure()
	 {
	     jl_icici = new JLabel (new ImageIcon("icicibank.gif"));
        jl_incomptran = new JLabel ("The transaction was incomplete. No credit was debited.");
        jl_refresh = new JLabel ("Please refresh your gateway page and re enter your credit card details.");
        jl_sorry = new JLabel ("Sorry for the inconvenience.");
        jl_error = new JLabel ("An ERROR occurred during the transaction and had to be aborted.");
        jb_back = new JButton ("Back");
        jl_navi = new JLabel ("Click back to navigate to the gateway page.");

        //adjust size and set layout
        jp_fail.setPreferredSize (new Dimension (560, 435));
        jp_fail.setLayout (null);

        //add components
        jp_fail.add (jl_icici);
        jp_fail.add (jl_incomptran);
        jp_fail.add (jl_refresh);
        jp_fail.add (jl_sorry);
        jp_fail.add (jl_error);
        jp_fail.add (jb_back);
        jp_fail.add (jl_navi);

        //set component bounds (only needed by Absolute Positioning)
        jl_icici.setBounds (0, 0, 565, 65);
        jl_incomptran.setBounds (110, 115, 335, 30);
        jl_refresh.setBounds (65, 150, 410, 30);
        jl_sorry.setBounds (65, 185, 170, 30);
        jl_error.setBounds (90, 80, 380, 30);
        jb_back.setBounds (210, 230, 100, 25);
        jl_navi.setBounds (230, 185, 250, 30);
	 }	  

    public static void main (String[] args) {
        new Failure();
        
    }
}
