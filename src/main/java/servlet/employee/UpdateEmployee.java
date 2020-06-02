package servlet.employee;

import additionals.MyBusinessException;
import additionals.ValidationDB;
import dao.CarDAO;
import dao.EmployeeDAO;
import dao.OrderDAO;
import model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdateEmployee", urlPatterns = "/updateemployee")
public class UpdateEmployee extends HttpServlet {
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final Logger LOG = LoggerFactory.getLogger(Class.class.getName());
    private static String error = "";
    private HttpSession session;
    private int id;
    private static List<Employee> employees = new ArrayList<>();
    private String name;
    private String surname;
    private String address;
    private String note;
    private Integer hourly;
    private int phone;
    private Employee employee;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        address = request.getParameter("address");
        note = request.getParameter("note");
        session = request.getSession();
        id = (int) session.getAttribute("id");


        try {
            phone = ValidationDB.validationPhone(request.getParameter("phone"));
            hourly = Integer.parseInt(request.getParameter("hourly"));
            employee = new Employee.Builder(name, surname, hourly).address(address).note(note).telNumber(phone).build();
            employee.setId(id);
            if (employeeDAO.updateEmployee(employee)) {
                request.setAttribute("message", "Employee has been updated");
                request.getRequestDispatcher("/jsp/employee/updateEmployee.jsp").forward(request, response);
            }


        } catch (MyBusinessException e) {
            LOG.error("This is cause of Exception: " + e.getCause());
            error = ValidationDB.validation(e.getMessage());
            request.setAttribute("message", "Following value exists already in DB: " + error);
            employees = EmployeeDAO.allEmployees();
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/jsp/employee/updateEmployee2.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            employees = EmployeeDAO.read(id);
            employee = employees.get(0);
            request.setAttribute("employee", employee);
            LOG.error(" Tel-Number is incorrect " + e.getMessage());
            request.setAttribute("message", "Phone number should have 9 digits");
            request.getRequestDispatcher("/jsp/employee/updateEmployee2.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            id = Integer.parseInt(request.getParameter("id"));
            session = request.getSession();
            session.setAttribute("id", id);
            employees = EmployeeDAO.read(id);
            employee = employees.get(0);
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("/jsp/employee/updateEmployee2.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            employees = EmployeeDAO.allEmployees();
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/jsp/employee/updateEmployee.jsp").forward(request, response);
        }
    }

}
