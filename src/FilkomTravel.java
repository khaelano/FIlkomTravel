import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FilkomTravel {
    private static ArrayList<Member> memberDB;
    private static Scanner S;

    public static void main(String[] args) throws Exception {
        memberDB = new ArrayList<>();
        S = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Filkom Travel!!");
            System.out.print("Do you want to rent as member? [y/n] ");
            String selection = S.nextLine();

            switch (selection) {
                case "y":
                    memberMode();
                    break;
            
                case "n":
                    guestMode();
                    break;
                default:
                    System.out.println("Please select a valid choice");
                    break;
            }
        }
    }

    private static Car generateCar() {
        System.out.println("What type of car do you want to rent?");
        System.out.print("[small/medium/large] ");
        String carType = S.nextLine();

        System.out.print("Enter car brand: ");
        String brand = S.nextLine();
        System.out.print("Enter car model: ");
        String model = S.nextLine();
        System.out.print("Enter the car color: ");
        String color = S.nextLine();
        System.out.print("Enter car license plate: ");
        String licensePlate = S.nextLine();
        
        Car car;
        switch (carType) {
            case "small":
                car = new SmallCar();
                car.brand = brand;
                car.model = model;
                car.licensePlateNum = licensePlate;
                car.color = color;
                break;
        
            case "medium":
                car = new MediumCar();
                car.brand = brand;
                car.model = model;
                car.licensePlateNum = licensePlate;
                car.color = color;
                break;
        
            case "large":
                car = new LargeCar();
                car.brand = brand;
                car.model = model;
                car.licensePlateNum = licensePlate;
                car.color = color;
                break;
        
            default:
                System.out.println("Please selec a valid input");
                car = null;
                break;
        }

        return car;
    }
    
    private static void guestMode() {
        User guest = guestRegistration();
        Car car = generateCar();

        Order order  = guest.order(car);
        
        System.out.println("Set rent start date and time in this format: ");
        System.out.print("dd/MM/yyyy HH:mm ");
        order.setRentStartDate(S.nextLine());

        System.out.println("Set rent start date and time in this format: ");
        System.out.print("dd/MM/yyyy HH:mm ");
        order.setRentEndtDate(S.nextLine());

        order.printBill();
    }

    private static void memberMode() {
        System.out.print("Have you registered as a member? [y/n] ");
        String selection = S.nextLine();
    }

    private static void memberLogin() {
        System.out.print("Enter your username: ");
        String username = S.nextLine();

        System.out.print("Enter your password: ");
        String password = S.nextLine();
        
        // Member member = memberDB.;
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
        member.login(null, null);
        System.out.print("Enter your username: ");
        String username = S.nextLine();

        System.out.print("Enter your password: ");
        String password = S.nextLine();

        member.setCredentials(username, password);

        return member;
    }
}

class User {
    protected static int userCounter;
    protected String UID;
    protected String name;
    protected String identityNum;
    String phoneNum;
    String address;

    public User(String name, String identityNum) {
        this.name = name;
        this.identityNum = identityNum;
        this.UID = "101" + Integer.toString(userCounter);
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

        this.orderHistory = new ArrayList<>();
    }

    public void setCredentials(String username, String password) {
        if (!loggedIn) return;

        this.username = username;
        this.password = password;
    } 

    public String getUsername() {
        return this.username;
    }

    public void login(String username, String password) {
        if (!(username.equals(this.username) && password.equals(this.password))) return;

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

    public int calculateDuration() {
        Duration duration = Duration.between(rentStartDate, rentEndDate);
        return (int) Math.ceil(duration.getSeconds()/3600);
    }

    public int calculateTotalCharges() {
        int durationInHour = calculateDuration();
        return durationInHour * rentedCar.getRentFee();
    }

    public void printBill() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Car car = this.rentedCar;

        System.out.println("################################################");
        System.out.println("########          Payment Bill          ########");
        System.out.println("################################################");
        System.out.println("Invoice date: " + invoiceDate.format(formatter));
        System.out.println("Renter Name: " + this.renter.name);
        System.out.println("------------------------------------------------");
        System.out.println("--------------- Rented Car Details -------------");
        System.out.printf("%-21s: %s\n", "Brand Name", car.brand);
        System.out.printf("%-21s: %s\n", "Model", car.model);
        System.out.printf("%-21s: %s\n", "Color", car.color);
        System.out.printf("%-21s: %s person\n", "Capacity", car.capacity);
        System.out.printf("%-21s: %s\n", "License Plate Number", car.licensePlateNum);
        System.out.printf("%-21s: %s /hr\n", "Rental Fee", car.rentFee);
        System.out.println("------------------------------------------------");
        System.out.println("------------------ Rent Details ----------------");
        System.out.printf("%-20s: %s\n", "Start Date", this.rentStartDate.format(formatter));
        System.out.printf("%-20s: %s\n", "End Date", this.rentEndDate.format(formatter));
        System.out.printf("%-20s: %s hour\n", "Duration", Integer.toString(calculateDuration()));
        System.out.printf("%-20s: %s\n", "Total Charges", Integer.toString(calculateTotalCharges()));
        System.out.println("################################################");
        System.out.println("######  Thank you for using FilkomTravel!  #####");
        System.out.println("######    We hope to see you next time!    #####");
        System.out.println("################################################");
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

    public int getRentFee() {
        return this.rentFee;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isIncludeDriver(boolean includeDriver) {
        this.includeDriver = includeDriver;
        return this.includeDriver;
    }
}

class SmallCar extends Car {
    final private double RENTAL_PRICE_PER_6_HOURS = 40_000;
    SmallCar(){
        super();
        setCapacity(5);
    }
    
    void carCapacity(){
        if (isIncludeDriver(true)) {
            capacity -= 1;
        } else {
            capacity = capacity;
        }
    }

    //bikin method if (member) dapet diskon 

    public void printCar(){
        System.out.println("################################################");
        System.out.println("Brand: "+ brand);
        System.out.println("Model: " + model);
        System.out.println("License Plate: " + licensePlateNum);
        System.out.println("Capacity: " + getCapacity() + " persons");
        System.out.println("------------------------------------------------");
        System.out.println("Rent Fee: Rp" + getRentFee());
        if (isIncludeDriver(true)) {
            System.out.println("Driver: Included");
        } else {
            System.out.println("Driver: Not included");
        }
        System.out.println("################################################");
    }
}

class MediumCar extends Car {
    final private double RENTAL_PRICE_PER_6_HOURS = 80_000;
    MediumCar(){
        super();
        setCapacity(8);
    }
    
    void carCapacity(){
        if (isIncludeDriver(true)) {
            capacity -= 1;
        } else {
            capacity = capacity;
        }
    }
    public void printCar(){
        System.out.println("################################################");
        System.out.println("Brand: "+ super.brand);
        System.out.println("Model: " + super.model);
        System.out.println("License Plate: " + super.licensePlateNum);
        System.out.println("Capacity: " + super.getCapacity() + " persons");
        System.out.println("------------------------------------------------");
        System.out.println("Rent Fee: Rp" + getRentFee());
        if (isIncludeDriver(true)) {
            System.out.println("Driver: Included");
        } else {
            System.out.println("Driver: Not included");
        }
        System.out.println("################################################");
    }
}

class LargeCar extends Car {
    final private double RENTAL_PRICE_PER_6_HOURS = 120_000;
    LargeCar(){
        super();
        setCapacity(16);
    }
    
    void carCapacity(){
        if (isIncludeDriver(true)) {
            capacity -= 1;
        } else {
            capacity = capacity;
        }
    }
    public void printCar(){
        System.out.println("################################################");
        System.out.println("Brand: "+ super.brand);
        System.out.println("Model: " + super.model);
        System.out.println("License Plate: " + super.licensePlateNum);
        System.out.println("Capacity: " + super.getCapacity() + " persons");
        System.out.println("------------------------------------------------");
        System.out.println("Rent Fee: Rp" + getRentFee());
        if (isIncludeDriver(true)) {
            System.out.println("Driver: Included");
        } else {
            System.out.println("Driver: Not included");
        }
        System.out.println("################################################");
    }
}