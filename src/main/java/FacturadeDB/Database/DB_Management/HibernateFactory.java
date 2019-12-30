package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Client.Client;
import FacturadeDB.Facturade.Factures.Facture;
import FacturadeDB.Facturade.Product.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(Facture.class);
        configuration.addAnnotatedClass(Product.class);

        StandardServiceRegistryBuilder registryBuilder =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(registryBuilder.build());
        return sessionFactory;
    }
}