package e.churchagenda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;

public class PlanEventFieldText extends JTextField{
    PlanEventFieldText(String str){
        this.setText(str);
        this.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        this.setForeground(Color.gray);
        this.setPreferredSize(new Dimension(250, 30));
    }
}