package model;

import java.util.Comparator;

public class ClubComparator implements Comparator<Club> {

	@Override
	public int compare(Club c1, Club c2) {
		int a1 = c1.getTheOwners().size();
		int a2 = c2.getTheOwners().size();
		if (a1 > a2)
			return 1;
		if (a1 < a2)
			return -1;
		return 0;
	}

}
