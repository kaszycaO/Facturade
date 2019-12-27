package FacturadeDB.Facturade.Factures;

import FacturadeDB.Facturade.Client.client;
import FacturadeDB.Facturade.Product.product;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class factureCreator {
	private final static String newline = "\n";
	final private ArrayList<product> _newProdList;
	final private JTextArea _printingArea;
	
	
	public factureCreator(JTextArea printingArea){
		_printingArea = printingArea;
		_newProdList = new ArrayList<product>();
		
	}
	
	public void printFacture(final client _client) {
		_printingArea.setText("");
		_printingArea.setFont(new Font("Serif",Font.BOLD,16));
		_printingArea.append(newline);
		_printingArea.append("Imie: " + _client.getClientName() + newline + "Nazwisko : " + _client.getClientSurname() + newline + "Adres zamieszkania : " + _client.getClientAdress() + newline + "NIP : " + _client.getClientNIP());
		_printingArea.append(newline + newline+ newline);
		
		int sum = 0;

		for (product product : _newProdList) {
			//_printingArea.append(product.getNameOfProduct() + " " + product.getPriceOfProduct() + " " + product.getQuantityOfProduct() + newline);
			//sum += product.getPriceOfProduct() * product.getQuantityOfProduct();
		}
		
		_printingArea.append(newline + newline);
		_printingArea.append("Suma do Zaplaty : " + sum + " [ZL]");
		
	}
	
	
	public void addProduct(final product _product) {
		_newProdList.add(_product);
	}
	
	/*public void saveFacture(final client _client) {
		_client.addFacture(new facture(_newProdList, _client.getFactureList().size() + 1,_printingArea.getText()));
		_printingArea.setText("");
	}*/
}
