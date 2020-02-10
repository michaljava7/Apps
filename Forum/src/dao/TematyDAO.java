package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import encje.Temat;

public class TematyDAO {
	private EntityManager em;

	public TematyDAO(EntityManager em) {

		this.em = em;
	}

	public List<Temat> pobierzTematy() {
		List<Temat> tematy = this.em.createQuery("SELECT t from Temat t").getResultList();
		return tematy;
	}

	public Temat pobierzTemat(int id) {
		this.em.clear();
		return em.find(Temat.class, id);
	}

	public boolean dodajTemat(Temat t) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(t);
			et.commit();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			et.rollback();
			return false;

		}
	}
}
