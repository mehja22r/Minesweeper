
public class Square {				//establishing a class called square
 
	private boolean isMine;			//whether a square holds a mine or not
	private boolean isRevealed;		//var to determine whether a square should reveal or hide its contents (mines/numbers)
	private int neighboringMines;			//number of mines around a square
	//private String gridObject;				//grid contents 
	private int intValue; 					//number to be assigned to individual cells
	public Square () {					//constructor
		isMine = false;					//setting the square to hold no mine
		isRevealed = false;				//the square won't reveal its content
		neighboringMines = 0;			//sqaure doesn't have any neighboring mines
		//gridObject = "o";				
		intValue = 0; 				//int value in the square is 0
	}
	public void setMines() {			//setter for mines in a square
		isMine = true;					//squre will hold a mine
	}
	public boolean getMine() {			//getter for mines in a square
		return isMine;					//return boolean
	}
	public void setRevealed() {			//setter for revealing the contents of a square
		isRevealed = true; 				//square will reveal its content (either mine or number)
	}
	public boolean getRevealed() {		//getter function for a square to reveal its contents
		return isRevealed;				//return boolean
	}
	public int getNeighboringMines() {		//getter function for getting neighboring mines
			return neighboringMines;		//return int of neigboring mines
	}
	
	public void setNeighboringMines(int n)	{		//setter function for neighboring mines
		neighboringMines = n;						//setting neighboring mine to an int n to be determined later
	}
	
	public String showDisplay() {				//function to set up display
		if( ! isRevealed )						//if isRevealed is false, that is square is unrevealed
		{
			return "-" ;						//return blank -
		}
		else if( isMine )						//if square is a mine
		{
			return "*" ;						//return * for mine
		}
		else									//if square holds a number
		{
			return Integer.toString(intValue) ;		//return the number held in the square
		}
	}
	/*public void setShowDisplay(String gridObject) {
		this.gridObject = gridObject;
	}*/
	public int getIntValue() {				//getter function for int value in a square
		return intValue;					//return int
	}
	public void setIntValue(int d) {		//setter function for number in square
		intValue=d;							//set value to int d that will be determined later
	}
}
