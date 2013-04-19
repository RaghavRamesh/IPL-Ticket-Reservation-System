import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Issue
{
   JFrame jf;
	JPanel jp;
	JLabel jl_issdate,jl_retdate,jl_membid,jl_name,jl_bookid;
	JTextField jt_issdate,jt_retdate,jt_membid,jt_name,jt_bookid;
	JButton jb_issue,jb_find,jb_return;
	Connection con;
	Statement S;
	ResultSet rs;
	String membid,issdate,retdate,name,bookid;
	
	public Issue()
	{
	   jf = new JFrame();
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(10,100);
		
		jp = new JPanel();
		jf.add(jp);
		
		addComp();
		setUpDB();
		issueBook();
		returnBook();
		
		jf.pack();
		jf.setVisible(true);
	}
	
	public void addComp()
	{
	   jl_issdate = new JLabel("Issue Date:");	
		jl_retdate = new JLabel("Return Date:");
		jl_name = new JLabel("Name:");
		jl_membid = new JLabel("MembID:");
		jl_bookid = new JLabel("BookID:");
		
		jt_issdate = new JTextField(10);
		jt_retdate = new JTextField(10);
		jt_name = new JTextField(10);
		jt_membid = new JTextField(10);
		jt_bookid = new JTextField(10);
		
		jb_find = new JButton("FIND");
		jb_issue = new JButton("ISSUE");
		jb_return = new JButton("RETURN");
		
		jp.add(jl_membid);
		jp.add(jt_membid);
		jp.add(jl_bookid);
		jp.add(jt_bookid);
		jp.add(jl_name);
		jp.add(jt_name);
		jp.add(jl_issdate);
		jp.add(jt_issdate);
		jp.add(jl_retdate);
		jp.add(jt_retdate);
		
		jp.add(jb_find);
		jp.add(jb_issue);
		jp.add(jb_return);
	}
	
	public void setUpDB()
	{
	   String URL = "jdbc:mysql://localhost:3306/library";
		try{
		   Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,"root","");
			if(con.isClosed()) JOptionPane.showMessageDialog(null,"Connection Failed.");
			else	JOptionPane.showMessageDialog(null,"Connection Successful!");
		}
		catch(Exception err)
		{
		   JOptionPane.showMessageDialog(null,err.getMessage());
		}
	}
	
	public void issueBook()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   if(e.getSource()==jb_find)
				{
				   membid = jt_membid.getText();
					try
					{
					   S = con.createStatement();
						rs = S.executeQuery("SELECT * FROM MEMBERS WHERE MEMBerID = "+membid);
						if(rs.next())
						{
						   bookid = rs.getString("BOOKID");
							name = rs.getString("NAME");
							issdate = rs.getString("ISSUEDATE");
							retdate = rs.getString("RETURNDATE");
							
							jt_bookid.setText(bookid);
							jt_name.setText(name);
							jt_issdate.setText(issdate);
							jt_retdate.setText(retdate);
						}
						else
						{
						   JOptionPane.showMessageDialog(null,"No member matching the given id was found");
							jt_bookid.setText("");	
							jt_membid.setText("");
							jt_name.setText("");
							jt_issdate.setText("");
							jt_retdate.setText("");
						}
					}
					catch(Exception er)
					{
					   JOptionPane.showMessageDialog(null,er.getMessage());
					}
				}
				
				if(e.getSource()==jb_issue)
				{
					try{
						S = con.createStatement();
						rs = S.executeQuery("SELECT CURDATE()");  
						if(rs.next())
						{
							String curdate = rs.getString("CURDATE()");
							jt_issdate.setText(curdate);
							int r = S.executeUpdate("UPDATE members SET ISSUEDATE = CURDATE() WHERE MEMBERID = "+membid);
							if(r==1)
							JOptionPane.showMessageDialog(null,"Book issued.");
						}	
						else
						   JOptionPane.showMessageDialog(null,"Book not issued.");
						
					}
					catch(Exception er)
					{
					   JOptionPane.showMessageDialog(null,er.getMessage());
					}		
				}	
			}
		};
		jb_find.addActionListener(al);
		jb_issue.addActionListener(al);
	}							
	
	public void returnBook()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   membid = jt_membid.getText();
				try
				{
				   S = con.createStatement();
					int r = S.executeUpdate("UPDATE MEMBERS SET ISSUEDATE = null WHERE MEMBERID = "+membid);
					if(r==1)
					{
					   JOptionPane.showMessageDialog(null,"Book returned.");
						jt_issdate.setText("");
					}
					else
					   JOptionPane.showMessageDialog(null,"Book not returned.");
				}
				catch(Exception er)
				{
				   JOptionPane.showMessageDialog(null,er.getMessage());
				}			
			}
		};
		jb_return.addActionListener(al);
	}									
   
	public static void main(String[] args)
	{
	   new Issue();
	}
}						
	
	