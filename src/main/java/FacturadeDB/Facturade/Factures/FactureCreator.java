package FacturadeDB.Facturade.Factures;

import FacturadeDB.Database.DB_Management.FactureDAO;
import FacturadeDB.Facturade.Client.Client;

import java.awt.Font;

import FacturadeDB.Facturade.Product.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextArea;

public class FactureCreator {
	private final static String newline = "\n";
	private ArrayList<Product> _newProdList;
	private JTextArea _printingArea;
	
	
	public FactureCreator(JTextArea printingArea){
		_printingArea = printingArea;
		_newProdList = new ArrayList<>();
	}
	
	public void printFacture(final Client _client) {
		_printingArea.setText("");
		_printingArea.setFont(new Font("Serif",Font.BOLD,16));
		_printingArea.append(newline);
		_printingArea.append("Imie: " + _client.getClientName() + newline + "Nazwisko : " + _client.getClientSurname() + newline + "Adres zamieszkania : " + _client.getClientAdress() + newline + "NIP : " + _client.getClientNIP());
		_printingArea.append(newline + newline+ newline);
		
		int sum = 0;

		for (Product product : _newProdList) {
			_printingArea.append(product.getNameOfProduct() + " " + product.getPriceOfProduct() + " " + product.get_stockQuantity() + newline);
			sum += product.getPriceOfProduct() * product.get_stockQuantity();
		}
		
		_printingArea.append(newline + newline);
		_printingArea.append("Suma do Zaplaty : " + sum + " [ZL]");
		
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
		_printingArea.setText("");
	}

	public void set_newProdList(ArrayList<Product> _newProdList) {
		this._newProdList = _newProdList;
	}
}
