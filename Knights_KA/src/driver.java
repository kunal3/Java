// Kunal Agrawal ================= =================
// Knights- imitates the movement of a knight across a chess board.

// The human player works fine for all cases.
// The computer which selects random moves work fine for all cases.
// The computer with AI does not work as intended (classic)
// I included that code even though its not working because
// I spent more than 6 hours working on it

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class driver 
{
    public static void main(String[] args) 
    {
        int inputx=0;
        int inputy=0;
        int playAgain=1;
        int playerOrComp=0;
        int boardSize=0;
        boolean valid=false;
        boolean firstTime=true;
        player user=new player();
        
        // LEXI SMITH wrote the code for repositioning 
        // the boxes which asks for the next x and y coordinate
        //  of the knight using JFrame. =================
        JFrame f =  new JFrame("Position");
        f.setLocation(750,150);
        
        while(playAgain==1)
        {
	        do
	        {
	        	boardSize=Integer.parseInt(JOptionPane.showInputDialog(null,
	                    "What board size would you like? (max size=10)"));
	        }
	        while(boardSize>10);
	       
            user.setSize(boardSize);
            user.createBoard(boardSize);
            firstTime=true;
	            
            playerOrComp=Integer.parseInt(JOptionPane.showInputDialog(null,
                "Do you want the user(enter 1) or computer(enter 2) to play?"));
            if(playerOrComp==1)
                while(!user.winner())
                {
                    do
                    {
                        f.setVisible(true);
                        inputy=Integer.parseInt(JOptionPane.showInputDialog(f,
                            "Enter row: "));
                        inputx=Integer.parseInt(JOptionPane.showInputDialog(f,
                            "Enter column: "));
                        f.setVisible(false);
                        
                        if(inputx>(boardSize-1)||inputy>(boardSize-1))
                            JOptionPane.showMessageDialog(null,
                                "Value out of bounds", "Try Again",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    while(inputx>(boardSize-1) || inputy>(boardSize-1));
                    
                    // Setting instance variable in Player class ===========
                    user.setFirst(firstTime);
                    user.setPos(inputx, inputy);
                    valid=user.valid_move();
                    user.setValid(valid);
                    user.player_move();
                    if(user.winner())
                        JOptionPane.showMessageDialog(null, 
                        		"You have won!!");
                    else if(user.noValidMovesLeft()&&valid)
                    {
                        JOptionPane.showMessageDialog(null, 
                        	"You have no valid moves left. You lose.");
                        user.setBoardCounter(boardSize*boardSize);
                    }
                    firstTime=false;
                }
            else if(playerOrComp==2)
                user.computerMove(boardSize);
            user.setBoardCounter(0);
            playAgain=Integer.parseInt(JOptionPane.showInputDialog(null,
            "Program ended. Do you want to play again? (0/1)"));
        }
    }
}