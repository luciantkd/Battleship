package battleShipGame;

public class Square {
	
	private int row;
	private int column;
	private boolean hasShip;
	private Battleship battleship;
	private boolean firedUpon;
	
	// Constructor initialises the square
	public Square (int row, int column) {
		this.row = row;
		this.column = column;
		this.hasShip = false;
		this.firedUpon = false;
	}
	
	public boolean hasShip() {return hasShip;}
	public int getRow() {return row;}
	public int getColumn() {return column;}
	public boolean isFiredUpon() {return firedUpon;}
	public Battleship getBattleship() {return battleship;}
	public void setBattleShip(Battleship battleship) {
		this.battleship = battleship;
		this.hasShip = (battleship != null); // If a battleship is set hasShip is true
	}
	public void setHasShip(boolean hasShip) {this.hasShip = hasShip;}
	public void setFiredUpon(boolean firedUpon) {this.firedUpon = firedUpon;}
	
	// Returns the status of the square
	public String toString() {
		if(!firedUpon) {
			return "-"; //not fired upon
		} else if (hasShip && firedUpon) {
			return  "X"; //hit
		} else {
			return "O"; //miss
		}
	}
}
