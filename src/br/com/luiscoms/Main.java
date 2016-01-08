package br.com.luiscoms;

import java.io.File;
import java.util.ArrayList;

import br.com.luiscoms.data.Salesman;
import br.com.luiscoms.parse.FileParser;
import br.com.luiscoms.parse.SalesmanParser;

public class Main {

    public static void main(String[] args) {
        

        try {

          FileParser fileParser = new FileParser();
            while (true) {
                System.out.println("Program running ");
                
                ArrayList<String> inputFiles = fileParser.getInputFiles();
                
                if (inputFiles.size() == 0) {
                    System.out.println("no files to parse");
                }
                for (String filename : inputFiles) {
                    fileParser.parse(filename);
                }

                System.out.println("sleep ");
                Thread.sleep(2000);
            }
        
        } catch (InterruptedException e) {
            System.out.println("User finished");
        }

        System.out.println("Program finished");

    }

}
