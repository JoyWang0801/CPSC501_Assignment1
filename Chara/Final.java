package Chara;

	import java.util.ArrayList;

import main.map;
	public class Final extends Character {

	public Final(String name, int id, int att, int health, int mana,
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, 5, maxAtt, maxHealth, 5, move, range, "");
	}

	//costs three mana points to for the final to use
		@Override
		protected boolean CheckManaEnoughForSpecial() {
			if(getMana() < 3) {
				System.out.println("This special requires 3 mana");
				return false;
			}
			return true;
		}

		//this special move is a powerful AOE attack that hits all players within one space of the final three times
		@Override
		protected boolean DoSpecialAttack(map theMap, ArrayList<Character> players, ArrayList<Character> enemies, int xPos, int yPos) {
			//these two for loops check each spot around the final
			boolean didSomething = false;
			for (int a = -1; a < 2; a++) {
				for (int b = -1; b < 2; b++) {
					int zoneX = theMap.getPos(this.getID())[0] + a;
					int zoneY = theMap.getPos(this.getID())[1] + b;
					int[] zone = new int[2];
					zone[0] = zoneX;
					zone[1] = zoneY;

					/* this for loop checks if any player is in the spot being
					 * checked and attacks them three times if it is */

					for(int i = 0; i < players.size(); i++) {
						if((theMap.getPos(players.get(i).getID())[0] == zone[0]) && (theMap.getPos(players.get(i).getID())[1] == zone[1])) {
							attack(players.get(i));
							attack(players.get(i));
							attack(players.get(i));
						}
					}
				}
			}
			setMana(getMana() - 3);
			return true;
		}
	}
