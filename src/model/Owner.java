package model;

import java.io.*;
import java.util.*;

public class Owner implements Serializable, Comparable<Owner>, Comparator<Owner> {

	private String id;
	private String name;
	private String lastName;
	private String date;
	private int favPet;
	private ArrayList<Pet> thePets;

	public Owner(String id, String name, String lastName, String date, int favPet) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.date = date;
		this.favPet = favPet;
		thePets = new ArrayList<Pet>();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getFavPet() {
		return favPet;
	}

	public void setFavPet(int favPet) {
		this.favPet = favPet;
	}

	public ArrayList<Pet> getThePets() {
		return thePets;
	}

	public void setThePets(ArrayList<Pet> thePets) {
		this.thePets = thePets;
	}

	/**
	 * The following method adds a pet to the system
	 * 
	 * @param id    the id of the pet
	 * @param name  the name of the pet
	 * @param genre the genre of the pet
	 * @param type  the type of the pet
	 * @param date  the pet's birthdate
	 * @throws PetWithTheSameNameException
	 */
	public void addPet(String id, String name, int genre, int type, String date) throws PetWithTheSameNameException {
		if (verifyExistanceOfAPet(name)) {
			throw new PetWithTheSameNameException(
					"The Owner" + getName() + getLastName() + "has already a pet called " + name);
		} else {
			Pet newPet = new Pet(id, name, genre, type, date);
			thePets.add(newPet);
			System.out.println(
					"The pet " + name + " has been added to the system for the owner " + this.name + this.lastName);
		}
	}

	/**
	 * This method verifies if a pet already exists in the system for an owner
	 * 
	 * @param namePet the pet's name which is going to be added
	 * @return a boolean that says if the pet already exists
	 */

	public boolean verifyExistanceOfAPet(String namePet) {
		boolean stop = false;
		for (int i = 0; i < thePets.size() && !stop; i++) {
			if (thePets.get(i).getName().equals(namePet)) {
				stop = true;
			}
		}
		return stop;
	}

	/**
	 * This method removes a pet from an owner
	 * 
	 * @param petId the pet's id
	 */
	public void toRemoveAPetFromAnOwner(String petId) {
		for (int i = 0; i < thePets.size(); i++) {
			if (thePets.get(i).equals(petId)) {
				thePets.remove(i);
			}
		}
	}

	/**
	 * This method allows to see the information of the pets an owner has.
	 */
	public void showInformationAboutThePets() {
		String messagge = "";
		for (int i = 0; i < thePets.size(); i++) {
			messagge += i + "." + thePets.get(i).toString() + "\n";
		}
	}

	/**
	 * This method search a pet given an specific name
	 * 
	 * @param nameOfThePet the pet's name
	 * @return the pet that is searched
	 */
	public Pet toSearchAPet(String nameOfThePet) {
		Pet p = null;
		for (int i = 0; i < thePets.size(); i++) {
			if (thePets.get(i).getName().equals(nameOfThePet)) {
				p = thePets.get(i);
			}
		}
		return p;

	}
	public void deleteASpecificPet(String petToDelete)  {
		boolean stop= false;
		for(int i=0; i<thePets.size() && !stop; i++) {
			if(thePets.get(i).getName().equals(petToDelete)) {
				thePets.remove(i);
				stop=true;
			}else {
				System.out.println("The pet " + petToDelete + " cannot be deleted because is not in the system");
			}
			
		}
	}

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

	public int compareByFav(Owner owner1, Owner owner2) {
		int pO1 = owner1.getFavPet();
		int pO2 = owner2.getFavPet();
		if (pO1 > pO2)
			return 1;
		if (pO1 < pO2)
			return -1;
		return 0;
	}

	@Override
	public int compareTo(Owner owner) {
		return this.getName().compareTo(owner.getName());

	}
	
	//with lastName
	public int compareToLName(Owner owner) {
		return this.getLastName().compareTo(owner.getLastName());
	}
	//with the id
	public int compareToId(Owner owner) {
		return this.getId().compareTo(owner.getId());
	}
	//with the birthdate
	public int compareToDate(Owner owner) {
		return this.getDate().compareTo(owner.getDate());
	}
	
}
