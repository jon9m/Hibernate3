package com.mmks.dto;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

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

			//Get user and its value objects list----------------------------------------------------
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			UserDetails user = session.get(UserDetails.class, userId);
			// System.out.println(user.toString());

			Thread.sleep(1000);

			// Send a new sql query after 3 seconds to collect addresses
			System.out.println(Arrays.toString(user.getAddresses().toArray()));

			session.getTransaction().commit();
			session.close();

			// OneToOne mapping---------------------------------------------------------------------
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			UserDetails userdtl = session.get(UserDetails.class, userId);			
			
			Citizenship citizenship = new Citizenship("US", 10, new GregorianCalendar(2017, 5, 30).getTime());
			userdtl.setCitizenship(citizenship);
			
			//save the transient instance before flushing
//			session.save(citizenship); //No need to save if use cascade
			
//			session.save(userdtl); //Save wouldn't work with cascade PERSIST
			session.persist(userdtl);

			session.getTransaction().commit();
			session.close();
			
			// OneToMany mapping---------------------------------------------------------------------
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			UserDetails userdt2 = session.get(UserDetails.class, userId);			
			
			Vehicle vehicle1 = new Vehicle("Subaru Legacy");
			Vehicle vehicle2 = new Vehicle("Subaru Forester");
			Vehicle vehicle3 = new Vehicle("Subaru Impreza");
			
			userdt2.getVehicles().add(vehicle1);
			userdt2.getVehicles().add(vehicle2);
			userdt2.getVehicles().add(vehicle3);
			
			//Reverse relationship - Many To One
			vehicle1.setUser(userdt2);
			vehicle2.setUser(userdt2);
			vehicle3.setUser(userdt2);
					
			//save the transient instance before flushing
//			session.save(vehicle1);  //No need to save if use cascade
//			session.save(vehicle2);
//			session.save(vehicle3);			
			
//			session.save(userdt2); //Save Wouldn't work with cascade PERSIST			
			session.persist(userdt2);			

			session.getTransaction().commit();
			session.close();
			
			
			//Get user and its one to many list----------------------------------------------------
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			UserDetails user3 = session.get(UserDetails.class, userId);

			Thread.sleep(1000);

			// Send a new sql query after 3 seconds to collect addresses
			System.out.println(Arrays.toString(user3.getVehicles().toArray()));

			session.getTransaction().commit();
			session.close();
			
			// ManyToMany mapping---------------------------------------------------------------------
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			UserDetails userdt3 = session.get(UserDetails.class, userId);			
			
			Job job1 = new Job("Plumber");
			Job job2 = new Job("Cleaner");
			Job job3 = new Job("Carpenter");
			Job job4 = new Job("Engineer");
			
			userdt3.getJobs().add(job1);
			userdt3.getJobs().add(job2);
			userdt3.getJobs().add(job3);
			userdt3.getJobs().add(job4);
			
			job1.getUsers().add(userdt3);
			job2.getUsers().add(userdt3);
			job3.getUsers().add(userdt3);
			job4.getUsers().add(userdt3);
			
			//save the transient instance before flushing
//			session.save(job1);	//No need to save if use cascade
//			session.save(job2);
//			session.save(job3);
//			session.save(job4);
			
//			session.save(userdt3);  //Save Wouldn't work with cascade PERSIST 			
			session.persist(userdt3);			

			session.getTransaction().commit();
			session.close();
			
			//Get user and its one to many jobs list----------------------------------------------------
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			UserDetails user5 = session.get(UserDetails.class, userId);

			Thread.sleep(3000);

			// Send a new sql query after 3 seconds to collect addresses
			System.out.println(Arrays.toString(user5.getJobs().toArray()));

			session.getTransaction().commit();
			session.close();
			
			//Delete cascade----------------------------------------------------
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			UserDetails user6 = session.get(UserDetails.class, userId);

			Thread.sleep(3000);
			System.out.println(user6.toString());
			session.delete(user6);

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
