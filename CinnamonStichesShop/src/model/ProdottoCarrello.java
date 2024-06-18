package model;

public class ProdottoCarrello {
	private Prodotto prodotto;
	private int quantity = 1;
	private float tot;

	public ProdottoCarrello(Prodotto prodotto, int quantità) {
		this.prodotto = prodotto;
		this.quantity = quantità;
		this.tot = prodotto.getCosto() * quantity;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantita() {
		return quantity;
	}

	public void setQuantity(int quantità) {
		this.quantity = quantità;
	}

	public float getTot() {
		return tot;
	}

	public void setTot(float tot) {
		this.tot = tot;
	}

	public void incrementaQ() {
		quantity++;
	}

	public void decrementaQ() {
		quantity--;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		ProdottoCarrello p = (ProdottoCarrello) obj;
		return p.prodotto.getCodice()==this.prodotto.getCodice();
	}
}
