package com.example.wecareforpets_;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class add extends AppCompatActivity {

    TextView petCategory, breed, sex, price, purpose, ownerName, ownerEmail, ownerPhone, ownerAddress;
    ImageView imageViewUploadedPetBtn, imageViewUploadedPet;
    Button buttonAddPost;
    Uri imageUri;
    RelativeLayout relativeLayout;

    private FirebaseDatabase database;
    private FirebaseStorage firebasestorage;

    ProgressDialog dialog;

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    imageViewUploadedPet.setImageURI(imageUri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        database= FirebaseDatabase.getInstance();
        firebasestorage = FirebaseStorage.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.setTitle("Post Uploading...");
        dialog.setCanceledOnTouchOutside(false);

        petCategory = findViewById(R.id.PetCategory);
        breed = findViewById(R.id.Breed);
        sex = findViewById(R.id.Sex);
        price = findViewById(R.id.Price);
        purpose = findViewById(R.id.Purpose);
        ownerName = findViewById(R.id.OwnerName);
        ownerEmail = findViewById(R.id.OwnerEmail);
        ownerPhone = findViewById(R.id.OwnerPhone);
        ownerAddress = findViewById(R.id.OwnerAddress);
        relativeLayout = findViewById(R.id.reletive);

        imageViewUploadedPet = findViewById(R.id.imageViewUploadedPet);
        imageViewUploadedPetBtn = findViewById(R.id.imageViewUploadedPetbtn);

        buttonAddPost = findViewById(R.id.buttonAddPost);
        imageViewUploadedPetBtn.setOnClickListener(view -> {
            uploadImage();
            relativeLayout.setVisibility(View.VISIBLE);
            imageViewUploadedPetBtn.setVisibility(View.GONE);
        });


        buttonAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  dialog.show();

                  final StorageReference reference = firebasestorage.getReference().child("pet")
                          .child(System.currentTimeMillis()+"");
                  reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                      @Override
                      public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                          reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                              @Override
                              public void onSuccess(Uri uri) {
                                  projetModel Mmodel = new projetModel();
                                  Mmodel.setImageViewUploadedPet(uri.toString());

                                  Mmodel.setPetCategory(petCategory.getText().toString());
                                  Mmodel.setBreed(breed.getText().toString());
                                  Mmodel.setSex(sex.getText().toString());
                                  Mmodel.setPrice(price.getText().toString());
                                  Mmodel.setPurpose(purpose.getText().toString());
                                  Mmodel.setOwnerName(ownerName.getText().toString());
                                  Mmodel.setOwnerEmail(ownerEmail.getText().toString());
                                  Mmodel.setOwnerPhone(ownerPhone.getText().toString());
                                  Mmodel.setOwnerPhone(ownerAddress.getText().toString());

                                  database.getReference().child("pet").push().setValue(Mmodel)
                                          .addOnSuccessListener(new OnSuccessListener<Void>() {
                                              @Override
                                              public void onSuccess(Void unused) {
                                                  dialog.dismiss();
                                              Toast.makeText(add.this,"Post upload susufull",Toast.LENGTH_SHORT).show();
                                              }
                                          }).addOnFailureListener(new OnFailureListener() {
                                              @Override
                                              public void onFailure(@NonNull Exception e) {
                                                  dialog.dismiss();
                                                  Toast.makeText(add.this,"ERROR ",Toast.LENGTH_SHORT).show();
                                              }
                                          });

                              }
                          });
                      }
                  });

            }

        });


    }

    private void uploadImage() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        pickImage.launch(intent);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(add.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            if (data != null) {
                imageUri = data.getData();
                imageViewUploadedPet.setImageURI(imageUri);
            }
        }
    }
}
