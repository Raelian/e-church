package e.churchagenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class InterfataMenu extends JPanel implements ActionListener{
        MenuButtons menuBtn1, menuBtn2, menuBtn3, menuBtn4;
        JLabel titluMenu;
        JPanel panouBtn, cardDeck;
        CardLayout cardLayout;
    
    InterfataMenu(JPanel cardDeck, CardLayout cardLayout){
        this.cardDeck = cardDeck;
        this.cardLayout = cardLayout;
        
        menuBtn1 = new MenuButtons("Planificare eveniment");
        menuBtn2 = new MenuButtons("Lista evenimente");
        menuBtn3 = new MenuButtons("Anunturi");
        menuBtn4 = new MenuButtons("Afiseaza sfinti");
        
        menuBtn1.addActionListener(this);
        menuBtn2.addActionListener(this);
        menuBtn3.addActionListener(this);
        menuBtn4.addActionListener(this);

        titluMenu = new JLabel("E-Church Agenda", SwingConstants.CENTER);
        titluMenu.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titluMenu.setBorder(BorderFactory.createEmptyBorder(50, 100, 30, 100));
        
        panouBtn = new JPanel(new GridLayout(4,1, 20, 20));
        panouBtn.setBorder(BorderFactory.createEmptyBorder(30, 300, 50, 300));
        panouBtn.add(menuBtn1);
        panouBtn.add(menuBtn2);
        panouBtn.add(menuBtn3);
        panouBtn.add(menuBtn4);       
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());
        this.add(titluMenu, BorderLayout.NORTH);
        this.add(panouBtn, BorderLayout.CENTER);
        this.setVisible(false);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == menuBtn1)
            cardLayout.show(cardDeck, "eventPlan");
        else if( e.getSource() == menuBtn2)
            cardLayout.show(cardDeck, "eventList");
        else if(e.getSource() == menuBtn3)
            cardLayout.show(cardDeck, "anuntList");
        else if(e.getSource() == menuBtn4)
            cardLayout.show(cardDeck, "sfintiList");
    }
    
    public MenuButtons getBtn1(){
        return this.menuBtn1;
    }
    
    public MenuButtons getBtn2(){
        return this.menuBtn2;
    }
    
    public MenuButtons getBtn3(){
        return this.menuBtn3;
    }
    
    public MenuButtons getBtn4(){
        return this.menuBtn4;
    }
}
