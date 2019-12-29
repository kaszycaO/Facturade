package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Client.client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO implements MyRepository<client>{

    private List<client> clients = new ArrayList<>();
    private HibernateFactory factory = new HibernateFactory();

    public void addClient(client client) {

    }

    @Override
    public Optional<client> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<client> getAll() {
        return this.clients;
    }

    @Override
    public void save(client client)
    {
        Session session = factory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(client);
            session.getTransaction().commit();
            clients.add(client);
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
            factory.getSessionFactory().close();
        }
    }

    @Override
    public void update(client client, String[] params) {

    }

    @Override
    public void delete(client client) {
        Session session = factory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Serializable id = client.get_clientID();
        Object persistentInstance = session.load(client.getClass(),id);
        try {
            session.delete(persistentInstance);
            session.getTransaction().commit();
            clients.remove(client);
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
            factory.getSessionFactory().close();
        }
    }
}
