package model;
import java.util.Comparator;

public class FavPetComparator implements Comparator<Owner>{

	@Override
	public int compare(Owner owner1, Owner owner2) {
		int pO1 = owner1.getFavPet();
		int pO2 = owner2.getFavPet();
		if (pO1 > pO2)
			return 1;
		if (pO1 < pO2)
			return -1;
		return 0;
	}
}
