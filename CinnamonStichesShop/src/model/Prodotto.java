package model;

import java.util.ArrayList;
import java.util.List;

public class Prodotto {
	private int codice;
	private String nome;
	private String descrizione;
	private float costo;
	private List<ImmagineProdotto> immagini;
	private List<TagliaProdotto> taglie;
	
	public Prodotto() {
		immagini=new ArrayList<>();
		taglie = new ArrayList<>();
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
	public List<ImmagineProdotto> getImmagini() {
		return immagini;
	}
	public List<TagliaProdotto> getTaglie() {
		return taglie;
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
	public void setImmagini(List<ImmagineProdotto> immagini) {
		this.immagini = immagini;
	}
	public void setTaglie(List<TagliaProdotto> taglie) {
		this.taglie = taglie;
	}
	public void aggiungiImmagine(String path) {
		ImmagineProdotto im = new ImmagineProdotto(path);
		im.setIdProdotto(codice);
		immagini.add(im);
	}
	public void aggiungiTaglia(TagliaProdotto t) {
		taglie.add(t);
	}
	public void removeImmagine(String path) {
		if(!immagini.isEmpty()) {
			ImmagineProdotto im = new ImmagineProdotto(path);
			im.setIdProdotto(codice);
			immagini.remove(im);
			}
	}
	public void removeTaglia(TagliaProdotto t) {
		if(!taglie.isEmpty())
			taglie.remove(t);
	}

}
