package control.Utente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Prodotto;
import model.Utente;
import model.PreferitiDAO;

@WebServlet("/Preferiti")
public class PreferitiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String returnAddress;
		HttpSession session = request.getSession();
		Utente u = (Utente) session.getAttribute("user");
		if (u == null) {
			returnAddress = "/Login";
		} else {
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			PreferitiDAO pr = new PreferitiDAO(ds);
			List<Prodotto> preferiti = null;
			try {
				preferiti = pr.doRetrieveAllFavourite(u.getId());
				System.out.println(preferiti.size());
			} catch (SQLException e) {
				String errorMessage = " Errore Generico, dati inseriti non accettabili";
				request.setAttribute("errorMessage", errorMessage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
			request.setAttribute("preferiti", preferiti);
			returnAddress = "/view/preferiti.jsp";

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(returnAddress);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
