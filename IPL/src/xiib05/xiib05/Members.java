import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Members
{
   JFrame jf;
	JPanel jp;
	JLabel jl_bookid,jl_name,jl_issdate,jl_retdate,jl_membid;
	JTextField jt_bookid,jt_name,jt_issdate,jt_retdate,jt_membid;
	JButton jb_insert,jb_remove,jb_find;
	Connection con;
	ResultSet rs;
	Statement S;
	String bookid,membid,issdate,retdate,name;
	
	public Members()
	{
	   jf = new JFrame();
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(0,100);
		
		jp = new JPanel();
		jf.add(jp);
		
		addComponents();
		setUpDB();
		insert();
		remove();
		
		jf.pack();
		jf.setVisible(true);
	}
	
	public void setUpDB()
	{
	   String URL = "jdbc:mysql://localhost:3306/library";
		try{
		   Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,"root","");
			if(con.isClosed()) JOptionPane.showMessageDialog(null,"Connection Failed");
			else JOptionPane.showMessageDialog(null,"Connection Successful!");
		}
		catch(Exception er)
		{
		   JOptionPane.showMessageDialog(null,er.getMessage());
		}		
	}
	
	public void addComponents()
	{
	   jl_bookid = new JLabel("BookID:");
		jl_membid = new JLabel("Member ID:");
		jl_name = new JLabel("Name:");
		jl_issdate = new JLabel("Issue date:");
		jl_retdate = new JLabel("Return date:");
		
		jt_bookid = new JTextField(10);
		jt_membid = new JTextField(10);
		jt_name = new JTextField(15);
		jt_issdate = new JTextField(10);
		jt_retdate = new JTextField(10);
		
		jb_insert = new JButton("INSERT");
		jb_remove = new JButton("REMOVE");
		jb_find = new JButton("FIND");
		
		jp.add(jl_membid);
		jp.add(jt_membid);
		jp.add(jl_name);
		jp.add(jt_name);
		jp.add(jl_bookid);
		jp.add(jt_bookid);
		jp.add(jl_issdate);
		jp.add(jt_issdate);
		jp.add(jl_retdate);
		jp.add(jt_retdate);
		jp.add(jb_find);
		jp.add(jb_insert);
		jp.add(jb_remove);
	}
	
	public void insert()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   name = jt_name.getText();
				retdate = jt_retdate.getText();
				issdate = jt_issdate.getText();
				membid = jt_membid.getText();
				bookid = jt_bookid.getText();
				
				try
				{
				   S = con.createStatement();
					String QUERY = "INSERT INTO MEMBERS "+ 
					               "VALUES ('"+membid+"','"+name+"','"+bookid+"','"+issdate+"','"+retdate+"')";
					int r = S.executeUpdate(QUERY);
					if(r==1)
					{
					   JOptionPane.showMessageDialog(null,"Member added");
						jt_name.setText("");
						jt_membid.setText("");
						jt_bookid.setText("");
						jt_issdate.setText("");
						jt_retdate.setText("");
					}
					else
					   JOptionPane.showMessageDialog(null,"Invalid entry");
				}
				catch(Exception er)
				{
				   JOptionPane.showMessageDialog(null,er.getMessage());	
				}
			}
		};
		jb_insert.addActionListener(al);
	}				
	
	public void remove()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   if(e.getSource()==jb_remove)
				{  
					membid = jt_membid.getText();
					try
					{
				 	  S = con.createStatement();
						String QUERY = "DELETE FROM MEMBERS WHERE MEMBERID = "+membid;
						int r = S.executeUpdate(QUERY);
						if(r==1) 
						{
					 	  JOptionPane.showMessageDialog(null,"Member removed.");
					  	  jt_name.setText("");
						  jt_membid.setText("");
						  jt_bookid.setText("");
						  jt_issdate.setText("");
						  jt_retdate.setText("");
						}
						else								
	                 JOptionPane.showMessageDialog(null,"Member not removed.");
					}
					catch(Exception er)
					{
				   	JOptionPane.showMessageDialog(null,er.getMessage());
					}
				}
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

			}	
		};
		jb_remove.addActionListener(al);
		jb_find.addActionListener(al);
	}
							
	public static void main(String[] args)
	{
	   new Members();
	}
}			
