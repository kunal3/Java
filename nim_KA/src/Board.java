// ====== Kunal Agrawal ========= Nim ===================
// 				Board class

import java.util.Random;

public class Board 
{
	// instantiating board's private variables
	private int [] matches=new int[4];
	private boolean [] valid=new boolean[4];

	// smart computer's private  variables
	private int [] resetMatch= new int [4];
	Random generator=new Random();
	private int [][] smart1=new int [4][8];
	private int [][] smart2=new int [4][8];
	private int [][] record=new int [4][8];
	private int alternate=-1;
	private int pickRow=0;
	private int pickMatch=0;

	// default constructor
	public Board()
	{
		randomize();
	}
	
	//	outputs the board after every move
	public void drawBoard()
	{
		for(int i=0;i<4;i++)
		{
			System.out.print("Row "+(i+1)+": ");
			for(int j=0;j<matches[i];j++)
				System.out.print(" |");
			System.out.println();
		}
		System.out.println();
	}
	
	// checks if user has won
	public boolean hasWon()
	{
		int counter=0;
		for(int i=0;i<4;i++)
			if(valid[i]==false)
				counter++;
		if(counter==4)
			return true;
		else return false;
	}

	// Smart's methods start ================================
	
	// creates a new random board in the beginning of the match
	public void randomize()
	{
		for(int i=0;i<4;i++)
		{
			valid[i]=true;
			matches[i]=generator.nextInt(7)+2;
			resetMatch[i]=matches[i];
		}
		for(int i=0;i<4;i++)
			for(int j=0;j<8;j++)
				smart1[i][j]=smart2[i][j]=0;
	}
	
	// instead of creating a new board, this resets the board
	// after a game has ended and the board is empty and the
	// user wants to go again
	public void resetBoard()
	{
		for(int i=0;i<4;i++)
		{
			matches[i]=resetMatch[i];
			valid[i]=true;
		}
		for(int i=0;i<4;i++)
			for(int j=0;j<8;j++)
				smart1[i][j]=smart2[i][j]=0;
	}
	
	// this method chooses which move to make next based on 
	// the 'record' array which has data from computer matches
	// returns decision in the form of next row*10 + nextMatch
	public int sortingDecision()
	{
		int [] high=new int [4];
		int [] sub=new int [4];
		int match=0;
		int row=0;
		
		// this for loop chooses the number of matches to
		// go "behind" and check the record array, in case
		// there's less than three matches remaining on board
		for(int i=0;i<4;i++)
			for(int j=2;j>=0;j--)
				if(matches[i]>j)
				{
					sub[i]=++j;
					j=0;
				}

		// Setting high equal to the minimum possible
		// value in record
		for(int i=0;i<4;i++)
			high[i]=-50000;

		// if there are matches left in the row,
		// check the record from the number of columns
		// specified in 'sub' array to the current column
		for(int i=0;i<4;i++)
			for(int j=(matches[i]-sub[i]);j<matches[i];j++)
				if(record[i][j]>=high[i])
					high[i]=record[i][j];

		// selects the highest value from all rows
		match=high[0];
		for(int i=0;i<4;i++)
			if(high[i]>match)
			{		
				row=i;
				match=high[i];
			}
		
		// finds the row and column of highest record
		for(int i=0;i<4;i++)
			for(int j=0;j<matches[i];j++)
				if(record[i][j]==match)
					match=matches[i]-j;

		return (match+row*10);
	}
	
	// the computer plays itself in this loop, making 
	// random but valid moves
	public void calculate()
	{
		alternate++;
		do{
			pickRow=generator.nextInt(4);
		}while(valid[pickRow]==false);
		
		do{
			pickMatch=generator.nextInt(3)+1;
		}while(matches[pickRow]-pickMatch<0);

		matches[pickRow]-=pickMatch;
		
		if(alternate%2==0)
			smart1[pickRow][matches[pickRow]]=1;
		else
			smart2[pickRow][matches[pickRow]]=1;
		if(matches[pickRow]==0)
			valid[pickRow]=false;
	}
	
	// records moves by winning computer as good moves
	// and loosing computer as bad moves
	public void record(int [][] a, int [][]b)
	{
		if(alternate%2==0)
			for(int i=0;i<4;i++)
				for(int j=0;j<8;j++)
				{
					if(a[i][j]==1)
						record[i][j]++;
					if(b[i][j]==1)
						record[i][j]--;
				}
		else
			for(int i=0;i<4;i++)
				for(int j=0;j<8;j++)
				{
					if(b[i][j]==1)
						record[i][j]++;
					if(a[i][j]==1)
						record[i][j]--;
				}
	}
	// smart's methods end ================================

	// Gets and sets =================================
	public int [][] getSmart1()
	{
		return smart1;
	}
	
	public int [][] getSmart2()
	{
		return smart2;
	}

	public int [] getMatches()
	{
		return matches;
	}
	
	public boolean [] getValid()
	{
		return valid;
	}

	public void setMatches(int [] a)
	{
		for(int i=0;i<4;i++)
			matches[i]=a[i];
	}
	
	public void setValid(boolean [] a)
	{
		for(int i=0;i<4;i++)
			valid[i]=a[i];
	}

}
