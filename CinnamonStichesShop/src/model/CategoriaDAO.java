package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class CategoriaDAO {
	private DataSource ds = null;

	public CategoriaDAO(DataSource ds) {
		this.ds = ds;
	}

	public synchronized void doSave(Categoria ca) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("INSERT INTO categoria" + "(nome,descrizione) VALUES (?,?)");
			ps.setString(1, ca.getNome());
			ps.setString(2, ca.getDescrizione());
			if (ps.executeUpdate() != 1)
				throw new Exception("Errore INSERT INTO");
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			ca.setId(id);

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

	public synchronized void doDelete(int id) throws SQLException {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ds.getConnection();
			ps = cn.prepareStatement("DELETE FROM categoria WHERE id =?");
			ps.setInt(1, id);
			if (ps.executeUpdate() != 1) {
				throw new Exception("Errore aggiornamento");
			}
		} catch (Exception e) {
			System.out.println("Errore:" + e.getMessage());
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

	public synchronized void doUpdate(Categoria ca) throws SQLException {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ds.getConnection();
			ps = cn.prepareStatement("UPDATE categoria " + "SET nome=?,descrizione=?" + " WHERE id=?");
			ps.setString(1, ca.getNome());
			ps.setString(2, ca.getDescrizione());
			ps.setInt(3, ca.getId());

			if (ps.executeUpdate() != 1) {
				throw new Exception("Errore aggiornamento");
			}
		} catch (Exception e) {
			System.out.println("Errore:" + e.getMessage());
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

	public synchronized Collection<Categoria> doRetrieveAll(String order) throws SQLException {
		Connection cn = null;
		PreparedStatement ps = null;
		Collection<Categoria> cl = new LinkedList<Categoria>();
		try {
			cn = ds.getConnection();
			ps = cn.prepareStatement("SELECT * FROM categoria ORDER BY ?");
			if (order != null && !order.equals(""))
				ps.setString(1, order);
			else
				ps.setString(1, "id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categoria ca = new Categoria();
				ca.setId(rs.getInt("id"));
				ca.setNome(rs.getString("nome"));
				ca.setDescrizione(rs.getString("descrizione"));
				cl.add(ca);
			}
		} catch (Exception e) {
			System.out.println("Erroredao:" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (cn != null)
					cn.close();
			}
		}
		return cl;
	}

	public synchronized Categoria doRetriveById(int id) throws SQLException {
		Connection cn = null;
		PreparedStatement ps = null;
		Categoria cat =new Categoria();
		try {
			cn = ds.getConnection();
			ps = cn.prepareStatement("SELECT * FROM categoria WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categoria ca = new Categoria();
				ca.setNome(rs.getString("nome"));
				ca.setDescrizione(rs.getString("descrizione"));
			}
		} catch (Exception e) {
			System.out.println("Errore:" + e.getMessage());

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (cn != null)
					cn.close();
			}
		}
		return cat;
	}
	public synchronized ArrayList<Categoria> doRetriveByNome(String nome) throws SQLException {
		Connection cn = null;
		PreparedStatement ps = null;
		ArrayList<Categoria> cl = new ArrayList<Categoria>();
		try {
			cn = ds.getConnection();
			ps = cn.prepareStatement("SELECT * FROM categoria WHERE nome=?");
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categoria ca = new Categoria();
				ca.setNome(rs.getString("nome"));
				ca.setDescrizione(rs.getString("descrizione"));
				cl.add(ca);
			}
		} catch (Exception e) {
			System.out.println("Errore:" + e.getMessage());

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (cn != null)
					cn.close();
			}
		}
		return cl;
	}
}
