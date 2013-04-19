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
	     jf_ko = new JFrame("Chennai Super Kings");
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
        sp_ko = new JLabel (new ImageIcon("ko_teamLogo.jpg"));
        jta_ko = new JTextArea (5, 5);
        jta_supko = new JTextArea (5, 5);
        jta_kosq = new JTextArea (5, 5);
		  
		  jta_ko.setText("Chennai Super Kings, owned by\n" +  " India Cements, managed to get the\n" +
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
				  
        jta_supko.setText("Support Staff:\n"+

                            "Stephen Fleming:\n" +
                             "Gregory King:\n" + 
                              "Tom Simsek:\n" + 
                             "Dr.Gopal Ramanathan:\n" +       
                            "R Radhakrishnan:\n" + 
                             "Chandrasekar VB:\n" + 
                             "Rodney Parry:\n" + 
                             "Venkatesh Prasad"); 

        jta_kosq.setText("Squad: M S Dhoni\n"+ "Suresh Raina \n"+" Murali Vijay \n"+"Albie Morkel (all retained) \n"+"Wriddhiman Saha \n"+"R Ashwin \n"+"S Badrinath \n"+"Joginder sharma \n"+"Sudeep Tyagi \n"+"Michael Hussey \n"+"Dwayne Bravo \n"+"Doug Bollinger \n"+"Scott Styris \n"+"Ben Hilfenhaus \n"+"Nuwan Kulasekara \n"+"Suraj Randiv \n"+"George Bailey \n" +"Francois Du Plessis");

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
