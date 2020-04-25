package servlet.client;

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
import java.util.List;

@WebServlet(name = "ShowAllClients", urlPatterns = "/showallclients")
public class ShowAllClients extends HttpServlet {
    private Integer id = null;
    private List<Client> clients = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            id = Integer.parseInt(request.getParameter("id"));
            clients = ClientDAO.read(id);
            request.setAttribute("id", id);
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("/jsp/client/showAllClients.jsp").forward(request, response);


        } catch (NumberFormatException e) {
            clients = ClientDAO.allClients();
            request.setAttribute("clients", clients);

            request.getRequestDispatcher("/jsp/client/showAllClients.jsp").forward(request, response);
        }
    }
}
