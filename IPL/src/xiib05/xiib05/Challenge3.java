// R. Raghav roll no: 5 xiib05 qno 16

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Challenge3
{
   JFrame jf;
	JPanel jp;
	JLabel jl_bookid,jl_status;
	JTextField jt_bookid,jt_status;
	JButton jb_check;
	Connection con;
	Statement S;
	ResultSet rs;
	
	public Challenge3()
	{
      jf = new JFrame("Challenge3");
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(300,100);
		
		jp = new JPanel();
		jf.add(jp);
		
		addComp();
		setUpDB();
		status();
		
		jf.pack();
		jf.setVisible(true);
	}
	
	public void addComp()
	{
	   jl_bookid = new JLabel("BookID:");
		jl_status = new JLabel("Status:");
		
		jt_bookid = new JTextField(10);
		jt_status = new JTextField(10);
		jt_status.setEnabled(false);
		
		jb_check = new JButton("CHECK");
		
		jp.add(jl_bookid);
		jp.add(jt_bookid);
		jp.add(jl_status);
		jp.add(jt_status);
		jp.add(jb_check);	
	}
	
	public void setUpDB()
	{
	   String URL = "jdbc:mysql://localhost:3306/library";
		try{
		   Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,"root","");
			if(con.isClosed()) JOptionPane.showMessageDialog(null,"Connection Failed");
			else	JOptionPane.showMessageDialog(null,"Connection successful!");
		}
		catch(Exception err)
		{
		   JOptionPane.showMessageDialog(null,err.getMessage());
		}
	}
	
	public void status()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   String bookid = jt_bookid.getText();
				try
				{
				   S = con.createStatement();
					rs = S.executeQuery("SELECT L.BOOKID FROM MEMBERS M,LIBRARY L WHERE M.BOOKID = L.BOOKID AND L.BOOKID = "+bookid);
					if(rs.next())
					   jt_status.setText("Borrowed");
				   else	
					   jt_status.setText("Available");
				}
				catch(Exception er)
				{
				   JOptionPane.showMessageDialog(null,er.getMessage());
				}					
			}
		};
		jb_check.addActionListener(al);
	}		
	
	public static void main(String[] args)
	{
	   new Challenge3();
	}
}			