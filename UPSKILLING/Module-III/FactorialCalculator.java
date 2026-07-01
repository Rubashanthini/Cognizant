import java.util.Scanner;

class FactorialCalculator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter a non-negative integer: ");
        int num = sc.nextInt();

        long factorial = 1;

        for (int i = 1; i <= num; i++) {
            factorial = factorial * i;
        }

        
        System.out.println("Factorial of " + num + " = " + factorial);

        sc.close();
    }
}