package e.churchagenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class InterfataListaAnunt extends JPanel implements ActionListener{
    JLabel titluListAnunt;
    JButton backBtn, newAnuntBtn, modBtn, stergeBtn;
    JTextArea textAnunt;
    JScrollPane scrollTextAnunt;
    CardLayout cardLayout;
    AgendaEvenimente agenda;
    JPanel cardDeck, bottomPanel;
    
    InterfataListaAnunt(JPanel cardDeck, CardLayout cardLayout, AgendaEvenimente agenda){
        this.agenda = agenda;
        this.cardDeck = cardDeck;
        this.cardLayout = cardLayout;
        
        titluListAnunt = new JLabel("Lista anunturi", SwingConstants.CENTER);
        titluListAnunt.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titluListAnunt.setBorder(BorderFactory.createEmptyBorder(50, 100, 30, 100));
        
        backBtn = new JButton("Inapoi la menu");
        backBtn.setPreferredSize(new Dimension(150, 50));
        backBtn.addActionListener(this);
        
        newAnuntBtn = new JButton("Anunt nou");
        newAnuntBtn.setPreferredSize(new Dimension(150, 50));
        newAnuntBtn.addActionListener(this);
        
        modBtn = new JButton("Modifica anunt");
        modBtn.setPreferredSize(new Dimension(150, 50));
        modBtn.addActionListener(this);
        
        stergeBtn = new JButton("Sterge anunt");
        stergeBtn.setPreferredSize(new Dimension(150, 50));
        stergeBtn.addActionListener(this);
        
        textAnunt = new JTextArea();
        textAnunt.setFont(new Font("Times New Roman", Font.BOLD, 25));
        textAnunt.setEditable(false);
        
        scrollTextAnunt = new JScrollPane(textAnunt);
        scrollTextAnunt.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));
        
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 100));
        bottomPanel.add(newAnuntBtn);
        bottomPanel.add(modBtn);
        bottomPanel.add(stergeBtn);
        bottomPanel.add(backBtn);
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());
        this.add(titluListAnunt, BorderLayout.NORTH);
        this.add(scrollTextAnunt, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backBtn){
            cardLayout.show(cardDeck, "menu");
        }
    }
}
