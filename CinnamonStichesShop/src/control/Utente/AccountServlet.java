package control.Utente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Sanitizer;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountManage")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UtenteDAO userDao = new UtenteDAO(ds);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String via = request.getParameter("via");
		String cap = request.getParameter("cap");
		String città = request.getParameter("city");
		String san = email + password + nome + cognome + via + cap + città;
		try {
			if (Sanitizer.sanitizeInput(san) && userDao.checkingEmail(email) == null) {
				Utente ut = new Utente();
				ut.setNome(nome);
				ut.setCognome(cognome);
				ut.setEmail(email);
				ut.setPassword(Sanitizer.hashPassword(password));
				ut.setVia(via);
				ut.setCap(cap);
				ut.setCittà(città);
				ut.setIsAdmin(false);
				userDao.doUpdate(ut);
			}
		} catch (SQLException e) {
			String errorMessage = " Errore Generico, dati inseriti non accettabili";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/account.jsp");
		dispatcher.forward(request, response);

	}

}
