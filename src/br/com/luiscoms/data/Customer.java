package br.com.luiscoms.data;

public class Customer {
    private long cnpj;
    private String name;
    private String businessArea;
    public long getCnpj() {
        return cnpj;
    }
    public void setCNPJ(long cnpj) {
        this.cnpj = cnpj;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBusinessArea() {
        return businessArea;
    }
    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

}
