package model;

import java.time.LocalDate;
import java.util.ArrayList;

enum STATUS {
	SPEDITO, CONSEGNATO, IN_LAVORAZIONE;
}

public class Ordine {
	private int codiceOrdine;
	private int idCliente;
	private LocalDate data;
	private String stato;
	private String indirizzoSpedizione;
	private float tot;
	private ArrayList<ProdottoCarrello> prodotti;
	private int quantitàProdotti;

	public Ordine() {
		prodotti = new ArrayList<>();
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
		this.data = data;
	}

	public void setIndirizzoSpedizione(String via, String cap, String paese) {
		String indirizzoSpedizione = componiIndirizzo(via, cap, paese);
		this.indirizzoSpedizione = indirizzoSpedizione;
	}

	public void setIndirizzoSpedizione(String indirizzo) {
		this.indirizzoSpedizione = indirizzo;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public void setTot(float tot) {
		this.tot = tot;
	}
	public void setQuantitàProdotti(int quantitàProdotti) {
		this.quantitàProdotti = quantitàProdotti;
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
		this.prodotti.add(p);
		this.tot += p.getTot();

	}

	private String controllaStato() {
        LocalDate now = LocalDate.now();
        long i = data.until(now).getDays();
        if (i<=2) {
            return "In Lavorazione";
        } else if (i>=3 && i<=6) {
            return STATUS.SPEDITO.toString();
        } else {
            return STATUS.CONSEGNATO.toString();
        }
    }

	private static String componiIndirizzo(String via, String cap, String paese) {
		return via + "," + cap + "," + paese;

	}

}
