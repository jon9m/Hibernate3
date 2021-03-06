package com.mmks.dto;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateCriteriaTest {

	public static void main(String[] args) throws RuntimeException {
		Session session = null;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			//Not working
			/*Criteria criteria = session.createCriteria(UserDetails.class);
			List users = criteria.list();
			System.out.println(users);*/
			
/*			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Vehicle> criteriaQuery = builder.createQuery(Vehicle.class);
			Root<Vehicle> root = criteriaQuery.from(Vehicle.class); // Root entity
			criteriaQuery.where(builder.equal(root.get("name"), "Subaru Legacy"));
			criteriaQuery.select(root);
*/			
	        /*CriteriaBuilder builder = session.getCriteriaBuilder();	        
	        CriteriaQuery<UserDetails> criteriaQuery = builder.createQuery(UserDetails.class);	        
	        //Root entity
	        Root<UserDetails> root = criteriaQuery.from(UserDetails.class);  
	        
	        //criteriaQuery.where(builder.equal(root.get("userId"), 102));
	        criteriaQuery.where(builder.greaterThan(root.get("userId"), 100));
	        
	        criteriaQuery.select(root);  	        
	        List list = session.createQuery(criteriaQuery).getResultList();*/
	        //-------------------------------------------------------------
	        
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<String[]> criteriaQuery = builder.createQuery(String[].class);
			Root<UserDetails> root = criteriaQuery.from(UserDetails.class); 
			
			//Projection
	        criteriaQuery.multiselect(root.get("userId"), root.get("userName"), root.get("description"));  	     
	        
	        //Parameters
	        ParameterExpression<Integer> usserIdParameter = builder.parameter(Integer.class);
	        criteriaQuery.where(builder.greaterThan(root.get("userId"), usserIdParameter));	        
	        	        
	        //Selection
	        Query<String[]> query = session.createQuery(criteriaQuery);
	        query.setParameter(usserIdParameter, 100);

			List<String[]> list = query.getResultList();
			for (Object[] objects : list) {
				System.out.print("Name : " + objects[0]);
				System.out.print(", userName : " + objects[1]);
				System.out.println(", description : " + objects[2]);
			}	        
	        
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}

}
