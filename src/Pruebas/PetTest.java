package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import model.Pet;

class PetTest {
	private ArrayList<Pet> pets;
	public void setUpEscenario1() {
		Pet pet1 = new Pet("1010", "Pinina", "female", "1", "8/12/2005");
		Pet pet2 = new Pet("88", "Susy", "female", "1", "10/9/2015");
		Pet pet3 = new Pet("44", "Negra", "female", "3", "3/05/2019");
		pets = new ArrayList<Pet>();
		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);

	}
	@Test
	public void compareToTest() {
		setUpEscenario1();
		Collections.sort(pets);
		String expected= "Negra";
		String actual= pets.get(0).getName();
		assertEquals(expected, actual);

	}
}
