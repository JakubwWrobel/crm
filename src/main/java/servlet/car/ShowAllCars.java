package servlet.car;

import dao.CarDAO;
import dao.ClientDAO;
import dao.EmployeeDAO;
import model.Car;
import model.Client;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowAllCars", urlPatterns = "/showallcars")
public class ShowAllCars extends HttpServlet {
    private int id = 0;
    private List<Car> cars = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            id = Integer.parseInt(request.getParameter("id"));
            cars = CarDAO.read(id);
            if(cars == null){
                throw new NumberFormatException();
            }
            request.setAttribute("id", id);
            request.setAttribute("cars", cars);

            request.getRequestDispatcher("/jsp/car/showAllCars.jsp").forward(request, response);


        } catch (NumberFormatException e) {
            cars = CarDAO.allCars();
            request.setAttribute("cars", cars);

            request.getRequestDispatcher("/jsp/car/showAllCars.jsp").forward(request, response);
        }
    }
}
