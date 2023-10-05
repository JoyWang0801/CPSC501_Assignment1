package item;
import Chara.Character;

public class MovementPotion extends Item{

	public MovementPotion() {
		super(34, "Movement Potion");
	}
	
	//increases the users movement by two permenently
	public boolean use(Character c) {
		c.setMove(c.getMove() + 2);
		return true;
	}
}

