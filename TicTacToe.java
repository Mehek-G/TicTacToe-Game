//Mehek Ghattora
//CPSC-39-12103

import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;
import java.util.Arrays;

public class TicTacToe {


  // creates an array of the board
  static String[] board;
  // keeps track of whose turn it is whether it is player "X" or "O"
  static String turn;
  // player chooses to play alone or against CPU
  static boolean playCPU; //mehek
  //tracks X's wins
  static int winsX = 0; //mehek
  //tracks O's wins
  static int winsO = 0; //mehek
  //tracks ties
  static int draws = 0; //mehek


  //allows the user to choose whether to play against CPU or not
  static void gameMode() { //mehek
	//scanner object to read user input
    Scanner in = new Scanner(System.in); //mehek
    // asks user if they want to play against CPU
    System.out.println("Would you like to play against the CPU or another player?"); //mehek
    System.out.println("Type 1 to play against another player."); //mehek
    System.out.println("Type 2 to play against the CPU."); //mehek
    //choice is the user's input
    int choice; //mehek
    do { //mehek
      System.out.print("Choose your game mode: "); //mehek
      try { //mehek
    	//read the number input by user
        choice = in.nextInt(); //mehek
        //anything other than 1 or 2
        if (choice < 1 || choice > 2) { //mehek
          System.out.println("Invalid input. Please try again."); //mehek
        }
        //anything that is not a number
      } catch (InputMismatchException e) { //mehek
        System.out.println("Invalid input. Please enter 1 or 2."); //mehek
        //reads the next input
        in.nextLine(); //mehek
        //keep looping until a valid input
        choice = 0; //mehek
      }
    } while (choice < 1 || choice > 2); //mehek
    //playCPU set to true if the user chooses to play against the CPU
    playCPU = (choice == 2); //mehek
    
  }
  
  //reads user's input when it is their turn for placement of X or O
  static int getUserInput(Scanner in) { //mehek
	//states whose turn it is
    System.out.println(turn + " 's turn; enter a slot number to place " + turn + " in:"); //mehek
    //reads user input to place their turn
    return in.nextInt(); //mehek
  }

  //method to get CPU's input and place on board
  static int getCPUInput() { //mehek
	  //produces a random number
    Random rand = new Random(); //mehek
    //returns number between 1 and 9
    return rand.nextInt(9) + 1; //mehek
  }

  /*
   * Base code citation
   * Title: geeksforgeeks source code
   * Author: sakshikulshreshtha
   * Date: 12-19-2022
   * Code Version: unknown
   * Availability: https://www.geeksforgeeks.org/tic-tac-toe-game-in-java/
   */

  // different cases/possibilities of winning 3 in a row
  static String checkWinner() {
    // for loop to check rows for 3 in a row each turn
    for (int a = 0; a < 8; a++) {
      // initializes line with null value until there is 3 in a row
      String line = null;
      // selects the case depending on the iteration of "a"
      switch (a) {
        // top row
        case 0:
          line = board[0] + board[1] + board[2];
          break; //break to stop checking cases
        // middle row
        case 1:
          line = board[3] + board[4] + board[5];
          break; //break to stop checking cases
        // bottom row
        case 2:
          line = board[6] + board[7] + board[8];
          break; //break to stop checking cases
        // left column
        case 3:
          line = board[0] + board[3] + board[6];
          break; //break to stop checking cases
        // middle column
        case 4:
          line = board[1] + board[4] + board[7];
          break; //break to stop checking cases
        // right column
        case 5:
          line = board[2] + board[5] + board[8];
          break; //break to stop checking cases
        // diagonal from top left to bottom right
        case 6:
          line = board[0] + board[4] + board[8];
          break; //break to stop checking cases
        // diagonal from top right to bottom left
        case 7:
          line = board[2] + board[4] + board[6];
          break; //break to stop checking cases
      }
      // For X winner
      if (line.equals("XXX")) {
        return "X";
      }
      // For O winner
      else if (line.equals("OOO")) {
        return "O";
      }
    }

    // for loop to check if there is a tie after each turn
    for (int a = 0; a < 9; a++) {
      // continues each round
      if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
        break;
        // once "a == 8 " then it is a tie since there are no more turns left
      } else if (a == 8) {
        return "draw";
      }
    }
    //continues the game
    return null;
  }

  // example of the board
  /*
   * |---|---|---|
   * | 1 | 2 | 3 |
   * |-----------|
   * | 4 | 5 | 6 |
   * |-----------|
   * | 7 | 8 | 9 |
   * |---|---|---|
   */

  // method to print the board
  static void printBoard() {
    // top border
    System.out.println("|---|---|---|");
    // top row with borders and numbers
    System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
    // second row with borders
    System.out.println("|-----------|");
    // middle row with borders and numbers
    System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
    // third row with borders
    System.out.println("|-----------|");
    // bottom row with borders and numbers
    System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
    // bottom border
    System.out.println("|---|---|---|");
  }

  //prints the scores stored in each number after every game
  static void printScore() { //mehek
    System.out.println("Score:"); //mehek
    System.out.println("Player X wins: " + winsX); //mehek
    System.out.println("Player O wins: " + winsO); //mehek
    System.out.println("Draws: " + draws); //mehek
  }




  // method to play the game
  public static void main(String[] args) {
    // read in the scanner for user input
    Scanner in = new Scanner(System.in);

    //boolean to keep track of whether the game continues
    boolean playAgain = true; //mehek
    //while the user chooses to play again, it cycles through the entire game
    while( playAgain ) {
    	//goes to method gameMode for the player to choose
      gameMode(); //mehek

    // creates a new board and sets the user turn to "X"
    // "String[9]" creates an array of 9 strings
      board = new String[9];
      turn = "X";
    // winner is set as "null" until there is a winner
      String winner = null;

    // for loop to set each position on the board to a number
      for (int a = 0; a < 9; a++) {
      // adding 1 to the index to make it start at 1 instead of 0
        board[a] = String.valueOf(a + 1);
      }

    // prints the board
      printBoard();

      //set to false until a player inputs an invalid input
      boolean invalidInputShown = false; //mehek
      
      //set to null to keep the game going until there is a winner
      while (winner == null) { //mehek 
    	  //user's input
        int numInput; //mehek
        //if not playing against CPU or it is X's turn
        if (!playCPU || turn.equals("X")) { //mehek
          try {
        	  //read user's input
        	  numInput = getUserInput(in); //mehek
        	  //checks for invalid inputs
        } catch (InputMismatchException e) { //mehek
        	System.out.println("Invalid input. Please enter a number: "); //mehek
        	//continues program
        	in.nextLine(); //mehek
        	continue; //mehek
        }
          //gets CPU input if playing the CPU 
      } else { //mehek
        numInput = getCPUInput(); //mehek
        System.out.println("CPUs turn; the CPU entered " + numInput); //mehek
      }
        //if input is not 1 to 9 or the position on board is not empty
      if (numInput < 1 || numInput > 9 || !board[numInput - 1].equals(String.valueOf(numInput))) { //mehek
    	  //if invalid input is true
    	if (!invalidInputShown) { //mehek
          System.out.println("Invalid input. Please enter a number: "); //mehek
          invalidInputShown = true; //mehek
        }
        continue; //mehek
      }
      //places input in proper location since each value is + 1 of the array number slot
      board[numInput - 1] = turn; //mehek
      printBoard(); //mehek
      //calls checkWinner to see if there a winner
      winner = checkWinner(); //mehek
      //if no winner, 
      if (winner == null) {
    	  //if it was X's turn, it becomes O's turn
        turn = (turn.equals("X")) ? "O" : "X"; //mehek
      }

      }
      //if there is no winner, prints out draw
      if (winner.equalsIgnoreCase("draw")) {
        System.out.println("It's a draw! Thanks for playing.");
        //increment draw score by 1
        draws++;

      } else {
    	  //prints who the winner is
        System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        // if the winner is X, increment score by 1
        if (winner.equals("X")) {
          winsX++;
          //if winner is O, increment score by 1
        } else {
          winsO++;
        }

      }
      
      // call print score to print the total after every game
      printScore(); //mehek
      
      //asks user if they want to play again
      System.out.println("Would you like to play again? (y/n)"); //mehek
      //reads next line/input
      in.nextLine(); //mehek
      //reads the next line
      String playAgainInput = in.nextLine().trim();
      //if user types y, play again
      //if user types n, stops running and outputs "thanks"
      playAgain = playAgainInput.equalsIgnoreCase("y"); //mehek
      if(!playAgain) { //mehek
        System.out.println("Thanks for playing!"); //mehek
      }

    }
    //closes scanner
    in.close();
  }

}
