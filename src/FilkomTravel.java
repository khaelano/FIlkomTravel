import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FilkomTravel {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

class User {
    private static int userCounter;
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
        Order order = new Order(this, car);
        order.

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
    String username;
    String password;
    boolean loggedIn;
    Order[] orderHistory;

    public Member(String name, String identityNum) {
        super(name, identityNum);
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


        return order;
    }
}

class Order {
    private LocalDate invoiceDate;
    User renter;
    Car rentedCar;
    LocalDateTime rentStartDate;
    LocalDateTime rentEndDate;
    private int totalCharges;

    public Order(User renter, Car rentedCar) {
        this.renter = renter;
        this.rentedCar = rentedCar;

        this.invoiceDate = LocalDate.now();
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

    }

    public void printBill() {

    }
}

class Car {

}

class Date {
    int day;
    int month;
    int year;
    int hour;
    int minutes;

    public int calculateDurationInDay() {

    }

    public int calculataDurationInHour() {

    }
}