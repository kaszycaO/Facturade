package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Client.client;
import FacturadeDB.Facturade.Factures.facture;
import FacturadeDB.Facturade.Product.product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        configuration.addAnnotatedClass(client.class);
        configuration.addAnnotatedClass(facture.class);
        configuration.addAnnotatedClass(product.class);

        StandardServiceRegistryBuilder registryBuilder =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(registryBuilder.build());
        return sessionFactory;
    }
}