package com.example.a2pack.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2pack.R;
import com.example.a2pack.activity.ProdcutViewActivity;
import com.example.a2pack.activity.ProductDetailActivity;
import com.example.a2pack.helperClasses.BannerModel;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder> {


     List<BannerModel> bannerModelList;
     Context context;

    public productAdapter(List<BannerModel> bannerModelList, Context context) {
        this.bannerModelList = bannerModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productAdapter.ViewHolder holder, int position) {

        final BannerModel bannerModel = bannerModelList.get(position);
        int bannerImage = bannerModelList.get(position).getBannerImage();
        String bannerTag = bannerModelList.get(position).getBannerTag();
        holder.setModelData(bannerImage,bannerTag);

        holder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProdcutViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bannerModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.product_image);
            mText = itemView.findViewById(R.id.product_text);

        }
        public  void setModelData(int resource,final String tag){
            mImage.setImageResource(resource);
            mText.setText(tag);

        }
    }
}
