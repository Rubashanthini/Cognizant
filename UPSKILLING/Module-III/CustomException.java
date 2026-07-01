import java.util.Scanner;

class InvalidAgeException extends Exception {

    InvalidAgeException(String message) {
        super(message);
    }
}

class CustomException {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            
            System.out.print("Enter your age: ");
            int age = sc.nextInt();

            
            if (age < 18) {
                throw new InvalidAgeException("Age must be 18 or above.");
            }

            System.out.println("Access Granted.");

        } 
        catch (InvalidAgeException e) {

            
            System.out.println("Exception: " + e.getMessage());
        }

        sc.close();
    }
}