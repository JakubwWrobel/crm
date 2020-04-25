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

@WebServlet(name = "DeleteEmployee", urlPatterns = "/deleteemployee")
public class DeleteEmployee extends HttpServlet {
    private List<Employee> employees = new ArrayList<>();
    private Integer id = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println("test");
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
