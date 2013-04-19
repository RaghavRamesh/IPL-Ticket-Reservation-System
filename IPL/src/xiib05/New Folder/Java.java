import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Library
{
   JFrame jf;
	JPanel jp;
	JLabel jl_bookid,jl_author,jl_title,jl_subject,jl_category;
	JTextField jt_bookid,jt_author,jt_title,jt_subject,jt_category;
	JButton jb_insert,jb_find,jb_details;
	Connection con;
	ResultSet rs;
	
	public Library()
	{
	   jf = new JFrame("");
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(10,300);
		
		jp = new JPanel();
		jf.add(jp);
		  
		buildLibrary();
		setUpDB();
		accept();
		jf.pack();
	   jf.setVisible(true);
	}		
		
	public void buildLibrary()
	{
	   jl_bookid = new JLabel("Book ID:");
		jl_author = new JLabel("Author:");
		jl_title = new JLabel("Title:");
		jl_subject = new JLabel("Subject:");
		jl_category = new JLabel("Category:");
		
		jt_bookid = new JTextField(4);
		jt_author = new JTextField(15);
		jt_title = new JTextField(15);
		jt_subject = new JTextField(15);
		jt_category = new JTextField(3);
		
		jb_insert = new JButton("INSERT");
		jb_find = new JButton("FIND");
		jb_details = new JButton("DETAILS");
		
		jp.add(jl_bookid);
		jp.add(jt_bookid);
		jp.add(jl_author);
		jp.add(jt_author);
		jp.add(jl_title);
		jp.add(jt_title);
		jp.add(jl_subject);
		jp.add(jt_subject);
		jp.add(jl_category);
		jp.add(jt_category);
		
		jp.add(jb_insert);
		jp.add(jb_find);
		jp.add(jb_details);
				
	}
	
	public void setUpDB()
	{
	   String URL = "jdbc:mysql://localhost:3306/library";
		try{
		   Class.forName("com.mysql.jdbc.Driver");
		   con = DriverManager.getConnection(URL,"root","");
			
			//if(con.isClosed())JOptionPane.showMessageDialog(null,"CONNECTION FAILED");
			//else JOptionPane.showMessageDialog(null,"CONNECTION SUCCESSFUL");
		}
		catch(Exception error)
		{
		   JOptionPane.showMessageDialog(null,""+error.getMessage());
		}		
	}
	
	public void accept()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   if(e.getSource()==jb_insert)
				{
				   String bookid = jt_bookid.getText();
					String author = jt_author.getText();
					String title = jt_title.getText();
					String category = jt_category.getText();
					String subject = jt_subject.getText();
				
					try{
				   	Statement S = con.createStatement();
						int r =S.executeUpdate("INSERT INTO LIBRARY VALUES('"+bookid+"','"+title+"','"+author+"','"+subject+"','"+category+"')");
						if(r==1)
					  		JOptionPane.showMessageDialog(null,"Book insertion successful");
						else  
					  		JOptionPane.showMessageDialog(null,"Book insertion failed");
			   	}
				
					catch(Exception er)
					{
				   	JOptionPane.showMessageDialog(null,"insertion" +er.getMessage());
					}			  
			
					System.out.print(""+bookid+author+title+category+subject);
				}
				
				if(e.getSource()==jb_find)
				{
				   String bookid = jt_bookid.getText();
					try{
						Statement S = con.createStatement();
						ResultSet rs;
						rs = S.executeQuery("SELECT * FROM LIBRARY WHERE BOOKID = "+bookid);
						if(rs.next())
						{
							String author = rs.getString("AUTHOR");
							String title = rs.getString("TITLE");
							String subject = rs.getString("SUBJECT");
							String category = rs.getString("CATEGORY");
							
							jt_author.setText(author+"");
							jt_subject.setText(subject+"");
							jt_title.setText(title+"");
							jt_category.setText(category+"");
						}
						else
						{
						   JOptionPane.showMessageDialog(null,"no book matching the given id was found");
							jt_bookid.setText("");
							jt_author.setText("");
							jt_subject.setText("");
							jt_title.setText("");
							jt_category.setText("");	
						}	
					}
					catch(Exception er)
					{
					   JOptionPane.showMessageDialog(null,er.getMessage());
					}		
				}
				
				if(e.getSource()==jb_details)
				{
				   String title = jt_title.getText();
					try{
					   Statement S = con.createStatement();
						ResultSet rs;
						rs = S.executeQuery("SELECT * FROM LIBRARY WHERE TITLE = "+title);
						if(rs.next())
						{
							String author = rs.getString("AUTHOR");	
							String bookid = rs.getString("BOOKID");
							String category = rs.getString("CATEGORY");
							String subject = rs.getString("SUBJECT");
							
							jt_author.setText(author+"");
							jt_bookid.setText(bookid+"");
							jt_category.setText(category+"");
							jt_subject.setText(subject+"");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"No book matching the given title was found");
							jt_bookid.setText("");
							jt_author.setText("");
							jt_subject.setText("");
							jt_title.setText("");
							jt_category.setText("");
						}
					}
					catch(Exception er)
					{
					   JOptionPane.showMessageDialog(null,er.getMessage());
					}
				}				
			}		
		};
		jb_insert.addActionListener(al);
		jb_find.addActionListener(al);
		jb_details.addActionListener(al);
	}			
				
	
	public static void main(String[] args)
	{
	   new Library();
	}
}		
		