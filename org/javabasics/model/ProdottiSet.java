package org.javabasics.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

import org.javabasics.controller.Controller;

public class ProdottiSet{

    private HashSet<Prodotto> prodotti;

    public ProdottiSet(){
        prodotti = new HashSet<>();
    }
    
    public boolean add(Prodotto mag){
        return prodotti.add(mag);
    }

    public boolean existId(int id){
        for(Prodotto mag : prodotti)
            if(mag.getId() == id)
                return true;
        return false;
    }

    public Prodotto getById(int id){
        for(Prodotto mag : prodotti){
            if(mag.getId()==id)
                return mag;
        }
        return null;
    }

    public boolean exportFreeMagToCsv(){
        String[] name = {"ID","Data Inserimento", "Marca", "Nome","Prezzo","Disponibile"};
        ArrayList<String[]> value = new ArrayList<>();
        for(Prodotto mag : prodotti){
            if(mag.isFree()){
                String[] valueArray = {
                                        mag.getId().toString(),
                                        mag.getData_inserimento(),
                                        mag.getMarca(),
                                        mag.getNome(),
                                        mag.getPrezzo(),
                                        mag.getDisponibileStr(),
                                      };
                value.add(valueArray);
            }
        }
        LocalDate ld = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");

        String fileName = "prodotti_"+ld.format(formatter)+".csv";
        Csv csv = new Csv(fileName);
        try{
            csv.write(name, value, ';');
            return true;
        }
        catch(IOException e){
            return false;
        } 
    }

    public void importFromCsv(String file){
        Csv csv = new Csv(file);
        ArrayList<String[]> csvList = csv.getCsvLineList();
        for(String[] line : csvList){
            try{
                int id = Integer.parseInt(line[0]);
                String nome = line[1];
                String data_inserimento = line[2];
                String prezzo = line[3];
                String marca = line[4];
                Disponibile disponibile;
                if(line[5].equals("SI"))
                    disponibile = Disponibile.SI;
                else
                    disponibile = Disponibile.NO;
                Prodotto mag = new Prodotto(id, data_inserimento, marca, nome, prezzo, disponibile);
                if(!this.add(mag))
                    Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE PRODOTTO: ID GIA' PRESENTE");;
            }
            catch(NumberFormatException e){
                Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE PRODOTTO: ID NON VALIDO");
            }
        }
    }

    @Override
    public String toString(){
        String str = "";
        for(Prodotto mag : prodotti){
            str+=mag.toString();
            str+="\n";
        }
        return str;
    }
    
}
