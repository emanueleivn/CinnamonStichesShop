package control.Prodotto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Prodotto;
import model.ProdottoDAO;

@WebServlet("/OrdinaElementi")
public class OrdinaElementiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrdinaElementiServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ordine = request.getParameter("ordine");

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProdottoDAO prodottoDAO = new ProdottoDAO(ds);
		List<Prodotto> prodotti;

		try {
			prodotti = prodottoDAO.doRetrieveAll();

			if (ordine != null) {
				switch (ordine) {
				case "prezzocrescente":
					Collections.sort(prodotti, Comparator.comparing(Prodotto::getCosto));
					break;
				case "prezzodecrescente":
					Collections.sort(prodotti, Comparator.comparing(Prodotto::getCosto).reversed());
					break;
				case "a-z":
					Collections.sort(prodotti, Comparator.comparing(Prodotto::getNome));
					break;
				case "z-a":
					Collections.sort(prodotti, Comparator.comparing(Prodotto::getNome).reversed());
					break;
				}
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();

			for (Prodotto prodotto : prodotti) {
				JSONObject json = new JSONObject();
				json.put("codice", prodotto.getCodice());
				json.put("nome", prodotto.getNome());
				json.put("descrizione", prodotto.getDescrizione());
				json.put("costo", prodotto.getCosto());
				json.put("isDisp", prodotto.getIsDisp());
				json.put("immagine", prodotto.getImmagine());
				jsonArray.put(json);
			}

			out.print(jsonArray.toString());
			out.flush();
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Errore interno.");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}}
