package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
int quantity = 0;
String whippedCream = new String("");
    String choco = new String("");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Displays order summary when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary());

    }

    /**
     * This method creates a summary of the order
     * @return Order summary
     */
    public String createOrderSummary(){
        return "Name: " + getName() + "\nQuantity: " + quantity + "\nTotal: $" + calculatePrice() + whippedCream + choco + "\nThank you!";
    }
    /**
     *This method increments the quantity when the plus button is clicked
     */
    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     *This method decrements the quantity when the minus button is clicked
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the price on the screen
     * @param message displays price on the screen
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView)findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return  is the amount of coffees ordered
     */
    private int calculatePrice(){
        return (quantity * 5);
    }


    public void toppings(View view){
        //is the view now checked
        CheckBox topping = (CheckBox)findViewById(R.id.whipped_cream);
        boolean checked = topping.isChecked();
        //check which checkbox was clicked
                if (checked){
                    whippedCream = "\nAdd whipped cream";
                }
                //add to order summary
                else{
                    //do nothing
                    whippedCream = "";
        }
    }
    public void chocolate(View view){
        CheckBox chocolate = (CheckBox)findViewById(R.id.chocolate_check_box);
        boolean chocoYes = chocolate.isChecked();
        if (chocoYes){
            choco = "\nAdd Chocolate";
        }
        else {
            choco = "";
        }
    }
    public String getName(){
        TextView nameField = (TextView)findViewById(R.id.name_box);
         String name =  new String (nameField.getText().toString());
        return name;
    }
}