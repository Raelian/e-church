package e.churchagenda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

class AgendaEvenimente{
    private final HashMap<String, Luna> luni;
    
    AgendaEvenimente(){
        this.luni = new HashMap<>();
        String luna;
        for(int i = 1; i < 13; i++){
            luna = String.format("%02d", i);
            this.luni.put(luna, new Luna(luna));
        }
    }
    
    public Luna getLuna(String luna){
        return this.luni.get(luna);
    }
    
    public String getEventList(String ziua, String luna, String anul) throws Exception{
        String fileName = this.luni.get(luna).getZiua(ziua).getFile();
        
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        StringBuilder message = new StringBuilder("Lista cu servicii pe data de " + ziua + "/" + luna + "/" + anul + ":\r\n\r\n");
        
        String line;
        String[] splitLine;
        while((line = in.readLine()) != null){
            splitLine = line.split("-");
            message.append("Eveniment: ");
            message.append("\r\n");
            message.append("Data: ");
            message.append(splitLine[1]);
            message.append("\r\n");
            message.append("Ora: ");
            message.append(splitLine[2]);
            message.append("\r\n");
            message.append("Strada: ");
            message.append(splitLine[3]);
            message.append("\r\n");
            message.append("Tel: ");
            message.append(splitLine[4]);
            message.append("\r\n");
            message.append("Nume enorias: ");
            message.append(splitLine[5]);
            message.append("\r\n");
            message.append("\r\n");
        }
        in.close();
        return message.toString();
    }
    
    public String getAnuntList(String ziua, String luna, String anul) throws Exception{
        String fileName = this.luni.get(luna).getZiua(ziua).getAnunt();
        
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        StringBuilder message = new StringBuilder("Lista cu anunturi pe data de " + ziua + "/" + luna + "/" + anul + ":\r\n\r\n");
         
        String line;
        while((line = in.readLine()) != null){
            message.append(line);
        }
        in.close();
        return message.toString();
    }
    
    public String getSfintiList(String ziua, String luna, String anul) throws Exception{
        String fileName = this.luni.get(luna).getZiua(ziua).getSfinti();
        
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        StringBuilder message = new StringBuilder("Lista cu Sfinti pe data de azi: " + ziua + "/" + luna + "/" + anul + "\r\n\r\n");
         
        String line;
        while((line = in.readLine()) != null){
            message.append(line);
            message.append("\r\n");
        }
        in.close();
        return message.toString();
    }
}
