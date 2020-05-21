package servlet.car;

import additionals.Communication;
import dao.CarDAO;
import dao.ClientDAO;
import model.Car;
import model.Client;

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

@WebServlet(name = "DeleteCar", urlPatterns = "/deletecar")
public class DeleteCar extends HttpServlet {
    private List<Car> cars = new ArrayList<>();
    private String id = null;
    private List<Client> clients = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();
    private Car car;
    boolean isValid = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.flushBuffer();
        id = request.getParameter("id"); // this is your data sent from client
        map.clear();
        isValid = false;
        cars = CarDAO.readByIdNumber(id);
        car = cars.get(0);
        if (CarDAO.validationCarOrder(car)) {
            isValid = false;
        } else if (CarDAO.deleteCar(car)) {
            isValid = true;
            map.put("id", id);
        }
        map.put("isValid", isValid);
        Communication.write(response, map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        cars = CarDAO.allCars();
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/jsp/car/deleteCar.jsp").forward(request, response);

    }
}
