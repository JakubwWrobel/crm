package servlet.order;

import additionals.MyBusinessException;
import additionals.ValidationDB;
import com.sun.mail.imap.protocol.INTERNALDATE;
import dao.CarDAO;
import dao.ClientDAO;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdateOrder", urlPatterns = "/updateorder")
public class UpdateOrder extends HttpServlet {
    private OrderDAO orderDAO = new OrderDAO();
    private static final Logger LOG = LoggerFactory.getLogger(Class.class.getName());
    private static String error = "";
    private static List<Car> cars;
    private static List<Order> orders;
    private static List<Employee> employees;
    private static Car car;
    private static Employee employee;
    private static Order order;
    private int id;
    private static HttpSession session;
    private long costOfHour = 0;
    private String resolutionDes;
    private String problemDes;
    private String statusS;
    private Status status;
    private double costOfItems;
    private double workhours;
    private double costOfRepair;
    private LocalDate dateStartRepairL;
    private int carId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateReceived = request.getParameter("dateReceived");
        LocalDate dateReceivedL = LocalDate.parse(dateReceived, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String plannedDateStartRepair = request.getParameter("plannedDateStartRepair");
        LocalDate plannedDateStartRepairL = LocalDate.parse(plannedDateStartRepair, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String dateStartRepair = request.getParameter("dateStartRepair");
        dateStartRepairL = LocalDate.parse(dateStartRepair, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        costOfItems = Double.parseDouble(request.getParameter("costOfItems"));
        workhours = Double.parseDouble(request.getParameter("workHours"));

        int employeeAssigned = Integer.parseInt(request.getParameter("employeeAssigned"));
        employees = EmployeeDAO.read(employeeAssigned);
        employee = employees.get(0);
        costOfHour = employee.getHourlyCost();

        resolutionDes = request.getParameter("resolutionDes");
        problemDes = request.getParameter("problemDes");

        statusS = request.getParameter("status");
        status = Status.valueOf(statusS);

        carId = Integer.parseInt(request.getParameter("car"));
        cars = CarDAO.read(carId);

        car = cars.get(0);

        costOfRepair = costOfHour * workhours;

        try {

            Order order = new Order.Builder(dateReceivedL, plannedDateStartRepairL, problemDes, status, car, employee).costOfHour(costOfHour).costOfItems(costOfItems).dateStartRepair(dateStartRepairL).resolutionDes(resolutionDes).workHours(workhours).costRepair(costOfRepair).build();
            session = request.getSession();
            order.setId(id);

            if (orderDAO.updateOrder(order)) {
                request.setAttribute("message", "Zamówienie został zaaktualizowane");
                request.getRequestDispatcher("/jsp/order/updateOrder.jsp").forward(request, response);
            }
            session.invalidate();
        } catch (MyBusinessException e) {
            LOG.error("This is cause of Exception: " + e.getCause());
            error = ValidationDB.validation(e.getMessage());
            request.setAttribute("message", "Podana wartość istnieje już w systemie: " + error);

            session = request.getSession();
            id = (int) session.getAttribute("id");
            cars = CarDAO.allCars();
            employees = EmployeeDAO.allEmployees();
            orders = OrderDAO.read(id);
            order = orders.get(0);

            request.setAttribute("employees", employees);
            request.setAttribute("cars", cars);
            request.setAttribute("order", order);

            request.getRequestDispatcher("/jsp/order/updateOrder2.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            id = Integer.parseInt(request.getParameter("id"));
            session = request.getSession();
            session.setAttribute("id", id);
            orders = OrderDAO.read(id);
            order = orders.get(0);
            cars = CarDAO.allCars();
            employees = EmployeeDAO.allEmployees();
            request.setAttribute("cars", cars);
            request.setAttribute("employees", employees);
            request.setAttribute("order", order);
            request.getRequestDispatcher("/jsp/order/updateOrder2.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            orders = OrderDAO.allOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/jsp/order/updateOrder.jsp").forward(request, response);
        }
    }

}
