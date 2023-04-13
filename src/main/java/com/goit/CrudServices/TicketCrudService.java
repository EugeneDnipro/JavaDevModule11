package com.goit.CrudServices;

import com.goit.Entities.Client;
import com.goit.Entities.Planet;
import com.goit.Entities.Ticket;
import com.goit.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.List;

public class TicketCrudService {

    public static long create(long clientId, String fromPlanetId, String toPlanetId) {
        Ticket ticket = new Ticket();
        long addedTicketId;

        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            ticket.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            ticket.setClient(session.get(Client.class, clientId));
            ticket.setFromPlanetId(session.get(Planet.class, fromPlanetId));
            ticket.setToPlanetId(session.get(Planet.class, toPlanetId));

            Transaction transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();

            addedTicketId = session.createQuery("from Ticket where client.id = "
                            + clientId
                            + " and id = (select max(id) from Ticket where client.id = "
                            + clientId
                            + ")", Ticket.class)
                    .uniqueResult()
                    .getId();
        }
        return addedTicketId;
    }

    public static String getById(long id) {
        Ticket ticketFound;
        String ticketFoundClientName;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            ticketFound = session.get(Ticket.class, id);
            if (ticketFound == null) {
                throw new IllegalArgumentException("There is no such ID in the table");
            }
            ticketFoundClientName = ticketFound.getClient().getName();
        }
        return ticketFoundClientName;
    }

    public static void setToPlanetId(long id, String toPlanetId) {
        getById(id);
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Ticket ticket = session.get(Ticket.class, id);
            ticket.setToPlanetId(session.get(Planet.class, toPlanetId));
            Transaction transaction = session.beginTransaction();
            session.merge(ticket);
            transaction.commit();
        }
    }

    public static void deleteById(long id) {
        getById(id);
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Ticket ticket = session.get(Ticket.class, id);
            Transaction transaction = session.beginTransaction();
            session.remove(ticket);
            transaction.commit();
        }
    }

    public static List<Ticket> listAll() {
        List<Ticket> ticketsLines;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            ticketsLines = session.createQuery("from Ticket", Ticket.class).list();
            session.createQuery("from Client", Client.class).list();
            session.createQuery("from Planet", Planet.class).list();
        }
        return ticketsLines;
    }
}
