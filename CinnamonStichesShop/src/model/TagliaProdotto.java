package model;

public class TagliaProdotto {
	private String taglia;

	public TagliaProdotto() {

	}
	public TagliaProdotto(String taglia) {
		this.taglia=taglia;
	}
	
	public String getTaglia() {
		return taglia;
	}
	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		TagliaProdotto t= (TagliaProdotto)obj;
		
		return t.equals(t.taglia);
	}
}
