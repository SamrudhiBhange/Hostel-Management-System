package com.example.hostelmsa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<StaffModule> staffDataHolder;

    public MyAdapter(ArrayList<StaffModule> staffDataHolder) {
        this.staffDataHolder = staffDataHolder;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_staff_details_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tId.setText(staffDataHolder.get(position).getStaffId());
//        holder.tPassword.setText(staffDataHolder.get(position).getStaffPassword());
        holder.tName.setText(staffDataHolder.get(position).getStaffFullName());
        holder.tAddress.setText(staffDataHolder.get(position).getStaffAddress());
        holder.tHostelAllocation.setText(staffDataHolder.get(position).getStaffHostelAllocation());
        holder.tContact.setText(staffDataHolder.get(position).getStaffContact());
        holder.tEmail.setText(staffDataHolder.get(position).getStaffEmail());
    }

    @Override
    public int getItemCount() {
        return staffDataHolder.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tName, tAddress, tContact, tEmail, tId, tPassword, tHostelAllocation;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.displayName);
            tAddress = itemView.findViewById(R.id.displayAddress);
            tContact = itemView.findViewById(R.id.displayContact);
            tEmail = itemView.findViewById(R.id.displayEmail);
            tId = itemView.findViewById(R.id.displayId);
//            tPassword = itemView.findViewById(R.id.displayPassword);
            tHostelAllocation = itemView.findViewById(R.id.displayHostelAllocation);
        }
    }
}
