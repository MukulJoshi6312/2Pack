package com.example.a2pack.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.a2pack.R;
import com.example.a2pack.adapters.SliderAdapter;
import com.example.a2pack.helperClasses.BannerModel;
import com.example.a2pack.adapters.packAdapter;
import com.example.a2pack.adapters.productAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private CardView cardView;

    private RecyclerView recyclerView,packRecyclerView;
    SliderView sliderView,featured_slider;
    int[] images = {R.drawable.s1,
            R.drawable.s2,
            R.drawable.s3,
            R.drawable.s4};
    String[] tag ={"First","Second","Third","Four"};

    int[] featuredImage ={R.drawable.ff1,
            R.drawable.ff2,
            R.drawable.ff3,
            R.drawable.ff4,
            R.drawable.ff5,
            R.drawable.ff6,
            R.drawable.ff7,
            R.drawable.ff8};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.main_recyclerView);

        //main slider code slider code start
        sliderView = view.findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images,tag);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        // slider code end


        // featured slider start code
        featured_slider = view.findViewById(R.id.featured_slider);
        SliderAdapter featuredSliderAdapter =  new SliderAdapter(featuredImage,tag);
        featured_slider.setSliderAdapter(featuredSliderAdapter);
        featured_slider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        featured_slider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        featured_slider.startAutoCycle();
        // featured slider start end





        // product code start
        final List<BannerModel> bannerModelList = new ArrayList<>();
        bannerModelList.add(new BannerModel("PolyBag", R.drawable.m_polybag));
        bannerModelList.add(new BannerModel("PaperBag", R.drawable.m_paper_bag));

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        productAdapter adapter = new productAdapter(bannerModelList,getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // product code end


        // pack recycler view code start
        final List<BannerModel> packList = new ArrayList<>();

        packList.add(new BannerModel(getResources().getString(R.string.demo),R.drawable.pack1));
        packList.add(new BannerModel(getResources().getString(R.string.demo),R.drawable.pack2));
        packList.add(new BannerModel(getResources().getString(R.string.demo),R.drawable.pack3));
        packList.add(new BannerModel(getResources().getString(R.string.demo),R.drawable.pack4));
        packList.add(new BannerModel(getResources().getString(R.string.demo),R.drawable.pack5));
        packList.add(new BannerModel(getResources().getString(R.string.demo),R.drawable.pack6));

        packRecyclerView = view.findViewById(R.id.packed_recyclerView);
        StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        packRecyclerView.setLayoutManager(layoutManager1);
        packAdapter packAdapter = new packAdapter(packList,getContext());
        packRecyclerView.setAdapter(packAdapter);
        packAdapter.notifyDataSetChanged();
        // pack recycler view cod end

        return view;
    }
}