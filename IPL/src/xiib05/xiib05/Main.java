import javax.swing.*;
class Main
{
   
	static void check(int x)
	{
		
		if(x==1)
	    JOptionPane.showMessageDialog(null,"True");
		else
		 JOptionPane.showMessageDialog(null,"False"); 
	}
	
	public static void main(String[] args)
	{
	   int i=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter i:"));  
		check(i);
		i++;
		check(i);
	}
}			 
		
	