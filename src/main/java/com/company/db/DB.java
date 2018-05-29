package com.company.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DB {

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();

	public void add(Films film) {
		session.beginTransaction();
		session.save(film);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Films> getFilms(){
		session.beginTransaction();
		List<Films> q = session.createQuery("FROM films").list();
		session.getTransaction().commit();
		session.close();
		return q;
		
	}
}
