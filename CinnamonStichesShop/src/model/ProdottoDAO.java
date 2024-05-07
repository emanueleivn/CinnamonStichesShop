package model;

import javax.sql.DataSource;

public class ProdottoDAO {
	private DataSource ds = null;

	public ProdottoDAO(DataSource ds) {
		this.ds = ds;
		
	}
}
