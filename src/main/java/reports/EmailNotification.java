package reports;

import additionals.Email;
import dao.CarDAO;
import model.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "EmailNotification", urlPatterns = "/emailnotification")
public class EmailNotification extends HttpServlet {
    private static List<Car> cars = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cars = CarDAO.nearCheckUpDateClients();
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i).getClient().getLastName());
        }

        request.setAttribute("message", "Przypomnienie zostało wysłane do poniższych klientów");
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/jsp/reports/emailNotification.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cars = CarDAO.nearCheckUpDateClients();
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/jsp/reports/emailNotification.jsp").forward(request, response);
    }
}
