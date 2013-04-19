import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Chu
{  
   static JRadioButton jrb ;
   public static void main(String[] args)
	{
	   JFrame jf = new JFrame();
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel();
		jf.add(jp);
		String[] items = {"Item1", "Item2", "Item3", "Item4", "Item5"};
      JList jlist = new JList(items);
		jlist.setVisibleRowCount(4);
		JScrollPane jsp = new JScrollPane(jlist);
		jp.add(jsp);
		System.out.print(ListSelectionModel.SINGLE_SELECTION);
		
		JButton jb = new JButton("Clear");
		
		jrb = new JRadioButton("hi");
		jp.add(jrb); 
		jp.add(jb);
		
		ActionListener al = new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
			{
			   jrb.setSelected(false);
			}
		};
		jb.addActionListener(al);		
		jf.setVisible(true);
	}
}		