import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        Colors.println("-----------------------------", Colors.CYAN);
        Colors.println("| Welcome to The Liar's Bar |", Colors.CYAN);
        Colors.println("-----------------------------", Colors.CYAN);
        Colors.println("1. How to Play?", Colors.LIGHT_BLUE);
        Colors.println("2. Start The Game", Colors.LIGHT_BLUE);
        Colors.println("3. Exit", Colors.LIGHT_BLUE);
        // TODO input validation
        int mainMenuChoice;
        do {
            Colors.print("Enter your choice: ", Colors.LIGHT_BLUE);
            mainMenuChoice = scanner.nextInt();
            switch (mainMenuChoice) {
                case 1 : {
                    howToPlay();
                    break;
                }
                case 2 : {
                    GameManager gameManager = new GameManager();
                    gameManager.start();
                    break;
                }
                case 3: {
                    break;
                }
                default: {
                    Colors.println("Invalid Choice", Colors.RED);
                }
            }
        } while (mainMenuChoice != 3);
        scanner.close();
    }

    static void howToPlay() {
        Colors.println("Information About The Game", Colors.GREEN);
    }
}
