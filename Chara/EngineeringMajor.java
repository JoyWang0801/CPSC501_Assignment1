package Chara;

import java.util.ArrayList;

import main.GameStatus;
import main.map;


public class EngineeringMajor extends Character {
	
		
		//constructor
		public EngineeringMajor(int id) {
			super("Engineering Major", id, 20, 200, 3, 20, 200, 5, 4, 4, "This special move attacks any enemies in the same chosen column or row as the Engineering Major (costs 3 mana). Select a tile in the same row or column as the engineering major to decide the direction to attack");
		}

	@Override
	protected boolean CheckManaEnoughForSpecial() {
		if (getMana() < 2) {
			System.out.println("This special requires 2 mana");
			return false;
		}
		return true;
	}

	//this special attacks any enemies in the same row as the engineer
	@Override
	protected boolean DoSpecialAttack(map theMap, ArrayList<Character> players, ArrayList<Character> enemies, int xPos, int yPos, GameStatus gameStatus) {

		theMap = null;
		players = null;
		enemies = null;


		boolean didSomething = false;
		int row = gameStatus.theMap.getPos(getID())[1];
		int col = gameStatus.theMap.getPos(getID())[0];

		//if same position as engineer return false
		if (this.getID() == gameStatus.theMap.getID(xPos, yPos)) {
			didSomething = false;

			//if column
		} else if (gameStatus.theMap.getPos(this.getID())[0] == xPos) {

			//if above
			for(int each = 0; each < gameStatus.enemies.size(); each++) {
				if(gameStatus.theMap.getPos(gameStatus.enemies.get(each).getID())[0] == col && gameStatus.theMap.getPos(gameStatus.enemies.get(each).getID())[1] < gameStatus.theMap.getPos(this.getID())[1]) {
					attack(gameStatus.enemies.get(each));
					System.out.println("I attacked");
				}
			}

			//if below
			for(int each = 0; each < gameStatus.enemies.size(); each++) {
				if(gameStatus.theMap.getPos(gameStatus.enemies.get(each).getID())[0] == col && gameStatus.theMap.getPos(gameStatus.enemies.get(each).getID())[1] > gameStatus.theMap.getPos(this.getID())[1]) {
					attack(gameStatus.enemies.get(each));
				}
			}
			didSomething = true;
			//if row
		} else if (gameStatus.theMap.getPos(this.getID())[1] == yPos) {

			//if left
			for(int each = 0; each < gameStatus.enemies.size(); each++) {
				if(gameStatus.theMap.getPos(gameStatus.enemies.get(each).getID())[1] == row && gameStatus.theMap.getPos(gameStatus.enemies.get(each).getID())[0] < gameStatus.theMap.getPos(this.getID())[0]) {
					attack(gameStatus.enemies.get(each));
				}
			}

			//if right
			for(int each = 0; each < gameStatus.enemies.size(); each++) {
				if(gameStatus.theMap.getPos(gameStatus.enemies.get(each).getID())[1] == row && gameStatus.theMap.getPos(gameStatus.enemies.get(each).getID())[0] > gameStatus.theMap.getPos(this.getID())[0]) {
					attack(gameStatus.enemies.get(each));
				}
			}
			didSomething = true;
		}

		if (didSomething == true) {
			setMana(getMana() - 2);
		}
		return didSomething;
	}
}
