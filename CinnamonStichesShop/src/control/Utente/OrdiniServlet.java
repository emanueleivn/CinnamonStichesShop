package control.Utente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Ordine;
import model.OrdineDAO;
import model.Utente;

/**
 * Servlet implementation class OrdiniServlet
 */
@WebServlet("/Ordini")
public class OrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrdiniServlet() {
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
		Utente u = (Utente) session.getAttribute("user");
		if (u == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
			dispatcher.forward(request, response);
		}
		OrdineDAO o = new OrdineDAO((DataSource) getServletContext().getAttribute("Data Source"));

		try {
			ArrayList<Ordine> ordini = o.doRetrieveByIdCliente(u.getId());
			request.setAttribute("ordini", ordini);
		} catch (SQLException e) {
			e.printStackTrace();
			String errorMessage = " Errore Generico.";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
			dispatcher.forward(request, response);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/ordini.jsp");
		dispatcher.forward(request, response);
	}

}
