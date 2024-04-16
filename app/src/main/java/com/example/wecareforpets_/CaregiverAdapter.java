package com.example.wecareforpets_;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CaregiverAdapter extends RecyclerView.Adapter<CaregiverAdapter.CaregiverViewHolder> {
    private List<caregiversModel> caregiversList;
    private OnBookClickListener onBookClickListener;

    public CaregiverAdapter(List<caregiversModel> caregiversList, OnBookClickListener onBookClickListener) {
        this.caregiversList = caregiversList;
        this.onBookClickListener = onBookClickListener;
    }

    @NonNull
    @Override
    public CaregiverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_caregivers, parent, false);
        return new CaregiverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaregiverViewHolder holder, int position) {
        caregiversModel caregiver = caregiversList.get(position);

        holder.nameTextView.setText(caregiver.getName());
        holder.ageTextView.setText(String.valueOf(caregiver.getAge()));
        holder.phoneNumberTextView.setText(String.valueOf(caregiver.getPhoneNumber()));
        holder.availableTextView.setText(caregiver.getAvailable());
        holder.priceTextView.setText(String.valueOf(caregiver.getPrice()));
    }

    @Override
    public int getItemCount() {
        return caregiversList.size();
    }

    public interface OnBookClickListener {
        void onBookClick(int position);
    }

    public class CaregiverViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, ageTextView, phoneNumberTextView, availableTextView, priceTextView;
        Button bookButton;

        public CaregiverViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.CareGiverName);
            ageTextView = itemView.findViewById(R.id.CareGiverAge);
            phoneNumberTextView = itemView.findViewById(R.id.CareGiverCNumber);
            availableTextView = itemView.findViewById(R.id.CareGiverAva);
            priceTextView = itemView.findViewById(R.id.CareGiverPrice);
            bookButton = itemView.findViewById(R.id.btn_Book);

            bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onBookClickListener != null) {
                        onBookClickListener.onBookClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}

