package Chara;

import java.util.ArrayList;

import main.GameStatus;
import main.map;


public class PhilosophyMajor extends Character {
	
	
	//constructor
	public PhilosophyMajor(int id) {
		super("Philosophy Major", id, 15, 200, 2, 15, 200, 5, 5, 2, "This special lowers the attack of a chosen enemy (costs 2 mana). Select the tile of the enemy you want to target");
	}
	
	//methods
	@Override
	protected boolean CheckManaEnoughForSpecial() {
		if(getMana() < 2)
		{
			System.out.println("This special requires 2 mana");
			return false;
		}
		return true;
	}

	//this special lowers the attack of an enemy
	@Override
	protected boolean DoSpecialAttack(map theMap, ArrayList<Character> players, ArrayList<Character> enemies, int xPos, int yPos, GameStatus gameStatus) {
		theMap = null;
		players = null;
		enemies = null;

		boolean didSomething = false;
		int choice = gameStatus.theMap.getID(xPos, yPos);
		for(Character foe: gameStatus.enemies) {
			if (choice == foe.getID()) {
				gameStatus.enemies.get(choice).setAttack(gameStatus.enemies.get(choice).getAttack() - 5);
				setMana(getMana() - 2);
				didSomething = true;
			}
		}
		return didSomething;
	}
}
