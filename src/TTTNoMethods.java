import java.util.Scanner;

public class TTTNoMethods {
    public static void main(String[] args) {

        // Set up scanner
        Scanner keyboard = new Scanner(System.in);

        // Make variables
        char userSym;
        int userNum;
        char compSym;
        boolean playAgain = true;
        int[] moveAttempt = new int[2];
        boolean compWent;
        int[] WLD = new int[3];

        // PLAY AGAIN LOOP
        do {int moves = 0;
            boolean won = false;

            // SET UP SYMBOLS
            while (true) {
                // Ask user for letter
                System.out.println("Would you like to play as X or O?");

                // Get input
                char input = keyboard.next().charAt(0);

                // Check if it's X or O
                if (input == 'X' || input == 'O') {
                    // Good input
                    System.out.println("User symbol set to " + input);
                    userSym = input;

                    // Check and set computer symbol
                    if (userSym == 'X') {
                        userNum = 0;
                        compSym = 'O';
                    } else {
                        userNum = 1;
                        compSym = 'X';
                    }
                    break;

                } else {
                    // Incorrect char input
                    System.out.println("Invalid input, please try again");
                }
            }

            // Make board
            char[][] board = new char[3][3];
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    board[i][j] = 'N';
                }
            }

            // GAME LOOP
            while (moves < 9) {

                // IF IT'S THE USER'S TURN
                if (moves % 2 == userNum) {

                    // PRINT BOARD
                    for (char[] row : board) {
                        for (char col : row) {
                            if (col == 'N') {
                                System.out.print("- ");
                            } else {
                                System.out.print(col + " ");
                            }
                        }
                        System.out.println();
                    }
                    System.out.println();

                    // GET USER MOVE AND VALIDATE
                    while (true) {
                        System.out.println("Please enter a row");
                        moveAttempt[0] = keyboard.nextInt();

                        System.out.println("Please enter a column");
                        moveAttempt[1] = keyboard.nextInt();

                        moveAttempt[0]--;
                        moveAttempt[1]--;

                        if (moveAttempt[0] >= 3 && moveAttempt[1] >= 3) {
                            System.out.println("Sorry, invalid input.");
                            continue;
                        }

                        if (board[moveAttempt[0]][moveAttempt[1]] != 'N') {
                            System.out.println("Sorry, invalid input.");
                            continue;
                        }

                        break;
                    }

                    // DO MOVE AND INCREASE MOVE COUNT
                    board[moveAttempt[0]][moveAttempt[1]] = userSym;
                    moves++;

                    // PRINT BOARD
                    for (char[] row : board) {
                        for (char col : row) {
                            if (col == 'N') {
                                System.out.print("- ");
                            } else {
                                System.out.print(col + " ");
                            }
                        }
                        System.out.println();
                    }
                    System.out.println();

                    // CHECK FOR WIN
                    // rows
                    boolean rowWin = false;
                    for (char[] row : board) {
                        if (row[0] == 'N') {                       // If first element is blank, skip
                            continue;
                        }
                        else if ((row[0] == row[1]) && (row[1] == row[2])) {
                            rowWin = true;
                            break;
                        }
                    }

                    if (rowWin) {
                        // User won
                        System.out.println("Congratulations!");
                        WLD[0]++;
                        won = true;
                        break;
                    }

                    // cols
                    boolean colWin = false;
                    for(int i = 0; i < board.length; i++) {
                        if (board[0][i] == 'N') {                   // If first element is blank, skip
                            continue;
                        } else if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) {
                            colWin = true;
                            break;
                        }
                    }

                    if(colWin){
                        // User won
                        System.out.println("Congratulations!");
                        WLD[0]++;
                        won = true;
                        break;
                    }

                    // diag1
                    boolean diag1Win = false;
                    if((board[0][0] != 'N') && (board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) {
                        diag1Win = true;
                    }

                    if(diag1Win){
                        // User won
                        System.out.println("Congratulations!");
                        WLD[0]++;
                        won = true;
                        break;
                    }

                    // diag2
                    boolean diag2Win = false;
                    if((board[0][2] != 'N') && (board[0][2] == board[1][1]) && (board[1][1] == board[2][0])) {
                        diag2Win = true;
                    }

                    if(diag2Win){
                        // User won
                        System.out.println("Congratulations!");
                        WLD[0]++;
                        won = true;
                        break;
                    }
                }
                // IF IT'S THE COMPUTER'S TURN
                else {
                    // Reset check boolean
                    compWent = false;
                    System.out.println("Computer's turn");

                    // If board is empty, go to middle
                    // Check if empty
                    boolean empty = true;
                    for (char[] row : board) {
                        for (char letter : row) {
                            if (letter == 'X' || letter == 'O') {
                                empty = false;
                                break;
                            }
                        }
                    }

                    // If empty, go for middle
                    if (empty) {
                        board[1][1] = compSym;
                        moves++;
                        compWent = true;
                        System.out.println("Option 1");
                    }

                    // If not, then if 2 of computer's pieces in a row and third spot is empty, go for third
                    // Check rows
                    if (!compWent) {
                        for (char[] row : board) {
                            if (row[0] == compSym && row[1] == compSym && row[2] == 'N') {
                                row[2] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 2");
                                break;
                            } else if (row[0] == compSym && row[2] == compSym && row[1] == 'N') {
                                row[1] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 2");
                                break;
                            } else if (row[1] == compSym && row[2] == compSym && row[0] == 'N') {
                                row[0] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 2");
                                break;
                            }
                        }
                    }

                    // Check columns
                    if (!compWent) {
                        for (int i = 0; i < 3; i++) {
                            if (board[0][i] == compSym && board[1][i] == compSym && board[2][i] == 'N') {
                                board[2][i] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 2");
                                break;
                            } else if (board[0][i] == compSym && board[2][i] == compSym && board[1][i] == 'N') {
                                board[1][i] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 2");
                                break;
                            } else if (board[1][i] == compSym && board[2][i] == compSym && board[0][i] == 'N') {
                                board[0][i] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 2");
                                break;
                            }
                        }
                    }

                    // Check diag1
                    if (!compWent) {
                        if (board[0][0] == compSym && board[1][1] == compSym && board[2][2] == 'N') {
                            board[2][2] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 2");
                        } else if (board[0][0] == compSym && board[2][2] == compSym && board[1][1] == 'N') {
                            board[1][1] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 2");
                        } else if (board[1][1] == compSym && board[2][2] == compSym && board[0][0] == 'N') {
                            board[0][0] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 2");
                        }
                    }

                    // Check diag2
                    if (!compWent) {
                        if (board[0][2] == compSym && board[1][1] == compSym && board[2][0] == 'N') {
                            board[2][0] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 2");
                        } else if (board[0][2] == compSym && board[2][0] == compSym && board[1][1] == 'N') {
                            board[1][1] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 2");
                        } else if (board[1][1] == compSym && board[2][0] == compSym && board[0][2] == 'N') {
                            board[0][2] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 2");
                        }
                    }

                    // If not, then if 2 of user's pieces in a row and third spot is empty, go for third
                    // Check rows
                    if (!compWent) {
                        for (char[] row : board) {
                            if (row[0] == userSym && row[1] == userSym && row[2] == 'N') {
                                row[2] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 3");
                                break;
                            } else if (row[0] == userSym && row[2] == userSym && row[1] == 'N') {
                                row[1] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 3");
                                break;
                            } else if (row[1] == userSym && row[2] == userSym && row[0] == 'N') {
                                row[0] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 3");
                                break;
                            }
                        }
                    }

                    // Check columns
                    if (!compWent) {
                        for (int i = 0; i < 3; i++) {
                            if (board[0][i] == userSym && board[1][i] == userSym && board[2][i] == 'N') {
                                board[2][i] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 3");
                                break;
                            } else if (board[0][i] == userSym && board[2][i] == userSym && board[1][i] == 'N') {
                                board[1][i] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 3");
                                break;
                            } else if (board[1][i] == userSym && board[2][i] == userSym && board[0][i] == 'N') {
                                board[0][i] = compSym;
                                compWent = true;
                                moves++;
                                System.out.println("Option 3");
                                break;
                            }
                        }
                    }

                    // Check diag1
                    if (!compWent) {
                        if (board[0][0] == userSym && board[1][1] == userSym && board[2][2] == 'N') {
                            board[2][2] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 3");
                        } else if (board[0][0] == userSym && board[2][2] == userSym && board[1][1] == 'N') {
                            board[1][1] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 3");
                        } else if (board[1][1] == userSym && board[2][2] == userSym && board[0][0] == 'N') {
                            board[0][0] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 3");
                        }
                    }

                    // Check diag2
                    if (!compWent) {
                        if (board[0][2] == userSym && board[1][1] == userSym && board[2][0] == 'N') {
                            board[2][0] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 3");
                        } else if (board[0][2] == userSym && board[2][0] == userSym && board[1][1] == 'N') {
                            board[1][1] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 3");
                        } else if (board[1][1] == userSym && board[2][0] == userSym && board[0][2] == 'N') {
                            board[0][2] = compSym;
                            compWent = true;
                            moves++;
                            System.out.println("Option 3");
                        }
                    }

                    // If not, just go in first empty spot
                    if (!compWent) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                    if (board[i][j] == 'N') {
                                        board[i][j] = compSym;
                                        compWent = true;
                                        moves++;
                                        System.out.println("Option 4");
                                        break;
                                    }
                                }
                            if(compWent){
                                break;
                            }
                        }
                    }

                    // CHECK FOR WIN
                    // rows
                    boolean rowWin = false;
                    for (char[] row : board) {
                        if (row[0] == 'N') {                       // If first element is blank, skip
                            continue;
                        }
                        else if ((row[0] == row[1]) && (row[1] == row[2])) {
                            rowWin = true;
                            break;
                        }
                    }

                    if (rowWin) {
                        // Computer won
                        System.out.println("Better luck next time :(");
                        WLD[1]++;
                        won = true;
                        // PRINT BOARD
                        for (char[] row : board) {
                            for (char col : row) {
                                if (col == 'N') {
                                    System.out.print("- ");
                                } else {
                                    System.out.print(col + " ");
                                }
                            }
                            System.out.println();
                        }
                        System.out.println();
                        break;
                    }

                    // cols
                    boolean colWin = false;
                    for(int i = 0; i < board.length; i++) {
                        if (board[0][i] == 'N') {                   // If first element is blank, skip
                            continue;
                        } else if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) {
                            colWin = true;
                            break;
                        }
                    }

                    if(colWin){
                        // Computer won
                        System.out.println("Better luck next time :(");
                        WLD[1]++;
                        won = true;
                        // PRINT BOARD
                        for (char[] row : board) {
                            for (char col : row) {
                                if (col == 'N') {
                                    System.out.print("- ");
                                } else {
                                    System.out.print(col + " ");
                                }
                            }
                            System.out.println();
                        }
                        System.out.println();
                        break;
                    }

                    // diag1
                    boolean diag1Win = false;
                    if((board[0][0] != 'N') && (board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) {
                        diag1Win = true;
                    }

                    if(diag1Win){
                        // Computer won
                        System.out.println("Better luck next time :(");
                        WLD[1]++;
                        won = true;
                        // PRINT BOARD
                        for (char[] row : board) {
                            for (char col : row) {
                                if (col == 'N') {
                                    System.out.print("- ");
                                } else {
                                    System.out.print(col + " ");
                                }
                            }
                            System.out.println();
                        }
                        System.out.println();
                        break;
                    }

                    // diag2
                    boolean diag2Win = false;
                    if((board[0][2] != 'N') && (board[0][2] == board[1][1]) && (board[1][1] == board[2][0])) {
                        diag2Win = true;
                    }

                    if(diag2Win){
                        // Computer won
                        System.out.println("Better luck next time :(");
                        WLD[1]++;
                        won = true;
                        // PRINT BOARD
                        for (char[] row : board) {
                            for (char col : row) {
                                if (col == 'N') {
                                    System.out.print("- ");
                                } else {
                                    System.out.print(col + " ");
                                }
                            }
                            System.out.println();
                        }
                        System.out.println();
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
            while (true) {
                // Ask user for letter
                System.out.println("Would you like to play again? Y or N");

                // Get input
                char input = keyboard.next().charAt(0);

                // Check input
                if (input == 'Y') {
                    break;
                }
                else if (input == 'N') {
                    playAgain = false;
                    break;
                }
                else {
                    // Incorrect char input
                    System.out.println("Invalid input, please try again");
                }
            }

        } while (playAgain);

        // Show stats
        System.out.println("Your stats:");
        System.out.println("Wins: " + WLD[0]);
        System.out.println("Losses: " + WLD[1]);
        System.out.println("Draws: " + WLD[2]);

        keyboard.close();
    }
}
