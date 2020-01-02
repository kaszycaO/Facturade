package FacturadeDB.Facturade.Client;

import FacturadeDB.Database.DB_Management.ClientDAO;

import java.awt.Choice;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ClientChoiceList extends Choice {
	
	private static final long serialVersionUID = -9204974643877220484L;
	private static List<Client> listOfClients;

	public void addClientToList(Client _client) {
		add("[" + _client.get_clientID() + "] " + _client.getClientName() + " " + _client.getClientSurname() +  "  NIP: " + _client.getClientNIP());
		listOfClients.add(_client);
	}
	
	public List<Client> getListOfClients() {
		return listOfClients;
	}
	
	public ClientChoiceList(){
		super();
		ClientDAO clientDAO = new ClientDAO();
		listOfClients = clientDAO.getAll();
		for(Client client : listOfClients){
			add("[" + client.get_clientID() + "] " + client.getClientName() + " " + client.getClientSurname() +  "  NIP: " + client.getClientNIP());
		}
	}
	
	public Client getPickedClient() {
		return listOfClients.get(this.getSelectedIndex());
	}
	
	/*public static boolean checkIfClientExists(final Client _client) {
		for(Client client : listOfClients) {
			if (client.getClientNIP().equals(_client.getClientNIP()) && !_client.getClientNIP().equals("-")) {
				return true;
			}
		}
		return false;
	}*/

	public void reloadClientList(){
		this.removeAll();
		listOfClients = new ArrayList<>();
		listOfClients = ClientDAO.getClients();
		for(Client client : listOfClients){
			add("[" + client.get_clientID() + "] " + client.getClientName() + " " + client.getClientSurname() +  "  NIP: " + client.getClientNIP());
		}
	}
}

