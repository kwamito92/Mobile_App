package com.kwameasamoa.finanaceapp;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Information> listData; //actual data being collected and display on recycler.
    private List<String> keys; //actual list with keys of each position.

    //adapter's constructor
    public MyAdapter(List<Information> listData, List<String> id) {
        this.listData = listData;
        this.keys = id;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //populate the display layout to xml activity.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtphone, txtdate, txttime;
        Button delete;

        public ViewHolder(View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.deleteButton);
            txtname = itemView.findViewById(R.id.textContactName);
            txtphone = itemView.findViewById(R.id.textPhoneNumber);
            txtdate = itemView.findViewById(R.id.textDatePick);
            txttime = itemView.findViewById(R.id.textTimePick);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Information model = listData.get(position);
        holder.txtname.setText(model.getName());
        holder.txtphone.setText(model.getNumber());
        holder.txtdate.setText(model.getDate());
        holder.txttime.setText(model.getTime());

        //alert dialog to delete data row.
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder Builder = new AlertDialog.Builder(view.getRootView().getContext());
                Builder.setTitle("Deletion Warning!!");
                Builder.setMessage("Are you sure you want to delete? ");
                Builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listData.remove(position);
                        notifyItemRemoved(position);
                        String key = keys.get(position);
                        //delete actual data from firebase database real-time.
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Information");
                        //since push creates unique ID we use list to track position to remove data.
                        reference.child(key).removeValue();
                        Toast.makeText(view.getContext(), "Data Successfully Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                Builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                Builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
