package db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import beans.User;

public class UsersDAO {
	private EntityManager em;
	private DBconfig dbConfig;
	private EntityTransaction et;

	public UsersDAO() {
		em = dbConfig.getEMF().createEntityManager();

	}

	public List<User> getALL() {
		List resultList = em.createQuery("SELECT u from User u").getResultList();
		return resultList;

	}

	public void addUser(String imie, String nazwisko) {
		et = em.getTransaction();
		try {
			et.begin();
			User u = new User();
			u.setImie(imie);
			u.setNazwisko(nazwisko);
			em.persist(u);
			et.commit();

		} catch (Exception e) {
			if (et.isActive()) {
				et.rollback();
			}
		}
	}

}
