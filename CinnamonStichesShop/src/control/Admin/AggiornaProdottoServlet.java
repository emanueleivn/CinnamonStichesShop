package control.Admin;


import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Prodotto;
import model.ProdottoDAO;
import model.Sanitizer;

/**
 * Servlet implementation class AggiornaProdottoServlet
 */
@WebServlet("/admin/AggiornaProdotto")
public class AggiornaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiornaProdottoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codice = request.getParameter("codice");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzo");
		if (!Sanitizer.sanitizeInput(codice+descrizione + prezzo)) {
			request.setAttribute("errorMessage", "Parametri Non Corretti");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
			try {
				ProdottoDAO pDao = new ProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
				Prodotto p = pDao.doRetrieveById(Integer.parseInt(codice));
				p.setCosto(Float.parseFloat(prezzo));
				p.setDescrizione(descrizione);
				pDao.doUpdate(p);
				List<Prodotto> prodotti = pDao.doRetrieveAll();
				request.setAttribute("prodotti", prodotti);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/amministraProdotti.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Errore interno.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
	}

