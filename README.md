# Progetto-Java-Basics
Progetto Java Basics per Start2Impact

<img width="459" alt="Schermata 2022-02-08 alle 12 17 22" src="https://user-images.githubusercontent.com/82523501/152977124-c0f369b4-968f-4320-b53a-817f9510e962.png">

## Compilare, creare il file .jar ed eseguire l'applicazione 
Windows (è necessario aver installato JDK):

Scaricare la cartella in formato zip nel proprio computer e scompattarla. 
Aprire il prompt dei comandi e posizionarsi all'interno della cartella appena scompattata.
Per compilare l'applicazione digitare:
```sh
javac org\javabasics\Application.java
```
per creare il file jar digitare ora il seguente comando:
```sh
jar cfe application.jar org.javabasics.Application org\javabasics\Application.class org\javabasics\model\*.class  org\javabasics\service\*.class org\javabasics\controller\*.class org\javabasics\csv\*.csv
```
per eseguire l'applicazione digitare
```sh
java -jar application.jar
```
## Funzioni dell'applicazione
L'applicazione permette di gestire le vendite di un e-commerce plant based. Di seguito si riportano i principali comandi dell'applicazione. Il numero relativo al comando che si vuole impartire deve essere inserito nel terminale seguito dal tasto invio. 
* (1) Visualizza tutti i prodotti:
Mostra tutti i prodotti in catalogo. Per ogni prodotto viene mostrato l'ID, il nome, marca, prezzo, data inserimento e disponibilità.
* (2) Comprare un prodotto:
Permette di comprare un prodotto se questo è disponibile. E' necessario fornire l'id del prodotto e l'id dell'utente che vuole acquistare.
* (3) Restituisci una vendita:
Consente di visualizzare un prodotto già venduto. E' necessario fornire l'id della vendita.
* (4) Aggiungi un nuovo utente:
Permette di aggiungere un nuovo utente, per ogni utente inserito è necessario fornire un id, un nome, un cognome, una data di nascita (in formato dd/mm/yyyy), un indirizzo e un documento d'identità.
* (5) Esporta i prodotti disponibili su file:
Esporta i prodotti diposnibili su file .csv nominato con la data del giorno (riviste_dd_mm_yyyy).
* (6) Visualizza tutti gli utenti:
Visualizza tutti gli utenti presenti nell'applicazione.
* (0) ESCI: Termina l'esecuzione dell'applicazione.
