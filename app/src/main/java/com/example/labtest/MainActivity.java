package com.example.labtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText;
    private Spinner divisionsSpinner;
    private String name,  email;
    private TextView quantityTextView, priceTextView;
    private Button incrementButton, decrementButton, submitButton;

    private Pattern namePattern = Pattern.compile("[a-z A-Z._]+");
    private Pattern emailPattern = Pattern.compile("[a-z]+[0-9]+@(gmail|yahoo).com" );

    private int quantity = 0;
    private final int pricePerItem = 100;  // Sample price per item (BDT 100)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        divisionsSpinner = findViewById(R.id.divisionsSpinner);
        quantityTextView = findViewById(R.id.quantityTextView);
        priceTextView = findViewById(R.id.priceTextView);
        incrementButton = findViewById(R.id.incrementButton);
        decrementButton = findViewById(R.id.decrementButton);
        submitButton = findViewById(R.id.submitButton);

        // Set onClickListeners for increment and decrement buttons
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementQuantity();
            }
        });

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementQuantity();
            }
        });

        // Set onClickListener for submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name = nameEditText.getText().toString();

                email = emailEditText.getText().toString();


                if (name.isEmpty()){
                    nameEditText.setError("Empty!!");
                    nameEditText.requestFocus();
                }

                else if (!namePattern.matcher(name).matches()){
                    nameEditText.setError("Name can be only Alphabet");
                    nameEditText.requestFocus();
                }



                else if (email.isEmpty()){
                    emailEditText.setError("Empty!!");
                    emailEditText.requestFocus();
                }
                else if (!emailPattern.matcher(email).matches()){
                    emailEditText.setError("Email is not accepted");
                    emailEditText.requestFocus();
                }






                submitOrder();
            }
        });
    }

    // Method to increase quantity
    private void incrementQuantity() {
        quantity++;
        updateQuantityAndPrice();
    }

    // Method to decrease quantity
    private void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        } else {
            Toast.makeText(this, "Quantity cannot be less than 0", Toast.LENGTH_SHORT).show();
        }
        updateQuantityAndPrice();
    }

    // Method to update the quantity and price in the UI
    private void updateQuantityAndPrice() {
        quantityTextView.setText(String.valueOf(quantity));
        int totalPrice = quantity * pricePerItem;
        priceTextView.setText("Price: BDT " + totalPrice);
    }

    // Method to handle form submission
    private void submitOrder() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String division = divisionsSpinner.getSelectedItem().toString();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String orderSummary = "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Division: " + division + "\n"
                + "Quantity: " + quantity + "\n"
                + "Total Price: BDT " + (quantity * pricePerItem);

        Toast.makeText(this, "Order Submitted :\n" + orderSummary, Toast.LENGTH_LONG).show();
        // Here you can add additional logic to handle the order submission
    }
}