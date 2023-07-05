
// I have added the comments for better understanding of the code

import javax.swing.JOptionPane; // here i am using swing for input and output in dialog box
import java.util.Random; // using random class for generating random number

public class GuessTheNumber {
    public static void main(String[] args) {
        // Generate random number

        char play = 'y'; // for playing again

        int round = 1; // i am here using round for counting the number of rounds
        int score = 0; // for counting the score
        while (play == 'y') {
            Random random = new Random();
            int targetNumber = random.nextInt(100) + 1; // generating random number between 1 and 100

            // Set initial values
            int attempts = 0;
            boolean guessed = false;
            System.out.println("Target number is :" + targetNumber); // for testing purpose

            int success = 0; // for checking the success of the game
            JOptionPane.showMessageDialog(null, "Round: \t " + round + "\t  Attempts left: " + (10 - attempts));
            // Game loop
            while (!guessed && attempts <= 10) {
                // try catch block for handling the exception in input
                try {
                    String input = JOptionPane.showInputDialog(null,
                            " (Attempts left : \t" + (10 - attempts) + ")\t Enter your guess:");
                    int guess = Integer.parseInt(input);
                    attempts++;
                    if (guess == 0)
                        break;
                    else if (guess < 1 || guess > 100) {
                        JOptionPane.showMessageDialog(null, "Enter a number between 1 and 100");

                        continue;
                    } else if (guess == targetNumber) {
                        score+=((10-attempts+1)*10);
                        JOptionPane.showMessageDialog(null,
                                "Congratulations! You guessed the number: \t" + targetNumber + " \tNumber of attempts: "
                                        + attempts+ "\nYour score is: "+score+"\nYou are playing well!");
                        guessed = true;
                        success = 1;
                    } else if (guess < targetNumber) {
                        JOptionPane.showMessageDialog(null, "Too low! Try again.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Too high! Try again.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "Enter a number between 1 and 100 \n for quitting the game press 0");

                }
            }

            if (success == 0) {
                JOptionPane.showMessageDialog(null, "You lose! The number was: " + targetNumber);
                play = JOptionPane.showInputDialog(null, "Do you want to play again? (y/n)").charAt(0);
                if (play == 'y')
                    round++;
            } else {
                play = JOptionPane.showInputDialog(null, "Do you want to play again? (y/n)").charAt(0);
                if (play == 'y')
                    round++;

            }
        }
    }
}
