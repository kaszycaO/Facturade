package FacturadeDB.Facturade.UI;

import javax.swing.*;
import java.awt.*;

public class SelectFrame extends JFrame {

   private JTextArea textArea;


    SelectFrame(){

        setSize(new Dimension(800,800));
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea,  ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 18));
        textArea.setEditable(false);



        add(scrollPane,BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);


    }


    public void setTextArea(String data){
        textArea.setText(textArea.getText()+data);
    }

    public void clearArea(){
        textArea.setText("");
    }

}
