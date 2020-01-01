package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Client.Client;
import FacturadeDB.Facturade.Factures.Facture;
import FacturadeDB.Facturade.Product.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    public static String username;
    public static String password;
    Configuration configuration = new Configuration();

    public SessionFactory getSessionFactory() {

        setHibernateConfiguration("root","root");
        configuration.configure();

        StandardServiceRegistryBuilder registryBuilder =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory =  configuration.buildSessionFactory(registryBuilder.build());
        return sessionFactory;
    }

    public void setHibernateConfiguration(String username, String password){
        configuration.setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/FacturadeDB");
        configuration.setProperty("hibernate.connection.username",username);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.show_sql","true");
        configuration.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        configuration.addAnnotatedClass(FacturadeDB.Facturade.Client.Client.class);
        configuration.addAnnotatedClass(FacturadeDB.Facturade.Product.Product.class);
        configuration.addAnnotatedClass(FacturadeDB.Facturade.Factures.Facture.class);
    }
}