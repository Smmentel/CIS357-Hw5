package com.example.hw5_mentel;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Integer.parseInt;

/** Displays POST GUI to user */
public class GUI extends Application {

    /*Method to display Post Register and Sale*/
    public void start(Stage beforeSaleStage) throws FileNotFoundException {

        double total = 0;

        //Pane object to hold first page
        GridPane postPane = new GridPane();
        postPane.setAlignment(Pos.CENTER);
        postPane.setPadding(new Insets(11.5,12.5,13.5,14.5));
        postPane.setHgap(5.5);
        postPane.setVgap(5.5);

        //Add text to pane
        Text text1 = new Text(0, 1, "Welcome to the Mentel's Store");
        text1.setFont(Font.font("Times New Romain", FontWeight.BOLD, 15));
        postPane.add(text1, 0, 2);

        //Add text to pane
        Text text2 = new Text(0,6, "\n Total Sale for the day is: $" + Register.getTotalSales());
        postPane.add(text2, 0, 6);

        //Add button to pane
        Button btnNewSale = new Button("New Sale");
        btnNewSale.setMaxWidth(200);
        btnNewSale.setFont(Font.font("Times New Romain", FontWeight.EXTRA_BOLD, 15));
        postPane.add(btnNewSale, 0, 4);
        postPane.getChildren();

        //Create and display scene with the pane that was just designed
        Scene beforeSaleScene = new Scene(postPane, 300, 250);
        beforeSaleStage.setTitle("Post Register");
        beforeSaleStage.setScene(beforeSaleScene);
        beforeSaleStage.show();


        /**Create new pane for sale transaction */
        GridPane salePane = new GridPane();
        salePane.setAlignment(Pos.CENTER);
        salePane.setPadding(new Insets(11.5,5.5,13.5,14.5));
        salePane.setHgap(5.5);
        salePane.setVgap(5.5);

        //Item ID
        salePane.add(new Label("Item ID:"), 0 ,0);
        ComboBox cmboItemID = new ComboBox();
        cmboItemID.setMaxWidth(100);
        salePane.add(cmboItemID, 1,0);
        ProductCatalog pc;
        pc = new ProductCatalog();
        cmboItemID.setItems(pc.getList());

        //Item Name
        salePane.add(new Label("Item Name: "), 0 , 1);
        Label lblItemName = new Label("N/A");
        salePane.add(lblItemName, 1, 1);

        //Item Price
        salePane.add(new Label("Item Price: "), 0, 2);
        Label lblItemPrice = new Label("$0.00");
        salePane.add(lblItemPrice, 1, 2);

        //Quantity
        salePane.add(new Label("Quantity: "), 0, 3);
        TextField txtQuantity = new TextField();
        txtQuantity.appendText("0");
        txtQuantity.setMaxWidth(100);
        salePane.add(txtQuantity, 1, 3);
        Button btnAdd = new Button("Add");
        salePane.add(btnAdd,1, 4);

        //Text area to display user item history
        TextArea txtAHistory = new TextArea();
        txtAHistory.setPrefSize(300,100);
        salePane.add(txtAHistory, 1, 5, 1, 5);

        //Sale Subtotal
        salePane.add(new Label("Sale Subtotal: "), 0, 15);
        Label lblSubtotal = new Label("$0.00");
        salePane.add(lblSubtotal, 1 ,15);

        //Sale Tax
        salePane.add(new Label("Sale Tax Total (%6): "), 0, 16);
        Label lblTotal = new Label("$0.00");
        salePane.add(lblTotal, 1, 16);

        //Tendered Amount and checking out
        salePane.add(new Label("Tendered Amount: "), 0, 17);
        TextField txtTenderedAmount = new TextField();
        txtTenderedAmount.setMaxWidth(100);
        salePane.add(txtTenderedAmount, 1, 17);
        Button btnCheckout = new Button("Checkout");
        salePane.add(btnCheckout, 1, 19);

        //Change
        salePane.add(new Label("Change: "), 0, 20);
        Label lblChange = new Label("$0.00");
        salePane.add(lblChange, 1, 20);

        //Done button for user to go back to the first scene
        Button btnDone = new Button("Done");
        salePane.add(btnDone, 1, 21);

        //Create new scene with the salePane that was just designed
        Scene newSaleScene = new Scene(salePane);



        /** Event handler, when user clicks newSale button, the scene changes to New Sale */
        btnNewSale.setOnAction(e -> beforeSaleStage.setScene(newSaleScene));

        beforeSaleStage.setTitle("New Sale");


        final String[] itemID = {"aaaaaa"};

        /**Event handler, when user determines which item to purchase, labels change */
        cmboItemID.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue <? extends String> observable, String oldValue, String newValue) {
                itemID[0] = newValue;
                lblItemName.setText(pc.getSpecification(newValue).getName());
                lblItemPrice.setText(String.valueOf(pc.getSpecification(newValue).getUnitPrice()));

            }
        });

        Register register = new Register(pc);

        /**Event handler, when user clicks on the add button, Register enters item into sale*/
       // btnAdd.setOnAction(e -> register.enterItem(itemID[0], Integer.parseInt(txtQuantity.getText()), txtAHistory, lblSubtotal, lblTotal));

        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                register.enterItem(itemID[0], Integer.parseInt(txtQuantity.getText()), txtAHistory, lblSubtotal, lblTotal);
            }
        });
        //Event handler, when user clicks to check out, their change is displayed.
        btnCheckout.setOnAction(e -> {
            try {
                register.endSale(lblChange, txtTenderedAmount.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        /**Create new pane for modify item data*/
        GridPane itemPane = new GridPane();
        itemPane.setAlignment(Pos.CENTER);
        itemPane.setPadding(new Insets(11.5,5.5,13.5,14.5));
        itemPane.setHgap(5.5);
        itemPane.setVgap(5.5);

        //ItemID
        itemPane.add(new Label("Item ID:"), 0 ,0);
        ComboBox cmboItemIDMod = new ComboBox();
        cmboItemIDMod.setMaxWidth(100);
        itemPane.add(cmboItemIDMod, 1,0);
        cmboItemIDMod.setItems(pc.getList());

        //Item Name
        itemPane.add(new Label("Item Name: "), 0 , 1);
        Label lblItemNameModed = new Label("N/A");
        itemPane.add(lblItemNameModed, 1, 1);

        //Change Item Code
        itemPane.add(new Label("Code: "), 0, 2);
        //User Input
        TextField txtItemCode = new TextField();
        txtItemCode.setMaxWidth(100);
        itemPane.add(txtItemCode, 1, 2);

        //Change Item Name
        itemPane.add(new Label("Name: "), 0, 3);
        //User input
        TextField txtItemName = new TextField();
        txtItemName.setMaxWidth(100);
        itemPane.add(txtItemName, 1, 3);


        //Change Item Price
        salePane.add(new Label("Change: "), 0, 4);
        //User input
        TextField txtItemPrice = new TextField();
        txtItemPrice.setMaxWidth(100);
        itemPane.add(txtItemPrice, 1, 4);

        //Modification button
        Button btnModify = new Button("Modify");
        itemPane.add(btnModify, 0, 5);

        //Modification button
        Button btnAddItem = new Button("Add");
        itemPane.add(btnAddItem, 1, 5);

        //Delete button
        Button btnDelete = new Button("Delete");
        itemPane.add(btnDelete, 2, 5);

        //Quit button
        Button btnQuit = new Button("Quit");
        itemPane.add(btnQuit, 3, 5);

        //Create new scene with the salePane that was just designed
        Scene itemScene = new Scene(itemPane);

        /** Event handler, when user clicks done button, new Scene to modify item data */
       btnDone.setOnAction(e -> beforeSaleStage.setScene(itemScene));

        beforeSaleStage.setTitle("Modify Items");

        /**Event handler, when user determines which item to purchase, labels change */
        cmboItemIDMod.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue <? extends String> observable, String oldValue, String newValue) {
                itemID[0] = newValue;
                lblItemNameModed.setText(pc.getSpecification(newValue).getName());
            }
        });

        /** Event handler, when user clicks Modify */
        btnModify.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    String itemID = String.valueOf(cmboItemIDMod.valueProperty());

                    pc.modifyItem(itemID, txtItemCode.getText(), txtItemName.getText(), txtItemPrice.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        /** Event handler, when user clicks Add */
        btnAddItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    pc.addItem(txtItemCode.getText(), txtItemName.getText(), txtItemPrice.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        /** Event handler, when user clicks Delete */
        btnDelete.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    pc.deleteItem(String.valueOf(cmboItemIDMod.valueProperty()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        /** Event handler, when user clicks Quit */
        btnQuit.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                pc.quit();
            }
        });

        }
    }


      /**try {

      } catch(InputMismatchException ex){
          //Pane object to hold first page
          GridPane exceptionPane = new GridPane();
          exceptionPane.setAlignment(Pos.CENTER);
          exceptionPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
          exceptionPane.setHgap(5.5);
          exceptionPane.setVgap(5.5);

          //Add text to pane
          Text input = new Text(0, 1, "Please enter valid input" );
          input.setFont(Font.font("Times New Romain", FontWeight.BOLD, 15));
          exceptionPane.add(input, 0, 2);

          //Add text to pane
          Text tryAgain = new Text(0, 6, "\n Try Again");
          exceptionPane.add(tryAgain, 0, 6);
      }
*/

