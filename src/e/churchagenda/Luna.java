package e.churchagenda;

import java.util.HashMap;

class Luna{
    private String nume;
    private HashMap<String, Ziua> zile;
    
    Luna(){}
    
    Luna(String luna){
        this.zile = new HashMap<>();
        switch(luna){
            case "01" -> this.nume = "Ianuarie";
            case "02" -> this.nume = "Februarie";
            case "03" -> this.nume = "Martie";
            case "04" -> this.nume = "Aprilie";
            case "05" -> this.nume = "Mai";
            case "06" -> this.nume = "Iunie";
            case "07" -> this.nume = "Iulie";
            case "08" -> this.nume = "August";
            case "09" -> this.nume = "Septembrie";
            case "10" -> this.nume = "Octombrie";
            case "11" -> this.nume = "Noiembrie";
            case "12" -> this.nume = "Decembrie";
        }  
        
        String ziua;
        if(luna.matches("^0[1,3,5,7,8]|1[0,2]")){
            for(int i = 1; i < 32; i++){
                ziua = String.format("%02d", i);
                this.zile.put(ziua, new Ziua(ziua, this.nume));
            }
        }else if(luna.matches("^0[4,6,9]|1[1]")){
            for(int i = 1; i < 31; i++){
                ziua = String.format("%02d", i);
                this.zile.put(ziua, new Ziua(ziua, this.nume));
            }
        }else if(luna.matches("^0[2]")){
            for(int i = 1; i < 29; i++){
                ziua = String.format("%02d", i);
                this.zile.put(ziua, new Ziua(ziua, this.nume));
            }
        }
    }

    public Ziua getZiua(String ziua){
        return zile.get(ziua);
    }
}
