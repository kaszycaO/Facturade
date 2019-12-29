package FacturadeDB.Facturade.UI;

import FacturadeDB.Facturade.Client.clientList;
import FacturadeDB.Facturade.Factures.factureCreator;
import FacturadeDB.Facturade.Product.product;
import FacturadeDB.Facturade.Product.productsList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**Klasa glownego okna aplikacji*/

class AppPanel extends JPanel implements ActionListener{
	
	private AppWindow _frame;
	private productsList prodList;
    private JButton _createFacBtn;
    private JButton _addProdToFacBtn;
    private JButton _addProdToListBtn;
    private JButton _addClientBtn;
    private JButton _showFacturesBtn;
    private JButton _saveFacBtn;
    private JLabel _productLabel;
    private JLabel _clientLabel;
    private buttonController _btnController;
    
    private product _pickedProduct;
    private clientList _clientList;
    private factureCreator _facCreator;
    
	
    AppPanel(final AppWindow frame) {
    	super();
		setLayout(new GridBagLayout());	
		_frame = frame;

		_productLabel = new JLabel();
		_clientLabel = new JLabel();
		prodList = new productsList();
		_clientList = new clientList();
		_btnController = new buttonController(this);
		_facCreator = new factureCreator(_frame.getPrintArea());
		
		_saveFacBtn = new JButton("Zapisz fakture");
		_saveFacBtn.addActionListener(this);
		_addProdToFacBtn = new JButton("Dodaj produkt do faktury");
		_addProdToFacBtn.addActionListener(this);
		_addProdToListBtn = new JButton("Dodaj nowy produkt");
		_addProdToListBtn.addActionListener(this);
		_addClientBtn = new JButton("Dodaj nowego clienta");
		_addClientBtn.addActionListener(this);
		_showFacturesBtn = new JButton("Pokaz faktury dla wybranego uzytkownika");
		_showFacturesBtn.addActionListener(this);
		_createFacBtn = new JButton("Stworz nowa fakture dla wybranego uzytkownika");
		_createFacBtn.addActionListener(this);
		
		
  	  
		final GridBagConstraints c = new GridBagConstraints();
  	    c.anchor = GridBagConstraints.FIRST_LINE_START;
	  
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.weightx = 0.1;
	    c.weighty = 0;
	    c.insets = new Insets(20,310,20,20);
	    _productLabel.setFont(new Font("SansSerif",Font.BOLD,19));
	    _productLabel.setText("Opcje zwiazane z produktami :");
	    add(_productLabel,c);
	  
	   
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.weightx = 0.1;
	    c.weighty = 0;
	    c.insets = new Insets(20,20,5,20);
	    add(_addProdToListBtn,c);
	  
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 2;
	    c.weightx = 0.1;
 	    c.weighty = 0;
	    c.insets = new Insets(0,20,0,20);
	    add(_addProdToFacBtn,c);
	  
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 3;
	    c.weightx = 0.1;
	    c.weighty = 0.1;
	    c.insets = new Insets(5,20,5,20);
	    add(prodList,c);
	  
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 4;
	    c.weightx = 0.1;
	    c.weighty = 0;
	    c.insets = new Insets(20,310,20,20);
	    _clientLabel.setFont(new Font("SansSerif",Font.BOLD,19));
	    _clientLabel.setText("Opcje zwiazane z klientem :");
	    add(_clientLabel,c);
	  
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 5;
	    c.weightx = 0.1;
	    c.weighty = 0;
	    c.insets = new Insets(5,20,0,20);
	    add(_addClientBtn,c);
	   
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 6;
	    c.weightx = 0.1;
	    c.weighty = 0;
	    c.insets = new Insets(5,20,0,20);
	    add(_createFacBtn,c);
	  
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 7;
	    c.weightx = 0.1;
	    c.weighty = 0;
	    c.insets = new Insets(5,20,0,20);
	    add(_saveFacBtn,c);
	  
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 8;
	    c.weightx = 0.1;
	    c.weighty = 0;
	    c.insets = new Insets(5,20,0,20);
	    add(_showFacturesBtn,c);

	  
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 9;
	    c.weightx = 0.1;
	    c.weighty = 0.5;
	    c.insets = new Insets(5,20,0,20);
	    add(_clientList,c);
	  
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 10;
	    c.weightx = 0.1;
	    c.weighty = 0.4;
	    c.insets = new Insets(5,360,0,20);
	    final JLabel programNameL = new JLabel();
	    programNameL.setFont(new Font("SansSerif",Font.BOLD,50));
	    programNameL.setForeground(Color.BLUE);
	    programNameL.setText("Facturade");
	    add(programNameL,c);
	  
	    _frame.add(this);
	  
     }
    
	@Override
	public void actionPerformed(final ActionEvent event) {
		if(event.getSource() == this._saveFacBtn) {
			_btnController.doCertainAction("Save facture");
		}
		else if(event.getSource() == this._addProdToFacBtn) {
			_btnController.doCertainAction("Add product to facture");
		}
		else if(event.getSource() == this._addProdToListBtn) {
			_btnController.doCertainAction("Add product to list");
		}
		else if(event.getSource() == this._addClientBtn) {
			_btnController.doCertainAction("Add new client");
		}
		else if(event.getSource() == this._showFacturesBtn) {
			_btnController.doCertainAction("Show factures");
		}
		else if(event.getSource() == this._createFacBtn) {
			_btnController.doCertainAction("Create facture");
		}
		
	}
	
	  public product getPickedProduct() {
		  return _pickedProduct;
	  }
	    
	  public clientList getClientList() {
		  return _clientList;
	  }
	   
	  public factureCreator getFacCreator() {
		  return _facCreator;
	  }
	  
	  public productsList getProductsList() {
		  return prodList;
	  }
	  
	  public AppWindow getFrame() {
		  return _frame;
	  }
}

public class AppWindow extends JFrame {

  private final JTextArea _printLabel;
  private AppPanel _panel;
  private final long serialVersionUID = 1L;
  
  public JTextArea getPrintArea() {
	  return _printLabel;
  }
  
  public AppWindow() {
	  super();
	  setSize(new Dimension(1920,1080));
	  setLayout(new GridLayout(1,2));
	  _printLabel = new JTextArea();
	  _printLabel.setEditable(false);
  	  _panel = new AppPanel(this);

	  add(_printLabel);
	  setVisible(true);
  }
  
}
