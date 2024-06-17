package control.Admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirAdminServlet
 */
@WebServlet("/PaginaAmministratore")
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
		String azione = request.getParameter("azione");
		String reAddress ="";
		switch (azione) {
		case "add":reAddress="/view/aggiungiProdotto.jsp";
		case "mod":reAddress="/view/modificaProdotto.jsp";
		case "showOrders":reAddress="/view/ordini.jsp";
		case "showUsers":reAddress="/view/utenti.jsp";
		default: reAddress= "/view/amministrazione.jsp";
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
