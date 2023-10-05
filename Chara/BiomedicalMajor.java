package Chara;
import java.util.ArrayList;

import main.map;

public class BiomedicalMajor extends Character {
	
	//instance variables
	
	
	
	//constructor
	public BiomedicalMajor(int id) {
super("Biomedical Major", id, 10, 250, 3, 10, 250, 7, 4, 1, "This special boosts the health of a chosen nearby ally (costs 3 mana). Select the tile of the teammate you would like to heal that is within three tiles");
	}
	
	//methods


	@Override
	protected boolean CheckManaEnoughForSpecial() {
		if(getMana() < 3){
			System.out.println("This special requires 3 mana to use");
			return false;
		}
		return true;
	}

	//this special boosts the health of a near-by ally
	@Override
	protected boolean DoSpecialAttack(map theMap, ArrayList<Character> players, ArrayList<Character> enemies, int xPos, int yPos) {
		boolean didSomething = false;

		Integer choice = theMap.getID(xPos, yPos);
		boolean isPlayer = false;
		int playerID = 0;
		int playerIndex = 0;
		for(int i = 0; i < players.size(); i++) {
			if(!isPlayer) {
				playerID = players.get(i).getID();
				isPlayer = playerID == choice;
				playerIndex = i;
			}
		}

		if(isPlayer) {
			int[] playerPos = theMap.getPos(playerID);
			int[] healerPos = theMap.getPos(getID());
			int range = Math.abs(healerPos[0] - playerPos[0]) + Math.abs(healerPos[1] - playerPos[1]);
			if(range <= 3) {
				Character healed = players.get(playerIndex);
				healed.setHealth(healed.getHealth() + 40);
				setMana(getMana() - 3);
				didSomething = true;
			} else {
				System.out.println("Teammate is not in range");
			}
		} else {
			System.out.println("That teammate does not exist");
		}

		return didSomething;
	}
}
