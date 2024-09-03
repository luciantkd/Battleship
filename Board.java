package battleShipGame;

import java.util.Random;

public class Board {
	
	private Square [][] squares;
	private int rows;
	private int columns;
	private int shipsToPlace;
	private int shipsSunk;
	
	 // Sets up the board with squares and ships
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.squares = new Square[rows][columns]; // Initialise the squares array
		this.shipsToPlace = 5; // number of ships to place
		this.shipsSunk = 0; // number of ships sunk
		populateBoard();
		generateBattleships();
	}
	
	  public int getRows() {return rows;}
	  public int getColumns() {return columns;}
	
	// Fills the board with squares  
	private void populateBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				squares [i][j] = new Square(i,j); // Initialise each square with its coordinates
			}
		}
	}
	
	// Generates and places battleships on the board
	private void generateBattleships() {
		Random r = new Random();
		// Generate and place the specific number of each size battleship
		generateSpecificBattleships(r, Battleship.SmallBattleship.TOTAL_ALLOWED,1);
		generateSpecificBattleships(r, Battleship.MediumBattleship.TOTAL_ALLOWED,2);
		generateSpecificBattleships(r, Battleship.LargeBattleship.TOTAL_ALLOWED,3);
	}
	
	private void generateSpecificBattleships(Random r, int totalAllowed, int size) {
		for (int i=0; i < totalAllowed; i++) {
			Battleship battleship = null;
			// Create a battleship of the correct size 
			if (size == 1 ) {
					battleship = new Battleship.SmallBattleship();
				} else if (size == 2) {
					battleship = new Battleship.MediumBattleship();
				} else if (size == 3) {
					battleship = new Battleship.LargeBattleship();
				} else {
					throw new IllegalArgumentException("Invalid size for battleship");
				}
				
				boolean placed = false;
				while (!placed) {
					// Randomly select a starting point and orientation for the battleship
					int row = r.nextInt(rows);
					int column = r.nextInt(columns);
					boolean isVertical = r.nextBoolean();
				
					// Adjust starting point if ship is vertical.	
				if (isVertical) {
	                row = r.nextInt(rows - size + 1);
	     
	            } else {
	            	 column = r.nextInt(columns - size + 1);
	            }

				// Check if battleship can be placed at the location
				if (canPlaceBattleship(row, column, isVertical, size)) {
	                placeBattleship(battleship, row, column, isVertical);
	                placed = true;
	            }
			} 
		}
	}	
	
	private boolean canPlaceBattleship (int row, int column, boolean isVertical, int size) {
		// Check if placement would go out of bounds
		if (isVertical && (row + size > rows) || !isVertical && (column + size > columns)) {
            System.out.println("Placement would be out of bounds.");
            return false;
        }
		// Check each square to ensure no overlap with existing ships
        for (int i = 0; i < size; i++) {
            int currentRow = isVertical ? row + i : row;
            int currentColumn = isVertical ? column : column + i;

            if (squares[currentRow][currentColumn].hasShip()) {
                /* System.out.println("Placement would collide with another ship.");
            	this was displaying and I did not know how to stop from doing it
                checks If a ship is already present, the placement is not allowed.
            	*/
            	return false;
            }   
        }
		return true;
		
	}
	
	// Method to place a battleship on the board at the specified location and orientation
	private void placeBattleship(Battleship battleship, int row, int column, boolean isVertical) {
		if (isVertical) {
			for (int i=0; i < battleship.getSize(); i++) {
				squares[row + i][column].setHasShip(true);
				squares[row + i][column].setBattleShip(battleship);
				} 
			} else {
				for (int i=0; i < battleship.getSize(); i++) {
					squares[row][column + i].setHasShip(true);
					squares[row][column + i].setBattleShip(battleship);
				}
			}
		}
	
	public Square getSquare(int row, int column) {
		return squares[row][column];
	}
	// Method to check if last ship has been sunk
	public boolean checkIfLastShip() {
		return shipsSunk == shipsToPlace;
	}
	public void incrementShipsSunk() {
		shipsSunk++;
	}
	
	// Returns a string representation of the board
	public String toString() {
		String boardString = "  ";
		for (int col=0; col < columns; col++) {
				boardString += col + " ";
			}
		boardString += ("\n");
		
		for (int row=0; row < rows; row++) {
			boardString += row + " ";
			
			for (int col = 0; col < columns; col++) {
				boardString += getSquareRepresentation(row,col) + " ";
			}
			boardString += "\n";
		}
		return boardString;
	}
	
	// Gets the string representation of a square
	private String getSquareRepresentation (int row, int col) {
		Square square = squares[row][col];
		return square.toString();
	}
}


