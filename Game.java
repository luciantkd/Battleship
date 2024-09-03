package battleShipGame;

import java.util.Scanner;

public class Game {
	private Player player1;
	private Player player2;
	private Board board;
	private Scanner scanner;
	
	public Game() {
		scanner = new Scanner(System.in);
		board = new Board(10,10);
		System.out.println("Enter name for Player 1: ");
		player1 = new Player(scanner.nextLine(), board);
		System.out.println("Enter name for Player 2: ");
		player2 = new Player(scanner.nextLine(), board);
	}
	
	// Method to start the game loop, alternating turns between players until game ends
	public void start() {
		boolean gameEnded = false;
		while(!gameEnded) {
			if(player1.takeTurn(scanner)) {
				gameEnded = true;
				System.out.println(player1.getName() + " wins!");
				break;
			}
			displayBoard();
			displayCurrentScores();
			
			if(!gameEnded && player2.takeTurn(scanner)) {
				gameEnded = true;
				System.out.println(player2.getName() + " wins!");
				break;
			}
			displayBoard();
			displayCurrentScores();
		}
		announceWinner();
	}
	
	// Method to display the current scores of both players
	private void displayCurrentScores() {
			System.out.println("Current Scores:");
			System.out.println(player1.getName() + ": " + player1.getScore());
			System.out.println(player2.getName() + ": " + player2.getScore());
			System.out.println();
		}
	// Method to announce the winner based on scores
	private void announceWinner() {
			int score1 = player1.getScore();
			int score2 = player2.getScore();
			if(score1 > score2){
				System.out.println(player1.getName() + " wins with " + score1 + " points!");
			} else if (score2 > score1) {
				System.out.println(player2.getName() + " wins with " + score2 + " points!");
			} else {
				System.out.println("It is a draw!");
			}
		}
		
		private void displayBoard() {
			System.out.println(board);
		}
	
	// Main method to start the game	
	public static void main(String[] args) {
			Game game = new Game();
			game.start();
			game.scanner.close();
		}
	}
	

