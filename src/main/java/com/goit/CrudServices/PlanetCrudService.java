package com.goit.CrudServices;

import com.goit.Entities.Planet;
import com.goit.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService {

    public static String create(String id, String name) {
        Planet planetFound;
        Planet planet = new Planet();
        planet.setId(id);
        planet.setName(name);

        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
            planetFound = session.createQuery("from Planet where name = \"" + name + "\" and id =  \"" + id + "\"", Planet.class).uniqueResult();
        }
        return planetFound.getId();
    }

    public static String getById(String id) {
        Planet planetFound;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            planetFound = session.get(Planet.class, id);
        }
        if (planetFound == null) {
            throw new IllegalArgumentException("There is no such ID in the table");
        }
        return planetFound.getName();
    }

    public static void setName(String id, String name) {
        getById(id);
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Planet planet = session.get(Planet.class, id);
            planet.setName(name);
            Transaction transaction = session.beginTransaction();
            session.merge(planet);
            transaction.commit();
        }
    }

    public static void deleteById(String id) {
        getById(id);
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Planet planet = session.get(Planet.class, id);
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        }
    }

    public static List<Planet> listAll() {
        List<Planet> planetLines;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            planetLines = session.createQuery("from Planet", Planet.class).list();
        }
        return planetLines;
    }
}
