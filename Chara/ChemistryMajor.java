package Chara;

import java.util.ArrayList;

import main.map;


public class ChemistryMajor extends Character {
	
	
	//constructor
	public ChemistryMajor(int id) {
		super("Chemistry Major", id, 15, 200, 2, 15, 200, 5, 5, 1, "This special boosts the attack of a chosen ally (costs 3 mana). Select the ally you want to boost on the map");
	}

	//methods
	@Override
	protected boolean CheckManaEnoughForSpecial() {
		if(getMana() < 3)
		{
			System.out.println("This special requires 3 mana");
			return false;
		}
		return true;
	}

	//this special boosts the attack of your allies
	@Override
	protected boolean DoSpecialAttack(map theMap, ArrayList<Character> players, ArrayList<Character> enemies, int xPos, int yPos) {
		boolean didSomething = false;
		int choice = theMap.getID(xPos, yPos);
		for(Character teammate: players) {
			if (choice == teammate.getID()) {
				teammate.setAttack(teammate.getAttack() + 5);
				setMana(getMana() - 3);
				didSomething = true;
			}
		}

		return didSomething;
	}
}
