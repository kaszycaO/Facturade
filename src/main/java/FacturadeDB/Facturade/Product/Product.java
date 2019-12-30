package FacturadeDB.Facturade.Product;

import javax.persistence.*;
import java.io.Serializable;

@Table( name = "produkty")
@Entity
public class Product implements Serializable {

	@Id
	@Column( name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _productID;
	@Column( name = "nazwa")
	private String _name;
	@Column( name = "cena_sztuki")
	private float _price;
	@Column(name = "zasoby")
	private int _stockQuantity;

	public Product(){};

	public Product(String name, float price, int stockQuantity){
		this._name = name;
		this._price = price;
		this._stockQuantity = stockQuantity;
	}

	public float getPriceOfProduct(){
		return this._price;
	}

	public int get_productID(){
		return this._productID;
	}

	public String getNameOfProduct() {
		return this._name;
	}

	public int get_stockQuantity(){
		return this._stockQuantity;
	}

	public void setProductPrice(float price) {
		this._price = price;
	}

	public void renameProduct(String name) {
		this._name = name;
	}
	public void set_productID(int quantity){
		this._stockQuantity = quantity;
	}

}
