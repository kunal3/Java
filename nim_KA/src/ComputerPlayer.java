// ====== Kunal Agrawal ========= Nim ===================
// 				Computer player class

public class ComputerPlayer extends Player 
{
	// instantiating computer's private variables
	private boolean []valid=new boolean[4];
	private int [] match=new int[4];
	private int nextRow=0;
	private int nextMatch=0;
	private int next=0;
	
	// default constructor =================================
	public ComputerPlayer(String name) 
	{
		super(name);
	}
	
	public String getPrompt()
	{
		return "Hmm... Let me think...(Press Enter)";
	}
	
	public String getWinMessage()
	{
		return "You have lost, human. Panariello laughs at you.";
	}
		
	// takes in the next move to make in the form of 
	// next row*10 + nextMatch
	// so the rows and matches can be combined in one int
	public void makeMove(int nextMove)
	{
		next=nextMove;
		nextMatch=next%10;
		nextRow=(next-nextMatch)/10;
		match[nextRow]-=nextMatch;
		if(match[nextRow]==0)
			valid[nextRow]=false;
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