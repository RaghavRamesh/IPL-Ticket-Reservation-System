import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class MyPanel {
    
    private JFrame frame,jf1;
    JPanel jp1,jp2,jp3;	
	 private JMenuBar jmb;
    private JLabel jl_adv,jl_date,jl_bytn,jl_dlficon,jl_bcci,jl_todmat,jlbl_adv;
    private JButton jb_rr,jb_mi,jb_rcb,jb_csk,jb_kkr,jb_kp,jb_dd,jb_dc,jb_abtus,jb_sb,jb_bb;
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
	 private ResultSet rs;
	 private Connection con;
	 private Statement S;
	 card c1 = new card();
	 Success s = new Success();

	 
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
				//	 jta.setEnabled(false);
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
       jb_home = new JButton ("Back");
	    jl_yn = new JLabel("");
	    jb_chkav = new JButton ("Check Availability");
	    String[] jcb_standItems = {"STANDA", "STANDB", "Pavilion", "Terrace"};
	    jcb_stand = new JComboBox (jcb_standItems);	
		 jb_booknow = new JButton ("Book Now");
		 String[] jcb_matItems = {"RCB vs CSK", "MI vs KXIP"}; 
		 jcb_mat = new JComboBox (jcb_matItems); 
		  
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
       		 	String[] jcb_mmItems = {"March", "April"};
	        		String[] jcb_ddItems = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "13", "14", "15", "16", "17", "18", "20", "21", "22", "23", "24", "27", "28", "30"};
        		 	
        		 	

        			//construct components
        			jlbl_date = new JLabel ("Date:");
        			jcb_mm = new JComboBox (jcb_mmItems);
        			jcb_dd = new JComboBox (jcb_ddItems);
        			jl_match = new JLabel ("Match:");
        			
        			jl_stand = new JLabel ("Stand:");
        			
        			jlbl_tic = new JLabel ("No of Tickets:");
        			jt_tic = new JTextField (1);
        			jl_price = new JLabel ("Price:");
        			jt_price = new JTextField (1);
        			jl_rupee = new JLabel ("`");
					jl_rupee.setFont(new Font("Rupee Foradian",Font.BOLD,12));
        			
        			
        			jb_choose = new JButton ("Choose");
		         jl_gilly = new JLabel(new ImageIcon("gilly.jpg"));
					jl_yuvi = new JLabel(new ImageIcon("yuvi.jpg"));
					
               jl_bytn = new JLabel();
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
        			jcb_mm.setBounds (250, 80, 75, 25);
        			jcb_dd.setBounds (350, 80, 35, 25);
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
				if(e.getSource()==jb_chkav)
				{
				   jl_yn.setText(""+execQuery());
				   jb_choose.setEnabled(true);
            }
						  
		      
				if(e.getSource()==jb_booknow)
				{
				   
					frame.setVisible(false);
			//		card c = new card();
					c1.bookNow();
					frame.remove(jp2);
					if(jp1!=null)
					   frame.remove(jp1);
					if(jp3!=null)
					   frame.remove(jp3);	
					frame.add(c1.jp_card);
					frame.setVisible(true);
					
				}
				
				cancel();
				sucPayHome();		
			}	
		  };
		  jb_sb.addActionListener(al);
		  jb_home.addActionListener(al);
		  jb_chkav.addActionListener(al);
		  jb_booknow.addActionListener(al);
		  jcb_mat.addActionListener(al);
		  
    }
	 
	 void cancel()
	 {
	   
		 ActionListener al = new ActionListener()
		 {
		    public void actionPerformed(ActionEvent e)
			 {
			     
		        if(e.getSource()==c1.jb_cancel)
				  {
				     frame.setVisible(false);
					  frame.remove(c1.jp_card);
					  frame.add(jp2);
					  frame.setVisible(true);
				  }
				  if(e.getSource()==c1.jb_pay)
				  {
				     frame.setVisible(false);
					  frame.remove(c1.jp_card);
					  frame.add(s.jp_sucpay);
					  s.successPay();
					  frame.setVisible(true);
				  }	  
			 }
		 };
		 c1.jb_cancel.addActionListener(al);	 	  	  
		 c1.jb_pay.addActionListener(al);
	 }
	 
	 void sucPayHome()
	 {
	      
		 ActionListener al = new ActionListener()
		 {
		    public void actionPerformed(ActionEvent e)
			 {
			    if(e.getSource()==s.jbtns_home)
		  		{
		     		frame.setVisible(false);
			  		frame.remove(s.jp_sucpay);
			  		frame.add(jp1);
			  		frame.setVisible(true);
		  		}
			}
		};
		s.jbtns_home.addActionListener(al);
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
		  fileMenu.setMnemonic('f');
        exitItem = new JMenuItem ("Exit",'x');
		  exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));

        fileMenu.add (exitItem);
        helpMenu = new JMenu ("Help");
		  helpMenu.setMnemonic('h');
        sponsorsItem = new JMenuItem ("Sponsors",'s');
		  sponsorsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        helpMenu.add (sponsorsItem);
        aboutItem = new JMenuItem ("About Us",'u');
		  aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK));
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
        jb_kp = new JButton (new ImageIcon("KP_teamLogo.gif"));
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
        jp1.add (jb_kp);
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
        jb_kp.setBounds (400, 80, 75, 65);
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
		  
		  ActionListener al = new ActionListener()
		  {
		     public void actionPerformed(ActionEvent e)
			  {
			     if(e.getSource()==jb_rr)
				  {
				     rr rr_obj = new rr();
				  }
				  if(e.getSource()==jb_rcb)
				  {
				     rcb rcb_obj = new rcb();
				  }
				  if(e.getSource()==jb_mi)
				  {
				     mi mi_obj = new mi();
				  }
				  if(e.getSource()==jb_kp)
				  {
				     kp mi_obj = new kp();
				  }
				  if(e.getSource()==jb_kkr)
				  {
				     kkr mi_obj = new kkr();
				  }
				  if(e.getSource()==jb_dd)
				  {
				     dd mi_obj = new dd();
				  }
				  if(e.getSource()==jb_dc)
				  {
				     dc mi_obj = new dc();
				  }
				  if(e.getSource()==jb_csk)
				  {
				     csk mi_obj = new csk();
				  }
			  }
		  };
		  jb_rr.addActionListener(al);
		  jb_rcb.addActionListener(al);
		  jb_mi.addActionListener(al);	  	  
		  jb_kp.addActionListener(al);
		  jb_kkr.addActionListener(al);
		  jb_dd.addActionListener(al);
		  jb_dc.addActionListener(al);
		  jb_csk.addActionListener(al);	  
	 }
	 
	 void setUpDB()
     {
      
        String URL = "jdbc:mysql://198.168.0.7:3306/xib05" ;
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection(URL,"xib05","");
           if (con.isClosed()) JOptionPane.showMessageDialog(null, "CONNECTION FAILED...");
           else JOptionPane.showMessageDialog(null, "CONNECTION SUCCESSFUL");
        }
	  
	    catch(Exception e){
           JOptionPane.showMessageDialog(null,"*** MySQL ERROR! *** " + e.getMessage());
           //System.exit(0);
        }
    }
	
	 String execQuery()
    {
        setUpDB();
		  String s ="" ;
		  try{
			  JOptionPane.showMessageDialog(null, jcb_stand.getSelectedItem().toString());
			  rs = S.executeQuery("SELECT * FROM " +jcb_stand.getSelectedItem().toString()+ " WHERE STAT IS NULL");
			  JOptionPane.showMessageDialog(null, rs.getString("STAT"));
			
			  int i;
			  for(i=0;rs.next();i++);
			  if(i==0)
			    s="no";
           else
			    s="yes";
         //   jl_yn.setText(""+s);
		    JOptionPane.showMessageDialog(null,""+s);
		    
        }
	    catch(Exception error)
	    {
			JOptionPane.showMessageDialog(null,""+error.getMessage());
        }
		return s;
    }	  


    public static void main (String[] args) {
        
		  new MyPanel();
    }
}

class rr  {
    
    private JLabel sp_rr;
    private JTextArea jta_rr;
    private JTextArea jta_suprr;
    private JFrame jf_rr;
	 private JPanel jp_rr;
	 private JScrollPane rr_det, rr_sup;

    public rr() {
	       
	     jf_rr = new JFrame("Rajasthan Royals");
		  jf_rr.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_rr = new JPanel();
		  jp_rr.setBackground(Color.blue);    
		  jf_rr.add(jp_rr);    
		  
        buildRR();
		  jf_rr.pack();    
		  jf_rr.setVisible (true);
    }
	 
	 void buildRR()
	 {
	       
        sp_rr = new JLabel (new ImageIcon("rr_teamLogo1.gif"));
        jta_rr = new JTextArea (5, 5);
        jta_suprr = new JTextArea (5, 5);
		  jta_rr.setText("The team from India's princely state didn't quite command\n" + 
		  "the kind of respect and interest that befits royalty. So when the Rajasthan Royals,\n" +
		  "led by Shane Warne, swept the opposition away and took home the first-ever IPL title,\n" +
		   "everyone was surprised. Sohail Tanvir and Shane Watson were the other big names that\n" +
	      "powered the side, but it was players like Swapnil Asnodkar and Ravindra Jadeja that shone.\n" +
         "IPL 2009 got underway with Bollywood diva Shilpa Shetty and her then fiance Raj Kundra \n" +
		 "also joining the franchise as co-owners. Kaif was left out and Watson was unavailable.\n" +
		  "Tyron Henderson, who was roped in after a tug-of-war with KXIP, played just one game.\n" +
		   "Yusuf Pathan starred in a win over rr in the only Super Over of the tournament.\n" +
		    "At the 2010 auction, Rajasthan bought two Australian players - Adam Voges and in \n" +
			"a surprise move, the out of action Damien Martyn. The team had bought out \n" +
			"Tyron Henderson, Robert Quiney and Mohd Kaif prior to the auction"); 
        jta_suprr.setText("Support Staff:\n"+

                            "Darren Berry\n"+ 
                            "Sushil Tulaskar \n"+ 
                            "Monty Desai \n"+ 
                            "Saurabh Walkar ");     
		  rr_det = new JScrollPane(jta_rr);
		  rr_sup = new JScrollPane(jta_suprr);
        
        jp_rr.setPreferredSize (new Dimension (560, 435));
        jp_rr.setLayout (null);

        
        jp_rr.add (sp_rr);
        jp_rr.add (rr_det);
        jp_rr.add (rr_sup);

        
        sp_rr.setBounds (175, 15, 215, 150);
        rr_det.setBounds (20, 185, 320, 165);
        rr_sup.setBounds (350, 190, 175, 150);
    }
}
class rcb  {
    public JLabel sp_rcb;
    public JTextArea jta_rcb;
    public JTextArea jta_suprcb;
    public JFrame jf_rcb;
	 public JPanel jp_rcb;
	 public JScrollPane rcb_det, rcb_sup;

    public rcb() {
	   
	     jf_rcb = new JFrame("Royal Challengers Bangalore");
		  jf_rcb.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_rcb = new JPanel();
		  jp_rcb.setBackground(Color.red);    
		  jf_rcb.add(jp_rcb);    
		  
        buildrcb();
		  jf_rcb.pack();    
		  jf_rcb.setVisible (true);
    }
	 
	 void buildrcb()
	 {
	       
        sp_rcb = new JLabel (new ImageIcon("rcb_teamLogo.jpg"));
        jta_rcb = new JTextArea (5, 5);
        jta_suprcb = new JTextArea (5, 5);
		  jta_rcb.setText("Royal Challengers Bangalore, like Deccan Chargers,\n" +
		   "had ended up at the bottom of the table in the inaugural edition of IPL.\n" +
		    "Both teams ended up meeting in the final of IPL 2009 in South Africa. Anil Kumble\n" +
			"-led RCB with great heart, but was unable to win the title. Kumble's most impressive\n" +
			 "virtues as a cricketer - grit and determination - came to the fore in South Africa.\n" +
		    " The side lost four of their first five matches in IPL 2009, but made a startling \n" +
			 "comeback to make it to the semi-finals.\n" +
             "The team qualified for the CLT20 but could not qualify for the knock-out stage.\n" +
			  "The team has undergone a sea-change in terms of personnel and support staff\n" +
			   "from Season 2008 with new coach Ray Jennings making a huge impact.\n" +
			   " Former captain Kevin Pietersen's participation is still debatable owing to\n" +
				 "an extended injury break. Their only buy at the 2010 auction was\n" +
				 " Englishman Eoin Morgan, but they also have in their ranks India's under-19 captain\n" +
				  " Ashok Menaria while Nathan Bracken has been bought out. ");
				    
        jta_suprcb.setText("Support Staff:\n"+

                            "Ray Jennings \n"+
							"Evan Speechley  \n"+ 
                            "Sanath Kumar \n"+ 
                            "Ramesh Man\n"+ 
                            "Avinash Vaidya ");
							     
		  rcb_det = new JScrollPane(jta_rcb);
		  rcb_sup = new JScrollPane(jta_suprcb);
        
        jp_rcb.setPreferredSize (new Dimension (560, 435));
        jp_rcb.setLayout (null);

        
        jp_rcb.add (sp_rcb);
        jp_rcb.add (rcb_det);
        jp_rcb.add (rcb_sup);

        
        sp_rcb.setBounds (175, 15, 215, 150);
        rcb_det.setBounds (20, 185, 320, 165);
        rcb_sup.setBounds (350, 190, 175, 150);
    }
}
class mi  {
    public JLabel sp_mi;
    public JTextArea jta_mi;
    public JTextArea jta_supmi;
    public JFrame jf_mi;
	 public JPanel jp_mi;
	 public JScrollPane mi_det, mi_sup;

    public mi() {
	   
	     jf_mi = new JFrame("Mumbai Indians");
		  jf_mi.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_mi = new JPanel();
		  jp_mi.setBackground(Color.blue);    
		  jf_mi.add(jp_mi);    
		  
        buildmi();
		  jf_mi.pack();    
		  jf_mi.setVisible (true);
    }
	 
	 void buildmi()
	 {
	       
        sp_mi = new JLabel (new ImageIcon("mi_teamLogo.jpg"));
        jta_mi = new JTextArea (5, 5);
        jta_supmi = new JTextArea (5, 5);
		  jta_mi.setText("Sachin Tendulkar was named captain and icon player\n" + 
		  "of Mumbai Indians, the highest-priced side, owned by the Reliance Group\n" +
		   "of Companies, ahead of the inaugural IPL. The team had some turbo-charged \n" +
		   "players like Jayasuriya, Shaun Pollock, Harbhajan and Malinga. The side,\n" +
		    "however, failed to deliver on its promise in the inaugural competition,\n" +
			 "despite some memorable performances like that of the young all-rounder Abhishek Nayar.\n" +
              "The second season opened with two new names\n" + 
             "for the side - Zaheer Khan, who was brought in from RCB,\n" +
			  "and batsman Shikhar Dhawan from Daredevils. JP Duminy, the\n" +
			   "South African powerhouse, was roped in, fresh from his success\n" +
			    "in Australia. But the side could not quite make it even this time\n" +
				 "round, bowing out of the competition before the semis.\n" +
				  "Kyle Mills and Mohd. Ashraful have been bought out.\n" +
				   "At the 2010 auction, they bagged the biggest player of them all -\n" +
				    "Kieron Pollard - at USD 750,000 after winning the silent tie-breaker.\n" +
					 "Harshal Patel from the U-19 side has also found a place in the side.\n" +
					   "and batsman Shikhar Dhawan from Daredevils. JP Duminy, the South African\n" +
					    "powerhouse, was roped in, fresh from his success in Australia.\n" +
						" But the side could not quite make it even this time round, bowing \n" +
						 "out of the competition before the semis. Kyle Mills and Mohd.\n" +
						  "Ashraful have been bought out. At the 2010 auction, they bagged \n" +
						  "the biggest player of them all - Kieron Pollard - at USD 750,000\n" +
						   "after winning the silent tie-breaker. Harshal Patel from the U-19 side\n" +
						    "has also found a place in the side."); 
        jta_supmi.setText("Support Staff:\n"+

                            "Robin Singh \n"+ 
                            "Shaun Pollock \n"+ 
                            "Dr. Nitin Pate\n"+ 
                            "Donald Shugg ");     
		  mi_det = new JScrollPane(jta_mi);
		  mi_sup = new JScrollPane(jta_supmi);
        
        jp_mi.setPreferredSize (new Dimension (560, 435));
        jp_mi.setLayout (null);

        
        jp_mi.add (sp_mi);
        jp_mi.add (mi_det);
        jp_mi.add (mi_sup);

        
        sp_mi.setBounds (175, 15, 215, 150);
        mi_det.setBounds (20, 185, 320, 165);
        mi_sup.setBounds (350, 190, 175, 150);
    }
}
class kp  {
    public JLabel sp_kp;
    public JTextArea jta_kp;
    public JTextArea jta_supkp;
    public JFrame jf_kp;
	 public JPanel jp_kp;
	 public JScrollPane kp_det, kp_sup;

    public kp() {
	   
	     jf_kp = new JFrame("Kings XI Punjab");
		  jf_kp.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_kp = new JPanel();
		  jp_kp.setBackground(Color.pink);    
		  jf_kp.add(jp_kp);    
		  
        buildkp();
		  jf_kp.pack();    
		  jf_kp.setVisible (true);
    }
	 
	 void buildkp()
	 {
	      
        sp_kp = new JLabel (new ImageIcon("kp_teamLogo.jpg"));
        jta_kp = new JTextArea (5, 5);
        jta_supkp = new JTextArea (5, 5);
		  jta_kp.setText("Kings XI Punjab have an aura of glamour and verve\n" + 
		  "around them thanks to owners Preity Zinta and Ness Wadia. They boast\n" +
		   "the services of the flamboyant Yuvraj Singh and dependable Sangakkara;\n" +
		    "the destructive Irfan Pathan and experienced Jayawardene. So when the\n" +
		    "first edition of the IPL kicked off, the team from India's lion-hearted\n" +
			 "state were looked on with awe and admiration. But the side could not\n" +
			 "progress beyond the semi-final stage.\n" +
             "In 2009, the spectacular South African setting did little to embellish \n" +
			 "the team's chances - they bowed out before the semis, depleted by the absence\n" +
			  "of Brett Lee and Sreesanth at various stages. Left-arm paceman Yusuf \n" +
			  "Abdulla was one of the finds of the tournament and he has been retained\n" +
			   "for the third edition. Luke Pomersbach and Nuwan Kulasekara were bought\n" +
			    "our ahead of the 2010 auction while they picked former India batsman \n" +
				"Mohammed Kaif who is expected to recreate the magic with Yuvraj Singh in the\n" +
				 "Punjab side under new captain Kumar Sangakkara.");
		   
        jta_supkp.setText("Support Staff:\n"+
                           " Tom Moody  \n"+
                           "Patrick Farhart \n"+ 
                           "Trevor Penney\n"+ 
                            " Alan Campbell\n"+ 
                             "Colonel S. K. Mehta  \n"+ 
                             "Bhupinder Singh \n"+ 
                              "David O Nosworthy");
  
							   
		  kp_det = new JScrollPane(jta_kp);
		  kp_sup = new JScrollPane(jta_supkp);
        
        jp_kp.setPreferredSize (new Dimension (560, 435));
        jp_kp.setLayout (null);

        
        jp_kp.add (sp_kp);
        jp_kp.add (kp_det);
        jp_kp.add (kp_sup);

        
        sp_kp.setBounds (175, 15, 215, 150);
        kp_det.setBounds (20, 185, 320, 165);
        kp_sup.setBounds (350, 190, 175, 150);
    }
}
class kkr  {

    public JLabel sp_kkr;
    public JTextArea jta_kkr;
    public JTextArea jta_supkkr;
    public JFrame jf_kkr;
	 public JPanel jp_kkr;
	 public JScrollPane kkr_det, kkr_sup;

    public kkr() {
	   
	     jf_kkr = new JFrame("Kolkata Knight Riders");
		  jf_kkr.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_kkr = new JPanel();
		  jp_kkr.setBackground(Color.black);    
		  jf_kkr.add(jp_kkr);    
		  
        buildKKR();
		  jf_kkr.pack();    
		  jf_kkr.setVisible (true);
    }
	 
	 void buildKKR()
	 {
	       
        sp_kkr = new JLabel (new ImageIcon("KKR_teamLogo.jpg"));
        jta_kkr = new JTextArea (5, 5);
        jta_supkkr = new JTextArea (5, 5);
		  jta_kkr.setText("When Shahrukh Khan - India's biggest movie star - co-purchased the Kolkata Knight Riders\n"+
		  "franchise, the nation couldn't help but feel an affinity with the side.\n" +
		   "And when Brendon McCullum stormed into the competition with a whirlwind 158*,\n"+ 
			"it drew a collective gasp of admiration from fans of the sport across the world.\n" + 
			"If Sourav helmed the side, then there was John Buchanan who powered the think-tank.\n"+ 
			"But the team could not live up to expectations.\n " +

        "In the second season, suggestions of a multiple-captain approach drew a lot of debate \n"+
		  "but as it turned out, McCullum ended up leading the side throughout. But the side's \n"+
		  "prospects did not improve. KKR ended the tournament pretty close to the bottom of the table.\n"+
		  "Ricky Ponting and Morne van Wyk have been bought out ahead of Season 2010, while Shane Bond\n" +
		  "has been bought at USD 750,000. U-19 player Harpreet Singh has also been picked for the\n"+
		  " tournament."); 
        jta_supkkr.setText("Support Staff:\n"+

                            "Dave Whatmore\n"+ 
                            "Wasim Akram\n"+ 
                            "Andrew Leipus\n"+ 
                            "Adrian Le");     
		  kkr_det = new JScrollPane(jta_kkr);
		  kkr_sup = new JScrollPane(jta_supkkr);
        
        jp_kkr.setPreferredSize (new Dimension (560, 435));
        jp_kkr.setLayout (null);

        
        jp_kkr.add (sp_kkr);
        jp_kkr.add (kkr_det);
        jp_kkr.add (kkr_sup);

        
        sp_kkr.setBounds (175, 15, 215, 150);
        kkr_det.setBounds (20, 185, 320, 165);
        kkr_sup.setBounds (350, 190, 175, 150);
    }
}
class dd  {
    public JLabel sp_dd;
    public JTextArea jta_dd;
    public JTextArea jta_supdd;
    public JFrame jf_dd;
	 public JPanel jp_dd;
	 public JScrollPane dd_det, dd_sup;

    public dd() {
	 
	     jf_dd = new JFrame("Delhi Daredevils");
		  jf_dd.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_dd = new JPanel();
		  jp_dd.setBackground(Color.red);    
		  jf_dd.add(jp_dd);    
		  
        buildDD();
		  jf_dd.pack();    
		  jf_dd.setVisible (true);
    }
	 
	 void buildDD()
	 {
	      
        sp_dd = new JLabel (new ImageIcon("DD_teamLogo.jpg"));
        jta_dd = new JTextArea (5, 5);
        jta_supdd = new JTextArea (5, 5);
		  jta_dd.setText("Delhi Daredevils, owned by GMR Industries, have a number of\n" + 
		  "genuine match-winners in their squad. They boast of Virender Sehwag and Gautam\n" +
		  "Gambhir at the top of the order while wicketkeeper-batsman Dinesh Karthik beefs\n" +
		  "up the middle-order. Tillakaratne Dilshan, along with AB de Villiers, has been a\n" +
		  " revelation in this format. Veteran Daniel Vettori adds depth to the squad.\n" +
		  " Daredevils made it to the semis in the first season.\n" +
          "In the second season, they rode high on the strength of Dirk Nannes' pace, topped\n" +
		  " the league, but again, lost in the semis. Gambhir has now stepped in as captain after\n" +
		  "Sehwag decided to step down. Dehi have bought out Oz legend Glenn McGrath before the \n" +
		  "third season. They picked up Wayne Parnell, the South African left-arm pacer for a whopping\n" +
		  "USD 610,000 at the 2010 auction. They also included the U-19 player Manan Sharma in their\n" +
		  " ranks.");
		   
        jta_supdd.setText("Support Staff:\n"+
                           "Gregory Shipped\n"+ 
                            "Eric Simons\n"+ 
                            "Rajeev Kumar\n"+ 
                            "Trent Woodhill\n"+ 
                             "Rob Walters\n"+ 
                             "Justin Steer");   
							   
		  dd_det = new JScrollPane(jta_dd);
		  dd_sup = new JScrollPane(jta_supdd);
        
        jp_dd.setPreferredSize (new Dimension (560, 435));
        jp_dd.setLayout (null);

        
        jp_dd.add (sp_dd);
        jp_dd.add (dd_det);
        jp_dd.add (dd_sup);

        
        sp_dd.setBounds (175, 15, 215, 150);
        dd_det.setBounds (20, 185, 320, 165);
        dd_sup.setBounds (350, 190, 175, 150);
    }	
}
class dc  {
    public JLabel sp_dc;
    public JTextArea jta_dc;
    public JTextArea jta_supdc;
    public JFrame jf_dc;
	 public JPanel jp_dc;
	 public JScrollPane dc_det, dc_sup;

    public dc() {

	     jf_dc = new JFrame("Deccan Chargers");
		  jf_dc.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_dc = new JPanel(); 
		  jp_dc.setBackground(Color.gray);   
		  jf_dc.add(jp_dc);    
		  
        buildDC();
		  jf_dc.pack();    
		  jf_dc.setVisible (true);
    }
	 
	 void buildDC(){
	     
        sp_dc = new JLabel (new ImageIcon("dc_teamLogo.jpg"));
        jta_dc = new JTextArea (5, 5);
        jta_supdc = new JTextArea (5, 5);
		  jta_dc.setText("The reigning title-holders of the second edition of the IPL,\n" +
		  " Deccan Chargers had a fairytale second season. Plagued by poor performances,\n" +
		  " the team was placed at the bottom of the table at the end of IPL's opening season.\n" +	
		  " the team was placed at the bottom of the table at the end of IPL's opening season.\n" +
		  " But they scripted an incredible turnaround in the second edition, claiming the title.\n" +
		  " Much credit for their rags to riches story goes to their captain Adam Gilchrist.\n" +
		  " But they scripted an incredible turnaround in the second edition, claiming the title.\n" +
		  " Much credit for their rags to riches story goes to their captain Adam Gilchrist.\n" +
          "His compatriot Darren Lehmann joined the team as coach for the second edition.\n" +
		  " Deccan's pace attack is led by the fiery Fidel Edwards and the steady RP Singh.\n" +
		  " They bought out Sri Lankans Nuwan Zoysa and Chamara Silva ahead of the third edition.\n" +
		  " To further strengthen their bowling, they picked up the much sought after West Indies\n" +
		  " fast bowler, Kemar Roach, for a staggering USD 720,000 at the 2010 auction. U-19 player\n" +
		  " Harmeet Singh was also picked for the coming tournament.");
		   
        jta_supdc.setText("Support Staff:\n"+
                            "Darren Lehmann\n"+ 
                            "Mike Young \n"+
                            "Steve Smith \n"+
                            "Sean Slattery \n"+
                            "Ashleigh Joyce "); 
                                                  
		  dc_det = new JScrollPane(jta_dc);
		  dc_sup = new JScrollPane(jta_supdc);

		  
        
        jp_dc.setPreferredSize (new Dimension (561, 436));
        jp_dc.setLayout (null);

        
        jp_dc.add (sp_dc);
        jp_dc.add (dc_det);
        jp_dc.add (dc_sup);

        
        sp_dc.setBounds (175, 15, 215, 150);
       dc_det.setBounds (20, 185, 320, 165);
        dc_sup.setBounds (350, 190, 175, 150);
    }
}
class csk  {
    public JLabel sp_csk;
    public JTextArea jta_csk;
    public JTextArea jta_supcsk;
    public JFrame jf_csk;
	 public JPanel jp_csk;
	 public JScrollPane csk_det, csk_sup;

    public csk() {
	 
	     jf_csk = new JFrame("Chennai Super Kings");
		  jf_csk.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_csk = new JPanel();
		  jp_csk.setBackground(Color.yellow);    
		  jf_csk.add(jp_csk);    

        buildCSK();
		  jf_csk.pack();    
		  jf_csk.setVisible (true);
    }
	 
	 void buildCSK(){
	     
        sp_csk = new JLabel (new ImageIcon("csk_teamLogo.jpg"));
        jta_csk = new JTextArea (5, 5);
        jta_supcsk = new JTextArea (5, 5);
		  jta_csk.setText("Chennai Super Kings, owned by India Cements, managed to get the\n" +
		   "prized services of Mahendra Singh Dhoni in the inaugural edition of IPL. Quite\n" +
		   "understandably, the Indian captain commanded the highest price at the player\n" +
		   "auctions - a whopping USD 1.5 million. Australia's batting tour de force Matthew\n" +
			"Hayden was roped in to give the batting a destructive edge - and he played his role\n" +
			 "to perfection in two successive seasons.\n" +
              "Stephen Fleming, who doubled as coach in the second season; the wily Muralidaran;\n" +
			  "batsman Suresh Raina; and all-rounder Albie Morkel helped Chennai reach the final.\n" +
			   "When the second round of auctions took place, Andrew Flintoff joined as one of the\n" +
				"highest-paid players. In 2009, Hayden scored prolifically while his team entered\n" +
				 "the semi-final, only to be pipped at the post by RCB. Fleming has now been bought\n" +
				 "out by the side and is just the coach. At the 2010 auction, the team bought South \n" +
				 "African Justin Kemp, they also brought in Sri Lankan Thisara Perera for a sum\n" +
				  "of USD 50,000"); 
        jta_supcsk.setText("Support Staff:\n"+

                            "Stephen Fleming:\n" +
                             "Gregory King:\n" + 
                              "Tom Simsek:\n" + 
                             "Dr.Gopal Ramanathan:\n" +       
                            "R Radhakrishnan:\n" + 
                             "Chandrasekar VB:\n" + 
                             "Rodney Parry:\n" + 
                             "Venkatesh Prasad"); 
                                                  
		  csk_det = new JScrollPane(jta_csk);
		  csk_sup = new JScrollPane(jta_supcsk);
        //adjust size and set layout
        jp_csk.setPreferredSize (new Dimension (561, 436));
        jp_csk.setLayout (null);

        //add components
        jp_csk.add (sp_csk);
        jp_csk.add (csk_det);
        jp_csk.add (csk_sup);

        //set component bounds (only needed by Absolute Positioning)
        sp_csk.setBounds (175, 15, 215, 150);
        csk_det.setBounds (20, 185, 320, 165);
        csk_sup.setBounds (350, 190, 175, 150);
    }
}
class card {
    JPanel jp_card;
    public JLabel jl_cc;
    public JTextField jt_cc1;
    public JLabel jl_cc1;
    public JTextField jt_cc2;
    public JLabel jl_cc2;
    public JTextField jt_cc3;
    public JLabel jl_price;
    public JTextField jt_price;
    public JLabel jl_date;
    public JTextField jt_date;
    public JLabel jl_ctype;
    public JComboBox jcb_cardtype;
    public JLabel jl_cc3;
    public JTextField jt_cc4;
    public JLabel jl_expdate;
    public JComboBox jcb_month;
    public JComboBox jcb_date;
    public JLabel jl_rupeec;
    public JLabel jl_name;
    public JTextField jt_name;
    public JButton jb_pay;
    public JButton jb_cancel;
    public JLabel jl_icici;
    public JLabel jl_expcaption;
    public JLabel jl_cardcaption;
    public JLabel jl_nocaption;
    public JLabel jl_typecaption;
 
    public card() {

       jp_card = new JPanel();
		 jb_pay = new JButton ("Pay");
		 jp_card.add (jb_pay);
       jb_cancel = new JButton ("Cancel");   
		 jp_card.add (jb_cancel);         
	 }
	 
	 void bookNow()
	 {
	    //construct preComponents
        String[] jcb_cardtypeItems = {"Maestro	", "MasterCard", "VISA"};
        String[] jcb_monthItems = {"MM", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] jcb_dateItems = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

        //construct components
        jl_cc = new JLabel ("Credit Card No:");
        jt_cc1 = new JTextField (1);
        jl_cc1 = new JLabel ("--");
        jt_cc2 = new JTextField (1);
        jl_cc2 = new JLabel ("--");
        jt_cc3 = new JTextField (1);
        jl_price = new JLabel ("Purchase Amount:");
        jt_price = new JTextField (1);
        jl_date = new JLabel ("Date of purchase:");
        jt_date = new JTextField (1);
        jl_ctype = new JLabel ("Card Type:");
        jcb_cardtype = new JComboBox (jcb_cardtypeItems);
        jl_cc3 = new JLabel ("--");
        jt_cc4 = new JTextField (1);
        jl_expdate = new JLabel ("Expiry Date:");
        jcb_month = new JComboBox (jcb_monthItems);
        jcb_date = new JComboBox (jcb_dateItems);
        jl_rupeec = new JLabel ("`");
		  jl_rupeec.setFont(new Font("Rupee Foradian",Font.BOLD,12));
        jl_name = new JLabel ("Name On Card:");
        jt_name = new JTextField (1);
        
        
        jl_icici = new JLabel (new ImageIcon("icicibank.gif"));
        jl_expcaption = new JLabel ("(Please enter expiry date on your card)");
        jl_cardcaption = new JLabel ("(Please enter the name on your card)");
        jl_nocaption = new JLabel ("(Enter 16 digit no)");
        jl_typecaption = new JLabel ("(Choose card type)");

        //set components properties
        jt_price.setEnabled (false);
        jt_date.setEnabled (false);
		  jb_pay.setEnabled(false);

        //adjust size and set layout
        jp_card.setPreferredSize (new Dimension (560, 435));
        jp_card.setLayout (null);
		  jp_card.setBackground(Color.orange);

        //add components
        jp_card.add (jl_cc);
        jp_card.add (jt_cc1);
        jp_card.add (jl_cc1);
        jp_card.add (jt_cc2);
        jp_card.add (jl_cc2);
        jp_card.add (jt_cc3);
        jp_card.add (jl_price);
        jp_card.add (jt_price);
        jp_card.add (jl_date);
        jp_card.add (jt_date);
        jp_card.add (jl_ctype);
        jp_card.add (jcb_cardtype);
        jp_card.add (jl_cc3);
        jp_card.add (jt_cc4);
        jp_card.add (jl_expdate);
        jp_card.add (jcb_month);
        jp_card.add (jcb_date);
        jp_card.add (jl_rupeec);
        jp_card.add (jl_name);
        jp_card.add (jt_name);
        
        
        jp_card.add (jl_icici);
        jp_card.add (jl_expcaption);
        jp_card.add (jl_cardcaption);
        jp_card.add (jl_nocaption);
        jp_card.add (jl_typecaption);

        //set component bounds (only needed by Absolute Positioning)
        jl_cc.setBounds (85, 115, 90, 25);
        jt_cc1.setBounds (195, 115, 40, 25);
        jl_cc1.setBounds (240, 115, 10, 25);
        jt_cc2.setBounds (250, 115, 40, 25);
        jl_cc2.setBounds (295, 115, 10, 25);
        jt_cc3.setBounds (360, 115, 40, 25);
        jl_price.setBounds (85, 175, 110, 25);
        jt_price.setBounds (205, 175, 100, 25);
        jl_date.setBounds (85, 235, 105, 25);
        jt_date.setBounds (195, 235, 100, 25);
        jl_ctype.setBounds (85, 85, 80, 25);
        jcb_cardtype.setBounds (195, 85, 100, 25);
        jl_cc3.setBounds (350, 115, 10, 25);
        jt_cc4.setBounds (305, 115, 40, 25);
        jl_expdate.setBounds (85, 145, 100, 25);
        jcb_month.setBounds (195, 145, 45, 25);
        jcb_date.setBounds (255, 145, 45, 25);
        jl_rupeec.setBounds (195, 175, 15, 25);
        jl_name.setBounds (85, 205, 100, 25);
        jt_name.setBounds (195, 205, 100, 25);
        jb_pay.setBounds (150, 280, 100, 25);
        jb_cancel.setBounds (265, 280, 100, 25);
        jl_icici.setBounds (0, 0, 560, 75);
        jl_expcaption.setBounds (305, 145, 240, 25);
        jl_cardcaption.setBounds (300, 205, 215, 25);
        jl_nocaption.setBounds (405, 115, 100, 25);
        jl_typecaption.setBounds (305, 85, 115, 25);
    }
}
class Success  {
	 JPanel jp_sucpay;
    public JLabel jl_success;
    public JLabel jl_clicksave;
    public JLabel jl_clickpre;
    public JButton jb_preview;
    public JButton jbtns_home;
    public JButton jb_print;
    public JLabel jl_iciciadv;

    public Success() {
	     jp_sucpay = new JPanel();
		  jp_sucpay.setBackground(Color.orange);
        
		  jbtns_home = new JButton ("Home");
		  jp_sucpay.add (jbtns_home);
        
    }
    
	 void successPay()
	 {
	    //construct components
        jl_success = new JLabel ("Transaction successful. Booking completed. ");
        jl_clicksave = new JLabel ("Click save to save your ticket as a pdf file to be printed later.");
        jl_clickpre = new JLabel ("Click Preview to preview your ticket.");
        jb_preview = new JButton ("Preview");
        
        jb_print = new JButton ("Print");
        jl_iciciadv = new JLabel (new ImageIcon("icicibank.gif"));

        //adjust size and set layout
        jp_sucpay.setPreferredSize (new Dimension (560, 435));
        jp_sucpay.setLayout (null);

        //add components
        jp_sucpay.add (jl_success);
        jp_sucpay.add (jl_clicksave);
        jp_sucpay.add (jl_clickpre);
        jp_sucpay.add (jb_preview);
        
        jp_sucpay.add (jb_print);
        jp_sucpay.add (jl_iciciadv);

        //set component bounds (only needed by Absolute Positioning)
        jl_success.setBounds (90, 100, 265, 30);
        jl_clicksave.setBounds (90, 135, 340, 30);
        jl_clickpre.setBounds (90, 165, 225, 30);
        jb_preview.setBounds (90, 205, 100, 25);
        jbtns_home.setBounds (310, 205, 100, 25);
        jb_print.setBounds (200, 205, 100, 25);
        jl_iciciadv.setBounds (0, 0, 560, 80);
    }
}	 

	 
	 	 	  	 