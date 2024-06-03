package control.Utente;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Sanitizer;
import model.Utente;
import model.UtenteDAO;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -8697651045570564505L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to login page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        UtenteDAO userDao = new UtenteDAO(ds);
        String username = request.getParameter("user");
        String password = request.getParameter("password");

        if (!Sanitizer.sanitizeInput(username) && Sanitizer.sanitizeInput(password)) {
            String errorMessage = " Caratteri inseriti non validi!";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        password = Sanitizer.hashPassword(password);
        try {
            Utente user = userDao.doRetrieveByUserPassword(username, password);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("isLogged", true);
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/view/index.jsp");
            } else {
                String errorMessage = "Utente non valido!";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Errore del server, riprova più tardi.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}

