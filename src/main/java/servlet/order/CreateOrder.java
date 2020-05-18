package servlet.order;

import additionals.MyBusinessException;
import additionals.ValidationDB;
import dao.CarDAO;
import dao.EmployeeDAO;
import dao.OrderDAO;
import model.Car;
import model.Employee;
import model.Order;
import model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CreateOrder", urlPatterns = "/createorder")
public class CreateOrder extends HttpServlet {
    private OrderDAO orderDAO = new OrderDAO();
    private static final Logger LOG = LoggerFactory.getLogger(Class.class.getName());
    private static String error = "";
    private List<Car> cars = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private Car car;
    private Employee employee;
    private long costOfHour = 0;
    private int carId;
    private String statusS;
    private Status status;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateReceived = request.getParameter("dateReceived");
        LocalDate dateReceivedL = LocalDate.parse(dateReceived, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String plannedDateStartRepair = request.getParameter("plannedDateStartRepair");
        LocalDate plannedDateStartRepairL = LocalDate.parse(plannedDateStartRepair, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        int employeeAssigned = Integer.parseInt(request.getParameter("employeeAssigned"));
        employees = EmployeeDAO.read(employeeAssigned);
        employee = employees.get(0);
        costOfHour = employee.getHourlyCost();

        String problemDes = request.getParameter("problemDes");


        statusS = request.getParameter("status");
        status = Status.valueOf(statusS);

        carId = Integer.parseInt(request.getParameter("car"));
        cars = CarDAO.read(carId);
        car = cars.get(0);

        try {

            Order order = new Order.Builder(dateReceivedL, plannedDateStartRepairL, problemDes, status, car, employee).costOfHour(costOfHour).build();

            if (orderDAO.createOrder(order)) {
                request.setAttribute("message", "Zamówienie został dodane");
                request.getRequestDispatcher("/jsp/order/createOrder.jsp").forward(request, response);
            }

        } catch (MyBusinessException e) {
            LOG.error("This is cause of Exception: " + e.getCause());
            error = ValidationDB.validation(e.getMessage());
            request.setAttribute("message", "Podana wartość istnieje już w systemie: " + error);
            request.getRequestDispatcher("/jsp/order/createOrder.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cars = CarDAO.allCars();
        employees = EmployeeDAO.allEmployees();
        request.setAttribute("cars", cars);
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/jsp/order/createOrder.jsp").forward(request, response);
    }
}
