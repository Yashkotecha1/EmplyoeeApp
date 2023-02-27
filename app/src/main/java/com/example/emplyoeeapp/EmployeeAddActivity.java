package com.example.emplyoeeapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAddActivity extends AppCompatActivity {

    private EditText txt_firstname, txt_lasttname, txt_phone_number, txt_designation;

    private String first_name, last_name, designation, phone;

    private String id;

    //----first table
    private EmployeeDao employeeDao;

    //-----secound table
    private EmployeeDataDao employeeDataDao;

    private EmployeeDataEntity employeeDataEntity;
    private Button save_id, cancel_id;

    private EmplyoeeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add);
        AppDb db = Room.databaseBuilder(getApplicationContext(),
                AppDb.class, "database-name").allowMainThreadQueries().build();
        employeeDao = db.employeeDao();

        EmployeeDb employeeDb = Room.databaseBuilder(getApplicationContext(),
                EmployeeDb.class, "database-name-employee").allowMainThreadQueries().build();

        employeeDataDao = employeeDb.employeeDataDao();
        FindViewById();
        Body();

    }

    private void Body() {


        save_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                first_name = txt_firstname.getText().toString();
                last_name = txt_lasttname.getText().toString();
                designation = txt_designation.getText().toString();
                phone = txt_phone_number.getText().toString();


                Log.d(TAG, "onClick: " + first_name + "--" + last_name + "--" + designation + "--" + phone);

                //---------Insert Data
                EmployeeDataEntity employeeDataEntity = new EmployeeDataEntity();
                employeeDataEntity.setFirstName(first_name);
                employeeDataEntity.setLastName(last_name);
                employeeDataEntity.setDesignation(designation);
                employeeDataEntity.setPhone(phone);

                employeeDataDao.insertAll(employeeDataEntity);
                Log.d(TAG, "onClick: " + employeeDataEntity.toString());


                //------------Dialog Box
                AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeAddActivity.this);
                builder.setMessage("Successfully Inserted");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    finish();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });
        cancel_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }


    private void FindViewById() {
        txt_firstname = findViewById(R.id.txt_firstname);
        txt_lasttname = findViewById(R.id.txt_lasttname);
        txt_phone_number = findViewById(R.id.txt_phone_number);
        txt_designation = findViewById(R.id.txt_designation);
        save_id = findViewById(R.id.save_id);
        cancel_id = findViewById(R.id.cancel_id);
    }
}