package br.com.luiscoms.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import br.com.luiscoms.data.Customer;
import br.com.luiscoms.data.Sale;
import br.com.luiscoms.data.Salesman;

public class FileParser {
    
    public void parse(String filename) {

        System.out.println("Parsing file " + filename);
        try {
            ArrayList<Salesman> salesmanList = new SalesmanParser().parse(new BufferedReader(new FileReader(filename)));
            ArrayList<Customer> customerList = new CustomerParser().parse(new BufferedReader(new FileReader(filename)));
            ArrayList<Sale> salesList = new SaleParser().parse(new BufferedReader(new FileReader(filename)));
            
            doOutput(salesList, customerList, salesList);
        } catch (FileNotFoundException e) {
//            System.out.println();
            e.printStackTrace();
        }
        moveToDone(filename);
    }

    private void doOutput(ArrayList<Sale> salesList, ArrayList<Customer> customerList, ArrayList<Sale> salesList2) {
        System.out.println(String.format("Amount of clients in the input file is %d", customerList.size()));
        System.out.println(String.format("Amount of salesman in the input file %d", salesList.size()));
        long idExpensive = 0;
        SaleParser sp = new SaleParser();
        String worstSalesman = "";
        try {
            idExpensive = sp.getMostExpensive(salesList).getId();
            worstSalesman = sp.getWorstSalesman(salesList);
        } catch (Exception e){}
        
        System.out.println(String.format("ID of the most expensive sale %d", idExpensive));
        System.out.println(String.format("Worst salesman ever is %s", worstSalesman));
    }

    private void moveToDone(String filename) {
        File file = new File(filename);
        String newfilename = filename
                                .replace(".dat", ".done.dat")
                                .replace("/data/in/", "/data/out/");

        System.out.println("Moving file from "+filename+" to "+newfilename);
        if (file.renameTo(new File(newfilename))) {
            System.out.println("File is moved successful!");
        } else {
            System.out.println("File is failed to move!");
        }
    }

    public ArrayList<String> getInputFiles() {
        ArrayList<String> results = new ArrayList<>();
        
        File folder = new File("data/in");
//        if (folder != null) {

            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    results.add(file.getAbsolutePath());
                }
            }
            }
//        }
        
        return results;
    }

}
