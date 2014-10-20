// ====== Kunal Agrawal ========= Nim ===================
// 				Human player's class

import javax.swing.JOptionPane;

public class HumanPlayer extends Player 
{
	// instantiating human's private variables
	private int[] match=new int[4];
	private boolean [] valid=new boolean[4];
	private int pickRow=0;
	private int pickMatch=0;
	
	// default constructor =================================
	public HumanPlayer(String name) 
	{
		super(name);
	}
	
	public String getPrompt()
	{
		return "Your turn...";
	}
	
	public String getWinMessage()
	{
		return "Congratulations! You won!";
	}
				
	// asks user to make a valid move
	public void makeMove(int a)
	{
		do
		{
			pickRow=Integer.parseInt(JOptionPane.showInputDialog
					("Pick a row between 1 and 4"));
			pickRow--;
			if(!valid[pickRow])
				JOptionPane.showMessageDialog(null,"Not a valid row",
						"Warning", JOptionPane.WARNING_MESSAGE);
		}while((pickRow>3 || pickRow<0) || !valid[pickRow]);
		
		do
		{
			pickMatch=Integer.parseInt(JOptionPane.showInputDialog
					("Pick number of matches between 1 and 3"));
			if(pickMatch>3 || pickMatch<1)
				JOptionPane.showMessageDialog(null, 
						"Only between 1 and 3",
						"Warning", JOptionPane.WARNING_MESSAGE);
			if(match[pickRow]-pickMatch<0)
				JOptionPane.showMessageDialog(null,
						"Not enough matches in the row. Try again",
						"Warning", JOptionPane.WARNING_MESSAGE);
		}while((pickMatch>3||pickMatch<1)||(match[pickRow]-pickMatch<0));
		match[pickRow]-=pickMatch;
		
		if(match[pickRow]==0)
			valid[pickRow]=false;
	}
	
	// Gets and sets =================================
	public void setMatches(int [] matches)
	{
		for(int i=0;i<4;i++)
			match[i]=matches[i];
	}
	
	public int[] getMatches()
	{
		return match;
	}
	
	public void setValid(boolean [] a)
	{
		for(int i=0;i<4;i++)
			valid[i]=a[i];
	}
	
	public boolean[] getValid()
	{
		return valid;
	}
}