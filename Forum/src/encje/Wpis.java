package encje;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wpis")
public class Wpis {
	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}
	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}
	public Temat getTemat() {
		return temat;
	}
	public void setTemat(Temat temat) {
		this.temat = temat;
	}
	@Id
	@GeneratedValue
	private int id;
	private Timestamp data;
	@Lob
	private String tresc; 
	@ManyToOne
	@JoinColumn(name = "uzytkownik")
	private Uzytkownik uzytkownik;
	@ManyToOne
	@JoinColumn(name = "temat")
	private Temat temat;
	
	
	
	public String getTresc() {
		return tresc;
	}
	public void setTresc(String tresc) {
		this.tresc = tresc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public Wpis() {}

}
