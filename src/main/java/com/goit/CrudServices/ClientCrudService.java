package com.goit.CrudServices;

import com.goit.Entities.Client;
import com.goit.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientCrudService {

    public static long create(String name) {
        Client client = new Client();
        long addedClientId;

        client.setName(name);

        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
            addedClientId = session.createQuery("from Client where name = \""
                            + name
                            + "\" and id = (select max(id) from Client where name = \""
                            + name
                            + "\")", Client.class)
                    .uniqueResult()
                    .getId();
        }
        return addedClientId;
    }

    public static String getById(long id) {
        Client clientFound;
        String clientFoundName;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            clientFound = session.get(Client.class, id);
        }
        if (clientFound == null) {
            throw new IllegalArgumentException("There is no such ID in the table");
        }
        clientFoundName = clientFound.getName();
        return clientFoundName;
    }

    public static void setName(long id, String name) {
        getById(id);
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Client client = session.get(Client.class, id);
            client.setName(name);
            Transaction transaction = session.beginTransaction();
            session.merge(client);
            transaction.commit();
        }
    }

    public static void deleteById(long id) {
        getById(id);
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Client client = session.get(Client.class, id);
            Transaction transaction = session.beginTransaction();
            session.remove(client);
            transaction.commit();
        }
    }

    public static List<Client> listAll() {
        List<Client> clientLines;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            clientLines = session.createQuery("from Client", Client.class).list();
        }
        return clientLines;
    }
}
