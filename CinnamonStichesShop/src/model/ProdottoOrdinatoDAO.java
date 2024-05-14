package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ProdottoOrdinatoDAO {
	private DataSource ds = null;

	public ProdottoOrdinatoDAO(DataSource ds) {
		this.ds = ds;
	}

	public synchronized void doSave(ProdottoOrdinato prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		byte[] bt = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("INSERT INTO prodottoOrdinato (costo, nome, quantità,taglia,descrizione) VALUES (?, ?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setFloat(1, prodotto.getCosto());
			ps.setString(2, prodotto.getNome());
			ps.setInt(3, prodotto.getQuantità());
			ps.setString(4, prodotto.getTaglia());
			ps.setString(5, prodotto.getDescrizione());


			if (ps.executeUpdate() != 1)
				throw new Exception("Errore INSERT INTO");

			ResultSet rs = ps.getGeneratedKeys();
			int idProdotto = rs.getInt(1);
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

	public synchronized ArrayList<ProdottoOrdinato> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<ProdottoOrdinato> prodotti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM prodottoOrdinato ORDER BY idProdotto");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProdottoOrdinato prodotto = new ProdottoOrdinato();
				prodotto.setCodice(rs.getInt("idProdotto"));
				prodotto.setCosto(rs.getFloat("costo"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setQuantità(rs.getInt("quantità"));
				prodotto.setTaglia(rs.getString("taglia"));
				prodotto.setDescrizione(rs.getString("descrizione"));
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

	public synchronized ProdottoOrdinato doRetrieveById(int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ProdottoOrdinato prodotto = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM prodottoOrdinato WHERE idProdotto = ?");
			ps.setInt(1, codice);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				prodotto = new ProdottoOrdinato();
				prodotto.setCodice(rs.getInt("codice"));
				prodotto.setCosto(rs.getFloat("costo"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setTaglia(rs.getString("taglia"));
				prodotto.setDescrizione(rs.getString("descrizione"));
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

	private synchronized ArrayList<ImmagineProdotto> doRetrieveImmagine(int idProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<ImmagineProdotto> immagini = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM immaginiProdotto WHERE idProdotto = ?");
			ps.setInt(1, idProdotto);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ImmagineProdotto immagine = new ImmagineProdotto();
				immagine.setId(rs.getInt("id"));
				immagine.setIdProdotto(rs.getInt("idProdotto"));
				immagine.setImmagine(rs.getBytes("immagine"));
				immagini.add(immagine);
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
		return immagini;
	}
}
