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

import model.Ordine;
import model.OrdineDAO;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class RedirAdminServlet
 */
@WebServlet("/admin/PaginaAmministratore")
public class RedirAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RedirAdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String azione = request.getParameter("azioneAdmin");
		String reAddress = "/view/amministrazione.jsp";
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		List<Prodotto> prodotti = null;
		if (azione != null) {
			switch (azione) {
			case "add":
				reAddress = "/view/aggiungiProdotto.jsp";
				break;
				
			case "modify":

				ProdottoDAO productDAO = new ProdottoDAO(ds);
				
				try {
					prodotti = productDAO.doRetrieveAllSaleable();
				} catch (SQLException e) {
					e.printStackTrace();
					reAddress = "/view/error.jsp";
					request.setAttribute("errorMessage", "Errore interno.");
					break;
				}

				request.setAttribute("prodotti", prodotti);
				reAddress = "/view/amministraProdotti.jsp";
				break;
				
			case "showOrders":
				List<Ordine> listaOrdini = null;
				String ordine = request.getParameter("ordine");
				OrdineDAO o = new OrdineDAO(ds);
				try {
					listaOrdini = o.doRetrieveAll(null);
					request.setAttribute("ordini", listaOrdini);
				} catch (SQLException e) {
					e.printStackTrace();
					reAddress = "/view/error.jsp";
					request.setAttribute("errorMessage", "Errore interno.");
					break;
				}
				reAddress = "/view/ordini.jsp";
				break;
			case "showUsers":
				List<Utente> utenti = null;
				UtenteDAO u = new UtenteDAO(ds);
				try {
					utenti = u.doRetrieveAll("ordine");
					request.setAttribute("utenti", utenti);
				} catch (SQLException e) {
					e.printStackTrace();
					reAddress = "/view/error.jsp";
					request.setAttribute("errorMessage", "Errore interno.");
					break;
				}
				reAddress = "/view/amministraUtenti.jsp";
				break;
			default:
				reAddress = "/view/error.jsp";
				break;
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(reAddress);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
