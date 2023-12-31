package Chara;
import java.util.ArrayList;
import item.*;
import main.*;
public abstract class Character {
	
	//instance variables
	private int health;
	private int maxHealth;
	private int attack;
	private int maxAttack;
	private int mana;
	private int maxMana;
	private int ID;
	private String name;
	private Item[] inventory = { new EmptyItem(),  new EmptyItem(),  new EmptyItem()};
	private int range;
	private int move;
	private String specDesc = "No special description";
	
	//constructors
	public Character() {
		
	}
	public Character(String name, int id, int att, int health, int mana, int maxAtt, int maxHealth, int maxMana, int move, int range, String specDesc) {
		this.name = name;
		this.ID = id;
		setMove(move);
		setRange(range);
		setMaxAttack(maxAtt);
		setMaxHealth(maxHealth);
		setMaxMana(maxMana);
		setAttack(att);
		setHealth(health);
		setMana(mana);
		this.specDesc = specDesc;
	}
	
	//methods
	public int getMana() {return mana;}
	
	public void setMana(int newMana) {
		if(newMana >= maxMana) mana = maxMana;
		else mana = newMana;
	}
	
	public int getMaxMana() {return maxMana;}
	
	public void setMaxMana(int newMax)
	{
		if(newMax < 0)
		{
			maxMana = 0;
		}
		else
		{
			if(getMana() > newMax)
			{
				setMana(newMax);
			}
			maxMana = newMax;
		}
	}
	
	public int getMove() {return move;}
	
	public void setMove(int newMove) {move = newMove;}
	
	public int getAttack() {
      return attack;
	}
	public void setAttack(int att) {
      attack = att;
	}
	public void setMaxAttack(int i) {
		maxAttack = i;
		if(getAttack() > i)
		{
			setAttack(i);
		}
	}
	public int getMaxAttack() {
		int toReturn = maxAttack;
		return toReturn;
	}
	public int getMaxHealth() {
		int toReturn = maxHealth;
		return toReturn;
	}
	public void setMaxHealth(int i) {
		maxHealth = i;
		if(getHealth() > i)
		{
			setHealth(i);
		}
	}
	public int getRange() {  
	      return range;
		}
		public void setRange(int r) {
	      range = r;
		}
	 public Item[] getInventory() {
		Item[] toReturn = new Item[3];
	    for (int index = 0; index < this.inventory.length; index++) {
	    	toReturn[index] = this.inventory[index];
	    	}
	    return toReturn;
	    }
	 public void setItem(Item newItem,int index) {
	        inventory[index] = newItem;
	    }
	 public String getItemName(int ind) {
		 return new String(this.inventory[ind].getName());
	 }
	 
	 public Item getItem(int ind) {
		 return this.inventory[ind];
	 }
	
	 public void setHealth(int i) {
		if (i >= maxHealth) {
			health = maxHealth;
		}else {
			health = i;
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setID(int i) {
		ID = i;
	}
	
	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}
	
	public String getSpecDesc() {
		return specDesc;
	}
	
	public boolean Special(int xPos, int yPos, GameStatus gameStatus)
	{
		if(CheckManaEnoughForSpecial())
		{
			return DoSpecialAttack(xPos, yPos, gameStatus);
		}
		return false;
	}

	protected abstract boolean CheckManaEnoughForSpecial();

	protected abstract boolean DoSpecialAttack(int xPos, int yPos, GameStatus gameStatus);

	public void attack(Character receiver) {
		receiver.setHealth(receiver.getHealth() - getAttack());
	}
}

