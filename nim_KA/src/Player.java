// ====== Kunal Agrawal ========= Nim ===================
// 				Abstract Player class

public abstract class Player
{
	private String myName;

	// Constructor
	public Player(String name)
	{
		myName=name;
	}
	// Returns prompt to be displayed before the next move
	public abstract String getPrompt();
	
	// Returns a message to be displayed when this player has won
	public abstract String getWinMessage();
	
	// Called to initiate this player's next move
	public abstract void makeMove(int a);
	
	// Gets and sets =================================
	public abstract void setMatches(int [] a);
	public abstract int[] getMatches();
	public abstract void setValid(boolean [] a);
	public abstract boolean[] getValid();
	
	
	public String getName()
	{
		return myName;
	}
}