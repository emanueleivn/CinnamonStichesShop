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
import model.ProdottoCarrello;
import model.ProdottoDAO;
import model.Utente;
import model.Carrello;
import model.PreferitiDAO;

@WebServlet("/Preferiti")
public class PreferitiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String returnAddress;
		String action=request.getParameter("actionFav");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		PreferitiDAO pr = new PreferitiDAO(ds);
		Utente u = (Utente)request.getSession().getAttribute("user");
		if(action!=null && u !=null) {
			String codice = request.getParameter("codiceProdotto");
			switch(action) {
				
			case "addFavorite" :
				try {
					pr.addFavorite(u.getId(), Integer.parseInt(codice));
					break;
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
					String errorMessage = " Errore Generico, dati inseriti non accettabili";
					request.setAttribute("errorMessage", errorMessage);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
					dispatcher.forward(request, response);
					return;
				}
				
			case "addCart":
					ProdottoDAO pdao = new ProdottoDAO(ds);
				try {
					Prodotto p = pdao.doRetrieveById(Integer.parseInt(codice));
					ProdottoCarrello pc = new ProdottoCarrello(p, 1);
					Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
					carrello.aggiungiAlCarrello(pc);
					request.getSession().setAttribute("carrello", carrello);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Carrello");
					dispatcher.forward(request, response);
					return;
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
					String errorMessage = " Errore Generico, dati inseriti non accettabili";
					request.setAttribute("errorMessage", errorMessage);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
					dispatcher.forward(request, response);
					return;
				}
					
			case "remove" : 
				try {
					pr.removeFavorite(u.getId(), Integer.parseInt(codice));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
					String errorMessage = " Errore Generico, dati inseriti non accettabili";
					request.setAttribute("errorMessage", errorMessage);
					RequestDispatcher dispatcher2 = request.getRequestDispatcher("/view/error.jsp");
					dispatcher2.forward(request, response);
					return;
				}
				break;
			
			}
			
		}
		if (u == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
		    return;
		} else {
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
