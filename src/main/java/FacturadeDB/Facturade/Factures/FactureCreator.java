package FacturadeDB.Facturade.Factures;

import FacturadeDB.Facturade.Client.Client;

import java.awt.Font;

import FacturadeDB.Facturade.Product.Product;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class FactureCreator {
	private final static String newline = "\n";
	final private ArrayList<Product> _newProdList;
	final private JTextArea _printingArea;
	
	
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
		//_client.addFacture(new Facture(_newProdList, _client.getFactureList().size() + 1,_printingArea.getText()));
		_printingArea.setText("");
	}
}
