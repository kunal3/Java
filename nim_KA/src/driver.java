// ====== Kunal Agrawal ========= Nim ===================
// 				driver class

// human and computer player both work,
// computer is smart and should win noticeably more
// than a random moves selecting computer.
// a flaw-if you choose to play reversed version, computer
// doesn't know that and it basically aims for loosing
import javax.swing.JOptionPane;

public class driver
{
	public static void main(String [] args)
	{
		int currentPlayer=0;
		int firstChoice=0;
		int countr=0;
		int playAgain=1;
		boolean comp=false;
		boolean reversed=false;
		boolean firstTime;
		Player players[]=new Player[2];
		Board board=new Board();
		
		while(playAgain==1)
		{
			// resetting variables for play again
			firstChoice=0;
			countr=0;
			reversed=false;
			firstTime=true;
			
			// Inputting player type and names =======================
			currentPlayer=Integer.parseInt(JOptionPane.showInputDialog
					("Input 0 for human and 1 for computer players"));
			if(currentPlayer==0)
			{
				comp=false;
				players[0]=new HumanPlayer(JOptionPane.showInputDialog
							("Enter 1st human player's name"));
				players[1]=new HumanPlayer(JOptionPane.showInputDialog
							("Enter 2nd human player's name"));
			}
			else if(currentPlayer==1)
			{
				comp=true;
				players[0]=new HumanPlayer(JOptionPane.
						showInputDialog("Enter human player's name"));
				players[1]=new ComputerPlayer(JOptionPane.
						showInputDialog("Enter computer player's name"));
			}
			reversed=(Integer.parseInt(JOptionPane.showInputDialog(
					"Do you want to play the reversed version? (0/1)")
					)==1?true:false);
	
			// smart begin===========================================
			// this piece of code runs 50,000 times where the computer
			// plays itself using random values.
			// If computer A wins and computer B loses, all the moves
			// made by A are recorded as good moves in an array and 
			// B's moves are recorded as bad moves. Using the data at
			// the end of 50,000 runs, the computer plays the human.
			// (This loop takes less than a second to run 50,000 times)
			if(comp&&firstTime)
			{
				firstTime=false;
				board.randomize();		
				do
				{
					board.resetBoard();
					do
					{
						board.calculate();
					}while(!board.hasWon());
					
					board.record(board.getSmart1(),board.getSmart2());
					countr++;
				}while(countr!=50000);
			}
			
			// Starting  player type and names =======================
			board.resetBoard();
			board.drawBoard();
			for(int i=0;i<2;i++)
				players[i].setMatches(board.getMatches());
			
			firstChoice=Integer.parseInt(JOptionPane.showInputDialog(
				"Who would like to go first? Player 1 or 2? (1/2)"));
	
			// Beginning game for both players until one win
			currentPlayer=(firstChoice==1?0:1);
			do{
				JOptionPane.showMessageDialog(null,
						(players[currentPlayer].getName()+"- "+
								players[currentPlayer].getPrompt()));
	
				for(int i=0;i<2;i++)
				{
					players[i].setMatches(board.getMatches());
					players[i].setValid(board.getValid());
				}
				if(comp && currentPlayer==1)
					players[currentPlayer].makeMove
					(board.sortingDecision());
				else
					players[currentPlayer].makeMove(0);
				
				board.setMatches(players[currentPlayer].getMatches());
				board.setValid(players[currentPlayer].getValid());
				board.drawBoard();
				
				currentPlayer=(currentPlayer==0?1:0);
			}while(!board.hasWon());
			// ends when either player wins
			currentPlayer=(currentPlayer==0?1:0);
	
			// Display winning messages 
			if(!reversed)
				JOptionPane.showMessageDialog(null,
						(players[currentPlayer].getName()+"- "+
								players[currentPlayer].getWinMessage()));
			else
			{
				currentPlayer=(currentPlayer==0?1:0);
				JOptionPane.showMessageDialog(null,
						(players[currentPlayer].getName()+"- "+
								players[currentPlayer].getWinMessage()));
			}
			
			// play again feature
			playAgain=Integer.parseInt(JOptionPane.showInputDialog
					("Do you want to play again? (0/1)"));
		}
	}
}