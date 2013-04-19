import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Subject
{
   JFrame jf;
	JPanel jp;
	JLabel jl_subject,jl_bookid,jl_title,jl_category,jl_author;
	JTextField jt_subject,jt_bookid,jt_title,jt_category,jt_author;
	JButton jb_next,jb_prev,jb_first,jb_last,jb_search;
	Connection con;
	ResultSet rs;
	Statement S;
	String title,subject,bookid,category,author;
	
	public Subject()
	{
	   jf = new JFrame();
		jf.setSize(300,300);
		jf.setLocation(0,100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jp = new JPanel();
		jf.add(jp);
		
		buildSubject();
		setUpDB();
		acceptSubject();
		acceptCategory();
		acceptAuthor(); 
		
		jf.pack();
		jf.setVisible(true);
	}
	
	public void buildSubject()
	{
	   jl_subject = new JLabel ("Subject:");	
		jl_title = new JLabel ("Title:");
		jl_author = new JLabel ("Author:");
		jl_category = new JLabel ("Category:");
		jl_bookid = new JLabel ("BookID:");
		
		jt_subject = new JTextField(15);
		jt_title = new JTextField(15);
		jt_author = new JTextField(15);
		jt_category = new JTextField(3);
		jt_bookid = new JTextField(4);
		
		jb_first = new JButton("FIRST");
		jb_prev = new JButton("PREVIOUS");
		jb_next = new JButton("NEXT");
		jb_last = new JButton("LAST");
		jb_search = new JButton("SEARCH");
		
		jp.add(jl_subject);
		jp.add(jt_subject);
		jp.add(jl_bookid);
		jp.add(jt_bookid);
		jp.add(jl_title);
		jp.add(jt_title);
		jp.add(jl_author);
		jp.add(jt_author);
		jp.add(jl_category);
		jp.add(jt_category);
		
		jp.add(jb_search);
		jp.add(jb_first);
		jp.add(jb_prev);
		jp.add(jb_next);
		jp.add(jb_last);
	}
	
	public void setUpDB()
	{
	   String URL = "jdbc:mysql://localhost:3306/library";
		try{
		   Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,"root","");
			if(con.isClosed()) JOptionPane.showMessageDialog(null,"CONNECTION FAILED..");
			else JOptionPane.showMessageDialog(null,"CONNECTION SUCCESSFUL.");
		}
		catch(Exception e)
		{
		   JOptionPane.showMessageDialog(null,"Connection Error "+e.getMessage());
		}		
	}
	
	public void acceptSubject()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   if(e.getSource()==jb_search)
				{
				   subject = jt_subject.getText();
					
					try{
					   S = con.createStatement();
					   rs = S.executeQuery("SELECT * FROM LIBRARY WHERE SUBJECT = '"+subject+"'");
						if(rs.next())
						{
						   title = rs.getString("TITLE");  
							author = rs.getString("AUTHOR");  
							category = rs.getString("CATEGORY");  
							bookid = rs.getString("BOOKID");  
							
							jt_title.setText(title);
							jt_author.setText(author);
							jt_category.setText(category);
							jt_bookid.setText(bookid);
						}
						else
						{
						   JOptionPane.showMessageDialog(null,"No books were found under thos subject.");
							jt_title.setText("");	
							jt_author.setText("");
							jt_subject.setText("");
							jt_category.setText("");
							jt_bookid.setText("");
						}
					}
					catch(Exception err)
					{ 
					   JOptionPane.showMessageDialog(null,err.getMessage());
					}
				}
				
				if(e.getSource()==jb_next)
				{
				   try{  
					   if(rs.next())
						{
						   title = rs.getString("TITLE");  
							author = rs.getString("AUTHOR");  
							category = rs.getString("CATEGORY");  
							bookid = rs.getString("BOOKID");  
							
							jt_title.setText(title);
							jt_author.setText(author);
							jt_category.setText(category);
							jt_bookid.setText(bookid);
						}
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}
				
				if(e.getSource()==jb_prev)
				{
				   try{  
					   if(rs.previous())
						{
						   title = rs.getString("TITLE");  
							author = rs.getString("AUTHOR");  
							category = rs.getString("CATEGORY");  
							bookid = rs.getString("BOOKID");  
							
							jt_title.setText(title);
							jt_author.setText(author);
							jt_category.setText(category);
							jt_bookid.setText(bookid);
						}
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}
				if(e.getSource()==jb_last)
				{
				     
					try{  
					   rs.last();
					   title = rs.getString("TITLE");  
					 	author = rs.getString("AUTHOR");  
						category = rs.getString("CATEGORY");  
						bookid = rs.getString("BOOKID");  
							
						jt_title.setText(title);
						jt_author.setText(author);
						jt_category.setText(category);
						jt_bookid.setText(bookid);
						
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}
				if(e.getSource()==jb_first)
				{
				     
					try{  
					   rs.first();
					   title = rs.getString("TITLE");  
						author = rs.getString("AUTHOR");  
						category = rs.getString("CATEGORY");  
						bookid = rs.getString("BOOKID");  
							
						jt_title.setText(title);
						jt_author.setText(author);
						jt_category.setText(category);
						jt_bookid.setText(bookid);	
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}	
			}
		};
		jb_search.addActionListener(al);
		jb_next.addActionListener(al);
		jb_prev.addActionListener(al);
		jb_first.addActionListener(al);
		jb_last.addActionListener(al);
	}
	
	public void acceptCategory()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   if(e.getSource()==jb_search)
				{
				   category = jt_category.getText();
					
					try{
					   S = con.createStatement();
					   rs = S.executeQuery("SELECT * FROM LIBRARY WHERE CATEGORY = '"+category+"'");
						if(rs.next())
						{
						   title = rs.getString("TITLE");  
							author = rs.getString("AUTHOR");  
							subject = rs.getString("SUBJECT");  
							bookid = rs.getString("BOOKID");  
							
							jt_title.setText(title);
							jt_author.setText(author);
							jt_subject.setText(subject);
							jt_bookid.setText(bookid);
						}
						else
						{
						   JOptionPane.showMessageDialog(null,"No books were found under thos subject.");
							jt_title.setText("");	
							jt_author.setText("");
							jt_subject.setText("");
							jt_category.setText("");
							jt_bookid.setText("");
						}
					}
					catch(Exception err)
					{ 
					   JOptionPane.showMessageDialog(null,err.getMessage());
					}
				}
				
				if(e.getSource()==jb_next)
				{
				   try{  
					   if(rs.next())
						{
						   title = rs.getString("TITLE");  
							author = rs.getString("AUTHOR");  
							subject = rs.getString("SUBJECT");  
							bookid = rs.getString("BOOKID");  
							
							jt_title.setText(title);
							jt_author.setText(author);
							jt_subject.setText(subject);
							jt_bookid.setText(bookid);
						}
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}
				
				if(e.getSource()==jb_prev)
				{
				   try{  
					   if(rs.previous())
						{
						   title = rs.getString("TITLE");  
							author = rs.getString("AUTHOR");  
							subject = rs.getString("SUBJECT");  
							bookid = rs.getString("BOOKID");  
							
							jt_title.setText(title);
							jt_author.setText(author);
							jt_subject.setText(subject);
							jt_bookid.setText(bookid);
						}
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}
				if(e.getSource()==jb_last)
				{
				     
					try{  
					   rs.last();
					   title = rs.getString("TITLE");  
					 	author = rs.getString("AUTHOR");  
						subject = rs.getString("subject");  
						bookid = rs.getString("BOOKID");  
							
						jt_title.setText(title);
						jt_author.setText(author);
						jt_subject.setText(subject);
						jt_bookid.setText(bookid);
						
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}
				if(e.getSource()==jb_first)
				{
				     
					try{  
					   rs.first();
					   title = rs.getString("TITLE");  
						author = rs.getString("AUTHOR");  
						subject = rs.getString("subject");  
						bookid = rs.getString("BOOKID");  
							
						jt_title.setText(title);
						jt_author.setText(author);
						jt_subject.setText(subject);
						jt_bookid.setText(bookid);	
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}	
			}
		};
		jb_search.addActionListener(al);
		jb_next.addActionListener(al);
		jb_prev.addActionListener(al);
		jb_first.addActionListener(al);
		jb_last.addActionListener(al);
	}
	
	public void acceptAuthor()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   if(e.getSource()==jb_search)
				{
				   author = jt_author.getText();
					
					try{
					   S = con.createStatement();
					   rs = S.executeQuery("SELECT * FROM LIBRARY WHERE author = '"+author+"'");
						if(rs.next())
						{
						   title = rs.getString("TITLE");  
							category = rs.getString("category");  
							subject = rs.getString("SUBJECT");  
							bookid = rs.getString("BOOKID");  
							
							jt_title.setText(title);
							jt_category.setText(category);
							jt_subject.setText(subject);
							jt_bookid.setText(bookid);
						}
						else
						{
						   JOptionPane.showMessageDialog(null,"No books were found under thos subject.");
							jt_title.setText("");	
							jt_author.setText("");
							jt_subject.setText("");
							jt_category.setText("");
							jt_bookid.setText("");
						}
					}
					catch(Exception err)
					{ 
					   JOptionPane.showMessageDialog(null,err.getMessage());
					}
				}
				
				if(e.getSource()==jb_next)
				{
				   try{  
					   if(rs.next())
						{
						   title = rs.getString("TITLE");  
							category = rs.getString("category");  
							subject = rs.getString("SUBJECT");  
							bookid = rs.getString("BOOKID");  
							
							jt_title.setText(title);
							jt_category.setText(category);
							jt_subject.setText(subject);
							jt_bookid.setText(bookid);
						}
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}
				
				if(e.getSource()==jb_prev)
				{
				   try{  
					   if(rs.previous())
						{
						   title = rs.getString("TITLE");  
							category = rs.getString("category");  
							subject = rs.getString("SUBJECT");  
							bookid = rs.getString("BOOKID");  
							
							jt_title.setText(title);
							jt_category.setText(category);
							jt_subject.setText(subject);
							jt_bookid.setText(bookid);
						}
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}
				if(e.getSource()==jb_last)
				{
				     
					try{  
					   rs.last();
					   title = rs.getString("TITLE");  
					 	category = rs.getString("category");  
						subject = rs.getString("subject");  
						bookid = rs.getString("BOOKID");  
							
						jt_title.setText(title);
						jt_category.setText(category);
						jt_subject.setText(subject);
						jt_bookid.setText(bookid);
						
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}
				if(e.getSource()==jb_first)
				{
				     
					try{  
					   rs.first();
					   title = rs.getString("TITLE");  
						category = rs.getString("category");  
						subject = rs.getString("subject");  
						bookid = rs.getString("BOOKID");  
							
						jt_title.setText(title);
						jt_category.setText(category);
						jt_subject.setText(subject);
						jt_bookid.setText(bookid);	
					}
					catch(Exception er)
					{
					   System.exit(0);
					}   
				}	
			}
		};
		jb_search.addActionListener(al);
		jb_next.addActionListener(al);
		jb_prev.addActionListener(al);
		jb_first.addActionListener(al);
		jb_last.addActionListener(al);
	}							
						  
	public static void main(String[] args)
	{
	   new Subject();
	}
}			
		