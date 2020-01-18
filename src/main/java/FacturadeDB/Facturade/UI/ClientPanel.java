package FacturadeDB.Facturade.UI;

import javax.swing.*;
import java.awt.*;

public class ClientPanel extends JPanel {

    /**
     * Wyświetla dane klienta
     */
    private JTextArea _clientInfo;

    /**
     * Wyświetla dane właściciela
     */
    private JTextArea _ownerInfo;

    private final String owner;
    private final static String newline = "\n";

    ClientPanel() {


        setLayout(new GridLayout());
        setPreferredSize(new Dimension(920,200));

        owner = "Sprzedający: \n\n"+ "Oliwier\nKaszyca\nul. Reja 11\nWrocław\n1234567890";
        _clientInfo = new JTextArea();
        _clientInfo.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 16));

        _ownerInfo = new JTextArea(owner);
        _ownerInfo.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 16));

        _clientInfo.setEditable(false);
        _ownerInfo.setEditable(false);


        add(_ownerInfo,BorderLayout.WEST);
        add(_clientInfo,BorderLayout.EAST);

    }
    public void clearAreas(){
        _clientInfo.setText("");
    }

    public void setClientText(String statement){
        _clientInfo.setText("");
        _clientInfo.append(statement);
        _clientInfo.append(newline);
    }
}
