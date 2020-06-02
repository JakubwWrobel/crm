package reports;

import dao.CarDAO;
import dao.EmployeeDAO;
import model.Car;
import model.WorkingHoursModel;

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


@WebServlet(name = "WorkingHours", urlPatterns = "/workinghours")
public class WorkingHours extends HttpServlet {
    private static List<Car> cars = new ArrayList<>();
    private LocalDate dateFromL;
    private String dateFrom;
    private LocalDate dateToL;
    private String dateTo;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        dateFrom = request.getParameter("dateFrom");
        dateFromL = (dateFrom.isEmpty()) ? null : LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<WorkingHoursModel> workingHoursModelList = EmployeeDAO.workingHours(dateFromL);
        request.setAttribute("workingHoursModelList", workingHoursModelList);
        request.getRequestDispatcher("/jsp/reports/workingHours.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/reports/workingHours.jsp").forward(request, response);
    }
}
