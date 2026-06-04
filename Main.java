 /*Name: Hossain,Yusyra
  Date: July 23, 2024
  Purpose of Program: This program is a game of modified Rock, Paper, Scissors. It is called Dragon, Knight, and Wizard, where the player competes against the computer. The game will randomly generate the algorithm's choice, store points in an array and determine the winner based on the rules. */

import java.util.Scanner;


public class Main {

  static int newUserPoints; 
  static int newComputerPoints; //Globalize Variable so I can use it in and out of the main method


  /* This is the main method that runs the game program. */

  public static void main(String[] args) {

    // Declare Constants
      final int NUM_OF_ROUNDS = 10;

    // Initialize variables to keep track of game statistics
      int computerChoice = 0;
      int userChoice = 0;
      int rounds = 0; // To keep track of the number of rounds played

    //Make an array for each: Points for computer and player
      int[] computerPoints = new int[NUM_OF_ROUNDS+1];
      int[] userPoints = new int[NUM_OF_ROUNDS+1];

    //Start up the game
      info(); //Call subroutine to display game info of game to user
      boolean play = true; // Make play true so that the game can start

    //Main Game Loop
      while (play)   
      {

        do  
          {
            userChoice = choice1(rounds); //Call subroutine to check if user wants to play the game

            if (userChoice == 1 || userChoice == 2 || userChoice == 3)
              {
                rounds++;
                computerChoice = choice2(); //Call subroutine to generate computer's choice
                userPoints[rounds] = determineWinner(userChoice, computerChoice, rounds, userPoints); //Call subroutine to display winner and update user points
                computerPoints[rounds] = updateComp(userChoice, computerChoice, rounds, computerPoints); //Call subroutine to update computer points
                newUserPoints = earningsPlayer(userPoints, rounds); //Call subroutine to display user's points
                newComputerPoints = earningsComp(computerPoints, rounds); //Call subroutine to display computer's points
              }
            else if (userChoice == 4) 
              {
                System.out.println ("");
                System.out.println ("");
                System.out.print ("You have chose to quit. Thank you for playing!");
                play = false;
              }
          } while (play && rounds<NUM_OF_ROUNDS); //End of Main game loop

        if (play) // Show stats and check if the user wants to play again
          { 
            checkWin(newUserPoints, newComputerPoints); //Call subroutine to check if user has won the game
            histogram1(userPoints, NUM_OF_ROUNDS); //Call subroutine to display histogram of user's points
            histogram2(computerPoints, NUM_OF_ROUNDS); //Call subroutine to display histogram of computer's points

            // Reset rounds variable at the beginning of each game
            rounds = 0; 
            for (int i = 0; i <= NUM_OF_ROUNDS; i++) 
              {
                computerPoints[i] = 0;
                userPoints[i] = 0;
              }
          } 
      } // End of outer loop
  }



  /* This method doesn't recieve any values. It is just used to display the information to the user.
    Parameters: N/A
    Returns: void
  */

  public static void info() {

    System.out.println("Dragon, Knight, and Wizard");
    System.out.println("++++++++++++++++++++++++++++");
    System.out.println("");
    System.out.println ("Welcome to the Fantasy form of Rock, Papers, Scissors! The 3 characters of this game are Dragon, Knight, and Wizard. The rules are simple: ");
    System.out.println ("1) The Dragon beats the Wizard but is defeated by the Knight.");
    System.out.println ("2) The Knight beats the Dragon but is defeated by the Wizard.");
    System.out.println ("3) The Wizard beats the Knight but is defeated by the Dragon.");
    System.out.println ("");
    System.out.println ("The way this game works is that you will be asked to choose any one of these 3 characters. The computer will also choose one, and the winner of each round will be determined by the rules above. The game will continue until you have played 10 rounds. At the end, the game will display the number of times you have won and lost. Good luck!");
  }



  /* This method is a checks the character the user chooses and makes sure it is a valid input. It recieves the round number to display it, then sends this information back to the main method.
    Parameters: int rounds
    Returns: int userChoice
  */

  public static int choice1(int roundNumber) {

    //Create a Scanner object for user input
      Scanner input = new Scanner(System.in);

    //Initialize int Variable
      int userChoice = 0;

    //Make a boolean expression for the loop 
      boolean isTrue = true;

    //Check if player wants to play
      while (isTrue == true) {

      //Get Input
      System.out.println("");
      System.out.println("-----------------");
      System.out.println ("Round Number " + (roundNumber+1) + ":");
      System.out.println("");
      System.out.println ("CHARACTERS:");
      System.out.println ("1) Dragon ");
      System.out.println ("2) Knight ");
      System.out.println ("3) Wizard ");
      System.out.println ("4) Exit ");
      System.out.println ("");
      System.out.print ("Please enter your option (Integer Only): ");
      String playerOption = input.nextLine();
      System.out.println ("");

      try 
      {
        userChoice = Integer.parseInt(playerOption); //Parse string into int

        if (userChoice == 1 || userChoice == 2 || userChoice == 3 || userChoice == 4)
          {
            if (userChoice == 1) //Nested If-Else Statement to check tell user their choice
              {System.out.println ("You have chosen Dragon!");
              isTrue = false;}
            else if (userChoice == 2)
              {System.out.println ("You have chosen Knight!");
              isTrue = false;}
            else if (userChoice == 3)
              {System.out.println ("You have chosen Wizard!");
              isTrue = false;}
            else
              isTrue = false;
          }
        else
          {
            System.out.println ("Invalid input. Please try again.");
            System.out.println ("");
            System.out.println ("");
          }
      }
      catch (NumberFormatException e) //Invalid Input
      {
        System.out.println ("Invalid input. Please try again.");
        System.out.println ("");
        System.out.println ("");
      } 
      }

      //Return the result of the player's choice
      return userChoice;
  }



  /* This method genrates the choice of the computer. It then sends this information back to the main method.
    Parameters: N/A
    Returns: int computerChoice
  */

  public static int choice2() {

    //Generate Random Number
      int computerChoice = (int) (Math.random() * 3 + 1);

    //Output Choice
      if (computerChoice == 1)
        {System.out.println ("The computer has choosen Dragon!");}
      else if (computerChoice == 2)
        {System.out.println ("The computer has choosen Knight!");}
      else if (computerChoice == 3)
        {System.out.println ("The computer has choosen Wizard!");}

    //Return the result of the player's choice
      return computerChoice;
  }



  /* This method processes output information based on the choices. It recieves the choices, userPoints array (empty indexes), and round number. It then sends back the recived points of the user.
    Parameters: N/A
    Returns: int computerChoice
  */

  public static int determineWinner(int userChoice, int computerChoice, int roundNumber, int [] userPoints) {

        //Result
      if (computerChoice == userChoice)
        {
          System.out.println ("It's a tie!");
          userPoints[roundNumber] = userPoints[roundNumber] + 0;
          return userPoints[roundNumber];
        }
      else if (computerChoice == 1 && userChoice == 2)
        {
          System.out.println ("Yes! Your knight destroyed the dragon!");
          userPoints[roundNumber] = userPoints[roundNumber] + 1;
          return userPoints[roundNumber];
        }
      else if (computerChoice == 2 && userChoice == 3)
        {
          System.out.println ("Yes! Your wizard has out the knight under a mighty spell!");
          userPoints[roundNumber] = userPoints[roundNumber] + 1;
          return userPoints[roundNumber];
        }
      else if (computerChoice == 3 && userChoice == 1)
        {
          System.out.println ("Yes! Your dragon burned the wizard's wand!");
          userPoints[roundNumber] = userPoints[roundNumber] + 1;
          return userPoints[roundNumber];
        }
      else
        {
          System.out.println ("Aww, you're DESTROYED!");
          userPoints[roundNumber] = userPoints[roundNumber] + 0;
          return userPoints[roundNumber];
        }
  }



  /* This method just updates the computer's points, as it can't be done in the previous method. It recieves the choices, computerPoints array (empty indexes), and round number. It then sends back the recived points of the computer.
    Parameters: N/A
    Returns: int computerChoice
  */

  public static int updateComp(int userChoice, int computerChoice, int roundNumber, int [] computerPoints) {

        //Result
      if (computerChoice == userChoice || (computerChoice == 1 && userChoice == 2) || (computerChoice == 2 && userChoice == 3) || (computerChoice == 3 && userChoice == 1))
        {
          computerPoints[roundNumber] = computerPoints[roundNumber] + 0;
          return computerPoints[roundNumber];
        }
      else
        {
          computerPoints[roundNumber] = computerPoints[roundNumber] + 1;
          return computerPoints[roundNumber];
        }
  }


  /* This method updates the points of the player. It recieves the current points and round number and sends back the total points of the user.
    Parameters: int userPoints [], int rounds
    Returns: totalUserPoints  */

    public static int earningsPlayer(int[] points, int roundNumber)
    {
      int totalPoints = points[roundNumber]; // Store the current round's points
      for (int i = 0; i < roundNumber; i++)
        {
          totalPoints = totalPoints + points[i]; // Add previous points to the current round
        }

      System.out.println ("");
      System.out.println ("Your current points are: " + totalPoints);

      return totalPoints;
    } 



  /* This method updates the points of the computer. It recieves the current points and round number and sends back the total points of the computer.
    Parameters: int computerPoints [], int rounds
    Returns: totalCompPoints
  */

    public static int earningsComp(int[] points, int roundNumber)
    {
      int totalPoints = points[roundNumber]; // Store the current round's points
      for (int i = 0; i < roundNumber; i++)
        {
          totalPoints = totalPoints + points[i]; // Add previous points to the current round
        }

      System.out.println ("The computer's current points are: " + totalPoints);
      System.out.println ("");
      return totalPoints;
    }



  /* This method is a subroutine that checks if the player has won or not. It recieves the points of the user and computer and sends out (to the user) the result of the game (win or lose). 
    Parameters: int userPoints, int computerPoints
    Returns: void
  */

  public static void checkWin(int playerPoints, int computerPoints)
  {
        //Check if player has won
      if (playerPoints > computerPoints)
        {
          System.out.println("");
          System.out.println("-------------------------------------------");
          System.out.println("");
          System.out.println ("Congratulations! You have won the game!");
          System.out.println("");
          System.out.println("");
        }

      else if (playerPoints < computerPoints)
        {
          System.out.println("");
          System.out.println("-------------------------------------------");
          System.out.println("");
          System.out.println ("Sorry, you have lost the game.");
          System.out.println("");
          System.out.println("");
        }

      else
        {
          System.out.println("");
          System.out.println("-------------------------------------------");
          System.out.println("");
          System.out.println ("It's a tie!");
          System.out.println("");
          System.out.println("");
        }
  }



  /* This method is a subroutine that outputs the histogram of the user's points. It recieves the user's points that were stored in an array and displays the histogram.
    Parameters: int points [], int TOTAL_ROUNDS
    Returns: void
  */

  public static void histogram1(int [] points, int TOTAL_ROUNDS)
  {

    System.out.println("Here is a histogram of points you earned per round (No asterisk means no points earned in that round): ");

    for (int i = 0; i < TOTAL_ROUNDS; i++) //Print out round number
      {
        System.out.print(i + 1 + ": ");

          for (int j = 0; j < points[i+1]; j++) //Print out how many points earned per round
            {
              System.out.print("*");
            }

        System.out.println("");
      }

      System.out.println("");
      System.out.println("");
  }



  /* This method is a subroutine that outputs the histogram of the computer's points. It recieves the computer's points that were stored in an array and displays the histogram.
    Parameters: int points [], int TOTAL_ROUNDS
    Returns: void
  */

  public static void histogram2(int [] points, int TOTAL_ROUNDS)
  {

    System.out.println("Here is a histogram of points the computer earned per round (No asterisk means no points earned in that round): ");

    for (int i = 0; i < TOTAL_ROUNDS; i++) //Print out round number
      {
        System.out.print(i + 1 + ": ");

          for (int j = 0; j < points[i+1]; j++) //Print out how many points earned per round
            {
              System.out.print("*");
            }

        System.out.println("");
      }

      System.out.println("");
      System.out.println("");
      System.out.println("You can now play again, or press 4 to exit the game.");
  }

}  