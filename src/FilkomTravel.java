/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import components.car.*;
import components.payment.*;
import components.user.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class FilkomTravel {
    private static HashMap<String, Member> memberDB;
    private static HashMap<String, Guest> guestDB;
    private static ArrayList<Car> carDB;
    private static Scanner S;
    private static HashMap<String, Order> cartDB;

    public static void main(String[] args) throws Exception {
        memberDB = new HashMap<>();
        carDB = initializeCars();
        S = new Scanner(System.in);

        welcomeScreen();

    }

    public static void welcomeScreen() {
        while (true) {
            System.out.println("Welcome to Filkom Travel!!");
            System.out.print("Do you want to log in as member? [y/n] ");
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
        LocalDate promoStartDate = LocalDate.of(2024, 1, 1);
        LocalDate promoEndDate = LocalDate.of(2024, 12, 31);
        PercentOffPromo discount = new PercentOffPromo(
            192910, 
            "Diskon Full Senyum", 
            promoStartDate, 
            promoEndDate, 
            0.5, 
            400_000
        );

        CashbackPromo cashback = new CashbackPromo(
            199368, 
            "Cashback Ceria", 
            promoStartDate, 
            promoEndDate, 
            0.3, 
            1_000_000
        );

        ShippingDiscount shippingDiscount = new ShippingDiscount(
            123093, 
            "Penyelamat Ongkir", 
            promoStartDate, 
            promoEndDate, 
            0.5, 
            300_000
        );


        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("|                                     Order Mode                                    |");
        System.out.println("-------------------------------------------------------------------------------------");

        // Print all available cars .....
        printCars();

        System.out.printf("Please choose your car [0-%d]  : ", carDB.size() - 1);
        Car car = carDB.get(S.nextInt());
        System.out.print("Enter the quantity for the car : ");
        int qty = S.nextInt(); S.nextLine();

        System.out.println("---------------------------- Set rent start and end date ----------------------------");
        System.out.println("The time formatting is [dd/MM/yyyy HH:mm]");
        System.out.println("Example: 29/05/2024 23:15");
        System.out.printf("%-20s: ", "Enter start date");
        String startDate = S.nextLine();
        System.out.printf("%-20s: ", "Enter end date");
        String endDate = S.nextLine();
        System.out.println();

        Order order = user.makeOrder(car, qty);
        order.setRentStartDate(startDate);

        if (user instanceof Member) {
            PriorityQueue<Promotion> promotion = new PriorityQueue<>();
            if (discount.isCustomerEligible((Member) user)) {
                if (discount.isMinimumPriceEligible(order)) {
                    discount.setOrder(order);
                    promotion.add(discount);
                }

                if (cashback.isMinimumPriceEligible(order)) {
                    cashback.setOrder(order);
                    promotion.add(cashback);
                }

                if (shippingDiscount.isShippingDiscountEligible(order)) {
                    discount.setOrder(order);
                    promotion.add(shippingDiscount);
                }
            }

            if (!promotion.isEmpty()) {
                System.out.println("Please choose what promo you want to use: ");

                int c = 1;
                System.out.println("0. Skip promo");
                for (Promotion pr : promotion) {
                    long totalPromo = pr.calculateShippingDiscount() + pr.totalCashback() + pr.totalDiscount();
                    System.out.printf("%d. %s - %d\n", c, pr.getPromoName(), totalPromo);
                }

                System.out.printf("Select promo : [%d - %d] ", 1, promotion.size());
                int selection = S.nextInt(); S.nextLine();

                Promotion appliedPromo = null;
                for (int i = 0; i < selection; i++) {
                    appliedPromo = promotion.remove();
                }

                if (order.applyPromo(appliedPromo)) {
                    System.out.println("Promo applied!");
                } else {
                    System.out.println("Promo isn't applied");
                }
            }
        }
        System.out.println("Order creation successful!");
        System.out.println("To confirm your payment, please select the check out menu.");

        return order;
    }

    public static void addToCart(String IDPemesan, String IDMenu, int qty, String TanggalAwal) {
        User user = memberDB.get(IDPemesan);
        if (user == null) {
            user = guestDB.get(IDPemesan);
        }

        Car car = null;
        for (Car c : carDB) {
            if (c.model().equals(IDMenu)) {
                car = c;
                break;
            }
        }

        if (user == null || car == null) {
            System.out.println("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
            return;
        }

        boolean isUpdated = false;
        String cartKey = IDPemesan + "-" + IDMenu;
        Order order = cartDB.get(cartKey);

        if (order != null) {
            order.setRentDuration(order.getRentDuration() + qty);
            isUpdated = true;
        } else {
            order = user.makeOrder(car, qty);
            order.setRentStartDate(TanggalAwal);
            cartDB.put(cartKey, order);
        }

        String message = String.format("ADD_TO_CART SUCCESS: %d %s %s %s %s",
            qty,
            (qty > 1) ? "days" : "day",
            car.getBrand() + " " + car.getModel(),
            car.getLicensePlate(),
            isUpdated ? "(UPDATED)" : "(NEW)"
        );

        System.out.println(message);
    }


    private static void checkOut(User user) {
        // TODO implements check out method
        HashMap<Integer, Order> orders  = user.getOrders();
        Set<Integer> keyset = orders.keySet();

        if (orders.isEmpty()) {
            System.out.println("There are no pending order");
            System.out.println("Returning back...");
            return;
        }
        
        System.out.println("No.  Model           Original price");
        for (Integer integer : keyset) {
            Order order = orders.get(integer);
            System.out.printf("%-4d %-15s %-13d\n", integer, order.getCarName(), (int) order.calculatePrice());
        }
        System.out.print("Select which order you want to check out : ");
        int selection = S.nextInt(); S.nextLine();

        Order order = orders.remove(selection);
        order.checkOut();
        order.printDetails();

        order.pay();
        System.out.println("Payment successful!");
    }

    private static void guestMode() {
        User guest = guestRegistration();

        main:
        while (true) {
            System.out.println("Please select the menu: ");
            System.out.println("0. Rent a Car");
            System.out.println("1. Check out order");
            System.out.println("2. Log out");
            System.out.print("Selection [0-2] ");

            String selection = S.nextLine();
            switch (selection) {
                case "0":
                    Order order = takeOrder(guest);
                    break;

                case "1":
                    checkOut(guest);
                    break;

                case "2":
                    break main;
            
                default:
                    break;
            }
            
        }
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

        menu:
        while (true) {
            System.out.println("Please Select the Menu:");
            System.out.println("0. Rent a Car");
            System.out.println("1. Print Order History");
            System.out.println("2. Check out order");
            System.out.println("3. Log out");
            System.out.print("Selection [0-3] ");
            selection = S.nextLine();

            switch (selection) {
                case "0":
                    Order order = takeOrder(member);
                    break;
            
                case "1":
                    member.printHistory();
                    break;

                case "2":
                    checkOut(member);
                    break;
                
                case "3":
                    break menu;

                default:
                    System.out.println("Please input a valid value");
                    System.out.println();
                    return;
            }
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
        System.out.print("Enter your first name: ");
        String firstName = S.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = S.nextLine();

        Guest guest = new Guest(firstName, lastName);

        System.out.print("Enter your phone number: ");
        guest.phoneNum = S.nextInt(); S.nextLine();

        System.out.print("Enter your home address: ");
        guest.address = S.nextLine();
        System.out.println();

        return guest;
    }

    private static Member memberRegistration() {
        // Basic user registration
        System.out.print("Enter your first name: ");
        String firstName = S.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = S.nextLine();

        // Set the user credentials
        System.out.print("Enter your username: ");
        String username = S.nextLine();

        System.out.print("Enter your password: ");
        String password = S.nextLine();

        Member member = new Member(firstName, lastName);
        member.register(username, password);

        // More user information registrarion
        System.out.print("Enter your phone number: ");
        member.phoneNum = S.nextInt(); S.nextLine();

        System.out.print("Enter your home address: ");
        member.address = S.nextLine();

        System.out.println("Registration Successful!");
        System.out.println();

        memberDB.put(username, member);

        return member;
    }

    private static ArrayList<Car> initializeCars() {
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
        System.out.printf("| %-2s | %-10s | %-12s | %-12s | %-6s | %-8s |\n", "N.", "Brand", "Model", "Rent Fee", "Color", "Capacity");
        System.out.println("-------------------------------------------------------------------------------------");
        
        for (int i = 0; i < carDB.size(); i++) {
            Car car = carDB.get(i);
            System.out.printf(
                "| %-2d | %-10s | %-12s | Rp. %-8s | %-6s | %-8s |\n", 
                i, 
                car.brand, 
                car.model, 
                car.getRentalFee(), 
                car.color, 
                car.getCapacity()
            );
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

}