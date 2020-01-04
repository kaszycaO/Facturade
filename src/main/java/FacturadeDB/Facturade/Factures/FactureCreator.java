package FacturadeDB.Facturade.Factures;

import FacturadeDB.Database.DB_Management.FactureDAO;
import FacturadeDB.Facturade.Client.Client;

import java.awt.Font;

import FacturadeDB.Facturade.Product.Product;
import FacturadeDB.Facturade.UI.ClientPanel;
import FacturadeDB.Facturade.UI.InvoicePanel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextArea;

public class FactureCreator {
	private final static String newline = "\n";
	private ArrayList<Product> _newProdList;
	//private JTextArea _printingArea;
	private InvoicePanel _invoicePanel;



	
	public FactureCreator(InvoicePanel invoicePanel){
		//_printingArea = printingArea;
		_invoicePanel = invoicePanel;
		_newProdList = new ArrayList<>();
	}
	
	public void printFacture(final Client _client) {

		String clientText = "Kupujący: " + newline + newline + _client.getClientName() + newline + _client.getClientSurname() + newline +  _client.getClientAdress() + newline +  _client.getClientNIP();
		_invoicePanel.get_clientPanel().setClientText(clientText);
		
		int sum = 0;
		int counter = 0;


		_invoicePanel.clearTextAreas();
		for (Product product : _newProdList) {
			counter++;

			//TODO ilosc do poprawy
			sum += product.getPriceOfProduct() * product.get_stockQuantity();

			_invoicePanel.setInvoiceText(counter+"",product.getNameOfProduct(),product.get_stockQuantity()+"",product.getPriceOfProduct()+"",12+"");
		}
		_invoicePanel.setFinalPrice(sum+"");
		
	}
	
	
	public void addProduct(final Product _product) {
		_newProdList.add(_product);
	}
	
	public void saveFacture(final Client _client) {
		FactureDAO factureDao = new FactureDAO();
		int clientID = _client.get_clientID();
		Facture newFacture = new Facture();
		newFacture.set_factureID(factureDao.getNewFactureID()+1);
		newFacture.set_clientID(clientID);

		for(Product product : this._newProdList){

			newFacture.set_productID(product.get_productID());

			System.out.println("ProductID = " + product.get_productID());
			newFacture.set_prodQuantity(product.get_stockQuantity());
			newFacture.set_factureDate(null);
			factureDao.save(newFacture);
		}
		_newProdList.clear();
		//_printingArea.setText("");
		_invoicePanel.clearTextAreas();
		_invoicePanel.get_clientPanel().clearAreas();
	}

	public void set_newProdList(ArrayList<Product> _newProdList) {
		this._newProdList = _newProdList;
	}
}
