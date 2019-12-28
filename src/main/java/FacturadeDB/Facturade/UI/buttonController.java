package FacturadeDB.Facturade.UI;


import FacturadeDB.Database.DB_Management.DBController;
import FacturadeDB.Facturade.Client.client;
import FacturadeDB.Facturade.Client.clientList;
import FacturadeDB.Facturade.Factures.factureCreator;
import FacturadeDB.Facturade.Product.productsList;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.springframework.context.*;

@Service
public class buttonController {
	private final AppPanel _panel;
	buttonController(final AppPanel panel){
		_panel = panel;
	}
	
	public void doCertainAction(final String _action) {
		switch(_action) {

			case "Save facture":
				//ApplicationContext context = new
				//DBController test = (DBController) context
				// = test.addNewClient(new client("test","test",13231,"312312","1321312","1231231","131231"));
				break;
			case "Add product to facture":
				addProdToFacture();
				break;
			case "Add product to list":
				addProdToList();
				break;
			case "Add new client":
				addNewClient();
				break;
			case "Show factures":
				showFactures();
				break;
			case "Create facture":
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
			final productsList prodList = _panel.getProductsList();
			final client client = _panel.getClientList().getPickedClient();
			final factureCreator facCreator = _panel.getFacCreator();
			
			if(prodList.getItemCount() >= 1) {
				facCreator.addProduct(prodList.getPickedProduct());
				facCreator.printFacture(client);
			}
		}
	}
	
	public void addProdToList() {
		int _price = 0;
		int _quantity = 0;
		
    	try {

        	final String input = JOptionPane.showInputDialog(null,"Wprowadz dane nastepujaco : [nazwa],[cena],[ilosc]");
        	final List<String> _splittedInput = Arrays.asList(input.split(","));
        	
        	try {
            	_price = Integer.parseInt(_splittedInput.get(1));
            	_quantity = Integer.parseInt(_splittedInput.get(2));
            	
            	if(_price <= 0) {
            		throw new NumberFormatException("Podales za niska cene!");
            	}
            	if(_quantity <= 0) {
            		throw new NumberFormatException("Taka ilosc nie ma sensu");
            	}
            	
            	//_panel.getProductsList().addProductToList(new product(_splittedInput.get(0),_price,_quantity));
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
		new addClientFrame(_panel.getClientList());
	}
	
	public void showFactures() {
		final clientList _clientList = _panel.getClientList();
		final JTextArea label = _panel.getFrame().getPrintArea();
		final ArrayList<Integer> factureIDs = new ArrayList<>();
		
		/*for(final facture _facture :_clientList.getPickedClient().getFactureList()) {
			factureIDs.add(_facture.getFactureID());
		}
		
		
		if(_clientList.getPickedClient().getFactureList().size() >= 1) {
    		final int pickedFactureID = (int) JOptionPane.showInputDialog(null,"Wybierz fakture","Pick facture for",JOptionPane.PLAIN_MESSAGE,null, (Object [])factureIDs.toArray(),_clientList.getPickedClient().getFactureList().get(0).getFactureID());
    		label.setText(_clientList.getPickedClient().getFactureList().get(pickedFactureID - 1).getFacInSTR());
    	}*/
	}

	public void createFacture() {
		final client _client = _panel.getClientList().getPickedClient();
		final factureCreator _facCreator = new factureCreator(_panel.getFrame().getPrintArea());
		_facCreator.printFacture(_client);
	}
}
