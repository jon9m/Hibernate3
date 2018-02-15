package com.mmks.dto;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateCacheTest {

	public static void main(String[] args) throws RuntimeException {
		Session session = null;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			// Issue a select query
			UserDetails userDetails1 = session.get(UserDetails.class, 1);

			// Issue a update query
			userDetails1.setDescription("New description " + new Date());

			// Get the object from cache
			UserDetails userDetails2 = session.get(UserDetails.class, 1);

			transaction.commit();
			session.close();

			// Open session and get userDetails close session
			for (int i = 0; i < 100; i++) {
				session = sessionFactory.openSession();
				transaction = session.beginTransaction();

				// Issue a New select query - unless second level cache is configured
				UserDetails userDetails3 = session.get(UserDetails.class, 1);

				transaction.commit();
				session.close();
			}

			System.out.println("----------- Query cache--------------");
			// Query cache
			for (int i = 0; i < 100; i++) {
				session = sessionFactory.openSession();
				transaction = session.beginTransaction();

				// Issue a New select query - unless second level cache is configured
				Query query = session.createQuery("from UserDetails where userId = 1");
				query.setCacheable(true);
				List users = query.list();
//				System.out.println(users);

				transaction.commit();
				session.close();
			}

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
