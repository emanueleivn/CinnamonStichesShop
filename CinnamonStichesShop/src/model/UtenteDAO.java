package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

public class UtenteDAO {
	private DataSource ds = null;

	public UtenteDAO(DataSource ds) {
		this.ds = ds;
	}

	public synchronized void doSave(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(
					"INSERT INTO utente (nome, cognome, email, pass, via, cap, citta, username) VALUES (?, ?, ?, ?, ?, ?, ?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, utente.getNome());
			ps.setString(2, utente.getCognome());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getPassword());
			ps.setString(5, utente.getVia());
			ps.setString(6, utente.getCap());
			ps.setString(7, utente.getCittà());
			ps.setString(8, utente.getUsername());

			if (ps.executeUpdate() != 1)
				throw new Exception("Errore INSERT INTO");

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int idUtente = rs.getInt(1);
				utente.setId(idUtente);
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
	}

	public synchronized void doDelete(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("DELETE FROM utente WHERE username = ?");
			ps.setString(1, username);
			if (ps.executeUpdate() != 1) {
				throw new Exception("Errore eliminazione utente");
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

	public synchronized void doUpdate(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(
					"UPDATE utente SET nome = ?, cognome = ?, email = ?, pass = ?, via = ?, cap = ?, citta = ?,username = ? WHERE cod_ut = ?");
			ps.setString(1, utente.getNome());
			ps.setString(2, utente.getCognome());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getPassword());
			ps.setString(5, utente.getVia());
			ps.setString(6, utente.getCap());
			ps.setString(7, utente.getCittà());
			ps.setString(8, utente.getUsername());
			ps.setInt(9, utente.getId());
			System.out.println(utente.getId());
			if (ps.executeUpdate() != 1) {
				throw new Exception("Errore aggiornamento utente");
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

	public synchronized ArrayList<Utente> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Utente> utenti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM utente ORDER BY ?");
			if (order != null && !order.equals(""))
				ps.setString(1, order);
			else
				ps.setString(1, "idUtente");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Utente utente = new Utente();
				utente.setId(rs.getInt("cod_ut"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setUsername(rs.getString("username"));
				utente.setEmail(rs.getString("email"));
				utente.setPassword(rs.getString("pass"));
				utente.setVia(rs.getString("via"));
				utente.setCap(rs.getString("cap"));
				utente.setCittà(rs.getString("citta"));
				utente.setIsAdmin(rs.getBoolean("isAdmin"));
				utenti.add(utente);
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
		return utenti;
	}

	public synchronized Utente doRetrieveById(int idUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		Utente utente = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("SELECT * FROM utente WHERE idUtente = ?");
			ps.setInt(1, idUtente);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				utente = new Utente();
				utente.setId(rs.getInt("cod_ut"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setUsername(rs.getString("username"));
				utente.setEmail(rs.getString("email"));
				utente.setPassword(rs.getString("pass"));
				utente.setVia(rs.getString("via"));
				utente.setCap(rs.getString("cap"));
				utente.setCittà(rs.getString("citta"));
				utente.setIsAdmin(rs.getBoolean("isAdmin"));
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
		return utente;
	}

	public synchronized Utente doRetrieveByUserPassword(String username, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		Utente utente = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("SELECT * FROM " + "utente WHERE username=? AND pass=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				utente = new Utente();
				utente.setId(rs.getInt("cod_ut"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setUsername(rs.getString("username"));
				utente.setEmail(rs.getString("email"));
				utente.setPassword(rs.getString("pass"));
				utente.setVia(rs.getString("via"));
				utente.setCap(rs.getString("cap"));
				utente.setCittà(rs.getString("citta"));
				utente.setIsAdmin(rs.getBoolean("isAdmin"));

			}
		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (con != null)
					con.close();
			}
		}
		return utente;

	}

	public synchronized Utente checkingUser(String username) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		Utente utente = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("SELECT * FROM " + "utente WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				utente = new Utente();
			}
		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (con != null)
					con.close();
			}
		}
		return utente;
	}
	public synchronized Utente checkingEmail(String email) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		Utente utente = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("SELECT * FROM " + "utente WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				utente = new Utente();
			}
		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (con != null)
					con.close();
			}
		}
		return utente;

	}
}
