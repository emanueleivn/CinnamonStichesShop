package model;

import java.util.ArrayList;

public class Categoria {
	private int idCategoria;
	private String nome;
	private String descrizione;
	private ArrayList<Prodotto> listaP;

	public Categoria() {
		listaP=new ArrayList<Prodotto>();
	}

	public String getDescrizione() {
		return descrizione;
	}

	public int getId() {
		return idCategoria;
	}
	public void setId(int id) {
		this.idCategoria = id;
	}

	public String getNome() {
		return nome;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setNome(String nome) {
		this.nome = nome;
	} 

	public ArrayList<Prodotto> getListaP() {
		return listaP;
	}
	public void setListaP(ArrayList<Prodotto> listaP) {
		this.listaP = listaP;
	}
	public void aggiungiProdotto(Prodotto p) {
		listaP.add(p);
	}
	public void rimuoviProdotto(Prodotto p) {
		listaP.remove(p);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Categoria c = (Categoria)obj;
		return idCategoria==c.idCategoria;
	}

}
