import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

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
        order.setRentEndtDate(endDate);

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

class User {
    protected static int userCounter;
    protected String UID;
    protected String name;
    protected String identityNum;
    protected double discount;
    String phoneNum;
    String address;

    public User(String name, String identityNum) {
        this.name = name;
        this.identityNum = identityNum;
        this.UID = "101" + Integer.toString(userCounter);
        this.discount = 0;
        userCounter++;
    }

    public Order order(Car car) {
        // Unfinished
        Order order = new Order(this, car);

        return order;
    }

    public String getUID() {
        return this.UID;
    }

    public String getName() {
        return this.name;
    }

    public String getIdentityNum() {
        return this.identityNum;
    }
}

class Member extends User {
    private String username;
    private String password;
    private boolean loggedIn;
    private ArrayList<Order> orderHistory;

    public Member(String name, String identityNum) {
        super(name, identityNum);
        this.identityNum = "111" + Integer.toString(userCounter);
        this.discount = 0.1;
        this.loggedIn = true;

        this.orderHistory = new ArrayList<>();
    }

    public void setCredentials(String username, String password) {
        if (!loggedIn)
            return;

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void login(String username, String password) {
        if (!(username.equals(this.username) && password.equals(this.password))) {
            System.out.println("Login Failed!");
            throw new RuntimeException("Fatal: Incorrect Credentials!");
        }
        
        System.out.println("Logged In!");
        this.loggedIn = true;
    }

    public void logout() {
        this.loggedIn = false;
    }

    @Override
    public Order order(Car car) {
        Order order = new Order(this, car);

        orderHistory.add(order);
        return order;
    }

    public void printHistory() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("--------------------------------- Order History -------------------------------------");
        System.out.printf("| %-16s | %-16s | %-16s | %-8s | %-13s |\n", "Invoice Date", "Start Date", "End Date", "Duration", "Net. Charges");

        for (Order order : orderHistory) {
            System.out.printf("| %-16s | %-16s | %-16s | %-5s hr | Rp. %-9s |\n", 
                order.getInvoiceDate(),
                order.getRentStartDate(), 
                order.getRentEndDate(), 
                order.calculateDuration(),
                order.calculateTotalCharges()
            );
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println();
    }
}

class Order {
    private LocalDateTime invoiceDate;
    User renter;
    Car rentedCar;
    private LocalDateTime rentStartDate;
    private LocalDateTime rentEndDate;

    public Order(User renter, Car rentedCar) {
        this.renter = renter;
        this.rentedCar = rentedCar;

        this.invoiceDate = LocalDateTime.now();
    }

    public void setRentStartDate(String formattedDateAndTime) {
        // Unfinished
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime proposedDate = LocalDateTime.parse(formattedDateAndTime, formatter);
        LocalDateTime currentDate = LocalDateTime.now();

        if (proposedDate.isAfter(currentDate)) {
            this.rentStartDate = proposedDate;
        } else {
            System.out.println("Error! The rent start date can't be before current date!");
        }
    }

    public void setRentEndtDate(String formattedDateAndTime) {
        // Unfinished
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime proposedDate = LocalDateTime.parse(formattedDateAndTime, formatter);

        if (proposedDate.isAfter(this.rentStartDate)) {
            this.rentEndDate = proposedDate;
        } else {
            System.out.println("Error! The rent end date can't be before rent start date!");
        }
    }

    public String getRentStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.rentStartDate.format(formatter);
    }
    
    public String getRentEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.rentEndDate.format(formatter);
    }

    public String getInvoiceDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.invoiceDate.format(formatter);
    }

    public int calculateDuration() {
        Duration duration = Duration.between(rentStartDate, rentEndDate);
        return (int) Math.ceil(duration.getSeconds() / 3600);
    }

    public int calculateTotalCharges() {
        int durationInHour = calculateDuration();
        return (int) (Math.ceil(durationInHour/6) * rentedCar.getRentFee() * (1 - this.renter.discount));
    }

    public void printBill() {
        Car car = this.rentedCar;

        System.out.println("################################################");
        System.out.println("########          Payment Bill          ########");
        System.out.println("################################################");
        System.out.println("Invoice date: " + getInvoiceDate());
        System.out.println("Renter Name: " + this.renter.name);
        System.out.println("------------------------------------------------");
        System.out.println("--------------- Rented Car Details -------------");
        System.out.printf("%-21s: %s\n", "Brand Name", car.brand);
        System.out.printf("%-21s: %s\n", "Model", car.model);
        System.out.printf("%-21s: %s\n", "Color", car.color);
        System.out.printf("%-21s: %s\n", "License Plate Number", car.licensePlateNum);
        System.out.printf("%-21s: %b\n", "include driver?", car.includeDriver);
        System.out.printf("%-21s: %s person\n", "Capacity", car.capacity);
        System.out.printf("%-21s: Rp. %s /6 hr\n", "Rental Fee", car.rentFee);
        System.out.println("------------------------------------------------");
        System.out.println("------------------ Rent Details ----------------");
        System.out.printf("%-20s: %s\n", "Start Date", getRentStartDate());
        System.out.printf("%-20s: %s\n", "End Date", getRentEndDate());
        System.out.printf("%-20s: %s hour\n", "Duration", Integer.toString(calculateDuration()));
        System.out.printf("%-30s: Rp. %s\n", "Total Charges", (int) (car.getRentFee() * Math.ceil(calculateDuration()/6)));
        System.out.printf("%-30s: Rp. %s\n", "Total Charges (after discount)", Integer.toString(calculateTotalCharges()));
        System.out.println("################################################");
        System.out.println("######  Thank you for using FilkomTravel!  #####");
        System.out.println("######    We hope to see you next time!    #####");
        System.out.println("################################################");
        System.out.println();
    }
}

class Car {
    protected int rentFee;
    protected String brand;
    protected String model;
    protected String color;
    protected String licensePlateNum;
    protected int capacity;
    protected boolean includeDriver;
    protected int carUniqueCode;

    public int getRentFee() {
        return this.rentFee;
    }

    public int getCapacity() {
        return this.capacity;
    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isIncludeDriver(boolean includeDriver) {
        this.includeDriver = includeDriver;
        return this.includeDriver;
    }

    public void setCarUniqueCode(int carUniqueCode){
        this.carUniqueCode = carUniqueCode;
    }

    public int getCarUniqueCode(){
        return this.carUniqueCode;
    }
}

class SmallCar extends Car {
    SmallCar(){
        super();
        setCapacity(5);
        this.rentFee = 40_000;
    }

    void carCapacity() {
        if (isIncludeDriver(true))
            capacity -= 1;
    }

}

class MediumCar extends Car {
    MediumCar(){
        super();
        setCapacity(8);
        this.rentFee = 80_000;
    }

    void carCapacity() {
        if (isIncludeDriver(true))
            capacity -= 1;
    }
}

class LargeCar extends Car {
    LargeCar(){
        super();
        setCapacity(16);
        this.rentFee = 120_000;
    }

    void carCapacity() {
        if (isIncludeDriver(true)) 
            capacity -= 1;
    }
}
