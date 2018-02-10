package com.mmks.dto;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	public static void main(String[] args) {

		Session session = null;
		SessionFactory sessionFactory = null;

		UserDetails userDetails = new UserDetails();
		// userDetails.setUserId(2);
		userDetails.setUserName("first user");
		userDetails.setJoinedDate(new java.util.Date());
		userDetails.setDescription("user description ...... ");
		userDetails.setLong_description("user Long description - user Long description user Long description ");

		Address homeAddress = new Address("home street", "Mel", "vic", "1234");
		Address workAddress = new Address("work street", "Mel", "vic", "1234");
		Address address3 = new Address("street3 ", "Mel", "vic", "1234");
		Address address4 = new Address("street4 ", "Mel", "vic", "1234");

		userDetails.getAddresses().add(homeAddress);
		userDetails.getAddresses().add(workAddress);
		userDetails.getAddresses().add(address3);
		userDetails.getAddresses().add(address4);

		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			
			//Save Userdetails ----------------------------------------------------------------------
			session = sessionFactory.openSession();
			session.beginTransaction();
			Integer userId = (Integer) session.save(userDetails);

			session.getTransaction().commit();
			session.close();

			//Get user and its value objects list----------------------------------------------------
			session = sessionFactory.openSession();
			session.beginTransaction();
			UserDetails user = session.get(UserDetails.class, userId);
			// System.out.println(user.toString());

			Thread.sleep(1000);

			// Send a new sql query after 3 seconds to collect addresses
			System.out.println(Arrays.toString(user.getAddresses().toArray()));

			session.getTransaction().commit();
			session.close();

			// OneToOne mapping---------------------------------------------------------------------
			session = sessionFactory.openSession();
			session.beginTransaction();
			UserDetails userdtl = session.get(UserDetails.class, userId);			
			
			Citizenship citizenship = new Citizenship("US", 10, new GregorianCalendar(2017, 5, 30).getTime());
			userdtl.setCitizenship(citizenship);
			
			session.save(citizenship);
			session.save(userdtl);

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
}
