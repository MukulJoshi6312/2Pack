package com.example.a2pack.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2pack.helperClasses.ContactModel;
import com.example.a2pack.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import java.util.Objects;


public class ContactFragment extends Fragment {

    private static final int RESULT_OK = -1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private CardView callCard, emailCard, location;
    private TextInputLayout name, email, phone_number, city, company, quantity, message;
    private Button saveData, selectImage;
    private ImageView choose_image;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ProgressBar uploadPro;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private TextView pText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        callCard = view.findViewById(R.id.phone_call);
        emailCard = view.findViewById(R.id.email_contact);
        location = view.findViewById(R.id.map_location);

        name = view.findViewById(R.id.userName);
        email = view.findViewById(R.id.userEmail);
        phone_number = view.findViewById(R.id.userPhone);
        city = view.findViewById(R.id.userCity);
        company = view.findViewById(R.id.userCompany);
        quantity = view.findViewById(R.id.quantity);
        message = view.findViewById(R.id.message);
        uploadPro = view.findViewById(R.id.upload_progress);

        choose_image = view.findViewById(R.id.choose_image);

        selectImage = view.findViewById(R.id.choose_button);
        saveData = view.findViewById(R.id.message_button);

        pText = view.findViewById(R.id.progressText);


        mStorageRef = FirebaseStorage.getInstance().getReference("Contacts");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Contacts");


        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpLoadData();
            }
        });

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Select Image", Toast.LENGTH_SHORT).show();
                openFileChooser();
            }
        });

        callCard.setOnClickListener(v -> {
            String phone_number = "7290067460";
            try {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone_number));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Sorry we are not found activity!", Toast.LENGTH_SHORT).show();
            }
        });

        emailCard.setOnClickListener(new View.OnClickListener() {
            String email_address = "order@2pack.in";

            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getActivity(), "Gmail Opening", Toast.LENGTH_SHORT).show();
                    Intent intent = requireActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Sorry we are not found activity!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        String mapUrl = "https://www.google.com/maps/place/GLOBAL+PARACHEM+LLP/@29.7567847,75.9019868,8z/data=!4m9!1m2!2m1!1sGlobal+Parachem+LLP+C-19,sector-A2,%5CnTronica+City,Loni,Ghaziabad,%5CnUttar+Pradesh+201103!3m5!1s0x390cffc21737b639:0xc656daaea308b1d!8m2!3d28.7885093!4d77.2620075!15sCldHbG9iYWwgUGFyYWNoZW0gTExQIEMtMTksc2VjdG9yLUEyLFxuVHJvbmljYSBDaXR5LExvbmksR2hhemlhYmFkLFxuVXR0YXIgUHJhZGVzaCAyMDExMDOSARFwYWNrYWdpbmdfY29tcGFueQ";
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
                startActivity(intent);
            }
        });

        return view;
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(choose_image);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = requireContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void UpLoadData() {
        String cName = Objects.requireNonNull(name.getEditText()).getText().toString();
        String cEmail = Objects.requireNonNull(email.getEditText()).getText().toString();
        String cPhone = Objects.requireNonNull(phone_number.getEditText()).getText().toString();
        String cCity = Objects.requireNonNull(city.getEditText()).getText().toString();
        String cCompany = Objects.requireNonNull(company.getEditText()).getText().toString();
        String cQuantity = Objects.requireNonNull(quantity.getEditText()).getText().toString();
        String cMessage = Objects.requireNonNull(message.getEditText()).getText().toString();

        if (cName.isEmpty()) {
            name.setError("Please Enter The Name");
            return;
        }
        if (cEmail.isEmpty()) {
            email.setError("Please Entre The Email");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(cEmail).matches()) {
            email.setError("Please Enter The Valid Email");
            return;
        }
        if (cPhone.isEmpty()) {
            phone_number.setError("Please Enter The Phone Number");
            return;
        }
        if (cCity.isEmpty()) {
            city.setError("Please Enter The City Name");
            return;
        }
        if (cCompany.isEmpty()) {
            company.setError("Pleas Enter The Company Name");
            return;
        }
        if (cQuantity.isEmpty()) {
            quantity.setError("Pleas Enter The Quantity");
            return;
        }
        if (cMessage.isEmpty()) {
            message.setError("Pleas Enter The Message");
            return;
        }

        if (mImageUri != null) {
            uploadPro.setVisibility(View.VISIBLE);
            pText.setVisibility(View.VISIBLE);
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            uploadPro.setProgress(0);

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            final String sdownload_url = String.valueOf(downloadUrl);

                            Toast.makeText(getContext(), "Upload Successfully", Toast.LENGTH_SHORT).show();
                            ContactModel contactModel = new ContactModel(cName, cEmail, cPhone, cCity, cCompany, cQuantity, cMessage, sdownload_url);
                            String uploadId = mDatabaseRef.push().getKey();
                            assert uploadId != null;
                            mDatabaseRef.child(uploadId).setValue(contactModel);
                            uploadPro.setVisibility(View.GONE);
                            pText.setVisibility(View.VISIBLE);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            uploadPro.setVisibility(View.GONE);
                            pText.setVisibility(View.GONE);

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            uploadPro.setVisibility(View.VISIBLE);
                            uploadPro.setProgress((int) progress);
                            pText.setText(String.valueOf(progress+"%"));
                        }
                    });
        } else {
            Toast.makeText(getContext(), "Please select a image", Toast.LENGTH_SHORT).show();
        }
    }
}