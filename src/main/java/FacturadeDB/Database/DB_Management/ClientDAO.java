package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Client.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO implements DAO_Repository<Client>{

    static List<Client> clients;
    private HibernateFactory factory = new HibernateFactory();
    Session session = factory.getSessionFactory().openSession();

    public ClientDAO(){
        clients = (ArrayList<Client>) session.createQuery("from Client").list();
    }
    @Override
    public Optional<Client> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Client> getAll() {
        return this.clients;
    }

    @Override
    public void save(Client client)
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
    public void update(Client client, String[] params) {

    }

    @Override
    public void delete(Client client) {
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

    public static List<Client> getClients() {
        return clients;
    }
}
