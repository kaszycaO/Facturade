package FacturadeDB.Facturade.UI;

import FacturadeDB.Database.DB_Management.HibernateFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.spi.ServiceException;
import javax.swing.*;

import static java.lang.System.exit;

public class LoginToDatabaseFrame {
    public LoginToDatabaseFrame(){
        final JTextField loginField = new JTextField();
        final JTextField passwordField = new JTextField();

        final Object[] inputs = {
          "Login :", loginField, "Password :", passwordField
        };


        while(true) {
            int messageDialog = JOptionPane.showConfirmDialog(null, inputs,"Log in",JOptionPane.OK_CANCEL_OPTION);

            if(messageDialog == JOptionPane.CANCEL_OPTION){
                exit(0);
            }
            if (loginField.getText().equals("") || passwordField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Some fields are empty!");
            } else {
                try {
                    boolean approved = approvedLoginAndPassword(loginField.getText(), passwordField.getText());
                    if(approved){
                        userLogIn();
                        break;
                    }
                    else{
                        passwordField.setText("");
                    }
                } catch (NumberFormatException ex2) {
                    JOptionPane.showMessageDialog(null, "Wprowadziles bledne dane(Login lub haslo)!");
                }

            }
        }
    }

    private boolean approvedLoginAndPassword(String login, String password){
        try{
            HibernateFactory.username = login;
            HibernateFactory.password = password;
            HibernateFactory factory = new HibernateFactory();

            StandardServiceRegistryBuilder registryBuilder =
                    new StandardServiceRegistryBuilder().applySettings(factory.getConfiguration().getProperties());
            SessionFactory sessionFactory =  factory.getConfiguration().buildSessionFactory(registryBuilder.build());
            sessionFactory.close();
            return true;
        }catch (ServiceException ex){
            JOptionPane.showMessageDialog(null,"Wrong username or password!");
            return false;
        }
    }

    private void userLogIn(){

    }
}
