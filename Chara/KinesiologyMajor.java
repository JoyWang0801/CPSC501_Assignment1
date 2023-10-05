package Chara;

import java.util.ArrayList;

import main.GameStatus;
import main.map;


public class KinesiologyMajor extends Character {
	//constructor
	public KinesiologyMajor(int id) {
		super("KinesiologyMajor", id, 30, 250, 3, 30, 250, 5, 4, 1, "This special deals a chosen near-by enemy a large amount of damage (costs 2 mana). Select the tile of the enemy you want to attack.");
			}
	
	//methods
	@Override
	protected boolean CheckManaEnoughForSpecial() {
		if(getMana() < 2)
		{
			System.out.println("This special requires 2 mana");
			return false;
		}
		return true;
	}

	//this special deals a close-by enemy a large amount of damage
	@Override
	protected boolean DoSpecialAttack(int xPos, int yPos, GameStatus gameStatus) {
		boolean success = false;
		map theMap = gameStatus.getCurrentMap();
		ArrayList<Character> enemies = gameStatus.getEnemies();
		int choice = theMap.getID(xPos, yPos);
		boolean isEnemy = false;
		int enemyID = 0;
		int enemyIndex = 0;
		for(int i = 0; i < enemies.size(); i++) {
			if(!isEnemy) {
				enemyID = enemies.get(i).getID();
				isEnemy = enemyID == choice;
				enemyIndex = i;
			}
		}
		if(isEnemy) {
			int[] enemyPos = theMap.getPos(enemyID);
			int[] playerPos = theMap.getPos(getID());
			int range = Math.abs(enemyPos[0] - playerPos[0]) + Math.abs(enemyPos[1] - playerPos[1]);
			if(range <= 1) {
				int regAttack = getAttack();
				setAttack(regAttack * 2);
				attack(enemies.get(enemyIndex));
				setAttack(regAttack);
				setMana(getMana() - 2);
				success = true;
			} else {
				System.out.println("Enemy is not in range");
			}
		} else {
			System.out.println("That enemy does not exist");
		}
		return success;
	}
}
