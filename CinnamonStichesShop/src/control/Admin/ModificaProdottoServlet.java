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

/**
 * Servlet implementation class ModificaProdottoServlet
 */
@WebServlet("/admin/ModificaProdotto")
public class ModificaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaProdottoServlet() {
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
		ProdottoDAO p = new ProdottoDAO(ds);
		String reAddress="/view/error.jsp";
		request.setAttribute("errorMessage", "Errore nella modifica del prodotto.");
		String azione=request.getParameter("azioneAdmin");
		if(azione.equals("update")) {
			try {
				Prodotto prodotto = p.doRetrieveById(Integer.parseInt(request.getParameter("codice")));
				request.setAttribute("prodotto", prodotto);
				reAddress="/view/modificaProdotto.jsp";
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
				reAddress="/view/error.jsp";
				request.setAttribute("errorMessage", "Errore nella modifica del prodotto.");
			}
		}else if(azione.equals("delete")) {
			try {
				p.doDelete(Integer.parseInt(request.getParameter("codice")));
				List<Prodotto> prodotti = p.doRetrieveAllSaleable();
				request.setAttribute("prodotti", prodotti);
				reAddress="/view/amministraProdotti.jsp";
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
				reAddress="/view/error.jsp";
				request.setAttribute("errorMessage", "Errore nella cancellazione del prodotto.");
				
			}
		}else if(azione.equals("alive")) {
			try {
				p.doDisponibile(Integer.parseInt(request.getParameter("codice")));
				List<Prodotto> prodotti = p.doRetrieveAllSaleable();
				request.setAttribute("prodotti", prodotti);
				reAddress="/view/amministraProdotti.jsp";
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
				reAddress="/view/error.jsp";
				request.setAttribute("errorMessage", "Errore nella cancellazione del prodotto.");
				
			}
		}
		RequestDispatcher dispatcher= request.getRequestDispatcher(reAddress);
		dispatcher.forward(request, response);
	}

}
