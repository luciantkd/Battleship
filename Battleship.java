package battleShipGame;

import java.util.Random;

public class Battleship {
	
	private boolean sunk; // Checks if the ship is sunk
	private int health; // Current health of the ship,
	private int size; // Size of the ship
	
	// Constructor sets the size and initialises the ship
	public Battleship(int size) {
		// Validate size, default size to 1 if invalid
		if (size <= 0) {
            System.out.println("Ship size must be bigger than 0. As a result, chang size to 1. ");
            size = 1;
		} 
        this.health = size;
		this.sunk = false;
		this.size = size;
	}
	
	// Records a hit and checks if the ship is sunk
	public void hit() {
		if (health > 0) {
			health--;
			if (health == 0) {
				sunk = true; // If health is 0, the ship is sunk
				System.out.println("Battleship is sunk");
			}
		}
	}
	
	public boolean isSunk() {return sunk;}
	public int getHealth() {return health;}	
	public int getSize() {return size;}
	
	// Inner class for a small battleship
    public static class SmallBattleship extends Battleship {
        public static final int TOTAL_ALLOWED = 3;
        
        public SmallBattleship() {
            super(1);
        }
    }

    // Inner class for a medium battleship
    public static class MediumBattleship extends Battleship {
        public static final int TOTAL_ALLOWED = 2;
        
        public MediumBattleship() {
            super(2);
        }   
    }

    // Inner class for a large battleship
    public static class LargeBattleship extends Battleship {
        public static final int TOTAL_ALLOWED = 1;
        
        public LargeBattleship() {
            super(3);
        }   
    }
}