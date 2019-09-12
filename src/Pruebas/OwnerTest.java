package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import model.Pet;
import model.PetWithTheSameNameException;

import org.junit.jupiter.api.Test;

import model.Owner;
import model.Pet;

class OwnerTest {
	private Owner ownerAndrea;

	public void setUpEscenario1() {
		ownerAndrea = new Owner("1010", "Andrea", "Nr", "2000/10/12", "4");
		ArrayList<Pet> perros;
		perros = new ArrayList<Pet>();
		ownerAndrea.setThePets(perros);
	}


	@Test
	public void addPetTest() throws PetWithTheSameNameException {
		setUpEscenario1();
		ownerAndrea.addPet("3", "qqq", "222", "3", "222");
		int expected = 1;
		int actual = ownerAndrea.getThePets().size();
		assertEquals(expected, actual);
	}

	@Test
	public void compareTest() throws PetWithTheSameNameException {
		setUpEscenario1();
		Owner e = new Owner("222", "Maria", "Rodriguez", "3/4/2015", "5");
		ArrayList<Pet> dogs;
		dogs = new ArrayList<Pet>();
		e.setThePets(dogs);

		e.addPet("111", "aaa", "female", "5", "4/3/2017");

		ownerAndrea.addPet("1221", "aaa", "female", "5", "4/3/2017");

		int actual = ownerAndrea.compare(ownerAndrea, e);
		int expected = 0;
		assertEquals(expected, actual);

	}

}
