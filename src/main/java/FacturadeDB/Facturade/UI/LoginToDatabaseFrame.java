package FacturadeDB.Facturade.UI;

import FacturadeDB.Database.DB_Management.HibernateFactory;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.lang.module.Configuration;

public class LoginToDatabaseFrame {
    /*LoginToDatabaseFrame(){
        final JTextField loginField = new JTextField();
        final JTextField passwordField = new JTextField();

        final Object[] inputs = {
          "Login :", loginField, "Password :", passwordField
        };

        JOptionPane.showConfirmDialog(null, inputs,"Log in",JOptionPane.OK_CANCEL_OPTION);

        while(true) {
            if (loginField.getText().equals("") || passwordField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Nie wypelniles wszystkich pol!");
            } else {
                try {
                    checkLoginAndPassword(loginField.getText(), passwordField.getText());
                    userLogIn();
                    break;
                } catch (NumberFormatException ex2) {
                    JOptionPane.showMessageDialog(null, "Wprowadziles bledne dane(Login lub haslo)!");
                }

            }
        }
    }

    private void checkLoginAndPassword(String login, String password){
        Configuration
        configuration.configure();

        StandardServiceRegistryBuilder registryBuilder =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory =  config.configuration.buildSessionFactory(registryBuilder.build());
        User result = null;

        Session session = HibernateUtil
        String sql = "select s from User where username=:username and password=password(:password)";
        Query query = session.createQuery(sql);
        query.setString("username", login);
        query.setString("password", password);

        result = (User) query.uniqueResult();
    }

    private void userLogIn(){

    }

    private Configuration getConfigurationForLogIn(){
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/FacturadeDB");
        configuration.setProperty("hibernate.connection.username",username);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.show_sql","true");
        configuration.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        configuration.addAnnotatedClass(FacturadeDB.Facturade.Client.Client.class);
        configuration.addAnnotatedClass(FacturadeDB.Facturade.Product.Product.class);
        configuration.addAnnotatedClass(FacturadeDB.Facturade.Factures.Facture.class);
    }*/
}
