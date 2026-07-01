import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class StudentDAO {

    static final String url = "jdbc:mysql://localhost:3306/studentdb";
    static final String user = "root";
    static final String password = "root";


    void insertStudent(int id, String name, int age) {

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "INSERT INTO students VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);

            ps.executeUpdate();

            System.out.println("Record Inserted");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    void updateStudent(int id, String newName) {

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "UPDATE students SET name=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, newName);
            ps.setInt(2, id);

            ps.executeUpdate();

            System.out.println("Record Updated");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        dao.insertStudent(3, "Arun", 22);

        dao.updateStudent(3, "Kiran");
    }
}