/*
 Sanyerlis Camacaro - Sanyerliscamacaro@uat.edu - CSC263
Assignment 5.3: Final Project

Requirements:
1. The application should not be something just to demonstrate the requirements, it should "do" something, be innovative.
2. Be clever, not complex.
3. This application is required to have a good UX.
4. This application is required to be over-commented.
5. Create a Java application or game of your choice. It must meet each of the requirements of the items listed below:
-Opening screen with a description of the application and instructions.
-Menu for the user to choose options.
-At least 4 classes in total.
-Inheritance (minimum 2 derived classes).
-Polymorphism (Overloading and overriding).
-Encapsulation.
-Abstraction.
-Multi-Threading.
-Recursion.
-Exception handling.

About Code:
The provided Java code represents an interactive console-based adventure game titled "Mission: Starbase 13."
The game begins with an opening screen introducing the user to their role as a top-tier agent on a critical mission
at Starbase 13, a space station dedicated to scientific research within SpaceX Spaceship.

The user interacts with the game through a menu system offering options such as exploring the space station,
viewing a mission log, performing background processes, checking their health, and exiting the mission.

The core of the game involves the user, represented as an agent, exploring Starbase 13, encountering various
adventures with potential point gains or losses, and making decisions that impact their success. The code
incorporates object-oriented principles, including abstraction through abstract classes, inheritance with
derived classes, encapsulation with private fields and methods, and polymorphism through method overriding.

The game also features recursion in the exploration process, simulated multi-threading in a background process,
and exception handling. While the code does not currently include file input/output processing, iterators,
or generic programming, it provides a framework for an engaging and interactive text-based adventure experience.
 */
// Importing the ArrayList class from the java.util package
import java.util.ArrayList;
// Importing the Random class from the java.util package
import java.util.Random;
// Importing the Scanner class from the java.util package
import java.util.Scanner;

// Abstract class representing a space station
abstract class SpaceStation {
    protected String name;

    // Constructor for SpaceStation
    public SpaceStation(String name) {
        this.name = name;
    }

    // Abstract method to describe the space station
    public abstract void describe();

    // Getter method for the name of the space station
    public String getName() {
        return name;
    }
}

// Concrete class representing a specific space station (Starbase13)
class Starbase13 extends SpaceStation {
    // Constructor for Starbase13
    public Starbase13(String name) {
        super(name);
    }

    // Implementation of the describe method for Starbase13
    @Override
    public void describe() {
        // Print introductory information about Starbase13
        System.out.println("\n" + name + " is the new building for scientific research inside SpaceX Spaceship.\n");
        System.out.println("The mission of " + name + " is to find extraterrestrial life while orbiting and watching over planet EARTH.\n");
        System.out.println("We believe that Extraterrestrials can guide us with advanced technology to help us save EARTH from global warming.\n");
        System.out.println("This may seem like a scary mission because of the risks if we encounter dangerous Extraterrestrials.\n");
        System.out.println("Only the best agents have access to our mission, and you are one of our BEST agents.\n");
        System.out.println("Get ready, agent! The fate of humanity rests in your hands...\n");
    }

    // Method to simulate exploration of the space station
    public void explore(UserInteraction userInteraction) {
        exploreRecursively(userInteraction, 3); // Limiting to 3 adventures for recursion
    }

    // Recursive method for exploration
    private void exploreRecursively(UserInteraction userInteraction, int adventuresLeft) {
        if (adventuresLeft <= 0) {
            System.out.println("You have completed your exploration. Returning to the main menu.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("You are now exploring " + name + "...");
        System.out.println("Please choose an adventure:");

        // Display available adventures and let the user choose
        int adventureChoice = userInteraction.chooseAdventure();

        // Gain or lose points based on the selected adventure
        int pointsChange = 0;
        switch (adventureChoice) {
            case 1:
                System.out.println("You find a hidden room with advanced technology. Points gained: 10");
                pointsChange = 10;
                break;
            case 2:
                System.out.println("You encounter a friendly alien who shares knowledge. Points gained: 5");
                pointsChange = 5;
                break;
            case 3:
                System.out.println("You attempt to disarm a security system...");
                // Simulate the security system challenge
                if (new Random().nextBoolean()) {
                    System.out.println("Security system successfully disarmed. Points gained: 15");
                    pointsChange = 15;
                } else {
                    System.out.println("Security system cannot be disarmed. Points lost: 10");
                    pointsChange = -10;
                }
                break;
            case 4:
                System.out.println("You discover a medical kit. Points gained: 8");
                pointsChange = 8;
                break;
            case 5:
                System.out.println("You navigate a challenging area...");
                // Simulate the challenging area
                if (new Random().nextBoolean()) {
                    System.out.println("You successfully navigate the challenging area. Points gained: 10");
                    pointsChange = 10;
                } else {
                    System.out.println("You struggle in the challenging area. Points lost: 15");
                    pointsChange = -15;
                }
                break;
            default:
                System.out.println("Invalid choice. No adventure selected.");
                return;
        }

        // Update user's health by adding or subtracting points
        userInteraction.updateHealth(pointsChange);
        // Display points gained or lost
        userInteraction.displayPoints(pointsChange);
        System.out.println("Your current health: " + userInteraction.getHealth());

        // Recursively call the exploration method with reduced adventuresLeft
        exploreRecursively(userInteraction, adventuresLeft - 1);
    }
}

// Class for user interaction and managing user-related information
class UserInteraction {
    private Scanner scanner;
    private int health;

    // Constructor for UserInteraction
    public UserInteraction() {
        this.scanner = new Scanner(System.in);
        this.health = 100; // Starting health
    }

    // Method to get the user's name
    public String getUserName() {
        System.out.println("\nI am MACHINA X22, a Programmed Algorithmic Computer, What is your name? ");
        return scanner.nextLine();
    }

    // Method to confirm the user's identity
    public void confirmIdentity() {
        System.out.println("\nIdentity confirmed. Thank You.");
    }

    // Method to welcome the user
    public void welcomeUser(String userName) {
        System.out.println("\nHello, " + userName + ", Welcome to " + Starbase13.class.getName() + ". We've been expecting you.");
    }

    // Method to display the current year
    public void displayCurrentYear() {
        System.out.println("\nCurrent Year is: 2491.");
    }

    // Method to display the main menu and get user's choice
    public int displayMenu() {
        System.out.println("\n===== Adventure Game Menu =====");
        System.out.println("1. Explore Starbase 13");
        System.out.println("2. View Mission Log");
        System.out.println("3. Perform Background Process");
        System.out.println("4. Check Your Health");
        System.out.println("5. Exit Mission");
        System.out.print("Enter your choice (1-5): ");
        return scanner.nextInt();
    }

    // Method to gain points to the user's health
    public void gainHealth(int points) {
        health += points;
        if (health > 100) {
            health = 100; // Ensure health doesn't exceed 100
        }
    }

    // Method to lose points from the user's health
    public void loseHealth(int points) {
        health -= points;
        if (health < 0) {
            health = 0; // Ensure health doesn't go below 0
        }
    }

    // Method to display the user's current health
    public void displayHealth() {
        System.out.println("\nYour current health: " + health);
    }

    // Getter method for the user's health
    public int getHealth() {
        return health;
    }

    // Method to display the points gained or lost
    public void displayPoints(int points) {
        if (points > 0) {
            System.out.println("Points gained: " + points);
        } else if (points < 0) {
            System.out.println("Points lost: " + (-points));
        } else {
            System.out.println("No points gained or lost.");
        }
    }

    // Method to display available adventures and let the user choose
    public int chooseAdventure() {
        System.out.println("1. Explore hidden room with advanced technology");
        System.out.println("2. Encounter a friendly alien");
        System.out.println("3. Disarm a security system");
        System.out.println("4. Discover a medical kit");
        System.out.println("5. Navigate a challenging area");
        System.out.print("Enter the number of the adventure you want to explore: ");
        return scanner.nextInt();
    }

    // Method to update health by adding or subtracting points
    public void updateHealth(int points) {
        if (points >= 0) {
            gainHealth(points);
        } else {
            loseHealth(-points);
        }
    }
}

// Main class for the program
public class Main {
    public static void main(String[] args) {
        System.out.println("\n\n\t *** Mission: Starbase 13 ***\n\n");
        System.out.println("\nIn this interactive Java console game, you are a top-tier agent embarking on a critical mission");
        System.out.println("\nat Starbase 13, the cutting-edge space station dedicated to scientific research within SpaceX Spaceship.");
        System.out.println("\nYour mission is to explore the space station, navigate challenges, and make decisions that impact");
        System.out.println("\nyour success in finding extraterrestrial life, all while safeguarding planet EARTH from global warming.");
        System.out.println("\nBe prepared for thrilling adventures, encounters with advanced technology, and the possibility of");
        System.out.println("\ninteracting with extraterrestrial entities. Your choices will determine your success and the future our home planet.");

        // Create an instance of UserInteraction
        UserInteraction userInteraction = new UserInteraction();
        // Get the user's name
        String userName = userInteraction.getUserName();
        // Confirm the user's identity
        userInteraction.confirmIdentity();
        // Welcome the user
        userInteraction.welcomeUser(userName);
        // Display the current year
        userInteraction.displayCurrentYear();

        // Create an instance of Starbase13
        SpaceStation starbase13 = new Starbase13("Starbase 13");
        // Describe Starbase13
        starbase13.describe();

        // List to store mission log entries
        ArrayList<String> missionLog = new ArrayList<>();
        // Flag to control program termination
        boolean exit = false;

        // Main program loop
        while (!exit) {
            // Display the main menu and get the user's choice
            int choice = userInteraction.displayMenu();

            // Switch statement to handle user's choice
            switch (choice) {
                case 1:
                    // Explore Starbase 13
                    ((Starbase13) starbase13).explore(userInteraction);
                    break;

                case 2:
                    // View mission log
                    viewMissionLog(missionLog);
                    break;

                case 3:
                    // Perform background process
                    performBackgroundProcess();
                    break;

                case 4:
                    // Check user's health
                    userInteraction.displayHealth();
                    break;

                case 5:
                    // Exit mission
                    System.out.println("\nExiting mission. Goodbye, " + userName + "!");
                    exit = true;
                    break;

                default:
                    // Handle invalid choices
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    // Method to view the mission log
    private static void viewMissionLog(ArrayList<String> missionLog) {
        Scanner scanner = new Scanner(System.in);

        // Display mission log entries
        System.out.println("\n===== Mission Log =====");
        if (missionLog.isEmpty()) {
            System.out.println("No notes in the Mission Log.");
        } else {
            int entryNumber = 1;
            for (String logEntry : missionLog) {
                System.out.println(entryNumber + ". " + logEntry);
                entryNumber++;
            }
        }

        // Display options for mission log
        System.out.println("\n===== Mission Log Options =====");
        System.out.println("1. Add Note to Mission Log");
        System.out.println("2. Delete Note from Mission Log");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter your choice (1-3): ");
        int logOption = scanner.nextInt();

        // Switch statement to handle mission log options
        switch (logOption) {
            case 1:
                // Add note to mission log
                addNoteToMissionLog(scanner, missionLog);
                break;
            case 2:
                // Delete note from mission log
                deleteNoteFromMissionLog(scanner, missionLog);
                break;
            case 3:
                // Do nothing, go back to the main menu
                break;
            default:
                // Handle invalid choices
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
        }
    }

    // Method to add a note to the mission log
    private static void addNoteToMissionLog(Scanner scanner, ArrayList<String> missionLog) {
        System.out.print("Enter your note: ");
        scanner.nextLine(); // Consume the newline character
        String userNote = scanner.nextLine();
        missionLog.add("Note: " + userNote);
        System.out.println("Note added to Mission Log.");
    }

    // Method to delete a note from the mission log
    private static void deleteNoteFromMissionLog(Scanner scanner, ArrayList<String> missionLog) {
        System.out.print("Enter the number of the note to delete: ");
        int noteNumber = scanner.nextInt();

        if (noteNumber >= 1 && noteNumber <= missionLog.size()) {
            missionLog.remove(noteNumber - 1);
            System.out.println("Note deleted from Mission Log.");
        } else {
            System.out.println("Invalid note number. Please enter a valid note number.");
        }
    }

    // Method to perform a background process (simulated by a sleep)
    private static void performBackgroundProcess() {
        System.out.println("\nPerforming background process...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Background process completed.");
    }
}
