package com.mmks.dto;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateHQLTest {

	public static void main(String[] args) throws RuntimeException {
		Session session = null;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			// String queryString = "from UserDetails";
			// String queryString = "from UserDetails user_details where user_details.id = 152";
//			String queryString = "from UserDetails";
//
//			Query<UserDetails> query = session.createQuery(queryString);
//			query.setFirstResult(3);
//			query.setMaxResults(2);
//
//			List<UserDetails> userDetails = query.list();
//			System.out.println(userDetails);
			
			
			/*String queryString = "select userId, userName, description from UserDetails user where user.userId > ?";
			Query<Object[]> query = session.createQuery(queryString);			
			query.setParameter(0, 100);*/
			
			String queryString = "select userId, userName, description from UserDetails user where user.userId > :userId";
			Query<Object[]> query = session.createQuery(queryString);			
			query.setParameter("userId", 100);
		
			List<Object[]> userDetails = query.list();
			for(Object[] a : userDetails) {
				System.out.println(a[0].toString());
				System.out.println(a[1].toString());
				System.out.println(a[2].toString());
			}
			System.out.println(userDetails);

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
