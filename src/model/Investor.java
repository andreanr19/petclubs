package model;

import java.io.*;
import java.util.*;

public class Investor {

	private ArrayList<Club> theClubs;
	private ArrayList<Owner> theOwners;
	private ArrayList<Pet> thePets;
	private static final String path= "data/clubData.csv";
	private static final String serialized = "data/owner.txt";

	public Investor() {
		theClubs = new ArrayList<Club>();
		theOwners = new ArrayList<Owner>();
		thePets = new ArrayList<Pet>();
		try {
			loadClubsInformation();
			loadOwnerInformation();
			loadPetInformation();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toAddAnewClub(String id, String name, String foundationDate, String type) {
		Club newClub = new Club(id, name, foundationDate, type);
		theClubs.add(newClub);
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadClubsInformation() throws IOException {

		BufferedReader br= new BufferedReader(new FileReader(new File(path)));
		String line = br.readLine();
		while (line != null) {
			String[] components = line.split(",");
			String id = components[0];
			String name = components[1];
			String foundationDate = components[2];
			String type = components[3];

			Club newC = new Club(id, name, foundationDate, type);
			theClubs.add(newC);
			line = br.readLine();
		}
		br.close();
		
	}
//	public void loadClubsInformation2() throws IOException {
//
//		File fl = new File("data/clubData. csv");
//		FileReader fr = new FileReader(fl);
//		BufferedReader br = new BufferedReader(fr);
//		String line = br.readLine();
//		while (line != null) {
//			String[] components = line.split(",");
//			String id = components[0];
//			String name = components[1];
//			String foundationDate = components[2];
//			String type = components[3];
//
//			Club newC = new Club(id, name, foundationDate, type);
//			theClubs.add(newC);
//			line = br.readLine();
//		}
//		br.close();
//		fr.close();
//	}

	public void loadOwnerInformation() throws IOException {
		File fl = new File("data/ownerData.cvs");
		FileReader fr = new FileReader(fl);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			String[] components = line.split(",");
			String id = components[0];
			String name = components[1];
			String lastName = components[2];
			String date = components[3];
			String favPet = components[4];

			Owner newOwner = new Owner(id, name, lastName, date, favPet);
			theOwners.add(newOwner);
			line = br.readLine();

		}
		br.close();
		fr.close();
	}

	public void loadPetInformation() throws IOException {
		File fl = new File("data/petData.cvs");
		FileReader fr = new FileReader(fl);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			String[] components = line.split(",");
			String id = components[0];
			String name = components[1];
			String gender = components[2];
			String type = components[3];
			String date = components[4];

			Owner newOwner = new Owner(id, name, gender, type, date);
			theOwners.add(newOwner);
			line = br.readLine();

		}
		br.close();
		fr.close();
	}

	public String makeAReportOfTheClubs() {
		String messagge = " The clubs that are actually in the system are: ";
		for (int i = 0; i < theClubs.size(); i++) {
			messagge += i + ")" + "ID: " + theClubs.get(i).getId() + " - NAME: " + theClubs.get(i).getName()
					+ " - FOUNDATION DATE: " + theClubs.get(i).getFoundationDate() + " - FAV TYPE OF PET: "
					+ theClubs.get(i).getType() + "/n";
		}
		return messagge;
	}

	public void removeAClubFromSystem(String name) {
		for (int i = 0; i < theClubs.size(); i++) {
			if (theClubs.get(i).getName().equals(name)) {
				theClubs.remove(i);
			}
		}
	}

	public void toPrintAReport() throws FileNotFoundException {
		String msg = "";
		PrintWriter w = new PrintWriter(new File(path));
		for (int i = 0; i < theClubs.size(); i++) {
			msg += theClubs.get(i).getId() + "_" + theClubs.get(i).getName() + "_" + theClubs.get(i).getFoundationDate()
					+ "_" + theClubs.get(i).getFoundationDate() + "\n";
		}

		w.print(msg);
		w.close();

	}
	public void toPrintAReportOfOwner() throws FileNotFoundException, IOException{
		ObjectOutputStream towrite= new ObjectOutputStream(new FileOutputStream(serialized)); 
		towrite.writeObject(theOwners);
		towrite.close();
	}
	
	public void toLoadOwners() throws FileNotFoundException, ClassNotFoundException, IOException{
		ObjectInputStream toLoad = new ObjectInputStream(new FileInputStream(serialized));
		theOwners=(ArrayList<Owner>) toLoad.readObject();
		toLoad.close();
	}
	
	
	//METODOS DE BUSQUEDA
	public Club searchClubByTheName(String name) throws NonExistentClubException {
		Club theClub = null;
		boolean found = false;
		for (int i = 0; i < theClubs.size() && !found; i++) {
			if (theClubs.get(i).getName().equalsIgnoreCase(name)) {
				theClub = theClubs.get(i);
				found = true;

			} else {
				throw new NonExistentClubException("The club " + name + " doesn't exist in the system");
			}

		}
		return theClub;

	}

	public Club searchClubByTheDateOfFundation(String dateOfFundation) throws NonExistentClubException {
		Club theClub = null;
		boolean found = false;
		for (int i = 0; i < theClubs.size() && !found; i++) {
			if (theClubs.get(i).getFoundationDate().equalsIgnoreCase(dateOfFundation)) {
				theClub = theClubs.get(i);
				found = true;

			} else {
				throw new NonExistentClubException(
						"The club with the date of foundation " + dateOfFundation + " doesn't exist in the system");
			}

		}
		return theClub;

	}

	//ELIMINAR CLUB
	public void toDeleteAClub(String name) throws NonExistentClubException {
		boolean found = false;
		for (int i = 0; i < theClubs.size() && !found; i++) {
			if (theClubs.get(i).getName().equalsIgnoreCase(name)) {
				found = true;
				theClubs.remove(i);
				System.out.println("The club " + name + " has been sucesfull deleted from the system");
			} else {
				throw new NonExistentClubException("The club " + name + " doesn't exist in the system");
			}
		}
	}

	//BUSQUEDA
	public Club searchClubByThId(String id) throws NonExistentClubException {
		Club theClub = null;
		boolean found = false;
		for (int i = 0; i < theClubs.size() && !found; i++) {
			if (theClubs.get(i).getId().equalsIgnoreCase(id)) {
				theClub = theClubs.get(i);
				found = true;

			} else {
				throw new NonExistentClubException("The club with the id " + id + " doesn't exist in the system");
			}

		}
		return theClub;

	}

	//ORDENAMIENTO
	public void bubbleSortAmountOwner() {

		Club aux = null;

		for (int i = 0; i < theClubs.size(); i++) {
			for (int j = 0; j < theClubs.size() - 1; j++) {
				// Mayor a cero, el primer objeto es mayor al segundo
				if (theClubs.get(j).compare(theClubs.get(j), theClubs.get(j + 1)) > 0) {
					aux = theClubs.get(j);
					theClubs.set(j, theClubs.get(j + 1));
					theClubs.set(j + 1, aux);
				}
			}
		}
	}
	//METODOS DE ORDENAMIENTO POR ID
	public void selectionSortId() {
		for(int i=0; i<theClubs.size()-1; i++) {
			String clubMin= theClubs.get(i).getId();
			int min=i;
			for(int j=i+1; j<theClubs.size(); j++) {
				String actual =theClubs.get(j).getId();
				if(actual.compareTo(clubMin)<0) {
					clubMin= actual;
					min=j;
					
				}
			}
			Club temporal = theClubs.get(min);
			theClubs.set(min, theClubs.get(i));
			theClubs.set(i, temporal);
		}
	}

	//METODOS DE ORDENAMIENTO POR NOMBRE
	public void selectionSortName() {
		for(int i=0; i<theClubs.size()-1; i++) {
			String clubMin= theClubs.get(i).getName();
			int min=i;
			for(int j=i+1; j<theClubs.size(); j++) {
				String actual =theClubs.get(j).getName();
				if(actual.compareTo(clubMin)<0) {
					clubMin= actual;
					min=j;
					
				}
			}
			Club temporal = theClubs.get(min);
			theClubs.set(min, theClubs.get(i));
			theClubs.set(i, temporal);
		}
	}
	//METODOS DE ORDENAMIENTO POR FECHA DE CREACIÓN
	public void selectionSortFoundationDate() {
		for(int i=0; i<theClubs.size()-1; i++) {
			String clubMin= theClubs.get(i).getFoundationDate();
			int min=i;
			for(int j=i+1; j<theClubs.size(); j++) {
				String actual =theClubs.get(j).getFoundationDate();
				if(actual.compareTo(clubMin)<0) {
					clubMin= actual;
					min=j;
					
				}
			}
			Club temporal = theClubs.get(min);
			theClubs.set(min, theClubs.get(i));
			theClubs.set(i, temporal);
		}
	}
	//METODOS DE ORDENAMIENTO POR TIPO DE MASCOTA FAVORITA
	public void selectionSortforFavPet() {
		for(int i=0; i<theClubs.size()-1; i++) {
			String clubMin= theClubs.get(i).getType();
			int min=i;
			for(int j=i+1; j<theClubs.size(); j++) {
				String actual =theClubs.get(j).getType();
				if(actual.compareTo(clubMin)<0) {
					clubMin= actual;
					min=j;
					
				}
			}
			Club temporal = theClubs.get(min);
			theClubs.set(min, theClubs.get(i));
			theClubs.set(i, temporal);
		}
	}
	
	//BUSQUEDA BINARIA POR NOMBRE
	public Club binaryByName(String name) {
		Club pet = null;
		int start = 0;
		int stop = theClubs.size() - 1;
		int middle = 0;
		boolean ok = false;
		while (start <= stop && !ok) {
			middle = (stop - start) / 2;
			if (theClubs.get(middle).getName().compareTo(name) < 0) {
				start = middle + 1;

			} else if (theClubs.get(middle).getName().compareTo(name) > 0) {
				stop = middle - 1;
			} else if (theClubs.get(middle).getName().compareTo(name) == 0) {
				ok = true;
			}
		}
		return theClubs.get(middle);

	}
	//ORDENAMIENTO COMPARATOR POR ID
	public void comparatorSortByIdOfTheOwner() {
		Collections.sort(theOwners,new OwnerComparator());
	}

	public ArrayList<Club> getTheClubs() {
		return theClubs;
	}

	public void setTheClubs(ArrayList<Club> theClubs) {
		this.theClubs = theClubs;
	}

	public void addClub(Club c) {
		theClubs.add(c);
	}

}
