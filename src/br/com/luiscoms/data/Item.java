package br.com.luiscoms.data;

public class Item {
    private long id;
    private int quantity;
    private double price;
    
    public Item(long id, int quantity, double price) {

        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotalPrice() {
        return quantity*price;
    }
}
