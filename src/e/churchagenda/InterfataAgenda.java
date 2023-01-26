package e.churchagenda;

import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfataAgenda extends JFrame{
    InterfataMenu menu;
    InterfataPlanEvent planEvent;
    InterfataListaEvent listEvent;
    InterfataListaAnunt listAnunt;
    InterfataListaSfinti listSfinti;
    CardLayout cardLayout;
    JPanel cardDeck;
    ImageIcon icon;
    AgendaEvenimente agenda;
    
    InterfataAgenda(){
        agenda = new AgendaEvenimente();
        cardLayout = new CardLayout();
        cardDeck = new JPanel(cardLayout);
        
        menu = new InterfataMenu(cardDeck, cardLayout);
        planEvent = new InterfataPlanEvent(cardDeck, cardLayout, agenda);
        listEvent = new InterfataListaEvent(cardDeck, cardLayout, agenda);
        listAnunt = new InterfataListaAnunt(cardDeck, cardLayout, agenda);
        listSfinti = new InterfataListaSfinti(cardDeck, cardLayout, agenda);
        cardDeck.add(menu, "menu");
        cardDeck.add(planEvent, "eventPlan");
        cardDeck.add(listEvent, "eventList");
        cardDeck.add(listAnunt, "anuntList");
        cardDeck.add(listSfinti, "sfintiList");
        
        this.setTitle("E-Church Agenda");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(900, 700);
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.add(cardDeck);
        
        icon = new ImageIcon("C:\\evenimente\\images\\cross.png");
        this.setIconImage(icon.getImage());
    }
}
