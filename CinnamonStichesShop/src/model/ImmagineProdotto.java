package model;

public class ImmagineProdotto {
	private int idProdotto;
	private String path;
	
	public ImmagineProdotto() {
	}
	public ImmagineProdotto(String path) {
		this.path=path;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		ImmagineProdotto img= (ImmagineProdotto)obj;
		
		return idProdotto==img.idProdotto
			&& path.equals(img.path);
	}
 }
