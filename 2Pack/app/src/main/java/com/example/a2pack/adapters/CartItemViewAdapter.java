package com.example.a2pack.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2pack.R;
import com.example.a2pack.helperClasses.DatabaseHelper;
import com.example.a2pack.helperClasses.productViewModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.MissingResourceException;
import java.util.Objects;


public class CartItemViewAdapter extends RecyclerView.Adapter<CartItemViewAdapter.ViewHolder> {

    Context context;
    List<productViewModelClass> list;
    DatabaseHelper databaseHelper;

    public CartItemViewAdapter(Context context, List<productViewModelClass> list) {
        this.context = context;
        this.list = list;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_view_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewAdapter.ViewHolder holder,final int position) {

        productViewModelClass modelClass = list.get(position);
        holder.model.setText(modelClass.getProductModel());
        holder.size.setText(modelClass.getProductSize());
        holder.perPiecePrice.setText(modelClass.getProductPricePerPiece());
        holder.price.setText(modelClass.getProductPrice());
        holder.quantity.setText(modelClass.getProductQty());

        FirebaseAuth auth = FirebaseAuth.getInstance();

//        holder.remove_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
//                DatabaseReference getRef = FirebaseDatabase.getInstance().getReference();
//                FirebaseDatabase.getInstance().getReference().child("Cart").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                    }
//                });
//            }
//        });

        holder.remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Remove Item")
                        .setMessage("Are you sure you want to remove this item!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                databaseHelper.deleteItem(modelClass.getId());
                                list.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no,null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView model, size, perPiecePrice, price, quantity;
        private TextView remove_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            model = itemView.findViewById(R.id.cart_model_name);
            size = itemView.findViewById(R.id.cart_size);
            perPiecePrice = itemView.findViewById(R.id.cart_price_of_piece);
            price = itemView.findViewById(R.id.cart_price);
            quantity = itemView.findViewById(R.id.cart_qty_text);
            remove_item = itemView.findViewById(R.id.remove_item);

        }
    }
}
