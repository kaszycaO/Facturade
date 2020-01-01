package FacturadeDB.Facturade.UI;

import FacturadeDB.Database.DB_Management.ClientDAO;
import FacturadeDB.Facturade.Client.Client;
import FacturadeDB.Facturade.Client.ClientChoiceList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddClientFrame {
	AddClientFrame(final ClientChoiceList clientList){
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
					addNewClient(clientNameF, clientSurnameF, adressF, clientPeselF, clientPostCodeF, clientCityF, clientNIPF);
				}
				catch(NumberFormatException ex2) {
					JOptionPane.showMessageDialog(null, "Wprowadziles bledny NIP!");
				}
			}
		}
	}

	private void addNewClient(JTextField clientNameF, JTextField clientSurnameF, JTextField adressF, JTextField clientPeselF, JTextField clientPostCodeF, JTextField clientCityF, JTextField clientNIPF) {
		ClientDAO clientDao = new ClientDAO();
		if(clientNIPF.getText().equals("-")) {
			Client tester = new Client(clientNameF.getText(),clientSurnameF.getText(),Integer.parseInt(clientPeselF.getText()),adressF.getText(),clientPostCodeF.getText(),clientCityF.getText(),null);
			clientDao.save(tester);
		}
		else {
			Client tester = new Client(clientNameF.getText(),clientSurnameF.getText(),Integer.parseInt(clientPeselF.getText()),adressF.getText(),clientPostCodeF.getText(),clientCityF.getText(),clientNIPF.getText());
			clientDao.save(tester);
		}
	}

}
