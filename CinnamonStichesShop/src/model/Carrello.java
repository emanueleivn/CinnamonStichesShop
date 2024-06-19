package model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
	private int id;
	private int idCliente;
	private ArrayList<ProdottoCarrello> prodottiCarrello;
	private float totale;
	public Carrello() {
		prodottiCarrello = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public ArrayList<ProdottoCarrello> getProdottiCarrello() {
		return prodottiCarrello;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProdottiCarrello(ArrayList<ProdottoCarrello> prodottiCarrello) {
		this.prodottiCarrello = prodottiCarrello;
	}

	public void aggiungiAlCarrello(ProdottoCarrello p) {
		prodottiCarrello.add(p);
	}

	public void rimuoviDalCarrello(ProdottoCarrello p) {
		prodottiCarrello.remove(p);
	}
	
	public void setTotale(float tot) {
		this.totale = tot;
	}
	public float getTotale() {
		float tot = 0;
		for (ProdottoCarrello p : prodottiCarrello) {
			tot += p.getProdotto().getCosto() * p.getQuantita();
		}
		return tot;
	}

	public int getNumeroProdotti() {
		return prodottiCarrello.size();
	}

	public void incrementa(ProdottoCarrello p) {
			prodottiCarrello.get(prodottiCarrello.indexOf(p)).incrementaQ();
	}

	public void decrementa(ProdottoCarrello p) {
		prodottiCarrello.get(prodottiCarrello.indexOf(p)).decrementaQ();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Carrello c = (Carrello) obj;
		return id == c.id;
	}

}
