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
  private static ArrayList<Car> carDB = new ArrayList<>();
  private static Scanner sn = new Scanner(System.in);

  public static void main(String[] args) {
    mainLoop: while (true) {
      String command = sn.next();
      switch (command) {
        case "ADD": {
          String subcmd = sn.next();
          switch (subcmd) {
            case "MEMBER": {
              // TODO: Implements member creation mechanism
              String[] memberData = sn.next().split("|");
              sn.nextLine();

              String[] userName = memberData[1].split(" ", 1);
              LocalDate joinDate = Converter.stringToLocalDate(memberData[2]);
              long initialBalance = Long.parseLong(memberData[3]);

              // Validate member ID input
              if (!memberData[0].matches("A[0-9]{3}\b"))
                System.out.println("CREATE MEMBER FAILED: INVALID USER ID");

              // Creating Member
              if (createMember(
                memberData[0], // Member ID
                userName[0], // First name
                userName[1], // Last name
                joinDate, 
                initialBalance
              )) {
                System.out.printf(
                  "CREATE MEMBER SUCCESS: %s %s\n",
                  memberData[0], memberData[1]
                );
              } else {
                System.out.printf(
                  "CREATE MEMBER FAILED: %s IS EXISTS\n",
                  memberData[0]
                );
              }
            }
              break;

            case "GUEST": {
              // TODO: Implements guest creation mechanism
              String[] guestData = sn.next().split("|");
              sn.nextLine();

              long initialBalance = Long.parseLong(guestData[3]);

              // Validate guest ID input
              if (!guestData[0].matches("B[0-9]{3}\b"))
                System.out.println("CREATE GUEST FAILED: INVALID USER ID");

              // Creating Guest
              if (createGuest(
                guestData[0], // Guest ID
                initialBalance
              )) {
                System.out.printf(
                  "CREATE GUEST SUCCESS: %s %s\n",
                  guestData[0], guestData[1]
                );
              } else {
                System.out.printf(
                  "CREATE GUEST FAILED: %s IS EXISTS\n",
                  guestData[0]
                );
              }
            }
              break;

            case "MENU":
              {
                // TODO: Implements vehicle menu creation mechanism
              }
              break;

            case "PROMO":
              {
                // TODO: Implements promo creation mechanism
              }
              break;

            default:
              System.out.println("Invalid subcommand, try again.");
              break;
          }
        }
          break;

        case "ADD_TO_CART":
          {
            // TODO: Implements cart mechanism
          }
          break;

        case "REMOVE_FROM_CART":
          {
            // TODO: Implements cart mechanism
          }
          break;

        case "APPLY_PROMO":
          {
            // TODO: Implements promo mechanism
          }
          break;

        case "TOP_UP":
          {
            // TODO: Implements top up mechanism
          }
          break;

        case "CHECK_OUT":
          {
            // TODO: Implements check out mechanism
          }
          break;

        case "PRINT":
          {
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
    long initialBalance
  ) {
    // TODO: Implements member creation mechanism

    // Check if memberID is available
    if (userDB.containsKey(memberID)) return false;

    userDB.put(
      memberID, 
      new Member(memberID, firstName, lastName, joinDate, initialBalance)
    );

    return true;
  }

  public static boolean createGuest(String guestID, long initialBalance) {
    // TODO: Implements guest creation mechanism

    // Check if guestID is available
    if (userDB.containsKey(guestID)) return false;

    userDB.put(
      guestID, 
      new Guest(guestID, initialBalance)
    );

    return true;
  }
}
