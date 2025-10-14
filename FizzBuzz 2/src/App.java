import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner console = new Scanner(System.in);

        // Input
        System.out.println("Escribe un número y presiona ENTER para generar FizzBuzz hasta ese numero: ");
        int count = console.nextInt();        

        // Processing & Output
        System.out.println("Classic");
        FizzBuzzClassic(count);

        System.out.println("Faster");
        FizzBuzzFaster(count);

        System.out.println("Max");
        String maxResult = FizzBuzzMax(count);
        System.out.println(maxResult);

        System.out.println("Ultra");
        String ultraResult = FizzBuzzUltra(count);
        System.out.println(ultraResult);

        console.close();
    }

    public static void FizzBuzzClassic(int count) {
        for (int i = 1; i <= count; i++) {

            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FIZZBUZZ");
            }
            else if (i % 5 == 0) {
                System.out.println("BUZZ");  
            }
            else if (i % 3 == 0) {
                System.out.println("FIZZ");  
            }
            else {
                System.out.println(i);
            }
         }
    }

    public static void FizzBuzzFaster(int count) {
        StringBuilder result = new StringBuilder();     

        for (int i = 1; i <= count; i++) {

            if (i % 3 == 0 && i % 5 == 0) {
                result.append("FIZZBUZZ");
            }
            else if (i % 5 == 0) {
                result.append("BUZZ");
            }
            else if (i % 3 == 0) {
                result.append("FIZZ");
            }
            else {
                result.append(i);
            }

            result.append("\n");
         }

         System.out.print(result.toString());
    }

    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";
    private static final String FIZZBUZZ = "FizzBuzz";

    public static String FizzBuzzMax(int count) {
        StringBuilder result = new StringBuilder();     

        for (int i = 1; i <= count; i++) {

            if (i % 15 == 0) {
                result.append(FIZZBUZZ);
            }
            else if (i % 5 == 0) {
                result.append(BUZZ);
            }
            else if (i % 3 == 0) {
                result.append(FIZZ);
            }
            else {
                result.append(i);
            }

            result.append("\n");
         }

         return result.toString();
    }

    public static String FizzBuzzUltra(int count) {
        StringBuilder result = new StringBuilder();     
        int fizz = 0;
        int buzz = 0;

        for (int i = 1; i <= count; i++) {
            fizz++;
            buzz++;
            
            boolean isFizz = fizz == 3;
            boolean isBuzz = buzz == 5;
            
            if (isFizz && isBuzz) {
                result.append(FIZZBUZZ);
                fizz = 0;
                buzz = 0;
            } else if (isFizz) {
                result.append(FIZZ);
                fizz = 0;
            } else if (isBuzz) {
                result.append(BUZZ);
                buzz = 0;
            } else {
                result.append(i);
            }
            
            if (i < count) {
                result.append('\n');
            }
        }
        
        return result.toString();
    }
}

