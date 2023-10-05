package Chara;
import java.util.ArrayList;

import main.GameStatus;
import main.map;

public class Quiz extends Character {

	public Quiz(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range,"none");
	}
	@Override
	public boolean Special(map theMap, ArrayList<Character> players, ArrayList<Character> enemies, int xPos, int yPos, GameStatus gameStatus) {
		// TODO Auto-generated method stub
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
