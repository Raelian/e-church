package e.churchagenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class InterfataListaEvent extends JPanel implements ActionListener{
    JPanel bottomPanel;
    JLabel titluListEvent;
    JTextField ziuaListaField;
    JTextArea textList;
    JScrollPane scrollTextList;
    JButton showListBtn, backBtn;
    CardLayout cardLayout;
    AgendaEvenimente agenda;
    JPanel cardDeck;
    
    InterfataListaEvent(JPanel cardDeck, CardLayout cardLayout, AgendaEvenimente agenda){
        this.agenda = agenda;
        this.cardDeck = cardDeck;
        this.cardLayout = cardLayout;
        
        titluListEvent = new JLabel("Lista eveniment", SwingConstants.CENTER);
        titluListEvent.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titluListEvent.setBorder(BorderFactory.createEmptyBorder(50, 100, 30, 100));
        
        ziuaListaField = new JTextField("24/02/2023");
        ziuaListaField.setPreferredSize(new Dimension(150, 30));
        
        textList = new JTextArea();
        textList.setFont(new Font("Times New Roman", Font.BOLD, 25));
        textList.setEditable(false);
        scrollTextList = new JScrollPane(textList);
        scrollTextList.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));
        
        showListBtn = new JButton("Arata servicii");
        showListBtn.setPreferredSize(new Dimension(200, 50));
        showListBtn.addActionListener(this);
        
        backBtn = new JButton("Inapoi la menu");
        backBtn.setPreferredSize(new Dimension(200, 50));
        backBtn.addActionListener(this);
        
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 100));
        bottomPanel.add(ziuaListaField);
        bottomPanel.add(showListBtn);
        bottomPanel.add(backBtn);
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());
        this.add(titluListEvent, BorderLayout.NORTH);
        this.add(scrollTextList, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backBtn){
            cardLayout.show(cardDeck, "menu");
        }else if(e.getSource() == showListBtn){
            String data = ziuaListaField.getText();
            Boolean check = data.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
            if(check){
                try{
                    data = ziuaListaField.getText();
                    String[] splitData = data.split("/");
                    String message = agenda.getEventList(splitData[0], splitData[1], splitData[2]);
                    textList.append(message);
                }catch(Exception ex) {
                    Logger.getLogger(InterfataPlanEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else{
                JOptionPane.showMessageDialog(null, "Va rog folositi formatul corect: dd/mm/yyy");
            }  
        }
    }
}
