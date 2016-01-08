package br.com.luiscoms.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import br.com.luiscoms.data.Salesman;

public class SalesmanParser implements Parser {

    private String separetor = "ç";
    
    @Override
    public ArrayList<Salesman> parse(BufferedReader bufferedReader) {
        ArrayList<Salesman> list = new ArrayList<>();
        
        try {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                try {
//                    System.out.println("Line: !" + line + "!");
                    String[] RowData = line.split(separetor);
                    if (RowData.length < 4) {
//                        System.out.println("Size of line is wrong!");
                        continue;
                    }
                    if (!RowData[0].trim().equals("001")) {
//                        System.out.println("Invalid line!");
                        continue;
                    }
                    
                    Salesman salesman = new Salesman();
                    for (int i = 0; i < RowData.length; i++) {
                        switch (i) {
                        case 1:
                            salesman.setCPF(Long.parseLong(RowData[i].trim()));
                            break;

                        case 2:
                            salesman.setName(RowData[i].trim());
                            break;
                            
                        case 3:
                            salesman.setSalary(Double.parseDouble(RowData[i].trim()));
                            break;

                        default:
                            break;
                        }
                    }
                    
                    list.add(salesman);
                    
                } catch (NumberFormatException e) {
//                    System.out.println("Invalid line!");
//                    e.printStackTrace();
                }
                line = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    

}
