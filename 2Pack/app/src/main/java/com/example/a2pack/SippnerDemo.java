package com.example.a2pack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class SippnerDemo extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    private Spinner spinner1,spinner2,spinner3;
    private TextView textView;
    String model,size,quantity;
    Button orderButton;
    private TextView ratePerPack;
    private TextView totalAmount;
    private TextView currentQuantity;
    private ImageView increaseQty,decreaseQty;

    // currentQuantity show karne ke liye integer variable
    int qty = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sippner_demo);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        textView = findViewById(R.id.resizeText);
        orderButton = findViewById(R.id.orderDone);
        ratePerPack = findViewById(R.id.rate_per_pack);
        totalAmount = findViewById(R.id.total_amount);
        currentQuantity = findViewById(R.id.currentQuantity);//here show the current quantity
        increaseQty = findViewById(R.id.increase_quantity);
        decreaseQty = findViewById(R.id.decrease_quantity);

       currentQuantity.setText(String.valueOf(qty));

        increaseQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty = qty+1;
                currentQuantity.setText(String.valueOf(qty));


            }
        });

        decreaseQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty==1){
                    Toast.makeText(SippnerDemo.this, "Please select the value", Toast.LENGTH_SHORT).show();
                    return;
                }
                qty = qty-1;
                currentQuantity.setText(String.valueOf(qty));

            }
        });




        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);

        String[] model = getResources().getStringArray(R.array.model);
        String[] size = getResources().getStringArray(R.array.size);
        String[] qty = getResources().getStringArray(R.array.quantity);

        ArrayAdapter modelAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,model);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(modelAdapter);

        ArrayAdapter sizeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,size);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(sizeAdapter);

        ArrayAdapter qtyAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,qty);
        qtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(qtyAdapter);


        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oredrSuccessfullyDone();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.spinner1){
            model =  parent.getItemAtPosition(position).toString();

        }
        if (parent.getId() == R.id.spinner2){
            size =  parent.getItemAtPosition(position).toString();
        }
        if (parent.getId() == R.id.spinner3){
            quantity = parent.getItemAtPosition(position).toString();

        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void oredrSuccessfullyDone() {

    }
}