package com.example.hw5_mentel;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.*;

/**Sale class contains methods for individual sale*/
public class Sale {

    //Array list to hold sale line items
    private static List <SalesLineItem> saleLineItems= new ArrayList<>();

    private boolean isComplete = false;

    private String curItem;

    /**Method to add item to array list and display specs to user */
    public void makeLineItem(ProductSpecification spec, int quantity){
        SalesLineItem curItem = new SalesLineItem(spec, quantity);
        saleLineItems.add(curItem);
    }

    /** Return method to indicate sale is complete */
    public boolean becomeComplete(){
        return true;
    }

    /** Method to get the total of the sales line items */
    public static double getInitialTotal() {

        double initialTotal = 0.0;

        for(int i = 0; i < saleLineItems.size(); i++){
            initialTotal += (saleLineItems.get(i).getSubtotal());
        }

        return initialTotal;
    }

    /** Method to get the tax of the sales line items */
        public static double getTax() {
            double tax = 0;

            Iterator it = saleLineItems.iterator();
            while (it.hasNext()) {
                SalesLineItem sli = (SalesLineItem) it.next();
                if(sli.hasSalesTax()){
                    tax += ((sli.getSubtotal() * sli.getQuantity()) * 0.06);
                }
            }
            return tax;
        }

    /** Method to display sales line items */
    public void displaySalesLineItems(){

        for(int i = 0; i < saleLineItems.size(); i++){
            System.out.println(saleLineItems.get(i).getCode()+ "  " + saleLineItems.get(i).getName()
                    + "  $" + saleLineItems.get(i).getSubtotal());

        }
    }




}


