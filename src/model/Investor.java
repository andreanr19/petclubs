package model;

import java.io.*;
import java.util.*;

public class Investor {

	private ArrayList<Club> theClubs;

	public Investor() {
		theClubs = new ArrayList<Club>();
		try {
			loadClubsInformation();

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

		File fl = new File("data/MOCK_DATA (1).csv");
		FileReader fr = new FileReader(fl);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			String[] components = line.split("_");
			String id = components[0];
			String name = components[1];
			String foundationDate = components[2];
			String type = components[3];

			Club newC = new Club(id, name, foundationDate, type);
			theClubs.add(newC);
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

	public String toPrint() {
		String message = "";
		for (int i = 0; i < theClubs.size(); i++) {
			message += theClubs.get(i).getId() + "_" + theClubs.get(i).getName() + "_"
					+ theClubs.get(i).getFoundationDate() + "_" + theClubs.get(i).getFoundationDate() + "\n";
		}
		return message;
	}

	public void toPrintAReport() throws FileNotFoundException {
		String ruta = ("data/MOCK_DATA (1).csv");
		PrintWriter w = new PrintWriter(new File(ruta));
		String theReport = toPrint();
		w.print(theReport);
		w.close();

	}

	// TO SEARCH
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
	
	public void toDeleteAClub(String name) throws NonExistentClubException{
		boolean found= false;
		for(int i=0; i<theClubs.size() && !found; i++) {
			if(theClubs.get(i).getName().equalsIgnoreCase(name)) {
				found =true;
				theClubs.remove(i);
				System.out.println("The club " + name + " has been sucesfull deleted from the system");
			}else  {
				throw new NonExistentClubException("The club " + name + " doesn't exist in the system");
			}
		}
	}

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
	public void bubbleSortAmountOwner() {
		
		Club aux=null;
		
		for(int i=0;i<theClubs.size();i++) {
			for(int j=0; j<theClubs.size()-1;j++) {
				//Mayor a cero, el primer objeto es mayor al segundo
				if(theClubs.get(j).compare(theClubs.get(j), theClubs.get(j+1))>0) {
					aux=theClubs.get(j);
					theClubs.set(j, theClubs.get(j+1));
					theClubs.set(j+1,aux);
				}
			}
		}
	}
	public void selectionSortId() {
		for(int i=0;i< theClubs.size()-1;i++) {
			int min=i;
			Club aux= null;
			for(int j=i+1; j< theClubs.size();j++) {
				if(theClubs.get(j).idComparation(theClubs.get(min))<0){
					min=j;
					aux= theClubs.get(min);
					theClubs.set(min, theClubs.get(i));
					theClubs.set(i, aux);
				}
			}
		}
	}
	public void selectionSortName() {
		for(int i=0;i< theClubs.size()-1;i++) {
			int min=i;
			Club aux= null;
			for(int j=i+1; j< theClubs.size();j++) {
				if(theClubs.get(j).compareTo(theClubs.get(min))<0){
					min=j;
					aux= theClubs.get(min);
					theClubs.set(min, theClubs.get(i));
					theClubs.set(i, aux);
				}
			}
		}
	}
	public void selectionSortFoundationDate() {
		for(int i=0;i< theClubs.size()-1;i++) {
			int min=i;
			Club aux= null;
			for(int j=i+1; j< theClubs.size();j++) {
				if(theClubs.get(j).idComparation(theClubs.get(min))<0){
					min=j;
					aux= theClubs.get(min);
					theClubs.set(min, theClubs.get(i));
					theClubs.set(i, aux);
				}
			}
		}
	}
	public void selectionSortforFavPet() {
		for(int i=0;i< theClubs.size()-1;i++) {
			int min=i;
			Club aux= null;
			for(int j=i+1; j< theClubs.size();j++) {
				if(theClubs.get(j).petFavComparation(theClubs.get(min))<0){
					min=j;
					aux= theClubs.get(min);
					theClubs.set(min, theClubs.get(i));
					theClubs.set(i, aux);
				}
			}
		}
	}
	
	public Club binaryByName(String name) {
		Club pet=null;
		int start=0;
		int stop=theClubs.size()-1;
		int middle=0;
		boolean ok=false;
		while(start<=stop && !ok) {
			middle= (stop-start)/2;
			if(theClubs.get(middle).getName().compareTo(name)<0) {
				start= middle+1;
				
			}else if(theClubs.get(middle).getName().compareTo(name)>0){
				stop= middle-1;
			}else if(theClubs.get(middle).getName().compareTo(name)==0) {
				ok=true;
			}
		}
		return theClubs.get(middle);
		
	}

	public ArrayList<Club> getTheClubs() {
		return theClubs;
	}

	public void setTheClubs(ArrayList<Club> theClubs) {
		this.theClubs = theClubs;
	}

}
