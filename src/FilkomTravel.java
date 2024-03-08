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

    public Order order(car: Car) {

    }

    public String getUID() {
        return this.UID;
    }

    public String getName() {
        return this.name;
    }

    public String getIdentityNumber() {
        return this.identityNum;
    }
}

class Member extends User {
    private static int userCounter;
    String username;
    String password;
    boolean loggedIn;
    Order[] orderHistory;

    public Member(String name, String identityNum) {
        super(name, identityNum);
        userCounter++;
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
}

class Order {

}

class Car {

}