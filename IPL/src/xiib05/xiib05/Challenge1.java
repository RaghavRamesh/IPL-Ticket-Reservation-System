// R. Raghav roll no: 5 xiib05 qno 14

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Challenge1
{
   JFrame jf;
	JPanel jp;
	Connection con;
	JLabel jl_membid,jl_bookid,jl_name,jl_issdate,jl_retdate;
	JTextField jt_membid,jt_bookid,jt_name,jt_issdate,jt_retdate;
	JButton jb_find,jb_issue;
	Statement S,S1;
	ResultSet rs;
	String membid,bookid,name,issdate,retdate;
	
	public Challenge1()
	{
	   jf = new JFrame("Challenge1");
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(10,100);
		
		jp = new JPanel();
		jf.add(jp);
		
		addComp();
		setUpDB();
		issueBook();
		JOptionPane.showMessageDialog(null,"Enter Member ID");
		
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
			else	JOptionPane.showMessageDialog(null,"Connection successful!");
		}
		catch(Exception err)
		{
		   JOptionPane.showMessageDialog(null,err.getMessage());
		}
	}
	
	public void addComp()
	{
	   jl_name = new JLabel("Name:");
		jl_membid = new JLabel("MemberID:");
		jl_bookid = new JLabel("BookID:");
		jl_issdate = new JLabel("Issue Date:");
		jl_retdate = new JLabel("Return Date:");
		
		jt_name = new JTextField(10);
		jt_name.setEnabled(false);
		jt_membid = new JTextField(10);
		jt_bookid = new JTextField(10);
		jt_bookid.setEnabled(false);
		jt_issdate = new JTextField(10);
		jt_issdate.setEnabled(false);
		jt_retdate = new JTextField(10);
		jt_retdate.setEnabled(false);
		
		jb_find = new JButton("FIND");
		jb_issue = new JButton("ISSUE");
		
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
					membid = jt_membid.getText();
					try{
						S = con.createStatement();
						S1 = con.createStatement();
						String QUERY = "UPDATE MEMBERS,LIBRARY SET ISSUEDATE=CURDATE() WHERE LIBRARY.BOOKID=MEMBERS.BOOKID AND LIBRARY.CATEGORY !='R' AND MEMBERID = "+membid;
						int r = S.executeUpdate(QUERY);  
						if(r==1)
						{
						   rs = S1.executeQuery("SELECT curdate()");  
							JOptionPane.showMessageDialog(null,"Book issued.");
						   rs.next();
							jt_issdate.setText(rs.getString("CURDATE()"));
						}
						else
						   JOptionPane.showMessageDialog(null,"Category R.");	
						
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
	
	public static void main(String[] args)
	{
	   new Challenge1();
	}
}		
					
			