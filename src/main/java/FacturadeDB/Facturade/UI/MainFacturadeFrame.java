package FacturadeDB.Facturade.UI;

import javax.swing.*;
import java.awt.*;

public class MainFacturadeFrame extends JFrame {

    private final JTextArea _printLabel;
    private MainPanel _panel;
    private final long serialVersionUID = 1L;

    public JTextArea getPrintArea() {
        return _printLabel;
    }

    public MainFacturadeFrame() {
        super();
        setSize(new Dimension(1920,1080));
        setLayout(new GridLayout(1,2));
        _printLabel = new JTextArea();
        _printLabel.setEditable(false);
        _panel = new MainPanel(this);

        add(_printLabel);
        setVisible(true);
    }

}