package servlet.order;

import additionals.Communication;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DeleteOrder", urlPatterns = "/deleteorder")
public class DeleteOrder extends HttpServlet {
    private Integer id = null;
    private List<Order> orders = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();
    private Order order;
    boolean isValid = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.flushBuffer();

        id = Integer.parseInt(request.getParameter("id")); // this is your data sent from client
        map.clear();
        isValid = false;
        orders = OrderDAO.read(id);
        order = orders.get(0);
        if (OrderDAO.deleteOrder(order)) {
            isValid = true;
            map.put("id", id);
        } else {
            isValid = false;

        }
        map.put("isValid", isValid);
        Communication.write(response, map);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        orders = OrderDAO.allOrders();
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/jsp/order/deleteOrder.jsp").forward(request, response);

    }
}
