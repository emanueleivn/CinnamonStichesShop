package control.general;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;

import model.Carrello;
import model.Prodotto;
import model.ProdottoCarrello;
import model.ProdottoDAO;

/**
 * Servlet implementation class CarrelloServlet
 */
@WebServlet("/Carrello")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarrelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		if (carrello == null) {
			carrello = new Carrello();
			session.setAttribute("carrello", carrello);
		}

		String action = request.getParameter("action");
		if (action != null) {
			try {

				DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

				switch (action) {

				case "add": {
					int codiceProdotto = Integer.parseInt(request.getParameter("codice"));
					ProdottoDAO pdao = new ProdottoDAO(ds);
					Prodotto p = pdao.doRetrieveById(codiceProdotto);
					int quantita = Integer.parseInt(request.getParameter("quantita_" + codiceProdotto));
					ProdottoCarrello pc = new ProdottoCarrello(p, quantita);
					carrello.aggiungiAlCarrello(pc);
					break;
				}
				case "update": {
					int codiceProdotto = Integer.parseInt(request.getParameter("codice"));
					int quantita = Integer.parseInt(request.getParameter("quantita"));
					
					Prodotto p = new Prodotto();
					p.setCodice(codiceProdotto);
					ProdottoCarrello prodottoCarrello = null;
					for (ProdottoCarrello pc : carrello.getProdottiCarrello()) {
						if (pc.getProdotto().getCodice() == codiceProdotto) {
							prodottoCarrello = pc;
							prodottoCarrello.setQuantity(quantita);
							prodottoCarrello.setTot(prodottoCarrello.getProdotto().getCosto() * quantita);
							
							break;
						}
					}
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					JSONObject json = new JSONObject();
					json.put("success", true);
					json.put("codice", codiceProdotto);
					json.put("totProdotto", prodottoCarrello.getTot());
					json.put("totaleCarrello", carrello.getTotale());
					out.print(json.toString());
					out.flush();
					return;

				}
				case "remove": {
					int codiceProdotto = Integer.parseInt(request.getParameter("codice"));
					Prodotto p = new Prodotto();
					p.setCodice(codiceProdotto);
					ProdottoCarrello pc = new ProdottoCarrello(p, 0);
					carrello.rimuoviDalCarrello(pc);
					break;
				}

				case "delete":
					carrello = new Carrello();
					session.setAttribute("carrello", carrello);
					break;
				case "view":
					break;

				default:
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
					dispatcher.forward(request, response);
					return;

				}

			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Errore generico.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		session.setAttribute("carrello", carrello);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/carrelloView.jsp");
		dispatcher.forward(request, response);
	}

}
