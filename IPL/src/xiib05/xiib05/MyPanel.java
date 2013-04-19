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
	 Failure f = new Failure();
	 Button b = new Button();
	 String price = "";
	 String date = "";
	 

	 int n;

	 
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
			    jt_tic = new JTextField (1);

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
					 if(c1.jp_card!=null)
					 frame.remove(c1.jp_card);
					 if(s.jp_sucpay!=null)
					 frame.remove(s.jp_sucpay);
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
		 jb_choose = new JButton ("Choose");
		 jt_tic = new JTextField (1);
		 
		 
		 
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
        			jt_tic.setText("");
        			jl_stand = new JLabel ("Stand:");
        			
        			jlbl_tic = new JLabel ("No of Tickets:");
        			jl_price = new JLabel ("Price:");
        			jt_price = new JTextField (1);
        			jl_rupee = new JLabel ("`");
					jl_rupee.setFont(new Font("Rupee Foradian",Font.BOLD,15));
        			
        			
        			
		         jl_gilly = new JLabel(new ImageIcon("RCBANIM.gif"));
					jl_yuvi = new JLabel(new ImageIcon("CSKANIM.gif"));
					
               jl_bytn = new JLabel();
        			//set components properties
        			jcb_stand.setEnabled (false);
        			jt_price.setEnabled (false);
        	//		jb_booknow.setEnabled (false);
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
					c1.jt_price.setText(""+price);
					c1.jt_date.setText(""+date);
				}
				
				if(e.getSource()==jb_choose)
				{
					
					b.buildButton();
					b.confirm();
					
					
				}	
				if(e.getSource()==jt_tic)
				{
				   double p=0,sa = 2000,sb=1000,pav=10000,ter=500;  
					if(jcb_stand.getSelectedItem().toString().equals("STANDA"))
					   p=sa;
					if(jcb_stand.getSelectedItem().toString().equals("STANDB"))
					   p=sb;
					if(jcb_stand.getSelectedItem().toString().equals("Pavilion"))
					   p=pav;
					if(jcb_stand.getSelectedItem().toString().equals("Terrace"))
					   p=ter;
					int i = Integer.parseInt(jt_tic.getText());
					price =	""+(b.i*p);
					if(i==b.i)
					{
					   jt_price.setText(""+price);
					}
					else
					{   
					   JOptionPane.showMessageDialog(null,"No of tickets chosen do not match. Please re enter");
						jt_tic.setText("");
					}
					date = ""+jcb_dd.getSelectedItem().toString()+"-"+jcb_mm.getSelectedItem().toString()+","+"2010";
					//JOptionPane.showMessageDialog(null,""+date);
				}		
							     
				cancel();
				pay();
				sucPayHome();		
			}	
		  };
		  jb_sb.addActionListener(al);
		  jb_home.addActionListener(al);
		  jb_chkav.addActionListener(al);
		  jb_booknow.addActionListener(al);
		  jcb_mat.addActionListener(al);
		  jb_choose.addActionListener(al);
		  jt_tic.addActionListener(al);
		  
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
			 }
		 };
		 c1.jb_cancel.addActionListener(al);
	 }	 
	    	 	 	  
	 void pay()
	 {
	   
		 ActionListener al = new ActionListener()
		 {
		    public void actionPerformed(ActionEvent e)
			 { 
			     if(e.getSource()==c1.jb_pay)
				  {
				     if(c1.validate())
					  {
					     frame.setVisible(false);
					     frame.remove(c1.jp_card);
					     frame.add(s.jp_sucpay);
					     s.successPay();
				        frame.setVisible(true);
					  }   
					  //if(c1.jt_cc1.getText().equals("") || c1.jt_cvv.getPassword().equals("") || c1.jt_name.getText().equals(""))
					 /*else
					  {
					     frame.setVisible(false);
					  	  frame.remove(c1.jp_card);
				
				        frame.add(f.jp_fail);
					     f.failure();
		           
					     frame.setVisible(true);
					  }*/
				  }
				  	  
			 }
		 };
		 	 	  	  
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
				if(e.getSource()==f.jb_back)
		  		{
		     		frame.setVisible(false);
			  		frame.remove(f.jp_fail);
			  		frame.add(c1.jp_card);
			  		frame.setVisible(true);
		  		}
			
			}
		};
		s.jbtns_home.addActionListener(al);
		f.jb_back.addActionListener(al);
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
		  "the kind of respect and interest that befits royalty.\n"+
		  " So when the Rajasthan Royals," +
		  "led by Shane Warne, swept the \n"+"opposition away and took home the first-ever IPL title,\n" +
		   "everyone was surprised. Sohail Tanvir and Shane Watson\n"+
			" were the other big names that" +
	      "powered the side, but it was players like\n"+" Swapnil Asnodkar and Ravindra Jadeja that shone.\n" +
         "IPL 2009 got underway with Bollywood diva Shilpa Shetty\n"+" and her then fiance Raj Kundra " +
		 "also joining the franchise as co-owners.\n"+ "Kaif was left out and Watson was unavailable.\n" +
		  "Tyron Henderson, who was roped in after a tug-of-war with \n"+"KXIP, played just one game." +
		   "Yusuf Pathan starred in a win over rr\n"+" in the only Super Over of the tournament.\n" +
		    "At the 2010 auction, Rajasthan bought\n"+" two Australian players - Adam Voges and in \n" +
			"a surprise move, the out of action Damien Martyn. The team had bought out \n" +
			"Tyron Henderson, Robert Quiney and \nMohd Kaif prior to the auction"); 
        jta_suprr.setText("              Support Staff:\n"+

                          "              Darren Berry\n"+ 
                          "              Sushil Tulaskar \n"+ 
                          "              Monty Desai \n"+ 
                          "              Saurabh Walkar ");     
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
		  WindowAdapter wl = new WindowAdapter(){
			   public void windowClosing(WindowEvent we){
			      jf_rr.dispose();
         }
        };
		       jf_rr.addWindowListener(wl);
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
		  	WindowAdapter wl = new WindowAdapter(){
			   public void windowClosing(WindowEvent we){
			      jf_rcb.dispose();
         }
        };
		       jf_rcb.addWindowListener(wl);
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
		  	WindowAdapter wl = new WindowAdapter(){
			   public void windowClosing(WindowEvent we){
			      jf_mi.dispose();
         }
        };
		       jf_mi.addWindowListener(wl);
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
		  	WindowAdapter wl = new WindowAdapter(){
			   public void windowClosing(WindowEvent we){
			      jf_kp.dispose();
         }
        };
		       jf_kp.addWindowListener(wl);
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
		  WindowAdapter wl = new WindowAdapter(){
			   public void windowClosing(WindowEvent we){
			      jf_kkr.dispose();
         }
        };
		       jf_kkr.addWindowListener(wl);
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
		  WindowAdapter wl = new WindowAdapter(){
			   public void windowClosing(WindowEvent we){
			      jf_dd.dispose();
         }
        };
		       jf_dd.addWindowListener(wl);
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
		  	WindowAdapter wl = new WindowAdapter(){
			   public void windowClosing(WindowEvent we){
			      jf_dc.dispose();
         }
        };
		       jf_dc.addWindowListener(wl);
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
		  
		  WindowAdapter wl = new WindowAdapter(){
			   public void windowClosing(WindowEvent we){
			      jf_csk.dispose();
         }
        };
		       jf_csk.addWindowListener(wl);
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
	 public JLabel jl_cvv;
    public JPasswordField jt_cvv;
    public JLabel jcomp30;
 
    public card() {

       jp_card = new JPanel();
		 jb_pay = new JButton ("Pay");
		 jp_card.add (jb_pay);
       jb_cancel = new JButton ("Cancel");   
		 jp_card.add (jb_cancel);         
	 }
	 
	 boolean validate()
	 {
	    boolean validate = false;
		 boolean val = false;boolean vals = true;  
		 try
		 {
		    int cc1 = Integer.parseInt(jt_cc1.getText());
			 int cc2 = Integer.parseInt(jt_cc2.getText());
			 int cc3 = Integer.parseInt(jt_cc3.getText());
			 int cc4 = Integer.parseInt(jt_cc4.getText());
		 }
		 catch(Exception err1)
		 {
		    val = false;  
			 JOptionPane.showMessageDialog(null,"Please enter the cc no properly");
		 }	 
		/* try{	 
			 String cvv = jt_cvv.getPassword().toString();
			 int len = cvv.length();
			 if(len == 3)
			    vals = true;
			 else 
			 {
			    vals = false;
				 JOptionPane.showMessageDialog(null,"Please enter the cvv no properly");
			 }	 	 
				 
		 }
		 catch(Exception err2)
		 {
		    //JOptionPane.showMessageDialog(null,"Please enter the cvv no properly");
		 }	*/
		 try{	 
			 String name = jt_name.getText().toString();
			 for(int i=0;i<name.length();i++)
			 {
			    char ch = name.charAt(i);
				 if(ch>='a' && ch<='z')
				    val = true;
				 else
				 { 
				    val = false;
					 JOptionPane.showMessageDialog(null,"Please enter the name properly");
				 }	 
			 }		 	 
			 	 
			 
			 
		 }
		 catch(Exception err3)
		 {
		   // JOptionPane.showMessageDialog(null,"Please enter the name properly");
		 }
		 if(val==true && vals == true)
			    validate = true;
		 return validate;
	 }	 	 
			 	 	 
	 public void bookNow()
	 {
	     //construct preComponents
        String[] jcb_cardtypeItems = {"Maestro	", "MasterCard", "VISA"};
        String[] jcb_monthItems = {"MM", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] jcb_dateItems = {"YYYY", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};

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
		  jl_rupeec.setFont(new Font("Rupee Foradian",Font.BOLD,14));
        jl_name = new JLabel ("Name On Card:");
        jt_name = new JTextField (1);
        
        
        jl_icici = new JLabel (new ImageIcon("icicibank.gif"));
        jl_expcaption = new JLabel ("(Please enter expiry date on your card)");
        jl_cardcaption = new JLabel ("(Please enter the name on your card)");
        jl_nocaption = new JLabel ("(Enter 16 digit no)");
        jl_typecaption = new JLabel ("(Choose card type)");
        jl_cvv = new JLabel ("CVV No:");
        jt_cvv = new JPasswordField(3);
        jcomp30 = new JLabel ("(Enter the 3 digit CVV No)");

        //set components properties
        jt_price.setEnabled (false);
        jt_date.setEnabled (false);

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
        jp_card.add (jl_cvv);
        jp_card.add (jt_cvv);
        jp_card.add (jcomp30);

        //set component bounds (only needed by Absolute Positioning)
        jl_cc.setBounds (85, 125, 90, 25);
        jt_cc1.setBounds (195, 125, 40, 25);
        jl_cc1.setBounds (240, 125, 10, 25);
        jt_cc2.setBounds (250, 125, 40, 25);
        jl_cc2.setBounds (295, 125, 10, 25);
        jt_cc3.setBounds (360, 125, 40, 25);
        jl_price.setBounds (85, 215, 110, 25);
        jt_price.setBounds (210, 215, 100, 25);
        jl_date.setBounds (85, 275, 105, 25);
        jt_date.setBounds (195, 275, 100, 25);
        jl_ctype.setBounds (85, 95, 80, 25);
        jcb_cardtype.setBounds (195, 95, 100, 25);
        jl_cc3.setBounds (350, 125, 10, 25);
        jt_cc4.setBounds (305, 125, 40, 25);
        jl_expdate.setBounds (85, 185, 100, 25);
        jcb_month.setBounds (195, 185, 90, 25);
        jcb_date.setBounds (300, 185, 70, 25);
        jl_rupeec.setBounds (200, 215, 15, 25);
        jl_name.setBounds (85, 245, 100, 25);
        jt_name.setBounds (195, 245, 100, 25);
        jb_pay.setBounds (150, 320, 100, 25);
        jb_cancel.setBounds (265, 320, 100, 25);
        jl_icici.setBounds (0, 0, 560, 75);
        jl_expcaption.setBounds (375, 185, 240, 25);
        jl_cardcaption.setBounds (305, 245, 215, 25);
        jl_nocaption.setBounds (405, 125, 100, 25);
        jl_typecaption.setBounds (305, 95, 115, 25);
        jl_cvv.setBounds (85, 155, 100, 25);
        jt_cvv.setBounds (195, 155, 30, 25);
        jcomp30.setBounds (245, 155, 170, 25);
    }
}
class Success  {
	 JFrame jf;
	 JPanel jp_sucpay,jp;
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
        
        jb_print = new JButton ("Save");
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
		  
		  ActionListener al = new ActionListener()
		  {
		     public void actionPerformed(ActionEvent e)
			  {
			     JOptionPane.showMessageDialog(null,"Your ticket is saved as ticket.pdf in your desktop");
			  }
		  };
		  jb_print.addActionListener(al);
		  ActionListener al_p = new ActionListener()
		  {
		     public void actionPerformed(ActionEvent e)
			  {	  	  	  
			     jf = new JFrame("Preview Ticket");
				  jf.setSize(560,435);
				  jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				  jf.add(jp = new JPanel());
				  jp.add(new JLabel(new ImageIcon("ipl-ticket[1].jpg")));
				  jf.setVisible(true);
				  window();
			  }
		  };
		  jb_preview.addActionListener(al_p);
		  	}
		  void window()
		  {
		  	WindowAdapter wl = new WindowAdapter()
		  	{
		  	  public void windowClosing(WindowEvent we)
			  {
			      jf.dispose();
           }
        	};
		  	jf.addWindowListener(wl);
        } 	  
    
}	 
class Button {
    JFrame jf_button;
	 JPanel jp_button;
    public JToggleButton jtb_1;
    public JToggleButton jtb_2;
    public JToggleButton jtb_3;
    public JToggleButton jtb_4;
    public JToggleButton jtb_5;
    public JToggleButton jtb_6;
    public JToggleButton jtb_7;
    public JToggleButton jtb_8;
    public JToggleButton jtb_9;
    public JToggleButton jtb_10;
    public JToggleButton jtb_11;
    public JToggleButton jtb_12;
    public JToggleButton jtb_13;
    public JToggleButton jtb_14;
    public JToggleButton jtb_15;
    public JToggleButton jtb_16;
    public JToggleButton jtb_17;
    public JToggleButton jtb_18;
    public JToggleButton jtb_19;
    public JToggleButton jtb_20;
    public JToggleButton jtb_21;
    public JToggleButton jtb_22;
    public JToggleButton jtb_23;
    public JToggleButton jtb_24;
    public JToggleButton jtb_25;
    public JToggleButton jtb_26;
    public JToggleButton jtb_27;
    public JToggleButton jtb_28;
    public JToggleButton jtb_29;
    public JToggleButton jtb_30;
    public JButton jb_confirm;
	 int i = 0;

    public Button() {
	        
		  
    }
	 void buildButton()
	 {
	    jf_button = new JFrame("Choose your Tickets");
		  jf_button.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_button = new JPanel();
		  jp_button.setBackground(Color.orange);
		  jf_button.add(jp_button);
	     buttons();
		  jf_button.pack();
        jf_button.setVisible (true);
	}	  
    void buttons()
	 {
	     //construct components
        jtb_1 = new JToggleButton ("", false);
        jtb_2 = new JToggleButton ("", false);
        jtb_3 = new JToggleButton ("", false);
        jtb_4 = new JToggleButton ("", false);
        jtb_5 = new JToggleButton ("", false);
        jtb_6 = new JToggleButton ("", false);
        jtb_7 = new JToggleButton ("", false);
        jtb_8 = new JToggleButton ("", false);
        jtb_9 = new JToggleButton ("", false);
        jtb_10 = new JToggleButton ("", false);
        jtb_11 = new JToggleButton ("", false);
        jtb_12 = new JToggleButton ("", false);
        jtb_13 = new JToggleButton ("", false);
        jtb_14 = new JToggleButton ("", false);
        jtb_15 = new JToggleButton ("", false);
        jtb_16 = new JToggleButton ("", false);
        jtb_17 = new JToggleButton ("", false);
        jtb_18 = new JToggleButton ("", false);
        jtb_19 = new JToggleButton ("", false);
        jtb_20 = new JToggleButton ("", false);
        jtb_21 = new JToggleButton ("", false);
        jtb_22 = new JToggleButton ("", false);
        jtb_23 = new JToggleButton ("", false);
        jtb_24 = new JToggleButton ("", false);
        jtb_25 = new JToggleButton ("", false);
        jtb_26 = new JToggleButton ("", false);
        jtb_27 = new JToggleButton ("", false);
        jtb_28 = new JToggleButton ("", false);
        jtb_29 = new JToggleButton ("", false);
        jtb_30 = new JToggleButton ("", false);
        jb_confirm = new JButton ("Confirm");

        //adjust size and set layout
        jp_button.setPreferredSize (new Dimension (560, 435));
        jp_button.setLayout (null);

        //add components
        jp_button.add (jtb_1);
        jp_button.add (jtb_2);
        jp_button.add (jtb_3);
        jp_button.add (jtb_4);
        jp_button.add (jtb_5);
        jp_button.add (jtb_6);
        jp_button.add (jtb_7);
        jp_button.add (jtb_8);
        jp_button.add (jtb_9);
        jp_button.add (jtb_10);
        jp_button.add (jtb_11);
        jp_button.add (jtb_12);
        jp_button.add (jtb_13);
        jp_button.add (jtb_14);
        jp_button.add (jtb_15);
        jp_button.add (jtb_16);
        jp_button.add (jtb_17);
        jp_button.add (jtb_18);
        jp_button.add (jtb_19);
        jp_button.add (jtb_20);
        jp_button.add (jtb_21);
        jp_button.add (jtb_22);
        jp_button.add (jtb_23);
        jp_button.add (jtb_24);
        jp_button.add (jtb_25);
        jp_button.add (jtb_26);
        jp_button.add (jtb_27);
        jp_button.add (jtb_28);
        jp_button.add (jtb_29);
        jp_button.add (jtb_30);
        jp_button.add (jb_confirm);

        //set component bounds (only needed by Absolute Positioning)
        jtb_1.setBounds (100, 65, 45, 40);
        jtb_2.setBounds (150, 65, 45, 40);
        jtb_3.setBounds (200, 65, 45, 40);
        jtb_4.setBounds (250, 65, 45, 40);
        jtb_5.setBounds (300, 65, 45, 40);
        jtb_6.setBounds (350, 65, 45, 40);
        jtb_7.setBounds (100, 110, 45, 40);
        jtb_8.setBounds (150, 110, 45, 40);
        jtb_9.setBounds (200, 110, 45, 40);
        jtb_10.setBounds (250, 110, 45, 40);
        jtb_11.setBounds (300, 110, 45, 40);
        jtb_12.setBounds (350, 110, 45, 40);
        jtb_13.setBounds (100, 155, 45, 40);
        jtb_14.setBounds (150, 155, 45, 40);
        jtb_15.setBounds (200, 155, 45, 40);
        jtb_16.setBounds (250, 155, 45, 40);
        jtb_17.setBounds (300, 155, 45, 40);
        jtb_18.setBounds (350, 155, 45, 40);
        jtb_19.setBounds (100, 200, 45, 40);
        jtb_20.setBounds (150, 200, 45, 40);
        jtb_21.setBounds (200, 200, 45, 40);
        jtb_22.setBounds (250, 200, 45, 40);
        jtb_23.setBounds (300, 200, 45, 40);
        jtb_24.setBounds (350, 200, 45, 40);
        jtb_25.setBounds (100, 245, 45, 40);
        jtb_26.setBounds (150, 245, 45, 40);
        jtb_27.setBounds (200, 245, 45, 40);
        jtb_28.setBounds (250, 245, 45, 40);
        jtb_29.setBounds (300, 245, 45, 40);
        jtb_30.setBounds (350, 245, 45, 40);
        jb_confirm.setBounds (200, 300, 95, 30);  
		 
		  ActionListener al = new ActionListener()
		  {
		     public void actionPerformed(ActionEvent e)
			  {
			     if(e.getSource()==jtb_1)
				  {
				     boolean sel = jtb_1.isSelected();  
					  if(sel)
					     jtb_1.setText("B");
					  else
					     jtb_1.setText("");	  
					    
					 // jtb_1.setEnabled(false);
				  }
				  if(e.getSource()==jtb_2)
				  {
				  boolean sel = jtb_2.isSelected();  
					  if(sel)
					     jtb_2.setText("B");
					  else
					     jtb_2.setText("");
				     					    
					  
				  }
				  if(e.getSource()==jtb_3)
				  {
				     boolean sel = jtb_3.isSelected();  
					  if(sel)
					     jtb_3.setText("B");
					  else
					     jtb_3.setText("");
					    
					  
				  }
				  if(e.getSource()==jtb_4)
				  {
				    boolean sel = jtb_4.isSelected();  
					  if(sel)
					     jtb_4.setText("B");
					  else
					     jtb_4.setText("");
					    
				  }
				  if(e.getSource()==jtb_5)
				  {
                boolean sel = jtb_5.isSelected();  
					  if(sel)
					     jtb_5.setText("B");
					  else
					     jtb_5.setText("");					    
					    
				  }
				  if(e.getSource()==jtb_6)
				  {
				    boolean sel = jtb_6.isSelected();  
					  if(sel)
					     jtb_6.setText("B");
					  else
					     jtb_6.setText("");					    
					    
				  }
				  
				  if(e.getSource()==jtb_7)
				  {
				    boolean sel = jtb_7.isSelected();  
					  if(sel)
					     jtb_7.setText("B");
					  else
					     jtb_7.setText("");					    
				  }
				  if(e.getSource()==jtb_8)
				  {
				     boolean sel = jtb_8.isSelected();  
					  if(sel)
					     jtb_8.setText("B");
					  else
					     jtb_8.setText("");					    
					    
				  }
				  if(e.getSource()==jtb_9)
				  {
				     boolean sel = jtb_9.isSelected();  
					  if(sel)
					     jtb_9.setText("B");
					  else
					     jtb_9.setText("");					    
				  }
				  if(e.getSource()==jtb_10)
				  {
				    boolean sel = jtb_10.isSelected();  
					  if(sel)
					     jtb_10.setText("B");
					  else
					     jtb_10.setText("");					    
				  }
				  if(e.getSource()==jtb_11)
				  {
				     boolean sel = jtb_11.isSelected();  
					  if(sel)
					     jtb_11.setText("B");
					  else
					     jtb_11.setText("");
					    
				  }
				  if(e.getSource()==jtb_12)
				  {
				     boolean sel = jtb_12.isSelected();  
					  if(sel)
					     jtb_12.setText("B");
					  else
					     jtb_12.setText("");					    
				  }
				  if(e.getSource()==jtb_13)
				  {
				    boolean sel = jtb_13.isSelected();  
					  if(sel)
					     jtb_13.setText("B");
					  else
					     jtb_13.setText("");					    
				  }
				  if(e.getSource()==jtb_14)
				  {
				    boolean sel = jtb_14.isSelected();  
					  if(sel)
					     jtb_14.setText("B");
					  else
					     jtb_14.setText("");					    
				  }
				  if(e.getSource()==jtb_15)
				  {
				    boolean sel = jtb_15.isSelected();  
					  if(sel)
					     jtb_15.setText("B");
					  else
					     jtb_15.setText("");
					    
				  }
				  if(e.getSource()==jtb_16)
				  {
				    boolean sel = jtb_16.isSelected();  
					  if(sel)
					     jtb_16.setText("B");
					  else
					     jtb_16.setText("");
					    
				  }
				  if(e.getSource()==jtb_17)
				  {
				    boolean sel = jtb_17.isSelected();  
					  if(sel)
					     jtb_17.setText("B");
					  else
					     jtb_17.setText("");					    
				  }
				  if(e.getSource()==jtb_18)
				  {
				     boolean sel = jtb_18.isSelected();  
					  if(sel)
					     jtb_18.setText("B");
					  else
					     jtb_18.setText("");
					    
				  }
				  if(e.getSource()==jtb_19)
				  {
				    boolean sel = jtb_19.isSelected();  
					  if(sel)
					     jtb_19.setText("B");
					  else
					     jtb_19.setText("");
					    
				  }
				  if(e.getSource()==jtb_20)
				  {
				    boolean sel = jtb_20.isSelected();  
					  if(sel)
					     jtb_20.setText("B");
					  else
					     jtb_20.setText("");					    
				  }
				  if(e.getSource()==jtb_21)
				  {
				    boolean sel = jtb_21.isSelected();  
					  if(sel)
					     jtb_21.setText("B");
					  else
					     jtb_21.setText("");					    
				  }
				  if(e.getSource()==jtb_22)
				  {
				    boolean sel = jtb_22.isSelected();  
					  if(sel)
					     jtb_22.setText("B");
					  else
					     jtb_22.setText("");					    
				  }
				  if(e.getSource()==jtb_23)
				  {
				     boolean sel = jtb_23.isSelected();  
					  if(sel)
					     jtb_23.setText("B");
					  else
					     jtb_23.setText("");					    
				  }
				  
				  if(e.getSource()==jtb_24)
				  {
				    boolean sel = jtb_24.isSelected();  
					  if(sel)
					     jtb_24.setText("B");
					  else
					     jtb_24.setText("");					    
				  }
				  if(e.getSource()==jtb_25)
				  {
				    boolean sel = jtb_25.isSelected();  
					  if(sel)
					     jtb_25.setText("B");
					  else
					     jtb_25.setText("");					    
				  }
				  if(e.getSource()==jtb_26)
				  {
				    boolean sel = jtb_26.isSelected();  
					  if(sel)
					     jtb_26.setText("B");
					  else
					     jtb_26.setText("");					    
				  }
				  if(e.getSource()==jtb_27)
				  {
				    boolean sel = jtb_27.isSelected();  
					  if(sel)
					     jtb_27.setText("B");
					  else
					     jtb_27.setText("");					    
				  }
				  if(e.getSource()==jtb_28)
				  {
				    boolean sel = jtb_28.isSelected();  
					  if(sel)
					     jtb_28.setText("B");
					  else
					     jtb_28.setText("");					    
				  }
				  if(e.getSource()==jtb_29)
				  {
				    boolean sel = jtb_29.isSelected();  
					  if(sel)
					     jtb_29.setText("B");
					  else
					     jtb_29.setText("");
					    
				  }
				  if(e.getSource()==jtb_30)
				  {
				    boolean sel = jtb_30.isSelected();  
					  if(sel)
					     jtb_30.setText("B");
					  else
					     jtb_30.setText("");					    
				  }
				  
					//	jt_tic.setText(""+i);
			     }

		  };
		  jtb_1.addActionListener(al);
		  jtb_2.addActionListener(al);
		  jtb_3.addActionListener(al);
		  jtb_4.addActionListener(al);
		  jtb_5.addActionListener(al);
		  jtb_6.addActionListener(al);
		  jtb_7.addActionListener(al);
		  jtb_8.addActionListener(al);
		  jtb_9.addActionListener(al);
		  jtb_10.addActionListener(al);
		  jtb_11.addActionListener(al);
		  jtb_12.addActionListener(al);
		  jtb_13.addActionListener(al);
		  jtb_14.addActionListener(al);
		  jtb_15.addActionListener(al);
		  jtb_16.addActionListener(al);
		  jtb_17.addActionListener(al);
		  jtb_18.addActionListener(al);
		  jtb_19.addActionListener(al);
		  jtb_20.addActionListener(al);
		  jtb_21.addActionListener(al);
		  jtb_22.addActionListener(al);
		  jtb_23.addActionListener(al);
		  jtb_24.addActionListener(al);
		  jtb_25.addActionListener(al);
		  jtb_26.addActionListener(al);
		  jtb_27.addActionListener(al);
		  jtb_28.addActionListener(al);
		  jtb_29.addActionListener(al);
		  jtb_30.addActionListener(al);
	   //  jb_confirm.addActionListener(al);
		  			
		  WindowAdapter wl = new WindowAdapter()
		  {
		     public void windowClosing(WindowEvent w)
			  {
			     jf_button.dispose();
			  }
		  };
		  jf_button.addWindowListener(wl);
		  	  	  

    }
	 void confirm()
	 {
	    ActionListener al_con = new ActionListener()
		  {
		     public void actionPerformed(ActionEvent e)
			  {
			     if(e.getSource()==jb_confirm)
				  {
				      
						boolean sel1 = jtb_1.isSelected();
						boolean sel2 = jtb_2.isSelected();
						boolean sel3 = jtb_3.isSelected();
						boolean sel4 = jtb_4.isSelected();
						boolean sel5 = jtb_5.isSelected();
						boolean sel6 = jtb_6.isSelected();
						boolean sel7 = jtb_7.isSelected();
						boolean sel8 = jtb_8.isSelected();
						boolean sel9 = jtb_9.isSelected();
						boolean sel10 = jtb_10.isSelected();
						boolean sel11 = jtb_11.isSelected();
						boolean sel12 = jtb_12.isSelected();
						boolean sel13 = jtb_13.isSelected();
						boolean sel14 = jtb_14.isSelected();
						boolean sel15 = jtb_15.isSelected();
						boolean sel16 = jtb_16.isSelected();
						boolean sel17 = jtb_17.isSelected();
						boolean sel18 = jtb_18.isSelected();
						boolean sel19 = jtb_19.isSelected();
						boolean sel20 = jtb_20.isSelected();
						boolean sel21 = jtb_21.isSelected();
						boolean sel22 = jtb_22.isSelected();
						boolean sel23 = jtb_23.isSelected();
						boolean sel24 = jtb_24.isSelected();
						boolean sel25 = jtb_25.isSelected();
						boolean sel26 = jtb_26.isSelected();
						boolean sel27 = jtb_27.isSelected();
						boolean sel28 = jtb_28.isSelected();
						boolean sel29 = jtb_29.isSelected();    
						boolean sel30 = jtb_30.isSelected();  
					   if(sel1 && jtb_1.getText().equals("B"))
						   i++;
						if(sel2 && jtb_2.getText().equals("B"))
						   i++;
						if(sel3 && jtb_3.getText().equals("B"))
						   i++;
						if(sel4 && jtb_4.getText().equals("B"))
						   i++;
						if(sel5 && jtb_5.getText().equals("B"))
						   i++;
						if(sel6 && jtb_6.getText().equals("B"))
						   i++;
						if(sel7 && jtb_7.getText().equals("B"))
						   i++;
						if(sel8 && jtb_8.getText().equals("B"))
						   i++;
						if(sel9 && jtb_9.getText().equals("B"))
						   i++;
						if(sel10 && jtb_10.getText().equals("B"))
						   i++;
						if(sel11 && jtb_11.getText().equals("B"))
						   i++;
						if(sel12 && jtb_12.getText().equals("B"))
						   i++;
						if(sel13 && jtb_13.getText().equals("B"))
						   i++;
						if(sel14 && jtb_14.getText().equals("B"))
						   i++;
						if(sel15 && jtb_15.getText().equals("B"))
						   i++;
						if(sel16 && jtb_16.getText().equals("B"))
						   i++;
						if(sel17 && jtb_17.getText().equals("B"))
						   i++;
						if(sel18 && jtb_18.getText().equals("B"))
						   i++;
						if(sel19 && jtb_19.getText().equals("B"))
						   i++;
						if(sel20 && jtb_20.getText().equals("B"))
						   i++;
						if(sel21 && jtb_21.getText().equals("B"))
						   i++;
						if(sel22 && jtb_22.getText().equals("B"))
						   i++;
						if(sel23 && jtb_23.getText().equals("B"))
						   i++;
						if(sel24 && jtb_24.getText().equals("B"))
						   i++;
						if(sel25 && jtb_25.getText().equals("B"))
						   i++;
						if(sel26 && jtb_26.getText().equals("B"))
						   i++;
						if(sel27 && jtb_27.getText().equals("B"))
						   i++;
						if(sel28 && jtb_28.getText().equals("B"))
						   i++;
						if(sel29 && jtb_29.getText().equals("B"))
						   i++;  
						if(sel30 && jtb_30.getText().equals("B"))
						   i++;  
					   JOptionPane.showMessageDialog(null,""+i);
					}
				}
			};
			jb_confirm.addActionListener(al_con);
		}	
}
class Failure{
    
	 public JPanel jp_fail;
    public JLabel jl_icici;
    public JLabel jl_incomptran;
    public JLabel jl_refresh;
    public JLabel jl_sorry;
    public JLabel jl_error;
    public JButton jb_back;
    public JLabel jl_navi;

    public Failure() {
       
		 jp_fail = new JPanel();
		 jb_back = new JButton ("Back");		  
		 jp_fail.add (jb_back);
		 jp_fail.setBackground(Color.orange);
		  

    }
    void failure()
	 {
	     jl_icici = new JLabel (new ImageIcon("icicibank.gif"));
        jl_incomptran = new JLabel ("The transaction was incomplete. No amount was debited.");
        jl_refresh = new JLabel ("Please refresh your gateway page and re enter your credit card details.");
        jl_sorry = new JLabel ("Sorry for the inconvenience.");
        jl_error = new JLabel ("An ERROR occurred during the transaction and had to be aborted.");
        
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
}

	 
	 	 	  	 