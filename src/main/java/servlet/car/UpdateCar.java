package servlet.car;

import additionals.MyBusinessException;
import additionals.ValidationDB;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import dao.CarDAO;
import dao.ClientDAO;
import model.Car;
import model.Client;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "UpdateCar", urlPatterns = "/updatecar")
public class UpdateCar extends HttpServlet {
    private CarDAO carDAO = new CarDAO();
    private static final Logger LOG = LoggerFactory.getLogger(Class.class.getName());
    private static String error = "";
    private static List<Car> cars = new ArrayList<>();
    private static Car car;
    private int check;
    private Client client;
    private List<Client> clients;
    private String model;
    private String brand;
    private String dateProduction;
    private String dateNextCheckup;
    private int clientId;
    private String idNumber;
    private static Date date;
    private static Date date2;
    HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            model = Objects.requireNonNull(request.getParameter("model"));
            brand = Objects.requireNonNull(request.getParameter("brand"));
            dateProduction = Objects.requireNonNull(request.getParameter("date"));
            dateNextCheckup = request.getParameter("date2");
            idNumber = Objects.requireNonNull(request.getParameter("idNumber"));
            clientId = Integer.parseInt(request.getParameter("clientId"));
            session = request.getSession();
            check = (int) session.getAttribute("check");
            date =Date.valueOf(dateProduction);
            date2 =Date.valueOf(dateNextCheckup);
            clients = ClientDAO.read(clientId);
            client = clients.get(0);

            Car car = new Car.Builder(model, brand, date, idNumber, client).nextCheckupDate(date2).build();
            car.setId(check);
            if (carDAO.updateCar(car)) {
                request.setAttribute("message", "Car has been updated");
                request.getRequestDispatcher("/jsp/car/updateCar2.jsp").forward(request, response);
            }


        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println();
        } catch (MyBusinessException e) {
            LOG.error("This is cause of Exception: " + e.getCause());
            error = ValidationDB.validation(e.getMessage());
            request.setAttribute("message", "Following value already exists in DB: " + error);
            cars = CarDAO.read(check);
            car = cars.get(0);
            clients = ClientDAO.allClients();

            request.setAttribute("car", car);
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("/jsp/car/updateCar2.jsp").forward(request, response);
        }
    }

    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            check = Integer.parseInt(request.getParameter("id"));
            cars = CarDAO.read(check);
            car = cars.get(0);
            session = request.getSession();
            session.setAttribute("check", check);
            clients = ClientDAO.allClients();
            request.setAttribute("car", car);
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("/jsp/car/updateCar2.jsp").forward(request, response);

        } catch (NumberFormatException e) {

            cars = carDAO.allCars();
            request.setAttribute("cars", cars);
            request.getRequestDispatcher("/jsp/car/updateCar.jsp").forward(request, response);
        }
    }
}
