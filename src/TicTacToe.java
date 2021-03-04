import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        // Make board
        char[][] board = new char[3][3];

        // Set up user symbol
        char userSym = setUserSymbol();

        // Set up computer symbol
        char compSym = setCompSymbol(userSym);

        // Other initializations
        // Player order
        int userNumber = setUserNumber(userSym);
        int compNumber = setCompNumber(userNumber);

        // Set up for later
        int[] moveAttempt;

        // Store wins, losses, draws
        int[] score = new int[3];

        // Keep track of number of moves
        int moves = 0;

        // Outer loop to check for play again
        while(true){
            // Actual game
            while(moves <= 9){

                // Player's turn
                if(moves % 2 == userNumber){
                    // Print board
                    printBoard(board);

                    // Move loop
                    do {
                        // Get move
                        moveAttempt = getMove();

                        // Check move legitimacy
                    } while (!moveCheck(moveAttempt, board));

                    // Do move
                    doMove(moveAttempt, board, userSym);

                    // Print board
                    printBoard(board);

                    // Check for win
                    if(winCheck(board)){
                        System.out.println("You won!");
                        score[0]++;
                        break;
                    } else {
                        moves++;
                    }

                    // Add to moves or finish game
                }

                // Computer's turn
                else {
                    // Check board, decide move
                    moveAttempt = calcMove(board, compSym, userSym);

                    // Do move
                    doMove(moveAttempt, board, compSym);

                    // Check for win
                    if(winCheck(board)){
                        System.out.println("Better luck next time :(");
                        score[1]++;
                        break;
                    } else {
                        moves++;
                    }
                }
            }

            if(!playAgain()){
                break;
            }
        }
    }

    private static int[] calcMove(char[][] board, char compSym, char userSym) {
        int[] goal = new int[2];

        // If board is empty, go to middle
        // Check if empty
        boolean empty = true;
        for(char[] row : board){
            for(char letter : row){
                if(letter == 'X' || letter == 'O'){
                    empty = false;
                    break;
                }
            }
        }

        // If empty, go for middle
        if(empty){
            goal[0] = 1;
            goal[1] = 2;
            return goal;
        }

        // Create check lists
        char[] row1 = new char[3];
        char[] row2 = new char[3];
        char[] row3 = new char[3];

        // If 2 of computer's pieces in a row, go for third place
        // Check rows
        char[] check = new char[3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                // Add elements of row to check list
                check[j] = board[i][j];
            }
            // Check check list
            if(check[0] == compSym && check[1] == compSym && check[2] == 0){
                goal[0] = i;
                goal[1] = 2;
                return goal;
            }
            else if(check[0] == compSym && check[1] == 0 && check[2] == compSym){
                goal[0] = i;
                goal[1] = 1;
                return goal;
            }
            else if(check[0] == 0 && check[1] == compSym && check[2] == compSym){
                goal[0] = i;
                goal[1] = 0;
                return goal;
            }
        }

        // Check cols
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                check[j] = board[j][i];
            }
            // Check check list
            if(check[0] == compSym && check[1] == compSym && check[2] == 0){
                goal[0] = 2;
                goal[1] = i;
                return goal;
            }
            else if(check[0] == compSym && check[1] == 0 && check[2] == compSym){
                goal[0] = 1;
                goal[1] = i;
                return goal;
            }
            else if(check[0] == 0 && check[1] == compSym && check[2] == compSym){
                goal[0] = 0;
                goal[1] = i;
                return goal;
            }
        }

        // If 2 of user's pieces in a row, go for third place
        // Check rows
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                // Add elements of row to check list
                check[j] = board[i][j];
            }
            // Check check list
            if(check[0] == userSym && check[1] == userSym && check[2] == 0){
                goal[0] = i;
                goal[1] = 2;
                return goal;
            }
            else if(check[0] == userSym && check[1] == 0 && check[2] == userSym){
                goal[0] = i;
                goal[1] = 1;
                return goal;
            }
            else if(check[0] == 0 && check[1] == userSym && check[2] == userSym){
                goal[0] = i;
                goal[1] = 0;
                return goal;
            }
        }

        // Check cols
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                check[j] = board[j][i];
            }
            // Check check list
            if(check[0] == userSym && check[1] == userSym && check[2] == 0){
                goal[0] = 2;
                goal[1] = i;
                return goal;
            }
            else if(check[0] == userSym && check[1] == 0 && check[2] == userSym){
                goal[0] = 1;
                goal[1] = i;
                return goal;
            }
            else if(check[0] == 0 && check[1] == userSym && check[2] == userSym){
                goal[0] = 0;
                goal[1] = i;
                return goal;
            }
        }

        // If user didn't take middle, go there
        if(board[1][1] == 0){
            goal[0] = 1;
            goal[1] = 1;
            return goal;
        }

        // Otherwise, go for first empty spot
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][i] == 0){
                    goal[0] = i;
                    goal[1] = j;
                }
            }
        }
        return goal;
    }

    private static boolean playAgain() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Do you want to play again? Y or N");

        char ans = 0;

        while(true){
            try{
                ans = keyboard.next().charAt(0);

                if(ans == 'Y'){
                    return true;
                }
                else if(ans == 'N'){
                    return false;
                }
                else{
                    System.out.println("Invalid input");
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input");
            }
        }
    }

    private static int setCompNumber(int userNumber) {
        if(userNumber==1){
            return 0;
        } else {
            return 1;
        }
    }

    private static int setUserNumber(char userSym) {
        if(userSym=='X'){
            return 0;
        } else {
            return 1;
        }
    }

    private static boolean winCheck(char[][] board) {
        // Row check
        boolean rowWin = false;
        char standard = 0;
        for(char[] row : board){
            if(row[0] == 0) {                       // If first element is blank, skip
                continue;
            }
            for(int i = 0; i < row.length; i++){
                if(i == 0){                         // Check if up to start of row
                    standard = row[i];              // Store first element of row
                } else {                            // Check if next elements in row equal first element
                    if(row[i] == standard){
                        rowWin = true;
                    } else {
                        rowWin = false;
                        break;
                    }
                }
            }
        }

        if(rowWin) {return true;}

        // Col check
        boolean colWin = false;
        for(int i = 0; i < board.length; i++){
            if(board[0][i] == 0){                   // If first element is blank, skip
                continue;
            }
            for(char[] row : board){
                if(row == board[0]){                // Check if up to top of col
                    standard = row[i];              // Store first element of col
                } else {                            // Check col elements against first element
                    if(row[i] == standard){
                        colWin = true;
                    } else {
                        colWin = false;
                        break;
                    }
                }
            }
        }

        if(colWin) {return true;}

        // Diag 1 check
        boolean diag1Win = false;
        for(int i = 0; i < board.length; i++){
            if(board[0][0] != 0){                   // Check if top left is empty
                if(i == 0){
                    standard = board[i][i];         // If not, store top left element
                } else {
                    if(board[i][i] == standard){    // Check other elements against top left element
                        diag1Win = true;
                    } else {
                        diag1Win = false;
                        break;
                    }
                }
            }
        }

        if(diag1Win) {return true;}

        // Diag 2 check
        boolean diag2Win = false;
        for(int i = 0; i < board.length; i++){
            int rowEnd = board[0].length - 1;
            if(board[0][rowEnd] != 0){
                if(i == 0){
                    standard = board[i][rowEnd - i];
                } else{
                    if(board[i][rowEnd - 1] == standard){
                        diag2Win = true;
                    } else {
                        diag2Win = false;
                        break;
                    }
                }
            }
        }

        return diag2Win;
    }

    private static void doMove(int[] moveAttempt, char[][] board, char userSym) {
        int row = moveAttempt[0];
        int col = moveAttempt[1];
        board[row][col] = userSym;
    }

    private static boolean moveCheck(int[] moveAttempt, char[][] board) {
        return board[moveAttempt[0]][moveAttempt[1]] == 0;
    }

    private static int[] getMove() {

        // Set up scanner
        Scanner keyboard = new Scanner(System.in);

        // Set up variables
        int row;
        int col;

        // Get the input
        System.out.println("Please enter a row");
        row = keyboard.nextInt();

        System.out.println("Please enter a column");
        col = keyboard.nextInt();

        // Close scanner because security
        keyboard.close();

        // Clean the input, because no one enters row 0
        row--;
        col--;

        // Return the input
        return new int[]{row,col};
    }

    private static void printBoard(char[][] board) {
        for(char[] row : board){
            for(char col : row){
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    private static char setCompSymbol(char userSym) {
        if(userSym == 'x'){ return 'o'; }
        else {return 'x';}
    }

    private static char setUserSymbol() {
        // Set up scanner
        Scanner keyboard = new Scanner(System.in);

        // Get user letter - keep going until a real letter is input
        while(true){
            // Ask user for letter
            System.out.println("Would you like to play as X or O?");

            try{
                char input = keyboard.next().charAt(0);

                // Check if it's X or O
                if(input == 'X' || input == 'O'){
                    // Good input - close scanner and return sybmol
                    System.out.println("User symbol set to " + input);
                    keyboard.close();
                    return input;
                } else {
                    // Incorrect char input
                    System.out.println("Invalid input, please try again");
                }

            } catch(InputMismatchException e) {
                // Not char input
                System.out.println("Invalid input, please try again");
            }
        }

    }
}
