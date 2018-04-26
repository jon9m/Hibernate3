package com.mmks.dto;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTestMappedBy {

	public static void main(String[] args) throws RuntimeException{

		Session session = null;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;

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
			transaction = session.beginTransaction();
			Integer userId = (Integer) session.save(userDetails);

			session.getTransaction().commit();
			session.close();		
			
			// OneToMany mapping---------------------------------------------------------------------
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			UserDetails userdt2 = session.get(UserDetails.class, userId);			
			
			Vehicle vehicle1 = new Vehicle("Subaru Legacy");
			Vehicle vehicle2 = new Vehicle("Subaru Forester");
			Vehicle vehicle3 = new Vehicle("Subaru Impreza");
			
			Hatchback vw = new Hatchback("VW", "4");
			Hatchback hi = new Hatchback("Hiundai", "4");
			Sedan mazda5 = new Sedan("Mazda", "5");
			
			userdt2.getVehicles().add(vehicle1);
			userdt2.getVehicles().add(vehicle2);
			userdt2.getVehicles().add(vehicle3);
			
			userdt2.getVehicles().add(vw);
			userdt2.getVehicles().add(hi);
			userdt2.getVehicles().add(mazda5);
			
			//Reverse relationship - Many To One
			vehicle1.setUser(userdt2);
			vehicle2.setUser(userdt2);
			vehicle3.setUser(userdt2);
			
			vw.setUser(userdt2);
			hi.setUser(userdt2);
			mazda5.setUser(userdt2);
					
			//save the transient instance before flushing
			session.save(vehicle1);  //No need to save if use cascade
			session.save(vehicle2);
			session.save(vehicle3);			
			
			session.save(vw);			
			session.save(hi);			
			session.save(mazda5);			
			
//			session.save(userdt2); //Save Wouldn't work with cascade PERSIST			
			session.persist(userdt2);			

			session.getTransaction().commit();
			session.close();
						
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			
			UserDetails user = session.get(UserDetails.class, userId);
			System.out.println(user.toString());
						
			session.getTransaction().commit();
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
