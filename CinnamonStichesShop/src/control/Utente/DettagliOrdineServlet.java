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

import model.Ordine;
import model.OrdineDAO;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class DettagliOrdineServlet
 */
@WebServlet("/DettagliOrdine")
public class DettagliOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliOrdineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codice = request.getParameter("codice");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		OrdineDAO oDao = new OrdineDAO(ds);
		try {
			Ordine ordine = oDao.doRetriveByCodiceOrdine(Integer.parseInt(codice));
			UtenteDAO u = new UtenteDAO(ds);
			Utente utente = u.doRetrieveById(ordine.getIdCliente());
			request.setAttribute("ordine", ordine);
			request.setAttribute("utente", utente);
			request.setAttribute("prodottiOrdine", ordine.getProdotti());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/dettagliOrdine.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			String errorMessage = " Errore recupero informazioni";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}
