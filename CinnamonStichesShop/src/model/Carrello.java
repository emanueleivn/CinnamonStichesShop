package model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
	private int id;
	private ArrayList<Prodotto> prodottiCarrello;
	public Carrello() {
		prodottiCarrello= new ArrayList<>();
	}
	public int getId() {
		return id;
	}
	public ArrayList<Prodotto> getProdottiCarrello() {
		return prodottiCarrello;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setProdottiCarrello(ArrayList<Prodotto> prodottiCarrello) {
		this.prodottiCarrello = prodottiCarrello;
	}
	public void aggiungiAlCarrello(Prodotto p) {
		prodottiCarrello.add(p);
	}
	public void rimuoviDalCarrello(Prodotto p) {
		prodottiCarrello.remove(p);
	}
	public float getTotale() {
		float tot=0;
		for(Prodotto p : prodottiCarrello) {
			tot+=p.getCosto();
		}
		return tot;
	}
	public int getNumeroProdotti() {
		return prodottiCarrello.size();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Carrello c = (Carrello)obj;
		return id==c.id;
	}

}
