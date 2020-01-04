package FacturadeDB.Facturade.UI;

import FacturadeDB.Facturade.Client.Client;

import javax.swing.*;
import java.awt.*;

public class MainFacturadeFrame extends JFrame {

    private final InvoicePanel _invoicePanel;
    private MainPanel _panel;

    private final long serialVersionUID = 1L;

    public MainFacturadeFrame() {
        super();
        setSize(new Dimension(1920,1080));
        setLayout(new BorderLayout());

        _invoicePanel = new InvoicePanel();
        _panel = new MainPanel(this);

        add(_panel, BorderLayout.WEST);
        add(_invoicePanel, BorderLayout.EAST);
        setVisible(true);
    }


    public InvoicePanel get_invoicePanel() {
        return _invoicePanel;
    }


}