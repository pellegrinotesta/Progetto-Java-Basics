package org.javabasics.model;

public class Prodotto {
    
    private Integer id;
    private String data_inserimento;
    private String marca;
    private String nome;
    private String prezzo;
    private Disponibile disponibile;

    public Prodotto(Integer id, String data_inserimento, String marca, String nome, String prezzo, Disponibile disponibile){
        this.id = id;
        this.data_inserimento = data_inserimento;
        this.marca = marca;
        this.nome = nome;
        this.prezzo = prezzo;
        this.disponibile = disponibile;
    }

    public Integer getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getMarca(){
        return marca;
    }

    public String getPrezzo(){
        return prezzo;
    }

    public String getData_inserimento(){
        return data_inserimento;
    }

    public String getDisponibileStr(){
        if(disponibile == Disponibile.SI)
            return "SI";
        else
            return "NO";
    }

    public boolean isFree(){
        if(disponibile == Disponibile.SI){
            return true;
        }
        return false;
    }

    public void setDisponibile(Disponibile disponibile){
        this.disponibile = disponibile;
    }

    @Override
    public boolean equals(Object mag){
        if(mag instanceof Prodotto && this.id == ((Prodotto)mag).getId())
            return true;
        return false;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public String toString(){
        return "PRODOTTO ID: "+this.id+
               "\n|-> Nome: "+this.nome+
               "\n|-> Data inserimento: "+this.data_inserimento+
               "\n|-> Marca: "+this.marca+
               "\n|-> Prezzo: "+this.prezzo+
               "\n|-> Disponibile: "+this.disponibile+
               "\n";
    }

}
