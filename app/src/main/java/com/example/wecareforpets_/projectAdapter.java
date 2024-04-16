package com.example.wecareforpets_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class projectAdapter extends  RecyclerView.Adapter<projectAdapter.ViewHolder> {

    ArrayList<projetModel> list ;
    Context context;

    public projectAdapter(ArrayList<projetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_pet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        projetModel Model = list.get(position);

        Picasso.get().load(Model.getImageViewUploadedPet()).placeholder(R.drawable.upload_img).into(holder.imageView);
        holder.viewcet.setText(Model.getPetCategory());
        holder.viewforwhat.setText(Model.getPurpose());
        holder.viewfbreed.setText(Model.getBreed());
        holder.viewsex.setText(Model.getSex());
        holder.viewpric.setText(Model.getPrice());
        holder.viewownername.setText(Model.getOwnerName());
        holder.viewowneremail.setText(Model.getOwnerEmail());
        holder.viewownerphonenumber.setText(Model.getOwnerPhone());
        holder.viewowneraddersss.setText(Model.getOwnerAddress());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView  viewcet,viewforwhat,viewfbreed,viewsex,viewpric,viewownername,viewowneremail,viewownerphonenumber,viewowneraddersss;
        ImageView imageView;
        public ViewHolder(@NonNull View pet){
            super(pet);
            viewcet = pet.findViewById(R.id.viewcet);
            viewforwhat = pet.findViewById(R.id.viewforwhat);
            viewfbreed = pet.findViewById(R.id.viewfbreed);
            viewsex = pet.findViewById(R.id.viewsex);
            viewpric = pet.findViewById(R.id.viewpric);
            viewownername = pet.findViewById(R.id.viewownername);
            viewowneremail = pet.findViewById(R.id.viewowneremail);
            viewownerphonenumber = pet.findViewById(R.id.viewownerphonenumber);
            viewowneraddersss = pet.findViewById(R.id.viewowneraddersss);
            imageView=pet.findViewById(R.id.imageView);
        }


    }
}
