package FacturadeDB.Facturade.Product;

import FacturadeDB.Database.DB_Management.ResponseParser;

import java.awt.Choice;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class productsList extends Choice{
	
	private static final long serialVersionUID = 846808656429232480L;
	
	private static ResponseParser parser;
	
	private static List<product> listOfProducts;

	public void addProductToList(final product _product) {
		
		if(checkIfProductExists(_product)) {
			JOptionPane.showMessageDialog(null, "Taki produkt juz znajduje sie na liscie!" + " [" + _product.getNameOfProduct() + "]");
		}
		else { 
			listOfProducts.add(_product);
			//add(_product.getNameOfProduct() + " [" + _product.getPriceOfProduct() + "]" + " [" + _product.getQuantityOfProduct() + "]");
		}
	}
	
	public List<product> getListOfProducts() {
		return listOfProducts;
	}
	
	public productsList(){
		super();
		listOfProducts = new ArrayList<>();
		parser = new ResponseParser();
	}
	
	public product getProductFromList(final int index) {
		return listOfProducts.get(index);
	}
	
	public product getPickedProduct() {
		return listOfProducts.get(this.getSelectedIndex());
	}
	
	public static boolean checkIfProductExists(final product _product) {
		for(final product product:listOfProducts) {
			if(product.getNameOfProduct().equals(_product.getNameOfProduct()) && product.getPriceOfProduct() == _product.getPriceOfProduct()) {
				return true;
			}
		}
		
		return false;
	}
}

