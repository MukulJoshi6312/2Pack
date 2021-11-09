package com.example.a2pack.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.a2pack.R;
import com.example.a2pack.adapters.productViewAdapter;
import com.example.a2pack.helperClasses.productViewModelClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdcutViewActivity extends AppCompatActivity {


    RecyclerView prodViewRecycler;
    int Piece1000 = 1000,Piece10000 = 10000,Piece25000 = 25000,Piece50000 = 50000,Piece100000 = 100000;
    String model = "Model",size = "Size";
    TextView cartQty;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodcut_view);
        prodViewRecycler = findViewById(R.id.product_view_recyclerView);

        toolbar = findViewById(R.id.toolbarProductView);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Products");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        cartQty = findViewById(R.id.cartQTy);


        cartQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CartViewActivity.class);
                startActivity(intent);
            }
        });





        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        final List<productViewModelClass> list = new ArrayList<>();

        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag A",size+" : 13 X 14","Price of "+Piece1000+" Piece","5000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag A",size+" : 13 X 14","Price of "+Piece10000+" Piece","45000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag A",size+" : 13 X 14","Price of "+Piece25000+" Piece","100000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag A",size+" : 13 X 14","Price of "+Piece50000+" Piece","180000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag A",size+" : 13 X 14","Price of "+Piece100000+" Piece","350000","1"));


        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag B",size+" : 14 X 16","Price of "+Piece1000+" Piece","6000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag B",size+" : 14 X 16","Price of "+Piece10000+" Piece","55000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag B",size+" : 14 X 16","Price of "+Piece25000+" Piece","125000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag B",size+" : 14 X 16","Price of "+Piece50000+" Piece","235000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag B",size+" : 14 X 16","Price of "+Piece100000+" Piece","430000","1"));


        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag C",size+" : 21 X 17","Price of "+Piece1000+" Piece","11000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag C",size+" : 21 X 17","Price of "+Piece10000+" Piece","90000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag C",size+" : 21 X 17","Price of "+Piece25000+" Piece","187500","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag C",size+" : 21 X 17","Price of "+Piece50000+" Piece","350000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag C",size+" : 21 X 17","Price of "+Piece1000+" Piece","660000","1"));



        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag D",size+" : 23 X 19","Price of "+Piece1000+" Piece","13000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag D",size+" : 23 X 19","Price of "+Piece10000+" Piece","120000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag D",size+" : 23 X 19","Price of "+Piece25000+" Piece","275000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag D",size+" : 23 X 19","Price of "+Piece50000+" Piece","525000","1"));
        list.add(new productViewModelClass(R.drawable.m_paper_bag,model+" :Myntra Paper Bag D",size+" : 23 X 19","Price of "+Piece100000+" Piece","1000000","1"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        prodViewRecycler.setLayoutManager(layoutManager);
        productViewAdapter productViewAdapter = new productViewAdapter(this,list,cartQty);
        prodViewRecycler.setAdapter(productViewAdapter);
        productViewAdapter.notifyDataSetChanged();

    }
}