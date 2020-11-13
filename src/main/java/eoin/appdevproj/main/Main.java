package eoin.appdevproj.main;

import eoin.appdevproj.model.Household;
import eoin.appdevproj.model.Occupant;
import eoin.appdevproj.service.HouseholdService;
import eoin.appdevproj.service.OccupantServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        OccupantServiceImp occupantserv = (OccupantServiceImp) context.getBean("occupantServiceImp");
        HouseholdService houseserv = (HouseholdService) context.getBean("householdServiceImp");

        Scanner keyboard = new Scanner(System.in);


        System.out.println("There are " + occupantserv.countTheOccupants() + " occupant(s) in the database...");
        System.out.println("There are " + houseserv.countTheHouseholds() + " household(s) in the database...");

        List<Occupant> occupants = occupantserv.findOccupants();
        for(Occupant occupant:occupants){
            System.out.println(occupant);
        }
        List<Household> households = houseserv.findHouseholds();
        for(Household household:households){
            System.out.println(household);
        }

        while(true){
            System.out.println("\n----------------------------------------\n");
            System.out.println("Enter a number to pick option  ==> ");
            System.out.println("1. Search for a household by Eircode, listing the details of the people in the household");
            System.out.println("2. Add a household, along with occupants");
            System.out.println("3. Add person and assign to household");
            System.out.println("4. Move a person from one household to another");
            System.out.println("5. Delete household (This also deletes its occupants)");
            System.out.println("6. Delete Person");
            System.out.println("7. Display statistics");
            System.out.println("\n----------------------------------------\n");

            int initialchoice = keyboard.nextInt();
            keyboard.nextLine();

            switch (initialchoice){
                case 1:
                    System.out.println("Enter the eircode ==> ");
                    String eir1 = keyboard.nextLine();
                    System.out.println(houseserv.findHouseholdByEircode(eir1));
                    System.out.println(occupantserv.findOccupantByEircode(eir1));
                    break;

                case 2:
                    System.out.println("Enter the eircode ==> ");
                    String eir2 = keyboard.nextLine();

                    System.out.println("Enter the address ==> ");
                    String add2 = keyboard.nextLine();

                    System.out.println(houseserv.addHousehold(eir2,add2));

                    System.out.println("How many occupants would you like to add? ==> ");
                    int numberocc = keyboard.nextInt();
                    keyboard.nextLine();
                    for(int i = 0; i < numberocc; i++){
                        System.out.println("Enter Name ==>");
                        String name2 = keyboard.nextLine();

                        System.out.println("Enter age ==>");
                        int age2 = keyboard.nextInt();
                        keyboard.nextLine();

                        System.out.println("Enter occupation ==>");
                        String occupation2 = keyboard.nextLine();

                        System.out.println(occupantserv.addOccupant(name2, age2, occupation2, eir2));
                    }
                    break;

                case 3:
                    System.out.println("Enter Name ==>");
                    String name3 = keyboard.nextLine();

                    System.out.println("Enter age ==>");
                    int age3 = keyboard.nextInt();
                    keyboard.nextLine();

                    System.out.println("Enter occupation ==>");
                    String occupation3 = keyboard.nextLine();

                    System.out.println("Enter eircode ==> ");
                    String eir3 = keyboard.nextLine();

                    System.out.println(occupantserv.addOccupant(name3, age3, occupation3, eir3));
                    break;

                case 4:
                    System.out.println("Enter the persons name ==> ");
                    String name4 = keyboard.nextLine();

                    System.out.println("Enter new eircode ==> ");
                    String eir4 = keyboard.nextLine();

                    System.out.println(occupantserv.changeOccupantEircode(name4,eir4));
                    break;

                case 5:
                    System.out.println("Enter eircode ==> ");
                    String eir5 = keyboard.nextLine();

                    System.out.println(houseserv.deleteHousehold(eir5));
                    break;

                case 6:
                    System.out.println("Delete by id or name? (id = 1, name = 2) ==> ");
                    int deletechoice = keyboard.nextInt();
                    keyboard.nextLine();
                    if(deletechoice==1){
                        System.out.println("Enter ID ==>");
                        int deleteid = keyboard.nextInt();
                        keyboard.nextLine();
                        System.out.println(occupantserv.deleteOccupant(deleteid));
                    }else{
                        System.out.println("Enter name ==> ");
                        String name6 = keyboard.nextLine();
                        System.out.println(occupantserv.deleteOccupant(name6));
                    }

                case 7:
                    System.out.println("Average Age : " + occupantserv.averageAge());
                    System.out.println("Number of Students : " + occupantserv.numberStudents());
                    System.out.println("Number of OAPs : " + occupantserv.numberOAP());
                    break;
            }
        }

    }
}
