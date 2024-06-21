package control.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Ordine;
import model.OrdineDAO;

/**
 * Servlet implementation class CercaOrdineIdServlet
 */
@WebServlet("/admin/CercaByOrdine")
public class CercaOrdineIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CercaOrdineIdServlet() {
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
		String cerca = request.getParameter("inputOrdine");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Ordine ordine = null;
		OrdineDAO o = new OrdineDAO(ds);
		try {
			ordine = o.doRetriveByCodiceOrdine(Integer.parseInt(cerca));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("codice", ordine.getCodiceOrdine());
			json.put("idCliente", ordine.getIdCliente());
			json.put("data", ordine.getData());
			json.put("indirizzo", ordine.getIndirizzoSpedizione());
			json.put("totale", ordine.getTot());
			out.print(json.toString());
			out.flush();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Errore interno.");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}

}
