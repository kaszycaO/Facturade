package FacturadeDB.Facturade.Product;

import javax.persistence.*;

@Table( name = "Produkty")
@Entity
public class product {

	@Id
	@Column( name = "id")
	private int _productID;
	@Column( name = "nazwa")
	private String _name;
	@Column( name = "cena_sztuki")
	private int _price;

	public product(){};

	public product(final String name,final int price){
		this._name = name;
		this._price = price;
	}
	
	public int getPriceOfProduct(){
		return _price;
	}
	
	public String getNameOfProduct() {
		return _name;
	}
	
	public void setProductPrice(final int price) {
		_price = price;
	}
	
	public void renameProduct(final String name) {
		_name = name;
	}
	
}
