package general;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.Prodotto;
import model.ProdottoCarrello;

/**
 * Servlet implementation class CarrelloServlet
 */
@WebServlet(name = "Carrello", urlPatterns = { "/Carrello" })
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if(carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }else {
        	String action= (String) request.getAttribute("action");
        	Prodotto p = (Prodotto) request.getAttribute("prodotto");
        	ProdottoCarrello pc = new ProdottoCarrello(p, (Integer) request.getAttribute("quantity"));
        	switch (action) {
			case "add": {
				carrello.aggiungiAlCarrello(pc);
				session.setAttribute("carrello", carrello);
			}
			case "remove":{
				carrello.rimuoviDalCarrello((ProdottoCarrello) request.getAttribute("prodotto"));
				session.setAttribute("carrello", carrello);
			}
			case "decr":{
				carrello.decrementa(pc);
				session.setAttribute("carrello", carrello);
			}
			case "incr":{
				carrello.incrementa(pc);
				session.setAttribute("carrello", carrello);
			}
			case "view":
				break;
			default:
				 RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
			     dispatcher.forward(request, response);
			     return;
			}
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/carrelloView.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
