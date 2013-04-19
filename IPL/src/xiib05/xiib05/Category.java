import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Category
{
   JFrame jf;
	JPanel jp;
	JLabel jl_bookid,jl_cat,jl_author,jl_title,jl_subject;
	JTextField jt_bookid,jt_cat,jt_author,jt_title,jt_subject;
	JButton jb_change,jb_find;
	Connection con;
	ResultSet rs;
	Statement S;
	String bookid,cat,subject,title,author;
	
	public Category()
	{
	   jf = new JFrame();
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(0,100);
		
		jp = new JPanel();
		jf.add(jp);
		
		addComponents();
		setUpDB();
		action();
		
		jf.pack();
		jf.setVisible(true);
	}
	
	public void addComponents()
	{
	   jl_bookid = new JLabel("BookID:");
		jl_author = new JLabel("Author:");	
		jl_cat = new JLabel("Category:");
		jl_title = new JLabel("Title:");
		jl_subject = new JLabel("Subject:");
		
		jt_bookid = new JTextField(4);
		jt_author = new JTextField(15);
		jt_cat = new JTextField(3);
		jt_title = new JTextField(15);
		jt_subject = new JTextField(15);
		
		jb_find = new JButton("FIND");
		jb_change = new JButton("CHANGE");
		
		jp.add(jl_bookid);
		jp.add(jt_bookid);
		jp.add(jl_title);
		jp.add(jt_title);
		jp.add(jl_author);
		jp.add(jt_author);
		jp.add(jl_subject);
		jp.add(jt_subject);
		jp.add(jl_cat);
		jp.add(jt_cat);
		
		jp.add(jb_find);
		jp.add(jb_change);
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
	
	public void action()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   if(e.getSource()==jb_find)
				{
				   bookid = jt_bookid.getText();
					try
					{
					   S = con.createStatement();
						rs = S.executeQuery("SELECT * FROM LIBRARY WHERE BOOKID = "+bookid);
						if(rs.next())
						{
						   author = rs.getString("author");
							cat = rs.getString("category");
							title = rs.getString("title");
							subject = rs.getString("subject");
							
							jt_author.setText(author);
							jt_cat.setText(cat);
							jt_title.setText(title);
							jt_subject.setText(subject);
						}
						else
						{
						   JOptionPane.showMessageDialog(null,"No book matching the given id was found.");
							jt_author.setText("");	
							jt_cat.setText("");
							jt_title.setText("");
							jt_subject.setText("");
							jt_bookid.setText("");
						}
					}
               catch(Exception er)
					{
					   JOptionPane.showMessageDialog(null,er.getMessage());
					}
				}
				
				if(e.getSource()==jb_change)
				{
				   String c = JOptionPane.showInputDialog(null,"Change to category...");
					jt_cat.setText(c);
					try{
						int r = S.executeUpdate("UPDATE LIBRARY SET CATEGORY = '"+c+"' WHERE BOOKID = "+bookid);
						if(r==1)
					  		JOptionPane.showMessageDialog(null,"Category of the book changed.");
						else
					  		JOptionPane.showMessageDialog(null,"Category of the book not changed.");
					}
					catch(Exception err)
					{
					   JOptionPane.showMessageDialog(null,err.getMessage());
					}			
					  
				}
					
			}
		};
		jb_find.addActionListener(al);
		jb_change.addActionListener(al);
	}							
	
	public static void main(String[] args)
	{
	   new Category();
	}
}			
	
	