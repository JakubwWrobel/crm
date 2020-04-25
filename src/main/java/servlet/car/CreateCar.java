package servlet.car;

import additionals.MyBusinessException;
import additionals.ValidationDB;
import dao.CarDAO;
import dao.ClientDAO;
import dao.EmployeeDAO;
import model.Car;
import model.Client;
import model.Employee;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CreateCar", urlPatterns = "/createcar")
public class CreateCar extends HttpServlet {
    private CarDAO carDAO = new CarDAO();
    private static final Logger LOG = LoggerFactory.getLogger(Class.class.getName());
    private static String error = "";
    private static List<Client> clients = new ArrayList<>();
    private static Client client;
    private String model;
    private String brand;
    private String dateProduction;
    private String dateNextCheckup;
    private String idNumber;
    private int clientId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            model = Objects.requireNonNull(request.getParameter("model"));
            brand = Objects.requireNonNull(request.getParameter("brand"));
            dateProduction = Objects.requireNonNull(request.getParameter("date"));
            dateNextCheckup = request.getParameter("date2");
            idNumber = Objects.requireNonNull(request.getParameter("id"));
            clientId = Integer.parseInt(request.getParameter("clientId"));


            //VALIDATION OF Phone
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date = simpleDateFormat.parse(dateProduction);
            Date date2 = simpleDateFormat.parse(dateNextCheckup);
            clients = ClientDAO.read(clientId);
            client = clients.get(0);

            Car car = new Car.Builder(model, brand, date, idNumber, client).nextCheckupDate(date2).build();

            if (carDAO.createCar(car)) {
                request.setAttribute("message", "Samochód został dodany");
                request.getRequestDispatcher("/jsp/car/createCar.jsp").forward(request, response);
            }


        } catch (MyBusinessException e) {
            LOG.error("This is cause of Exception: " + e.getCause());
            error = ValidationDB.validation(e.getMessage());
            request.setAttribute("message", "Podana wartość istnieje już w systemie: " + error);
            request.getRequestDispatcher("/jsp/car/createCar.jsp").forward(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
        //SPRAWDZIĆ JAK WYWALA BŁĄD?
        }
    }

    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        clients = ClientDAO.allClients();
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("/jsp/car/createCar.jsp").forward(request, response);
    }
}
