package model;

import java.util.ArrayList;

public class Categoria {
	private int id;
	private static int c=0;
	private String nome;
	private String descrizione;
	private ArrayList<Prodotto> listaP;

	public Categoria() {
		listaP=new ArrayList<Prodotto>();
	}

	public Categoria(String nome, String descrizione) {
		id=c++;
		this.nome = nome;
		this.descrizione = descrizione;
		listaP=new ArrayList<Prodotto>();
	}

	public String getDescrizione() {
		return descrizione;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return id==c.id;
	}

}
