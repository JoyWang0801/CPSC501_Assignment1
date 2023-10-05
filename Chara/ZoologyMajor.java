package Chara;
import java.util.ArrayList;

import main.GameStatus;
import main.map;


public class ZoologyMajor extends Character {

	
	//constructor
	public ZoologyMajor(int id) {
		super("Zoology Major", id, 20, 180, 2, 20, 180, 5, 8, 1, "This special allows the Zoology Major to move a second time (Costs 2 mana). Select the location you would like to move to");
		}
		
	//methods
	@Override
	protected boolean CheckManaEnoughForSpecial() {
		if (getMana() < 2) {
			System.out.println("This special requires 2 mana");
			return false;
		}
		return true;
	}

	//Special allows the ZoologyMajor Character to take a second movement action
	@Override
	protected boolean DoSpecialAttack(int xPos, int yPos, GameStatus gameStatus) {
		boolean didSomething = false;

		didSomething = gameStatus.theMap.move(this.getID(), xPos, yPos, this.getRange());
		if (didSomething == true) {
			setMana(getMana() - 2);
		}
		return didSomething;
	}
}
