package org.javabasics.model;

import java.util.ArrayList;
import java.util.HashSet;

import org.javabasics.controller.Controller;

public class VenditaSet{

    private int nextId = 1;
    private HashSet<Vendita> vendite;

    public VenditaSet(){
        vendite = new HashSet<>();
    }

    public int getNextId(){
        return nextId;
    }

    public boolean add(Vendita sub){
        if(vendite.add(sub)){ 
            if(sub.getId()>=nextId)
                nextId = sub.getId()+1;
            return true;
        }
        return false;
    }

    public boolean existId(int id){
        for(Vendita sub : vendite)
            if(sub.getId() == id)
                return true;
        return false;
    }

    public Vendita getById(int id){
        for(Vendita sub : vendite){
            if(sub.getId()==id)
                return sub;
        }
        return null;
    }


    public void importFromCsv(String file){
        Csv csv = new Csv(file);
        ArrayList<String[]> csvList = csv.getCsvLineList();
        for(String[] line : csvList){
            try{
                int id = Integer.parseInt(line[0]);
                int idProdotto = Integer.parseInt(line[1]);
                int idUtente = Integer.parseInt(line[2]);
              
                
                Vendita sub = new Vendita(id, idProdotto, idUtente);
                if(!this.add(sub))
                    Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE VENDITA: ID GIA' PRESENTE");;
            }
            catch(NumberFormatException e){
                Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE VENDITA: ID NON VALIDO");
            }
        }
    }

    @Override
    public String toString(){
        String str = "";
        for(Vendita sub : vendite){
            str += sub.toString();
            str+="\n";
        }
        return str;
    }
    
}
