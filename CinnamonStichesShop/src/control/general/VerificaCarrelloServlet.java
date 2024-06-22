package control.general;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONObject;

import model.Carrello;
import model.Prodotto;
import model.ProdottoCarrello;
import model.ProdottoDAO;

/**
 * Servlet implementation class VerificaCarrelloServlet
 */
@WebServlet("/VerificaCarrelloServlet")
public class VerificaCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerificaCarrelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
		    ProdottoDAO pDao = new ProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
		    try {
		        List<Prodotto> listaProdottiVendibili = pDao.doRetrieveAll();
		        if (cart == null || listaProdottiVendibili == null) {
		            throw new SQLException();
		        }
		        String nomi = "";
		        boolean check = true;
		        for (ProdottoCarrello p : cart.getProdottiCarrello()) {
		            if (!listaProdottiVendibili.contains(p.getProdotto())) {
		                nomi += p.getProdotto().getNome() + "\n";
		                check = false;
		            }
		        }
		        response.setContentType("application/json");
		        PrintWriter out = response.getWriter();
		        JSONObject json = new JSONObject();
		        json.put("check", check);
		        json.put("nomi", nomi);
		        out.print(json.toString());
		        out.flush();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        request.setAttribute("errorMessage", "Errore aggiornamento pagina.");
		        request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		        return;
		    }
		}


}
