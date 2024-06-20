package control.Admin;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Ordine;
import model.OrdineDAO;

/**
 * Servlet implementation class AdminOrdiniServlet
 */
@WebServlet("/admin/Ordini")
public class AdminOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminOrdiniServlet() {
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
		String data1 = request.getParameter("filterDate1");
		String data2 = request.getParameter("filterDate2");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		String reAddress;

		List<Ordine> listaOrdini = null;
		if (data1 != null && data2 != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate inizio = LocalDate.parse(data1, formatter);
			LocalDate fine = LocalDate.parse(data2, formatter);
			OrdineDAO odao = new OrdineDAO(ds);
			try {
				listaOrdini = odao.doRetrieveByDateRange(inizio, fine);
				request.setAttribute("ordini", listaOrdini);
				reAddress = "/view/ordiniAmministratore.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
				reAddress = "/view/error.jsp";
				request.setAttribute("errorMessage", "Errore interno.");
			}
		} else {
			reAddress = "/view/error.jsp";
			request.setAttribute("errorMessage", "Date non valide.");
		}

		request.getRequestDispatcher(reAddress).forward(request, response);

	}

}
