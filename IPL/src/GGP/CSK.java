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
		  jp_csk.setBackground(Color.yellow);    
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
