import java.util.ArrayList;
import java.util.Scanner;

class ArrayListExample {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

       
        ArrayList<String> names = new ArrayList<>();

       
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

       
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name " + (i + 1) + ": ");
            String name = sc.nextLine();
            names.add(name);
        }

        
        System.out.println("\nStudent Names:");

        for (String name : names) {
            System.out.println(name);
        }

        sc.close();
    }
}