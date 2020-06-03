package servlet.employee;

import additionals.MyBusinessException;
import additionals.ValidationDB;
import dao.EmployeeDAO;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "CreateEmployee", urlPatterns = "/createemployee")
public class CreateEmployee extends HttpServlet {
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final Logger LOG = LoggerFactory.getLogger(Class.class.getName());
    private static String error = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String note = request.getParameter("note");

        try {
        //VALIDATION OF Phone
        int phone = ValidationDB.validationPhone(request.getParameter("phone"));
        Integer hourly = Integer.parseInt(request.getParameter("hourly"));
        Employee employee = new Employee.Builder(name, surname, hourly).address(address).note(note).telNumber(phone).build();

            if (employeeDAO.createEmployee(employee)) {
                request.setAttribute("message", "Employee has been created");
                LOG.info("Employee has been created", "12");
                request.getRequestDispatcher("/jsp/employee/createEmployee.jsp").forward(request, response);
            }



        } catch (MyBusinessException e) {
            LOG.error("This is cause of Exception: " + e.getCause());
            error = ValidationDB.validation(e.getMessage());
            request.setAttribute("message", "Following value already exists in DB: " + error);
            request.getRequestDispatcher("/jsp/employee/createEmployee.jsp").forward(request, response);
        } catch (NumberFormatException e){
            LOG.error(" Tel-Number is incorrect " + e.getMessage());
            request.setAttribute("message", "Phone number should have 9 digits");
            request.getRequestDispatcher("/jsp/employee/createEmployee.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/employee/createEmployee.jsp").forward(request, response);
    }
}
