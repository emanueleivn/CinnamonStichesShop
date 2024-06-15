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
import model.Ordine;
import model.OrdineDAO;
import model.ProdottoCarrello;
import model.Utente;

/**
 * Servlet implementation class EffettuaOrdineServlet
 */
@WebServlet("/EffettuaOrdineServlet")
public class EffettuaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EffettuaOrdineServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		Utente user = (Utente) session.getAttribute("user");
		if (carrello == null || user == null) {
			String errore = "Errore sconosciuto.";
			request.setAttribute("errorMessage", errore);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
			dispatcher.forward(request, response);
		}
		Ordine ordine = new Ordine();
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ordine.setProdotti(carrello.getProdottiCarrello());
		OrdineDAO ordineDao = new OrdineDAO(ds);
		try {
			ordineDao.doSave(ordine, carrello);
		} catch (SQLException e) {
			e.printStackTrace();
			String errore = "Errore sconosciuto.";
			request.setAttribute("errorMessage", errore);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
			dispatcher.forward(request, response);
		}

		carrello = new Carrello();
		session.setAttribute("carrello", carrello);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/Ordini");
		dispatcher.forward(request, response);

	}

}
