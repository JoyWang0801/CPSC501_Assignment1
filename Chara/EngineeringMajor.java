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
	protected boolean DoSpecialAttack(int xPos, int yPos, GameStatus gameStatus) {
		boolean didSomething = false;
		map theMap = gameStatus.getCurrentMap();
		ArrayList<Character> enemies = gameStatus.getEnemies();
		int row = theMap.getPos(getID())[1];
		int col = theMap.getPos(getID())[0];

		//if same position as engineer return false
		if (this.getID() == theMap.getID(xPos, yPos)) {
			didSomething = false;
			//if column
		} else if (col == xPos) {
			//if above
			for(int each = 0; each < enemies.size(); each++) {
				int enemyId = enemies.get(each).getID();
				boolean isSameColumn = theMap.getPos(enemyId)[0] == col;
				if(isSameColumn) {
					attack(enemies.get(each));
					System.out.println("I attacked");
				}
			}
			didSomething = true;
			//if row
		} else if (row == yPos) {
			//if left
			for(int each = 0; each < enemies.size(); each++) {
				int enemyId = enemies.get(each).getID();
				boolean isSameRow = theMap.getPos(enemyId)[1] == row;
				if(isSameRow) {
					attack(enemies.get(each));
				}
			}
			didSomething = true;
		}

		if (didSomething) {
			setMana(getMana() - 2);
		}
		return didSomething;
	}
}
