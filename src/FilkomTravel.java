/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

import java.time.LocalDate;
import java.util.*;

import components.payment.*;
import components.user.*;
import components.vehicle.*;
import utils.Converter;

public class FilkomTravel {
  private static HashMap<String, User> userDB = new HashMap<>();
  private static HashMap<String, Vehicle> vehicleDB = new HashMap<>();
  private static HashMap<String, Promotion> promoDB = new HashMap<>();
  private static Scanner sn = new Scanner(System.in);

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
                  try {
                    // Creating bike
                    vehicleCreationResult = createBike(vehicleData[0], vehicleData[1], vehicleData[2],
                        Long.parseLong(vehicleData[3]));
                  } catch (Error e) {
                    System.out.println("CREATE MENU FAILED: " + e.getMessage());
                    continue mainLoop;
                  }
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

                  try {
                    // Creating car
                    vehicleCreationResult = createCar(vehicleData[0], vehicleData[1], vehicleData[2],
                        Long.parseLong(vehicleData[3]),
                        cs);
                  } catch (Error e) {
                    System.out.println("CREATE MENU FAILED: " + e.getMessage());
                    continue mainLoop;
                  }
                }
                  break;

                default:
                  System.out.println("Invalid vehicle type, try again.");
                  continue mainLoop;
              }

              System.out.printf(
                  "CREATE MENU SUCCESS: %s %s %s\n",
                  vehicleData[0],
                  vehicleData[1],
                  vehicleData[2]);

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

              try {
                createPromo(
                    promoType,
                    promoData[0],
                    promoStartDate,
                    promoEndDate,
                    percentage,
                    maxPromoValue,
                    minTranscTreshold);
                System.out.printf("CREATE PROMO %s SUCCESS: %s\n", promoType, promoData[0]);
              } catch (Error e) {
                System.out.println("CREATE PROMO %s FAILED: ".formatted(promoType) + e.getMessage());
                continue mainLoop;
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

          String userID = sn.next();
          String vehicle = sn.next();
          int qty = sn.nextInt();
          LocalDate rentStartDate = Converter.stringToLocalDate(sn.next());
          sn.nextLine();

          User renter = userDB.get(userID);
          Vehicle rentedVehicle = vehicleDB.get(vehicle);

          // Check if the user and vehicle are available
          if (renter == null || rentedVehicle == null) {
            System.out.println("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
            continue mainLoop;
          }

          // Check if the order already has the vehicle
          int result = addToCart(userID, vehicle, qty, rentStartDate);
          System.out.printf(
              "ADD_TO_CART SUCCESS: %d %s %s %s %s\n",
              result,
              result > 1 ? "days" : "day",
              rentedVehicle.getVehicleName(),
              rentedVehicle.getLicenseNumber(),
              result > qty ? "(UPDATE)" : "(NEW)");
        }
          break;

        case "REMOVE_FROM_CART": {
          // TODO: Implements cart mechanism
          String userID = sn.next();
          String vehicle = sn.next();
          int qty = sn.nextInt();
          sn.nextLine();

          User renter = userDB.get(userID);
          Vehicle rentedVehicle = vehicleDB.get(vehicle);

          // Check if the user and vehicle are available
          if (renter == null || rentedVehicle == null) {
            System.out.println("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
            continue mainLoop;
          }

          int result = removeFromCart(userID, vehicle, qty);
          System.out.printf(
              "REMOVE_FROM_CART SUCCESS: %s %s %s\n",
              rentedVehicle.getVehicleName(),
              rentedVehicle.getLicenseNumber(),
              (result > 0) ? "QUANTITY IS DECREMENTED" : "IS REMOVED");
        }
          break;

        case "APPLY_PROMO": {
          // TODO: Implements promo mechanism
          String userID = sn.next();
          String promoCode = sn.next();
          sn.nextLine();

          int result = applyPromo(userID, promoCode);
          if (result == 0) {
            System.out.printf("APPLY_PROMO SUCCESS: %s\n", promoCode);
          } else {
            System.out.printf(
                "APPLY_PROMO FAILED: %s %s\n",
                promoCode,
                result == 1 ? "IS EXPIRED" : "");
          }
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
          String userID = sn.next();
          sn.nextLine();

          try {
            checkOut(userID);
            System.out.println("CHECK_OUT SUCCESS: " + userID + " " + userDB.get(userID).getFullName());
          } catch (Error e) {
            System.out.println("CHECK_OUT FAILED: " + e.getMessage());
            continue mainLoop;
          }
        }
          break;

        case "PRINT": {
          // TODO: Implements print mechanism
          String userId = sn.next();
          sn.nextLine();

          try {
            printDetails(userId);
          } catch (Error e) {
            System.out.println("PRINT FAILED: " + e.getMessage());
            continue mainLoop;
          }
        }
          break;

        case "PRINT_HISTORY": {
          // TODO: Implements print mechanism
          String userId = sn.next();
          sn.nextLine();

          try {
            printHistory(userId);
          } catch (Error e) {
            System.out.println("PRINT FAILED: " + e.getMessage());
            continue mainLoop;
          }
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

    // Check if vehicleID is available
    if (vehicleDB.containsKey(vehicleID))
      throw new Error("%s IS EXISTS".formatted(licenseNumber));

    for (String key : vehicleDB.keySet()) {
      Vehicle vehicle = vehicleDB.get(key);
      if (vehicle.getLicenseNumber().equals(licenseNumber))
        throw new Error("%s IS EXISTS".formatted(licenseNumber));
    }

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
      throw new Error("%s IS EXISTS".formatted(licenseNumber));

    for (String key : vehicleDB.keySet()) {
      Vehicle vehicle = vehicleDB.get(key);
      if (vehicle.getLicenseNumber().equals(licenseNumber))
        throw new Error("%s IS EXISTS".formatted(licenseNumber));
    }

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
      throw new Error("%s IS EXISTS".formatted(promoCode));

    if (promoType.equals("DISCOUNT")) {
      promoDB.put(
          promoCode,
          new RegularDiscount(promoCode, promoStartDate, promoEndDate, percentage, maxPromoValue, minTranscTreshold));

    } else if (promoType.equals("CASHBACK")) {
      promoDB.put(
          promoCode,
          new Cashback(promoCode, promoStartDate, promoEndDate, percentage, maxPromoValue, minTranscTreshold));
    } else {
      throw new Error("INVALID PROMO TYPE");
    }

    return true;
  }

  public static int addToCart(String userID, String vehicleID, int duration, LocalDate rentStartDate) {
    // Check user and vehicle availabilty
    User user = userDB.get(userID);
    Vehicle vehicle = vehicleDB.get(vehicleID);

    // Check if the user has an active order
    if (user.getActiveOrder() == null) {
      user.makeOrder();
    }

    return user.addToCart(vehicle, rentStartDate, duration);
  }

  public static int removeFromCart(String userID, String vehicleID, int quantity) {
    // Check user and vehicle availabilty
    User user = userDB.get(userID);
    Vehicle vehicle = vehicleDB.get(vehicleID);

    // Check if the user has an active order
    if (user.getActiveOrder() == null) {
      return -1;
    }

    return user.removeFromCart(vehicle, quantity);
  }

  public static int applyPromo(String userID, String promoCode) {
    // TODO: Implements apply promo
    // Check user and promo availablilty
    Member user = (Member) userDB.get(userID);
    Promotion promo = promoDB.get(promoCode);
    if (user == null || user.getActiveOrder() == null)
      return -1; // Return -1 if not eligible

    // Check promo expiracy
    if (!(promo != null && LocalDate.now().isAfter(promo.getPromoStartDate())
        && LocalDate.now().isBefore(promo.getPromoEndDate())))
      return 1; // Return 1 if expired

    user.getActiveOrder().applyPromo(promo);
    return 0; // Return 0 if applied
  }

  public static boolean checkOut(String userID) {
    // TODO: Implements check out mechanism

    // Check if user is in the database
    User renter = userDB.get(userID);
    if (renter == null)
      throw new Error("NON EXISTENT CUSTOMER");

    // Check if the user has an active order
    if (renter.getActiveOrder() == null)
      throw new Error("NO ACTIVE ORDER ON CUSTOMER");

    if (renter.confirmPayment()) {
      return true;
    } else {
      throw new Error("%s %s INSUFFICIENT BALANCE".formatted(userID, renter.getFullName()));
    }
  }

  public static void printDetails(String userID) {
    // TODO: Implements print mechanism
    User renter = userDB.get(userID);
    if (renter == null)
      throw new Error("NON EXISTENT CUSTOMER");

    renter.printBill();
  }

  public static void printHistory(String userID) {
    // TODO: Implements print history mechanism
    Member renter = (Member) userDB.get(userID);
    if (renter == null)
      throw new Error("NON EXISTENT CUSTOMER");

    renter.printHistory();
  }
}