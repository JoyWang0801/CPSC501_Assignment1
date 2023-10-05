package Chara;
		
import java.util.ArrayList;

import main.GameStatus;
import main.map;
public class Midterm extends Character {
		
	public Midterm(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, 10, maxAtt, maxHealth, 10, move, range, "");
	}

	@Override
	protected boolean CheckManaEnoughForSpecial() {
		if(getMana() < 10)
		{
			System.out.println("This special requires 3 mana");
			return false;
		}
		return true;
	}

	// permanent-debuff on all players
	@Override
	protected boolean DoSpecialAttack(map theMap, ArrayList<Character> players, ArrayList<Character> enemies, int xPos, int yPos, GameStatus gameStatus) {
		//reduce all player characters attack value by one
		for(Character member : players) {
			member.setAttack(member.getAttack() - 1);
			//lower mana value by ten
			setMana(getMana() - 10);
		}
		//mark that special was used
		return true;
	}

}
