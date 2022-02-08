package org.javabasics.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;

import org.javabasics.controller.Controller;

public class UserSet{

    private HashSet<User> userSet;

    public UserSet(){
        userSet = new HashSet<>();
    }

    public boolean add(User usr){
        return(userSet.add(usr));
    }

    public boolean existId(int id){
        for(User usr : userSet)
            if(usr.getId() == id)
                return true;
        return false;
    }

    public void importFromCsv(String file){
        Csv csv = new Csv(file);
        ArrayList<String[]> csvList = csv.getCsvLineList();
        for(String[] line : csvList){
            try{
                int id = Integer.parseInt(line[0]);
                String nome = line[1];
                String cognome = line[2];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataDiNascita = LocalDate.parse(line[3], formatter);
                String indirizzo = line[4];
                String documentoId = line[5];
                User usr = new User(id, nome, cognome, dataDiNascita, indirizzo, documentoId);
                if(!this.add(usr))
                    Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE UTENTE: ID GIA' PRESENTE");;
            }
            catch(NumberFormatException e){
                Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE UTENTE: ID NON VALIDO");
            }
            catch(DateTimeParseException e){
                Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE UTENTE: DATA NON VALIDA");
            }
        }
    }

    @Override
    public String toString(){
        String str = "";
        for(User usr : userSet){
            str+=usr.toString();
            str+="\n";
        }
        return str;
    }
    
}
