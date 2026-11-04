import java.util.List;

/**
 * StudentDAO.java
 * Interface defining the contract for Student database operations.
 * Demonstrates Abstraction - callers depend on this interface,
 * not on how the operations are actually implemented (JDBC/SQL).
 */
public interface StudentDAO {

    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);

    Student getStudentById(int id);

    List<Student> getAllStudents();

    List<Student> searchStudentsByName(String name);
}
