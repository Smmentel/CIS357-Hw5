// Homework 5
// Course: CIS357
// Due date: 8-2-2022
// Name: Sarah Mentel
// Instructor: Il-Hyung Cho
// Program description: POST System that gets input from
// a file and implements multiple classes to complete transaction with user using GUI
//Github: https://github.com/Smmentel/CIS357-Hw5

/**	Conformance to the OO Design: xx %
        o	Support of item change: partial
        o	Support of random access file: no
        o	Javadoc conformed comments on the classes, methods, and attributes: full
        o	Handling wrong input and invalid input: no
        o	Program does not crash with exceptions: crashes
        o	Correct handling of payment and taxes: partial
        o	Overall layout of GUI and ease of use: almost perfect
*/
package com.example.hw5_mentel;

import java.io.FileNotFoundException;
import java.io.IOException;

/** Store class, creates product catalog and register, runs main method*/
class Hw5_Mentel {

    //create catalog instance
    static ProductCatalog catalog;

    //create catalog object
    static {
        try {
            catalog = new ProductCatalog();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //use catalog object to create register object
    static Register register = new Register(catalog);

    //constructor for store class
    Hw5_Mentel() throws FileNotFoundException {}

    /** Main method */
    public static void main(String [] args) throws IOException {
       GUI.launch(args);
    }

}

