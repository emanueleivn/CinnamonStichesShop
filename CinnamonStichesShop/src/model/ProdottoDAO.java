package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ProdottoDAO {
	private DataSource ds = null;

	public ProdottoDAO(DataSource ds) {
		this.ds = ds;
	}

	

	public synchronized void doSave(Prodotto prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("INSERT INTO prodotto (costo, descrizione, nome, immagine) VALUES (?, ?, ?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setFloat(1, prodotto.getCosto());
			ps.setString(2, prodotto.getDescrizione());
			ps.setString(3, prodotto.getNome());
			ps.setString(4, prodotto.getImmagine());

			if (ps.executeUpdate() != 1)
				throw new Exception("Errore INSERT INTO");

			int idProdotto =ps.getGeneratedKeys().getInt(1);
			prodotto.setCodice(idProdotto);
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
	}

	public synchronized void doDelete(int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("UPDATE prodotto SET isDisponibile = false  WHERE codice = ?");
			ps.setInt(1, codice);
			if (ps.executeUpdate() != 1) {
				throw new Exception("Errore eliminazione prodotto");
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
	}
	public synchronized void doDisponibile(int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("UPDATE prodotto SET isDisponibile = true  WHERE codice = ?");
			ps.setInt(1, codice);
			if (ps.executeUpdate() != 1) {
				throw new Exception("Errore eliminazione prodotto");
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
	}
	public synchronized void doUpdate(Prodotto prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(
					"UPDATE prodotto SET costo = ?, descrizione = ?, categoria = ? , nome = ? ,isDisponibile =?, immagine=? WHERE codice = ?");
			ps.setFloat(1, prodotto.getCosto());
			ps.setString(2, prodotto.getDescrizione());
			ps.setInt(3, prodotto.getIdCategoria());
			ps.setString(4, prodotto.getNome());
			ps.setBoolean(5, prodotto.getIsDisp());
			ps.setString(6, prodotto.getImmagine());
			ps.setInt(7, prodotto.getCodice());
			if (ps.executeUpdate() != 1) {
				throw new Exception("Errore aggiornamento prodotto");
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
	}

	public synchronized ArrayList<Prodotto> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM prodotto WHERE isDisponibile=true ORDER BY codice");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto prodotto = new Prodotto();
				prodotto.setCodice(rs.getInt("codice"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setCosto(rs.getFloat("costo"));
				prodotto.setDescrizione(rs.getString("descrizione"));
				prodotto.setIdCategoria(rs.getInt("categoria"));
				prodotto.setIsDisp(true);
				prodotto.setImmagine(rs.getString("immagine"));
				prodotti.add(prodotto);
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
		return prodotti;
	}
	public synchronized ArrayList<Prodotto> doRetrieveAllSaleable() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM prodotto ORDER BY nome");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto prodotto = new Prodotto();
				prodotto.setCodice(rs.getInt("codice"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setCosto(rs.getFloat("costo"));
				prodotto.setDescrizione(rs.getString("descrizione"));
				prodotto.setIdCategoria(rs.getInt("categoria"));
				prodotto.setIsDisp(rs.getBoolean("isDisponibile"));
				prodotto.setImmagine(rs.getString("immagine"));
				prodotti.add(prodotto);
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
		return prodotti;
	}

	public synchronized Prodotto doRetrieveById(int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		Prodotto prodotto = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM prodotto WHERE codice = ?");
			ps.setInt(1, codice);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				prodotto = new Prodotto();
				prodotto.setCodice(rs.getInt("codice"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setCosto(rs.getFloat("costo"));
				prodotto.setDescrizione(rs.getString("descrizione"));
				prodotto.setIdCategoria(rs.getInt("categoria"));
				prodotto.setIsDisp(rs.getBoolean("isDisponibile"));
				prodotto.setImmagine(rs.getString("immagine"));
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
		return prodotto;
	}
}
