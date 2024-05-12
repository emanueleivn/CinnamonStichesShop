package model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ImmagineProdottoDAO {
	private DataSource ds = null;

	public ImmagineProdottoDAO(DataSource ds) {
		this.ds = ds;
	}

	public synchronized void doSave(ImmagineProdotto img) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("INSERT INTO immaginiProdotto" + "(id,idProdotto,immagine) VALUES (?,?,?)");
			ps.setInt(1, img.getId());
			ps.setInt(2, img.getIdProdotto());
			ps.setBytes(3, img.getImmagine());
			if (ps.executeUpdate() != 1)
				throw new Exception("Errore INSERT INTO");
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			img.setId(id);

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
			ps = cn.prepareStatement("DELETE FROM immagineProdotto WHERE id =?");
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

	public synchronized void doUpdate(ImmagineProdotto img,InputStream photo) throws SQLException {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ds.getConnection();
			ps = cn.prepareStatement("UPDATE immagineProdotto " + "SET id=?,idProdotto=?,immagine=?" + " WHERE id=?");
			ps.setInt(1, img.getId());
			ps.setInt(2, img.getIdProdotto());
			ps.setBinaryStream(3, photo,photo.available());
			
			if (ps.executeUpdate() != 1) {
				throw new Exception("Errore aggiornamento");
			}
			cn.commit();
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

	public synchronized ArrayList<ImmagineProdotto> doRetrieveAll() throws SQLException {
		Connection cn = null;
		PreparedStatement ps = null;
		ArrayList<ImmagineProdotto> immagini = new ArrayList<ImmagineProdotto>();
		try {
			cn = ds.getConnection();
			ps = cn.prepareStatement("SELECT * FROM ImmagineProdotto ORDER BY ?");
			ps.setString(1, "id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ImmagineProdotto img = new ImmagineProdotto();
				img.setId(rs.getInt("id"));
				img.setIdProdotto(rs.getInt("idProdotto"));
				img.setImmagine(rs.getBytes("immagine"));
				immagini.add(img);
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
		return immagini;
	}

	public synchronized ArrayList<ImmagineProdotto> doRetriveById(int id) throws SQLException {
		Connection cn = null;
		PreparedStatement ps = null;
		ArrayList<ImmagineProdotto> immagini = new ArrayList<ImmagineProdotto>();
		try {
			cn = ds.getConnection();
			ps = cn.prepareStatement("SELECT * FROM immagineProdotto WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ImmagineProdotto img = new ImmagineProdotto();
				img.setId(rs.getInt("id"));
				img.setIdProdotto(rs.getInt("idProdotto"));
				img.setImmagine(rs.getBytes("immagine"));
				immagini.add(img);
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
		return immagini;
	}

	public synchronized ArrayList<ImmagineProdotto> doRetriveByIdProdotto(int id) throws SQLException {
		Connection cn = null;
		PreparedStatement ps = null;
		ArrayList<ImmagineProdotto> immagini = new ArrayList<ImmagineProdotto>();
		try {
			cn = ds.getConnection();
			ps = cn.prepareStatement("SELECT * FROM immagineProdotto WHERE idProdotto=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ImmagineProdotto img = new ImmagineProdotto();
				img.setId(rs.getInt("id"));
				img.setIdProdotto(rs.getInt("idProdotto"));
				img.setImmagine(rs.getBytes("immagine"));
				immagini.add(img);
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
		return immagini;
	}

}
