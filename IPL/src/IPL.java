import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class IPL 
{
    JFrame frame,jf1;
    JPanel jp1,jp2,jp3;	
    JMenuBar jmb;
    JLabel jl_adv,jl_date,jl_bytn,jl_dlficon,jl_bcci,jl_todmat,jlbl_adv;
    JButton jb_rr,jb_mi,jb_rcb,jb_csk,jb_kkr,jb_kp,jb_dd,jb_dc,jb_abtus,jb_sb,jb_bb,jb_ko,jb_pu;
    JLabel jlbl_date,jl_match,jl_price,jl_rupee,jlbl_tic,jl_stand;
    JComboBox jcb_mm,jcb_dd,jcb_mat,jcb_stand,jcb_todmat;
    JTextField jt_tic,jt_price;
    JButton jb_chkav,jb_booknow,jb_choose,jb_home,jbtn_home;
	 JMenu fileMenu,helpMenu;
	 JMenuItem exitItem,sponsorsItem,aboutItem;
	 JTextArea jta;
	 JLabel jl_gilly,jl_cskanim,jl_rcbanim,jl_rranim,jl_kpanim;
    JLabel jl_yuvi;
    JLabel jl_yn;
	 ResultSet rs,rs1;
	 Connection con,conn;
    Statement S,S1;
	 int[] a = new int[30]; 
	 card c1 = new card();
	 Success s = new Success();
	 Failure f = new Failure();
	 Button b = new Button();
	 String price = "";
	 String date = "";
	 boolean isValidate = false;  
    boolean valexp = false,valcvv = false,valcc = false,valname=false,valcardtype=false;
	 int n;
	 
    public IPL () 
	 {
	     frame = new JFrame ("IPLT20 Ticket Reservation System");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	           
		  jp1 = new JPanel();
		  jp1.setBackground(new Color(135,206,250)); 
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
				    int option = JOptionPane.showConfirmDialog(null,"Are you sure?","CONFIRMATION",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					 if(option==JOptionPane.YES_OPTION)  
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
					 jp3.setBackground(new Color(135,206,250));
					 String details = "This software is sponsored by:\n"
					                    +"HeroHonda - Dhak Dhak Go!\n" +
					                      "Vodafone-Power to you\n" +
										         
												 "CitiBank - Citi never sleeps\n" +
												 "DLF - Building India\n" +
												 "Fly KingFisher - Fly the Good times\n" +
												 "Karbonn mobiles\n" +
												 "Maxx\n" + 
												 "Global Cricket Ventures- Bring it Back\n" +
												 "World Sport Group\n" +
												 "Dubakoor International\n"+
												 "Pizza Villa";
					 							   
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
       jb_home = new JButton ("Back");
	    jl_yn = new JLabel("");
	    jb_chkav = new JButton ("Check Availability");
	    String[] jcb_standItems = {"STANDC", "ENTERTAINMENT", "PAVILION", "CELEBRITY"};
	    jcb_stand = new JComboBox (jcb_standItems);
		jcb_stand.setSelectedItem("");
		 jcb_stand.setSelectedItem("");	
		 jb_booknow = new JButton ("Book Now");
		 String[] jcb_matItems = {"--- vs ---","CSK vs RCB", "RR vs KXIP"}; 
		 jcb_mat = new JComboBox (jcb_matItems);
		 jb_choose = new JButton ("Choose");
		 jt_tic = new JTextField (1);
		 
		 ActionListener al = new ActionListener()
		 {
		    public void actionPerformed(ActionEvent e)
			 {
				if(e.getSource()==jb_sb)
				 {
				   b.i=0;
				 	frame.setVisible(false);
				 	frame.remove(jp1);
		 		 	jp2 = new JPanel();
					jp2.setBackground(new Color(135,206,250));
		 	    	frame.add(jp2);
		  			
       		 	String[] jcb_mmItems = {"-----","March", "April"};
	        		String[] jcb_ddItems = {"----","2", "3", "4", "5", "6", "7", "8", "9", "10", "13", "14", "15", "16", "17", "18", "20", "21", "22", "23", "24", "27", "28", "30"};
        		 
        			jlbl_date = new JLabel ("Date:");
        			jcb_mm = new JComboBox (jcb_mmItems);
        			jcb_dd = new JComboBox (jcb_ddItems);
        			jl_match = new JLabel ("Match:");
        			jt_tic.setText("");
        			jl_stand = new JLabel ("Stand:");
					jcb_mat.setSelectedIndex(0);
        			jl_yn.setText("");
        			jlbl_tic = new JLabel ("No of Tickets:");
        			jl_price = new JLabel ("Price:");
        			jt_price = new JTextField (1);
        			jl_rupee = new JLabel ("`");
					jl_rupee.setFont(new Font("Rupee Foradian",Font.BOLD,15));
        			
		         jl_gilly = new JLabel(new ImageIcon("gilly.jpg"));
					jl_yuvi = new JLabel(new ImageIcon("yuvi.jpg"));
					jl_cskanim = new JLabel(new ImageIcon("CSKANIM.gif"));
					jl_rcbanim = new JLabel(new ImageIcon("RCBANIM.gif"));
					jl_kpanim = new JLabel(new ImageIcon("KPANIM.gif"));
					jl_rranim = new JLabel(new ImageIcon("RRANIM.gif"));					
               jl_bytn = new JLabel();
        			
        			jcb_stand.setEnabled (false);
        			jt_price.setEnabled (false);
        			jb_choose.setEnabled (false);
					jcb_mat.setEnabled(false);
					jb_chkav.setEnabled(false);

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
        			jcb_dd.setBounds (350, 80, 50, 25);
        			jl_match.setBounds (170, 110, 50, 25);
        			jcb_mat.setBounds (250, 110, 100, 25);
        			jl_stand.setBounds (170, 140, 40, 25);
        			jcb_stand.setBounds (250, 140, 100, 25);
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
			      jl_cskanim.setBounds(10,80,135,210);
					jl_rcbanim.setBounds (450, 80, 135, 210);
					jl_rranim.setBounds(10,80,135,210);
					jl_kpanim.setBounds (450, 80, 135, 210);  
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
					if(jl_yn.getText().equals("No"))
					   jb_choose.setEnabled(false);
				   else  
						jb_choose.setEnabled(true);
            }
				if(e.getSource()==jcb_mat)
				{
				  	String mat = ""+jcb_mat.getSelectedItem().toString();		  
					
					if(mat.equals("CSK vs RCB"))
					{
						frame.setVisible(false);
						jp2.remove(jl_gilly);
						jp2.add(jl_cskanim);
						jp2.remove(jl_yuvi);
						jp2.add(jl_rcbanim);
						frame.setVisible(true);
					}
					else if(mat.equals("RR vs KXIP"))
					{
						frame.setVisible(false);
						jp2.remove(jl_gilly);
						jp2.add(jl_rranim);
						jp2.remove(jl_yuvi);
						jp2.add(jl_kpanim);
						frame.setVisible(true);
				   }
					mat="";
					jb_chkav.setEnabled(true);
					jcb_mat.setEnabled(false);
				}		
		      
				if(e.getSource()==jb_booknow)
				{
				   
					frame.setVisible(false);
				   c1.bookNow();
					frame.remove(jp2);
					if(jp1!=null)
					   frame.remove(jp1);
					if(jp3!=null)
					   frame.remove(jp3);		
					
					c1.jt_cvv.setText("");
					c1.jt_cc1.setText("");
					c1.jt_cc2.setText("");
					c1.jt_cc3.setText("");
					c1.jt_cc4.setText("");
					c1.jt_name.setText("");
					c1.jt_price.setText(""+price);
					c1.jt_date.setText(""+date);
				
					frame.add(c1.jp_card);
					frame.setVisible(true);
					
				}
				
				if(e.getSource()==jb_choose)
				{
					b.i=0;
					b.buildButton();
					
					b.confirm();
					insertIntoTable();
					if(jcb_stand.getSelectedItem().equals("CELEBRITY"))
					   b.jp_button.add(b.jl_pitch_c);
				   
               if(jcb_stand.getSelectedItem().equals("STANDC"))
					 	b.jp_button.add(b.jl_pitch_s);
				   
					if(jcb_stand.getSelectedItem().equals("ENTERTAINMENT"))
					   b.jp_button.add(b.jl_pitch_v);
				  
					if(jcb_stand.getSelectedItem().equals("PAVILION"))
					   b.jp_button.add(b.jl_pitch_e);
				}	
	
				if(e.getSource()==jt_tic)
				{
				   double p=0,sa = 2000,sb=1000,pav=10000,ter=30000;  
					if(jcb_stand.getSelectedItem().toString().equals("STANDC"))
					   p=sa;
					if(jcb_stand.getSelectedItem().toString().equals("ENTERTAINMENT"))
					   p=sb;
					if(jcb_stand.getSelectedItem().toString().equals("PAVILION"))
					   p=pav;
					if(jcb_stand.getSelectedItem().toString().equals("CELEBRITY"))
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
					  jt_tic.setText("");
					  b.i = 0;
					  jt_price.setText("");
					  jcb_stand.setSelectedItem("");
					  jcb_stand.setEnabled(false);
					  jcb_mat.setSelectedItem("--- vs ---");
					  jl_yn.setText("");
					  jcb_mat.setEnabled(false);
					  jcb_dd.setSelectedItem("----");
					  jcb_mm.setSelectedItem("-----");
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
			     if(e.getSource()==c1.jcb_cardtype)
				  {
				     if(!c1.validateCardType())
					  {
					     JOptionPane.showMessageDialog(null,"Please select a card type");
					  }
					  else
					     valcardtype=true;
				  }
				  if(e.getSource()==c1.jt_cc3)
				  {
				     if(!c1.validatecc())
					  {
					     JOptionPane.showMessageDialog(null,"Please enter a validate Credit Card number");
					  }
					  else
					     valcc=true;
				  }
				  if(e.getSource()==c1.jt_cvv)
				  {
				     if(!c1.validatecvv())
					  {
					     JOptionPane.showMessageDialog(null,"Please enter 3 digits");
					  }
					  else
					     valcvv=true;
				  }
				  if(e.getSource()==c1.jcb_date)
				  {
				     if(!c1.validateExp())
					  {
					     JOptionPane.showMessageDialog(null,"Please select an expiry date");
					  }
					  else
					     valexp=true;
				  }
				  if(e.getSource()==c1.jt_name)
				  {
				     if(!c1.validateName())
					  {
					     JOptionPane.showMessageDialog(null,"Please enter a valid name");
					  }
					  else
					     valname=true;
				  }  	  
			 }
		 };
		 	 	  	  
		 c1.jcb_cardtype.addActionListener(al);
       c1.jt_cc3.addActionListener(al);
		 c1.jt_cvv.addActionListener(al);
		 c1.jcb_date.addActionListener(al);
		 c1.jt_name.addActionListener(al);
		 
		  
		 ActionListener al_pay = new ActionListener()
		 {
		    public void actionPerformed(ActionEvent e)
			 {
			    isValidate=true;  
		 
				 if(isValidate){  
				 	frame.setVisible(false);
				 	frame.remove(c1.jp_card);
				 	frame.add(s.jp_sucpay);
				 	s.successPay();
				 	frame.setVisible(true);
				 }
				 else
				 {
				    frame.setVisible(false);
					 frame.remove(c1.jp_card);
				
				    frame.add(f.jp_fail);
					 f.failure();
		           
					 frame.setVisible(true);
			    }
		    }
	    };
		 c1.jb_pay.addActionListener(al_pay);		 
		 
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
				    if(jcb_mm.getSelectedIndex()!=0)  
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
		  jb_ko = new JButton (new ImageIcon("Copy of kochi_ipl_team_logo.jpg"));
        jb_pu = new JButton (new ImageIcon("punewar.jpg"));
        jb_abtus = new JButton ("Cancellation");
        jl_todmat = new JLabel ("Today's matches:");
        jcb_todmat = new JComboBox (jcb_todmatItems);
        jl_date = new JLabel ("Date: 21-3-2010");
        jl_bytn = new JLabel ("Book Your Tickets Now!!");
        jb_sb = new JButton ("Start Booking");
        jb_bb = new JButton ("Bulk Booking");
        jl_dlficon = new JLabel (new ImageIcon("ipl_logo.gif"));
        jl_bcci = new JLabel (new ImageIcon("BCCI_logo.gif"));
        jbtn_home = new JButton("Back");
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
        
        jp1.add (jl_todmat);
        jp1.add (jcb_todmat);
        jp1.add (jl_date);
        jp1.add (jl_bytn);
        jp1.add (jb_sb);
       
        jp1.add (jl_dlficon);
        jp1.add (jl_bcci);
		  jp1.add (jb_ko);
        jp1.add (jb_pu);

        //set component bounds (only needed by Absolute Positioning)
        frame.setJMenuBar(jmb);
        jl_adv.setBounds (0, 25, 635, 50);
        jb_rr.setBounds (0, 80, 55, 65);
        jb_rcb.setBounds (60, 80, 60, 65);
        jb_csk.setBounds (125, 80, 60, 65);
        jb_mi.setBounds (190, 80, 60, 65);
        jb_kkr.setBounds (255, 80, 60, 65);
        jb_kp.setBounds (320, 80, 60, 65);
        jb_dd.setBounds (385, 80, 60, 65);
        jb_dc.setBounds (450, 80, 60, 65);
        jb_abtus.setBounds (0, 330, 105, 30);
        jl_todmat.setBounds (140, 185, 110, 25);
        jcb_todmat.setBounds (255, 185, 100, 25);
        jl_date.setBounds (380, 185, 110, 25);
        jl_bytn.setBounds (245, 400, 140, 30);
        jb_sb.setBounds (140, 215, 330, 25);
        jb_bb.setBounds (505, 335, 125, 25);
        jl_dlficon.setBounds (325, 280, 115, 85);
        jl_bcci.setBounds (170, 280, 115, 85);
        jb_ko.setBounds (515, 80, 60, 65);
        jb_pu.setBounds (580, 80, 55, 65);

		  
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
				  if(e.getSource()==jb_ko)
				  {
				     ko mi_obj = new ko();
				  }
				  if(e.getSource()==jb_pu)
				  {
				     pu mi_obj = new pu();
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
		  jb_ko.addActionListener(al); 
		  jb_pu.addActionListener(al); 
	 }
	
	 void setUpDB()
     {
      
        String URL = "jdbc:mysql://localhost:3306/ipl";
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection(URL,"root","");
           //if (con.isClosed()) JOptionPane.showMessageDialog(null, "CONNECTION FAILED...");
           //else JOptionPane.showMessageDialog(null, "CONNECTION SUCCESSFUL");
        }
	  
	    catch(Exception e){
           JOptionPane.showMessageDialog(null,"*** MySQL CONNECTION ERROR! *** " + e.getMessage());
           //System.exit(0);
        }
    }
	 
	 void insertIntoTable()
	 {
	    setUpDB();
		 try
		 {
		     S1 = con.createStatement();
			  String q = "SELECT SEATNO FROM " +jcb_stand.getSelectedItem().toString()+ " WHERE STAT IS NOT NULL";
	        rs1 = S1.executeQuery(q+"");
			  
			  for(int i=0;rs1.next();i++)
			  {
			     a[i] = Integer.parseInt(rs1.getString("SEATNO"));
				  if(a[i]==1)
				 	{  
				  		b.jtb_1.setText("N");
				  		b.jtb_1.setEnabled(false);
			      }
					if(a[i]==2)
				 	{
			         b.jtb_2.setText("N");
				  		b.jtb_2.setEnabled(false);
			      }
					if(a[i]==3)
				 	{
					   b.jtb_3.setText("N");
				  		b.jtb_3.setEnabled(false);
			      }
					if(a[i]==4)
				 	{
			         b.jtb_4.setText("N");
				  		b.jtb_4.setEnabled(false);
			      }
					if(a[i]==5)
				 	{
			         b.jtb_5.setText("N");
				  		b.jtb_5.setEnabled(false);
			      }
					if(a[i]==6)
				 	{
			         b.jtb_6.setText("N");
				  		b.jtb_6.setEnabled(false);
			      }
					if(a[i]==7)
				 	{ 
					   b.jtb_7.setText("N");
				  		b.jtb_7.setEnabled(false);
			      }
					if(a[i]==8)
				 	{
			         b.jtb_8.setText("N");
				  		b.jtb_8.setEnabled(false);
			      }
					if(a[i]==9)
				 	{
			         b.jtb_9.setText("N");
				  		b.jtb_9.setEnabled(false);
			      }
					if(a[i]==10)
				 	{  
				  		b.jtb_10.setText("N");
				  		b.jtb_10.setEnabled(false);
			      }
					if(a[i]==11)
				 	{
			         b.jtb_11.setText("N");
				  		b.jtb_11.setEnabled(false);
			      }
					if(a[i]==12)
				 	{
			         b.jtb_12.setText("N");
				  		b.jtb_12.setEnabled(false);
			      }
					if(a[i]==13)
				 	{
			         b.jtb_13.setText("N");
				  		b.jtb_13.setEnabled(false);
			      }
					if(a[i]==14)
				 	{
			         b.jtb_14.setText("N");
				  		b.jtb_14.setEnabled(false);
			      }
					if(a[i]==15)
				 	{
			         b.jtb_15.setText("N");
				  		b.jtb_15.setEnabled(false);
			      }
					if(a[i]==16)
				 	{
			         b.jtb_16.setText("N");
				  		b.jtb_16.setEnabled(false);
			      }
					if(a[i]==17)
				 	{
			         b.jtb_17.setText("N");
				  		b.jtb_17.setEnabled(false);
			      }
					if(a[i]==18)
				 	{
			         b.jtb_18.setText("N");
				  		b.jtb_18.setEnabled(false);
			      }
					if(a[i]==19)
				 	{
			        b.jtb_19.setText("N");
				  		b.jtb_19.setEnabled(false);
			      }
					if(a[i]==20)
				 	{
			         b.jtb_20.setText("N");
				  		b.jtb_20.setEnabled(false);
			      }
					if(a[i]==21)
				 	{
			         b.jtb_21.setText("N");
				  		b.jtb_21.setEnabled(false);
			      }
					if(a[i]==22)
				 	{
			         b.jtb_22.setText("N");
				  		b.jtb_22.setEnabled(false);
			      }
					if(a[i]==23)
				 	{
			         b.jtb_23.setText("N");
				  		b.jtb_23.setEnabled(false);
			      }
					if(a[i]==24)
				 	{
			         b.jtb_24.setText("N");
				  		b.jtb_24.setEnabled(false);
			      }
					if(a[i]==25)
				 	{
			         b.jtb_25.setText("N");
				  		b.jtb_25.setEnabled(false);
			      }
					if(a[i]==26)
				 	{
			         b.jtb_26.setText("N");
				  		b.jtb_26.setEnabled(false);
			      }
					if(a[i]==27)
				 	{
			         b.jtb_27.setText("N");
				  		b.jtb_27.setEnabled(false);
			      }
					if(a[i]==28)
				 	{
			         b.jtb_28.setText("N");
				  		b.jtb_28.setEnabled(false);
			      }
					if(a[i]==29)
				 	{
			         b.jtb_29.setText("N");
				  		b.jtb_29.setEnabled(false);
			      }
					if(a[i]==30)
				 	{
			         b.jtb_30.setText("N");
				  		b.jtb_30.setEnabled(false);
			      }
				}
			  
		 }
	    catch(Exception error)
	    {
			JOptionPane.showMessageDialog(null,"Error"+error.getMessage());
       }
	 }

	 String execQuery()    
	 {
        setUpDB();
		  String s ="" ;
		  try{
			  S = con.createStatement();
			  String query = "SELECT * FROM " +jcb_stand.getSelectedItem().toString()+ " WHERE STAT IS NULL";
			  
			  rs = S.executeQuery(query+"");
			  
			
			  int i;
			  for(i=0;rs.next();i++);
			  if(i==0)
			    s="No";
           else
			    s=" Yes";
         
        }
	    catch(Exception error)
	    {
			JOptionPane.showMessageDialog(null,""+error.getMessage());
       }
		return s;
    }	  

    //THE PROGRAM BEGINS HERE
    public static void main (String[] args) 
	 {
       new IPL();
    }
}

class rr{
    public JLabel sp_rr;
    public JTextArea jta_rr;
    public JTextArea jta_suprr;
    public JTextArea jta_rrsq;
	 public JFrame jf_rr;
	 public JPanel jp_rr;
	 public JScrollPane rr_det, rr_sup, rr_sq;


    public rr()
	  {
	     jf_rr = new JFrame("Rajasthan Royals");
		  jf_rr.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_rr = new JPanel();
		  jp_rr.setBackground(Color.blue);    
		  jf_rr.add(jp_rr);    

        buildrr();
		  jf_rr.pack();    
		  jf_rr.setVisible (true);

        
    }
	 
	 void buildrr()
	 {
	    //construct components
        sp_rr = new JLabel (new ImageIcon("RR_teamLogo1.gif"));
        jta_rr = new JTextArea (5, 5);
        jta_suprr = new JTextArea (5, 5);
        jta_rrsq = new JTextArea (5, 5);
		  
		   jta_rr.setText("The team from India's princely\n" +  " state didn't quite command\n" +
		  "the kind of respect and interest\n" +  " that befits royalty.\n"+
		  " So when the Rajasthan Royals,\n" +
		  "led by Shane Warne, swept the opposition away \n" +  "and took home the \n" +  "first-ever IPL title,\n" +
		   "everyone was surprised.\n" +  " Sohail Tanvir and Shane Watson\n"+
			" were the other big names that" +
	      "powered the side,\n" +  " but it was players like\n"+" Swapnil Asnodkar and \n" +  "Ravindra Jadeja\n" +  " that shone.\n" +
         "IPL 2009 got underway with \n" +  "Bollywood diva Shilpa Shetty\n"+ " and her then fiance Raj Kundra \n" + 
		 "also joining the franchise as co-owners.\n" +  "Kaif was left out and Watson was\n" +  " unavailable.\n" +
		  "Tyron Henderson, who was roped \n" +  "in after a tug-of-war with \n"+ "KXIP, played just one game.\n" +
		   "Yusuf Pathan starred in a win over rr\n"+" in the only Super Over of the tournament.\n" +
		    "At the 2010 auction, Rajasthan bought\n"+" two Australian players - Adam Voges \n" +  "and in \n" +
			"a surprise move, the\n" +  " out of action Damien Martyn.\n" +  " The team had bought out \n" +
			"Tyron Henderson,\n" +  " Robert Quiney and \n" +  "Mohd Kaif prior to the auction"); 
        jta_suprr.setText("              Support Staff:\n"+

                          "              Darren Berry\n"+ 
                          "              Sushil Tulaskar \n"+ 
                          "              Monty Desai \n"+ 
                          "              Saurabh Walkar "); 
								  									 
        jta_rrsq.setText("Squad:\n"+"Shane Warne\n"+ "Shane Watson (both retained)\n"+ "Rahul Dravid\n"+ "Pankaj Singh\n"+ "Ross Taylor\n"+ "Johan Botha\n"+ "Paul Collingwood\n"+ "Shaun Tait.");

        //adjust size and set layout
        jp_rr.setPreferredSize (new Dimension (560, 435));
        jp_rr.setLayout (null);
        
		  rr_det = new JScrollPane(jta_rr);
		  rr_sup = new JScrollPane(jta_suprr);
		  rr_sq = new JScrollPane(jta_rrsq);
		  
        //add components
        jp_rr.add (sp_rr);
        jp_rr.add (rr_det);
        jp_rr.add (rr_sup);
        jp_rr.add (rr_sq);

        //set component bounds (only needed by Absolute Positioning)
        sp_rr.setBounds (105, 40, 190, 180);
        rr_det.setBounds (45, 235, 315, 155);
        rr_sup.setBounds (380, 235, 130, 155);
        rr_sq.setBounds (380, 40, 130, 185);
		  
		  WindowAdapter wl = new WindowAdapter()
		  {
			  public void windowClosing(WindowEvent we)
			  {
			     jf_rr.dispose();
           }
        };
		  jf_rr.addWindowListener(wl);

	  }	  
}

class rcb{
    public JLabel sp_rcb;
    public JTextArea jta_rcb;
    public JTextArea jta_suprcb;
    public JTextArea jta_rcbsq;
	 public JFrame jf_rcb;
	 public JPanel jp_rcb;
	 public JScrollPane rcb_det, rcb_sup, rcb_sq;


    public rcb()
	  {
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
	    //construct components
        sp_rcb = new JLabel (new ImageIcon("rcb_teamLogo.jpg"));
        jta_rcb = new JTextArea (5, 5);
        jta_suprcb = new JTextArea (5, 5);
        jta_rcbsq = new JTextArea (5, 5);
		  
		  jta_rcb.setText("Royal Challengers Bangalore,\n" +  " like Deccan Chargers,\n" +
		   "had ended up at the bottom of the\n"+
		   "table in the inaugural edition of IPL.\n" +
		    "Both teams ended up meeting in the final\n" + 
			" of IPL 2009 in South Africa. Anil Kumble\n" +
			"-led RCB with great heart, but was unable\n" + 
			" to win the title. Kumble's most impressive\n" +
			 "virtues as a cricketer - grit and \n" + 
			 "determination - came to the fore in South Africa.\n" +
		    " The side lost four of their first five\n" + 
			" matches in IPL 2009, but made a startling \n" +
			 "comeback to make it to the semi-finals.\n" +
             "The team qualified for the CLT20 but could\n" +
			  " not qualify for the knock-out stage.\n" +
			  "The team has undergone a sea-change in terms\n"+
			  " of personnel and support staff\n" +
			   "from Season 2008 with new coach Ray\n" + 
			   " Jennings making a huge impact.\n" +
			   " Former captain Kevin Pietersen's \n" + 
			   "participation is still debatable owing to\n" +
				 "an extended injury break. Their only\n" + 
				 " buy at the 2010 auction was\n" +
				 " Englishman Eoin Morgan, but they also\n" + 
				 " have in their ranks India's under-19 captain\n" +
				  " Ashok Menaria while Nathan Bracken has been\n" +  " bought out. ");
				    
        jta_suprcb.setText("Support Staff:\n"+

                            "Ray Jennings \n"+
							"Evan Speechley  \n"+ 
                            "Sanath Kumar \n"+ 
                            "Ramesh Man\n"+ 
                            "Avinash Vaidya ");
									 
        jta_rcbsq.setText("Squad:\n"+"Virat Kohli (retained)\n"+"Zaheer Khan\n"+"Saurabh Tiwary\n"+"Cheteshwar Pujara\n"+"Abhimanyu Mithun\n"+"Mohammed Kaif\n"+"T Dilshan\n"+"A B De Villiers\n"+"Daniel Vettori\n"+"Dirk Nannes\n"+"Charl Langeveldt\n"+"Luke Pomersbach\n"+"Johan van der Wath\n"+"Rile Rossouw\n"+"Nuwan Pradeep\n"+"Jonathan Vandlar.");

        //adjust size and set layout
        jp_rcb.setPreferredSize (new Dimension (560, 435));
        jp_rcb.setLayout (null);
        
		  rcb_det = new JScrollPane(jta_rcb);
		  rcb_sup = new JScrollPane(jta_suprcb);
		  rcb_sq = new JScrollPane(jta_rcbsq);
		  
        //add components
        jp_rcb.add (sp_rcb);
        jp_rcb.add (rcb_det);
        jp_rcb.add (rcb_sup);
        jp_rcb.add (rcb_sq);

        //set component bounds (only needed by Absolute Positioning)
        sp_rcb.setBounds (105, 40, 190, 180);
        rcb_det.setBounds (45, 235, 315, 155);
        rcb_sup.setBounds (380, 235, 130, 155);
        rcb_sq.setBounds (380, 40, 130, 185);
		  
		  WindowAdapter wl = new WindowAdapter()
		  {
			  public void windowClosing(WindowEvent we)
			  {
			     jf_rcb.dispose();
           }
        };
		  jf_rcb.addWindowListener(wl);

	  }	  
}


class mi{
    public JLabel sp_mi;
    public JTextArea jta_mi;
    public JTextArea jta_supmi;
    public JTextArea jta_misq;
	 public JFrame jf_mi;
	 public JPanel jp_mi;
	 public JScrollPane mi_det, mi_sup, mi_sq;


    public mi()
	  {
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
	    //construct components
        sp_mi = new JLabel (new ImageIcon("mi_teamLogo.jpg"));
        jta_mi = new JTextArea (5, 5);
        jta_supmi = new JTextArea (5, 5);
        jta_misq = new JTextArea (5, 5);
		  
		  jta_mi.setText("Sachin Tendulkar was named captain and icon player\n" + 
		  "of Mumbai Indians, the highest-priced side,\n" +
		   " owned by the Reliance Group\n" +
		   "of Companies, ahead of the inaugural IPL.\n" +
		     "The team had some turbo-charged \n" +
		   "players like Jayasuriya, Shaun Pollock,\n" + 
		    "Harbhajan and Malinga. The side,\n" +
		    "however, failed to deliver on its\n" + 
			 "promise in the inaugural competition,\n" +
			 "despite some memorable performances like \n" +
			  " that of the young all-rounder Abhishek Nayar.\n" +
              "The second season opened with two new names\n" + 
             "for the side - Zaheer Khan,\n" +
			   " who was brought in from RCB,\n" +
			  "and batsman Shikhar Dhawan from\n" + 
			   " Daredevils. JP Duminy, the\n" +
			   "South African powerhouse,\n" + 
			    " was roped in, fresh from his success\n" +
			    "in Australia. But the side could\n" + 
				  "not quite make it even this time\n" +
				 "round, bowing out of the \n" +  "competition before the semis.\n" +
				  "Kyle Mills and Mohd. Ashraful have been bought out.\n" +
				   "At the 2010 auction, they bagged\n" +  " the biggest player of them all -\n" +
				    "Kieron Pollard - at USD 750,000\n" +  " after winning the silent tie-breaker.\n" +
					 "Harshal Patel from the U-19 side has\n" +  " also found a place in the side.\n" +
					   "and batsman Shikhar Dhawan from\n" + "  Daredevils. JP Duminy, the South African\n" +
					    "powerhouse, was roped in, fresh from\n" +  " his success in Australia.\n" +
						" But the side could not quite make\n" +  " it even this time round, bowing \n" +
						 "out of the competition before the semis.\n" +  " Kyle Mills and Mohd.\n" +
						  "Ashraful have been bought out.\n" +   "At the 2010 auction, they bagged \n" +
						  "the biggest player of them all -\n" +  " Kieron Pollard - at USD 750,000\n" +
						   "after winning the silent tie-breaker.\n" + "Harshal Patel from the U-19 side\n" +
						    "has also found a place in the side."); 
        jta_supmi.setText("Support Staff:\n"+

                            "Robin Singh \n"+ 
                            "Shaun Pollock \n"+ 
                            "Dr. Nitin Pate\n"+ 
                            "Donald Shugg ");

        jta_misq.setText("Squad:\n"+"Sachin Tendulkar\n"+"Harbhajan Singh\n"+"Kieron Pollard\n"+"Lasith Malinga\n"+"Rohit Sharma\n"+"Munaf Patel\n"+"Andrew Symonds\n"+"David Jacobs\n"+"James Franklin\n"+"Clint McKay\n"+"Moises Henriques\n"+"Aiden Bllzzard");

        //adjust size and set layout
        jp_mi.setPreferredSize (new Dimension (560, 435));
        jp_mi.setLayout (null);
        
		  mi_det = new JScrollPane(jta_mi);
		  mi_sup = new JScrollPane(jta_supmi);
		  mi_sq = new JScrollPane(jta_misq);
		  
        //add components
        jp_mi.add (sp_mi);
        jp_mi.add (mi_det);
        jp_mi.add (mi_sup);
        jp_mi.add (mi_sq);

        //set component bounds (only needed by Absolute Positioning)
        sp_mi.setBounds (105, 40, 190, 180);
        mi_det.setBounds (45, 235, 315, 155);
        mi_sup.setBounds (380, 235, 130, 155);
        mi_sq.setBounds (380, 40, 130, 185);
		  
		  WindowAdapter wl = new WindowAdapter()
		  {
			  public void windowClosing(WindowEvent we)
			  {
			     jf_mi.dispose();
           }
        };
		  jf_mi.addWindowListener(wl);

	  }	  
}

class kp{
    public JLabel sp_kp;
    public JTextArea jta_kp;
    public JTextArea jta_supkp;
    public JTextArea jta_kpsq;
	 public JFrame jf_kp;
	 public JPanel jp_kp;
	 public JScrollPane kp_det, kp_sup, kp_sq;


    public kp()
	  {
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
	    //construct components
        sp_kp = new JLabel (new ImageIcon("kp_teamLogo.jpg"));
        jta_kp = new JTextArea (5, 5);
        jta_supkp = new JTextArea (5, 5);
        jta_kpsq = new JTextArea (5, 5);
		  
		  jta_kp.setText("Kings XI Punjab have an \n" +  "aura of glamour and verve\n" + 
		  "around them thanks to owners Preity Zinta \n" +  "and Ness Wadia. They boast\n" +
		   "the services of the flamboyant Yuvraj\n" +  " Singh and dependable Sangakkara;\n" +
		    "the destructive Irfan Pathan and \n" +  "experienced Jayawardene. So when the\n" +
		    "first edition of the IPL kicked off,\n" +  " the team from India's lion-hearted\n" +
			 "state were looked on with awe and admiration.\n" +  " But the side could not\n" +
			 "progress beyond the semi-final stage.\n" +
             "In 2009, the spectacular South African\n" +  " setting did little to embellish \n" +
			 "the team's chances - they bowed out\n" +  " before the semis, depleted by the absence\n" +
			  "of Brett Lee and Sreesanth at various stages.\n" +  " Left-arm paceman Yusuf \n" +
			  "Abdulla was one of the finds of the \n" +  "tournament and he has been retained\n" +
			   "for the third edition. Luke Pomersbach \n" +  "and Nuwan Kulasekara were bought\n" +
			    "our ahead of the 2010 auction while\n" +  " they picked former India batsman \n" +
				"Mohammed Kaif who is expected to recreate\n" +  " the magic with Yuvraj Singh in the\n" +
				 "Punjab side under new captain Kumar Sangakkara.");
		   
        jta_supkp.setText("Support Staff:\n"+
                           " Tom Moody  \n"+
                           "Patrick Farhart \n"+ 
                           "Trevor Penney\n"+ 
                            " Alan Campbell\n"+ 
                             "Colonel S. K. Mehta  \n"+ 
                             "Bhupinder Singh \n"+ 
                              "David O Nosworthy");
  
        
		  jta_kpsq.setText("Squad:\n"+"Dinesh Karthik\n"+"Abhishek Nayar\n"+"Praveen Kumar\n"+"Piyush Chawla\n"+"Adam Gilchrist\n"+"Shaun Marsh\n"+"David Hussey\n"+"Stuart Broad\n"+"Ryan Harris\n"+"Dimitri Mascarenhas\n"+"Nathan Rimmington.");

        //adjust size and set layout
        jp_kp.setPreferredSize (new Dimension (560, 435));
        jp_kp.setLayout (null);
        
		  kp_det = new JScrollPane(jta_kp);
		  kp_sup = new JScrollPane(jta_supkp);
		  kp_sq = new JScrollPane(jta_kpsq);
		  
        //add components
        jp_kp.add (sp_kp);
        jp_kp.add (kp_det);
        jp_kp.add (kp_sup);
        jp_kp.add (kp_sq);

        //set component bounds (only needed by Absolute Positioning)
        sp_kp.setBounds (105, 40, 190, 180);
        kp_det.setBounds (45, 235, 315, 155);
        kp_sup.setBounds (380, 235, 130, 155);
        kp_sq.setBounds (380, 40, 130, 185);
		  
		  WindowAdapter wl = new WindowAdapter()
		  {
			  public void windowClosing(WindowEvent we)
			  {
			     jf_kp.dispose();
           }
        };
		  jf_kp.addWindowListener(wl);

	  }	  
}

class kkr{
    public JLabel sp_kkr;
    public JTextArea jta_kkr;
    public JTextArea jta_supkkr;
    public JTextArea jta_kkrsq;
	 public JFrame jf_kkr;
	 public JPanel jp_kkr;
	 public JScrollPane kkr_det, kkr_sup, kkr_sq;


    public kkr()
	  {
	     jf_kkr = new JFrame("Kolkatta Knight Riders");
		  jf_kkr.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_kkr = new JPanel();
		  jp_kkr.setBackground(Color.black);    
		  jf_kkr.add(jp_kkr);    

        buildkkr();
		  jf_kkr.pack();    
		  jf_kkr.setVisible (true);

        
    }
	 
	 void buildkkr()
	 {
	    //construct components
        sp_kkr = new JLabel (new ImageIcon("kkr_teamLogo.jpg"));
        jta_kkr = new JTextArea (5, 5);
        jta_supkkr = new JTextArea (5, 5);
        jta_kkrsq = new JTextArea (5, 5);
		  
		  jta_kkr.setText("When Shahrukh Khan - India's biggest\n" +  " movie star - co-purchased the Kolkata Knight Riders\n"+
		  "franchise, the nation couldn't help \n" +  "but feel an affinity with the side.\n" +
		   "And when Brendon McCullum stormed \n" +  "into the competition with a whirlwind 158*,\n"+ 
			"it drew a collective gasp of admiration \n" +  "from fans of the sport across the world.\n" + 
			"If Sourav helmed the side, then there was\n" +  " John Buchanan who powered the think-tank.\n"+ 
			"But the team could not\n" +  " live up to expectations.\n " +

        "In the second season, suggestions of a \n" +  "multiple-captain approach drew a lot of debate \n"+
		  "but as it turned out, McCullum ended up \n" +  "leading the side throughout. But the side's \n"+
		  "prospects did not improve. \n" +  "KKR ended the tournament pretty close \n" +  "to the bottom of the table.\n"+
		  "Ricky Ponting and Morne van Wyk \n" +  "have been bought out ahead of\n" +  " Season 2010, while Shane Bond\n" +
		  "has been bought at USD 750,000.\n" +  " U-19 player Harpreet Singh has also\n" +  " been picked for the\n"+
		  " tournament."); 
        jta_supkkr.setText("Support Staff:\n"+

                            "Dave Whatmore\n"+ 
                            "Wasim Akram\n"+ 
                            "Andrew Leipus\n"+ 
                            "Adrian Le");         
		  jta_kkrsq.setText("Squad:\n"+"Gautam Gambhir\n"+"Yusuf Pathan\n"+"Manoj Tiwari\n"+"L Balaji\n"+"Jaidev Unadkat\n"+"Jacques Kallis\n"+"Brad Haddin\n"+"Shakib Al Hasasn\n"+"Brett Lee\n"+"Eoin Morgan\n"+"Ryan ten Doeschate\n"+"James Pattinson");

        //adjust size and set layout
        jp_kkr.setPreferredSize (new Dimension (560, 435));
        jp_kkr.setLayout (null);
        
		  kkr_det = new JScrollPane(jta_kkr);
		  kkr_sup = new JScrollPane(jta_supkkr);
		  kkr_sq = new JScrollPane(jta_kkrsq);
		  
        //add components
        jp_kkr.add (sp_kkr);
        jp_kkr.add (kkr_det);
        jp_kkr.add (kkr_sup);
        jp_kkr.add (kkr_sq);

        //set component bounds (only needed by Absolute Positioning)
        sp_kkr.setBounds (105, 40, 190, 180);
        kkr_det.setBounds (45, 235, 315, 155);
        kkr_sup.setBounds (380, 235, 130, 155);
        kkr_sq.setBounds (380, 40, 130, 185);
		  
		  WindowAdapter wl = new WindowAdapter()
		  {
			  public void windowClosing(WindowEvent we)
			  {
			     jf_kkr.dispose();
           }
        };
		  jf_kkr.addWindowListener(wl);

	  }	  
}
class pu{
    public JLabel sp_pu;
    public JTextArea jta_pu;
    public JTextArea jta_suppu;
    public JTextArea jta_pusq;
	 public JFrame jf_pu;
	 public JPanel jp_pu;
	 public JScrollPane pu_det, pu_sup, pu_sq;


    public pu()
	  {
	     jf_pu = new JFrame("Sahara Warriors");
		  jf_pu.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_pu = new JPanel();
		  jp_pu.setBackground(Color.red);    
		  jf_pu.add(jp_pu);    

        buildpu();
		  jf_pu.pack();    
		  jf_pu.setVisible (true);

        
    }
	 
	 void buildpu()
	 {
	    //construct components
        sp_pu = new JLabel (new ImageIcon("punewar1.jpg"));
        jta_pu = new JTextArea (5, 5);
        jta_suppu = new JTextArea (5, 5);
        jta_pusq = new JTextArea (5, 5);
		  
		   jta_pu.setText("Subrata Roy of Sahara Group acquired\n"+
		   "the Pune IPL team at the 2010 franchise auction for\n"+
		   "two new Indian Premier League teams. The bid was lost \n"+
		   "by The Videocon Group for the Pune IPL Team and for Rs 1,70\n"+
		   "2 crore Sahara group bought it on March 22, 2010. It was named\n"+
		   "as Sahara Pune Warriors at a function held at Pune on 24 April 2010.\n"+
		   "Sahara group declared its Pune IPL team name and revealed its logo at\n"+
		   "S.P. College ground in which Bollywood actress Urmila Matondkar and \n"+
		   "producer-director Mahesh Manjerekar began with a cultural programme. \n"+
		   "In the MCA Pune International Cricket Centre ground situated at Gahunje \n"+
		   "almost 25 km from Pune, the new IPL Team Sahara Pune Warriors will play \n"+
		   "their home matches."); 
        
		jta_suppu.setText(" Support Staff:\n"+
                             "Praveen Amre named Assistant\n"+" Coach of Sahara \n"+"Pune Warriors"); 
								  									 
        jta_pusq.setText("Squad:\n"+"Yuvraj Singh\n"+"Robin Uthappa\n"+"Ashish Nehra\n"+"Murali Kartik\n"+"Graeme Smith\n"+"Tim Paine\n"+"Agelo Mathews\n"+"Nathan McCullum\n"+"Callum Ferguson\n"+"Wayne Parnell\n"+"Mitchell Marsh\n"+"Jerome Taylor\n"+"Alfonson Thomas\n"+"Jesse Ryder");

        //adjust size and set layout
        jp_pu.setPreferredSize (new Dimension (560, 435));
        jp_pu.setLayout (null);
        
		  pu_det = new JScrollPane(jta_pu);
		  pu_sup = new JScrollPane(jta_suppu);
		  pu_sq = new JScrollPane(jta_pusq);
		  
        //add components
        jp_pu.add (sp_pu);
        jp_pu.add (pu_det);
        jp_pu.add (pu_sup);
        jp_pu.add (pu_sq);

        //set component bounds (only needed by Absolute Positioning)
        sp_pu.setBounds (105, 40, 190, 180);
        pu_det.setBounds (45, 235, 315, 155);
        pu_sup.setBounds (380, 235, 130, 155);
        pu_sq.setBounds (380, 40, 130, 185);
		  
		  WindowAdapter wl = new WindowAdapter()
		  {
			  public void windowClosing(WindowEvent we)
			  {
			     jf_pu.dispose();
           }
        };
		  jf_pu.addWindowListener(wl);

	  }	  
}

class ko{
    public JLabel sp_ko;
    public JTextArea jta_ko;
    public JTextArea jta_supko;
    public JTextArea jta_kosq;
	 public JFrame jf_ko;
	 public JPanel jp_ko;
	 public JScrollPane ko_det, ko_sup, ko_sq;


    public ko()
	  {
	     jf_ko = new JFrame("Kochi Bruisers");
		  jf_ko.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_ko = new JPanel();
		  jp_ko.setBackground(Color.orange);    
		  jf_ko.add(jp_ko);    

        buildko();
		  jf_ko.pack();    
		  jf_ko.setVisible (true);

        
    }
	 
	 void buildko()
	 {
	    //construct components
        sp_ko = new JLabel (new ImageIcon("kochi_ipl_team_logo.jpg"));
        jta_ko = new JTextArea (5, 5);
        jta_supko = new JTextArea (5, 5);
        jta_kosq = new JTextArea (5, 5);
		  
		  jta_ko.setText("For enlarging the IPL franchises,\n"+ 
		  "the auction was set on March 22, 2010 in which total 12\n"+ 
		  " teams participated including Ahmedabad, Rajkot, Cuttack, Kanpur,\n"+ 
		  " Vadodara, Kochi, Indore, Dharamsala, Pune, Nagpur, Gwalior and Vizag.\n"+ 
		  " From these 12 teams, Sahara Group was biding Pune team with highest cost \n"+ 
		  "of Rs.1,702 crore named as Sahara Pune Warriors alongside Rendezvous Sports\n"+ 
		  " World was on second big bid for the team Kochi at cost of Rs.1533.32 crores \n"+ 
		  "and it is Kochi IPL Team. Total purchase price of both new franchises is reportedly\n"+ 
		  " more than all original eight IPL franchises’ purchase price."); 
				  
        jta_supko.setText("Support Staff:\n"+
                           "Kochi Team yet to FINALISE\n"+" the Coaches and Staffs for\n"+" the season4\n"+
						    "Official coach :Geoff Lawson"); 

        jta_kosq.setText("Squad:\n" +
		                  "V V S Laxman\n"+"S Sreesanth\n"+"R P Singh\n"+"Parthiv Patel\n"+"Ravindra Jadeja\n"+"Ramesh Powar\n"+"R Vinay Kumar\n"+"Mahela Jayawardene\n"+"Brendon McCullum\n"+"Steven Smith\n"+"M Muralidharan\n"+"Brad Hodge\n"+"Tisara Perera\n"+"Stephen O'Keefe\n"+"Owais Shah\n"+"Michael Klinger\n"+"John Hastings");

        //adjust size and set layout
        jp_ko.setPreferredSize (new Dimension (560, 435));
        jp_ko.setLayout (null);
        
		  ko_det = new JScrollPane(jta_ko);
		  ko_sup = new JScrollPane(jta_supko);
		  ko_sq = new JScrollPane(jta_kosq);
		  
        //add components
        jp_ko.add (sp_ko);
        jp_ko.add (ko_det);
        jp_ko.add (ko_sup);
        jp_ko.add (ko_sq);

        //set component bounds (only needed by Absolute Positioning)
        sp_ko.setBounds (105, 40, 190, 180);
        ko_det.setBounds (45, 235, 315, 155);
        ko_sup.setBounds (380, 235, 130, 155);
        ko_sq.setBounds (380, 40, 130, 185);
		  
		  WindowAdapter wl = new WindowAdapter()
		  {
			  public void windowClosing(WindowEvent we)
			  {
			     jf_ko.dispose();
           }
        };
		  jf_ko.addWindowListener(wl);
	  }	  
}

class dd{
    public JLabel sp_dd;
    public JTextArea jta_dd;
    public JTextArea jta_supdd;
    public JTextArea jta_ddsq;
	 public JFrame jf_dd;
	 public JPanel jp_dd;
	 public JScrollPane dd_det, dd_sup, dd_sq;


    public dd()
	  {
	     jf_dd = new JFrame("Delhi Daredevils");
		  jf_dd.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_dd = new JPanel();
		  jp_dd.setBackground(Color.red);    
		  jf_dd.add(jp_dd);    

        builddd();
		  jf_dd.pack();    
		  jf_dd.setVisible (true);

        
    }
	 
	 void builddd()
	 {
	    //construct components
        sp_dd = new JLabel (new ImageIcon("dd_teamLogo.jpg"));
        jta_dd = new JTextArea (5, 5);
        jta_supdd = new JTextArea (5, 5);
        jta_ddsq = new JTextArea (5, 5);
		  
		 jta_dd.setText("Delhi Daredevils, owned by \n" +  "GMR Industries, have a number of\n" + 
		  "genuine match-winners in their squad.\n" +  " They boast of Virender Sehwag and Gautam\n" +
		  "Gambhir at the top of the order while\n" +  " wicketkeeper-batsman Dinesh Karthik beefs\n" +
		  "up the middle-order. Tillakaratne Dilshan,\n" +  " along with AB de Villiers, has been a\n" +
		  " revelation in this format. Veteran Daniel Vettori\n" +  " adds depth to the squad.\n" +
		  " Daredevils made it to the\n" +  " semis in the first season.\n" +
          "In the second season, they rode high on\n" +  " the strength of Dirk Nannes' pace, topped\n" +
		  " the league, but again, lost in the semis.\n" +  " Gambhir has now stepped in as captain after\n" +
		  "Sehwag decided to step down.\n" +  " Dehi have bought out Oz \n" +  "legend Glenn McGrath before the \n" +
		  "third season. They picked up Wayne Parnell,\n" +  " the South African left-arm pacer for a whopping\n" +
		  "USD 610,000 at the 2010 auction.\n" +  " They also included the U-19 player\n" +  " Manan Sharma in their\n" +
		  " ranks.");
		   
        jta_supdd.setText("Support Staff:\n"+
                           "Gregory Shipped\n"+ 
                            "Eric Simons\n"+ 
                            "Rajeev Kumar\n"+ 
                            "Trent Woodhill\n"+ 
                             "Rob Walters\n"+ 
                             "Justin Steer");           
		  jta_ddsq.setText("Squad:\n"+"Virender Sehwag (retained)\n"+"Irfan Pathan\n"+"Naman Ojha\n"+"Ajit Agarkar\n"+"Ashok Dinda\n"+"Umesh Yadav\n"+"Venugopal Rao\n"+"David Warner\n"+"James Hopes\n"+"Morne Morkel\n"+"Aaron Finch\n"+"Matthew Wade\n"+"Roelof van der Merve\n"+"Andrew McDonand\n"+"Travis Birt\n"+"Colin Ingram\n"+"Robert Frylinck.");

        //adjust size and set layout
        jp_dd.setPreferredSize (new Dimension (560, 435));
        jp_dd.setLayout (null);
        
		  dd_det = new JScrollPane(jta_dd);
		  dd_sup = new JScrollPane(jta_supdd);
		  dd_sq = new JScrollPane(jta_ddsq);
		  
        //add components
        jp_dd.add (sp_dd);
        jp_dd.add (dd_det);
        jp_dd.add (dd_sup);
        jp_dd.add (dd_sq);

        //set component bounds (only needed by Absolute Positioning)
        sp_dd.setBounds (105, 40, 190, 180);
        dd_det.setBounds (45, 235, 315, 155);
        dd_sup.setBounds (380, 235, 130, 155);
        dd_sq.setBounds (380, 40, 130, 185);
		  
		  WindowAdapter wl = new WindowAdapter()
		  {
			  public void windowClosing(WindowEvent we)
			  {
			     jf_dd.dispose();
           }
        };
		  jf_dd.addWindowListener(wl);

	  }	  
}

class dc{
    public JLabel sp_dc;
    public JTextArea jta_dc;
    public JTextArea jta_supdc;
    public JTextArea jta_dcsq;
	 public JFrame jf_dc;
	 public JPanel jp_dc;
	 public JScrollPane dc_det, dc_sup, dc_sq;


    public dc()
	  {
	     jf_dc = new JFrame("Deccan Chargers");
		  jf_dc.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_dc = new JPanel();
		  jp_dc.setBackground(Color.gray);    
		  jf_dc.add(jp_dc);    

        builddc();
		  jf_dc.pack();    
		  jf_dc.setVisible (true);

        
    }
	 
	 void builddc()
	 {
	    //construct components
        sp_dc = new JLabel (new ImageIcon("dc_teamLogo.jpg"));
        jta_dc = new JTextArea (5, 5);
        jta_supdc = new JTextArea (5, 5);
        jta_dcsq = new JTextArea (5, 5);
		  
		  jta_dc.setText("The reigning title-holders of\n" +  " the second edition of the IPL,\n" +
		  " Deccan Chargers had a fairytale second\n" +  " season. Plagued by poor performances,\n" +
		  " the team was placed at the bottom\n" +  " of the table at the end\n" +  " of IPL's opening season.\n" +	
		  " the team was placed at the bottom\n" +  " of the table at the end \n" +  "of IPL's opening season.\n" +
		  " But they scripted an incredible\n" +  " turnaround in the second edition,\n" +  " claiming the title.\n" +
		  " Much credit for their rags \n" +  "to riches story goes to their\n" +  " captain Adam Gilchrist.\n" +
		  " But they scripted an\n" +  " incredible turnaround in the second edition,\n" +  " claiming the title.\n" +
		  " Much credit for their rags to\n" +  " riches story goes to their\n" +  " captain Adam Gilchrist.\n" +
          "His compatriot Darren Lehmann\n" +  " joined the team as coach for\n" +  " the second edition.\n" +
		  " Deccan's pace attack is led \n" +  "by the fiery Fidel Edwards and\n" +  " the steady RP Singh.\n" +
		  " They bought out Sri Lankans\n" +  " Nuwan Zoysa and Chamara Silva ahead \n" +  "of the third edition.\n" +
		  " To further strengthen their bowling,\n" +  " they picked up the much sought\n" +  " after West Indies\n" +
		  " fast bowler, Kemar Roach,\n" +  " for a staggering USD 720,000\n" +  " at the 2010 auction.\n" +  " U-19 player\n" +
		  " Harmeet Singh was also picked\n" +  " for the coming tournament.");
		   
        jta_supdc.setText("Support Staff:\n"+
                            "Darren Lehmann\n"+ 
                            "Mike Young \n"+
                            "Steve Smith \n"+
                            "Sean Slattery \n"+
                            "Ashleigh Joyce "); 

        jta_dcsq.setText("Shikhar Dhawan\n"+"Ishant sharma\n"+"Pragyan Ojha\n"+"Amit Mishra\n"+"Manpreet Gony\n"+"Kevin Pietersen\n"+"Cameron White\n"+"Kumar Sangakkara\n"+"JP Duminy\n"+"Dale Steyn\n"+"Daniel Christian\n"+"Chris Lynn\n"+"Juan Theron\n"+"Michael Lumb.");


        //adjust size and set layout
        jp_dc.setPreferredSize (new Dimension (560, 435));
        jp_dc.setLayout (null);
        
		  dc_det = new JScrollPane(jta_dc);
		  dc_sup = new JScrollPane(jta_supdc);
		  dc_sq = new JScrollPane(jta_dcsq);
		  
        //add components
        jp_dc.add (sp_dc);
        jp_dc.add (dc_det);
        jp_dc.add (dc_sup);
        jp_dc.add (dc_sq);

        //set component bounds (only needed by Absolute Positioning)
        sp_dc.setBounds (105, 40, 190, 180);
        dc_det.setBounds (45, 235, 315, 155);
        dc_sup.setBounds (380, 235, 130, 155);
        dc_sq.setBounds (380, 40, 130, 185);
		  
		  WindowAdapter wl = new WindowAdapter()
		  {
			  public void windowClosing(WindowEvent we)
			  {
			     jf_dc.dispose();
           }
        };
		  jf_dc.addWindowListener(wl);

	  }	  
}

class csk{
    public JLabel sp_csk;
    public JTextArea jta_csk;
    public JTextArea jta_supcsk;
    public JTextArea jta_csksq;
	 public JFrame jf_csk;
	 public JPanel jp_csk;
	 public JScrollPane csk_det, csk_sup, csk_sq;


    public csk()
	  {
	     jf_csk = new JFrame("Chennai Super Kings");
		  jf_csk.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_csk = new JPanel();
		  jp_csk.setBackground(new Color(255,215,0));    
		  jf_csk.add(jp_csk);    

        buildCSK();
		  jf_csk.pack();    
		  jf_csk.setVisible (true);

        
    }
	 
	 void buildCSK()
	 {
	    //construct components
        sp_csk = new JLabel (new ImageIcon("csk_teamLogo.jpg"));
        jta_csk = new JTextArea (5, 5);
        jta_supcsk = new JTextArea (5, 5);
        jta_csksq = new JTextArea (5, 5);
		  
		  jta_csk.setText("Chennai Super Kings, owned by\n" +  " India Cements, managed to get the\n" +
		   "prized services of Mahendra Singh Dhoni \n" +  "in the inaugural edition of IPL. Quite\n" +
		   "understandably, the Indian captain commanded\n" +  " the highest price at the player\n" +
		   "auctions - a whopping USD 1.5 million\n" +  ". Australia's batting tour de force\n" +  " Matthew\n" +
			"Hayden was roped in to give \n" +  "the batting a destructive edge -\n" +  " and he played his role\n" +
			 "to perfection in two\n" +  " successive seasons.\n" +
              "Stephen Fleming, who \n" +  "doubled as coach in the second season;\n" +  " the wily Muralidaran;\n" +
			  "batsman Suresh Raina; and \n" +  "all-rounder Albie Morkel helped \n" +  "Chennai reach the final.\n" +
			   "When the second round of auctions\n" +  " took place, Andrew Flintoff \n" +  "joined as one of the\n" +
				"highest-paid players. In 2009,\n" +  " Hayden scored prolifically \n" +  "while his team entered\n" +
				 "the semi-final, only to be pipped\n" +  " at the post by RCB. Fleming \n" +  "has now been bought\n" +
				 "out by the side and is \n" +  "just the coach. At the 2010 auction,\n" +  " the team bought South \n" +
				 "African Justin Kemp, they\n" +  " also brought in Sri Lankan Thisara \n" +  "Perera for a sum\n" +
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

        jta_csksq.setText("Squad: M S Dhoni\n"+ "Suresh Raina \n"+" Murali Vijay \n"+"Albie Morkel (all retained) \n"+"Wriddhiman Saha \n"+"R Ashwin \n"+"S Badrinath \n"+"Joginder sharma \n"+"Sudeep Tyagi \n"+"Michael Hussey \n"+"Dwayne Bravo \n"+"Doug Bollinger \n"+"Scott Styris \n"+"Ben Hilfenhaus \n"+"Nuwan Kulasekara \n"+"Suraj Randiv \n"+"George Bailey \n" +"Francois Du Plessis");

        //adjust size and set layout
        jp_csk.setPreferredSize (new Dimension (560, 435));
        jp_csk.setLayout (null);
        
		  csk_det = new JScrollPane(jta_csk);
		  csk_sup = new JScrollPane(jta_supcsk);
		  csk_sq = new JScrollPane(jta_csksq);
		  
        //add components
        jp_csk.add (sp_csk);
        jp_csk.add (csk_det);
        jp_csk.add (csk_sup);
        jp_csk.add (csk_sq);

        //set component bounds (only needed by Absolute Positioning)
        sp_csk.setBounds (105, 40, 190, 180);
        csk_det.setBounds (45, 235, 315, 155);
        csk_sup.setBounds (380, 235, 130, 155);
        csk_sq.setBounds (380, 40, 130, 185);
		  
		  WindowAdapter wl = new WindowAdapter()
		  {
			  public void windowClosing(WindowEvent we)
			  {
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
		 jt_cc4 = new JTextField (1);
		 String[] jcb_monthItems = {"MM", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
       String[] jcb_dateItems = {"YYYY", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};

		 jcb_month = new JComboBox (jcb_monthItems);
       jcb_date = new JComboBox (jcb_dateItems); 
		 jt_cvv = new JPasswordField(3);
		 jt_name = new JTextField (1);
		 String[] jcb_cardtypeItems = {"Select Card","Maestro", "MasterCard", "VISA"};
		 jcb_cardtype = new JComboBox (jcb_cardtypeItems);
		 jt_cc3 = new JTextField (1);        
	 }
	 
	boolean validatecc()
   {
      boolean val = false;  
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
		 
		}
		return val;
	}	

	boolean validatecvv()
	{
		boolean vals = false;
		String cvv = jt_cvv.getPassword().toString();
		int len = cvv.length();
		if(len == 3)
	   	vals = true;
	
		return vals;
	}

	boolean validateName()
	{
      boolean valname = false;
		try{	 
		 String name = jt_name.getText().toString();
		 for(int i=0;i<name.length();i++)
		 {
		    char ch = name.charAt(i);
			 if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z') || (name.length()!=0))
			    valname = true;
			 	 
		 }		 	 
	   }
	 	catch(Exception err3)
	 	{
	      valname=false;
	 	}
	 
	 	return valname;
	}
	
	boolean validateExp()
	{
   	boolean valexp = false;
		if(!(jcb_month.getSelectedItem().toString().equals("MM") || jcb_date.getSelectedItem().toString().equals("YYYY")))
	   	valexp = true;
		return valexp;	
	}
	
	boolean validateCardType()
	{
   	boolean valcard = false;
		if(!(jcb_cardtype.getSelectedItem().toString().equals("Select Card")))		
	   	valcard = true;
		return valcard;
	}		
	 	 		 	 	 
			 	 	 
	 public void bookNow()
	 {
	      //construct components
        jl_cc = new JLabel ("Credit Card No:");
        jt_cc1 = new JTextField (1);
        jl_cc1 = new JLabel ("--");
        jt_cc2 = new JTextField (1);
        jl_cc2 = new JLabel ("--");
        
        jl_price = new JLabel ("Purchase Amount:");
        jt_price = new JTextField (1);
        jl_date = new JLabel ("Date of purchase:");
        jt_date = new JTextField (1);
        jl_ctype = new JLabel ("Card Type:");
        
        jl_cc3 = new JLabel ("--");
        
        jl_expdate = new JLabel ("Expiry Date:");
        
        jl_rupeec = new JLabel ("`");
		  jl_rupeec.setFont(new Font("Rupee Foradian",Font.BOLD,14));
        jl_name = new JLabel ("Name On Card:");
                
        jl_icici = new JLabel (new ImageIcon("icicibank.gif"));
        jl_expcaption = new JLabel ("(Please enter expiry date on your card)");
        jl_cardcaption = new JLabel ("(Please enter the name on your card)");
        jl_nocaption = new JLabel ("(Enter 16 digit no)");
        jl_typecaption = new JLabel ("(Choose card type)");
        jl_cvv = new JLabel ("CVV No:");
        
        jcomp30 = new JLabel ("(Enter the 3 digit CVV No)");

        //set components properties
        jt_price.setEnabled (false);
        jt_date.setEnabled (false);

        //adjust size and set layout
        jp_card.setPreferredSize (new Dimension (560, 435));
        jp_card.setLayout (null);
		  jp_card.setBackground(new Color(135,206,250));

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
		  jp_sucpay.setBackground(new Color(135,206,250));
        
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

class Button{
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
    public JLabel jl_pitch_c,jl_pitch_v,jl_pitch_s,jl_pitch_e;
	 Statement H;
	 Connection con;
    int i = 0;
	 		 
    WindowAdapter wl;
	 
	 void buildButton()
	 {
	    jf_button = new JFrame("Choose your Tickets");
		  jf_button.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        jp_button = new JPanel();
		  jp_button.setBackground(new Color(135,206,250));
		  jf_button.add(jp_button);
	     buttons();
		  jf_button.pack();
        jf_button.setVisible (true);
	}
     	
	 void setUpDB()
    {
      
        String URL = "jdbc:mysql://localhost:3306/ipl";
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection(URL,"root","");
		  }
	  
	    catch(Exception e){
           JOptionPane.showMessageDialog(null,"*** MySQL CONNECTION ERROR! *** " + e.getMessage());
           System.exit(0);
        }
    }
 
	 void buttons() {
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
        jl_pitch_c = new JLabel (new ImageIcon("images (1).jpg"));
		  jl_pitch_v = new JLabel (new ImageIcon("images (2).jpg"));
		  jl_pitch_e = new JLabel (new ImageIcon("images.jpg"));
		  jl_pitch_s = new JLabel (new ImageIcon("images (3).jpg"));

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
        jb_confirm.setBounds (440, 65, 95, 30);
        jl_pitch_c.setBounds (55, 295, 390, 125);
		  jl_pitch_s.setBounds (55, 295, 390, 125);
		  jl_pitch_e.setBounds (55, 295, 390, 125);
		  jl_pitch_v.setBounds (55, 295, 390, 125);
        
	     setUpDB();
		  
		  ActionListener al = new ActionListener()
		  {
		     public void actionPerformed(ActionEvent e)
			  {
			     try{ 
				  
				  Statement H = con.createStatement();  
				  
				  if(e.getSource()==jtb_1)
				  {
				     boolean sel = jtb_1.isSelected();  
					  if(sel)
					  {
					     jtb_1.setText("B");
						  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 1");
					  }	  
					  else
					     jtb_1.setText("");	  
					    
					 // jtb_1.setEnabled(false);
				  }
				  if(e.getSource()==jtb_2)
				  {
				  boolean sel = jtb_2.isSelected();  
					  if(sel)
					  {
					     jtb_2.setText("B");
						  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 2");
					  }	  
					  else
					     jtb_2.setText("");
				  }
				  if(e.getSource()==jtb_3)
				  {
				     boolean sel = jtb_3.isSelected();  
					  if(sel)
					  {
					     jtb_3.setText("B");
						  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 3");
					  }	  
					  else
					     jtb_3.setText("");
					    
					  
				  }
				  if(e.getSource()==jtb_4)
				  {
				    boolean sel = jtb_4.isSelected();  
					  if(sel)
					  {
					     jtb_4.setText("B");
						  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 4");
					  }
					  else
					     jtb_4.setText("");
					    
				  }
				  if(e.getSource()==jtb_5)
				  {
                boolean sel = jtb_5.isSelected();  
					  if(sel)
					  {
					     H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 5");
					     jtb_5.setText("B");
					  }
					  else
					     jtb_5.setText("");					    
					    
				  }
				  if(e.getSource()==jtb_6)
				  {
				    boolean sel = jtb_6.isSelected();  
					  if(sel)
					  {
					  	  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 6");
					     jtb_6.setText("B");
					  }
					  else
					     jtb_6.setText("");					    
					    
				  }
				  
				  if(e.getSource()==jtb_7)
				  {
				    boolean sel = jtb_7.isSelected();  
					  if(sel)
					  {
					     H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 7");
					     jtb_7.setText("B");
					  }
					  else
					     jtb_7.setText("");					    
				  }
				  if(e.getSource()==jtb_8)
				  {
				     boolean sel = jtb_8.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 8");
					     jtb_8.setText("B");}
					  else
					     jtb_8.setText("");					    
					    
				  }
				  if(e.getSource()==jtb_9)
				  {
				     boolean sel = jtb_9.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 9");
					     jtb_9.setText("B");}
					  else
					     jtb_9.setText("");					    
				  }
				  if(e.getSource()==jtb_10)
				  {
				    boolean sel = jtb_10.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 10");
					     jtb_10.setText("B");}
					  else
					     jtb_10.setText("");					    
				  }
				  if(e.getSource()==jtb_11)
				  {
				     boolean sel = jtb_11.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 11");
					     jtb_11.setText("B");}
					  else
					     jtb_11.setText("");
					    
				  }
				  if(e.getSource()==jtb_12)
				  {
				     boolean sel = jtb_12.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 12");
					     jtb_12.setText("B");}
					  else
					     jtb_12.setText("");					    
				  }
				  if(e.getSource()==jtb_13)
				  {
				    boolean sel = jtb_13.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 13");
					     jtb_13.setText("B");}
					  else
					     jtb_13.setText("");					    
				  }
				  if(e.getSource()==jtb_14)
				  {
				    boolean sel = jtb_14.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 14");
					     jtb_14.setText("B");}
					  else
					     jtb_14.setText("");					    
				  }
				  if(e.getSource()==jtb_15)
				  {
				    boolean sel = jtb_15.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 15");
					     jtb_15.setText("B");}
					  else
					     jtb_15.setText("");
					    
				  }
				  if(e.getSource()==jtb_16)
				  {
				    boolean sel = jtb_16.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 16");
					     jtb_16.setText("B");}
					  else
					     jtb_16.setText("");
					    
				  }
				  if(e.getSource()==jtb_17)
				  {
				    boolean sel = jtb_17.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 17");
					     jtb_17.setText("B");}
					  else
					     jtb_17.setText("");					    
				  }
				  if(e.getSource()==jtb_18)
				  {
				     boolean sel = jtb_18.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 18");
					     jtb_18.setText("B");}
					  else
					     jtb_18.setText("");
					    
				  }
				  if(e.getSource()==jtb_19)
				  {
				    boolean sel = jtb_19.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 19");
					     jtb_19.setText("B");}
					  else
					     jtb_19.setText("");
					    
				  }
				  if(e.getSource()==jtb_20)
				  {
				    boolean sel = jtb_20.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 20");
					     jtb_20.setText("B");}
					  else
					     jtb_20.setText("");					    
				  }
				  if(e.getSource()==jtb_21)
				  {
				    boolean sel = jtb_21.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 21");
					     jtb_21.setText("B");}
					  else
					     jtb_21.setText("");					    
				  }
				  if(e.getSource()==jtb_22)
				  {
				    boolean sel = jtb_22.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 22");
					  
					     jtb_22.setText("B");}
					  else
					     jtb_22.setText("");					    
				  }
				  if(e.getSource()==jtb_23)
				  {
				     boolean sel = jtb_23.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 23");
					     jtb_23.setText("B");}
					  else
					     jtb_23.setText("");					    
				  }
				  
				  if(e.getSource()==jtb_24)
				  {
				    boolean sel = jtb_24.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 24");
					     jtb_24.setText("B");}
					  else
					     jtb_24.setText("");					    
				  }
				  if(e.getSource()==jtb_25)
				  {
				    boolean sel = jtb_25.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 25");
					     jtb_25.setText("B");}
					  else
					     jtb_25.setText("");					    
				  }
				  if(e.getSource()==jtb_26)
				  {
				    boolean sel = jtb_26.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 26");
					     jtb_26.setText("B");}
					  else
					     jtb_26.setText("");					    
				  }
				  if(e.getSource()==jtb_27)
				  {
				    boolean sel = jtb_27.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 27");
					     jtb_27.setText("B");}
					  else
					     jtb_27.setText("");					    
				  }
				  if(e.getSource()==jtb_28)
				  {
				    boolean sel = jtb_28.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 28");
					     jtb_28.setText("B");}
					  else
					     jtb_28.setText("");					    
				  }
				  if(e.getSource()==jtb_29)
				  {
				    boolean sel = jtb_29.isSelected();  
					  if(sel){
					  H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 29");
					     jtb_29.setText("B");}
					  else
					     jtb_29.setText("");
					    
				  }
				  if(e.getSource()==jtb_30)
				  {
				    boolean sel = jtb_30.isSelected();  
					  if(sel)
					  {
					     H.executeUpdate("UPDATE ENTERTAINMENT SET STAT = 'B' WHERE SEATNO = 30");  
						  jtb_30.setText("B");
					  }	    
					  else
					     jtb_30.setText("");					    
				  }
				  }
				  catch(Exception eeeeee)
				  {
				     JOptionPane.showMessageDialog(null,eeeeee.getMessage());
					
			     }
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
    }
	 
	 void closeWindow()
	 {
	    WindowAdapter wl = new WindowAdapter()
		 {
		    public void windowClosing(WindowEvent we)
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
					   if(sel1 && jtb_1.getText().equals("B")){
						   i++;
						}	
						if(sel2 && jtb_2.getText().equals("B")){
						   i++;
						}
						if(sel3 && jtb_3.getText().equals("B")){
						   i++;}
						if(sel4 && jtb_4.getText().equals("B")){
						   i++;}
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
						if(i==0){
						   JOptionPane.showMessageDialog(null,"No tickets were chosen");
								  
						}	
					   else    
						JOptionPane.showMessageDialog(null,"You have chosen "+i+" ticket(s). Thank you! ");
						closeWindow();
						
												   
					
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
		 jp_fail.setBackground(new Color(135,206,250));
		  

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

	 
	 	 	  	 