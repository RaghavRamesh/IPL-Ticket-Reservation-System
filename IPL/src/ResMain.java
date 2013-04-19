import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class Reservation
{
   JFrame jf;
   JPanel jp;
   JLabel jl_date,jl_month,jl_matfix,jl_paymode,jl_stand,jl_qty,jl_price;
   JComboBox jcb_dd,jcb_mm,jcb_mf,jcb_stand,jcb_matfix;
   JTextField jt_qty,jt_price;
   JButton jb_bb,jb_sub,jb_ticavail;JTextField[] jt = new JTextField[30];

   public Reservation()
   {
      jf = new JFrame("IPL");
	  jf.setSize(300,300);
	  jf.setLocation(500,300);
	  jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  jp = new JPanel();
	  jf.add(jp);

	  buildGUI();
	  //ticketsAvailable();

	  jf.setVisible(true);
   }
   
   void ticketsAvailable()
{
   ActionListener al = new ActionListener()
   { 
      public void actionPerformed(ActionEvent e)
	  {
	     if(jp!=null)
		 {
		   jf.remove(jp);
		   
         }
		 jp = new JPanel();
		 JTextField[] jt = new JTextField[30];
         for(int i=0;i<30;i++)
         {
            jt[i] = new JTextField(1);
	        jt[i].setBackground(Color.pink);
         }
         for(int i=0;i<30;i++)
            jp.add(jt[i]);
         jf.add(jp);
      }
   };
   jb_ticavail.addActionListener(al);
}
   public void buildGUI()
   {
      String[] datem = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	  String[] datea = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
	  String[] month = {"March","April"};
      String[] stand = {"A","B","C","D","D SPL","E or G","F","Pavilion","Roof Top"};
      String[] matfix = {"CSK  vs RCB","DD vs MI"};
	  jl_date = new JLabel("Date:");
	  jl_paymode = new JLabel("Payment Mode:");
	  jl_matfix = new JLabel("Match Fixtures:");
	  jl_qty = new JLabel("No of Tickets:");
	  jl_price = new JLabel("Price:");
	  jl_stand = new JLabel("Stand:");
	  jl_month = new JLabel("Month:");
	  
 
      jt_qty = new JTextField(2);
	  jt_price = new JTextField(5);

      jcb_mm = new JComboBox(month);
	  jcb_dd = new JComboBox(datea);
	  jcb_stand = new JComboBox(stand);
      jcb_matfix = new JComboBox(matfix);
	  jb_sub = new JButton("Submit");
	  jb_bb = new JButton("Bulk Booking");
      jb_ticavail = new JButton("Tickets Available");
	   
	  jp.add(jl_month);
	  jp.add(jcb_mm);

	  jp.add(jl_date);
	  jp.add(jcb_dd);
	  
	  jp.add(jl_matfix);
	  jp.add(jcb_matfix);
	  jp.add(jl_paymode);
	  jp.add(jl_stand);
	  jp.add(jcb_stand);
	  jp.add(jl_qty);
	  jp.add(jt_qty);
	  jp.add(jl_price);
	  jp.add(jt_price);
	  jp.add(jb_ticavail);
	  jp.add(jb_bb);
	  jp.add(jb_sub);
	}
}

class ResMain
{
   public static void main(String[] args)
   {
      new Reservation();
   }
}
