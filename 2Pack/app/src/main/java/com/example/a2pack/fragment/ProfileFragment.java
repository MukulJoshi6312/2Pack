package com.example.a2pack.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2pack.R;
import com.example.a2pack.helperClasses.UserHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private TextView name,email,phone,city,address;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.pName);
        email = view.findViewById(R.id.pEmail);
        phone = view.findViewById(R.id.pPhone);
        city = view.findViewById(R.id.pCity);
        address = view.findViewById(R.id.pAddress);
        progressBar = view.findViewById(R.id.pProg);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        assert user != null;
        String userID = user.getUid();

        progressBar.setVisibility(View.VISIBLE);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserHelperClass userHelperClass = snapshot.getValue(UserHelperClass.class);
                if (userHelperClass != null){
                    String fullName = userHelperClass.fullname;
                    String userEmail = userHelperClass.email;
                    String userPhone = userHelperClass.phone;
                    String userCity = userHelperClass.city;
                    String userAddress = userHelperClass.address;
                    name.setText(fullName);
                    email.setText(userEmail);
                    phone.setText(userPhone);
                    city.setText(userCity);
                    address.setText(userAddress);
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error+"Something wr0ng happened!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });

        return view;
    }
}