import java.util.HashMap;
import java.util.Scanner;

class HashMapExample {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        
        HashMap<Integer, String> students = new HashMap<>();

       
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

       
        for (int i = 0; i < n; i++) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            students.put(id, name);
        }

       
        System.out.print("\nEnter Student ID to search: ");
        int searchId = sc.nextInt();

        if (students.containsKey(searchId)) {
            System.out.println("Student Name: " + students.get(searchId));
        } else {
            System.out.println("Student ID not found.");
        }

        sc.close();
    }
}