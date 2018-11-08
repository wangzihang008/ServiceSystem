package com.fdmgroup.Entity.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.fdmgroup.Entity.Administrator;

public class AdministratorDao {

	private EntityManagerFactory emf;

	public AdministratorDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public ArrayList<Administrator> getAdministrator(String username, String password) {
		EntityManager em = emf.createEntityManager();
		String queryStr = "select e.admin_id, e.status, e.first_name, e.last_name from admin e "
				+ "where e.username = '" + username + "' and e.password = '" + password + "'";
		TypedQuery query = em.createQuery(queryStr, Administrator.class);
		ArrayList<Administrator> resultList = (ArrayList) query.
				getResultList();
		em.close();
		return resultList;
		
	}
	
}
 