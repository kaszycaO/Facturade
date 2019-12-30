package FacturadeDB.Facturade.Client;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "klienci")
@Entity
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int _clientID;
	@Column(name = "imie")
	private String _clientName;
	@Column(name = "nazwisko")
	private String _clientSurname;
	@Column(name = "PESEL")
	private int _pesel;
	@Column(name = "NIP")
	private String _clientNIP;
	@Column(name = "adres")
	private String _adress;
	@Column(name = "kod_pocztowy")
	private String _postCode;
	@Column(name = "miasto")
	private String _city;

	public Client(){};
	
	public Client(String clientName, String clientSurname, int pesel, String adress, String postCode, String city, String clientNIP){
		_clientName = clientName;
		_clientSurname = clientSurname;
		_pesel = pesel;
		_adress = adress;
		_postCode = postCode;
		_city = city;
		_clientNIP = clientNIP;
		//_factureList = new ArrayList<>();
	}
	public int get_clientID(){
		return this._clientID;
	}

	public String getClientNIP() {
		return this._clientNIP;
	}
	
	public String getClientName() {
		return this._clientName;
	}
	
	public String getClientSurname() {
		return this._clientSurname;
	}
	
	public String getClientAdress() {
		return this._adress;
	}

	public String get_postCode() { return _postCode; }

	public String get_city() { return _city; }

	public int get_pesel() { return _pesel; }
}
