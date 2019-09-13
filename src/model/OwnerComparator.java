package model;

import java.util.Comparator;

public class OwnerComparator implements Comparator<Owner> {

	@Override
	public int compare(Owner owner1, Owner owner2) {
		int comparar;
		String owner1Id = owner1.getId();
		String owner2Id = owner2.getId();
		if (owner1Id.compareTo(owner2Id) < 0) {
			comparar = -1;

		} else if (owner1Id.compareTo(owner2Id) > 0) {
			comparar = 1;

		} else {
			comparar = 0;

		}
		return comparar;

	}

}
