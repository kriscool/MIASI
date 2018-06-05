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
	
	public List<Films> getAllFilms(){
		session.beginTransaction();
		List<Films> q = session.createQuery("FROM films").list();
		session.getTransaction().commit();
		session.close();
		return q;
		
	}
	
	public Films getFilm(int id){
		session.beginTransaction();
		Films q = session.load(Films.class, id);
		session.getTransaction().commit();
		session.close();
		return q;
		
	}
	
	public void updateFilm(Films film){
		session.beginTransaction();
		session.update(film);
		session.getTransaction().commit();
		session.close();
	}
	
	public void deleteFilm(Films film){
		session.beginTransaction();
		session.delete(film);
		session.getTransaction().commit();
		session.close();
	}
	
	
}
