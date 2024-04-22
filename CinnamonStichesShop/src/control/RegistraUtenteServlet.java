package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;

/**
 * Servlet implementation class RegistraUtenteServlet
 */
@WebServlet(name="RegistraUtenteServlet" , value = "/RegistraUtenteServlet")
public class RegistraUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistraUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /*to do controllo parametri*/
		 String email = request.getParameter("email");
         String password = request.getParameter("password");
         String nome = request.getParameter("nome");
         String cognome = request.getParameter("cognome");
         String via = request.getParameter("via");
         String cap = request.getParameter("cap");
         String città = request.getParameter("city");
         
         Utente ut= new Utente();
         ut.setNome(nome);
         ut.setCognome(cognome);
         ut.setEmail(email);
         ut.setPassword(password);
         ut.setVia(via);
         ut.setCap(cap);
         ut.setCittà(città);
         ut.setIsAdmin(false);
         /*se passano tutti i controlli salvo l'utente, altrimenti rimando
          * alla pagina di registrazione visualzizzando gli errori
          */
         request.setAttribute("errors", nome+cognome+email+password+via+cap+città);
         RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/Registrazione.jsp");
 		 dispatcher.forward(request, response);
 		 
         HttpSession session= request.getSession();
         session.setAttribute("utente", ut);
         session.setAttribute("Loggato", true);
         
         /*ritorna controllo pagina principale*/
         
	}

}
