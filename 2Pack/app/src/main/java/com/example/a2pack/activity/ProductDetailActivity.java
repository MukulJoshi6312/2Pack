package com.example.a2pack.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a2pack.R;

import java.util.Objects;

public class ProductDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;

    private Spinner qtyPerPack1, qtyPerPack2, qtyPerPack3, qtyPerPack4,qty1Spinner;
    Button orderButton;


    // variable  and calculation for the bag A
    TextView bagA, size13, bagA_RPP, bagA_total;
    String qty1;
    int qty1Rate = 5;
    String bagAqty1;
    int qty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));

        toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Order Detail");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        orderButton = findViewById(R.id.orderNow);


        // Bag a variable find in the xml file

        bagA = findViewById(R.id.bagA);
        size13 = findViewById(R.id.size13);
        bagA_RPP = findViewById(R.id.rate5);
        bagA_total = findViewById(R.id.total1);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        qtyPerPack1 = findViewById(R.id.qty_per_pack1);
        qtyPerPack2 = findViewById(R.id.qty_per_pack2);
        qtyPerPack3 = findViewById(R.id.qty_per_pack3);
        qtyPerPack4 = findViewById(R.id.qty_per_pack4);
        qty1Spinner = findViewById(R.id.qty1);

        qtyPerPack1.setOnItemSelectedListener(this);
        qtyPerPack2.setOnItemSelectedListener(this);
        qtyPerPack3.setOnItemSelectedListener(this);
        qtyPerPack4.setOnItemSelectedListener(this);
        qty1Spinner.setOnItemSelectedListener(this);

        String[] quantity = getResources().getStringArray(R.array.quantity);

        ArrayAdapter qtyAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, quantity);
        qtyAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtyPerPack1.setAdapter(qtyAdapter1);


        ArrayAdapter qtyAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, quantity);
        qtyAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtyPerPack2.setAdapter(qtyAdapter2);


        ArrayAdapter qtyAdapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, quantity);
        qtyAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtyPerPack3.setAdapter(qtyAdapter3);


        ArrayAdapter qtyAdapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, quantity);
        qtyAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtyPerPack4.setAdapter(qtyAdapter4);


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.qty_per_pack1) {
            qty1 = parent.getItemAtPosition(position).toString();
            int qty = Integer.parseInt(qty1);
            qty = qty*qty1Rate;
            bagA_RPP.setText(String.valueOf(qty));

        }
        if (parent.getId() == R.id.qty_per_pack2) {
            String qty = parent.getItemAtPosition(position).toString();
        }
        if (parent.getId() == R.id.qty_per_pack3) {
            String qty = parent.getItemAtPosition(position).toString();
        }
        if (parent.getId() == R.id.qty_per_pack4) {
            String qty = parent.getItemAtPosition(position).toString();
        }


    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}