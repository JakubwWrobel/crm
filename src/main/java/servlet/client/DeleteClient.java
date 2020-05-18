package servlet.client;

import additionals.Communication;
import dao.ClientDAO;
import dao.EmployeeDAO;
import model.Client;
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

@WebServlet(name = "DeleteClient", urlPatterns = "/deleteclient")
public class DeleteClient extends HttpServlet {
    private Integer id = null;
    private List<Client> clients = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();
    private Client client;
    boolean isValid = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.flushBuffer();

        int id = Integer.parseInt(request.getParameter("id")); // this is your data sent from client
        map.clear();
        isValid = false;

        clients= ClientDAO.read(id);
        client = clients.get(0);
        if (ClientDAO.deleteClient(client)) {
            isValid = true;
            map.put("id", id);
        }
        map.put("isValid", isValid);
        Communication.write(response, map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            id = Integer.parseInt(request.getParameter("id"));
            clients = ClientDAO.read(id);
            request.setAttribute("id", id);
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("/jsp/client/deleteClient.jsp").forward(request, response);


        } catch (NumberFormatException e) {
            clients = ClientDAO.allClients();
            request.setAttribute("clients", clients);

            request.getRequestDispatcher("/jsp/client/deleteClient.jsp").forward(request, response);
        }
    }
}
