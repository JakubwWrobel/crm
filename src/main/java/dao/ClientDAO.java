package dao;


import additionals.DbUtil;
import additionals.MyBusinessException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientDAO {
    private static final String CREATE_CLIENT = "insert into clients (firstName, lastName, birthDate) VALUES (?, ?, ?)";
    private static final String SHOW_ALL_CLIENTS = "SELECT * FROM clients";
    private static final String FIND_CLIENTS_BY_ID = "SELECT * FROM clients WHERE id = ?";
    private static final String UPDATE_CLIENT_BY_ID = "update clients set firstName = ?, lastName = ?, birthDate = ? WHERE id = ?";
    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM clients WHERE id = ?";
    private static PreparedStatement statement;
    private static List<Client> clients = new ArrayList<>();

    public boolean createClient(Client client) throws MyBusinessException {
        try (Connection connection = DbUtil.getConnection()) {
            statement = connection.prepareStatement(CREATE_CLIENT, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());

            java.util.Date javDate = client.getBirthDate();
            java.sql.Date sqlDate = new Date(javDate.getTime());
            statement.setDate(3, sqlDate);


            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                client.setId(resultSet.getInt(1));
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

    public static boolean updateClient(Client client) throws MyBusinessException {
        try (Connection connection = DbUtil.getConnection()) {
            statement = connection.prepareStatement(UPDATE_CLIENT_BY_ID);

            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());

            java.util.Date javDate = client.getBirthDate();
            java.sql.Date sqlDate = new Date(javDate.getTime());
            statement.setDate(3, sqlDate);
            statement.setInt(4, client.getId());

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

    public static List<Client> allClients() {
        try (Connection connection = DbUtil.getConnection()) {
            clients.clear();
            statement = connection.prepareStatement(SHOW_ALL_CLIENTS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client.Builder(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getDate("birthDate")).build();
                client.setId(resultSet.getInt("id"));
                clients.add(client);
            }
            return clients;


        } catch (SQLException e) {
            System.out.println("Błąd połączenia a bazą");
            e.printStackTrace();
            return null;
        }

    }

    public static List<Client> read(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            clients.clear();
            statement = connection.prepareStatement(FIND_CLIENTS_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Client client = new Client.Builder(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getDate("birthDate")).build();
                client.setId(resultSet.getInt("id"));
                clients.add(client);
                return clients;
            }

        } catch (SQLException e) {
            e.getCause();
            e.getMessage();
            System.out.println("Błąd połączenia z bazą");
        }
        return null;
    }
    public static boolean deleteClient(Client client) {
        try (Connection connection = DbUtil.getConnection()) {
            //DELETE EMPLOYEE
            statement = connection.prepareStatement(DELETE_CLIENT_BY_ID);
            statement.setInt(1, client.getId());
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



}
