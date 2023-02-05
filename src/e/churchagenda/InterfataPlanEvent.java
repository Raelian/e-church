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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

class InterfataPlanEvent extends JPanel implements ActionListener, FocusListener{
    JLabel titluPlanEvent, rightLabel;
    PlanEventFieldText eventField, ziuaField, oraField, adresaField, telField, nameField;
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
        
        eventField = new PlanEventFieldText("Eveniment");
        eventField.addFocusListener(this);
        ziuaField = new PlanEventFieldText("dd/MM/yyyy");
        ziuaField.addFocusListener(this);
        oraField = new PlanEventFieldText("hh:mm");
        oraField.addFocusListener(this);
        adresaField = new PlanEventFieldText("Adresa");
        adresaField.addFocusListener(this);
        telField = new PlanEventFieldText("Telefon");
        telField.addFocusListener(this);
        nameField = new PlanEventFieldText("Nume Prenume");
        nameField.addFocusListener(this);
        
        leftPanel = new JPanel(new GridLayout(6, 1, 50, 30));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 100));
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
    
    public void addPlaceholder(JTextField txtField){
        Font font = txtField.getFont();
        font = font.deriveFont(Font.ITALIC);
        txtField.setFont(font);
        txtField.setForeground(Color.gray);
    }
    
    public void removePlaceholder(JTextField txtField){
        Font font = txtField.getFont();
        font = font.deriveFont(Font.PLAIN);
        txtField.setFont(font);
        txtField.setForeground(Color.black);
    }
    
    @Override
    public void focusGained(FocusEvent e){
        if(e.getSource() == eventField && eventField.getText().equals("Eveniment")){
            eventField.setText(null);
            removePlaceholder(eventField);         
        }else if(e.getSource() == ziuaField && ziuaField.getText().equals("dd/MM/yyyy")){
            ziuaField.setText(null);
            removePlaceholder(ziuaField);
        }else if(e.getSource() == oraField && oraField.getText().equals("hh:mm")){
            oraField.setText(null);
            removePlaceholder(oraField);
        }else if(e.getSource() == adresaField && adresaField.getText().equals("Adresa")){
            adresaField.setText(null);
            removePlaceholder(adresaField);
        }else if(e.getSource() == telField && telField.getText().equals("Telefon")){
            telField.setText(null);
            removePlaceholder(telField);
        }else if(e.getSource() == nameField && nameField.getText().equals("Nume Prenume")){
            nameField.setText(null);
            removePlaceholder(nameField);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == eventField && eventField.getText().length() == 0){
            eventField.setText("Eveniment");
            addPlaceholder(eventField);
        }else if(e.getSource() == ziuaField && ziuaField.getText().length() == 0){
            ziuaField.setText("dd/MM/yyyy");
            addPlaceholder(ziuaField);
        }else if(e.getSource() == oraField && oraField.getText().length() == 0){
            oraField.setText("hh:mm");
            addPlaceholder(oraField);
        }else if(e.getSource() == adresaField && adresaField.getText().length() == 0){
            adresaField.setText("Adresa");
            addPlaceholder(adresaField);
        }else if(e.getSource() == telField && telField.getText().length() == 0){
            telField.setText("Telefon");
            addPlaceholder(telField);
        }else if(e.getSource() == nameField && nameField.getText().length() == 0){
            nameField.setText("Nume Prenume");
            addPlaceholder(nameField);
        }
    }
}
