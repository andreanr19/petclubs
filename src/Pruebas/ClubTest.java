package Pruebas;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.*;

class ClubTest {
	private Club c;
	
	public void setUpEscenario1(){
		c= new Club("22", "Vegas", "4/3/1990", "perros");
		ArrayList<Owner> o = new ArrayList<Owner>();
	}

	@Test
	public void addOwnerToAClubTest() throws OwnerAlreadyExistsException {
		setUpEscenario1();
		c.addOwnerToAClub("22", "aaa", "444", "2/6/1990", "5");
		int expected = 1;
		int actual = c.getTheOwners().size();
		assertEquals(expected, actual);
	}

	@Test
	public void orderBySelection() throws OwnerAlreadyExistsException {
		setUpEscenario1();
		c.addOwnerToAClub("22", "eee", "444", "2/6/1990", "5");
		c.addOwnerToAClub("22", "aaa", "444", "2/6/1990", "5");
		c.addOwnerToAClub("22", "iii", "444", "7/5/2001", "4");
		c.orderBySelection();
		String actual = "aaa";
		String expected = c.getTheOwners().get(0).getName();
		assertEquals(expected, actual);

	}
	@Test
	public void petFavComparationTest() throws OwnerAlreadyExistsException {
		
		setUpEscenario1();
		Club two= new Club("333", "Renacer Animal", "5/6/2014", "perros");
		int actual= c.petFavComparation(two);
		int expected=0;
		assertEquals(expected, actual);
		
		
	}
	

}
