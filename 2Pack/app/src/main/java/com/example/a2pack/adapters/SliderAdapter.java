package com.example.a2pack.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a2pack.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder>{

    int[] images;
    String[] tag;
    public SliderAdapter(int[] images,String[] tag){
        this.images = images;
        this.tag = tag;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        viewHolder.imageView.setImageResource(images[position]);
      //  viewHolder.textView.setText(tag[position]);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), tag[position] +" Click image", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends  SliderViewAdapter.ViewHolder{

        ImageView imageView;
        TextView textView;
        public Holder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.banner_image);
            textView = itemView.findViewById(R.id.banner_text);

        }
    }

}