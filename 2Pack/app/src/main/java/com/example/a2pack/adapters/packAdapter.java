package com.example.a2pack.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2pack.R;
import com.example.a2pack.helperClasses.BannerModel;

import java.util.List;


public class packAdapter extends RecyclerView.Adapter<packAdapter.ViewHolder> {


    List<BannerModel> packList;
    Context context;

    public packAdapter(List<BannerModel> packList, Context context) {
        this.packList = packList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.packed_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  packAdapter.ViewHolder holder, int position) {
        BannerModel bannerModel = packList.get(position);
        int image = packList.get(position).getBannerImage();
        String text = packList.get(position).getBannerTag();
        holder.SetData(image,text);

    }

    @Override
    public int getItemCount() {
        return packList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView pImage;
        private TextView pTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pImage = itemView.findViewById(R.id.pack_image);
            pTextView = itemView.findViewById(R.id.pack_text);
        }

        public  void SetData(int packImage,final String packText){
            pImage.setImageResource(packImage);
            pTextView.setText(packText);

        }
    }
}
