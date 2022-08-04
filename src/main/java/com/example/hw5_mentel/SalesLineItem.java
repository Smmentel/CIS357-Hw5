package com.example.hw5_mentel;

/** Class for individual item in each sale*/
public class SalesLineItem extends Sale {
    private ProductSpecification spec;
    private int quantity;
    private ProductCatalog catalog;

    /** Constructor */
    SalesLineItem(ProductSpecification spec, int quantity) {
        this.spec = spec;
        this.quantity = quantity;
    }

    /** Getter methods */
    public String getName() {
        return spec.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCode() {
        return spec.getCode();
    }

    public double getSubtotal() {
        return (spec.getUnitPrice() * quantity);
    }

    public boolean hasSalesTax(){
        return spec.hasSalesTax();
    }
}