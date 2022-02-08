package org.javabasics.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.javabasics.model.User;
import org.javabasics.model.Vendita;
import org.javabasics.service.Service;

public class Controller {
    
    private static Controller controller;

    private Service service;
    private Scanner sc;

    private Controller(){
        service = Service.getInstance();
        sc = new Scanner(System.in);
    }

    public static Controller getInstance(){
        if(controller == null)
            controller = new Controller();
        return controller;
    }

    public void run(){
        int code;
        do{
            printInstruction();
            code = readInt();
            String ans = service.request(code);
            System.out.println(ans);
        }while(code!=0);
    }

    public int readInt(){
        Integer input = null;
        do{
            try{
                input = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("ERRORE: INSERIRE UN NUMERO INTERO");
            }
        }while(input == null);
        return input;
    }

    public String readString(){
        String input = sc.nextLine();
        return input;
    }

    public LocalDate readDate(){
        LocalDate date = null;
        do{ 
            try{
                String input = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(input, formatter);
            }
            catch(DateTimeParseException e){
                System.out.println("ERRORE: inserire una data del tipo dd/MM/yyyy");
            }
        } while(date == null);
        return date;
    }

    public User readUser(){
        System.out.println("Inserisci l'ID: ");
        int id = readInt();
        
        System.out.println("Inserisci il nome: ");
        String nome = readString();
        
        System.out.println("Inserisci il cognome: ");
        String cognome = readString();
        
        System.out.println("Inserisci la data di nascita: ");
        LocalDate dataDiNascita = readDate();
        
        System.out.println("Inserisci l'indirizzo: ");
        String indirizzo = readString();
        
        System.out.println("inserisci il documento ID");
        String documentoId = readString();
        
        return new User(id, nome, cognome, dataDiNascita, indirizzo, documentoId);
    }

    public Vendita readSubscription(int id){

        System.out.println("Inserisci id utente: ");
        int userId = readInt();
        
        System.out.println("Inserisci id prodotto: ");
        int magazineId = readInt();

        return new Vendita(id, magazineId, userId);
    }

    public void printInstruction(){
        System.out.println("inserisci il numero dell'operazione da eseguire");
        System.out.println("1: Visualizza tutti i prodotti");
        System.out.println("2: Compra un prodotto");
        System.out.println("3: Restituisci una vendita");
        System.out.println("4: Aggiungi un nuovo utente");
        System.out.println("5: Esporta i prodotti disponibili su file");
        System.out.println("6: Visualizza tutti gli utenti");
        System.out.println("0: ESCI");
    }

    public void printMessage(String msg){
        System.out.println(msg);
    }

}
