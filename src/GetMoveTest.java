import java.util.Scanner;

public class GetMoveTest {
   public static void main(String[] args){
       // Set up scanner
       Scanner keyboard = new Scanner(System.in);

       // Set up variables
       int row;
       int col;

       System.out.println("Please enter a row");
       row = keyboard.nextInt();

       System.out.println("Please enter a column");
       col = keyboard.nextInt();

       System.out.print(row +", " + col);

       keyboard.close();
   }
}
