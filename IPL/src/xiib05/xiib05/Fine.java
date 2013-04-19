import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Fine
{
   JFrame jf;
	JPanel jp;
	JButton jb_next,jb_find,jb_prev;
	JLabel jl_membid,jl_bookid,jl_name,jl_issdate,jl_retdate;
	JTextField jt_membid,jt_bookid,jt_name,jt_issdate,jt_retdate;
	Connection con;
	Statement S;
	ResultSet rs;
	String name,bookid,membid,issdate,retdate;
	
	public Fine()
	{
	   jf = new JFrame();
		jf.setSize(300,300);
		jf.setLocation(10,100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jp = new JPanel();
		jf.add(jp);
		
		addComp();
		setUpDB();
		late();
		
		jf.pack();
		jf.setVisible(true);
	}
	
	public void setUpDB()
	{
	   String URL= "jdbc:mysql://localhost:3306/library";
		try
		{
		   Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,"root","");
			if(con.isClosed()) JOptionPane.showMessageDialog(null,"Connection Failed.");	
			else JOptionPane.showMessageDialog(null,"Connection Successful.");
		}
		catch(Exception er)
		{
		   JOptionPane.showMessageDialog(null,er.getMessage());
		}
	}
	
	public void addComp()
	{
	   jl_name = new JLabel("Name:");
		jl_issdate = new JLabel("Issue Date:");			
		jl_bookid = new JLabel("BookID:");
		jl_membid = new JLabel("Member ID:");
		jl_retdate = new JLabel("Return Date:");
		
		jt_name = new JTextField(10);
		jt_issdate = new JTextField(10);
		jt_bookid = new JTextField(10);
		jt_membid = new JTextField(10);
		jt_retdate = new JTextField(10);
		
		jb_next = new JButton("NEXT");
		jb_prev = new JButton("PREVIOUS");
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
		jp.add(jb_next);
		jp.add(jb_prev);
	}
	
	public void late()
	{
	   ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   if(e.getSource()==jb_find)
				{
				   try
					{
					   S = con.createStatement();
						rs = S.executeQuery("SELECT * FROM MEMBERS WHERE RETURNDATE < CURDATE()");
	               if(rs.next())
						{
						   membid = rs.getString("MEMBERID");
							bookid = rs.getString("BOOKID");
							name = rs.getString("NAME");
							issdate = rs.getString("ISSUEDATE");
							retdate = rs.getString("RETURNDATE");
							
							jt_membid.setText(membid);
							jt_bookid.setText(bookid);
							jt_name.setText(name);
							jt_issdate.setText(issdate);
							jt_retdate.setText(retdate);
						}
						else
						{
						   JOptionPane.showMessageDialog(null,"No late members.");	
						}
					}
					catch(Exception er)
					{
					   JOptionPane.showMessageDialog(null,er.getMessage());
					}
				}
				
				if(e.getSource()==jb_next)
				{
				   try{  
						if(rs.next())
						{
					   	membid = rs.getString("MEMBERID");
							bookid = rs.getString("BOOKID");
							name = rs.getString("NAME");
							issdate = rs.getString("ISSUEDATE");
							retdate = rs.getString("RETURNDATE");
							
							jt_membid.setText(membid);
							jt_bookid.setText(bookid);
							jt_name.setText(name);
							jt_issdate.setText(issdate);
							jt_retdate.setText(retdate);
						} 
					}
					catch(Exception er)
					{
					   JOptionPane.showMessageDialog(null,er.getMessage());
					}
				}		
				
				if(e.getSource()==jb_prev)
				{
				   try{  
						if(rs.previous())
						{
					   	membid = rs.getString("MEMBERID");
							bookid = rs.getString("BOOKID");
							name = rs.getString("NAME");
							issdate = rs.getString("ISSUEDATE");
							retdate = rs.getString("RETURNDATE");
							
							jt_membid.setText(membid);
							jt_bookid.setText(bookid);
							jt_name.setText(name);
							jt_issdate.setText(issdate);
							jt_retdate.setText(retdate);
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
		jb_prev.addActionListener(al);
		jb_next.addActionListener(al);
	}
								
	public static void main(String[] args)
	{
	   new Fine();
	}
}			