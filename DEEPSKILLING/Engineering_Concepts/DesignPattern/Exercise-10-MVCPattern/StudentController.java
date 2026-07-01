
public class StudentController {
    private Student student;
    private final StudentView studentView;

    public StudentController(Student student, StudentView studentView) {
        this.student = student;
        this.studentView = studentView;
    }

    public void setStudentName(String name) {
        student.setName(name);
    }

    public void setStudentId(String id) {
        student.setId(id);
    }

    public void setStudentGrade(String grade) {
        student.setGrade(grade);
    }

    public String getStudentName() {
        return student.getName();
    }

    public String getStudentId() {
        return student.getId();
    }

    public String getStudentGrade() {
        return student.getGrade();
    }

    /**
     * Asks the view to display the current state of the model.
     */
    public void updateView() {
        studentView.displayStudentDetails(
                student.getName(), student.getId(), student.getGrade());
    }
}
