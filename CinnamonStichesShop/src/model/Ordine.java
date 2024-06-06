package model;

import java.time.LocalDate;
import java.util.ArrayList;

enum STATUS {
	SPEDITO, CONSEGNATO, PREPARAZIONE;
}

public class Ordine {
	private int codiceOrdine;
	private int idCliente;
	private LocalDate data;
	private String stato;
	private String indirizzoSpedizione;
	private int quantitàProdotti;
	private float tot;
	private ArrayList<ProdottoCarrello> prodotti;

	public Ordine() {
		prodotti = new ArrayList<>();
		tot = 0;
		quantitàProdotti = 0;
		stato = STATUS.PREPARAZIONE.toString();
		data=LocalDate.now();
	}

	public Ordine(ArrayList<ProdottoCarrello> pList, int id, String via, String cap, String paese) {
		prodotti.addAll(pList);
		tot = getTot();
		quantitàProdotti = prodotti.size();
		stato = STATUS.PREPARAZIONE.toString();
		setIndirizzoSpedizione(via, cap, paese);
		idCliente = id;
		data = LocalDate.now();
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getCodiceOrdine() {
		return codiceOrdine;
	}

	public LocalDate getData() {
		return data;
	}

	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}

	public int getQuantitàProdotti() {
		return quantitàProdotti;
	}

	public String getStato() {
		return controllaStato();
	}

	public float getTot() {
		return tot;
	}

	public ArrayList<ProdottoCarrello> getProdotti() {
		return prodotti;
	}

	public void setData(LocalDate data) {
		this.data = LocalDate.now();
	}

	public void setIndirizzoSpedizione(String via, String cap, String paese) {
		String indirizzoSpedizione = componiIndirizzo(via, cap, paese);
		this.indirizzoSpedizione = indirizzoSpedizione;
	}
	
	public void setIndirizzoSpedizione(String indirizzo) {
		this.indirizzoSpedizione = indirizzo;
	}
	public void setQuantitàProdotti(int quantitàProdotti) {
		this.quantitàProdotti = quantitàProdotti;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public void setTot(float tot) {
		this.tot = tot;
	}

	public void setProdotti(ArrayList<ProdottoCarrello> prodotti) {
		this.prodotti = prodotti;
	}
	public void setCodiceOrdine(int id) {
		codiceOrdine = id;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Ordine o = (Ordine) obj;
		return codiceOrdine == o.getCodiceOrdine();
	}

	public void aggiungiProdotto(ProdottoCarrello p) {
		prodotti.add(p);
		quantitàProdotti++;
		tot = calcolaTotale();
	}

	private String controllaStato() {
		if (data.plusDays(2).isEqual(LocalDate.now()))
			return STATUS.SPEDITO.toString();
		if (data.plusDays(6).isEqual(LocalDate.now()))
			return STATUS.CONSEGNATO.toString();
		else
			return stato;

	}

	private float calcolaTotale() {
		if (prodotti.isEmpty())
			return 0;
		for (ProdottoCarrello p : prodotti)
			tot += p.getTot();
		return tot;
	}

	private static String componiIndirizzo(String via, String cap, String paese) {
		return via + "," + cap + "," + paese;

	}

}
