package model;

import java.io.*;
import java.util.*;

public class Club implements Comparable<Club>, Comparator<Club> {

	private String id;
	private String name;
	private String foundationDate;
	private String type;
	private ArrayList<Owner> theOwners;

	public Club(String id, String name, String foundationDate, String type) {
		this.id = id;
		this.name = name;
		this.foundationDate = foundationDate;
		this.type = type;

		theOwners = new ArrayList<Owner>();
		
		theOwners = OwnerDeserialize();
	}

	public void addOwnerToAClub(String id, String name, String lastName, String fDate, int favtype)
			throws OwnerAlreadyExistsException {

		Owner newOwner = new Owner(id, name, lastName, fDate, favtype);

		if (ownerExists(id)) {
			throw new OwnerAlreadyExistsException("The id " + id + " does already belongs to an user of the system");
		} else {
			theOwners.add(newOwner);
			OwnersSerialize();
		}

	}

	/**
	 * The method allows to know if an Owner already exists based on the id
	 * 
	 * @param idOwner the owner's id
	 * @return a boolean that allows to know if the owner already exists.
	 */
	private boolean ownerExists(String idOwner) {
		boolean stop = false;
		for (int i = 0; i < theOwners.size(); i++) {
			if (theOwners.get(i).getId().equals(idOwner)) {
				stop = true;
			}
		}
		return stop;
	}

	/**
	 * The method allows to delete an owner from the system
	 * 
	 * @param id the owner's id
	 */
	public void deleteOwnerFromSystem(String id) {
		for (int i = 0; i < theOwners.size(); i++) {
			if (theOwners.get(i).getId().equals(id)) {
				theOwners.remove(i);
				System.out.println("The Owner with the id " + id + " has been removed");
			}
		}
	}

	/**
	 * This method allows to serialize the data for a club's owners
	 */
	public void OwnersSerialize() {
		try {
			File fl = new File("ownerserialized/" + this.name);
			ObjectOutputStream myFichero = new ObjectOutputStream(new FileOutputStream(fl));
			myFichero.writeObject(theOwners);
			System.out.println("The data has been saved for the club " + getName());
			myFichero.close();

		} catch (Exception e) {
			System.err.println("The data couldn't been saved");
		}
	}

	public ArrayList<Owner> OwnerDeserialize() {

		ArrayList<Owner> owners = null;
		try {
			File fl = new File("ownerserialized/" + this.name);
			System.out.println(fl.getPath());
			ObjectInputStream mynewFichero = new ObjectInputStream(new FileInputStream(fl));
			owners = (ArrayList<Owner>) mynewFichero.readObject();
			System.out.println(owners.size());
			mynewFichero.close();

		} catch (Exception e) {
			System.err.println("The file can't be found");

		}return owners;
	}

	public Owner serchOwner(String ownersName) {
		Owner ownertosearch = null;
		for (int i = 0; i < theOwners.size(); i++) {
			if (theOwners.get(i).getName().equals(ownersName)) {
				ownertosearch = theOwners.get(i);

			}
		}
		return ownertosearch;
	}

	public Owner searchByTheLName(String lname) {
		Owner ownertosearch = null;
		for (int i = 0; i < theOwners.size(); i++) {
			if (theOwners.get(i).getLastName().equals(lname)) {
				ownertosearch = theOwners.get(i);

			}
		}
		return ownertosearch;
	}

	public Owner searchByTheID(String id) {
		Owner ownertosearch = null;
		for (int i = 0; i < theOwners.size(); i++) {
			if (theOwners.get(i).getId().equals(id)) {
				ownertosearch = theOwners.get(i);

			}
		}
		return ownertosearch;
	}

	// BUBBLESORT
	public void orderByBubbleSort() {
		for (int i = 0; i < theOwners.size(); i++) {
			for (int j = 0; j < theOwners.size() - 1; j++) {
				if (theOwners.get(j).compare(theOwners.get(i), theOwners.get(j + 1)) > 0) {
					Owner aux = theOwners.get(j);
					theOwners.set(j, theOwners.get(j + 1));
					theOwners.set(j + 1, aux);
				}
			}
		}
	}

	// SELECTION
	public void orderBySelection() {

		for (int i = 0; i < theOwners.size(); i++) {
			Owner aux = theOwners.get(i);
			int j = i - 1;
			while (j >= 0 && theOwners.get(j).compareTo(aux) > 0) {
				theOwners.set(j + 1, theOwners.get(j));
				j = j - 1;
			}
			theOwners.set(j + 1, aux);
		}
	}

	// INSERTION
	public void orderByInsertion() {
		for (int i = 0; i < theOwners.size(); i++) {
			Owner aux = theOwners.get(i);
			int j = i - 1;
			while (j > 0 && theOwners.get(j).compareToLName(aux) > 0) {
				theOwners.set(j + 1, theOwners.get(j));
				j = j - 1;
			}
			theOwners.set(j + 1, aux);
		}
	}
	public String showOwner() {
		String msj="";
		for(int i=0; i<theOwners.size();i++) {
			msj += "[" + i + "]" + theOwners.get(i).toString() + "\n\n";

		}return msj;
	}

	public void orderByInsertionOfTheId() {
		for (int i = 0; i < theOwners.size(); i++) {
			Owner aux = theOwners.get(i);
			int j = i - 1;
			while (j > 0 && theOwners.get(j).compareToId(aux) > 0) {
				theOwners.set(j + 1, theOwners.get(j));
				j = j - 1;
			}
			theOwners.set(j + 1, aux);
		}
	}

	public void orderByInsertionOftheBirthdate() {
		for (int i = 0; i < theOwners.size(); i++) {
			Owner aux = theOwners.get(i);
			int j = i - 1;
			while (j > 0 && theOwners.get(j).compareToDate(aux) > 0) {
				theOwners.set(j + 1, theOwners.get(j));
				j = j - 1;
			}
			theOwners.set(j + 1, aux);
		}
	}

	public void orderBubblebyFav() {
		for (int i = 0; i < theOwners.size(); i++) {
			for (int j = 0; j < theOwners.size() - 1; j++) {
				if (theOwners.get(j).compareByFav(theOwners.get(i), theOwners.get(j + 1)) > 0) {
					Owner aux = theOwners.get(j);
					theOwners.set(j, theOwners.get(j + 1));
					theOwners.set(j + 1, aux);
				}
			}
		}
	}
	
	public Owner searchByBinariMethodName() {
		Owner theonwer=null;
		int start=0;
		int lim= theOwners.size()-1;
		int middle=0;
		boolean ok= false;
		while(start<=lim && !ok) {
			middle=(lim-start)/2;
			if(theOwners.get(middle).getName().compareTo(name)<0) {
				start=middle+1;
			}else if(theOwners.get(middle).getName().compareTo(name)>0) {
				lim= middle -1;
				
				
			}else if(theOwners.get(middle).getName().compareTo(name) ==0) {
				ok=true;
			}
		}
		return theOwners.get(middle);
	}
	
	public int compare(Club c1, Club c2) {
		int a1=c1.getTheOwners().size();
		int a2= c2.getTheOwners().size();
		if(a1>a2) return 1;
		if(a1<a2) return -1;
		return 0;
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

	public String getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(String foundationDate) {
		this.foundationDate = foundationDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Owner> getTheOwners() {
		return theOwners;
	}

	public void setTheOwners(ArrayList<Owner> theOwners) {
		this.theOwners = theOwners;
	}

	@Override
	public int compareTo(Club c) {
		return this.name.compareTo(c.getName());
	}
	
	public int idComparation(Club c) {
		return id.compareTo(c.getId());
	}
	public int petFavComparation(Club c) {
		return type.compareTo(c.getType());
		
	}
}
