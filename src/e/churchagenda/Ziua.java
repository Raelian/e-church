package e.churchagenda;

import java.io.FileWriter;
import java.io.PrintWriter;

class Ziua extends Luna{
    private final String fisierEvent;
    private final String fisierAnunt;
    private final String fisierSfinti;
   
    
    Ziua(String ziua, String luna){
        this.fisierEvent = "C:\\agenda\\evenimente\\" + luna.toLowerCase() + "\\" + ziua;
        this.fisierAnunt = "C:\\agenda\\anunturi\\" + luna.toLowerCase() + "\\" + ziua;
        this.fisierSfinti = "C:\\agenda\\sfinti\\" + luna.toLowerCase() + "\\" + ziua + ".txt";
    }
    
    public void setEventZiua(String eveniment, String data, String ora, String adresa, String tel, String numeEnorias) throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter(this.fisierEvent, true));
        
        out.println(eveniment + "-" + data + "-" + ora + "-" + adresa + "-" + tel + "-" + numeEnorias);
        out.close();  
    }
    
    public String getFile(){
        return this.fisierEvent;
    }
    
    public void setAnuntZiua(String anunt) throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter(this.fisierAnunt, true));
        out.println(anunt);
        out.close();
    }
    
    public String getAnunt(){
        return this.fisierAnunt;
    }
    
    public String getSfinti(){
        return this.fisierSfinti;
    }
}
