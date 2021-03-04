public class ArrayLoop {
    public static void main(String[] args){
        int[] array1 = new int[26];

        for(int i = 0; i < array1.length; i++){
            array1[i] = 2*i;
            System.out.println(array1[i]);
        }

        String[] fruits = {"apple", "banana", "citrus", "grapes"};

        // ONLY use if array is full
        for(String fruit : fruits){
            System.out.println(fruit);
        }

        String[] books = {"Harry Potter", "Lord of the Rings", "Ender's Game", "Pride and Prejudice"};

        for(String book : books){
            System.out.println(book);
        }

        int[] numbers = new int[10];
        for(int i = 0; i < numbers.length; i++){
            numbers[i] = i*3;
            if(numbers[i] % 2 == 0){
                System.out.println("Even");
            } else {
                System.out.println("Odd");
            }
        }

        char[][] letters = {{'a','b','c'}, {'d','e','f'}, {'g','h','i'}};
        for(int i = 0; i < letters[0].length; i++){
            for(char[] letter : letters) {
                System.out.print(letter[i] + " ");
            }
            System.out.println();
        }
    }
}
