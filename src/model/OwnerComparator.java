package model;
import java.util.Comparator;

public class OwnerComparator implements Comparator<Owner> {

	@Override
	public int compare(Owner owner1, Owner owner2) {
		int arraylistOwner1 = owner1.getThePets().size();
		int arraylistOwner2 = owner2.getThePets().size();
		if (arraylistOwner1 > arraylistOwner2)
			return 1;
		if (arraylistOwner1 < arraylistOwner2)
			return -1;
		return 0;

	}

}
