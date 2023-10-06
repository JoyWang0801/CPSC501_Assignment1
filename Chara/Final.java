package Chara;

	import java.util.ArrayList;

	import main.GameStatus;
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
		protected boolean DoSpecialAttack(int xPos, int yPos, GameStatus gameStatus) {
			//these two for loops check each spot around the final
			map theMap = gameStatus.getCurrentMap();
			ArrayList<Character> players = gameStatus.getPlayers();
			int finalXPos = theMap.getPos(this.getID())[0];
			int finalYPos = theMap.getPos(this.getID())[1];


			for (int a = -1; a < 2; a++) {
				for (int b = -1; b < 2; b++) {
					int zoneX = finalXPos + a;
					int zoneY = finalYPos + b;

					/* this for loop checks if any player is in the spot being
					 * checked and attacks them three times if it is */
                    for (Character player : players) {
                        int playerId = player.getID();
                        int playerXPos = theMap.getPos(playerId)[0];
						int playerYPos = theMap.getPos(playerId)[1];
                        if ((playerXPos == zoneX) && (playerYPos == zoneY)) {
                            attack(player);
                            attack(player);
                            attack(player);
                        }
                    }
				}
			}
			setMana(getMana() - 3);
			return true;
		}
	}
