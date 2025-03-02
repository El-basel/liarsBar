import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        GameManager gameManager = new GameManager();
        System.out.println("***********************");
        System.out.println("Welcome to The Liar's Bar");
        System.out.println("***********************");
        System.out.println("1. How to Play?");
        System.out.println("2. Start The Game");
        System.out.println("3. Exit");
        // TODO input validation
        int mainMenuChoice;
        do {
            System.out.print("Enter your choice: ");
            mainMenuChoice = scanner.nextInt();
            switch (mainMenuChoice) {
                case 1 -> howToPlay();
                case 2 -> {
                    gameManager.initializeGame();
                    gameManager.start();
                }
                case 3 -> mainMenuChoice = 3;
                default -> System.out.println("Invalid Choice");
            }
        } while (mainMenuChoice != 3);
        scanner.close();
    }

    static void howToPlay() {
        System.out.println("Information About The Game");
    }
}
