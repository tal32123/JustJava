package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
int quantity = 1;
String whippedCream = new String("");
    String choco = new String("");

    /**
     * These booleans check if toppings are inside
     */
    boolean addChoc = false;
    boolean addWhip = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Displays and emails order summary when the order button is clicked.
     */
    public void submitOrder(View view) {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + getName());
            intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }


    }

    /**
     * This method creates a summary of the order
     * @return Order summary
     */
//    public String createOrderSummary(){
//        return "Name: " + getName() + "\nQuantity: " + quantity + "\nTotal: $" + calculatePrice() + whippedCream + choco + "\nThank you!";
//
//    }

    public String createOrderSummary(){
        return "Name: " + getName() + "\nQuantity: " + quantity + "\nTotal: " + NumberFormat.getCurrencyInstance().format(calculatePrice()) + whippedCream + choco + "\nThank you!";
    }
    /**
     *This method increments the quantity when the plus button is clicked
     */
    public void increment(View view) {
        if (quantity<100){
            quantity = quantity + 1;
        }
        else{
            quantity = 100;
            Toast.makeText(this, "100 Coffees is the maximum per order", Toast.LENGTH_SHORT).show();
        }

        display(quantity);
    }

    /**
     *This method decrements the quantity when the minus button is clicked
     */
    public void decrement(View view) {
        if (quantity > 1){
        quantity = quantity - 1;}
        else{
            quantity = 1;
            Toast.makeText(this, "You can not order less than one cup of coffee", Toast.LENGTH_SHORT).show();
        }
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
     * Calculates the price of the order based on the current quantity.
     *
     * @return  is the amount of coffees ordered
     */
    private int calculatePrice(){
        return (quantity * getBaseCost());
    }


    public void toppings(View view){
        //is the view now checked
        CheckBox topping = (CheckBox)findViewById(R.id.whipped_cream);
        boolean checked = topping.isChecked();
        //check which checkbox was clicked
                if (checked){
                    whippedCream = "\nAdd whipped cream";
                    addWhip = true;
                }
                //add to order summary
                else{
                    //do nothing
                    whippedCream = "";
                    addWhip = false;
        }
    }
    public void chocolate(View view){
        CheckBox chocolate = (CheckBox)findViewById(R.id.chocolate_check_box);
        boolean chocoYes = chocolate.isChecked();
        if (chocoYes){
            choco = "\nAdd Chocolate";
            addChoc = true;
        }
        else {
            choco = "";
            addChoc = false;
        }
    }
    public String getName(){
        TextView nameField = (TextView)findViewById(R.id.name_box);
         String name =  new String (nameField.getText().toString());
        return name;
    }
    public int getBaseCost(){
        if (addChoc && addWhip){
            return 8;
        }
        else if (addChoc){
            return 7;
        }
        else if (addWhip){
            return 6;
        }
        else {
            return 5;
        }
    }
}