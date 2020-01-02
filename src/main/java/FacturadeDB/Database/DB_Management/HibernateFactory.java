package FacturadeDB.Database.DB_Management;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    public static String username;
    public static String password;
    private Configuration configuration = new Configuration();

    public HibernateFactory(){
        setHibernateConfiguration();
    }
    public SessionFactory getSessionFactory() {
        
        configuration.configure();

        StandardServiceRegistryBuilder registryBuilder =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory =  configuration.buildSessionFactory(registryBuilder.build());
        return sessionFactory;
    }

    public void setHibernateConfiguration(){
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

    public Configuration getConfiguration(){
        return this.configuration;
    }
}