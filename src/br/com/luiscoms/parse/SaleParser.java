package br.com.luiscoms.parse;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument.Iterator;

import br.com.luiscoms.data.Item;
import br.com.luiscoms.data.Sale;
import br.com.luiscoms.data.Salesman;

public class SaleParser implements Parser {

    private String itemPattern = "(?<itemId>[\\d.]+)-(?<quantity>[\\d.]+)-(?<price>[\\d.]+)";
    private String itemsPattern = "(?<items>\\[(,?"+ itemPattern +")+])";
    private String saleWithoutItemsPattern = "003ç(?<saleId>\\d+)çç(?<name>\\w+)";
    private String salePattern = "003ç(?<saleId>\\d+)ç" + itemsPattern + "ç(?<name>\\w+)";
    
    @Override
    public ArrayList<Sale> parse(BufferedReader bufferedReader) {
        ArrayList<Sale> list = new ArrayList<>();

        try {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                
                if (!line.matches(salePattern)) {
//                    System.out.println("Line does not match!");
                    continue;
                }
                Sale sale = parseSale(line);
                ArrayList<Item> items = parseItems(line);
                sale.setItems(items);
//                    System.out.println("Sale Id: " + saleMatcher.group("saleId") + ", Name: " + saleMatcher.group("name"));
                
                
                list.add(sale);
                    
                line = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return list;
    }

    private Sale parseSale(String line) {
//        System.out.println("Line without items: !" + line.replaceAll(itemsPattern, "") + "!");
        
        Pattern pSaleWithoutItems = Pattern.compile(saleWithoutItemsPattern);
        
        String lineWithoutItems = line.replaceAll(itemsPattern, "");
        Matcher saleMatcher = pSaleWithoutItems.matcher(lineWithoutItems);
        saleMatcher.matches();
//        System.out.println("Sale Id: " + saleMatcher.group("saleId") + ", Name: " + saleMatcher.group("name"));
        
        return new Sale(Long.parseLong(saleMatcher.group("saleId")), saleMatcher.group("name"));
    }

    private ArrayList<Item> parseItems(String line) {
        ArrayList<Item> list = new ArrayList<>();
        
        Pattern pSale = Pattern.compile(salePattern);
        Matcher m = pSale.matcher(line);
        m.find();
//
        String lineOnlyItems = m.group("items").replaceFirst("\\[", "").replaceFirst("\\]", "");
//        System.out.println("Items: " + lineOnlyItems);
        
        Pattern pItem = Pattern.compile(itemPattern);
        
        for (String lineItemSplited : lineOnlyItems.split(",")) {
            Matcher itemMatcher = pItem.matcher(lineItemSplited);
            itemMatcher.find();
//            System.out.println("Item: " + lineItemSplited + " Id:" + itemMatcher.group("itemId") + " Quantity:" + itemMatcher.group("quantity") + " Price:" + itemMatcher.group("price"));
            
            list.add(new Item(Long.parseLong(itemMatcher.group("itemId")), Integer.parseInt(itemMatcher.group("quantity")), Double.parseDouble(itemMatcher.group("price"))));
        }
        
        return list;
    }

    public Sale getMostExpensive(ArrayList<Sale> salesList) {

        Sale expensiveSale = salesList.get(0);
        for (Sale sale : salesList) {
            if (expensiveSale.getSum() < sale.getSum()) {
                expensiveSale = sale;
            }
            
        }
        return expensiveSale;
    }
    
    public String getWorstSalesman(ArrayList<Sale> salesList) {

        LinkedHashMap<String, Double> salesmanSum = new LinkedHashMap<>();
        for (Sale sale : salesList) {
            salesmanSum.put(sale.getSalesmanName(), sale.getSum());
        }

        String salesmanName = "";
        double salesmanAmount = Double.MAX_VALUE;
        for (Map.Entry<String, Double> sum : salesmanSum.entrySet()) {
//            System.out.println(sum);
            if (sum.getValue() < salesmanAmount) {
                salesmanName = sum.getKey();
                salesmanAmount = sum.getValue();
            }
            
        }
        return salesmanName;
    }
    
    

}
