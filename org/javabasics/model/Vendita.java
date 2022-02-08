package org.javabasics.model;

public class Vendita {

    private int id;
    private int idProdotto;
    private int idUtente;

    public Vendita(int id, int idProdotto, int idUtente){
        this.id = id;
        this.idProdotto = idProdotto;
        this.idUtente = idUtente;
    }

    public int getId(){
        return id;
    }

    public int getIdProdotto(){
        return idProdotto;
    }

    public int getIdUtente(){
        return idUtente;
    }

    @Override
    public boolean equals(Object sub){
        if(sub instanceof Vendita && this.id == ((Vendita)sub).getId())
            return true;
        return false;
    }

    @Override
    public int hashCode(){
        return id;
    }

    
}
