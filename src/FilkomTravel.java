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
        S = new Scanner(System.in);
        carDB = new ArrayList<>();

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
                    System.out.println("Please select a valid choice");
                    break;
            }
        }
    }

    public static Order takeOrder(User user) {
        System.out.println("------------------------------------------------");
        System.out.println("|                  Order Mode                  |");
        System.out.println("------------------------------------------------");
        
        // Print all available cars .....

        System.out.printf("Please choose your car [0-%d] : ", carDB.size() - 1);
        Car car = carDB.get(S.nextInt());
        S.nextLine();

        System.out.println("--------- Set rent start and end date ----------");
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
        System.out.println();

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
        // Unfinished
        User guest = guestRegistration();
        Order order = takeOrder(guest);

        order.printBill();
    }

    private static void memberMode() {
        System.out.print("Have you registered as a member? [y/n] ");
        String selection = S.nextLine();
        Order order;
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
                break;
        }

        Car car = generateCar();
        order = member.order(car);
    }

    private static Member memberLogin() {
        System.out.print("Enter your username: ");
        String username = S.nextLine();
        System.out.print("Enter your password: ");
        String password = S.nextLine();
        Member member = memberDB.get(username);

        member.login(username, password);
        System.out.println("Login Successful!");

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
        System.out.println();

        member.setCredentials(username, password);
        memberDB.put(username, member);

        return member;
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
        if (!(username.equals(this.username) && password.equals(this.password)))
            return;

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
        return (int) Math.ceil(duration.getSeconds() / 3600);
    }

    public int calculateTotalCharges() {
        int durationInHour = calculateDuration();
        return (int) (Math.ceil(durationInHour/6) * rentedCar.getRentFee() * (1 - this.renter.discount));
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
    SmallCar(){
        super();
        setCapacity(5);
        this.rentFee = 40_000;
    }

    void carCapacity() {
        if (isIncludeDriver(true)) {
            capacity -= 1;
        } else {
            capacity = capacity;
        }
    }

    public void printCar() {
        System.out.println("################################################");
        System.out.println("Brand: " + brand);
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
    MediumCar(){
        super();
        setCapacity(8);
        this.rentFee = 80_000;
    }

    void carCapacity() {
        if (isIncludeDriver(true)) {
            capacity -= 1;
        } else {
            capacity = capacity;
        }
    }

    public void printCar() {
        System.out.println("################################################");
        System.out.println("Brand: " + super.brand);
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
    LargeCar(){
        super();
        setCapacity(16);
        this.rentFee = 120_000;
    }

    void carCapacity() {
        if (isIncludeDriver(true)) {
            capacity -= 1;
        } else {
            capacity = capacity;
        }

    }

    public void printCar() {
        System.out.println("################################################");
        System.out.println("Brand: " + super.brand);
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