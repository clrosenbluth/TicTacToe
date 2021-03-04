import java.util.Scanner;

public class TTTWithMethods {
    public static void main(String[] args){
        // General initializations
        Scanner keyboard = new Scanner(System.in);
        char userSym;
        int userNum;
        char compSym;
        boolean playAgain;
        int[] moveAttempt = new int[2];
        int[] WLD = new int[3];

        // Do-while loop to play again
        do
        {
            // Re-initialized each time
            int moves = 0;
            boolean won = false;

            // Methods to set symbols and user number
            userSym = setUserSym(keyboard);
            userNum = setUserNum(userSym);
            compSym = setCompSym(userSym);
            
            // Make/reset board
            char[][] board = makeBoard();
            
            // Play game
            while(moves < 9)
            {
                if (moves % 2 == userNum)
                {   // User's turn
                    // Print board
                    printBoard(board);

                    // Get, validate, make move
                    doUserMove(keyboard, moveAttempt, board, userSym);
                    moves++;

                    // Print board
                    printBoard(board);

                    // Check for win
                    if (winCheck(board))
                    {
                        System.out.println("Congratulations!");
                        WLD[0]++;
                        won = true;
                        break;
                    }
                }
                else
                {   // Computer's turn
                    System.out.println("Computer's turn");

                    // Computer move
                    doCompMove(board, compSym, userSym);
                    moves++;

                    // Check for win
                    if (winCheck(board))
                    {
                        System.out.println("Better luck next time :(");
                        WLD[1]++;
                        won = true;
                        break;
                    }
                }
            }

            // Check for draw
            if(!won) {
                System.out.println("Ended in a draw");
                WLD[2]++;
            }

            // Check if playing again
            playAgain = checkPlayAgain(keyboard);
        } 
        while(playAgain);

        // Game end
        showStats(WLD);

        keyboard.close();
    }

    private static void showStats(int[] WLD) {
        // Show stats
        System.out.println("Your stats:");
        System.out.println("Wins: " + WLD[0]);
        System.out.println("Losses: " + WLD[1]);
        System.out.println("Draws: " + WLD[2]);
    }

    private static boolean checkPlayAgain(Scanner keyboard) {
        while (true)
        {
            // Ask user for letter
            System.out.println("Would you like to play again? Y or N");

            // Get input
            char input = keyboard.next().charAt(0);

            // Check input
            if (input == 'Y')
            {
                return true;
            }
            else if (input == 'N')
            {
                return false;
            }
            else
            {
                // Incorrect char input
                System.out.println("Invalid input, please try again");
            }
        }
    }

    private static void doCompMove(char[][] board, char compSym, char userSym) {
        // If board is empty, go to middle
        // Check if empty
        boolean empty = true;
        for (char[] row : board)
        {
            for (char letter : row)
            {
                if (letter == 'X' || letter == 'O')
                {
                    empty = false;
                    break;
                }
            }
        }

        // If empty, go for middle
        if (empty)
        {
            board[1][1] = compSym;
            return;
        }

        // If not, then if 2 of computer's pieces in a row and third spot is empty, go for third
        // Check rows
        for (char[] row : board)
        {
            if (row[0] == compSym && row[1] == compSym && row[2] == 'N')
            {
                row[2] = compSym;
                return;
            }
            else if (row[0] == compSym && row[2] == compSym && row[1] == 'N')
            {
                row[1] = compSym;
                return;
            }
            else if (row[1] == compSym && row[2] == compSym && row[0] == 'N')
            {
                row[0] = compSym;
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++)
        {
            if (board[0][i] == compSym && board[1][i] == compSym && board[2][i] == 'N')
            {
                board[2][i] = compSym;
                return;
            }
            else if (board[0][i] == compSym && board[2][i] == compSym && board[1][i] == 'N')
            {
                board[1][i] = compSym;
                return;
            }
            else if (board[1][i] == compSym && board[2][i] == compSym && board[0][i] == 'N')
            {
                board[0][i] = compSym;
                return;
            }
        }

        // Check diag1
        if (board[0][0] == compSym && board[1][1] == compSym && board[2][2] == 'N')
        {
            board[2][2] = compSym;
            return;
        }
        else if (board[0][0] == compSym && board[2][2] == compSym && board[1][1] == 'N')
        {
            board[1][1] = compSym;
            return;
        }
        else if (board[1][1] == compSym && board[2][2] == compSym && board[0][0] == 'N')
        {
            board[0][0] = compSym;
            return;
        }

        // Check diag2
        if (board[0][2] == compSym && board[1][1] == compSym && board[2][0] == 'N')
        {
            board[2][0] = compSym;
            return;
        }
        else if (board[0][2] == compSym && board[2][0] == compSym && board[1][1] == 'N')
        {
            board[1][1] = compSym;
            return;
        }
        else if (board[1][1] == compSym && board[2][0] == compSym && board[0][2] == 'N')
        {
            board[0][2] = compSym;
            return;
        }

        // If not, then if 2 of user's pieces in a row and third spot is empty, go for third
        // Check rows
        for (char[] row : board)
        {
            if (row[0] == userSym && row[1] == userSym && row[2] == 'N')
            {
                row[2] = compSym;
                return;
            }
            else if (row[0] == userSym && row[2] == userSym && row[1] == 'N')
            {
                row[1] = compSym;
                return;
            }
            else if (row[1] == userSym && row[2] == userSym && row[0] == 'N')
            {
                row[0] = compSym;
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++)
        {
            if (board[0][i] == userSym && board[1][i] == userSym && board[2][i] == 'N')
            {
                board[2][i] = compSym;
                return;
            }
            else if (board[0][i] == userSym && board[2][i] == userSym && board[1][i] == 'N')
            {
                board[1][i] = compSym;
                return;
            }
            else if (board[1][i] == userSym && board[2][i] == userSym && board[0][i] == 'N')
            {
                board[0][i] = compSym;
                return;
            }
        }

        // Check diag1
        if (board[0][0] == userSym && board[1][1] == userSym && board[2][2] == 'N')
        {
            board[2][2] = compSym;
            return;
        }
        else if (board[0][0] == userSym && board[2][2] == userSym && board[1][1] == 'N')
        {
            board[1][1] = compSym;
            return;
        }
        else if (board[1][1] == userSym && board[2][2] == userSym && board[0][0] == 'N')
        {
            board[0][0] = compSym;
            return;
        }

        // Check diag2
        if (board[0][2] == userSym && board[1][1] == userSym && board[2][0] == 'N')
        {
            board[2][0] = compSym;
            return;
        }
        else if (board[0][2] == userSym && board[2][0] == userSym && board[1][1] == 'N')
        {
            board[1][1] = compSym;
            return;
        }
        else if (board[1][1] == userSym && board[2][0] == userSym && board[0][2] == 'N')
        {
            board[0][2] = compSym;
            return;
        }

        // If not, just go in first empty spot
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == 'N')
                {
                    board[i][j] = compSym;
                    return;
                }
            }
        }
    }

    private static boolean winCheck(char[][] board) {
        // rows
        for (char[] row : board)
        {
            if ((row[0] != 'N') && (row[0] == row[1]) && (row[1] == row[2])) {
                return true;
            }
        }

        // cols
        for(int i = 0; i < board.length; i++)
        {
            if ((board[0][i] != 'N') && (board[0][i] == board[1][i]) && (board[1][i] == board[2][i]))
            {
                return true;
            }
        }

        // diag1
        if((board[0][0] != 'N') && (board[0][0] == board[1][1]) && (board[1][1] == board[2][2]))
        {
            return true;
        }

        // diag2
        return (board[0][2] != 'N') && (board[0][2] == board[1][1]) && (board[1][1] == board[2][0]);
    }

    private static void doUserMove(Scanner keyboard, int[] moveAttempt, char[][] board, char userSym) {
        while (true)
        {
            System.out.println("Please enter a row (1-3)");
            moveAttempt[0] = keyboard.nextInt();

            System.out.println("Please enter a column (1-3)");
            moveAttempt[1] = keyboard.nextInt();

            moveAttempt[0]--;
            moveAttempt[1]--;

            if (moveAttempt[0] >= 3 || moveAttempt[1] >= 3)
            {
                System.out.println("Sorry, invalid input.");
                continue;
            }

            if (board[moveAttempt[0]][moveAttempt[1]] != 'N')
            {
                System.out.println("Sorry, invalid input.");
                continue;
            }

            break;
        }

        // DO MOVE
        board[moveAttempt[0]][moveAttempt[1]] = userSym;
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board)
        {
            for (char col : row)
            {
                if (col == 'N')
                {
                    System.out.print("- ");
                }
                else
                {
                    System.out.print(col + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static char[][] makeBoard() {
        char[][] temp = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                temp[i][j] = 'N';
            }
        }
        return temp;
    }

    private static char setCompSym(char userSym) {
        if (userSym == 'X') 
        {
            return 'O';
        } 
        else 
        {
            return 'X';
        }
    }

    private static int setUserNum(char userSym) {
        if (userSym == 'X') 
        {
            return 0;
        } 
        else 
        {
            return 1;
        }
    }

    private static char setUserSym(Scanner keyboard) {
        char input;
        while (true) 
        {
            // Ask user for letter
            System.out.println("Would you like to play as X or O?");

            // Get input
            input = keyboard.next().charAt(0);

            // Check if it's X or O
            if (input == 'X' || input == 'O') 
            {
                // Good input
                System.out.println("User symbol set to " + input);
                break;
            } 
            else 
            {
                // Incorrect char input
                System.out.println("Invalid input, please try again");
            }
        }
        return input;
    }
}
