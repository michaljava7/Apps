package encje;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;



@Entity
@Table(name = "temat")
public class Temat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private Timestamp data;
	
	private String tytul;
	@Lob 
	private String tresc;
	@ManyToOne
	@JoinColumn(name="uzytkownik")
	private Uzytkownik uzytkownik;
	
	
	@OneToMany(mappedBy="temat",fetch = FetchType.EAGER)
	@OrderBy("data ASC")
	private Set<Wpis> wpisy;

	
	public Temat() {
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

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public Set<Wpis> getWpisy() {
		return wpisy;
	}

	public void setWpisy(Set<Wpis> wpisy) {
		this.wpisy = wpisy;
	}

}
