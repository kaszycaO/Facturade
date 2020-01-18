package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Factures.Facture;
import FacturadeDB.Facturade.Product.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
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

    public int getNewFactureID(){

        List factureID = session.createSQLQuery("SELECT MAX(id) FROM facturadedb.faktury LIMIT 1").list();
        int id;
        if(factureID.get(0) != null)
            id = Integer.parseInt(factureID.get(0).toString());
        else
            id = 0;

        return id;

    }


    public ArrayList<Facture> getAllFactureByClientID(int clientID){
        Facture parsedFacture = new Facture();

        List<Object[]> rows = session.createSQLQuery("CALL facturadedb.showinvoice(:clientID)").setParameter("clientID",clientID).list();
        ArrayList<Facture> factureList = new ArrayList<>();

        if(rows.size() == 0){
            JOptionPane.showMessageDialog(null,"This client does not have any facture!");
            return null;
        }

        int id = Integer.parseInt(rows.get(0)[3].toString());
       // int firstID = id;

        parsedFacture.set_factureID(id);

        for(Object[] row : rows){
            if (id != Integer.parseInt(row[3].toString())) {
                factureList.add(parsedFacture);
                parsedFacture = new Facture();
                parsedFacture.set_factureID(Integer.parseInt(row[3].toString()));
            }
            Product newProduct = new Product(row[1].toString(),Float.parseFloat(row[2].toString()),
                    Integer.parseInt(row[0].toString()));
            newProduct.set_productID(Integer.parseInt(row[4].toString()));
            parsedFacture.addproduct(newProduct);
            id = Integer.parseInt(row[3].toString());
        }
        factureList.add(parsedFacture);
        return factureList;

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

        Session session = factory.getSessionFactory().openSession();
        int factureID = facture.getFactureID();
        List<Object[]> rows  = session.createSQLQuery("CALL facturadedb.removeInvoice(:invoiceID)").setParameter("invoiceID",factureID).list();
        factures.remove(facture);

    }
}
