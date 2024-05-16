package model;

import java.util.ArrayList;
import java.util.List;

public class Preferiti {
	private ArrayList<Integer> prodottiPreferiti;
	private int id;
	private int idCliente;

	public Preferiti() {
		prodottiPreferiti = new ArrayList<>();
	}
	public int getId() {
		return id;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public ArrayList<Integer> getProdottiPreferiti() {
		return prodottiPreferiti;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public void setProdottiPreferiti(ArrayList<Integer> prodottiPreferiti) {
		this.prodottiPreferiti = prodottiPreferiti;
	}
	public void aggiungiPref(int idP) {
		prodottiPreferiti.add(idP);
	}
	public void rimuoviPref(int idP) {
		prodottiPreferiti.remove(idP);
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
