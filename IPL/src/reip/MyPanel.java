//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MyPanel {
   
    private JFrame frame,jf1;
    private JPanel jp1,jp2,jp3;	
	 private JMenuBar jmb;
    private JLabel jl_adv,jl_date,jl_bytn,jl_dlficon,jl_bcci,jl_todmat,jlbl_adv;
    private JButton jb_rr,jb_mi,jb_rcb,jb_csk,jb_kkr,jb_kxip,jb_dd,jb_dc,jb_abtus,jb_sb,jb_bb;
    private JLabel jlbl_date,jl_match,jl_price,jl_rupee,jlbl_tic,jl_stand;
    private JComboBox jcb_mm,jcb_dd,jcb_mat,jcb_stand,jcb_todmat;
    private JTextField jt_tic,jt_price;
    private JButton jb_chkav,jb_booknow,jb_choose,jb_home,jbtn_home;
	 private JMenu fileMenu,helpMenu;
	 private JMenuItem exitItem,sponsorsItem,aboutItem;
	 private JTextArea jta;
	 private JLabel jl_gilly;
    private JLabel jl_yuvi;
    private JLabel jl_yn;
	 
    public MyPanel() {
        frame = new JFrame ("IPLT20 Ticket Reservation System");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	           
		  jp1 = new JPanel();
		  jp1.setBackground(Color.orange); 
		  frame.add(jp1);  
		  buildGUI();
		  startBooking();
		  menuAction(); 
		  frame.pack(); 
		  frame.setVisible (true);   
		 
    }
	 
	 public void menuAction()
	 {
	    ActionListener al = new ActionListener()
		 {
		    public void actionPerformed(ActionEvent ev)
			 {
			    if(ev.getSource()== exitItem)
				 {
				    System.exit(0);
				 }
				 
				 if(ev.getSource()== sponsorsItem)
				 {
				    frame.setVisible(false);
					 frame.remove(jp1);
					 if(jp2!=null)
					 frame.remove(jp2);
					 jp3 = new JPanel();
					 jp3.setBackground(Color.orange);
					 String details = "This software is sponsored by:\nHeroHonda - Dhak Dhak Go!\n" +
					                      "Vodafone\n" + 
												 "CitiBank - Citi never sleeps\n" +
												 "DLF - Building India\n" +
												 "Fly KingFisher - Fly the Good times\n" +
												 "Karbonn mobiles\n" +
												 "Maxx\n" + 
												 "Global Cricket Ventures- Bring it Back\n" +
												 "World Sport Group";
					 							   
					 jp3.add(jlbl_adv);
					 jp3.add(new JScrollPane(jta = new JTextArea(8,50)));
					 jta.setText(details);
					 jta.setForeground(Color.red);
					 jta.setEnabled(false);
					 jp3.add(jbtn_home);
					 frame.add(jp3);
					 frame.setVisible(true);
				 }
				 if(ev.getSource()==jbtn_home)
				 {
				    frame.setVisible(false);
			  		 frame.remove(jp3);
			  		 frame.add(jp1);
			  		 frame.setVisible(true);
		  		 }
				   	 
			 }
		 };
		 exitItem.addActionListener(al);
		 sponsorsItem.addActionListener(al);
		 jbtn_home.addActionListener(al);
	 }	 	 	 
				 	 
	 public void startBooking()
	 {
       jb_home = new JButton ("Home");	   
		 ActionListener al = new ActionListener()
		 {
		    public void actionPerformed(ActionEvent e)
			 {
			    if(e.getSource()==jb_sb)
				 {
				   
				 	frame.setVisible(false);
				 	frame.remove(jp1);
		 		 	jp2 = new JPanel();
					jp2.setBackground(Color.orange);
		 	    	frame.add(jp2);
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
        			jl_rupee = new JLabel ("`");
					jl_rupee.setFont(new Font("Rupee Foradian",Font.BOLD,12));
        			jb_chkav = new JButton ("Check Availability");
        			jb_booknow = new JButton ("Book Now");
        			jb_choose = new JButton ("Choose");
		         jl_gilly = new JLabel(new ImageIcon("gilly.jpg"));
					jl_yuvi = new JLabel(new ImageIcon("yuvi.jpg"));
					jl_yn = new JLabel();

        			//set components properties
        			jcb_stand.setEnabled (false);
        			jt_price.setEnabled (false);
        			jb_booknow.setEnabled (false);
        			jb_choose.setEnabled (false);
					jcb_mat.setEnabled(false);

        			//adjust size and set layout
        			jp2.setPreferredSize (new Dimension (560, 435));
        			jp2.setLayout (null);
              
        			//add components
        			jp2.add (jlbl_date);
        			jp2.add (jcb_mm);
        			jp2.add (jcb_dd);
        			jp2.add (jl_match);
        			jp2.add (jcb_mat);
        			jp2.add (jl_stand);
        			jp2.add (jcb_stand);
        			jp2.add (jlbl_tic);
        			jp2.add (jt_tic);
        			jp2.add (jl_price);
        			jp2.add (jt_price);
        			jp2.add (jl_rupee);
        			jp2.add (jb_chkav);
        			jp2.add (jb_booknow);
        			jp2.add (jb_choose);
		  			jp2.add (jb_home);
					jp2.add (jl_gilly);
               jp2.add (jl_yuvi);
               jp2.add (jl_yn);
					date();

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
        			jl_rupee.setBounds (250, 235, 20, 25);
        			jb_chkav.setBounds (170, 170, 135, 25);
        			jb_booknow.setBounds (170, 265, 130, 25);
               jb_choose.setBounds (170, 205, 80, 25);        			
					jb_home.setBounds (305, 265, 80, 25);
					jl_gilly.setBounds (10, 80, 135, 210);
               jl_yuvi.setBounds (450, 80, 135, 210);
               jl_yn.setBounds (320, 170, 80, 25);
		  			frame.setVisible(true);
		 		}
		  		if(e.getSource()==jb_home)
		  		{
		     		frame.setVisible(false);
			  		frame.remove(jp2);
			  		frame.add(jp1);
			  		frame.setVisible(true);
		  		}	  
		    }
		  };
		  jb_sb.addActionListener(al);
		  jb_home.addActionListener(al);
    }
	 
	 void date()
	 {
	    ActionListener al = new ActionListener()
		 {
		    public void actionPerformed(ActionEvent ev)
			 {
			    if(ev.getSource()==jcb_dd)
				 {
				    jcb_mat.setEnabled(true);
				 }
				 if(ev.getSource()==jcb_mat)
				 {
				    jcb_stand.setEnabled(true);
				 }	 
			 }
		 };
		 jcb_dd.addActionListener(al);
		 jcb_mat.addActionListener(al);
	 }	 	 	 	 

	 public void buildGUI()
	 {
	     //construct preComponents
        fileMenu = new JMenu ("File");
        exitItem = new JMenuItem ("Exit");
        fileMenu.add (exitItem);
        helpMenu = new JMenu ("Help");
        sponsorsItem = new JMenuItem ("Sponsors");
        helpMenu.add (sponsorsItem);
        aboutItem = new JMenuItem ("About Us");
        helpMenu.add (aboutItem);
        String[] jcb_todmatItems = {"RCB vs CSK", "MI vs KKR", "DC vs DD", "RR vs KXIP  "};

        //construct components
        jmb = new JMenuBar();
        jmb.add (fileMenu);
        jmb.add (helpMenu);
        jl_adv = new JLabel (new ImageIcon("vod.jpg"));
        jb_rr = new JButton (new ImageIcon("RR_teamLogo.gif"));
        jb_rcb = new JButton (new ImageIcon("RCB_teamLogo.gif"));
        jb_csk = new JButton (new ImageIcon("CSK_teamLogo.gif"));
        jb_mi = new JButton (new ImageIcon("MI_teamLogo.gif"));
        jb_kkr = new JButton (new ImageIcon("KKR_teamLogo.gif"));
        jb_kxip = new JButton (new ImageIcon("KP_teamLogo.gif"));
        jb_dd = new JButton (new ImageIcon("DD_teamLogo.gif"));
        jb_dc = new JButton (new ImageIcon("DC_teamLogo.gif"));
        jb_abtus = new JButton ("About Us");
        jl_todmat = new JLabel ("Today's matches:");
        jcb_todmat = new JComboBox (jcb_todmatItems);
        jl_date = new JLabel ("Date: 21-3-2010");
        jl_bytn = new JLabel ("Book Your Tickets Now!!");
        jb_sb = new JButton ("Start Booking");
        jb_bb = new JButton ("Bulk Booking");
        jl_dlficon = new JLabel (new ImageIcon("ipl_logo.gif"));
        jl_bcci = new JLabel (new ImageIcon("BCCI_logo.gif"));
        jbtn_home = new JButton("Home");
        jlbl_adv = new JLabel(new ImageIcon("vod.jpg"));
		  //adjust size and set layout
        jp1.setPreferredSize (new Dimension (632, 449));
        jp1.setLayout (null);

        //add components
        jp1.add (jmb);
        jp1.add (jl_adv);
        jp1.add (jb_rr);
        jp1.add (jb_rcb);
        jp1.add (jb_csk);
        jp1.add (jb_mi);
        jp1.add (jb_kkr);
        jp1.add (jb_kxip);
        jp1.add (jb_dd);
        jp1.add (jb_dc);
        jp1.add (jb_abtus);
        jp1.add (jl_todmat);
        jp1.add (jcb_todmat);
        jp1.add (jl_date);
        jp1.add (jl_bytn);
        jp1.add (jb_sb);
        jp1.add (jb_bb);
        jp1.add (jl_dlficon);
        jp1.add (jl_bcci);

        //set component bounds (only needed by Absolute Positioning)
        frame.setJMenuBar(jmb);
        jl_adv.setBounds (0, 25, 635, 50);
        jb_rr.setBounds (0, 80, 75, 65);
        jb_rcb.setBounds (80, 80, 75, 65);
        jb_csk.setBounds (160, 80, 75, 65);
        jb_mi.setBounds (240, 80, 75, 65);
        jb_kkr.setBounds (320, 80, 75, 65);
        jb_kxip.setBounds (400, 80, 75, 65);
        jb_dd.setBounds (480, 80, 75, 65);
        jb_dc.setBounds (560, 80, 75, 65);
        jb_abtus.setBounds (0, 330, 105, 30);
        jl_todmat.setBounds (140, 185, 110, 25);
        jcb_todmat.setBounds (255, 185, 100, 25);
        jl_date.setBounds (380, 185, 110, 25);
        jl_bytn.setBounds (245, 400, 140, 30);
        jb_sb.setBounds (140, 215, 330, 25);
        jb_bb.setBounds (505, 335, 125, 25);
        jl_dlficon.setBounds (325, 280, 115, 85);
        jl_bcci.setBounds (170, 280, 115, 85);
	 }	  


    public static void main (String[] args) {
        
		  new MyPanel();
    }
}
