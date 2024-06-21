package control.Admin;

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

import org.json.JSONArray;
import org.json.JSONObject;

import model.Ordine;
import model.OrdineDAO;

/**
 * Servlet implementation class CercaOrdiniServlet
 */
@WebServlet("/admin/CercaByCliente")
public class CercaOrdiniServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7559187701905300987L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CercaOrdiniServlet() {
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
		String cerca = request.getParameter("inputCliente");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		List<Ordine> listaOrdini = null;
		OrdineDAO o = new OrdineDAO(ds);
		try {
			listaOrdini = o.doRetrieveByIdCliente(Integer.parseInt(cerca));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
			for (Ordine ordine : listaOrdini) {
				JSONObject json = new JSONObject();
				json.put("codice", ordine.getCodiceOrdine());
				json.put("idCliente", ordine.getIdCliente());
				json.put("data", ordine.getData());
				json.put("indirizzo", ordine.getIndirizzoSpedizione());
				json.put("totale", ordine.getTot());
				jsonArray.put(json);
			}
			//System.out.println(jsonArray.toString());
			out.print(jsonArray.toString());
			out.flush();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Errore interno.");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}

	}
}
