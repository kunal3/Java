// Board class for displaying and updating the board

import java.awt.Color;
import apcslib.*;

public class board
{
	// Instantiating instance variables  =================
    private int [][] pos=new int [10][10];
    private SketchPad paper=new SketchPad(800,800,0);
    private DrawingTool pencil=new DrawingTool(paper);
    private int counter;
    private int squareSize;
 
    // Constructor for Board class  =================
    public board()
    {
        counter=0;
        squareSize=0;
    }
   
    // Clears the board for re-run  =================
    public void clearBoard()
    {
        pencil.up();
        pencil.move(0,0);
        pencil.down();
        pencil.setColor(Color.white);
        pencil.fillRect(800, 800);
        pencil.setColor(Color.black);
        for(int i=0;i<10;i++)
        	for(int j=0;j<10;j++)
        		pos[i][j]=0;
    }
    
    // Creates the board once every run  =================
    public void createBoard(int size)
    {
        clearBoard();
   
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
                pos[i][j]=0;
        
        if(size<10)
            for(int i=size;i<10;i++)
                for(int j=size;j<10;j++)
                    pos[i][j]=-1;
        
        if(size<9)
            squareSize=75;
        else
            squareSize=60;

        for(int i=0;i<size;i++)
        {
            pencil.up();
            pencil.move(-350,281.25-i*squareSize);
            pencil.down();
            pencil.drawString(""+i);
        }
        for(int i=0;i<size;i++)
        {
            pencil.up();
            pencil.move(-275+i*squareSize,340);
            pencil.down();
            pencil.drawString(""+i);
        }
        pencil.up();
        pencil.move(-281.25,281.25);
        pencil.down();
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
            {
                pencil.up();
                pencil.move(-281.25+j*squareSize, 281.25-i*squareSize);
                pencil.down();
                pencil.drawRect(squareSize, squareSize);
            }
    }
    
    // Takes in valid moves and displays them  =================
    public void display(int a, int b, boolean firstTime, int lastx, int lasty)
    {
        pos[a][b]=++counter;
        //System.out.print("ab="+pos[a][b]+" ");
        if(!firstTime)
        {
            pencil.up();
            pencil.move(-281.25+lastx*squareSize, 281.25-lasty*squareSize);
            pencil.down();
            pencil.setColor(Color.white);
            pencil.fillRect(20, 20);
            pencil.setColor(Color.black);
            pencil.drawString(String.valueOf(pos[lastx][lasty]));
        }
        pencil.up();
        pencil.move(-281.25+a*squareSize, 281.25-b*squareSize);
        pencil.down();
        pencil.drawString("K");
    }
    
    // Set and get methods for instance variables =================
    public void setCounter(int count)
    {
    	counter=count;
    }
    
    public int getCounter()
    {
    	return counter;
    } 
    
    public int getPosition(int a,int b)
    {
    	return pos[a][b];
    }
}