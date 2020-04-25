package dao;


import additionals.DbUtil;
import additionals.MyBusinessException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import model.Car;
import model.Client;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CarDAO {
    private static final String CREATE_CAR = "insert into cars (idNumber, model, brand, dateProduction, nextCheckupDate, clientId) VALUES (?, ?, ? ,? ,?,?)";
    private static final String SHOW_ALL_CARS = "SELECT * FROM cars";
    private static final String FIND_CAR_BY_ID = "SELECT * FROM cars WHERE id = ?";
    private static final String UPDATE_CAR_BY_ID = "UPDATE cars SET model = ?, brand = ?, dateProduction = ?, nextCheckupDate = ?, clientId = ?, idNumber = ?  WHERE id = ?";
    private static PreparedStatement statement;
    private static List<Car> cars = new ArrayList<>();
    private static int clientId;
    private static List<Client> clients;


    public boolean createCar(Car car) throws MyBusinessException {
        try (Connection connection = DbUtil.getConnection()) {
            statement = connection.prepareStatement(CREATE_CAR, Statement.RETURN_GENERATED_KEYS);


            statement.setString(1, car.getIdNumber());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getBrand());

            //MAP JAVA DATE TO SQL DATE
            java.util.Date utilStartDate = car.getDateProduction();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());

            statement.setDate(4, (sqlStartDate));

            java.util.Date nextJavDate = car.getNextCheckupDate();
            java.sql.Date nextSqlDate = new java.sql.Date(nextJavDate.getTime());
            statement.setDate(5, nextSqlDate);

            statement.setInt(6, car.getClient().getId());
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getInt(1));
            }

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

    public static List<Car> allCars() {
        try (Connection connection = DbUtil.getConnection()) {
            cars.clear();
            statement = connection.prepareStatement(SHOW_ALL_CARS);
            ResultSet resultSet = statement.executeQuery();
            Client client;
            while (resultSet.next()) {
                clientId = resultSet.getInt("clientId");
                clients = ClientDAO.read(clientId);
                client = clients.get(0);
                Car car = new Car.Builder(resultSet.getString("model"), resultSet.getString("brand"), resultSet.getDate("dateProduction"), resultSet.getString("idNumber"), client).nextCheckupDate(resultSet.getDate("nextCheckupDate")).build();
                car.setId(resultSet.getInt("id"));
                cars.add(car);
            }
            return cars;

        } catch (SQLException e) {
            System.out.println("Błąd połączenia a bazą");
            e.printStackTrace();
            return null;
        }

    }

    public static List<Car> read(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            cars.clear();
            statement = connection.prepareStatement(FIND_CAR_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Client client;
            if (resultSet.next()) {
                clientId = resultSet.getInt("clientId");
                clients = ClientDAO.read(clientId);
                client = clients.get(0);
                Car car = new Car.Builder(resultSet.getString("model"), resultSet.getString("brand"), resultSet.getDate("dateProduction"), resultSet.getString("idNumber"), client).nextCheckupDate(resultSet.getDate("nextCheckupDate")).build();
                car.setId(id);
                cars.add(car);
                return cars;
            }

        } catch (SQLException e) {
            e.getCause();
            e.getMessage();
            System.out.println("Błąd połączenia z bazą");
        }
        return null;
    }


    public boolean updateCar(Car car) throws MyBusinessException,  MySQLIntegrityConstraintViolationException {

        try (Connection connection = DbUtil.getConnection()) {
            statement = connection.prepareStatement(UPDATE_CAR_BY_ID);


            statement.setString(1, car.getModel());
            statement.setString(2, car.getBrand());

            //MAP JAVA DATE TO SQL DATE
            java.util.Date utilStartDate = car.getDateProduction();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            statement.setDate(3, (sqlStartDate));

            java.util.Date nextJavDate = car.getNextCheckupDate();
            java.sql.Date nextSqlDate = new java.sql.Date(nextJavDate.getTime());
            statement.setDate(4, nextSqlDate);

            statement.setInt(5, car.getClient().getId());
            statement.setString(6 ,car.getIdNumber());
            statement.setInt(7 ,car.getId());
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
}
