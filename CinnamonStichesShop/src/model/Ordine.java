package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

enum STATUS {
	SPEDITO, CONSEGNATO, PREPARAZIONE;
}

public class Ordine {
	private static int codiceOrdine = 0;
	private String id;
	private LocalDateTime data;
	private String stato;
	private String indirizzoSpedizione;
	private int quantitàProdotti;
	private float tot;
	private ArrayList<Prodotto> prodotti;

	public Ordine() {
		codiceOrdine++;
		prodotti = new ArrayList<>();
		tot = 0;
		quantitàProdotti = 0;
		stato = STATUS.PREPARAZIONE.toString();
	}

	public Ordine(ArrayList<Prodotto> pList, String codiceCliente, String via, String cap, String paese) {
		codiceOrdine++;
		prodotti.addAll(pList);
		tot = getTot();
		quantitàProdotti = prodotti.size();
		stato = STATUS.PREPARAZIONE.toString();
		setIndirizzoSpedizione(via, cap, paese);
		id = codiceCliente;
		data = LocalDateTime.now();
	}

	public int getCodiceOrdine() {
		return codiceOrdine;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getId() {
		return id;
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

	public ArrayList<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIndirizzoSpedizione(String via, String cap, String paese) {
		String indirizzoSpedizione = componiIndirizzo(via, cap, paese);
		this.indirizzoSpedizione = indirizzoSpedizione;
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

	public void setProdotti(ArrayList<Prodotto> prodotti) {
		this.prodotti = prodotti;
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

	public void aggiungiProdotto(Prodotto p) {
		prodotti.add(p);
		quantitàProdotti++;
		tot = calcolaTotale();
	}

	private String controllaStato() {
		if (data.plusDays(2).isEqual(LocalDateTime.now()))
			return STATUS.SPEDITO.toString();
		if (data.plusDays(6).isEqual(LocalDateTime.now()))
			return STATUS.CONSEGNATO.toString();
		else
			return stato;

	}

	private float calcolaTotale() {
		if (prodotti.isEmpty())
			return 0;
		for (Prodotto p : prodotti)
			tot += p.getCosto();
		return tot;
	}

	private static String componiIndirizzo(String via, String cap, String paese) {
		return via + "," + cap + "," + paese;

	}

}
