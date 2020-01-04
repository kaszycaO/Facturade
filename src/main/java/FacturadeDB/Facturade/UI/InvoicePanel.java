package FacturadeDB.Facturade.UI;

import javax.swing.*;
import java.awt.*;

public class InvoicePanel extends JPanel {

    private JTextArea _drawInvoiceLP;
    private JTextArea _drawInvoiceName;
    private JTextArea _drawInvoiceQuantity;
    private JTextArea _drawInvoicePrice;
    private JTextArea _drawInvoiceTotalPrice;
    private JTextArea _drawfinalPrice;
    private ClientPanel _clientPanel;

    InvoicePanel() {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1020,1080));

        _clientPanel = new ClientPanel();

        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(new GridLayout(1,5));

        _drawInvoiceLP = new JTextArea("LP \n\n");
        _drawInvoiceName = new JTextArea("Nazwa Produktu \n\n");
        _drawInvoiceQuantity = new JTextArea("Ilosc \n\n");
        _drawInvoicePrice = new JTextArea("Cena \n\n");
        _drawInvoiceTotalPrice = new JTextArea("Razem \n\n");

        setTextAreaProp(_drawInvoiceLP);
        setTextAreaProp(_drawInvoiceName);
        setTextAreaProp(_drawInvoiceQuantity);
        setTextAreaProp(_drawInvoicePrice);
        setTextAreaProp(_drawInvoiceTotalPrice);

        componentsPanel.add(_drawInvoiceLP);
        componentsPanel.add(_drawInvoiceName);
        componentsPanel.add(_drawInvoiceQuantity);
        componentsPanel.add(_drawInvoicePrice);
        componentsPanel.add(_drawInvoiceTotalPrice);

        JPanel finalPrice = new JPanel();
        finalPrice.setPreferredSize(new Dimension(1920,100));
        _drawfinalPrice = new JTextArea();
        _drawfinalPrice.setEditable(false);
        _drawfinalPrice.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 18));
        finalPrice.add(_drawfinalPrice);

        add(_clientPanel,BorderLayout.NORTH);
        add(componentsPanel,BorderLayout.CENTER);
        add(finalPrice,BorderLayout.PAGE_END);



    }

    private void addText(JTextArea textArea, String text){

            String currText = textArea.getText();
            textArea.setText("");
            currText += text;
            textArea.setText(currText);

    }

    public void clearTextAreas() {

        _drawInvoiceLP.setText("");
        _drawInvoiceName.setText("");
        _drawInvoiceQuantity.setText("");
        _drawInvoicePrice.setText("");
        _drawInvoiceTotalPrice.setText("");

        _drawInvoiceLP.setText("LP \n\n");
        _drawInvoiceName.setText("Nazwa Produktu \n\n");
        _drawInvoiceQuantity.setText("Ilosc \n\n");
        _drawInvoicePrice.setText("Cena \n\n");
        _drawInvoiceTotalPrice.setText("Razem \n\n");



    }

    public final void setTextAreaProp(JTextArea textArea) {
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 18));

    }

    public void setFinalPrice(String price){

        _drawfinalPrice.setText("");
        _drawfinalPrice.append("Razem:            "+price);

    }

    public void setInvoiceText(String LP, String name, String quantity, String price, String totalPrice) {

        addText(_drawInvoiceLP, LP + "\n");
        addText(_drawInvoiceName, name + "\n");
        addText(_drawInvoiceQuantity,quantity + "\n");
        addText(_drawInvoicePrice, price + "\n");
        addText(_drawInvoiceTotalPrice,totalPrice + "\n");

    }

    public ClientPanel get_clientPanel() {
        return _clientPanel;
    }
}
