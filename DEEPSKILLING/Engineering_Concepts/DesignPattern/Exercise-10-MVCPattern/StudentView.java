
public class StudentView {
    /**
     * Displays the given student's details to the console.
     *
     * @param name  the student's name
     * @param id    the student's ID
     * @param grade the student's grade
     */
    public void displayStudentDetails(String name, String id, String grade) {
        System.out.println("---- Student Record ----");
        System.out.println("ID    : " + id);
        System.out.println("Name  : " + name);
        System.out.println("Grade : " + grade);
        System.out.println("-------------------------");
    }
}
