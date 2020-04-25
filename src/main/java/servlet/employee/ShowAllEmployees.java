package servlet.employee;

import dao.EmployeeDAO;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowAllEmployees", urlPatterns = "/showallemployees")
public class ShowAllEmployees extends HttpServlet {
    private Integer id = null;
    private List<Employee> employees = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            id = Integer.parseInt(request.getParameter("id"));
            employees = EmployeeDAO.read(id);
            request.setAttribute("id", id);
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/jsp/employee/showAllEmployees.jsp").forward(request, response);


        } catch (NumberFormatException e) {
            employees = EmployeeDAO.allEmployees();
            request.setAttribute("employees", employees);

            request.getRequestDispatcher("/jsp/employee/showAllEmployees.jsp").forward(request, response);
        }
    }
}
