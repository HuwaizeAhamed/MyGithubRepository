package com.Hibernate.Demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;

public class Application {

	public static void main(String[] args) {

		Model_Pojo mp =new Model_Pojo();
		mp.setName("test");
		mp.setBloodGrp("B+");
		mp.setId(7);
		mp.setPhoneNumber(987627181);
		
		Configuration con=new Configuration().configure().addAnnotatedClass(Model_Pojo.class);
		
//		ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); -> old 4.x.x
		
		ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		
		SessionFactory sf=con.buildSessionFactory(sr);
		
		Session s=sf.openSession();
		
		Transaction t=s.beginTransaction();
		
		s.save(mp);
		
		t.commit();
		
		s.close();
		
		sf.close();

	}
}
