package model;

public class ImmagineProdotto {
	private int id;
	private int idProdotto;
	private byte[] immagine;
	
	public ImmagineProdotto() {
	}
	
	public int getId() {
		return id;
	}
	public byte[] getImmagine() {
		return immagine;
	}
	public void setImmagine(byte[] immagine) {
		this.immagine = immagine;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProdotto() {
		return idProdotto;
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
		
		return idProdotto==img.idProdotto;
	}
 }
