package FacturadeDB.Facturade.UI;

import FacturadeDB.Database.DB_Management.DBController;
import FacturadeDB.Facturade.Client.client;
import FacturadeDB.Facturade.Client.clientList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class addClientFrame{
	private DBController _controller = new DBController();
	addClientFrame(final clientList clientList){
		final JTextField clientNameF = new JTextField();
		final JTextField clientSurnameF = new JTextField();
		final JTextField adressF = new JTextField();
		final JTextField clientPeselF = new JTextField();
		final JTextField clientPostCodeF = new JTextField();
		final JTextField clientCityF = new JTextField();
		final JTextField clientNIPF = new JTextField();
		
		final Object[] inputs = {
				"Name :", clientNameF, "Surname :", clientSurnameF, "Pesel :", clientPeselF, "Adress", adressF,"Post Code :",clientPostCodeF,"City :",clientCityF, "NIP :", clientNIPF
		};
		
		JOptionPane.showConfirmDialog(null, inputs,"New CLient",JOptionPane.OK_CANCEL_OPTION);
		
		if(clientNameF.getText().equals("") || clientSurnameF.getText().equals("") || adressF.getText().equals("") || clientNIPF.getText().equals("") || clientPostCodeF.getText().equals("")|| clientPeselF.getText().equals("") || clientCityF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Nie wypelniles wszystkich pol!");
		}
		else {
			try {
				Integer.parseInt(clientNameF.getText());
				Integer.parseInt(clientSurnameF.getText());
				JOptionPane.showMessageDialog(null, "Wprowadziles bledne dane!");
			}
			catch(NumberFormatException ex) {
				try {
					if(clientNIPF.getText().equals("-")) {
						//_controller.create(clientNameF.getText(),clientSurnameF.getText(),Integer.parseInt(clientPeselF.getText()),adressF.getText(),clientPostCodeF.getText(),clientCityF.getText(),clientNIPF.getText());
					}
					else {
						Integer.parseInt(clientNIPF.getText());
						//_controller.create(clientNameF.getText(),clientSurnameF.getText(),Integer.parseInt(clientPeselF.getText()),adressF.getText(),clientPostCodeF.getText(),clientCityF.getText(),clientNIPF.getText());
					}
				}
				catch(NumberFormatException ex2) {
					JOptionPane.showMessageDialog(null, "Wprowadziles bledny NIP!");
				}
			}
		}
	}
	
	private void addNewClient(final String clientName, final String clientSurname,final int pesel, final String adress,final String postCode,final String city,final String clientNIP, final clientList _clientList) {
		_clientList.addClientToList(new client(clientName,clientSurname,pesel,adress,postCode,city,clientNIP));
	}
}
