import additionals.MyBusinessException;
import additionals.TestConnection;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import dao.EmployeeDAO;
import model.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    private  static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static String DB_URL = "jdbc:mysql://localhost:3306/crm?useSSL=false&characterEncoding=utf8";
    private static String DB_USER = "root";
    private static String DB_PASS = "coderslab";
    static final Logger LOG = LoggerFactory.getLogger(Test.class);

    public static Connection getConnection()throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        return connection;
    }
    public static void main(String[] args) throws MySQLIntegrityConstraintViolationException {
        LocalDate localDate2 = LocalDate.parse("20200102", DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(localDate2);


        try (Connection connection = TestConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO employees (id ) VALUES (1)");
            statement.executeUpdate();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = new Employee.Builder("mich", "2",3).build();
            employee.setId(2);
            employeeDAO.createEmployee(employee);

        }catch (MySQLIntegrityConstraintViolationException e){
            LOG.error("Duplicate Entry 1");
//            e.getMessage().substring(e.getMessage().indexOf("for key") + 9, e.getMessage().length() - 1)
        }catch (SQLException e){
            if(e.getMessage().contains("MySQLIntegrityConstraintViolationException")){
                throw new MySQLIntegrityConstraintViolationException("PRIMARY");
            }
            LOG.error("Testowe połączenie z logiem");
            LOG.error(e.getMessage().substring(e.getMessage().indexOf("for key") + 9, e.getMessage().length() - 1));
            System.out.println("1");
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());

            e.printStackTrace();
        } catch (MyBusinessException e) {
            e.printStackTrace();
        }


//        employeeDAO.createEmployee(employee);
    }
}


/*
Przycisk głowny nawiguje do menu
globalna inicjacja logera

SPRÓBOWAĆ:
CREATA CAR co wyrzuca Object.requireNonNull()
CZY W ORDER DAO dają carId czy carIdNumber jako referencje
* */