package model;

import java.util.ArrayList;
import java.util.List;

public class Preferiti {
	private ArrayList<Prodotto> prodottiPreferiti;
	private int id;

	public Preferiti() {
		prodottiPreferiti = new ArrayList<>();
	}
	public Preferiti(int id) {
		prodottiPreferiti = new ArrayList<>();
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public ArrayList<Prodotto> getProdottiPreferiti() {
		return prodottiPreferiti;
	}
	public void aggiungiPref(Prodotto p) {
		prodottiPreferiti.add(p);
	}
	public void rimuoviPref(Prodotto p) {
		prodottiPreferiti.remove(p);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Preferiti c = (Preferiti)obj;
		return id==c.id;
	}

}
