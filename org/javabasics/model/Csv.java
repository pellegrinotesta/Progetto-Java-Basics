package org.javabasics.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Csv {

    private String csvFile;

    public Csv (String csvFile){
        this.csvFile = csvFile;
    }

    public void write(String[] name, ArrayList<String[]> value, char separator) throws IOException{
        String toWrite = "";
        for(String elem : name){
            toWrite += elem;
            toWrite += separator;
        }
        toWrite += "\n";
        for(String[] line : value){
            for(String elem : line){
                toWrite += elem;
                toWrite += separator;
            }
            toWrite += "\n";
        }
        Path path = Paths.get(csvFile);
        byte[] strToBytes = toWrite.getBytes();
        Files.write(path, strToBytes);
    }

    public ArrayList<String[]> getCsvLineList(){
        ArrayList<String[]> myList = new ArrayList<>();
        ArrayList<String> lineList = new ArrayList<>();
        try {
            InputStream in = getClass().getResourceAsStream(csvFile);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
            String line;
            do{
                line = buffer.readLine();
                if(line != null)
                    lineList.add(line);
            }while (line != null && !line.equals(""));
            myList = parseLine(lineList);
            return myList;
        }
        catch(IOException e){
            System.out.println("IMPOSSIBILE IMPORTARE "+csvFile);
            return myList;
        }
        catch(NullPointerException e){
            System.out.println("IMPOSSIBILE IMPORTARE "+csvFile);
            return myList;
        }
    }

    private ArrayList<String[]> parseLine(ArrayList<String> lineList){
        ArrayList<String[]> parsedLineList = new ArrayList<>();
        int iLine = 1;
        while(iLine<lineList.size()){
            String line = lineList.get(iLine);
            String lineToParse = ""+line;
            int iChar = 0;
            while(iChar<line.length()){
                if(line.charAt(iChar)=='"'){
                    iChar++;
                    while(line.charAt(iChar)!='"'){
                        if(iChar == line.length()-1){
                            iChar = 0;
                            iLine++;
                            line = lineList.get(iLine);
                            lineToParse += line;
                        }
                        else{
                            iChar++;
                        }
                    }
                }
                iChar++;
            }

            String[] parsedLine = lineToParse.split(";");
            
            if(parsedLine.length > 0)
                parsedLineList.add(parsedLine);
            iLine++;
        }
    return parsedLineList;
    }

}
