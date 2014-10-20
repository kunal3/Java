// Player class for human and computer player

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class player 
{
	// Instantiating instance variables ====================
    private int a;
    private int b;
    private int lasta;
    private int lastb;
    private int notAvailable;
    private int applicable;
    private int boardSize;
    private int smart;
    private boolean valid;
    private boolean firstTime;
    private board chess=new board();
    
    // Supposedly smart computer's variables =================
    private int [][] compPos=new int[10][10];
    private int compCounter=0;
    private int erase;
    private int cantSelectA;
    private int cantSelectB;
    
    JFrame f =  new JFrame("Position");
    Random generator=new Random();

    // Constructor of Player class =================
    public player()
    {
        a=0;
        b=0;
        lasta=0;
        lastb=0;
        smart=0;
        notAvailable=0;
        valid=false;
        applicable=0;
        firstTime=true;
        boardSize=0;
        f.setLocation(750,150);
    }
    
    // Comes here if user selects human player =================
    public void player_move()
    {
        update_board();
    }
    
    // Random moves-computer method ===========================
    public void computerMove(int size)
    {
        smart=Integer.parseInt(JOptionPane.showInputDialog(null,
                "Do you want the computer to be smart(enter 1)" +
                " or pick random numbers(enter 0)?"));
               
        if(smart==1) // If user selects smart computer ==========
        {
	        if(boardSize<8 && boardSize>4)
	        {
		        for(int i=0;i<10;i++)
		        	for(int j=0;j<10;j++)
		        		compPos[i][j]=-1;
		        for(int i=0;i<boardSize;i++)
		        	for(int j=0;j<boardSize;j++)
		        		compPos[i][j]=0;
		        compCounter=0;
		        smartComputer();
	        }
	        else
	        	JOptionPane.showMessageDialog(null, "Sorry this " +
	        			"only works for board sizes less than " +
	        			"eight 8 and greater than 4");
        }
        else if(smart==0) // Random moves-computer ================
        {
        	compCounter=0;
            while(!noValidMovesLeft() || !winner())
            {
                lasta=a;
                lastb=b;
                if(firstTime)
                    do
                    {
                        b=Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Where do you want the computer to begin? Column: "));
                        a=Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Where do you want the computer to begin? Row: "));
                    }while(a>(boardSize-1)||b>(boardSize-1));
        
                // Selects next move if valid moves left =================
                else if(!noValidMovesLeft())
                    do
                    {
                        a=generator.nextInt(boardSize);
                        b=generator.nextInt(boardSize);
                    }while(!valid_move());
                setValid(valid_move());
                if(valid)
                    update_board();
                firstTime=false;
                
                if(!noValidMovesLeft())
                {
                    f.setVisible(true);
                    JOptionPane.showMessageDialog(f, "Press enter for next move",
                            "Next Computer move", JOptionPane.OK_CANCEL_OPTION);
                    f.setVisible(false);
                }
                
                // If no valid moves left  ===============================    
                else if(noValidMovesLeft())
                {
                    JOptionPane.showMessageDialog(null, 
                            "Computer has no valid moves left.");
                    setBoardCounter(boardSize*boardSize);
                }
            }
        }
    }
     
    // If valid move, displays it.
    // Else gives error message  ==================================
    public void update_board()
    {
        if(valid)
        {
            chess.display(a,b,firstTime, lasta,lastb);
            lasta=a;
            lastb=b;
        }
        else
            JOptionPane.showMessageDialog(null,
                "Not a valid move. Try again");
    }
    
    // Checks if it is a valid move and returns true if yes =============
    public boolean valid_move()
    {
        if(firstTime)
        {
        	lasta=0;
        	lastb=0;
            return true;
        }
        else if(((a-lasta)==2 || (a-lasta)==-2) && chess.getPosition(a,b)==0)
        {
            if((b-lastb)==1 || (b-lastb)==-1)
                return true;
        }
        else if(((a-lasta)==1 || (a-lasta)==-1) && chess.getPosition(a,b)==0)
        {
            if((b-lastb)==2 || (b-lastb)==-2)
                return true;
        }
        return false;
    }
    
    // Checks if no places left to move, returns true if yes =================
    public boolean noValidMovesLeft()
    {
        notAvailable=applicable=0;
        if((a-2>=0)&&(b+1<=(boardSize-1)))
            applicable++;
        if((a-1>=0)&&(b+2<=(boardSize-1)))
            applicable++;
        if((a+2<=(boardSize-1))&&(b+1<=(boardSize-1)))
            applicable++;
        if((a+1<=(boardSize-1))&&(b+2<=(boardSize-1)))
            applicable++;
        if((a-2>=0)&&(b-1>=0))
            applicable++;
        if((a-1>=0)&&(b-2>=0))
            applicable++;
        if((a+2<=(boardSize-1))&&(b-1>=0))
            applicable++;
        if((a+1<=(boardSize-1))&&(b-2>=0))
            applicable++;
        if((a-2>=0)&&(b+1<=(boardSize-1))&&(chess.getPosition(a-2,b+1)!=0))
            notAvailable++;
        if((a-1>=0)&&(b+2<=(boardSize-1))&&(chess.getPosition(a-1,b+2)!=0))
            notAvailable++;
        if((a+2<=(boardSize-1))&&(b+1<=(boardSize-1))&&
        		(chess.getPosition(a+2,b+1)!=0))
            notAvailable++;
        if((a+1<=(boardSize-1))&&(b+2<=(boardSize-1))&&
        		(chess.getPosition(a+1,b+2)!=0))
            notAvailable++;
        if((a-2>=0)&&(b-1>=0)&&(chess.getPosition(a-2,b-1)!=0))
            notAvailable++;
        if((a-1>=0)&&(b-2>=0)&&(chess.getPosition(a-1,b-2)!=0))
            notAvailable++;
        if((a+2<=(boardSize-1))&&(b-1>=0)&&(chess.getPosition(a+2,b-1)!=0))
            notAvailable++;
        if((a+1<=(boardSize-1))&&(b-2>=0)&&(chess.getPosition(a+1,b-2)!=0))
            notAvailable++;
        if(applicable==notAvailable)
            return true;
        else return false;
    }
    
    // Checks if user or random moves-computer has won =============
    public boolean winner()
    {
        if(chess.getCounter()==(boardSize*boardSize))
            return true;
        else return false;
    }
    
    // Method for smartComputer, does not work completely. =========
    public void smartComputer()
    {
    	// solvable: 1,5,6,7,8
    	if(firstTime)
    	{
    		a=generator.nextInt(boardSize);
    		b=generator.nextInt(boardSize);
    	}
    	/*
		if(firstTime)
		{
			a=generator.nextInt(boardSize);
			b=generator.nextInt(boardSize);
		}
		else if(!noValidCompMoves())
		{
			lasta=a;
		    lastb=b;
			do{
				a=generator.nextInt(5)+lasta-2;
				b=generator.nextInt(5)+lastb-2;
				setValid(validCompMove());
			}while(!valid);
		}
		else
			valid=false;
		if(boardSize*boardSize==compCounter)
		{
			// This is if the computer finds a winning path.
			// I didn't develop this part because smartComputer
			// never worked.
			System.out.println("I found a winner!");
		}
		else if(valid || firstTime)
		{
			firstTime=false;
			compPos[a][b]=++compCounter;
			smartComputer();
		}
		else if(noValidCompMoves())
		{
			erase=compCounter;
			do
			{
				compCounter--;
				lasta=a;
				lastb=b;
				for(int i=0;i<boardSize;i++)
					for(int j=0;j<boardSize;j++)
						if(compPos[i][j]==compCounter)
						{
							a=i;
							b=j;
						}
			}
			while(noValidCompMoves());
			do{
				for(int j=0;j<boardSize;j++)
					for(int k=0;k<boardSize;k++)
						if(compPos[j][k]==erase)
							compPos[j][k]=0;
				erase--;
			}while(erase-compCounter!=0);
			cantSelectA=lasta;
			cantSelectB=lastb;
			lasta=a;
			lastb=b;
            do
            {
                a=generator.nextInt(5)+cantSelectA-2;
                b=generator.nextInt(5)+cantSelectB-2;
			    if(a==cantSelectA || b==cantSelectB)
			    	continue;
            }while(!validCompMove());
            smartComputer();
		}
		*/
    }
    
    // Method Specially for smart computer =================
    // Checks if computer selected valid move =================
    public boolean validCompMove()
    {
        if(firstTime)
            return true;
        else if((a>=0 && b>=0)&&(((a-lasta)==2 || 
        		(a-lasta)==-2))&& compPos[a][b]==0)
        {
            if((b-lastb)==1 || (b-lastb)==-1)
                return true;
        }
        else if((a>=0 && b>=0)&&(((a-lasta)==1 ||
        		(a-lasta)==-1)) && compPos[a][b]==0)
        {
            if((b-lastb)==2 || (b-lastb)==-2)
                return true;
        }
        return false;
    }
    
    // Method specially for smart computer =================
    // Checks if computer has any moves left =================
    public boolean noValidCompMoves()
    {
        notAvailable=applicable=0;
        if((a-2>=0)&&(b+1<=(boardSize-1)))
            applicable++;
        if((a-1>=0)&&(b+2<=(boardSize-1)))
            applicable++;
        if((a+2<=(boardSize-1))&&(b+1<=(boardSize-1)))
            applicable++;
        if((a+1<=(boardSize-1))&&(b+2<=(boardSize-1)))
            applicable++;
        if((a-2>=0)&&(b-1>=0))
            applicable++;
        if((a-1>=0)&&(b-2>=0))
            applicable++;
        if((a+2<=(boardSize-1))&&(b-1>=0))
            applicable++;
        if((a+1<=(boardSize-1))&&(b-2>=0))
            applicable++;
        if((a-2>=0)&&(b+1<=(boardSize-1))&&(compPos[a-2][b+1]!=0))
            notAvailable++;
        if((a-1>=0)&&(b+2<=(boardSize-1))&&(compPos[a-1][b+2]!=0))
            notAvailable++;
        if((a+2<=(boardSize-1))&&(b+1<=(boardSize-1))&&
        		(compPos[a+2][b+1]!=0))
            notAvailable++;
        if((a+1<=(boardSize-1))&&(b+2<=(boardSize-1))&&
        		(compPos[a+1][b+2]!=0))
            notAvailable++;
        if((a-2>=0)&&(b-1>=0)&&(compPos[a-2][b-1]!=0))
            notAvailable++;
        if((a-1>=0)&&(b-2>=0)&&(compPos[a-1][b-2]!=0))
            notAvailable++;
        if((a+2<=(boardSize-1))&&(b-1>=0)&&(compPos[a+2][b-1]!=0))
            notAvailable++;
        if((a+1<=(boardSize-1))&&(b-2>=0)&&(compPos[a+1][b-2]!=0))
            notAvailable++;
        if(applicable==notAvailable)
            return true;
        else return false;
    }
    
    // Set methods for setting instance variable in Player
    // And in Board class  ================= =================
    public void setFirst(boolean first)
    {
    	firstTime=first;
    }
    
    public void setBoardCounter(int count)
    {
        chess.setCounter(count);
    }
    
    public void createBoard(int size)
    {
        chess.createBoard(size);
    }
    
    public void setValid(boolean valid)
    {
        this.valid=valid;
    }
    
    public void setPos(int a, int b)
    {
        this.a=a;
        this.b=b;
    }
    
    public void setSize(int size)
    {
        boardSize=size;
    }
}