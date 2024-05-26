/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

import java.time.LocalDate;
import java.util.*;

import components.car.*;
import components.payment.*;
import components.user.*;
import utils.Converter;

public class FilkomTravelV2 {
  private static HashMap<String, User> userDB = new HashMap<>();
  private static HashMap<String, Vehicle> vehicleDB = new HashMap<>();
  private static HashMap<String, Promotion> promoDB = new HashMap<>();
  private static Scanner sn = new Scanner(System.in);
  private static HashMap<String, Order> cartDB;

  public static void main(String[] args) {
    mainLoop: while (true) {
      String command = sn.next();
      switch (command) {
        case "CREATE": {
          String subcmd = sn.next();
          switch (subcmd) {
            case "MEMBER": {
              // TODO: Implements member creation mechanism
              String[] memberData = sn.nextLine().trim().split("\\|");

              String[] userName = memberData[1].split(" ", 2);
              String userLastName = userName.length >= 2 ? userName[1] : null;

              LocalDate joinDate = Converter.stringToLocalDate(memberData[2]);
              long initialBalance = Long.parseLong(memberData[3]);

              // Validate member ID input
              if (!memberData[0].trim().matches("A[0-9]{3}$")) {
                System.out.println("CREATE MEMBER FAILED: INVALID USER ID");
                continue mainLoop;
              }

              // Creating Member
              if (createMember(
                  memberData[0], // Member ID
                  userName[0], // First name
                  userLastName, // Last name
                  joinDate,
                  initialBalance)) {
                System.out.printf(
                    "CREATE MEMBER SUCCESS: %s %s\n",
                    memberData[0], memberData[1]);
              } else {
                System.out.printf(
                    "CREATE MEMBER FAILED: %s IS EXISTS\n",
                    memberData[0]);
              }
            }
              break;

            case "GUEST": {
              // TODO: Implements guest creation mechanism
              String[] guestData = sn.nextLine().trim().split("\\|");

              long initialBalance = Long.parseLong(guestData[1]);

              // Validate guest ID input
              if (!guestData[0].matches("G[0-9]{3}$")) {
                System.out.println("CREATE GUEST FAILED: INVALID USER ID");
                continue mainLoop;
              }

              // Creating Guest
              if (createGuest(
                  guestData[0], // Guest ID
                  initialBalance)) {
                System.out.printf(
                    "CREATE GUEST SUCCESS: %s %s\n",
                    guestData[0], guestData[1]);
              } else {
                System.out.printf(
                    "CREATE GUEST FAILED: %s IS EXISTS\n",
                    guestData[0]);
              }
            }
              break;

            case "MENU": {
              // TODO: Implements vehicle menu creation mechanism
              String subSubmenu = sn.next();
              boolean vehicleCreationResult = false;
              String[] vehicleData = sn.nextLine().trim().split("\\|");

              // Check the vehcle ID validity
              if (!vehicleData[0].matches("M[0-9]{3}$")) {
                System.out.println("CREATE MENU FAILED: INVALID VEHICLE ID");
                continue mainLoop;
              }

              switch (subSubmenu) {
                case "MOTOR": {
                  // Creating bike
                  vehicleCreationResult = createBike(
                      vehicleData[0], // Vehicle ID
                      vehicleData[1], // Vehicle name
                      vehicleData[2], // License number
                      Long.parseLong(vehicleData[3]) // Rent fee
                  );

                }
                  break;

                case "MOBIL": {
                  // Check if size is valid
                  CarSize cs = null;
                  if (vehicleData[4].equals("L")) {
                    cs = CarSize.LARGE;
                  } else if (vehicleData[4].equals("R")) {
                    cs = CarSize.REGULAR;
                  } else {
                    System.out.printf(
                        "CREATE MENU FAILED: %s IS NOT A VALID CAR SIZE\n",
                        vehicleData[4]);
                  }

                  // Creating car
                  vehicleCreationResult = createCar(
                      vehicleData[0], // Vehicle ID
                      vehicleData[1], // Vehicle name
                      vehicleData[2], // License number
                      Long.parseLong(vehicleData[3]), // Rent fee
                      cs);

                }
                  break;

                default:
                  System.out.println("Invalid vehicle type, try again.");
                  continue mainLoop;
              }

              if (vehicleCreationResult) {
                System.out.printf(
                    "CREATE MENU SUCCESS: %s %s %s\n",
                    vehicleData[0],
                    vehicleData[1],
                    vehicleData[2]);
              } else {
                System.out.printf(
                    "CREATE MENU FAILED: %s IS EXISTS",
                    vehicleData[0]);
              }
            }
              break;

            case "PROMO": {
              // TODO: Implements promo creation mechanism
              String promoType = sn.next();
              sn.nextLine();

              if (!(promoType.equals("DISCOUNT") || promoType.equals("CASHBACK"))) {
                System.out.println("CREATE PROMO FAILED: INVALID PROMO TYPE");
                continue mainLoop;
              }

              String[] promoData = sn.nextLine().trim().split("\\|");

              LocalDate promoStartDate = Converter.stringToLocalDate(promoData[1]);
              LocalDate promoEndDate = Converter.stringToLocalDate(promoData[2]);
              double percentage = Double.parseDouble(promoData[3].replace("%", ""));
              long maxPromoValue = Long.parseLong(promoData[4]);
              long minTranscTreshold = Long.parseLong(promoData[5]);

              boolean promoCreationResult = false;
              promoCreationResult = createPromo(
                  promoType,
                  promoData[0],
                  promoStartDate,
                  promoEndDate,
                  percentage,
                  maxPromoValue,
                  minTranscTreshold);
              if (promoCreationResult) {
                System.out.printf("CREATE PROMO %s SUCCESS: %s\n", promoType, promoData[0]);
              } else {
                System.out.printf("CREATE PROMO %s FAILED: %s IS EXISTS\n", promoType, promoData[0]);
              }
            }
              break;

            default:
              System.out.println("Invalid subcommand, try again.");
              continue mainLoop;
          }
        }
          break;

        case "ADD_TO_CART": {
          // TODO: Implements cart mechanism
        }
          break;

        case "REMOVE_FROM_CART": {
          // TODO: Implements cart mechanism
        }
          break;

        case "APPLY_PROMO": {
          // TODO: Implements promo mechanism
        }
          break;

        case "TOPUP": {
          // TODO: Implements top up mechanism

          String userID = sn.next();
          int nominal = sn.nextInt();
          sn.nextLine();

          User user = userDB.get(userID);
          if (user != null) {
            long balanceBerfore = user.getBalance();
            user.incrBalance(nominal);
            long balanceAfter = user.getBalance();
            System.out.printf("TOPUP SUCCESS: %s %d => %d\n", user.getFirstName(), balanceBerfore, balanceAfter);
          } else {
            System.out.println("TOPUP FAILED: NON EXISTENT CUSTOMER");
            continue mainLoop;
          }
        }
          break;

        case "CHECK_OUT": {
          // TODO: Implements check out mechanism
        }
          break;

        case "PRINT": {
          // TODO: Implements print mechanism
        }
          break;

        default:
          System.out.println("Invalid command, try again.");
          break;
      }
    }
  }

  public static boolean createMember(
      String memberID,
      String firstName,
      String lastName,
      LocalDate joinDate,
      long initialBalance) {
    // TODO: Implements member creation mechanism

    // Check if memberID is available
    if (userDB.containsKey(memberID))
      return false;

    userDB.put(
        memberID,
        new Member(memberID, firstName, lastName, joinDate, initialBalance));

    return true;
  }

  public static boolean createGuest(String guestID, long initialBalance) {
    // TODO: Implements guest creation mechanism

    // Check if guestID is available
    if (userDB.containsKey(guestID))
      return false;

    userDB.put(
        guestID,
        new Guest(guestID, initialBalance));

    return true;
  }

  public static boolean createBike(
      String vehicleID,
      String vehicleName,
      String licenseNumber,
      long rentFee) {
    // TODO: Implements bike creation mechanism

    /// Check if vehicleID is available
    if (vehicleDB.containsKey(vehicleID))
      return false;

    vehicleDB.put(
        vehicleID,
        new Bike(vehicleID, vehicleName, licenseNumber, rentFee));

    return true;
  }

  public static boolean createCar(
      String vehicleID,
      String vehicleName,
      String licenseNumber,
      long rentFee,
      CarSize size) {
    // TODO: Implements bike creation mechanism

    // Check if vehicleID is available
    if (vehicleDB.containsKey(vehicleID))
      return false;

    vehicleDB.put(
        vehicleID,
        new Car(vehicleID, vehicleName, licenseNumber, rentFee, size));

    return true;
  }

  public static boolean createPromo(
      String promoType,
      String promoCode,
      LocalDate promoStartDate,
      LocalDate promoEndDate,
      double percentage,
      long maxPromoValue,
      long minTranscTreshold) {
    // TODO: Implements promo creation mechanism

    // Check if promotion code is available
    if (promoDB.containsKey(promoCode))
      return false;

    if (promoType.equals("DISCOUNT")) {
      promoDB.put(
          promoCode,
          new RegularDiscount(promoCode, promoStartDate, promoEndDate, percentage, maxPromoValue, minTranscTreshold));

    } else if (promoType.equals("CASHBACK")) {
      promoDB.put(
          promoCode,
          new Cashback(promoCode, promoStartDate, promoEndDate, percentage, maxPromoValue, minTranscTreshold));
    } else {
      return false;
    }

    return true;
  }

  public static void addToCart(String userID, String vehicleID, int duration, String rentStartDate) {
    User user = userDB.get(userID);
    Vehicle vehicle = vehicleDB.get(vehicleID);

    if (user == null || vehicle == null) {
      System.out.println("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
      return;
    }

    String cartKey = userID + "-" + vehicleID;
    Order order = cartDB.get(cartKey);

    boolean isUpdated = false;

    if (order != null) {
      order.setRentDuration(order.getRentDuration() + duration);
      isUpdated = true;
    } else {
      order = new Order(vehicle, duration, user);
      order.setRentStartDate(rentStartDate);
      cartDB.put(cartKey, order);
    }

    String message = String.format("ADD_TO_CART SUCCESS: %d %s %s %s %s",
        duration,
        (duration > 1) ? "days" : "day",
        vehicle.getVehicleName(),
        vehicle.getLicenseNumber(),
        isUpdated ? "(UPDATED)" : "(NEW)");

    System.out.println(message);
  }

  public static void removeFromCart(String userID, String vehicleID, int quantity) {
    // Pengecekan eksistensi pengguna dan kendaraan
    User user = userDB.get(userID);
    Vehicle vehicle = vehicleDB.get(vehicleID);

    if (user == null || vehicle == null) {
        System.out.println("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
        return;
    }

    // Mencari order dalam keranjang pengguna
    String cartKey = userID + "-" + vehicleID;
    Order targetOrder = cartDB.get(cartKey);

    if (targetOrder == null) {
        System.out.println("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
        return;
    }

    // Mengurangi kuantitas order
    int newQuantity = targetOrder.getRentDuration() - quantity;
    if (newQuantity > 0) {
        targetOrder.setRentDuration(newQuantity);
        System.out.printf("REMOVE_FROM_CART SUCCESS: %s %d QUANTITY IS DECREMENTED\n", vehicle.getVehicleName(), newQuantity);
    } else {
        cartDB.remove(cartKey);
        System.out.printf("REMOVE_FROM_CART: %s IS REMOVED\n", vehicle.getVehicleName());
    }
}



}