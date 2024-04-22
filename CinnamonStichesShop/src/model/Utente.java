package model;

public class Utente {
	private int idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String via;
	private String cap;
	private String città;
	private boolean isAdmin;

	public Utente() {
	}

	public int getId() {
		return idUtente;
	}

	public void setId(int id) {
		idUtente = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Utente utente = (Utente) obj;
		return idUtente == utente.idUtente && isAdmin == utente.isAdmin && email.equalsIgnoreCase(utente.email)
				&& password.equalsIgnoreCase(utente.password) && nome.equalsIgnoreCase(utente.nome)
				&& cognome.equalsIgnoreCase(utente.cognome) && via.equalsIgnoreCase(utente.via)
				&& cap.equalsIgnoreCase(utente.cap) && città.equalsIgnoreCase(utente.città);
	}

}
