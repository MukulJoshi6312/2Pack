package com.example.a2pack.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.a2pack.R;
import com.example.a2pack.adapters.CartItemViewAdapter;
import com.example.a2pack.helperClasses.DatabaseHelper;
import com.example.a2pack.helperClasses.OrderClass;
import com.example.a2pack.helperClasses.productViewModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CartViewActivity extends AppCompatActivity {


    RecyclerView cartRecyclerView;
    DatabaseReference databaseReference;
    CartItemViewAdapter adapter;
    ArrayList<productViewModelClass> list;
    ProgressBar progressBar;
    private TextView orderNow,totalPrice;
    TextView showText;
    Toolbar toolbar;
    FirebaseAuth auth;
    FirebaseFirestore db;
    int cPrice;
    int temp = 0;
    private ImageView emptyImage;
    private Button Shopping;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitle("MyCart");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        cartRecyclerView = findViewById(R.id.cart_recyclerView);
        progressBar = findViewById(R.id.cartProgress);
        orderNow = findViewById(R.id.orderNow);
        showText = findViewById(R.id.showText);
        totalPrice = findViewById(R.id.total_price);

        emptyImage = findViewById(R.id.empty_cart_image);
        Shopping = findViewById(R.id.shopping);

        Picasso.get().load(R.drawable.tenor).into(emptyImage);


        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        databaseReference = FirebaseDatabase.getInstance().getReference("Cart");
        cartRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        adapter = new CartItemViewAdapter(this,list);
        cartRecyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);


        Shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartViewActivity.this,ProdcutViewActivity.class));
            }
        });


//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull  DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    productViewModelClass modelClass = dataSnapshot.getValue(productViewModelClass.class);
//                    assert modelClass != null;
//                    list.add(modelClass);
//                    Collections.reverse(list);
//
//                    String price = modelClass.getProductPrice();
//                    cPrice = Integer.parseInt(price);
//                    temp  = temp+cPrice;
//                }
//                totalPrice.setText("Total Amount: "+temp);
//                progressBar.setVisibility(View.GONE);
//                adapter.notifyDataSetChanged();
//
//            }
//            @Override
//            public void onCancelled(@NonNull  DatabaseError error) {
//                Toast.makeText(CartViewActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.GONE);
//
//
//            }
//        });


        DatabaseHelper helper = new DatabaseHelper(this);
        List<productViewModelClass> modelClasses = helper.getCartItem();
        Collections.reverse(modelClasses);


        for (productViewModelClass modelClass : modelClasses) {
            String model = modelClass.getProductModel();
            Log.v("tag", "Model  = " + model);
            String modelName = modelClass.getProductModel();
            String size = modelClass.getProductSize();
            String perPerice = modelClass.getProductPricePerPiece();
            String price = modelClass.getProductPrice();
            String qty = modelClass.getProductQty();

            cPrice = Integer.parseInt(price);
            temp = temp + cPrice;
            totalPrice.setText("Rs." + temp);

            Log.v("tag ", " model name " + modelName + "\n" +
                    "Model size " + size + "\n" +
                    "per price" + perPerice + "\n" +
                    "price " + temp + "\n" +
                    "quantity " + qty);

            String totalPrice = String.valueOf(temp);


            orderNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderClass orderClass = new OrderClass(modelName, size, perPerice, qty, totalPrice);
                    FirebaseDatabase.getInstance().getReference().child("Order").push().setValue(orderClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(CartViewActivity.this, "Order Placed Successfully!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(CartViewActivity.this,AddressDetailActivity.class);
                            startActivity(intent);

                        }
                    });

                }



            });
        }


        if (modelClasses.size() > 0){
            CartItemViewAdapter adapter = new CartItemViewAdapter(this,modelClasses);
            cartRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
            emptyImage.setVisibility(View.GONE);
            Shopping.setVisibility(View.GONE);
            orderNow.setVisibility(View.VISIBLE);
        }
        else {
            Toast.makeText(this,"There is no data!",Toast.LENGTH_SHORT).show();
            emptyImage.setVisibility(View.VISIBLE);
            Shopping.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            orderNow.setVisibility(View.GONE);
        }




        // clear cart code start
//        totalPrice.setVisibility(View.GONE);
//        totalPrice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//                databaseReference.child("Cart").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull  Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(CartViewActivity.this, "Cart is clear", Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(CartViewActivity.this, "Cart is Already Clear", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        });
        // clear cart code end

    }
}