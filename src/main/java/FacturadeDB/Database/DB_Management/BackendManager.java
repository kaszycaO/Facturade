package FacturadeDB.Database.DB_Management;


public class BackendManager {
	
	
	private QueryParser _qParser;
	private ResponseParser _rParser;
	private DatabaseConnector _connector;
	
	BackendManager(){
		
		
		_rParser = new  ResponseParser();
		_qParser = new QueryParser();
		_connector = new DatabaseConnector();
		
	}

}
