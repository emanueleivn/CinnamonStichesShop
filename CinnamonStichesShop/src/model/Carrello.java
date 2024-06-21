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
		aggiornaTotale();
	}

	public void aggiungiAlCarrello(ProdottoCarrello p) {
		if(prodottiCarrello.contains(p)) {
				int quantità = prodottiCarrello.get(prodottiCarrello.indexOf(p)).getQuantita();
				float tot =prodottiCarrello.get(prodottiCarrello.indexOf(p)).getTot();
				prodottiCarrello.remove(p);
				p.setQuantity(p.getQuantita()+quantità);
				p.setTot(p.getTot()+tot);
		}
		prodottiCarrello.add(p);
		totale += p.getTot();
	}

	public void rimuoviDalCarrello(ProdottoCarrello p) {
		prodottiCarrello.remove(p);
		totale -= p.getTot();
	}

	public void setTotale(float tot) {
		this.totale = tot;
	}

	public float getTotale() {
		float tot = 0;
		for (ProdottoCarrello p : prodottiCarrello) {
			tot += p.getTot();
		}
		return tot;
	}

	public int getNumeroProdotti() {
		return prodottiCarrello.size();
	}

	public void incrementa(ProdottoCarrello p) {
		prodottiCarrello.get(prodottiCarrello.indexOf(p)).incrementaQ();
		totale += p.getProdotto().getCosto();
	}

	public void decrementa(ProdottoCarrello p) {
		prodottiCarrello.get(prodottiCarrello.indexOf(p)).decrementaQ();
		totale -= p.getProdotto().getCosto();  
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

	private void aggiornaTotale() {
		totale = 0;
		for (ProdottoCarrello p : prodottiCarrello) {
			totale += p.getProdotto().getCosto() * p.getQuantita();
		}
	}
}
