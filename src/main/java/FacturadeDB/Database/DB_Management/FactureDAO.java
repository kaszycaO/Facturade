package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Factures.Facture;
import FacturadeDB.Facturade.Product.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FactureDAO implements DAO_Repository<Facture>{

    private List<Facture> factures;
    private HibernateFactory factory = new HibernateFactory();
    Session session = factory.getSessionFactory().openSession();

    public FactureDAO(){
        factures = (ArrayList<Facture>) session.createQuery("from Facture").list();
    }
    @Override
    public Optional<Facture> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Facture> getAll() {


        return this.factures;
    }

    public Facture getAllFactureByClientID(int clientID){
        Facture parsedFacture = new Facture();
        List<Object[]> rows = session.createSQLQuery("SELECT e.ilosc, a.nazwa,a.cena_sztuki FROM FacturadeDB.faktury as e join FacturadeDB.produkty as a on e.id_produktu = a.id where e.id_clienta=:clientID").setParameter("clientID",clientID).list();
        List<Product> querriedProductList;

        for(Object[] row : rows){
            parsedFacture.addproduct(new Product(row[1].toString(),Integer.parseInt(row[2].toString()),Integer.parseInt(row[0].toString())));
        }
        return parsedFacture;
    }

    @Override
    public void save(Facture facture)
    {
        Session session = factory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(facture);
            session.getTransaction().commit();
            factures.add(facture);
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
    public void update(Facture facture, String[] params) {

    }

    @Override
    public void delete(Facture facture) {
        /*Session session = factory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Serializable id = facture.get_clientID();
        Object persistentInstance = session.load(facture.getClass(),id);
        try {
            session.delete(persistentInstance);
            session.getTransaction().commit();
            factures.remove(facture);
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
            factory.getSessionFactory().close();
        }*/
    }
}
