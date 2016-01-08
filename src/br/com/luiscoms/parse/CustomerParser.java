package br.com.luiscoms.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import br.com.luiscoms.data.Customer;

public class CustomerParser implements Parser {

    private String separetor = "ç";
    
    @Override
    public ArrayList<Customer> parse(BufferedReader bufferedReader) {
        ArrayList<Customer> list = new ArrayList<Customer>();

        try {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                try {
//                    System.out.println("Line: !" + line + "!");
                    String[] RowData = line.split(separetor);
                    if (RowData.length < 4) {
//                        System.out.println("Size of columns is wrong!");
                        continue;
                    }
                    if (!RowData[0].trim().equals("002")) {
//                        System.out.println("Invalid line!");
                        continue;
                    }
                    
                    Customer customer = new Customer();
                    for (int i = 0; i < RowData.length; i++) {
                        switch (i) {
                        case 1:
                            customer.setCNPJ(Long.parseLong(RowData[i].trim()));
                            break;

                        case 2:
                            customer.setName(RowData[i].trim());
                            break;
                            
                        case 3:
                            customer.setBusinessArea(RowData[i].trim());
                            break;

                        default:
                            break;
                        }
                    }
                    
                    list.add(customer);
                    
                } catch (NumberFormatException e) {
//                    System.out.println("Invalid line!");
                }
                line = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
