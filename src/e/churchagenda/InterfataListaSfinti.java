package e.churchagenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class InterfataListaSfinti extends JPanel implements ActionListener{
    JLabel titluListSfinti;
    JButton backBtn, showSfinti;
    JTextArea textSfinti;
    JScrollPane scrollTextSfinti;
    CardLayout cardLayout;
    AgendaEvenimente agenda;
    JPanel cardDeck, bottomPanel;
    
    
    InterfataListaSfinti(JPanel cardDeck, CardLayout cardLayout, AgendaEvenimente agenda){
        this.agenda = agenda;
        this.cardDeck = cardDeck;
        this.cardLayout = cardLayout;
        
        titluListSfinti = new JLabel("Lista Sfinti in ziua curenta", SwingConstants.CENTER);
        titluListSfinti.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titluListSfinti.setBorder(BorderFactory.createEmptyBorder(50, 100, 30, 100));
        
        showSfinti = new JButton("Arata Sfinti");
        showSfinti.setPreferredSize(new Dimension(150, 50));
        showSfinti.addActionListener(this);
        
        backBtn = new JButton("Inapoi la menu");
        backBtn.setPreferredSize(new Dimension(150, 50));
        backBtn.addActionListener(this);
        
        textSfinti = new JTextArea();
        textSfinti.setFont(new Font("Times New Roman", Font.BOLD, 25));
        textSfinti.setEditable(false);
        
        scrollTextSfinti = new JScrollPane(textSfinti);
        scrollTextSfinti.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));
        
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 100));
        bottomPanel.add(showSfinti);
        bottomPanel.add(backBtn);
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());
        this.add(titluListSfinti, BorderLayout.NORTH);
        this.add(scrollTextSfinti, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backBtn){
            cardLayout.show(cardDeck, "menu");
        }else if(e.getSource() == showSfinti){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            String data = dtf.format(now);
            
            Boolean check = data.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
            if(check){
                try{
                    String[] splitData = data.split("/");
                    String message = agenda.getSfintiList(splitData[0], splitData[1], splitData[2]);
                    textSfinti.append(message);
                }catch(Exception ex) {
                    Logger.getLogger(InterfataPlanEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
    }
}
