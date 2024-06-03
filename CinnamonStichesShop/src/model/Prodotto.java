package model;

import java.util.ArrayList;
import java.util.List;

public class Prodotto {
	private int codice;
	private String nome;
	private String descrizione;
	private float costo;
	private List<ImmagineProdotto> immagini;
	private List<String> taglie;
	private int idCategoria;
	private boolean isDisp;
	
	public Prodotto() {
		taglie = new ArrayList<>();
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

	public List<String> getTaglie() {
		return taglie;
	}public boolean getIsDisp() {
		return isDisp;
	}
	public List<ImmagineProdotto> getImmagini() {
		return immagini;
	}
	public void setImmagini(List<ImmagineProdotto> immagini) {
		this.immagini = immagini;
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
	
	public void setTaglie(List<String> taglie) {
		this.taglie = taglie;
	}
	public void setIsDisp(boolean isDisp) {
		this.isDisp = isDisp;
	}
	

}
