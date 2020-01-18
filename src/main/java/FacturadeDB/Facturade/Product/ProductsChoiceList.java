package FacturadeDB.Facturade.Product;

import FacturadeDB.Database.DB_Management.ProductDAO;
import java.awt.Choice;
import java.util.List;
import javax.swing.*;

public class ProductsChoiceList extends Choice {

    private static final long serialVersionUID = 846808656429232480L;
    private static List<Product> listOfProducts;

    public void addProductToList(Product _product) {

        ProductDAO productDAO = new ProductDAO();
        if (checkIfProductExists(_product)) {
            JOptionPane.showMessageDialog(null, "Taki produkt juz znajduje sie na liscie!" + " [" + _product.getNameOfProduct() + "]");
        } else {
            add(_product.getNameOfProduct() + " [" + _product.getPriceOfProduct() + "]" + " [" + _product.get_stockQuantity() + "]");
            listOfProducts.add(_product);
            productDAO.save(_product);
        }
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public ProductsChoiceList() {
        super();
       reloadList();
    }

    public Product getProductFromList(final int index) {
        return listOfProducts.get(index);
    }

    public Product getPickedProduct() {
        return listOfProducts.get(this.getSelectedIndex());
    }

    public static boolean checkIfProductExists(final Product _product) {
        for (Product product : listOfProducts) {
            if (product.getNameOfProduct().equals(_product.getNameOfProduct()) && product.getPriceOfProduct() == _product.getPriceOfProduct()) {
                return true;
            }
        }

        return false;
    }

    public void reloadList() {

        ProductDAO productDAO = new ProductDAO();
        this.removeAll();
        listOfProducts = productDAO.getAll();
        for(Product product : listOfProducts){
            add(product.getNameOfProduct() + " [" + product.getPriceOfProduct() + "]" + " [" + product.get_stockQuantity() + "]");
        }

    }

    public void clearList(){ listOfProducts.clear(); }

}

