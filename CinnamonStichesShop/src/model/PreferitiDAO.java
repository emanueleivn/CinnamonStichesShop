package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class PreferitiDAO {
	private DataSource ds = null;
	public PreferitiDAO(DataSource ds) {
		this.ds=ds;
	}
	public synchronized void doSave(int idUtente) throws SQLException{
		Connection cn = null;
		PreparedStatement ps= null;
		try {
			cn= ds.getConnection();
			ps=cn.prepareStatement("INSERT INTO preferiti "+"(idUtente)"+"VALUES (?)");
			ps.setInt(1,idUtente);
			if (ps.executeUpdate() != 1)
				throw new Exception("Errore INSERT INTO");
		} catch (Exception e) {
			System.out.println("ErroreDao:" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (cn != null)
					cn.close();
			}
		}
	}
	public synchronized ArrayList<Prodotto> doRetrieveAllFavourite(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Prodotto> preferiti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM preferiti WHERE idUtente=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProdottoDAO p = new ProdottoDAO(ds);
				preferiti.add(p.doRetrieveById(rs.getInt("idProdotto")));
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
		return preferiti;
	}
	public synchronized ArrayList<Prodotto> addFavorite(int idUtente,int idProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Prodotto> preferiti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("INSERT INTO preferiti (idUtente,idProdotto) VALUES (?,?)");
			ps.setInt(1, idUtente);
			ps.setInt(2, idProdotto);
			if (ps.executeUpdate() != 1)
				throw new Exception("Errore INSERT INTO");
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
		return preferiti;
	}
	public synchronized ArrayList<Prodotto> removeFavorite(int idUtente,int idProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Prodotto> preferiti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("DELETE FROM preferiti WHERE idUtente=? AND idProdotto=?");
			ps.setInt(1, idUtente);
			ps.setInt(2, idProdotto);
			if (ps.executeUpdate() != 1)
				throw new Exception("Errore DELETE");
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
		return preferiti;
	}
}
