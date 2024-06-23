package control.Admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import model.Prodotto;
import model.ProdottoDAO;
import model.Sanitizer;

/**
 * Servlet implementation class AggiornaProdottoServlet
 */
@WebServlet("/admin/AggiornaProdotto")
@MultipartConfig(maxFileSize = 16177215)
public class AggiornaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiornaProdottoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codice = request.getParameter("codice");
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzo");
		if (!Sanitizer.sanitizeInput(nome + descrizione + prezzo)) {
			request.setAttribute("errorMessage", "Parametri Non Corretti");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		Part filePart = request.getPart("file");
		System.out.println(filePart.getName());
		String path = filePart.getSubmittedFileName();
		if (path != null && path != "") {
			try {
				ProdottoDAO pDao = new ProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
				Prodotto p = pDao.doRetrieveById(Integer.parseInt(codice));
				p.setNome(nome.trim());
				p.setCosto(Float.parseFloat(prezzo));
				p.setDescrizione(descrizione.trim());
				p.setImmagine(path);
				pDao.doUpdate(p);
				List<Prodotto> prodotti = pDao.doRetrieveAll();
				request.setAttribute("prodotti", prodotti);
				String uploadPath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + "products";
				File uploadDir = new File(uploadPath);
	            if (!uploadDir.exists()) {
	                uploadDir.mkdirs();
	            }
	            File file = new File(uploadDir, path);
	            try (InputStream fileContent = filePart.getInputStream()) {
	                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            }
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/amministraProdotti.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Errore interno.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
	}
}
