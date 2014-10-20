// Kunal Agrawal
// Tort driver- 21/11/2011
import javax.swing.JOptionPane;

public class tortoise_driver 
{
	public static void main(String [] args)
	{
		// 
		class_tortoise y=new class_tortoise();
		int x=1;
		while(x==1)
		{
			y.def();
			System.out.println("\n\nBANG !!!!!");
			System.out.print("AND THEY'RE OF !!!!!\n\n");
			
			// Time delay  ==================
			try { Thread.sleep(600); }
			catch(InterruptedException ie) 
			{System.out.println(ie.getMessage());}
			
			// Calling the method which displays the race
			y.display();
			
			// Play again ==================
			x=Integer.parseInt(JOptionPane.showInputDialog(null,
					"Do you want to play again? (0/1): "));
		}
	}
}