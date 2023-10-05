package item;
import Chara.Character;

public class EmptyItem extends Item {
	public EmptyItem() {
		super(31, "Empty");
	}
	
	@Override
	public boolean use(Character c) {
		// TODO Auto-generated method stub
		return false;
	}
}
