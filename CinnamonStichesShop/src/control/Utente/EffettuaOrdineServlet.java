package control.Utente;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Carrello;
import model.Ordine;
import model.OrdineDAO;
import model.Utente;

/**
 * Servlet implementation class EffettuaOrdineServlet
 */
@WebServlet("/EffettuaOrdine")
public class EffettuaOrdineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EffettuaOrdineServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Utente user = (Utente) session.getAttribute("user");

        if (carrello == null || user == null) {
            String errore = "Errore sconosciuto.";
            request.setAttribute("errorMessage", errore);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Ordine ordine = new Ordine();
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ordine.setProdotti(carrello.getProdottiCarrello());
        ordine.setData(LocalDate.now()); 
        ordine.setStato("In Lavorazione"); 
        ordine.setQuantitàProdotti(carrello.getNumeroProdotti());
        ordine.setTot(carrello.getTotale());
        ordine.setIdCliente(user.getId());
        ordine.setIndirizzoSpedizione(user.getVia()+" "+user.getCittà()+" "+user.getCap());
        OrdineDAO ordineDao = new OrdineDAO(ds);
        try {
            ordineDao.doSave(ordine, carrello);
        } catch (SQLException e) {
            e.printStackTrace();
            String errore = "Errore sconosciuto.";
            request.setAttribute("errorMessage", errore);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        carrello = new Carrello();
        session.setAttribute("carrello", carrello);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Ordini");
        dispatcher.forward(request, response);
    }
}

