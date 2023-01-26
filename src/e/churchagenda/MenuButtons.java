package e.churchagenda;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

public class MenuButtons extends JButton{
    MenuButtons(String name){
        this.setPreferredSize(new Dimension(300, 100));
        this.setText(name);
        this.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.setFocusable(false);
    }
}
