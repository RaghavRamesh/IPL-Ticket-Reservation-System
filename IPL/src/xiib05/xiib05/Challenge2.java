// R. Raghav roll no: 5 xiib05 qno 15

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Challenge2
{
   JFrame jf;
	JPanel jp;
	Connection con;
	JLabel jl_membid,jl_bookid,jl_name,jl_issdate,jl_retdate,jl_author,jl_title,jl_subject,jl_category;
	JTextField jt_membid,jt_bookid,jt_name,jt_issdate,jt_retdate,jt_author,jt_title,jt_subject,jt_category;
	JButton jb_find;
	Statement S,S1;
	ResultSet rs;
	String membid,bookid,name,issdate,retdate,author,subject,title,category;
	
	public Challenge2()
	{
	   jf = new JFrame("Challenge2");
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(10,100);
		
		jp = new JPanel();
		jf.add(jp);
		
		addComp();
		setUpDB();
		details();
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
		jl_author = new JLabel("Author:");
		jl_subject = new JLabel("Subject:");
		jl_category = new JLabel("Category:");
		jl_title = new JLabel("Title:");
		
		jt_name = new JTextField(10);
		jt_name.setEnabled(false);
		jt_membid = new JTextField(10);
		jt_bookid = new JTextField(10);
		jt_bookid.setEnabled(false);
		jt_issdate = new JTextField(10);
		jt_issdate.setEnabled(false);
		jt_retdate = new JTextField(10);
		jt_retdate.setEnabled(false);
		jt_author = new JTextField(10);
		jt_author.setEnabled(false);
		jt_subject = new JTextField(10);
		jt_subject.setEnabled(false);
		jt_category = new JTextField(10);
		jt_category.setEnabled(false);
		jt_title = new JTextField(10);
		jt_title.setEnabled(false);
		
		jb_find = new JButton("FIND");
		  
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
		jp.add(jl_author);
		jp.add(jt_author);
		jp.add(jl_title);
		jp.add(jt_title);
		jp.add(jl_subject);
		jp.add(jt_subject);
		jp.add(jl_category);
		jp.add(jt_category);
		
	}
	
	public void details()
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
						rs = S.executeQuery("SELECT M.*,L.* FROM MEMBERS M,LIBRARY L WHERE M.BOOKID = L.BOOKID AND M.MEMBERID = "+membid);
						if(rs.next())
						{
						   bookid = rs.getString("BOOKID");
							name = rs.getString("NAME");
							issdate = rs.getString("ISSUEDATE");
							retdate = rs.getString("RETURNDATE");
							category = rs.getString("CATEGORY");
							author = rs.getString("AUTHOR");
							title = rs.getString("TITLE");
						   subject = rs.getString("SUBJECT");
							
							jt_bookid.setText(bookid);
							jt_name.setText(name);
							jt_issdate.setText(issdate);
							jt_retdate.setText(retdate);
							jt_category.setText(category);
							jt_author.setText(author);
							jt_title.setText(title);
							jt_subject.setText(subject);
						}
						else
						{
						   JOptionPane.showMessageDialog(null,"No member matching the given id was found");
							jt_bookid.setText("");	
							jt_membid.setText("");
							jt_name.setText("");
							jt_issdate.setText("");
							jt_retdate.setText("");
							jt_category.setText("");
							jt_author.setText("");
							jt_title.setText("");
							jt_subject.setText("");
						}
					}
					catch(Exception er)
					{
					   JOptionPane.showMessageDialog(null,er.getMessage());
					}
				}
				
				
			}
		};
		jb_find.addActionListener(al);
		 
	}  
	
	public static void main(String[] args)
	{
	   new Challenge2();
	}
}		
					
			