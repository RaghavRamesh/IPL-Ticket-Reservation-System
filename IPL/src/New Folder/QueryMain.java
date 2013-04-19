import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Query
{
   JFrame jf;
	JPanel jp;
	  
	Connection con;
	ResultSet rs;
	Statement S;
	String[] jcb_standItems = {"STANDC", "ENTERTAINMENT", "PAVILION", "CELEBRITY"};  
	 JComboBox jcb_stand;
	int[] a = new int[30];
	public Query()
	{
		jf = new JFrame();
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp = new JPanel();
		jf.add(jp);
		jcb_stand = new JComboBox(jcb_standItems);
		jp.add(jcb_stand);
		process();
		jf.setVisible(true);
	}
	
	 void setUpDB()
   {
      
        String URL = "jdbc:mysql://localhost:3306/ipl";
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection(URL,"root","");
           if (con.isClosed()) JOptionPane.showMessageDialog(null, "CONNECTION FAILED...");
           else JOptionPane.showMessageDialog(null, "CONNECTION SUCCESSFUL");
        }
	  
	    catch(Exception e){
           JOptionPane.showMessageDialog(null,"*** MySQL CONNECTION ERROR! *** " + e.getMessage());
           System.exit(0);
        }
    }

	  void execQuery()    
	 {
        setUpDB();
		  String s ="" ;
		  try{
			  
			  S = con.createStatement();
			  String query = "SELECT SEATNO FROM " +jcb_stand.getSelectedItem().toString()+ " WHERE STAT IS NOT NULL";
			  
			  rs = S.executeQuery(query+"");
			  
			  for(int i=0;rs.next();i++)
			  {
			     a[i] = Integer.parseInt(rs.getString("SEATNO"));
				  System.out.println(a[i]);
			  }
			   
        }
	    catch(Exception error)
	    {
			JOptionPane.showMessageDialog(null,""+error.getMessage());
       }
		 
    }
	 
	 public void process()
	 {
	    ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			  execQuery();
			}
		};
		jcb_stand.addActionListener(al);	
	}	
	 
   public static void main(String[] args)
	{
	   new Query();
			
	}
}			 		