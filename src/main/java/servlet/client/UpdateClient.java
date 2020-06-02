package servlet.client;

import additionals.MyBusinessException;
import additionals.ValidationDB;
import dao.ClientDAO;
import javafx.beans.binding.NumberBinding;
import model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@WebServlet(name = "UpdateClient", urlPatterns = "/updateclient")
public class UpdateClient extends HttpServlet {
    private static List<Client> clients = new ArrayList<>();
    private ClientDAO clientDAO = new ClientDAO();
    private static final Logger LOG = LoggerFactory.getLogger(Class.class.getName());
    private static String error = "";
    private String firstName;
    private String lastName;
    private String birthDate;
    private Integer id;
    private String check;
    private Client client;
    private static Date date;
    HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        birthDate = request.getParameter("birthDate");
        try {
            session = request.getSession();
            id = (Integer) session.getAttribute("id");
            date = Date.valueOf(birthDate);
            Client client = new Client.Builder(firstName, lastName, date).build();
            client.setId(id);
            if (clientDAO.updateClient(client)) {
                request.setAttribute("message", "Client has been updated ");
                session.invalidate();
                request.getRequestDispatcher("/jsp/client/updateClient2.jsp").forward(request, response);
            }


        } catch (MyBusinessException e) {
            LOG.error("This is cause of Exception: " + e.getCause());
            error = ValidationDB.validation(e.getMessage());
            request.setAttribute("message", "Following value already exists in DB: " + error);
            request.getRequestDispatcher("/jsp/client/updateClient2.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            id = Integer.parseInt(request.getParameter("id"));
            session = request.getSession();
            session.setAttribute("id", id);

            clients = ClientDAO.read(id);
            client = clients.get(0);
            request.setAttribute("client", client);
            request.getRequestDispatcher("/jsp/client/updateClient2.jsp").forward(request, response);

        } catch (NumberFormatException e) {

            clients = ClientDAO.allClients();
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("/jsp/client/updateClient.jsp").forward(request, response);
        }
    }
}
