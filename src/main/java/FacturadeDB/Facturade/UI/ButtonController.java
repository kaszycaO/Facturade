package FacturadeDB.Facturade.UI;

import FacturadeDB.Database.DB_Management.ClientDAO;
import FacturadeDB.Database.DB_Management.DBBackup;
import FacturadeDB.Database.DB_Management.FactureDAO;
import FacturadeDB.Database.DB_Management.ProductDAO;
import FacturadeDB.Facturade.Client.Client;
import FacturadeDB.Facturade.Client.ClientChoiceList;
import FacturadeDB.Facturade.Factures.Facture;
import FacturadeDB.Facturade.Factures.FactureCreator;
import FacturadeDB.Facturade.Product.Product;
import FacturadeDB.Facturade.Product.ProductsChoiceList;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;





public class ButtonController {
	private MainPanel _panel;

	private boolean checkIfRemove = false;
	private boolean makeInvoice = false;


	ButtonController(final MainPanel panel) {
		_panel = panel;
	}

	public void doCertainAction(final String _action) {
		switch (_action) {
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
			case "Delete Invoice":
				deleteData("Invoice");
				break;
			case "Delete Product":
				deleteData("Product");
				break;
			case "Delete Client":
				deleteData("Klient");
				break;
				case "Backup":
					doBackup();
				break;
			case "Do Delivery":
				updateQuantity();
				break;
				case "Price":
				modifyPrice();
				break;
			case "Show":
				selectAll();
				break;
			default:

		}
	}

	private Product prepareProduct() {

		Product pickedProduct = new Product();
		ProductsChoiceList _productList = _panel.getProductsList();

		List<Integer> productsIDs = new ArrayList<>();
		for (Product product : _productList.getListOfProducts()) {
			productsIDs.add(product.get_productID());
		}

        if (_productList.getListOfProducts() == null) {
            return null;
        }
		final int pickedProductID = (int) JOptionPane.showInputDialog(null, "Wybierz produkt", "Pick product for", JOptionPane.PLAIN_MESSAGE, null, (Object[]) productsIDs.toArray(), _productList.getListOfProducts().toArray());

		for (Product product : _productList.getListOfProducts()) {
			if (pickedProductID == product.get_productID()) {
				pickedProduct = product;
			}
		}
		return pickedProduct;

	}

	private void updateQuantity() {

		ProductDAO productDAO = new ProductDAO();
		Product pickedProduct = prepareProduct();

		if(pickedProduct == null){
            JOptionPane.showMessageDialog(null, "Brak produktow!");
            return;
        }

		int parsedInput;

		final String input = JOptionPane.showInputDialog(null, "Podaj ilosc");
		if (input == null) {
			return;
		}

		try {
			parsedInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return;
		}

		int newQuantity = pickedProduct.get_stockQuantity() + parsedInput;

		String[] args = new String[3];
		args[0] = "update";
		args[2] = pickedProduct.get_productID() + "";
		args[1] = newQuantity + "";

		productDAO.update(null, args);
		_panel.getProductsList().reloadList();


	}

	public void saveFacture() {
		_panel.getFacCreator().saveFacture(_panel.getClientList().getPickedClient());
		_panel.getProductsList().reloadList();
		makeInvoice = false;


	}

	private void setQuantityInFacture(ProductsChoiceList productList, Client client, FactureCreator facCreator) {
		int quantity;
		final String input = JOptionPane.showInputDialog(null, "Podaj ilosc");
		if (input == null) {
			return;
		}

		try{
			quantity = Integer.parseInt(input);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "To nie jest liczba calkowita!");
			return;
		}

		if (quantity < 0){JOptionPane.showMessageDialog(null, "To nie ma sensu!"); return;}
		if (quantity > productList.getPickedProduct().get_stockQuantity()) {
			JOptionPane.showMessageDialog(null, "Nie ma tylu produktow na stanie!");
			return;
		}
		else {

			if (checkIfRemove)
				facCreator.removeProduct(productList.getPickedProduct());

			facCreator.addProduct(productList.getPickedProduct(), quantity);
			facCreator.printFacture(client);


		}
	}

	public void addProdToFacture() {

		if (_panel.getClientList() != null && makeInvoice) {
			ProductsChoiceList prodList = _panel.getProductsList();
			Client client = _panel.getClientList().getPickedClient();
			FactureCreator facCreator = _panel.getFacCreator();

			if (prodList.getItemCount() >= 1) {
				if (facCreator.isOnList(prodList.getPickedProduct().get_productID())) {
					int reply = JOptionPane.showConfirmDialog(null, "Czy chcesz zamienić wybrany produkt?", "Produkt jest juz na fakturze!", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.NO_OPTION) {
						checkIfRemove = false;
						return;
					} else if (reply == JOptionPane.YES_OPTION) {
						checkIfRemove = true;
						setQuantityInFacture(prodList, client, facCreator);
						facCreator.printFacture(client);
						checkIfRemove = false;
					}
					else
						return;
				} else {
					setQuantityInFacture(prodList, client, facCreator);
					facCreator.printFacture(client);
				}

			}

		}
	}

	public void addProdToList() {
		float _price = 0;
		int _quantity;

		try {

			final String input = JOptionPane.showInputDialog(null, "Wprowadz dane nastepujaco : [nazwa],[cena],[ilosc dostepnych sztuk]");
			if (input == null) {
				return;
			}

			final List<String> _splittedInput = Arrays.asList(input.split(","));
			try {
				_price = Float.parseFloat(_splittedInput.get(1));
				_quantity = Integer.parseInt(_splittedInput.get(2));

				if (_price <= 0) {
					throw new NumberFormatException("Podales za niska cene!");
				}
				if (_quantity <= 0) {
					throw new NumberFormatException("Taka ilosc nie ma sensu");
				}

				_panel.getProductsList().addProductToList(new Product(_splittedInput.get(0), _price, _quantity));


			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Podales zly format danych!");
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	public void addNewClient() {
		AddClientFrame _addFrame = new AddClientFrame(_panel.getClientList());
	}

	public void showFactures() {

		final ClientChoiceList _clientList = _panel.getClientList();
		final ArrayList<Integer> factureIDs = new ArrayList<>();

		FactureDAO factureDAO = new FactureDAO();
		ArrayList<Facture> factures = factureDAO.getAllFactureByClientID(_clientList.getPickedClient().get_clientID());

		if (factures == null) {
			return;
		}

		for (Facture facture : factures) {
			factureIDs.add(facture.getFactureID());
		}

		final int pickedFactureID = (int) JOptionPane.showInputDialog(null, "Wybierz fakture", "Pick Facture for", JOptionPane.PLAIN_MESSAGE, null, (Object[]) factureIDs.toArray(), factures.toArray());
		FactureCreator facCreator = _panel.getFacCreator();

		Facture pickedFacture = new Facture();
		for (Facture facture : factures) {
			if (pickedFactureID == facture.getFactureID()) {
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
		makeInvoice = true;
	}


	public void deleteData(String type) {


		if (type.equals("Klient")) {
			ClientDAO client = new ClientDAO();
			Client pickedClient = new Client();
			ClientChoiceList _clientList = _panel.getClientList();
			List<Client> new_client = client.getAll();

			List<Integer> clientsIDs = new ArrayList<>();
			for (Client klient : new_client) {
				clientsIDs.add(klient.get_pesel());
			}

            if (new_client == null) {
                JOptionPane.showMessageDialog(null,
                        "Brak klientow!");
                return;
            }
			final int pickedClientPesel = (int) JOptionPane.showInputDialog(null, "Wybierz klienta", "Pick client for", JOptionPane.PLAIN_MESSAGE, null, (Object[]) clientsIDs.toArray(), new_client.toArray());

			for (Client klient : new_client) {
				if (pickedClientPesel == klient.get_pesel()) {
					pickedClient = klient;
				}
			}

			client.delete(pickedClient);
			_clientList.reloadClientList();
		} else if (type.equals("Invoice")) {
			FactureDAO factureDAO = new FactureDAO();
			Facture pickedFacture = new Facture();
			List<Facture> factures = factureDAO.getAll();

			List<Integer> factureIDs = new ArrayList<>();
			for (Facture facture : factures) {
				factureIDs.add(facture.getFactureID());
			}
            if (factureIDs == null) {
                JOptionPane.showMessageDialog(null,
                        "Brak faktur!");
                return;
            }
			final int pickedFactureID = (int) JOptionPane.showInputDialog(null, "Wybierz fakture", "Pick Facture for", JOptionPane.PLAIN_MESSAGE, null, (Object[]) factureIDs.toArray(), factures.toArray());

			for (Facture facture : factures) {
				if (pickedFactureID == facture.getFactureID()) {
					pickedFacture = facture;
				}
			}

			factureDAO.delete(pickedFacture);

		} else if (type.equals("Product")) {
			ProductDAO productDAO = new ProductDAO();
			Product pickedProduct = prepareProduct();
            if(pickedProduct == null){
                JOptionPane.showMessageDialog(null, "Brak produktow!");
                return;
            }

			productDAO.delete(pickedProduct);
			_panel.getProductsList().reloadList();

		}
	}

	private void modifyPrice(){

		ProductDAO productDAO = new ProductDAO();
		String option = "";

		Object[] choose = {"Znizka", "Podwyzka"};
		int n = JOptionPane.showOptionDialog(null,
				"Wybierz opcje", "Produkty",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choose, choose[1]);
		if (n == 0) {
			option = "Znizka";
		} else if (n == 1) {
			option = "Podwyzka";
		}
		else
			return;

		final String input = JOptionPane.showInputDialog(null, "Podaj procentową zmianę");
		if (input == null) {
			return;
		}

		String args[] = new String[3];
		args[0] = "modify";
		args[1] = option;
		args[2] = input;

		productDAO.update(null,args);
		_panel.getProductsList().reloadList();


	}

	private void selectAll() {
		SelectFrame _selectFrame = new SelectFrame();
		JTextArea[] textAreas = _selectFrame.getTextAreas();
		String table;
		Object[] options = {"Klienci", "Produkty"};
		int k = JOptionPane.showOptionDialog(null,
				"Wybierz jaka tabele chcesz wyswietlic", "Zasoby",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (k == 0) {
			table = "klienci";
		} else if (k == 1) {
			table = "produkty";
		}
		else
			return;


		if (table.equals("produkty")) {
			ProductDAO productDAO = new ProductDAO();
			List<Product> products = productDAO.getAll();
			_selectFrame.setTextArea("ID \n" ,textAreas[0]);
			_selectFrame.setTextArea("Nazwa \n" ,textAreas[1]);
			_selectFrame.setTextArea("Cena \n" ,textAreas[2]);
			_selectFrame.setTextArea("Ilosc \n" ,textAreas[3]);

			for (Product product : products) {

				_selectFrame.setTextArea(product.get_productID()+"\n" ,textAreas[0]);
				_selectFrame.setTextArea( product.getNameOfProduct()+"\n" ,textAreas[1]);
				_selectFrame.setTextArea(product.getPriceOfProduct()+"\n" ,textAreas[2]);
				_selectFrame.setTextArea(product.get_stockQuantity()+"\n" ,textAreas[3]);

			}
			_selectFrame.setVisible(true);


		} else if(table.equals("klienci")) {
			ClientDAO clientDAO = new ClientDAO();
			List<Client> clients = clientDAO.getAll();
			_selectFrame.setTextArea("ID \n" ,textAreas[0]);
			_selectFrame.setTextArea("Imie \n" ,textAreas[1]);
			_selectFrame.setTextArea("Nazwisko \n" ,textAreas[2]);
			_selectFrame.setTextArea("Pesel \n" ,textAreas[3]);
			_selectFrame.setTextArea("Adres \n" ,textAreas[4]);
			_selectFrame.setTextArea("Kod pocztowy \n" ,textAreas[5]);
			_selectFrame.setTextArea("Miasto \n" ,textAreas[6]);
			_selectFrame.setTextArea("NIP \n" ,textAreas[7]);

			for (Client client : clients) {

				_selectFrame.setTextArea(client.get_clientID()+"\n" ,textAreas[0]);
				_selectFrame.setTextArea(client.getClientName()+"\n" ,textAreas[1]);
				_selectFrame.setTextArea(client.getClientSurname() +"\n" ,textAreas[2]);
				_selectFrame.setTextArea(client.get_pesel()+"\n" ,textAreas[3]);
				_selectFrame.setTextArea(client.getClientAdress()+"\n" ,textAreas[4]);
				_selectFrame.setTextArea(client.get_postCode()+" \n" ,textAreas[5]);
				_selectFrame.setTextArea(client.get_city()+"\n" ,textAreas[6]);
				_selectFrame.setTextArea(client.getClientNIP()+"\n" ,textAreas[7]);

			}
			_selectFrame.setVisible(true);

		}

	}


	private void doBackup(){

		Object[] choose = {"Backup", "Restore"};

		int n = JOptionPane.showOptionDialog(null,
				"Wybierz opcje", "Baza Danych",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choose, choose[1]);

		if (n == 0) {
			DBBackup.Backupdbtosql();
		} else if (n == 1) {
			DBBackup.Restoredbfromsql("backup.sql");
			_panel.getProductsList().reloadList();
			_panel.getClientList().reloadClientList();
		}



	}

}
