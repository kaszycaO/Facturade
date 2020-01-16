package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Product.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO implements DAO_Repository<Product> {

    private ArrayList<Product> Products;
    private HibernateFactory factory = new HibernateFactory();
    Session session = factory.getSessionFactory().openSession();

    public ProductDAO(){
        loadProducts();
    }

    private void loadProducts(){ Products = (ArrayList<Product>) session.createQuery("from Product").list();}

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

        if(params[0].equals("update")){
            int quantity = Integer.parseInt(params[1]);
            int id = Integer.parseInt(params[2]);
            List<Object[]> rows = session.createSQLQuery("CALL facturadedb.updatequantity(:id, :quantity)").setParameter("id",id).setParameter("quantity",quantity).list();


        }

    }

    @Override
    public void delete(Product product) {

        Session session = factory.getSessionFactory().openSession();
        int productID = product.get_productID();
        List<Object[]> rows  = session.createSQLQuery("CALL facturadedb.removeProduct(:productID)").setParameter("productID",productID).list();
        Products.remove(product);






//        Session session = factory.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Serializable id = product.get_productID();
//        Object persistentInstance = session.load(product.getClass(),id);
//        try {
//            session.delete(persistentInstance);
//            session.getTransaction().commit();
//            Products.remove(product);
//        } catch (Exception ex) {
//            transaction.rollback();
//            ex.printStackTrace();
//            throw new RuntimeException(ex);
//        } finally {
//            session.close();
//            factory.getSessionFactory().close();
//        }
    }



}

