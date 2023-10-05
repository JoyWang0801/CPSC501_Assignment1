package item;
import Chara.Character;

public class HealthPotion extends Item {

	public HealthPotion(){
	super(32, "HealthPotion");
}
	//increases the users health points by 50
	public boolean  use(Character c){
	c.setHealth(c.getHealth() + 50);
	return true;
	
	
}
}
