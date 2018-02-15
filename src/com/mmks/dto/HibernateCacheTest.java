package com.mmks.dto;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateCacheTest {

	public static void main(String[] args) throws RuntimeException {
		Session session = null;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			//Issue a select query
			UserDetails userDetails1 = session.get(UserDetails.class, 1);
			
			//Issue a update query
			userDetails1.setDescription("New description " + new Date()); 
			
			//Get the object from cache
			UserDetails userDetails2 = session.get(UserDetails.class, 1);

			transaction.commit();
			session.close();
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			//Isuue a New select query 
			UserDetails userDetails3 = session.get(UserDetails.class, 1);

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
