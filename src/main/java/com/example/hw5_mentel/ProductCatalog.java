package com.example.hw5_mentel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/** Class to handle file input, and hold item data */
public class ProductCatalog {
    private static Map<String, ProductSpecification> productSpecs = new HashMap<String, ProductSpecification>();

    //Create file instance
    private static File file = new File("src/Items");

    /** Constructor that reads from input file and loads file data into hash map */
    public ProductCatalog() throws FileNotFoundException {

        //Create scanner for the file
        Scanner fileInput = new Scanner(file);

        Scanner delimiter = fileInput.useDelimiter(",");

        //Read file data into Product Specification object
        while (delimiter.hasNext()) {

            String code = delimiter.next().trim();

            boolean salesTax = false;
            if (delimiter.hasNext()) {
                String name = delimiter.next().trim();

                String price = delimiter.next().trim();

                double unitPrice = Double.parseDouble(price);

                //add item to hash map
                ProductSpecification spec = new ProductSpecification(code, name, unitPrice, salesTax);
                productSpecs.put(code, spec);
            }
        }
    }

    /** Return method */
    public ProductSpecification getSpecification (String itemID){

        return productSpecs.get(itemID);
    }

    /** Method to print out items */
    public static void displayItems (){

        System.out.println("Item Code     Item Name          Unit Price");


        for(Map.Entry<String, ProductSpecification> entry: productSpecs.entrySet()){
            System.out.printf("%s %20s %10s", entry.getKey(), entry.getValue().getName(), "$"+entry.getValue().getUnitPrice());
            System.out.println();}

        System.out.println();
    }


    /** Method to add item */
    public static void addItem(String newID, String newName, String newUnitPrice) throws IOException {

        //Create object to write in the file
        FileWriter output = new FileWriter(file, true);

        Scanner input = new Scanner(System.in);

        boolean salesTax = false;

        if(newID.charAt(0) == 'A'){
            salesTax = true;
        }

        double unitPrice = Double.parseDouble(newUnitPrice);

        ProductSpecification spec = new ProductSpecification(newID, newName, unitPrice, salesTax);

        productSpecs.put(newID, spec);

        output.write("");

        output.write(newID + ", " + newName + ", " + unitPrice + ", ");

        output.close();
    }

    /** Method to delete item*/
    public static void deleteItem(String itemID) throws IOException {

        productSpecs.remove(itemID);

        System.out.println("Item: " + itemID + " has been removed");
    }

    /** Method to modify item */
    public static void modifyItem(String itemID, String newID, String newName, String newUnitPrice) throws IOException {

        productSpecs.get(itemID).setName(newName);
        productSpecs.get(itemID).setUnitPrice(Double.parseDouble(newUnitPrice));
        productSpecs.get(itemID).setCode(newID);

        System.out.println("Product: " + itemID + " has been modified.");
    }

    /** Method to quit */
    public static void quit(){
        System.out.println();
        System.out.print("Thank you for using POST System. Bye.");
        displayItems();
        System.exit(1);

    }

    /** Method to determine if the user entered an existing item */
    public static String determineCorrectItemID(String itemID){

        switch(itemID){
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

    /** Method to return list of item code for combo box*/
    public ObservableList getList(){

        ObservableList<String> list = FXCollections.observableArrayList();

        for(Map.Entry<String, ProductSpecification> entry: productSpecs.entrySet()){
            list.add(entry.getValue().getCode());
        }

        return list;
    }
}
