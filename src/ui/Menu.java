package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.*;

public class Menu {

	private Investor inv;
	private Scanner sc;

	public Menu() {
		inv = new Investor();
		sc = new Scanner(System.in);
		welcomeToTheProgram();
		systemManagement();
		init();

	}

	public void welcomeToTheProgram() {

		String msg = "";

		msg += "WELCOME TO THE PET CLUB APP";

		System.out.println();
		System.out.println(msg);
	}

	public void systemManagement() {

		boolean exit = false;

		while (!exit) {

			int userInput = optionsMenu();

			switch (userInput) {
			case 1:
				clubRegistration();

				break;

			case 2:
				try {
					petOwnerRegistration();
				} catch (NonExistentClubException e1) {
					e1.printStackTrace();
				}
				break;

			case 3:

				petRegistration();

				break;

			case 4:

				clubReportGenerate();
				break;

			case 5:

				ownerReportGenerate();
				break;

			case 6:

				try {
					searchClub();
				} catch (NonExistentClubException e1) {
					e1.printStackTrace();
				}
				break;

			case 7:
				try {
					findOwner();
				} catch (NonExistentClubException e1) {
					e1.printStackTrace();
				}

				break;
			case 8:

				toDeleteAClub();
				break;
			case 9:

				deleteOwnerFromSystem();

				break;
			case 10:

				deleteASpecificPet();

				break;

			case 11:

				toSaveInformation();

				break;

			case 12:
				toSaveInformation();
				try {
					inv.toPrintAReportOfOwner();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					inv.toPrintAReport();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				exit = true;

				break;

			default:
				System.out.println();

			}

		}

		System.out.println("***************** Thanks for using*******************");
	}

	private void toSaveInformation() {
		try {
			inv.toPrintAReport();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < inv.getTheClubs().size(); i++) {
			inv.getTheClubs().get(i).OwnersSerialize();
		}
	}

	private void deleteASpecificPet() {
		try {

			System.out.println(
					"Type the name of the club where the owner's pet you want to delete is");
			String name = sc.nextLine();
			Club p = inv.searchClubByTheName(name);
			System.out.println("Please type the name of the owner has  that you want to delete.");
			String owner = sc.nextLine();
			Owner o = p.serchOwner(owner);
			System.out.println("Please type the name of the owner has  that you want to delete.");
			String pet = sc.nextLine();
			o.deleteASpecificPet(pet);
			p.OwnersSerialize();
		} catch (NonExistentClubException e) {
			System.out.println(e.getMessage());
			System.out.println();
		}

	}

	private void deleteOwnerFromSystem() {

		try {

			System.out.println("Please type the name of the Club where is the owner that you want to delete.");
			String name = sc.nextLine();
			Club p = inv.searchClubByTheName(name);
			System.out.println("Please type the name of the owner  that you want to delete.");
			String owner = sc.nextLine();
			p.deleteOwnerFromSystem(owner);
		} catch (NonExistentClubException e) {

			System.out.println(e.getMessage());
			System.out.println();
		}

	}

	private void toDeleteAClub() {

		System.out.println("Please type the name of the Club that you want to delete.");
		String name = sc.nextLine();
		try {
			inv.toDeleteAClub(name);
		} catch (NonExistentClubException e) {

			System.out.println(e.getMessage());
			System.out.println();
		}

	}

	private void findOwner() throws NonExistentClubException {
		System.out.println("Please type the name of the club where is the owner that you want to look for:");
		String name = sc.nextLine();
		Club pt = inv.searchClubByTheName(name);
		System.out.println("you will look here: " + pt.getName() + "\n");
		System.out.println("Please type the name of the owner that you want to look for:");
		String owner = sc.nextLine();
		Owner o = pt.serchOwner(owner);
		System.out.println(o.toString());


	}

	private void searchClub() throws NonExistentClubException {
		System.out.println("Please type the name of the club that you want to look for:");
		String name = sc.nextLine();
		Club pt = inv.searchClubByTheName(name);
		System.out.println(pt.toString() + "\n");

	}

	public int optionsMenu() throws InputMismatchException {
		int value;
		while (!false) {
			try {
				System.out.println("What would you like to do?");

				System.out.println("1.  Register a club");
				System.out.println("2.  Register a pet owner ");
				System.out.println("3.  Register a pet ");
				System.out.println("4.  Generate clubs report");
				System.out.println("5.  Generate owners report");
				System.out.println("6.  Search Pet club");
				System.out.println("7.  search Owner");
				System.out.println("8.  delete club");
				System.out.println("9.  delete Owner");
				System.out.println("10. delete Pet");
				System.out.println("11. to save");
				System.out.println("12. exit");

				value = askInt();
			} catch (InputMismatchException e) {
				continue;
			}

			return value;
		}
	}

	public void clubRegistration() {

		System.out.println("please type the id of the club");
		String id = sc.nextLine();

		System.out.println("please type the name of the club");
		String name = sc.nextLine();

		System.out.println("please type the current date in this format: dd/mm/yy");
		String date = sc.nextLine();

		System.out.println("please type the pet type of the club\n1:for Dog\n2: for Cat\n3: for Bird\n4: for Reptile");
		String type = sc.nextLine();
		System.out.println(
				"you just registered a club with the following specs:\n" + id + " " + name + " " + date + " " + type);
		System.out.println();

		inv.toAddAnewClub(id, name, date, type);

	}

	public void petOwnerRegistration() throws NonExistentClubException {

		System.out.println("please type the name of the Pet club to which you want to add the owner :");
		String club = sc.nextLine();
		Club p = inv.searchClubByTheName(club);
		System.out.println("please type the id of the Owner");
		String idOwner = sc.nextLine();

		System.out.println("please type the name of the owner");
		String nameOwner = sc.nextLine();

		System.out.println("please type the name of last name owner");
		String lastName = sc.nextLine();

		System.out.println("please type the birthdate of the owner in this format: dd/mm/yy");
		String birthDate = sc.nextLine();

		System.out.println(
				"please type of pet the owner has the club\n1:for Dog\n2: for Cat\n3: for Bird\n4: for Reptile");
		String typeOwner = sc.nextLine();

		try {
			System.out.println("you registered a pet owner with the following specs:\n" + idOwner + " " + nameOwner
					+ " " + lastName + " " + birthDate + " " + "type of pet: " + typeOwner + "\n" + "to the club:" + " "
					+ p.getName());
			p.addOwnerToAClub(idOwner, nameOwner, lastName, birthDate, typeOwner);
			System.out.println();
		} catch (OwnerAlreadyExistsException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void petRegistration() {
		System.out
				.println("Please type the name of the Pet club where  is the Owner to which you want to assign a pet:");
		String club = sc.nextLine();
		Club p;
		try {
			p = inv.searchClubByTheName(club);
			System.out.println();
			System.out.println("Please type the name of the Owner you want to add a pet to");
			String owner = sc.nextLine();
			Owner o = p.serchOwner(owner);
			System.out.println("please type the id of the pet");
			String idPet = sc.nextLine();

			System.out.println("please type the name of the pet");
			String namePet = sc.nextLine();

			System.out.println("please type the genre of the pet");
			String genre = sc.nextLine();

			System.out.println(
					"please type of pet the owner has the club\n1:for dogs\n2: for birds\n3: bears\n4: lions");
			String petType = sc.nextLine();

			System.out.println("please type the birthdate of the pet in this format: dd/mm/yy");
			String bdate = sc.nextLine();
			try {
				o.addPet(idPet, namePet, genre, petType, bdate);
			} catch (PetWithTheSameNameException e) {
				System.out.println(e.getMessage());
			}
		} catch (NonExistentClubException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}

	}

	public void clubReportGenerate() {
		boolean flag;
		System.out.println("please type the point for sorting and generating the club report");
		System.out.println("1. by amount of owners ");
		System.out.println("2. by name ");
		System.out.println("3. by id ");
		System.out.println("4. by creation Date ");
		
		System.out.println("5. by pet preference ");


		int point = askInt();
		switch (point) {
		case 1:
			inv.bubbleSortAmountOwner();
			System.out.println(inv.makeAReportOfTheClubs());
			break;
		case 2:
			inv.selectionSortName();
			System.out.println(inv.makeAReportOfTheClubs());
			break;
		case 3:
			inv.selectionSortId();
			System.out.println(inv.makeAReportOfTheClubs());
			break;
		case 4:
			inv.selectionSortFoundationDate();
			System.out.println(inv.makeAReportOfTheClubs());
			break;
		
		case 5:
			inv.selectionSortforFavPet();
			System.out.println(inv.makeAReportOfTheClubs());
			break;
		}

	}

	public int askInt() {
		int ret = 0;
		try {
			String i = sc.nextLine();
			ret = Integer.parseInt(i);

		} catch (NumberFormatException e) {
			System.out.println("Please type a valid option");
		}
		return ret;
	}

	public void ownerReportGenerate() throws NumberFormatException {
		try {
			System.out.println("Please type the name of the Pet club from which you want to get the Owner report:");
			System.out.println();
			String name = sc.nextLine();
			Club p = inv.searchClubByTheName(name);
			System.out.println("the owners of the club: " + " " + p.getName() + " " + "are:");
			System.out.println();
			System.out.println("please type the point for sorting and generating the owner report");
			System.out.println("1. by amount of pets ");
			System.out.println("2. by name ");
			System.out.println("3. by last name ");
			System.out.println("4. by id ");
			System.out.println("5. by birth date ");
			System.out.println("6. by pet preference ");

			int point = askInt();
			switch (point) {
			case 1:
				p.orderByBubbleSort();
				System.out.println(inv.makeAReportOfTheClubs());
				break;
			case 2:
				p.orderBySelection();
				System.out.println(inv.makeAReportOfTheClubs());
				break;
			case 3:
				p.orderByInsertion();
				System.out.println(p.showOwner());
				break;
			case 4:
				p.orderByInsertion();
				System.out.println(p.showOwner());
				break;
			case 5:
				p.orderByInsertionOftheBirthdate();
				System.out.println(p.showOwner());
				break;
			case 6:
//				p.orderBubblebyFav();
				System.out.println(p.showOwner());
				break;
			}

		} catch (NonExistentClubException e) {
			System.out.println(e.getMessage());
		}
	}
	public void init() {
		Club c = new Club("111", "Anr", "2/3/2018", "perro");
		inv.addClub(c);
		Owner o = new Owner("111", "yuyu", "borrero", "20/11/2000", "dog");
		c.addOwner(o);
		Pet p = new Pet("11111", "pinina", "female", "dog", "3/6/2000");
		o.addPet(p);
		
		
	}

}