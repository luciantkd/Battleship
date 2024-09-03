package battleShipGame;

import java.util.Scanner;
import java.util.InputMismatchException;

// Player class represents a player in the game
public class Player {
	private String name;
	private int score;
	private Board board;
	
	public Player (String name, Board board) {
		this.name = name;
		this.score = 0;
		this.board = board;
		}
	
	// Method for the player to take a turn by guessing a square to fire upon
	public boolean takeTurn(Scanner scanner) {
		try {
			System.out.println(name + ", enter your guess (row column):");
			int row = scanner.nextInt();
			int column = scanner.nextInt();
			
			// Ensure its within the boards bounds
			if (row < 0 || row >= board.getRows() || column < 0 || column >= board.getColumns()) {
		       System.out.println("Invalid input. Please enter row and column within the board's range.");
		       return false;		
		}
		
		Square targetSquare = board.getSquare(row,column);
		
		// Check if the square has already been fired upon
		if (targetSquare.isFiredUpon()) {
			System.out.println("You have already fired here. You lose your turn.");
			return false;
		}
		
		targetSquare.setFiredUpon(true);
		
		// Check if there is a ship at the square and process the hit
		if (targetSquare.hasShip()) {
			Battleship battleship = targetSquare.getBattleship();
			battleship.hit();
			if (battleship.isSunk()) {
				score++;
				System.out.println("Hit and sunk!");
				board.incrementShipsSunk();
				return board.checkIfLastShip();
			} else {
				System.out.println("Hit!");
			}
		} else {
			System.out.println("Miss!");
		}
		return false;
	} catch (InputMismatchException e) {
		System.out.println("invalid input. Please enter numbers only.");
		scanner.nextLine();
		return false;
	}
}	
	
	// Method for a player to take a turn
	public void takeTurn(int row, int column) {
		Square square = board.getSquare(row, column);
		
		// Check if the square has already been targeted in a previous turn
		if(!square.isFiredUpon()) {
			// If not mark the square as fired upon
			square.setFiredUpon(true);
			
			// Check if there is a ship at the targeted square
			if (square.hasShip()) {
				score++;
				System.out.println(name + " hit a ship!");
			} else {
				// If no ship is present output a message indicating a miss
				System.out.println(name + " missed!");
			}
		} else {
			// If the square has already been fired upon player needs to choose another square
			System.out.println("This square has already been fired upon. Choose another square.");
		}
	}
	
	public String getName() {return name;}
	public int getScore() {return score;}
	public Board getBoard(){return board;}
}
