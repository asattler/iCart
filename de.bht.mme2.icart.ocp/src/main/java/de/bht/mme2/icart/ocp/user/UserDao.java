package de.bht.mme2.icart.ocp.user;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
public class UserDao{
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ocp");
	private static EntityManager em = emf.createEntityManager();
	
	public User findById(int id){
		return em.find(User.class, id);
	}
	
	public User findByEmail(String email){
		try{
		return (User) em.createQuery("from User u WHERE u.email = ?1").setParameter(1, email).getSingleResult();
		} catch (NoResultException e) {
            return null;
        }
	}
	
    public void save(User user) {
    	em.getTransaction().begin();
        if (user != null) {
            em.merge(user);
        } else {
            em.persist(user);
        }
        em.getTransaction().commit();
    }
	
}
