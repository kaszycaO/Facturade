package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Product.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class ProductDAO implements DAO_Repository<Product> {

    private ArrayList<Product> Products;
    private HibernateFactory factory = new HibernateFactory();
    Session session = factory.getSessionFactory().openSession();

    public ProductDAO(){
        Products = (ArrayList<Product>) session.createQuery("from Product").list();
    }

    @Override
    public Optional<Product> get(long id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Product> getAll() {
        return this.Products;
    }

    @Override
    public void save(Product product)
    {

        Transaction transaction = session.beginTransaction();
        try {
            session.save(product);
            session.getTransaction().commit();
            Products.add(product);
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
    public void update(Product product, String[] params) {

    }

    @Override
    public void delete(Product product) {
        Session session = factory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Serializable id = product.get_productID();
        Object persistentInstance = session.load(product.getClass(),id);
        try {
            session.delete(persistentInstance);
            session.getTransaction().commit();
            Products.remove(product);
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

