import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

import com.*;
import com.car.*;
import com.user.*;


public class FilkomTravel {
    private static HashMap<String, Member> memberDB;
    private static ArrayList<Car> carDB;
    private static Scanner S;

    public static void main(String[] args) throws Exception {
        memberDB = new HashMap<>();
        carDB = carInitialization();
        S = new Scanner(System.in);

        welcomeScreen();

    }

    public static void welcomeScreen() {
        while (true) {
            System.out.println("Welcome to Filkom Travel!!");
            System.out.print("Do you want to log in as member and get 10% discount? [y/n] ");
            String selection = S.nextLine();

            switch (selection) {
                case "y":
                    memberMode();
                    break;

                case "n":
                    guestMode();
                    break;
                default:
                    System.out.println("Please input a valid value");
                    System.out.println();
                    return;
            }

            System.out.print("Do you want to continue? [y/n] ");
            selection = S.nextLine();
            if (selection.equals("n")) {
                System.out.println("Thank you for using Filkom Travel");
                break;
            }
        }
    }

    private static Order takeOrder(User user) {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("|                                     Order Mode                                    |");
        System.out.println("-------------------------------------------------------------------------------------");

        // Print all available cars .....
        printCars();

        System.out.printf("Please choose your car [0-%d] : ", carDB.size() - 1);
        Car car = carDB.get(S.nextInt());
        S.nextLine();
        System.out.print("Do you want to hire a driver? (no additional charges) [y/n] ");
        car.includeDriver = S.nextLine().equals("y") ? true : false;

        System.out.println("---------------------------- Set rent start and end date ----------------------------");
        System.out.println("The time formatting is [dd/MM/yyyy HH:mm]");
        System.out.println("Example: 29/05/2024 23:15");
        System.out.printf("%-20s: ", "Enter start date");
        String startDate = S.nextLine();
        System.out.printf("%-20s: ", "Enter end date");
        String endDate = S.nextLine();
        System.out.println();

        Order order = user.order(car);
        order.setRentStartDate(startDate);
        order.setRentEndDate(endDate);

        return order;
    }

    private static void guestMode() {
        User guest = guestRegistration();
        Order order = takeOrder(guest);

        order.printBill();
    }

    private static void memberMode() {
        System.out.print("Have you registered as a member? [y/n] ");
        String selection = S.nextLine();
        Member member;

        switch (selection) {
            case "y":
                member = memberLogin();
                break;
        
            case "n":
                member = memberRegistration();
                break;

            default:
                System.out.println("Please input a valid value");
                member = null;
                return;
        }

        System.out.println("Please Select the Menu:");
        System.out.println("0. Rent a Car");
        System.out.println("1. Print Order History");
        System.out.print("Selection [0-1] ");
        selection = S.nextLine();

        switch (selection) {
            case "0":
                Order order = takeOrder(member);
                order.printBill();
                break;
        
            case "1":
                member.printHistory();
                break;

            default:
                System.out.println("Please input a valid value");
                System.out.println();
                return;
        }

        member.logout();
    }

    private static Member memberLogin() {
        System.out.print("Enter your username: ");
        String username = S.nextLine();
        System.out.print("Enter your password: ");
        String password = S.nextLine();
        Member member = memberDB.get(username);

        member.login(username, password);
        System.out.println();

        return member;
    }

    private static User guestRegistration() {
        // Basic user registration
        System.out.print("Enter your name: ");
        String name = S.nextLine();
        System.out.print("Enter your identity number: ");
        String identityNum = S.nextLine();
        User guest = new User(name, identityNum);
        System.out.print("Enter your phone number: ");
        guest.phoneNum = S.nextLine();
        System.out.print("Enter your home address: ");
        guest.address = S.nextLine();
        System.out.println();

        return guest;
    }

    private static Member memberRegistration() {
        // Basic user registration
        System.out.print("Enter your name: ");
        String name = S.nextLine();

        System.out.print("Enter your identity number: ");
        String identityNum = S.nextLine();

        // Set the user credentials
        System.out.print("Enter your username: ");
        String username = S.nextLine();

        System.out.print("Enter your password: ");
        String password = S.nextLine();

        Member member = new Member(name, identityNum, username, password);

        // More user information registrarion
        System.out.print("Enter your phone number: ");
        member.phoneNum = S.nextLine();

        System.out.print("Enter your home address: ");
        member.address = S.nextLine();

        System.out.println("Registration Successful!");
        System.out.println();

        memberDB.put(username, member);

        return member;
    }

    private static ArrayList<Car> carInitialization() {
        ArrayList<Car> cars = new ArrayList<>();
        // Small Cars
        SmallCar sc1 = new SmallCar("A 54 HEL");
        sc1.brand = "Honda";
        sc1.model = "Brio";
        sc1.includeDriver = false;
        sc1.color = "Red";
        cars.add(sc1);
    
        SmallCar sc2 = new SmallCar("M 43 NG");
        sc2.brand = "Daihatsu";
        sc2.model = "Ayla";
        sc2.includeDriver = true;
        sc2.color = "Blue";
        cars.add(sc2);
        
        // Medium Cars
        MediumCar mc1 = new MediumCar("N 941 AM");
        mc1.brand = "Toyota";
        mc1.model = "Avanza";
        mc1.includeDriver = true;
        mc1.color = "Silver";
        cars.add(mc1);
        
        MediumCar mc2 = new MediumCar("N 4 KAM");
        mc2.brand = "Honda";
        mc2.model = "BRV";
        mc2.includeDriver = false;
        mc2.color = "Black";
        cars.add(mc2);
        
        MediumCar mc3 = new MediumCar("N 45 GOR");
        mc3.brand = "Daihatsu";
        mc3.model = "Sigra";
        mc3.includeDriver = true;
        mc3.color = "Black";
        cars.add(mc3);
        
        // Large Cars
        LargeCar lc1 = new LargeCar("B 490 NG");
        lc1.brand = "Toyota";
        lc1.model = "HiAce";
        lc1.color = "White";
        cars.add(lc1);
        
        LargeCar lc2 = new LargeCar("B 444 AA");
        lc2.brand = "Isuzu";
        lc2.model = "Elf Microbus";
        lc2.color = "White";
        cars.add(lc2);

        return cars;
    }

    private static void printCars() {
        System.out.println("----------------------------- List of all available cars ----------------------------");
        System.out.printf("| %-2s | %-10s | %-12s | %-13s | %-12s | %-6s | %-8s |\n", "N.", "Brand", "Model", "License Plate", "Rent Fee", "Color", "Capacity");
        System.out.println("-------------------------------------------------------------------------------------");
        
        for (int i = 0; i < carDB.size(); i++) {
            Car car = carDB.get(i);
            System.out.printf(
                "| %-2d | %-10s | %-12s | %-13s | Rp. %-8s | %-6s | %-8s |\n", 
                i, 
                car.brand, 
                car.model, 
                car.getLicensePlate(), 
                car.getRentalFee(), 
                car.color, 
                car.getCapacity()
            );
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

}