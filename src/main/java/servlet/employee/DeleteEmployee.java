package servlet.employee;

import additionals.Communication;
import additionals.MyBusinessException;
import additionals.ValidationDB;
import com.google.gson.Gson;
import dao.EmployeeDAO;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@WebServlet(name = "DeleteEmployee", urlPatterns = "/deleteemployee")
public class DeleteEmployee extends HttpServlet {
    private List<Employee> employees = new ArrayList<>();
    private Integer id = null;
    private Map<String, Object> map = new HashMap<>();
    private Employee employee;
    boolean isValid = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.flushBuffer();

        id = Integer.parseInt(request.getParameter("id")); // this is your data sent from client
        map.clear();
        isValid = false;

        employees = EmployeeDAO.read(id);
        employee = employees.get(0);
        if (EmployeeDAO.deleteEmployee(employee)) {
            isValid = true;
            map.put("id", id);
        }
        map.put("isValid", isValid);
        Communication.write(response, map);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            id = Integer.parseInt(request.getParameter("id"));
            employees = EmployeeDAO.read(id);
            request.setAttribute("id", id);
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/jsp/employee/deleteEmployee.jsp").forward(request, response);

//            https://stackoverflow.com/questions/18995461/how-can-i-show-data-using-a-modal-when-clicking-a-table-row-using-bootstrap
        } catch (NumberFormatException e) {
            employees = EmployeeDAO.allEmployees();
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/jsp/employee/deleteEmployee.jsp").forward(request, response);
        }
    }
}
