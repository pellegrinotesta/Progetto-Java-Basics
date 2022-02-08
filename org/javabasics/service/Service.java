package org.javabasics.service;

import org.javabasics.controller.Controller;
import org.javabasics.model.Disponibile;
import org.javabasics.model.Prodotto;
import org.javabasics.model.ProdottiSet;
import org.javabasics.model.Vendita;
import org.javabasics.model.VenditaSet;
import org.javabasics.model.User;
import org.javabasics.model.UserSet;

public class Service {

    private static Service service;

    private ProdottiSet prodotti;
    private VenditaSet vendite;
    private UserSet utenti;

    private Service(){

        prodotti = new ProdottiSet();
        vendite = new VenditaSet();
        utenti = new UserSet();

        prodotti.importFromCsv("/org/javabasics/csv/prodotti.csv");
        vendite.importFromCsv("/org/javabasics/csv/vendite.csv");
        utenti.importFromCsv("/org/javabasics/csv/utenti.csv");

    }

    public static Service getInstance(){
        if(service == null)
            service = new Service();
        return service;
    }

    public String request(int code){
        
        String result = "";
        
        switch(code){
            case 0:
                result = "ARRIVEDERCI";
                break;
            case 1:
                result = prodotti.toString();
                break;
            case 2:
                result = addVendita();
                break;
            case 3:
                result = restituisciVendita();
                break;
            case 4:
                result = addUser();
                break;
            case 5:
                result = exportProdotti();
                break;
            case 6:
                result = utenti.toString();
                break;
            default:
                result = "ISTRUZIONE NON VALIDA";
                break;
        }

        return result;
    }

    private String addUser(){
        String result = "";
        Controller controller = Controller.getInstance();
        User newUser = controller.readUser();
        if(utenti.add(newUser)){
            result = "UTENTE INSERITO CORRETTAMENTE";
        }
        else{
            result = "IMPOSSIBILE INSERIRE L'UTENTE: L'ID UTENTE DEVE ESSERE UNICO ";
        }
        return result;
    }

    private String addVendita(){
        Controller controller = Controller.getInstance();
        Vendita newVendita = controller.readSubscription(vendite.getNextId());
        int prodottoId = newVendita.getIdProdotto();
        int userId = newVendita.getIdUtente();
        if(!prodotti.existId(prodottoId) || !utenti.existId(userId))
            return "IMPOSSIBILE INSERIRE LA VENDITA: L'ID UTENTE O L'ID PRODOTTO NON ESISTE";
        Prodotto mag = prodotti.getById(prodottoId);
        if(!mag.isFree())
            return "IMPOSSIBILE INSERIRE LA VENDITA: IL PRODOTTO NON E' DISPONIBILE";
        if(vendite.add(newVendita)){
            mag.setDisponibile(Disponibile.NO);
            return "VENDITA INSERITA CORRETTAMENTE";
        }
        else
            return "IMPOSSIBILE INSERIRE LA VENDITA: L'ID UTENTE DEVE ESSERE UNICO ";
    }

    private String restituisciVendita(){
        Controller controller = Controller.getInstance();
        controller.printMessage("Inserisci id vendita:");
        int subId = controller.readInt();
        if(!vendite.existId(subId))
            return "IMPOSSIBILE VISUALIZZARE LA VENDITA: L'ID VENDITA NON ESISTE ";
        Vendita sub = vendite.getById(subId);
        Prodotto mag = prodotti.getById(sub.getIdProdotto());
        if(sub.getIdProdotto() == mag.getId())	
		return "Il prodotto " + mag.getNome() + " " + mag.getMarca() + " è stato acquistato!";
		return null;
       
    }

    private String exportProdotti(){
            if(prodotti.exportFreeMagToCsv())
                return "ESPORTAZIONE AVVENUTA CON SUCCESSO";
            else
                return "IMPOSSIBILE SCRIVERE SU FILE";
    }

}
