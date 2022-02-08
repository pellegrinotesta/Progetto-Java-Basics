package org.javabasics;

import org.javabasics.controller.Controller;

public class Application{

    public static void main(String args[]){
        System.out.println("BENVENUTO");
        Controller controller = Controller.getInstance();
        controller.run();
    }

}