import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Practise
{
   JFrame jf;
	JPanel jp;
	JRadioButton jrb;
	JList jl;
	JTable jtb;
	JTextField jt;
	JTextArea jta;
	JButton jb;
	JScrollPane jsp;
	
	public Practise()
	{
	   jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(300,100);
		jf.setSize(300,300);
		
		jp = new JPanel();
		jf.add(jp);
		add();
		
		jf.setVisible(true);
	}
	
	public void add()
	{
	   jb = new JButton("Click");
		String[] options = {":)",":P",":("};
		jt = new JTextField(8);
		jl = new JList(options);
		jrb = new JRadioButton("Hi");
		jta = new JTextArea(5,10);
		jtb = new JTable(5,5);
		jsp = new JScrollPane(jtb);
		jp.add(jsp);
		jp.add(jt);
		jp.add(jta);
		jp.add(jb);
		jp.add(jl);
		jp.add(jrb);
	}	
		
}

public class PractiseMain
{
   public static void main(String[] args)
	{
	   new Practise();
	}
}			
		