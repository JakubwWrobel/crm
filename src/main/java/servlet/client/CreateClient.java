package servlet.client;

import additionals.MyBusinessException;
import additionals.ValidationDB;
import dao.ClientDAO;
import model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

@WebServlet(name = "CreateClient", urlPatterns = "/createclient")
public class CreateClient extends HttpServlet {
    private ClientDAO clientDAO = new ClientDAO();
    private static final Logger LOG = LoggerFactory.getLogger(Class.class.getName());
    private static String error = "";
    private String firstName;
    private String lastName;
    private String birthDate;
    private static Date date;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         firstName = request.getParameter("firstName");
         lastName = request.getParameter("lastName");
         birthDate = request.getParameter("birthDate");

        try {
            date = Date.valueOf(birthDate);
            Client client = new Client.Builder(firstName, lastName, date).build();

            if (clientDAO.createClient(client)) {
                request.setAttribute("message", "Client has been created");
                request.getRequestDispatcher("/jsp/client/createClient.jsp").forward(request, response);
            }

        } catch (MyBusinessException e) {
            LOG.error("This is cause of Exception: " + e.getCause());
            error = ValidationDB.validation(e.getMessage());
            request.setAttribute("message", "Following value already exists in DB: " + error);
            request.getRequestDispatcher("/jsp/employee/createEmployee.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/client/createClient.jsp").forward(request, response);
    }
}
