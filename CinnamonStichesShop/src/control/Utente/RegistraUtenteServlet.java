package control.Utente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Carrello;
import model.Sanitizer;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class RegistraUtenteServlet
 */
@WebServlet(name = "RegistraUtenteServlet", value = "/Registrazione")
public class RegistraUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistraUtenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UtenteDAO userDao = new UtenteDAO(ds);
		String username = request.getParameter("user");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String via = request.getParameter("via");
		String cap = request.getParameter("cap");
		String città = request.getParameter("city");
		String san = username + email + password + nome + cognome + via + cap + città;
		try {
			if (Sanitizer.sanitizeInput(san) && userDao.checkingUser(username) == null
					&& userDao.checkingEmail(email) == null) {
				Utente ut = new Utente();
				ut.setNome(nome.trim());
				ut.setCognome(cognome.trim());
				ut.setUsername(username.trim());
				ut.setEmail(email.trim());
				ut.setPassword(Sanitizer.hashPassword(password.trim()));
				ut.setVia(via.trim());
				ut.setCap(cap.trim());
				ut.setCittà(città.trim());
				ut.setIsAdmin(false);
				HttpSession session = request.getSession();
				session.setAttribute("isLogged", true);
				session.setAttribute("isAdmin", ut.getIsAdmin());
				session.setAttribute("user", ut);
				session.setAttribute("carrello", new Carrello());

				userDao.doSave(ut);
			}
		} catch (SQLException e) {
			String errorMessage = " Errore Generico, dati inseriti non accettabili";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/index.jsp");
		dispatcher.forward(request, response);

	}

}
