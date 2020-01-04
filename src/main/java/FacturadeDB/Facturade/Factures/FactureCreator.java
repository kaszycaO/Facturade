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
	private InvoicePanel _invoicePanel;
	private ArrayList<Integer> _currentQuantity;




	
	public FactureCreator(InvoicePanel invoicePanel){
		_invoicePanel = invoicePanel;
		_newProdList = new ArrayList<>();
		_currentQuantity = new ArrayList<>();
	}
	
	public void printFacture(final Client _client) {

		String clientText = "Kupujący: " + newline + newline + _client.getClientName() + newline + _client.getClientSurname() + newline +  _client.getClientAdress() + newline +  _client.getClientNIP();
		_invoicePanel.get_clientPanel().setClientText(clientText);
		
		int sum = 0;
		int counter = 0;


		_invoicePanel.clearTextAreas();
		for (Product product : _newProdList) {
			counter++;
			sum += product.getPriceOfProduct() * findProductQuantity(product.get_productID());


			_invoicePanel.setInvoiceText(counter+"",product.getNameOfProduct(),findProductQuantity(product.get_productID())+"",
					product.getPriceOfProduct()+"", product.getPriceOfProduct() * findProductQuantity(product.get_productID())+"");
		}
		_invoicePanel.setFinalPrice(sum+"");
		
	}
	
	
	public void addProduct(final Product _product, int currentQuantity) {
		_newProdList.add(_product);
		_currentQuantity.add(_product.get_productID());
		_currentQuantity.add(currentQuantity);


	}

	public void removeProduct(final Product product){

		for(int i = 0; i < _currentQuantity.size()/2; i++){
			if(_currentQuantity.get(2*i)==product.get_productID())
				_currentQuantity.remove(2*i+1);
				_currentQuantity.remove(2*i);
		}

		_newProdList.remove(product);

	}

	public boolean isOnList(int id) {

		for(Product product : this._newProdList) {

			if(product.get_productID() == id)
				return true;
		}

		return false;
	}

	public int findProductQuantity(int productID){

		for(int i = 0; i < _currentQuantity.size()/2; i++){

			if(_currentQuantity.get(2*i)==productID)
				return _currentQuantity.get(2*i+1);
		}
		return -1;
	}
	
	public void saveFacture(final Client _client) {
		FactureDAO factureDao = new FactureDAO();
		int clientID = _client.get_clientID();
		Facture newFacture = new Facture();
		newFacture.set_factureID(factureDao.getNewFactureID()+1);
		newFacture.set_clientID(clientID);

		for(Product product : this._newProdList){

			newFacture.set_productID(product.get_productID());

			newFacture.set_prodQuantity(findProductQuantity(product.get_productID()));
			newFacture.set_factureDate(null);
			factureDao.save(newFacture);
		}
		_newProdList.clear();
		_currentQuantity.clear();
		_invoicePanel.clearTextAreas();
		_invoicePanel.get_clientPanel().clearAreas();
	}

	public void set_newProdList(ArrayList<Product> _newProdList) {
		this._newProdList = _newProdList;
	}

	public void clearCurrentQuantity(){

		_currentQuantity.clear();

	}
	public void set_currentQuantity()
	{
		_currentQuantity.clear();
		for (Product product : _newProdList) {
			_currentQuantity.add(product.get_productID());
			_currentQuantity.add(product.get_stockQuantity());

		}

	}
}
