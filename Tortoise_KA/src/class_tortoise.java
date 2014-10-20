// Kunal Agrawal
// Tort class- 21/11/2011
import java.util.Random;

public class class_tortoise 
{
	// Instantiating private variables  ==================
	private int tposition;
	private int hposition;
	private int current;
	private boolean ouch;
	private int a;

	Random generator=new Random();
	char [] hare=new char[70];
	char [] tort=new char[70];

	// DEFAULT, Sets all values to zero ==================
	public void def()
	{
		tposition=hposition=current=0;
		ouch=false;
		for(int i=0;i<70;i++)
		{
			hare[i]='-';
			tort[i]='-';
		}
		hare[0]='H';
		tort[0]='T';
	}

	public void move()
	{
		current=generator.nextInt(10);

		tort[tposition]='-';
		// Tortoise move calculation ==================
		if(current<=4)
			tposition+=3;
		else if(current>=5&&current<=6)
			tposition-=6;
		else if(current>=7&&current<=9)
			tposition+=1;
		
		hare[hposition]='-';
		// Hare move calculation ==================
		if(current<=1)
			hposition+=0;
		else if(current>=2&&current<=3)
			hposition+=9;
		else if(current==4)
			hposition-=12;
		else if(current>=5&&current<=7)
			hposition+=1;
		else if(current>=8&&current<=9)
			hposition-=2;
		
		// Makes sure hare and tortoise don't go below 0
		// and above 69 ==================
		if(tposition<0) 
			tposition=0;
		if(hposition<0)
			hposition=0;
		if(tposition>69)
			tposition=69;
		if(hposition>69)
			hposition=69;
		
		tort[tposition]='T';
		hare[hposition]='H';
	}
	
	public void display()
	{
		System.out.println("\n\n\n\n");
		while(tposition<70&&hposition<70)
		{
			// Printing hare's path  ==================
			System.out.print("\n|");
			for(int i=0;i<70;i++)
				System.out.print(hare[i]);
			System.out.print("|\n|");
			
			// Printing tortoise's path  ==================
			for(int i=0;i<70;i++)
				System.out.print(tort[i]);
			System.out.print("|");
			
			// Printing OUCH and 
			// DANCING MAN when there is an OUCH ==========
			if(tposition==hposition&&ouch)
			{
				System.out.println();
				for(int i=0;i<tposition+2;i++)
					System.out.print(tposition+1==i?"OUCH!!!":" ");
				for(int k=tposition+8;k<80;k++)
					System.out.print(" ");
				System.out.println(" 0 ");
				for(int k=0;k<80;k++)
					System.out.print(" ");
				System.out.println("~|~");
				for(int k=0;k<80;k++)
					System.out.print(" ");
				System.out.println("_|_");
			}
			// Printing DANCING MAN during the race ==============
			else
			{
				System.out.println("         0 ");
				for(int k=0;k<80;k++)
					System.out.print(" ");
				if(a%2==0)
				{
					System.out.println("\\|");
					for(int k=0;k<80;k++)
						System.out.print(" ");
					System.out.println(" |\\");
				}
				else
				{
					System.out.println(" |/");
					for(int k=0;k<80;k++)
						System.out.print(" ");
					System.out.println("/|");
				}
				for(int k=0;k<80;k++)
					System.out.print(" ");
				System.out.println("/ \\");
				ouch=true;
				a++;
			}
			
			// Ends the loop if tie or either wins ==================
			// Else, go back in the move method
			if(tposition==69&&hposition==69)
				tposition=hposition=70;
			else if(tposition==69)
				tposition=70;
			else if(hposition==69)
				hposition=70;
			else
				move();
			
			// Time delay ==================
			try { Thread.sleep(600); }
			catch(InterruptedException ie) 
			{System.out.println(ie.getMessage());}
		}

		// Output  ==================
		if(tposition==hposition)
			System.out.print("\nIt's a tie.\n\n");
		else if(tposition==70)
			System.out.print("\nTORTOISE WINS!!! YAY!!!\n\n");
		else if(hposition==70)
			System.out.print("\nHare wins. Yuch.\n\n");
	}
}