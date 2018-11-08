package com.fdmgroup.Entity.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Dao<E> {

	private EntityManagerFactory emf;

	public Dao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public List<E> excuteQuery(Class<E> e, String query) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<E> q = (TypedQuery<E>) em.createQuery(query, e);
		ArrayList resultList = (ArrayList) q.getResultList();
		em.close();
		return resultList;
	}

}
