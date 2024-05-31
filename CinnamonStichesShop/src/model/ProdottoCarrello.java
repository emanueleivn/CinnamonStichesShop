package model;

public class ProdottoCarrello {
	  private Prodotto prodotto;
	  private int quantity;

	  public ProdottoCarrello(Prodotto prodotto, int quantità) {
	    this.prodotto = prodotto;
	    this.quantity = quantità;
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

	  public void incrementaQ(int incremento) {
	    this.quantity += incremento;
	  }
}
