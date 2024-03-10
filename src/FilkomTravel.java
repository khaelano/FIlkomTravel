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
        Car car = generateCar();

        Order order = guest.order(car);

        System.out.println("Set rent start date and time in this format: ");
        System.out.print("dd/MM/yyyy HH:mm ");
        order.setRentStartDate(S.nextLine());
        System.out.println();

        System.out.println("Set rent end date and time in this format: ");
        System.out.print("dd/MM/yyyy HH:mm ");
        order.setRentEndtDate(S.nextLine());

        System.out.println();

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
        member.login(null, null);
        System.out.print("Enter your username: ");
        String username = S.nextLine();

        System.out.print("Enter your password: ");
        String password = S.nextLine();
        System.out.println();

        member.setCredentials(username, password);

        return member;
    }

    private static void initSmallCar(){
        SmallCar sc1 = new SmallCar();
        sc1.brand = "Honda";
        sc1.model = "Brio";
        sc1.licensePlateNum = "A 54 HEL";
        sc1.isIncludeDriver(false);
        sc1.carCapacity();
        sc1.getRentFee();
        sc1.setCarUniqueCode(433);
        sc1.printCar();

        SmallCar sc2 = new SmallCar();
        sc2.brand = "Daihatsu";
        sc2.model = "Ayla";
        sc2.licensePlateNum = "M 43 NG";
        sc2.isIncludeDriver(true);
        sc2.carCapacity();
        sc2.getRentFee();
        sc2.setCarUniqueCode(333);
        sc2.printCar();
        
    }

    private static void initMedCar(){
        MediumCar mc1 = new MediumCar();
        mc1.brand = "Toyota";
        mc1.model = "Avanza";
        mc1.licensePlateNum = "N 941 AM";
        mc1.isIncludeDriver(true);
        mc1.carCapacity();
        mc1.getRentFee();
        mc1.setCarUniqueCode(999);
        mc1.printCar();
        
        MediumCar mc2 = new MediumCar();
        mc2.brand = "Honda";
        mc2.model = "BRV";
        mc2.licensePlateNum = "N 4 KAM";
        mc2.isIncludeDriver(false);
        mc2.carCapacity();
        mc2.getRentFee();
        mc2.setCarUniqueCode(215);
        mc2.printCar();
        
        MediumCar mc3 = new MediumCar();
        mc3.brand = "Daihatsu";
        mc3.model = "Sigra";
        mc3.licensePlateNum = "N 45 GOR";
        mc3.isIncludeDriver(true);
        mc3.carCapacity();
        mc3.getRentFee();
        mc3.setCarUniqueCode(468);
        mc3.printCar();
    }

    private static void initLargeCar(){
        LargeCar lc1 = new LargeCar();
        lc1.brand = "Toyota";
        lc1.model = "HiAce";
        lc1.licensePlateNum = "B 490 NG";
        lc1.isIncludeDriver(true);
        lc1.carCapacity();
        lc1.getRentFee();
        lc1.setCarUniqueCode(222);
        lc1.printCar();
        
        LargeCar lc2 = new LargeCar();
        lc2.brand = "Isuzu";
        lc2.model = "Elf Microbus";
        lc2.licensePlateNum = "B 444 AA";
        lc2.isIncludeDriver(false);
        lc2.carCapacity();
        lc2.getRentFee();
        lc2.setCarUniqueCode(122);
        lc2.printCar();

        Largecar 1c3 = new LargeCar();
        1c3.brand = "Mercy";
        1c3.model = "Sprinter";
        1c3.licensePlateNum = "B 111 GG";
        lc3.isIncludeDriver(true);
        lc3.carCapacity();
        lc3.getRentFee();
        lc3.setCarUniqueCode(111);
        lc3.printCar();

        
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
        System.out.println();
        System.out.println("Rent Fee: Rp" + getRentFee());
        if (isIncludeDriver(true)) {
            System.out.println("Driver: Included");
        } else {
            System.out.println("Driver: Not included");
        }
        System.out.println("------------------------------------------------");
        System.out.println("Car Unique Code: "+getCarUniqueCode());
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

        System.out.println("Brand: "+ brand);
        System.out.println("Model: " + model);
        System.out.println("License Plate: " + licensePlateNum);
        System.out.println("Capacity: " + getCapacity() + " persons");
        System.out.println();
        System.out.println("Rent Fee: Rp" + getRentFee());
        if (isIncludeDriver(true)) {
            System.out.println("Driver: Included");
        } else {
            System.out.println("Driver: Not included");
        }
        System.out.println("------------------------------------------------");
        System.out.println("Car Unique Code: "+getCarUniqueCode());
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


    public void printCar(){
        System.out.println("################################################");
        System.out.println("Brand: "+ brand);
        System.out.println("Model: " + model);
        System.out.println("License Plate: " + licensePlateNum);
        System.out.println("Capacity: " + getCapacity() + " persons");
        System.out.println();
        System.out.println("Rent Fee: Rp" + getRentFee());
        if (isIncludeDriver(true)) {
            System.out.println("Driver: Included");
        } else {
            System.out.println("Driver: Not included");
        }
        System.out.println("------------------------------------------------");
        System.out.println("Car Unique Code: "+getCarUniqueCode());
        System.out.println("################################################");
    }
}
