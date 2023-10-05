package Chara;
import java.util.ArrayList;

import main.GameStatus;
import main.map;

public class Enemy extends Character {
		
	public Enemy(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super("Enemy", id, 15, 150, 0, 15, 150, 0, 4, 1, "none");
	}
		
	/* basic enemies do not have specials.
	 * instead, they simply return that the special 
	 * cannot be used at all times	*/
		
	public boolean Special(map theMap, ArrayList<Character> players, ArrayList<Character> enemies, int xPos, int yPos) {
		return false;
	}

	@Override
	protected boolean CheckManaEnoughForSpecial() {
		return false;
	}

	@Override
	protected boolean DoSpecialAttack(int xPos, int yPos, GameStatus gameStatus) {
		return false;
	}
}
