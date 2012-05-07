package de.bht.mme2.icart.ocp.user;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import de.bht.mme2.icart.ocp.utils.PersistenceManager;

public class UserDao {
	private static EntityManagerFactory emf = PersistenceManager.getInstance()
			.createEntityManagerFactory();
	private static EntityManager em = emf.createEntityManager();

	public User findById(Long id) {
		return em.find(User.class, id);
	}

	public User findByEmail(String email) {
		try {
			return (User) em
					.createQuery("SELECT u from User u where u.email = :email")
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void save(User user) {
		em.getTransaction().begin();
		if (user.getId() != null) {
			em.merge(user);
		} else {
			em.persist(user);
		}
		em.getTransaction().commit();
	}

}
