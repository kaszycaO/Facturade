package FacturadeDB.Facturade.UI;

import FacturadeDB.Database.DB_Management.HibernateFactory;
import FacturadeDB.Facturade.Client.ClientChoiceList;
import FacturadeDB.Facturade.Factures.FactureCreator;
import FacturadeDB.Facturade.Product.Product;
import FacturadeDB.Facturade.Product.ProductsChoiceList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**Klasa glownego okna aplikacji*/

class MainPanel extends JPanel implements ActionListener{

    private MainFacturadeFrame _frame;
    private ProductsChoiceList prodList;
    private JButton _createFacBtn;
    private JButton _addProdToFacBtn;
    private JButton _addProdToListBtn;
    private JButton _addClientBtn;
    private JButton _showFacturesBtn;
    private JButton _saveFacBtn;
    private JButton _deleteClientBtn;
    private JButton _deleteInvoiceBtn;
    private JButton _deleteProductBtn;
    private JButton _updateQuantityBtn;
    private JButton _selectButton;
    private JLabel _productLabel;
    private JLabel _clientLabel;
    private ButtonController _btnController;
    private JPanel _printArea;

    private Product _pickedProduct;
    private ClientChoiceList _clientList;
    private FactureCreator _facCreator;


    MainPanel(final MainFacturadeFrame frame) {
        super();
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(800,1080));

        _frame = frame;

        _productLabel = new JLabel();
        _clientLabel = new JLabel();
        prodList = new ProductsChoiceList();
        _clientList = new ClientChoiceList();
        _btnController = new ButtonController(this);
        _facCreator = new FactureCreator(_frame.get_invoicePanel());

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
        _deleteClientBtn = new JButton("Usun klienta");
        _deleteClientBtn.addActionListener(this);
        _deleteInvoiceBtn = new JButton("Usun fakture");
        _deleteInvoiceBtn.addActionListener(this);
        _deleteProductBtn = new JButton("Usun produkt");
        _deleteProductBtn.addActionListener(this);
        _updateQuantityBtn = new JButton("Dostawa");
        _updateQuantityBtn.addActionListener(this);
        _selectButton = new JButton("Wyswietl tabele");
        _selectButton.addActionListener(this);


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
        if(!HibernateFactory.username.equals("Pracownik")){
            add(_addProdToListBtn,c);
        }

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
        c.weighty = 0;
        c.insets = new Insets(5,20,0,20);
        if(!HibernateFactory.username.equals("Pracownik"))
            add(_updateQuantityBtn,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0.1;
        c.weighty = 0;
        c.insets = new Insets(5,20,0,20);
        if(!HibernateFactory.username.equals("Pracownik"))
            add(_deleteProductBtn,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.insets = new Insets(5,20,5,20);
        add(prodList,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.insets = new Insets(10,310,10,20);
        _clientLabel.setFont(new Font("SansSerif",Font.BOLD,19));
        _clientLabel.setText("Opcje zwiazane z klientem :");
        add(_clientLabel,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        c.weightx = 0.1;
        c.weighty = 0;
        c.insets = new Insets(5,20,0,20);
        add(_addClientBtn,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 8;
        c.weightx = 0.1;
        c.weighty = 0;
        c.insets = new Insets(5,20,0,20);
        add(_createFacBtn,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 9;
        c.weightx = 0.1;
        c.weighty = 0;
        c.insets = new Insets(5,20,0,20);
        add(_saveFacBtn,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 10;
        c.weightx = 0.1;
        c.weighty = 0;
        c.insets = new Insets(5,20,0,20);
        add(_showFacturesBtn,c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 11;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.insets = new Insets(5,20,0,20);
        add(_clientList,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 12;
        c.weightx = 0.1;
        c.weighty = 0;
        c.insets = new Insets(5,20,0,20);
        if(!HibernateFactory.username.equals("Pracownik"))
            add(_deleteClientBtn,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 13;
        c.weightx = 0.1;
        c.weighty = 0;
        c.insets = new Insets(5,20,0,20);
        if(!HibernateFactory.username.equals("Pracownik"))
             add(_deleteInvoiceBtn,c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 14;
        c.weightx = 0.1;
        c.weighty = 0.3;
        c.insets = new Insets(5,20,0,20);
        add(_selectButton,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 15;
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
            _btnController.doCertainAction("Save Facture");
        }
        else if(event.getSource() == this._addProdToFacBtn) {
            _btnController.doCertainAction("Add Product to Facture");
        }
        else if(event.getSource() == this._addProdToListBtn) {
            _btnController.doCertainAction("Add Product to list");
        }
        else if(event.getSource() == this._addClientBtn) {
            _btnController.doCertainAction("Add new Client");
        }
        else if(event.getSource() == this._showFacturesBtn) {
            _btnController.doCertainAction("Show factures");
        }
        else if(event.getSource() == this._createFacBtn) {
            _btnController.doCertainAction("Create Facture");
        }
        else if(event.getSource() == this._deleteClientBtn) {
            _btnController.doCertainAction("Delete Client");
        }
        else if(event.getSource() == this._deleteInvoiceBtn) {
            _btnController.doCertainAction("Delete Invoice");
        }
        else if(event.getSource() == this._deleteProductBtn) {
            _btnController.doCertainAction("Delete Product");
        }
        else if(event.getSource() == this._updateQuantityBtn) {
            _btnController.doCertainAction("Do Delivery");
        }
        else if(event.getSource() == this._selectButton) {
            _btnController.doCertainAction("Show");
        }



    }

    public Product getPickedProduct() {
        return _pickedProduct;
    }

    public ClientChoiceList getClientList() {
        return this._clientList;
    }

    public FactureCreator getFacCreator() {
        return _facCreator;
    }

    public ProductsChoiceList getProductsList() {
        return prodList;
    }

    public MainFacturadeFrame getFrame() {
        return _frame;
    }
}
