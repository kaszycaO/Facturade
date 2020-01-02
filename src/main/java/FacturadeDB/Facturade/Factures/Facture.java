package FacturadeDB.Facturade.Factures;

import FacturadeDB.Facturade.Product.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Table(name = "faktury")
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

	@Transient
	private ArrayList<Product> _productList = new ArrayList<>();

	public Facture(){};

	public Facture(ArrayList<Product> prodList){
		_productList = prodList;
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

	public int get_productID(){ return  this._productID; }

	public int get_prodQuantity(){ return this._prodQuantity; }

	public ArrayList<Product> getProductListFromFac() {
		return _productList;
	}

	public void set_prodQuantity(int _prodQuantity) {
		this._prodQuantity = _prodQuantity;
	}

	public void set_factureID(int _factureID) {
		this._factureID = _factureID;
	}

	public void set_factureDate(String _factureDate) {
		this._factureDate = _factureDate;
	}

	public void set_productID(int _productID) {
		this._productID = _productID;
	}

	public void set_clientID(int _clientID) {
		this._clientID = _clientID;
	}

	public void set_productList(ArrayList<Product> _productList) {
		this._productList = _productList;
	}
}
