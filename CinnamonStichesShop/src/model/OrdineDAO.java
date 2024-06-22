package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class OrdineDAO {
	private DataSource ds = null;

	public OrdineDAO(DataSource ds) {
		this.ds = ds;
	}

	public synchronized void doSave(Ordine o, Carrello carrello) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(
					"INSERT INTO ordine (data, stato, quantità, tot, utente_cod, ind_spedizione) VALUES (curdate(), ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, o.getStato());
			ps.setInt(2, carrello.getNumeroProdotti());
			ps.setFloat(3, carrello.getTotale());
			ps.setInt(4, o.getIdCliente());
			ps.setString(5, o.getIndirizzoSpedizione());

			if (ps.executeUpdate() != 1) {
				throw new Exception("Errore INSERT INTO ordine");
			}

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {

			} else {
				throw new SQLException("Errore nel recupero della chiave generata per ordine.");
			}

			for (ProdottoCarrello p : carrello.getProdottiCarrello()) {
				PreparedStatement ps2 = connection
						.prepareStatement("INSERT INTO Contiene (codiceOrdine, idProdotto, quantità) VALUES (?, ?, ?)");
				ps2.setInt(1, rs.getInt(1));
				ps2.setInt(2, p.getProdotto().getCodice());
				ps2.setInt(3, p.getQuantita());

				if (ps2.executeUpdate() != 1) {
					throw new Exception("Errore INSERT INTO Contiene.");
				}

				ps2.close();
			}

			carrello.setProdottiCarrello(null);

		} catch (Exception e) {
			System.out.println("ErroreDao:" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	public synchronized ArrayList<Ordine> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Ordine> ordini = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM ordine ORDER BY ?");
			if (order != null && !order.equals(""))
				ps.setString(1, order);
			else
				ps.setString(1, "codiceOrdine");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ordine ordine = new Ordine();
				ordine.setCodiceOrdine(rs.getInt("codice"));
				Date d = rs.getDate("data");
				ordine.setData(d.toLocalDate());
				ordine.setStato(rs.getString("stato"));
				ordine.setIndirizzoSpedizione(rs.getString("ind_spedizione"));
				ordine.setQuantitàProdotti(rs.getInt("quantità"));
				ordine.setTot(rs.getFloat("tot"));
				ordine.setIdCliente(rs.getInt("utente_cod"));
				ordini.add(ordine);
			}
		} catch (Exception e) {
			System.out.println("Errore DAO: " + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return ordini;
	}

	public synchronized Ordine doRetriveByCodiceOrdine(int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Ordine ordine = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM ordine WHERE codice = ?");
			ps.setInt(1, codice);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ordine = new Ordine();
				ordine.setCodiceOrdine(rs.getInt("codice"));
				ordine.setData(rs.getDate("data").toLocalDate());
				ordine.setQuantitàProdotti(rs.getInt("quantità"));
				ordine.setIndirizzoSpedizione(rs.getString("ind_spedizione"));
				ordine.setTot(rs.getFloat("tot"));
				ordine.setIdCliente(rs.getInt("utente_cod"));

				ps1 = connection.prepareStatement("SELECT idProdotto, quantità FROM Contiene WHERE codiceOrdine = ?");
				ps1.setInt(1, codice);
				ResultSet rs1 = ps1.executeQuery();

				while (rs1.next()) {
					ProdottoDAO pDao = new ProdottoDAO(ds);
					Prodotto p = pDao.doRetrieveById(rs1.getInt("idProdotto"));
					ProdottoCarrello pc = new ProdottoCarrello(p, rs1.getInt("quantità"));
					ordine.aggiungiProdotto(pc);
				}
			} else {
				System.out.println("Ordine non trovato per il codice: " + codice);
			}
		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (ps1 != null)
					ps1.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return ordine;
	}

	public synchronized ArrayList<Ordine> doRetrieveByIdCliente(int idUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Ordine> ordini = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM ordine WHERE utente_cod = ?");
			ps.setInt(1, idUtente);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ordine ordine = new Ordine();
				ordine.setCodiceOrdine(rs.getInt("codice"));
				ordine.setData(rs.getDate("data").toLocalDate());
				ordine.setQuantitàProdotti(rs.getInt("quantità"));
				ordine.setIndirizzoSpedizione(rs.getString("ind_spedizione"));
				ordine.setTot(rs.getFloat("tot"));
				ordine.setIdCliente(rs.getInt("utente_cod"));

				OrdineDAO o = new OrdineDAO(ds);
				ordine.setProdotti(o.doRetrieveProdottiByCodiceOrdine(ordine.getCodiceOrdine()).getProdotti());

				ordini.add(ordine);
			}
		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return ordini;
	}

	public Ordine doRetrieveProdottiByCodiceOrdine(int codiceOrdine) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Ordine ordine = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM ordine WHERE codice = ?");
			ps.setInt(1, codiceOrdine);
			rs = ps.executeQuery();

			if (rs.next()) {
				ordine = new Ordine();
				ordine.setCodiceOrdine(rs.getInt("codice"));
				ordine.setData(rs.getDate("data").toLocalDate());
				ordine.setQuantitàProdotti(rs.getInt("quantità"));
				ordine.setIndirizzoSpedizione(rs.getString("ind_spedizione"));
				ordine.setTot(rs.getFloat("tot"));
				ordine.setIdCliente(rs.getInt("utente_cod"));

				ps1 = connection.prepareStatement("SELECT idProdotto, quantità FROM Contiene WHERE codiceOrdine = ?");
				ps1.setInt(1, codiceOrdine);
				rs1 = ps1.executeQuery();

				ProdottoDAO pDao = new ProdottoDAO(ds);
				while (rs1.next()) {
					Prodotto p = pDao.doRetrieveById(rs1.getInt("idProdotto"));
					ProdottoCarrello pc = new ProdottoCarrello(p, rs1.getInt("quantità"));
					ordine.aggiungiProdotto(pc);
				}
			} else {
				System.out.println("Ordine non trovato per il codice: " + codiceOrdine);
			}
		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (rs1 != null)
					rs1.close();
				if (ps != null)
					ps.close();
				if (ps1 != null)
					ps1.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return ordine;
	}

	public synchronized ArrayList<Ordine> doRetrieveByDateRange(LocalDate date1, LocalDate date2) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Ordine> ordini = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM ordine WHERE data BETWEEN ? AND ?");
			ps.setDate(1, Date.valueOf(date1));
			ps.setDate(2, Date.valueOf(date2));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ordine ordine = new Ordine();
				ordine.setCodiceOrdine(rs.getInt("codice"));
				ordine.setData(rs.getDate("data").toLocalDate());
				ordine.setStato(rs.getString("stato"));
				ordine.setIndirizzoSpedizione(rs.getString("ind_spedizione"));
				ordine.setQuantitàProdotti(rs.getInt("quantità"));
				ordine.setTot(rs.getFloat("tot"));
				ordine.setIdCliente(rs.getInt("utente_cod"));
				ordini.add(ordine);
			}
		} catch (Exception e) {
			System.out.println("Errore DAO: " + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return ordini;
	}
}
