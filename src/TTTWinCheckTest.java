public class TTTWinCheckTest {
    public static void main(String[] args) {
        // Test board
        char[][] board = {{'N', 'N', 'X'}, {'N', 'X', 'N'}, {'X', 'N', 'N'}};

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
            System.out.println("row win");
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
            System.out.println("col win");
        }

        // diag1
        boolean diag1Win = false;
        if((board[0][0] != 'N') && (board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) {
            diag1Win = true;
        }

        if(diag1Win){
            // User won
            System.out.println("diag 1 win");
        }

        // diag2
        boolean diag2Win = false;
        if((board[0][2] != 'N') && (board[0][2] == board[1][1]) && (board[1][1] == board[2][0])) {
            diag2Win = true;
        }

        if(diag2Win){
            // User won
            System.out.println("diag 2 win");
        }
    }
}
