
public class MVCTest {
    public static void main(String[] args) {
        System.out.println("=== MVC Pattern Test ===");

        Student student = new Student("S101", "Alice Johnson", "A");

  
        StudentView studentView = new StudentView();

        
        StudentController controller = new StudentController(student, studentView);

        System.out.println("Initial record:");
        controller.updateView();

        controller.setStudentName("Alice M. Johnson");
        controller.setStudentGrade("A+");

        System.out.println("\nRecord after update:");
        controller.updateView();
    }
}
