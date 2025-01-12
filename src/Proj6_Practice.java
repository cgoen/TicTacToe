import java.util.Scanner;

public class Proj6_Practice {
    public static void main(String[] args) {
        runGame();
    }//end main

    public static void runGame() {
        boolean xTurn = true;
        String winner = "";
        String[][] gameBoard = new String[3][3];

        initializeGameBoard(gameBoard);

        while(winner.equals("")) {
            printCurrentBoard(gameBoard);
            if(xTurn) {
                System.out.println("It is X's turn");
            }
            else{
                System.out.println("It is O's turn");
            }

            getUserInput(xTurn, gameBoard);

            xTurn = !xTurn;

            winner = getWinner(gameBoard);

            if(isBoardFull(gameBoard)) {
                winner = "C"; // If board is full and no winner, it's a tie!
                System.out.println("It's a tie!");
            }
        }

    }//end runGame


    public static void initializeGameBoard(String[][] gameBoard) {
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard.length; j++){
                gameBoard[i][j] = " ";
            }
        }
    }//end initializeGameBoard

    public static void printCurrentBoard(String[][] gameBoard) {
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard.length; j++) {
                System.out.print(gameBoard[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if(i < 2) {
                System.out.print("- - - - -");
            }
            System.out.println();
        }
    } //end printCurrentBoard

    public static void getUserInput(boolean xTurn, String[][] gameBoard) {
        int row;
        int col;
        Scanner keyboard = new Scanner(System.in);
        boolean keepAsking = true;

        while(keepAsking) {
            System.out.println("Enter a row then col with a space in between. Each value must be from 0 to 2.");
            row = keyboard.nextInt();
            col = keyboard.nextInt();
            keyboard.nextLine(); // Consumes the next line

            // Check if user has entered in valid numbers (0 to 2)
            if (col >= 0 && col <= 2 && row >=0 && row <=2) {
                if(!cellAlreadyOccupied(row, col, gameBoard)) { // Check if cell is occupied
                    keepAsking = false; // Exit while loop if valid number and cell not occupied
                    if(xTurn) {
                        gameBoard[row][col] = "X";
                    }else {
                        gameBoard[row][col] = "O";
                    }
                } else {
                    System.out.println("The cell is already occupied! Please select a different cell.");
                }
            }else {
                System.out.println("Row and col must be numbers from 0 and 2.");
            }
        }//end while
    }//end getUserInput

    public static boolean cellAlreadyOccupied(int row, int col, String[][] gameBoard) {
        return !gameBoard[row][col].equals(" ");
    }//end cellAlreadyOccupied

    public static String getWinner(String[][] gameBoard) {
        final int ROW = gameBoard.length;
        final int COL = gameBoard[0].length;

        //Check for horizontal win
        for(int i = 0; i < ROW; i++) {
            if(!gameBoard[i][0].equals(" ") &&
                gameBoard [i][0].equals(gameBoard[i][1]) &&
                gameBoard[i][1].equals(gameBoard[i][2])){
                    return gameBoard[i][0];
            }
        }

        // Check for vertical win
        for(int i = 0; i < COL; i ++) {
            if(!gameBoard[0][i].equals(" ") &&
                gameBoard[0][i].equals(gameBoard[1][i]) &&
                gameBoard[1][i].equals(gameBoard[2][i])) {
                    return gameBoard[0][i];
            }
        }

        // Check for horizontal win
        if (!gameBoard[0][0].equals(" ") && gameBoard[0][0].equals(gameBoard[1][1])
            && gameBoard[1][1].equals(gameBoard[2][2])) {
                return gameBoard[0][0];
        }

        //Check for horizontal win
        if (!gameBoard[0][2].equals(" ") && gameBoard[0][2].equals(gameBoard[1][1])
                && gameBoard[1][1].equals(gameBoard[2][0])) {
            return gameBoard[0][2];
        }

        return ""; // no winner yet
    }//end getWinner

    public static boolean isBoardFull(String[][] gameBoard) {
        int count = 0;

        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[0].length; j++) {
                if (!gameBoard[i][j].equals(" ")) {
                    count ++;
                }
            }
        }
        return count == 9;
    }//end isBoardFull
}//end class
