package model;

public class TagliaProdotto {
	private int idProdotto;
	private String taglia;

	public TagliaProdotto() {

	}
	public TagliaProdotto(String taglia) {
		this.taglia=taglia;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public String getTaglia() {
		return taglia;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}
}
