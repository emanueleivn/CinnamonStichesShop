package model;

public class ProdottoOrdinato {
	private int idProdotto;
	private String nome;
	private float costo;
	private String taglia;
	private int quantità;
	private String descrizione;
	public ProdottoOrdinato() {}
	
	public int getCodice() {
		return idProdotto;
	}
	public float getCosto() {
		return costo;
	}
	public String getNome() {
		return nome;
	}
	public int getQuantità() {
		return quantità;
	}
	public String getTaglia() {
		return taglia;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setCodice(int codice) {
		this.idProdotto = codice;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
