package servlet.order;

import dao.ClientDAO;
import dao.OrderDAO;
import model.Client;
import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowAllOrders", urlPatterns = "/showallorders")
public class ShowAllOrders extends HttpServlet {
    private Integer id = null;
    private List<Order> orders = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            id = Integer.parseInt(request.getParameter("id"));
            orders = OrderDAO.read(id);
            request.setAttribute("id", id);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/jsp/order/showAllOrders.jsp").forward(request, response);


        } catch (NumberFormatException e) {
            orders = OrderDAO.allOrders();
            request.setAttribute("orders", orders);

            request.getRequestDispatcher("/jsp/order/showAllOrders.jsp").forward(request, response);
        }
    }
}
