import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

import com.Order;
import com.car.*;
import com.user.Member;
import com.user.User;

public class FilkomTravel {
    private static HashMap<String, Member> memberDB;
    private static ArrayList<Car> carDB;
    private static Scanner S;

    public static void main(String[] args) throws Exception {
        memberDB = new HashMap<>();
        carDB = carInitialization();
        S = new Scanner(System.in);

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
        // Unfinished
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

        Member member = new Member(name, identityNum);

        System.out.print("Enter your phone number: ");
        member.phoneNum = S.nextLine();

        System.out.print("Enter your home address: ");
        member.address = S.nextLine();

        // Set the user credentials
        System.out.print("Enter your username: ");
        String username = S.nextLine();

        System.out.print("Enter your password: ");
        String password = S.nextLine();

        System.out.println("Registration Successful!");
        System.out.println();

        member.setCredentials(username, password);
        memberDB.put(username, member);

        return member;
    }

    private static ArrayList<Car> carInitialization() {
        ArrayList<Car> cars = new ArrayList<>();
        // Small Cars
        SmallCar sc1 = new SmallCar();
        sc1.brand = "Honda";
        sc1.model = "Brio";
        sc1.licensePlateNum = "A 54 HEL";
        sc1.isIncludeDriver(false);
        sc1.carCapacity();
        sc1.getRentFee();
        sc1.setCarUniqueCode(433);
        sc1.color = "Red";
        cars.add(sc1);
    
        SmallCar sc2 = new SmallCar();
        sc2.brand = "Daihatsu";
        sc2.model = "Ayla";
        sc2.licensePlateNum = "M 43 NG";
        sc2.isIncludeDriver(true);
        sc2.carCapacity();
        sc2.getRentFee();
        sc2.setCarUniqueCode(333);
        sc2.color = "Blue";
        cars.add(sc2);
        
        // Medium Cars
        MediumCar mc1 = new MediumCar();
        mc1.brand = "Toyota";
        mc1.model = "Avanza";
        mc1.licensePlateNum = "N 941 AM";
        mc1.isIncludeDriver(true);
        mc1.carCapacity();
        mc1.getRentFee();
        mc1.setCarUniqueCode(999);
        mc1.color = "Silver";
        cars.add(mc1);
        
        MediumCar mc2 = new MediumCar();
        mc2.brand = "Honda";
        mc2.model = "BRV";
        mc2.licensePlateNum = "N 4 KAM";
        mc2.isIncludeDriver(false);
        mc2.carCapacity();
        mc2.getRentFee();
        mc2.setCarUniqueCode(215);
        mc2.color = "Black";
        cars.add(mc2);
        
        MediumCar mc3 = new MediumCar();
        mc3.brand = "Daihatsu";
        mc3.model = "Sigra";
        mc3.licensePlateNum = "N 45 GOR";
        mc3.isIncludeDriver(true);
        mc3.carCapacity();
        mc3.getRentFee();
        mc3.setCarUniqueCode(468);
        mc3.color = "Black";
        cars.add(mc3);
        
        // Large Cars
        LargeCar lc1 = new LargeCar();
        lc1.brand = "Toyota";
        lc1.model = "HiAce";
        lc1.licensePlateNum = "B 490 NG";
        lc1.isIncludeDriver(true);
        lc1.carCapacity();
        lc1.getRentFee();
        lc1.setCarUniqueCode(222);
        lc1.color = "White";
        cars.add(lc1);
        
        LargeCar lc2 = new LargeCar();
        lc2.brand = "Isuzu";
        lc2.model = "Elf Microbus";
        lc2.licensePlateNum = "B 444 AA";
        lc2.isIncludeDriver(false);
        lc2.carCapacity();
        lc2.getRentFee();
        lc2.setCarUniqueCode(122);
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
                car.licensePlateNum, 
                car.getRentFee(), 
                car.color, 
                car.capacity
            );
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

}