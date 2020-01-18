package FacturadeDB.Facturade.UI;

import javax.swing.*;
import java.awt.*;

public class SelectFrame extends JFrame {



    private JPanel _componentPanel;
    private JTextArea[] _textAreas;



    SelectFrame(){

        setSize(new Dimension(800,800));
        setLayout(new BorderLayout());

        _componentPanel = new JPanel();
        _componentPanel.setLayout(new GridLayout(1,8));
        _textAreas = new JTextArea[8];
        setTextAreas();

        JScrollPane scrollPane = new JScrollPane(_componentPanel,  ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane,BorderLayout.CENTER);
        setResizable(false);
        setVisible(false);


    }


    private void setTextAreas(){
        for(int i = 0; i<8; i++){
            JTextArea textArea = new JTextArea();
            textArea.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 18));
            textArea.setEditable(false);
            _textAreas[i] = textArea;
            _componentPanel.add(_textAreas[i]);

        }
    }
    public void setTextArea(String data, JTextArea textArea){
        textArea.setText(textArea.getText()+data);
    }

    public JTextArea[] getTextAreas(){return _textAreas;}

}
