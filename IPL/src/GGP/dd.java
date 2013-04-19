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
	     jf_dd = new JFrame("Chennai Super Kings");
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
		  jta_ddsq.setText("Virender Sehwag (retained), Irfan Pathan, Naman Ojha, Ajit Agarkar, Ashok Dinda, Umesh Yadav, Venugopal Rao, David Warner, James Hopes, Morne Morke l, Aaron Finch, Matthew Wade, Roelof van der Merve, Andrew McDonand, Travis Birt, Colin Ingram and Robert Frylinck.");

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