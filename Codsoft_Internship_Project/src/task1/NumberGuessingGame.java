package task1;

import java.util.Random;
import java.util.Scanner;

class GenerateRandomNumber {
    private int randomNumber;

    GenerateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        System.out.println("Random Number is generated. Try and Guess it !!");
    }

    public int getRandomNumber() {
        return randomNumber;
    }
}

class GameLogic {
    private int targetNumber;
    private int attempts;

    public GameLogic(int targetNumber) {
        this.targetNumber = targetNumber;
        this.attempts = 0;
    }

    public void playGame() {
        Scanner sc = new Scanner(System.in);
        int guess;

        while (true) {
            System.out.print("Guess (1-100): ");

            // Check if input is an integer
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); // consume invalid input
                System.out.print("Guess (1-100): ");
            }

            guess = sc.nextInt();
            attempts++;

            // Check if guess is within range
            if (guess < 1 || guess > 100) {
                System.out.println("Guess should be between 1 and 100.");
                continue;
            }

            // This is the main logic behind the game.
            if (guess < targetNumber) {
                if (guess >= targetNumber - 3 && guess <= targetNumber - 1) {
                    System.out.println("You are very close. Guess Larger number.");
                } else {
                    System.out.println("Guess a Greater Number.");
                }
            } else if (guess > targetNumber) {
                if (guess <= targetNumber + 3 && guess >= targetNumber + 1) {
                    System.out.println("You are very close. Guess Smaller number.");
                } else {
                    System.out.println("Guess a Smaller Number.");
                }
            } else {
                System.out.println("Hurray !! You guessed it Correct in " + (attempts + 1)+ " attempts.");
                break;
            }

            if (attempts == 10) {
                System.out.println("Game over ! \nYou have reached the limit.");
                break;
            }
        }

        sc.close();
    }
}

public class NumberGuessingGame {
    public static void main(String[] args) {
        GenerateRandomNumber obj = new GenerateRandomNumber();
        int targetNumber = obj.getRandomNumber();
//        System.out.println(targetNumber); // For debugging purposes only.

        GameLogic game = new GameLogic(targetNumber);
        game.playGame();
    }
}
