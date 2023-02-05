package e.churchagenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class InterfataListaAnunt extends JPanel implements ActionListener, FocusListener{
    JLabel titluListAnunt;
    JButton showBtn, backBtn, newAnuntBtn, modBtn, stergeBtn;
    JScrollPane scrollTextAnunt;
    CardLayout cardLayout;
    AgendaEvenimente agenda;
    JPanel cardDeck, bottomPanel, centerPanel;
    JList list;
    DefaultListModel listModel;
    JTextField inputAnunt;
    
    InterfataListaAnunt(JPanel cardDeck, CardLayout cardLayout, AgendaEvenimente agenda){
        this.agenda = agenda;
        this.cardDeck = cardDeck;
        this.cardLayout = cardLayout;
        
        titluListAnunt = new JLabel("Lista anunturi", SwingConstants.CENTER);
        titluListAnunt.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titluListAnunt.setBorder(BorderFactory.createEmptyBorder(50, 100, 30, 100));
        
        showBtn = new JButton("Afiseaza");
        showBtn.setPreferredSize(new Dimension(100, 50));
        showBtn.addActionListener(this);
        
        backBtn = new JButton("Inapoi la menu");
        backBtn.setPreferredSize(new Dimension(120, 50));
        backBtn.addActionListener(this);
        
        newAnuntBtn = new JButton("Anunt nou");
        newAnuntBtn.setPreferredSize(new Dimension(100, 50));
        newAnuntBtn.addActionListener(this);
        
        modBtn = new JButton("Modifica anunt");
        modBtn.setPreferredSize(new Dimension(120, 50));
        modBtn.addActionListener(this);
        
        stergeBtn = new JButton("Sterge anunt");
        stergeBtn.setPreferredSize(new Dimension(120, 50));
        stergeBtn.addActionListener(this);
        
        listModel = new DefaultListModel();
        
        list = new JList(listModel);
        list.setFont(new Font("Times New Roman", Font.BOLD, 20));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(-1);
        
        scrollTextAnunt = new JScrollPane(list);
        scrollTextAnunt.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        inputAnunt = new JTextField("Introduceti anunt sau predica");
        inputAnunt.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        inputAnunt.setForeground(Color.gray);
        inputAnunt.setMaximumSize(new Dimension(800, 30));
        inputAnunt.addFocusListener(this);
        
        centerPanel = new JPanel(new GridLayout(2,1, 5, 5));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        centerPanel.add(scrollTextAnunt);
        centerPanel.add(inputAnunt);
        
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 100));
        bottomPanel.add(showBtn);
        bottomPanel.add(newAnuntBtn);
        bottomPanel.add(modBtn);
        bottomPanel.add(stergeBtn);
        bottomPanel.add(backBtn);
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());
        this.add(titluListAnunt, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
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
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backBtn){
            cardLayout.show(cardDeck, "menu");
            try {
                saveAnuntList();
            } catch (Exception ex) {
                Logger.getLogger(InterfataListaAnunt.class.getName()).log(Level.SEVERE, null, ex);
            }
            listModel.removeAllElements();
        }else if(e.getSource() == stergeBtn){
            int index = list.getSelectedIndex();
            try {
                saveAnuntList();
            } catch (Exception ex) {
                Logger.getLogger(InterfataListaAnunt.class.getName()).log(Level.SEVERE, null, ex);
            }
            listModel.remove(index);
        }else if(e.getSource() == modBtn && (inputAnunt.getText().length() != 0 || !inputAnunt.getText().equals("Introduceti anunt sau predica"))){
            int index = list.getSelectedIndex();
            String inputStr = inputAnunt.getText();
            listModel.remove(index);
            listModel.add(index, inputStr);
            inputAnunt.setText(null);
            try {
                saveAnuntList();
            } catch (Exception ex) {
                Logger.getLogger(InterfataListaAnunt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getSource() == newAnuntBtn && !inputAnunt.getText().equals("Introduceti anunt sau predica")){
            String inputStr = inputAnunt.getText();
            listModel.addElement(inputStr);
            inputAnunt.setText(null);
            try {
                saveAnuntList();
            } catch (Exception ex) {
                Logger.getLogger(InterfataListaAnunt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getSource() == showBtn){
            try {
                listModel.removeAllElements();
                String fileName = agenda.getAnuntList();
                BufferedReader in = new BufferedReader(new FileReader(fileName));
                
                String line;
                while((line = in.readLine()) != null){
                    listModel.addElement(line);
                }
                in.close();
            } catch (Exception ex) {
                Logger.getLogger(InterfataListaAnunt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void focusGained(FocusEvent e){
        if(e.getSource() == inputAnunt && inputAnunt.getText().equals("Introduceti anunt sau predica")){
            inputAnunt.setText(null);
            removePlaceholder(inputAnunt);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
       if(e.getSource() == inputAnunt && inputAnunt.getText().length() == 0){
           inputAnunt.setText("Introduceti anunt sau predica");
           addPlaceholder(inputAnunt);
       }
    }
    
    private void saveAnuntList() throws Exception{
        try{
            String fileName = agenda.getAnuntList();
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for(int i = 0; i < list.getModel().getSize(); i++){
                bw.write((String) list.getModel().getElementAt(i));
                bw.newLine();
            }
            bw.close();
        }catch(IOException ex){
            System.out.println("Error!");
        }
    }
}
