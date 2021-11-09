package com.example.a2pack.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a2pack.R;
import com.example.a2pack.helperClasses.DatabaseHelper;
import com.example.a2pack.helperClasses.productViewModelClass;
import java.util.List;

public class productViewAdapter extends RecyclerView.Adapter<productViewAdapter.ViewHolder> {

    Context context;
    List<productViewModelClass> productList;
    TextView cartQTy;
    String itemQuantity;
    String itemPrice;
    int id = 1;

    public productViewAdapter(Context context, List<productViewModelClass> productList, TextView cartQTy) {
        this.context = context;
        this.productList = productList;
        this.cartQTy = cartQTy;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view_layout,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull  productViewAdapter.ViewHolder holder, int position) {

        final productViewModelClass productViewModelClass = productList.get(position);
        int imageView = productList.get(position).getProductImage();
        String model = productList.get(position).getProductModel();
        String size = productList.get(position).getProductSize();
        String perPiecePrice = productList.get(position).getProductPricePerPiece();
        String price = productList.get(position).getProductPrice();
        String quantity = productList.get(position).getProductQty();
        holder.setData(imageView,model,size,perPiecePrice,price,quantity);


        holder.plusItem.setOnClickListener(v -> {
            int currentQty = Integer.parseInt(holder.qty.getText().toString());
            holder.qty.setText(String.valueOf(currentQty+1));
            itemQuantity = holder.qty.getText().toString();

            int temp = 0;
            int currentPrice = Integer.parseInt(holder.price.getText().toString());
            temp = currentPrice/currentQty;
            holder.price.setText(String.valueOf(temp+currentPrice));
            itemPrice =  holder.price.getText().toString();


        });

          holder.minusItem.setOnClickListener(v -> {
              int currentQty = Integer.parseInt(holder.qty.getText().toString());
              if(currentQty<=1){
                  Toast.makeText(context,"Please select minimum 1 Quantity",Toast.LENGTH_SHORT).show();
                  return;
              }
              holder.qty.setText(String.valueOf(currentQty-1));
              itemQuantity = holder.qty.getText().toString();

              int temp = 0;
              int currentPrice = Integer.parseInt(holder.price.getText().toString());
              temp = currentPrice/currentQty;
              holder.price.setText(String.valueOf(currentPrice-temp));
              itemPrice =  holder.price.getText().toString();

          });




        holder.AddToCart.setOnClickListener(v -> {

            String itemPrice = holder.price.getText().toString();
            String itemQuantity = holder.qty.getText().toString();

            DatabaseHelper helper = new DatabaseHelper(context);
            productViewModelClass modelClass = new productViewModelClass(model,size,perPiecePrice,itemPrice,itemQuantity);
            helper.addItemIntoCart(modelClass);
            Toast.makeText(context, "Data is added", Toast.LENGTH_SHORT).show();
            int currentQty = Integer.parseInt(cartQTy.getText().toString());
            cartQTy.setText(String.valueOf(currentQty+1));




//            DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
//            productViewModelClass productViewModelClass1 = new productViewModelClass(imageView,model,size,perPiecePrice,itemPrice,itemQuantity);
//            firebaseDatabase.child("Cart").push().setValue(productViewModelClass1).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull  Task<Void> task) {
//                    if (task.isSuccessful()){
//                        Toast.makeText(context, "Item is added", Toast.LENGTH_SHORT).show();
//
//                        int currentQty = Integer.parseInt(cartQTy.getText().toString());
//                        cartQTy.setText(String.valueOf(currentQty+1));
//                    }
//                    else {
//                        Toast.makeText(context, "Something error", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        });


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView plusItem,minusItem;
        private TextView AddToCart;
        private ImageView imageView;
        private TextView model,size,perPiecePrice,price,qty;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_image);
            model = itemView.findViewById(R.id.item_model_name);
            size = itemView.findViewById(R.id.item_size);
            perPiecePrice = itemView.findViewById(R.id.price_of_piece);
            price = itemView.findViewById(R.id.price);
            qty = itemView.findViewById(R.id.qty_text);
            plusItem = itemView.findViewById(R.id.add_image_btn);
            minusItem = itemView.findViewById(R.id.minus_image_btn);
            AddToCart = itemView.findViewById(R.id.addToCart);

        }
        public  void setData(int resource,final String mModel,final String mSize,final String mPerPrice,final String mPrice,final String mQty){
            imageView.setImageResource(resource);
            model.setText(mModel);
            size.setText(mSize);
            perPiecePrice.setText(mPerPrice);
            price.setText(mPrice);
            qty.setText(mQty);
        }
    }
}
