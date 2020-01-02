package FacturadeDB.Facturade.Factures;

import FacturadeDB.Facturade.Product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;

@Table(name = "Faktury")
@Entity
public class Facture implements Serializable{
	private static final long serialVersionUID = -8820364390362269465L;
	@Id
	@Column( name = "id")
	private int _factureID;
	@Column( name = "id_clienta")
	private int _clientID;
	@Column( name = "id_produktu")
	private int _productID;
	@Column( name = "ilosc")
	private int _prodQuantity;
	@Column( name = "data_sprzedazy")
	private String _factureDate;
	private String _facInSTR;

	private ArrayList<Product> _productList;

	public Facture(){};
	public Facture(ArrayList<Product> prodList, int ID, String facSTR){
		_productList = prodList;
		_factureID = ID;
		_facInSTR = facSTR;
	}
	
	public void addproduct(final Product product) {
		_productList.add(product);
	}
	public void setProductList(final ArrayList<Product> prodList) {
		_productList = prodList;
	}
	
	public int getFactureID() {
		return _factureID;
	}
	
	public String getFacInSTR() {
		return _facInSTR;
	}

	public int get_productID(){ return  this._productID; }

	public int get_prodQuantity(){ return this._prodQuantity; }
	
	public ArrayList<Product> getProductListFromFac() {
		return _productList;
	}
}	
