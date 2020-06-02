package dao;


import additionals.DbUtil;
import additionals.MyBusinessException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import model.Car;
import model.Employee;
import model.Order;
import model.Status;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final String CREATE_ORDER = "insert into orders (dateReceived, plannedDateStartRepair, employeeAssigned,costOfHour, problemDes,\n" +
            "                    status, car )\n" +
            "values (?,?,?,?,?,?,?);";
    private static final String SHOW_ALL_ORDERS = "SELECT * FROM orders";
    private static final String FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String UPDATE_ORDER = "UPDATE orders SET dateReceived = ?,  plannedDateStartRepair = ?,  dateStartRepair = ?,  employeeAssigned = ?,  problemDes = ?,  resolutionDes = ?,  status= ?, car= ?,  costRepair= ?,  costOfItems= ?,  workHours= ?,  costOfHour= ?  WHERE id = ?";
    private static final String DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id = ?";
    private static PreparedStatement statement;
    private static List<Order> orders = new ArrayList<>();
    private static List<Car> cars = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static Date dateReceived;
    private static Date plannedDateStartRepair;
    private static Date dateStartRepair;
    private Employee employee;
    private static LocalDate dateReceivedL;
    private static LocalDate plannedDateStartRepairL;
    private static LocalDate dateStartRepairL;
    private static Status status;
    private static String carS;
    private static String statusS;
    private static Car car;
    private static int employeeId;
    private static double costOfHour;
    private static double costOfItems;
    private static double costRepair;
    private static double workHours;


    public static boolean deleteOrder(Order order) {
        try (Connection connection = DbUtil.getConnection()) {
            statement = connection.prepareStatement(DELETE_ORDER_BY_ID);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
            System.out.println("Błąd połączenia z bazą");
            return false;
        }
    }

    public boolean createOrder(Order order) throws MyBusinessException {
        try (Connection connection = DbUtil.getConnection()) {
            statement = connection.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
//            MAPOWANIE POPRZEZ LOCALDATE
            LocalDate dateReceivedL = order.getDateReceived();
            dateReceived = Date.valueOf(dateReceivedL);
            statement.setDate(1, dateReceived);

            LocalDate plannedDateStartRepairL = order.getPlannedDateStartRepair();
            plannedDateStartRepair = Date.valueOf(plannedDateStartRepairL);
            statement.setDate(2, plannedDateStartRepair);
//          ZROBIĆ CHECKA!!
            employee = order.getEmployeeAssigned();

            statement.setInt(3, employee.getId());
            statement.setLong(4, employee.getHourlyCost());

            statement.setString(5, order.getProblemDes());
//DOUBLE CHECK!!!!!
            String statusS = order.getStatus().toString();
            String status = Status.valueOf(statusS).toString();
            statement.setString(6, status);

            statement.setString(7, order.getCar().getIdNumber());
/*            statement.setLong(9, order.getCostRepair());
            statement.setLong(10, order.getCostOfItems());
            statement.setLong(11, order.getWorkHours());*/

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
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

    public boolean updateOrder(Order order) throws MyBusinessException {
//   (dateReceived, plannedDateStartRepair, dateStartRepair, employeeAssigned, problemDes, resolutionDes, status, car, costRepair, costOfItems, workHours, costOfHour)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DbUtil.getConnection()) {
            statement = connection.prepareStatement(UPDATE_ORDER);
//          MAPOWANIE POPRZEZ LOCALDATE
            LocalDate dateReceivedL = order.getDateReceived();
            dateReceived = Date.valueOf(dateReceivedL);
            statement.setDate(1, dateReceived);

            LocalDate plannedDateStartRepairL = order.getPlannedDateStartRepair();
            plannedDateStartRepair = Date.valueOf(plannedDateStartRepairL);
            statement.setDate(2, plannedDateStartRepair);

            dateStartRepair = (order.getDateStartRepair() == null) ? null : Date.valueOf(order.getDateStartRepair());
            statement.setDate(3, dateStartRepair);

//          ZROBIĆ CHECKA!!
            employee = order.getEmployeeAssigned();

            statement.setInt(4, employee.getId());
            statement.setString(5, order.getProblemDes());
            statement.setString(6, order.getResolutionDes());
            String statusS = order.getStatus().toString();
            String status = Status.valueOf(statusS).toString();
            statement.setString(7, status);
            statement.setString(8, order.getCar().getIdNumber());
            statement.setDouble(9, order.getCostRepair());
            statement.setDouble(10, order.getCostOfItems());
            statement.setDouble(11, order.getWorkHours());
            statement.setLong(12, employee.getHourlyCost());
            statement.setLong(13, order.getId());

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


    public static List<Order> allOrders() {
        try (Connection connection = DbUtil.getConnection()) {
            orders.clear();
            statement = connection.prepareStatement(SHOW_ALL_ORDERS);
            ResultSet resultSet = statement.executeQuery();
            Employee employee;
            while (resultSet.next()) {
                dateReceivedL = resultSet.getObject("dateReceived", LocalDate.class);
                plannedDateStartRepairL = resultSet.getObject("plannedDateStartRepair", LocalDate.class);
                dateStartRepairL = resultSet.getObject("dateStartRepair", LocalDate.class);
                statusS = resultSet.getString("status");
                status = Status.valueOf(statusS);
                carS = resultSet.getString("car");
                cars = CarDAO.readByIdNumber(carS);
                car = cars.get(0);
                employeeId = resultSet.getInt("employeeAssigned");
                employees = EmployeeDAO.read(employeeId);
                employee = employees.get(0);
                costOfHour = resultSet.getDouble("costOfHour");
                costOfItems = resultSet.getDouble("costOfItems");
                workHours = resultSet.getDouble("workHours");
                costRepair = costOfHour * workHours + costOfItems;

                Order order = new Order.Builder(dateReceivedL, plannedDateStartRepairL, resultSet.getString("problemDes"), status, car, employee).
                        costOfHour(costOfHour).costOfItems(costOfItems).costRepair(costRepair).resolutionDes(resultSet.getString("resolutionDes")).workHours(workHours).dateStartRepair(dateStartRepairL).build();
                order.setId(resultSet.getInt("id"));
                orders.add(order);

            }
            return orders;

        } catch (SQLException e) {
            System.out.println("Błąd połączenia a bazą");
            e.printStackTrace();
            return null;
        }

    }

    public static List<Order> read(long id) {
        try (Connection connection = DbUtil.getConnection()) {
            orders.clear();
            statement = connection.prepareStatement(FIND_ORDER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Employee employee;
            if (resultSet.next()) {
                dateReceivedL = resultSet.getObject("dateReceived", LocalDate.class);
                plannedDateStartRepairL = resultSet.getObject("plannedDateStartRepair", LocalDate.class);
                dateStartRepairL = resultSet.getObject("dateStartRepair", LocalDate.class);
                statusS = resultSet.getString("status");
                status = Status.valueOf(statusS);
                carS = resultSet.getString("car");
                cars = CarDAO.readByIdNumber(carS);
                car = cars.get(0);
                employeeId = resultSet.getInt("employeeAssigned");
                employees = EmployeeDAO.read(employeeId);
                employee = employees.get(0);
                costOfHour = resultSet.getDouble("costOfHour");
                costOfItems = resultSet.getDouble("costOfItems");
                workHours = resultSet.getDouble("workHours");
                costRepair = costOfHour * workHours + costOfItems;

                Order order = new Order.Builder(dateReceivedL, plannedDateStartRepairL, resultSet.getString("problemDes"), status, car, employee).
                        costOfHour(costOfHour).costOfItems(costOfItems).costRepair(costRepair).resolutionDes(resultSet.getString("resolutionDes")).workHours(workHours).dateStartRepair(dateStartRepairL).build();
                order.setId(resultSet.getLong("id"));
                orders.add(order);
                return orders;
            }

        } catch (SQLException e) {
            e.getCause();
            e.getMessage();
            System.out.println("Błąd połączenia z bazą");
        }
        return null;
    }


}
