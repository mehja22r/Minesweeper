import java.io.IOException;
import java.util.Scanner;
public class Grid {					//creating class called Grid
	public int x;					//declaring an int called x
	public int y;					//declaring an int named y
	private final int COLUMNS = 8;		//number of columns in grid is 8
	private final int ROWS = 8;			//number of rows is 8
	Square[][] square;					//declaring an array of class Square named square
	private int numberOfMines;			//declaring an int to hold number of mines in the game
	//private String gridObject2;
	//boolean gameStatus;					//gameStatus is a boolean var
	public Grid () {							//constructor for Grid
		square = new Square[ROWS][COLUMNS];		//square is placed for all rows and columns
		setSquares();							//calling setSquares function
		setNumberOfMines() ;					//calling function to set number of mines
		assignNeighboringMines () ;				//calling to function to assign number of mines
		printGrid() ;							//calling function to print grid
		userInput();							//calling function to check player inputs
	}

	public double getNumberOfMines() {			//getter function for number of mines
		return numberOfMines;					//return double
	}

	public void setNumberOfMines()				//function that sets number of mines in the game
	{
		numberOfMines = (int) Math.floor( .125*COLUMNS*ROWS );	//mines are 12.5% of cells
		System.out.println( "# of mines : " + numberOfMines );	//print how many mines are in the game
		for (int j=0; j<numberOfMines; j++)						//loop goes till it reaches number of mines in the game
		{
			//System.out.println( "j: " + j );

			int currRow = (int) Math.floor(Math.random()*ROWS);		//var to hold a random place in rows
			int currColumn = (int) Math.floor(Math.random()*COLUMNS);	//var to hold a random place in columns

			while( square[currRow][currColumn].getMine() == true )		//while square in the randomly selected spots holds a mine
			{
				currRow = (int) Math.floor(Math.random()*ROWS);			//var to hold a random place in rows
				currColumn = (int) Math.floor(Math.random()*COLUMNS);	//var to hold a random place in columns
			}


			square[currRow][currColumn].setMines();						//set mines in the randomly selected places of the grid
			System.out.println("Mine at: " + currRow  +" "+ currColumn  ); //print out where the mines are (for checking purposes)

		}
	}

	public void setSquares()							//function to set squares
	{
		for (int i=0; i<ROWS;i++) {						//loop to run till the number of rows
			for (int j=0; j<COLUMNS;j++) {				//loop runs until number of columns
				square[i][j] = new Square();			//place a square in each intersection of rows and columns in the grid
			}
		}
	}

	public void assignNeighboringMines () {			//function to assign neighboring mines for each cell
		int temp;									//temporary variable to hold number of mines around each individual cell
		for (int i=0; i<ROWS;i++) {					//loop to run till the number of rows
			for (int j=0; j<COLUMNS;j++) {			//loop to run till the number of columns
				temp = findNeighboringMines(i,j);	//calling function and storing return value in temp
				square[i][j].setIntValue(temp);		//setting an int value=to the number of neighboring mines
			}
		}
	}

	public int findNeighboringMines(int x, int y) {		//find neighboring mines around square
		int cellNum=0;									//number in cell is currently 0
		for (int i=x-1; i<=x+1;i++) {					//loops to check all adjacent squares
			for (int j=y-1; j<=y+1;j++) {
				if (i>-1 & i<ROWS & j>-1 & j<COLUMNS) {		//if i and j are within bounds
					if (square[i][j].getMine()==true) { 	//and one of the adjacent square holds a mine
						cellNum++;							//add 1 to cellNum
					}
				}
			}
		}
		square[x][y].setNeighboringMines(cellNum);		//assign cellNum to the square
		return cellNum;									//return number contained in the cell
	}

	public void printGrid()					//function to print out the grid
	{
		for (int i=0; i<ROWS;i++)			//loop that runs till the length of row
		{
			String row = "" ;				//put a blank string row for each row spot

			for (int j=0; j<COLUMNS;j++)		//loop runs till length of columns
			{
				row += square[i][j].showDisplay() + " " ;		//calling showDispaly function and placing appropriate string in the grid
			}

			System.out.println( row ) ;						//print out grid
		}
	}


	/*public void squareSelected (int x, int y) {						//when user selects a square
		if (square[x][y].getMine()==true) {							//if that square holds a mine
			
			gameStatus=true;										//make gameStatus true
		}
		else {
			square[x][x].setRevealed();								//

		}
	}*/
	
	public boolean gameWon() {						//function to check if player wins
		boolean b=true;								//set a boolean b to true
		for (int i=0; i<ROWS;i++) {					//loops to run till the length of rows and columns
			for (int j=0; j<COLUMNS;j++) {
				if ((square[i][j].getMine()==false) && (square[i][j].getRevealed()==false)) {
					//if square is not a mine and square is not revealed (that is has not been selected by player)
					//System.out.println("YOU WON!!!");
					b = false; 	//change b to false (game has not ended yet)
				}
			
			}
		}
		if (b==true) {						//if b is true
			printGrid();					//print gird
			System.out.println("YOU WON!!!");	//print winning message
			revealGrid();
			System.exit(0);						//exit the game
		}		
		return b;						//return boolean b
	}
	
	public void revealMines()							//function to reveal all mines at the end of the game (after losing)
	{
		for (int k=0; k<ROWS;k++) {						//double loops for each spot in the grid
			for (int l=0; l<COLUMNS;l++) {				
				if (square[k][l].getMine()==true) {		//if square is a mine
					square[k][l].setRevealed();			//reveal the square
				}
			}
		}
		printGrid();									//print the grid
	}
	
	
	public void revealGrid() {								//function to reveal the entire grid
		for (int i=0; i<ROWS;i++) {							//loops to locate each spot in the grid
			for (int j=0; j<COLUMNS;j++) {
				square[i][j].setRevealed();					//reveal all squares
			}
		}
	}

	/*private boolean userFinished (String aLine) {
		if (aLine.charAt(0) == 'q' || aLine.charAt(0) == 'Q'){ 
			return true;
		}
		else {
			return false;
		}
	}*/

	/*public void printPrompt(boolean error) {
		// prompt the user for the expected input
		if (error){
			System.out.print("\n Incorrect input. ");
		} 
		System.out.println("Please enter an integer or q (or Q) to quit.\n");
	}*/

	public void userInput () {			//function to deal with player inputs
		int x;				//declaring ints x and y
		int y;
		Scanner keyboard =  new Scanner(System.in);	//using scanner
		System.out.println("enter two integers separated by space, or q (or Q) to quit.\n"); //show user mesage at the beginning of the game
		while (keyboard.hasNextLine()) {				
			String line = keyboard.nextLine();			//line to hold user inputs
			if (line.equals("q") || line.equals("Q"))		//if user types in Q or q
			{	
				System.out.println("You Quit.");			//show quit message
				System.exit(0);								//exit game
			}
			Scanner lineScanner = new Scanner(line) ;
			if ((lineScanner.hasNextInt())==true) {			//if user types in ints separated by space
				
					x = lineScanner.nextInt();					//x is first int user types in
					y = lineScanner.nextInt();					//y is second int
				if (x<0 || x>=ROWS || y<0 || y>=COLUMNS ) {			//if out of bounds
					System.out.println("Please put in 2 two integers(within bounds) separated by a space" + x + " " + y);
					//indicate the user to type in correct ints and continue the game
					continue;
				}
				
			
			}
			else {					//if user did not type in ints
				System.out.println("Type in two ints");		//indicate the user to type in correct ints and continue the game
				continue;
			}
			square[x][y].setRevealed();			//reveal selected square
			reveal(x,y);						//call recursive function if user selected a square with int 0
			gameWon();							//call gameWOn function to check if user won
			if (square[x][y].getMine()==true) {		//if user selected a mine square
				System.out.println("you lose :(");		//user lost
				revealMines() ;							//reveal all mines
				//revealGrid();
				System.exit(0);							//exit game
			}
			//System.out.println("print grid");
			printGrid();								//print grid
			
			lineScanner.close() ;						//close scanner
		}	
		keyboard.close() ;
	}

	public void reveal(int i,int j) {				//recursive function for 0 int squares

		if (square[i][j].getMine()==true) {			//if square is a mine
			return;									//do nothing
		}
		
		findNeighboringMines(i,j);					//find neighboring mines around the square
		square[i][j].setRevealed();				//reveal square
		 if ((square[i][j].getIntValue()>0) || square[i][j].getMine()==true) { 
			 //if square does not have number 0 or if it's a mine
			 //System.out.println( "Border at " + i + " " + j );
			 
			 return; //do nothing
		 }

		 else {			//if square has 0 number
			 for (int k=i-1; k<=i+1;k++) {			//for all adjacent squares
					for (int l=j-1; l<=j+1;l++) {
						if (k>=0 && k<ROWS && l>=0 && l<COLUMNS ) 	//if within bounds
						{
							findNeighboringMines(k,l);			//find neighboring mines for those adjacent squares
							if (square[k][l].getRevealed()==false) //if square has not been revealed or selected
							{
								reveal(k, l);  //call reveal function 
							}
						}
						
					}
		 }
	 }
	}
	
	
	

	public static void main (String[] args) { //main function
		Grid myGrid=new Grid();  //call an instance of Grid
	}
}
