package encje;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "rola")
public class Rola implements Serializable {
	
	 
	@Id
	@GeneratedValue
	private int id;
	private String login, rola;

	public Rola() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public Rola(int id, String login, String rola) {
		super();
		this.id = id;
		this.login = login;
		this.rola = rola;
	}

}
