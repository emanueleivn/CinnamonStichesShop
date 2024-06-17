package model;


public class Prodotto {
	private int codice;
	private String nome;
	private String descrizione;
	private float costo;
	private String immaginePath;
	private int idCategoria;
	private boolean isDisp;
	
	public Prodotto() {
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public int getCodice() {
		return codice;
	}
	public String getNome() {
		return nome;
	}
	public float getCosto() {
		return costo;
	}
	public String getDescrizione() {
		return descrizione;
	}

	public boolean getIsDisp() {
		return isDisp;
	}
	public String getImmagine() {
		return immaginePath;
	}
	public void setImmagine(String path) {
		this.immaginePath = path;
	} 
	
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public void setIsDisp(boolean isDisp) {
		this.isDisp = isDisp;
	}
	

}
