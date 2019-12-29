package FacturadeDB.Facturade.Factures;

import FacturadeDB.Database.DB_Management.ClientDAO;
import FacturadeDB.Database.DB_Management.HibernateFactory;
import FacturadeDB.Database.DB_Management.MyRepository;
import FacturadeDB.Facturade.Client.client;
import FacturadeDB.Facturade.UI.AppWindow;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**KLasa glowna programu*/
public class facturade {

  /**Funkcja glowna programu*/
  public static void main(String[] args) {
	  AppWindow window = new AppWindow();

      ClientDAO clientDao = new ClientDAO();
	  client tester = new client("testDelete","test",13231,"312312","1321312","1231231","131231");
	  //clientDao.save(tester);
	  clientDao.delete(tester);
  }



}
