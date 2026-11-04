import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAOImpl.java
 * Concrete implementation of StudentDAO using JDBC + MySQL.
 * Demonstrates Polymorphism - this class "is-a" StudentDAO and
 * can be swapped out for another implementation without changing
 * the rest of the application.
 *
 * All queries use PreparedStatement to prevent SQL injection.
 */
public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, age, course, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, course = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());
            ps.setInt(6, student.getId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToStudent(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching student: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                students.add(mapRowToStudent(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(mapRowToStudent(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error searching students: " + e.getMessage());
        }
        return students;
    }

    // Helper method to map a ResultSet row to a Student object
    private Student mapRowToStudent(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("course"),
                rs.getString("email"),
                rs.getString("phone")
        );
    }
}
