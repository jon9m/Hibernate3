package com.mmks.dto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

public class HibernateQueryByExample {

	public static void main(String[] args) throws RuntimeException {
		Session session = null;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			// Example Object
			/*UserDetails userDetails = new UserDetails();
			userDetails.setUserId(1);
			userDetails.setUserName("first user");
			userDetails.setDescription("user description ...... ");

			Example example = Example.create(userDetails);

			//!!!!!!!!! Not supported ???
			Criteria criteria = session.createCriteria(UserDetails.class).add(example);

			List users = criteria.list();
			System.out.println(users);

*
*/
			
			Job userJob = new Job("Plumber", 8);
			Example example = Example.create(userJob);

			//!!!!!!!!! Not supported ???
			Criteria criteria = session.createCriteria(Job.class).add(example);

			List jobs = criteria.list();
			System.out.println(jobs);

			
			
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
