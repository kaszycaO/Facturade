package FacturadeDB.Facturade.Client;

import java.awt.Choice;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class clientList extends Choice {
	
	private static final long serialVersionUID = -9204974643877220484L;
	private static int _currentClientID = 1;
	private static List<client> listOfClients;

	public void addClientToList(client _client) {
		
		if(checkIfClientExists(_client)) {
			JOptionPane.showMessageDialog(null, "Taki klient juz zostal wprowadzony!" + " [" +  _client.getClientName() + " " + _client.getClientSurname() + "] NIP: " + _client.getClientNIP()); 
		}
		else { 
			listOfClients.add(_client);
			//_client.setClientID(_currentClientID);
			add("[" + _currentClientID + "] " + _client.getClientName() + " " + _client.getClientSurname() +  "  NIP: " + _client.getClientNIP());
			_currentClientID++;
		}
	}
	
	public List<client> getListOfCLients() {
		return listOfClients;
	}
	
	public clientList(){
		super();
		listOfClients = new ArrayList<>();
	}
	
	public client getPickedClient() {
		return listOfClients.get(this.getSelectedIndex());
	}
	
	public static boolean checkIfClientExists(client _client) {
		for(final client client:listOfClients) {
			if(client.getClientNIP().equals(_client.getClientNIP()) && !client.getClientNIP().equals("-")) {
				return true;
			}
		}
		
		return false;
	}
}

