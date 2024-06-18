package control.Admin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import model.Prodotto;
import model.ProdottoDAO;
import model.Sanitizer;
import model.Upload;

/**
 * Servlet implementation class ModificaProdottoServlet
 */
@WebServlet("/ModificaProdotto")
public class ModificaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaProdottoServlet() {
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
		String azione = request.getParameter("azioneAdmin");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (azione != null) {
			String reAddress = "";
			switch (azione) {

			case "add":
				if (validate(request)) {
					String nome = request.getParameter("nomeProdotto");
					String descrizione = request.getParameter("descrizioneProdotto");
					if (nome != null && descrizione != null && !nome.equals("") && !descrizione.equals("")) {
						float prezzo = Float.parseFloat(request.getParameter("prezzoProdotto"));
						int categoria = Integer.parseInt(request.getParameter("idCategoria"));
						String uploadDirectory = request.getServletContext().getRealPath("/") + "images/products/";
						String fileName = uploadFile(request, uploadDirectory);
						if (fileName != null) {
							Prodotto p = new Prodotto();
							p.setNome(nome);
							p.setCosto(prezzo);
							p.setDescrizione(descrizione);
							p.setIdCategoria(categoria);
							p.setIsDisp(true);
							p.setImmagine(fileName);
							ProdottoDAO pDao = new ProdottoDAO(ds);
							try {
								pDao.doSave(p);
								reAddress = "/Catalogo";
							} catch (SQLException e) {
								e.printStackTrace();
								reAddress = "/view/error.jsp";
								break;
							}
						} else {
							reAddress = "/view/error.jsp";
						}
					} else {
						String error = "Errore durante il caricamento della pagina";
						request.setAttribute("message", error);
						reAddress = "/view/error.jsp";
					}
				}
			case "update":

				if (validate(request)) {
					String descrizione = request.getParameter("descrizioneProdotto");
					if (descrizione != null && !descrizione.equals("")) {
						float prezzo = Float.parseFloat(request.getParameter("prezzoProdotto"));
						int categoria = Integer.parseInt(request.getParameter("idCategoria"));
						boolean disp = Boolean.valueOf(request.getParameter("isDisp"));
						String uploadDirectory = request.getServletContext().getRealPath("/") + "images/products/";
						String fileName = uploadFile(request, uploadDirectory);
						if (fileName != null) {
							Prodotto p = new Prodotto();
							p.setCosto(prezzo);
							p.setDescrizione(descrizione);
							p.setIdCategoria(categoria);
							p.setIsDisp(disp);
							ProdottoDAO pDao = new ProdottoDAO(ds);
							try {
								pDao.doUpdate(p);
								reAddress = "/Catalogo";
							} catch (SQLException e) {
								e.printStackTrace();
								reAddress = "/view/error.jsp";
								break;
							}
						} else {
							reAddress = "/view/error.jsp";
						}
					} else {
						String error = "Errore durante il caricamento della pagina";
						request.setAttribute("message", error);
						reAddress = "/view/error.jsp";
					}
				}
			case "delete":
				ProdottoDAO pDao = new ProdottoDAO(ds);

				int i = Integer.parseInt(request.getParameter("idProdotto"));
				try {
					Prodotto p = pDao.doRetrieveById(i);
					pDao.doDelete(i);
					String uploadDirectory = request.getServletContext().getRealPath("/") + "images/products/";
					File imageFile = new File(uploadDirectory, p.getImmagine());
					if (imageFile.exists()) {
						imageFile.delete();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					request.setAttribute("message", "Errore generico");
					reAddress = "/view/error.jsp";
					break;
				}
				
			default:
				String error = "Errore durante il caricamento della pagina";
				request.setAttribute("message", error);
				reAddress = "/view/error.jsp";
				break;

			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(reAddress);
			dispatcher.forward(request, response);
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
		dispatcher.forward(request, response);
	}

	private static String uploadFile(HttpServletRequest request, String uploadDirectory)
			throws IOException, ServletException {
		Part filePart = request.getPart("file");
		if (filePart != null && Upload.isAllowedFileType(filePart.getContentType())
				&& Upload.isValidMagicNumber(filePart)) {
			return Upload.saveUploadedFile(filePart, uploadDirectory);
		}
		return null;
	}

	private static boolean validate(HttpServletRequest request) {
		return Sanitizer.sanitizeInput((String) request.getAttribute("nome"))
				&& Sanitizer.sanitizeInput((String) request.getAttribute("descrizione"))
				&& Sanitizer.sanitizeInput((String) request.getAttribute("costo"))
				&& Sanitizer.sanitizeInput((String) request.getAttribute("immagine"));
	}

}
