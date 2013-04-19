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
		  
		   jta_pu.setText("The team from India's princely\n" +  " state didn't quite command\n" +
		  "the kind of respect and interest\n" +  " that befits royalty.\n"+
		  " So when the Rajasthan Royals,\n" +
		  "led by Shane Warne, swept the opposition away \n" +  "and took home the \n" +  "first-ever IPL title,\n" +
		   "everyone was surprised.\n" +  " Sohail Tanvir and Shane Watson\n"+
			" were the other big names that" +
	      "powered the side,\n" +  " but it was players like\n"+" Swapnil Asnodkar and \n" +  "Ravindra Jadeja\n" +  " that shone.\n" +
         "IPL 2009 got underway with \n" +  "Bollywood diva Shilpa Shetty\n"+ " and her then fiance Raj Kundra \n" + 
		 "also joining the franchise as co-owners.\n" +  "Kaif was left out and Watson was\n" +  " unavailable.\n" +
		  "Tyron Henderson, who was roped \n" +  "in after a tug-of-war with \n"+ "KXIP, played just one game.\n" +
		   "Yusuf Pathan stapued in a win over pu\n"+" in the only Super Over of the tournament.\n" +
		    "At the 2010 auction, Rajasthan bought\n"+" two Australian players - Adam Voges \n" +  "and in \n" +
			"a surprise move, the\n" +  " out of action Damien Martyn.\n" +  " The team had bought out \n" +
			"Tyron Henderson,\n" +  " Robert Quiney and \n" +  "Mohd Kaif prior to the auction"); 
        jta_suppu.setText("              Support Staff:\n"+

                          "              Dapuen Bepuy\n"+ 
                          "              Sushil Tulaskar \n"+ 
                          "              Monty Desai \n"+ 
                          "              Saurabh Walkar "); 
								  									 
        jta_pusq.setText("Squad:\n"+"Shane Warne\n"+ "Shane Watson (both retained)\n"+ "Rahul Dravid\n"+ "Pankaj Singh\n"+ "Ross Taylor\n"+ "Johan Botha\n"+ "Paul Collingwood\n"+ "Shaun Tait.");

        //adjust size and set layout
        jp_pu.setPrefepuedSize (new Dimension (560, 435));
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
