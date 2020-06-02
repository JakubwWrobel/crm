package dao;


import additionals.DbUtil;
import additionals.MyBusinessException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import model.Employee;
import model.WorkingHoursModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {
    private static final String CREATEEMPLOYEE = "INSERT INTO employees (firstName, lastName, address, telNumber, note, hourlyCost) VALUES (?,?,?,?,?,?)";
    private static final String SHOW_ALL_EMPLOYEES = "SELECT * FROM employees";
    private static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id = ?";
    private static final String UPDATE_EMPLOYEE_BY_ID = "UPDATE employees SET firstName = ?, lastName = ?, address = ?, telNumber = ?, note = ?, hourlyCost = ? WHERE id = ?";
    private static final String DELETE_EMPLOYEE_BY_ID = "DELETE FROM employees WHERE id = ?";
    private static final String PUT_ALL_ORDERS_TO_FINISHED = "UPDATE orders SET status = 'DONE', employeeAssigned = 1 WHERE employeeAssigned = ?";
    private static final String WORKING_HOURS_EMPLOYEES = "SELECT firstName, lastName, hourlyCost, SUM(o.workHours) AS sum, hourlyCost * SUM(o.workHours) AS total_cost FROM orders o JOIN employees e ON o.employeeAssigned = e.id WHERE o.dateStartRepair > ? GROUP BY firstName, lastName, hourlyCost";
    private static PreparedStatement statement;
    private static List<Employee> employees = new ArrayList<>();
    private static List<WorkingHoursModel> workingHoursModels = new ArrayList<>();


    public boolean createEmployee(Employee employee) throws MyBusinessException {
        try (Connection connection = DbUtil.getConnection()) {
            statement = connection.prepareStatement(CREATEEMPLOYEE, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getAddress());
            //check if correct
            statement.setInt(4, employee.getTelNumber());
            statement.setString(5, employee.getNote());
            statement.setLong(6, employee.getHourlyCost());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
            }
            return true;

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                throw new MyBusinessException(e.getMessage(), e.initCause(new MySQLIntegrityConstraintViolationException()));
            }
            e.printStackTrace();
            return false;
        }

    }

    public static List<Employee> allEmployees() {
        try (Connection connection = DbUtil.getConnection()) {
            employees.clear();
            statement = connection.prepareStatement(SHOW_ALL_EMPLOYEES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee.Builder(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getInt("hourlyCost")).address(resultSet.getString("address")).note(resultSet.getString("note")).telNumber(resultSet.getInt("telNumber")).build();
                employee.setId(resultSet.getInt("id"));
                employees.add(employee);
            }
            return employees;

        } catch (SQLException e) {
            System.out.println("Błąd połączenia a bazą");
            e.printStackTrace();
            return null;
        }

    }

    public static List<Employee> read(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            employees.clear();
            statement = connection.prepareStatement(FIND_EMPLOYEE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Employee employee = new Employee.Builder(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getInt("hourlyCost")).address(resultSet.getString("address")).note(resultSet.getString("note")).telNumber(resultSet.getInt("telNumber")).build();
                employee.setId(resultSet.getInt("id"));
                employees.add(employee);
                return employees;
            }

        } catch (SQLException e) {
            e.getCause();
            e.getMessage();
            System.out.println("Błąd połączenia z bazą");
        }
        return null;
    }


    public boolean updateEmployee(Employee employee) throws MyBusinessException {
        try (Connection connection = DbUtil.getConnection()) {
            statement = connection.prepareStatement(UPDATE_EMPLOYEE_BY_ID);

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getAddress());
            //check if correct
            statement.setInt(4, employee.getTelNumber());
            statement.setString(5, employee.getNote());
            statement.setLong(6, employee.getHourlyCost());
            statement.setInt(7, employee.getId());

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                throw new MyBusinessException(e.getMessage(), e.initCause(new MySQLIntegrityConstraintViolationException()));
            }
            e.printStackTrace();
            return false;
        }

    }

    public static boolean deleteEmployee(Employee employee) {
        try (Connection connection = DbUtil.getConnection()) {

            //UPDATE ALL orders TO OWNER(ID=1)
            statement = connection.prepareStatement(PUT_ALL_ORDERS_TO_FINISHED);
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
            //DELETE EMPLOYEE
            statement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID);
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
            return true;

        } catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<WorkingHoursModel> workingHours(LocalDate str) {
        try (Connection connection = DbUtil.getConnection()) {
            Date dateFrom = Date.valueOf(str);
            statement = connection.prepareStatement(WORKING_HOURS_EMPLOYEES);
            statement.setDate(1, dateFrom);
            ResultSet resultSet = statement.executeQuery();
            workingHoursModels.clear();
            while (resultSet.next()) {
                WorkingHoursModel workingHoursModel = new WorkingHoursModel.Builder(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getDouble("hourlyCost"), resultSet.getDouble("sum"), resultSet.getDouble("total_cost")).build();
                workingHoursModels.add(workingHoursModel);
            }
            return workingHoursModels;

        } catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
