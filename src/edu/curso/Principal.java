package edu.curso;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Principal {
	public static void criarPet(EntityManager em) {
		Pet p1 = new Pet("Puma","Cachorro","Labrador");		
		Pet p2 = new Pet("Miau","Gato","Persa");

		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.getTransaction().commit();
	}

	public static void listarPet(EntityManager em) {
		TypedQuery<Pet> qry = em.createQuery("select p from Pet p", Pet.class);
		List<Pet> lista = qry.getResultList();
		for (Pet p : lista) {
			System.out.print("\nID : " + p.getId() + "\nNome : " + p.getNome() 
				+ "\nEspecie :" + p.getEspecie() + "\nRaça : " + p.getRaca());
		}
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PETS");

		EntityManager em = emf.createEntityManager();
		criarPet(em);
		listarPet(em);
		em.close();

		emf.close();

	}

}
