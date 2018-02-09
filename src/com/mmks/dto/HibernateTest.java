package com.mmks.dto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	public static void main(String[] args) {

		Session session = null;
		SessionFactory sessionFactory = null;

		UserDetails userDetails = new UserDetails();
//		userDetails.setUserId(2);
		userDetails.setUserName("first user");
		userDetails.setJoinedDate(new java.util.Date());
		userDetails.setDescription("user description ...... ");
		userDetails.setLong_description("user Long description - user Long description user Long description ");
		
		Address homeAddress = new Address("home street","Mel", "vic" ,"1234");		
		Address workAddress = new Address("work street","Mel", "vic" ,"1234");		
		userDetails.setHomeAddress(homeAddress);
		userDetails.setWorkAddress(workAddress);

		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();
			Integer userId = (Integer) session.save(userDetails);
			
			session.getTransaction().commit();
			session.close();

			session = sessionFactory.openSession();
			session.beginTransaction();			
			UserDetails user = session.get(UserDetails.class, userId);
			System.out.println(user.toString());
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}

	@Override
	public String toString() {
		return "HibernateTest [\\n]";
	}

}
