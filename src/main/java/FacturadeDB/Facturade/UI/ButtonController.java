package FacturadeDB.Facturade.UI;

import FacturadeDB.Database.DB_Management.FactureDAO;
import FacturadeDB.Database.DB_Management.ProductDAO;
import FacturadeDB.Facturade.Client.Client;
import FacturadeDB.Facturade.Client.ClientChoiceList;
import FacturadeDB.Facturade.Factures.Facture;
import FacturadeDB.Facturade.Factures.FactureCreator;
import FacturadeDB.Facturade.Product.Product;
import FacturadeDB.Facturade.Product.ProductsChoiceList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;





public class ButtonController {
	private MainPanel _panel;
	private boolean checkIfRemove = false;


	ButtonController(final MainPanel panel){
		_panel = panel;
	}
	
	public void doCertainAction(final String _action) {
		switch(_action) {
			case "Save Facture":
				saveFacture();
				break;
			case "Add Product to Facture":
				addProdToFacture();
				break;
			case "Add Product to list":
				addProdToList();
				break;
			case "Add new Client":
				addNewClient();
				break;
			case "Show factures":
				showFactures();
				break;
			case "Create Facture":
				createFacture();
				break;
			default:	
				
		}
	}
	
	public void saveFacture() {
		_panel.getFacCreator().saveFacture(_panel.getClientList().getPickedClient());
	}

	private void setQuantityInFacture(ProductsChoiceList productList, Client client, FactureCreator facCreator) {


		final String input = JOptionPane.showInputDialog(null, "Podaj ilosc");
		if (input == null) {
			return;
		}

		int quantity = Integer.parseInt(input);
		if (quantity > productList.getPickedProduct().get_stockQuantity()) {
			JOptionPane.showMessageDialog(null, "Nie ma tylu produktow na stanie!");
			return;
		}
		else {

			if(checkIfRemove)
				facCreator.removeProduct(productList.getPickedProduct());

			facCreator.addProduct(productList.getPickedProduct(),quantity);
			facCreator.printFacture(client);


		}
	}

	public void addProdToFacture() {

		if(_panel.getClientList() != null) {
			ProductsChoiceList prodList = _panel.getProductsList();
			Client client = _panel.getClientList().getPickedClient();
			FactureCreator facCreator = _panel.getFacCreator();

			if(prodList.getItemCount() >= 1) {
				if(facCreator.isOnList(prodList.getPickedProduct().get_productID())) {
					int reply = JOptionPane.showConfirmDialog(null, "Czy chcesz zamienić wybrany produkt?", "Produkt jest juz na fakturze!", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.NO_OPTION) {
						checkIfRemove = false;
						return;
					}
					else {
						checkIfRemove = true;
						setQuantityInFacture(prodList, client, facCreator);
						facCreator.printFacture(client);
						checkIfRemove = false;
					}
				}
				else {

					setQuantityInFacture(prodList, client, facCreator);
					facCreator.printFacture(client);

				}

			}

		}
	}
	
	public void addProdToList() {
		float _price = 0;
		int _quantity = 0;
		
    	try {

        	final String input = JOptionPane.showInputDialog(null,"Wprowadz dane nastepujaco : [nazwa],[cena],[ilosc dostepnych sztuk]");
        	if(input == null) {return;}

        	final List<String> _splittedInput = Arrays.asList(input.split(","));
        	try {
        		_price = Float.parseFloat(_splittedInput.get(1));
            	_quantity = Integer.parseInt(_splittedInput.get(2));

            	if(_price <= 0) {
            		throw new NumberFormatException("Podales za niska cene!");
            	}
            	if(_quantity <= 0) {
            		throw new NumberFormatException("Taka ilosc nie ma sensu");
            	}

				
				_panel.getProductsList().addProductToList(new Product(_splittedInput.get(0),_price,_quantity));


        	}
        	catch(NumberFormatException ex) {
        		JOptionPane.showMessageDialog(null,"Podales zly format danych!");
        	}
    	}
    	catch(NumberFormatException ex) {
    		JOptionPane.showMessageDialog(null, ex.getMessage());
    	}
	}
	
	public void addNewClient() {
		AddClientFrame _addFrame = new AddClientFrame(_panel.getClientList());
	}
	
	public void showFactures() {

		final ClientChoiceList _clientList = _panel.getClientList();
		//final JTextArea label = _panel.getFrame().getPrintArea();
		final ArrayList<Integer> factureIDs = new ArrayList<>();

		FactureDAO factureDAO = new FactureDAO();
		ArrayList<Facture> factures = factureDAO.getAllFactureByClientID(_clientList.getPickedClient().get_clientID());

		if(factures == null){
			return;
		}

		for(Facture facture : factures){
			factureIDs.add(facture.getFactureID());
		}

		final int pickedFactureID = (int) JOptionPane.showInputDialog(null,"Wybierz fakture","Pick Facture for",JOptionPane.PLAIN_MESSAGE,null, (Object [])factureIDs.toArray(),factures.toArray());
		FactureCreator facCreator = _panel.getFacCreator();

		Facture pickedFacture = new Facture();
		for(Facture facture : factures){
			if(pickedFactureID == facture.getFactureID()){
				pickedFacture = facture;
			}
		}

		facCreator.set_newProdList(factures.get(factures.indexOf(pickedFacture)).getProductListFromFac());
		facCreator.set_currentQuantity();
		facCreator.printFacture(_panel.getClientList().getPickedClient());
	}

	public void createFacture() {
		Client _client = _panel.getClientList().getPickedClient();
		FactureCreator _facCreator = _panel.getFacCreator();
		_facCreator.clearCurrentQuantity();
		_facCreator.set_newProdList(new ArrayList<>());
		_facCreator.printFacture(_client);
	}
}
