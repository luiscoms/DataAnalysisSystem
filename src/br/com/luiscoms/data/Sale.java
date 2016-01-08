package br.com.luiscoms.data;

import java.util.ArrayList;

public class Sale {
    private long id;
    private String salesmanName;
    private ArrayList<Item> items = new ArrayList<>();
    

    public Sale(long id, String salesmanName) {
        this.id = id;
        this.salesmanName = salesmanName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double getSum() {
        double sum = 0;
        for (Item item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }
    
}
