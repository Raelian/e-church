package e.churchagenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class InterfataPlanEvent extends JPanel implements ActionListener{
    JLabel titluPlanEvent, rightLabel;
    JTextField eventField, ziuaField, oraField, adresaField, telField, nameField;
    JPanel leftPanel, bottomPanel, cardDeck;
    JButton savePlanBtn, backBtn;
    BufferedImage img;
    Image dimg;
    ImageIcon bigCross;
    CardLayout cardLayout;
    AgendaEvenimente agenda;
    
    InterfataPlanEvent(JPanel cardDeck, CardLayout cardLayout, AgendaEvenimente agenda){
        this.agenda = agenda;
        this.cardDeck = cardDeck;
        this.cardLayout = cardLayout;
        
        titluPlanEvent = new JLabel("Planificare eveniment", SwingConstants.CENTER);
        titluPlanEvent.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titluPlanEvent.setBorder(BorderFactory.createEmptyBorder(50, 100, 30, 100));
        
        eventField = new JTextField("Nunta");
        ziuaField = new JTextField("24/02/1989");
        oraField = new JTextField("14:00");
        adresaField = new JTextField("Mugurului 15a");
        telField = new JTextField("0721985675");
        nameField = new JTextField("Mihai Emil");
        
        eventField.setPreferredSize(new Dimension(250, 30));
        ziuaField.setPreferredSize(new Dimension(250, 30));
        oraField.setPreferredSize(new Dimension(250, 30));
        adresaField.setPreferredSize(new Dimension(250, 30));
        telField.setPreferredSize(new Dimension(250, 30));
        nameField.setPreferredSize(new Dimension(250, 30));
        
        leftPanel = new JPanel(new GridLayout(6, 1, 50, 30));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 50, 100));
        leftPanel.add(eventField);
        leftPanel.add(ziuaField);
        leftPanel.add(oraField);
        leftPanel.add(adresaField);
        leftPanel.add(telField);
        leftPanel.add(nameField);
        
        savePlanBtn = new JButton("Salveaza eveniment");
        savePlanBtn.setPreferredSize(new Dimension(200, 50));
        savePlanBtn.addActionListener(this);
        
        backBtn = new JButton("Inapoi la menu");
        backBtn.setPreferredSize(new Dimension(200, 50));
        backBtn.addActionListener(this);
        
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 100));
        bottomPanel.add(savePlanBtn);
        bottomPanel.add(backBtn);
        
        rightLabel = new JLabel();
        
        img = null;
        try{
            img = ImageIO.read(new File("C:\\agenda\\evenimente\\images\\cross.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
     
        dimg = img.getScaledInstance(300, 450, Image.SCALE_SMOOTH);
        
        bigCross = new ImageIcon(dimg);
        rightLabel.setIcon(bigCross);
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());
        this.add(titluPlanEvent, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(rightLabel, BorderLayout.EAST);
        this.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backBtn){
            cardLayout.show(cardDeck, "menu");
        }
        else if( e.getSource() == savePlanBtn){
            try {
                String event = eventField.getText();
                String data = ziuaField.getText();
                Boolean checkData = data.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
                String ora = oraField.getText();
                Boolean checkOra = ora.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");
                String strada = adresaField.getText();
                String tel = telField.getText();
                Boolean checkTel = tel.matches("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
                String nume = nameField.getText();
                String[] month;
                month = data.split("/");
                
                if(!checkOra){
                    JOptionPane.showMessageDialog(null, "Introduceti ora corecta!");
                }else if(!checkData){
                    JOptionPane.showMessageDialog(null, "Introduceti data corecta!");
                }else if(!checkTel){
                    JOptionPane.showMessageDialog(null, "Introduceti un numar de telefon corect!");
                }else{
                    agenda.getLuna(month[1]).getZiua(month[0]).setEventZiua(event, data, ora, strada, tel, nume);
                    JOptionPane.showMessageDialog(null, "Informatiile au fost salvate!");
                }
            } catch (Exception ex) {
                Logger.getLogger(InterfataPlanEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
    }
}
