package de.bht.mme2.icart.ocp.recipe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import de.bht.mme2.icart.ocp.utils.PersistenceManager;

public class RecipeDao {
	private static EntityManagerFactory emf = PersistenceManager.getInstance()
			.createEntityManagerFactory();
	private static EntityManager em = emf.createEntityManager();

	
	public Recipe findById(Long id) {
		return em.find(Recipe.class, id);
	}
	
	public Recipe findByName(String name) {
		try {
			return (Recipe) em
					.createQuery("SELECT r from Recipe r where r.name = :name")
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Recipe findByURL(String url) {
		try {
			return (Recipe) em
					.createQuery("SELECT r from Recipe r where r.url = :url")
					.setParameter("url", url).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void save(Recipe recipe) {
		em.getTransaction().begin();
		if (recipe.getId() != null) {
			em.merge(recipe);
		} else {
			em.persist(recipe);
		}
		em.flush();
		em.getTransaction().commit();
		em.clear();
		em.getEntityManagerFactory().getCache().evictAll();
	}
}
