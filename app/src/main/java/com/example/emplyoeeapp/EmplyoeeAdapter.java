package com.example.emplyoeeapp;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class EmplyoeeAdapter extends RecyclerView.Adapter<EmplyoeeAdapter.ViewHolder> {

    private List<EmployeeDataEntity> employeedata;

    private EmployeeDataEntity dataEntity;
    private EmployeeDataDao employeeDataDao;

    private Intent intent;
    int id=0;

    public EmplyoeeAdapter(List<EmployeeDataEntity> employeedata) {
        this.employeedata = employeedata;
    }
    public EmplyoeeAdapter(EmployeeDataEntity dataEntity) {
        this.dataEntity = dataEntity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View employeeList = inflater.inflate(R.layout.activity_show_employee, parent, false);


        ViewHolder viewHolder = new ViewHolder(employeeList);

        EmployeeDb employeeDb = Room.databaseBuilder(parent.getContext(),
                EmployeeDb.class, "database-name-employee").allowMainThreadQueries().build();

        employeeDataDao = employeeDb.employeeDataDao();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.firstname.setText(employeedata.get(position).getFirstName());
        holder.lastname.setText(employeedata.get(position).getLastName());
        holder.designation.setText(employeedata.get(position).getDesignation());
        holder.phone.setText(employeedata.get(position).getPhone());


        //----------------Delete btn-------------------
        holder.delete_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Do You Want to Delete");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
//                     id = employeedata.get(position).getEmployeeId();
                    employeeDataDao.delete(employeedata.get(position));
                    employeedata = employeeDataDao.getAll();
                    notifyDataSetChanged();


                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {

                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //---------Update Btn--------------------
        holder.update_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(view.getContext(),UpdateActivity.class);
                i.putExtra("id",employeedata.get(position).getEmployeeId());
                Log.d(TAG, "onClick: "+employeedata.get(position).getEmployeeId());
                view.getContext().startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return employeedata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstname;
        private final TextView lastname;
        private final TextView designation;
        private final TextView phone;
        private ImageButton delete_id, update_id;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstname = itemView.findViewById(R.id.firstname);
            lastname = itemView.findViewById(R.id.lastname);
            designation = itemView.findViewById(R.id.designation);
            phone = itemView.findViewById(R.id.phone);
            delete_id = itemView.findViewById(R.id.delete_id);
            update_id = itemView.findViewById(R.id.update_id);

        }
    }
}
