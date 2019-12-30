package FacturadeDB.Facturade.UI;

import FacturadeDB.Database.DB_Management.ProductDAO;
import FacturadeDB.Facturade.Client.Client;
import FacturadeDB.Facturade.Client.ClientChoiceList;
import FacturadeDB.Facturade.Factures.FactureCreator;
import FacturadeDB.Facturade.Product.Product;
import FacturadeDB.Facturade.Product.ProductsChoiceList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ButtonController {
	private final MainPanel _panel;
	ButtonController(final MainPanel panel){
		_panel = panel;
	}
	
	public void doCertainAction(final String _action) {
		switch(_action) {
			case "Save Facture":
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
	
	/*public void saveFacture() {
		_panel.getFacCreator().saveFacture(_panel.getClientList().getPickedClient());
	}*/
	
	public void addProdToFacture() {
		if(_panel.getClientList() != null) {
			ProductsChoiceList prodList = _panel.getProductsList();
			Client client = _panel.getClientList().getPickedClient();
			FactureCreator facCreator = _panel.getFacCreator();
			
			if(prodList.getItemCount() >= 1) {
				facCreator.addProduct(prodList.getPickedProduct());
				facCreator.printFacture(client);
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

				ProductDAO prodDao = new ProductDAO();
            	prodDao.save(new Product(_splittedInput.get(0),_price,_quantity));
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
		final JTextArea label = _panel.getFrame().getPrintArea();
		final ArrayList<Integer> factureIDs = new ArrayList<>();
		
		/*for(final Facture _facture :_clientList.getPickedClient().getFactureList()) {
			factureIDs.add(_facture.getFactureID());
		}
		
		
		if(_clientList.getPickedClient().getFactureList().size() >= 1) {
    		final int pickedFactureID = (int) JOptionPane.showInputDialog(null,"Wybierz fakture","Pick Facture for",JOptionPane.PLAIN_MESSAGE,null, (Object [])factureIDs.toArray(),_clientList.getPickedClient().getFactureList().get(0).getFactureID());
    		label.setText(_clientList.getPickedClient().getFactureList().get(pickedFactureID - 1).getFacInSTR());
    	}*/
	}

	public void createFacture() {
		final Client _client = _panel.getClientList().getPickedClient();
		final FactureCreator _facCreator = new FactureCreator(_panel.getFrame().getPrintArea());
		_facCreator.printFacture(_client);
	}
}
