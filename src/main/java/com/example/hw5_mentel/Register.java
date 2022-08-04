package com.example.hw5_mentel;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

/**Register class where the sale is communicated with user*/
public class Register {

    //working variables
    private static Sale sale;

    static ArrayList<Sale> sales = new ArrayList<>();
    private static ProductCatalog catalog;
    public Scanner input = new Scanner(System.in);

    //register constructor
    public Register(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    /**Method to enter in sale line items and display in gui*/
    public static void enterItem(String itemID, int quantity, TextArea txtAHistory, Label lblSubtotal, Label lblTotal) {

        sale = new Sale();

        sales.add(sale);

        ProductSpecification spec = catalog.getSpecification(itemID);

        sale.makeLineItem(spec, quantity);

        String specLine = String.format("%s %10s %25s %10s\n", quantity, spec.getCode(), spec.getName(), spec.getUnitPrice());

        txtAHistory.appendText(specLine);

        lblSubtotal.setText("$" + String.valueOf(sale.getInitialTotal()));

        double total = sale.getTax() + sale.getInitialTotal();

        total = (int)(total*100.0)/ 100.0;

        lblTotal.setText("$" + String.valueOf(total));
    }

    public static void endSale(Label lblChange, String txtTenderedAmount) throws IOException {

        double change = 0;

        double tenderedAmount = Double.parseDouble(txtTenderedAmount);

        change = tenderedAmount - (sale.getInitialTotal() + sale.getTax());

        change = ((int)((change) * 100.0 )) / 100.0;

        System.out.println(change);

        lblChange.setText("$" + change);

        System.out.println(lblChange.getLabelFor());

        sale.becomeComplete();
    }

    /**Method to determine itemID */
    public String determineCorrectItemID(String itemID){

        switch(itemID){
            case"-1":   return "makePayment";
            case"0000": return "displayItems";
            case"A001": return itemID;
            case"A002": return itemID;
            case"A003": return itemID;
            case"A004": return itemID;
            case"A005": return itemID;
            case"A006": return itemID;
            case"A007": return itemID;
            case"B001": return itemID;
            case"B002": return itemID;
            case"B003": return itemID;
        }
        return "noMatch";
    }

    public static double getTotalSales(){
        double total = 0;

        for(int i = 0; i < sales.size(); i++){
           total +=  sales.get(i).getInitialTotal();
        }

        return total;
    }



}

