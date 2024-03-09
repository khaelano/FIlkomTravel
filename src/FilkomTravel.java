import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FilkomTravel {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
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

    public void setRentStartDate(int year, int month, int day, int hour, int minute) {
        // Unfinished
        this.rentEndDate = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minute));
    }

    public void setRentEndtDate(int year, int month, int day, int hour, int minute) {
        // Unfinished
        this.rentEndDate = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minute));
    }

    public int getTotalCharges() {
        Duration duration = Duration.between(rentStartDate, rentEndDate);
        int durationInHour = (int) Math.ceil(duration.getSeconds()/3600);

        return durationInHour * rentedCar.getRentFee();
    }

    public void printBill() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("################################################");
        System.out.println("########          Payment Bill          ########");
        System.out.println("################################################");
        System.out.println("Invoice date: " + invoiceDate.format(formatter));
        System.out.println("Renter Name: " + this.renter.name);
        System.out.println("------------------------------------------------");
        System.out.println("Rented Car Details:");
    }
}

class Car {
    private int rentFee;

    public int getRentFee() {

    }
}