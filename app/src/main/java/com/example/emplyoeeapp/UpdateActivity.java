package com.example.emplyoeeapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    private EditText txt_firstname, txt_lasttname, txt_phone_number, txt_designation;

    private EmployeeDataDao employeeDataDao;

    private EmployeeDataEntity employeeDataEntity;
    private Button save_id, cancel_id;

    private String first_name, last_name, designation, phone;

    int id;

    private EmplyoeeAdapter adapter;

    private List<EmployeeDataEntity> employeedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        EmployeeDb employeeDb = Room.databaseBuilder(getApplicationContext(),
                EmployeeDb.class, "database-name-employee").allowMainThreadQueries().build();

        employeeDataDao = employeeDb.employeeDataDao();
        FindViewById();
        Body();
    }


    private void Body()
    {

        Intent i = new Intent();

        i=getIntent();

        id=i.getIntExtra("id",0);

        employeeDataEntity = employeeDataDao.loadUsers(id);

        Log.d(TAG, "size: "+employeeDataEntity.getFirstName());

            txt_firstname.setText(employeeDataEntity.getFirstName());
            txt_lasttname.setText(employeeDataEntity.getLastName());
            txt_designation.setText(employeeDataEntity.getDesignation());
            txt_phone_number.setText(employeeDataEntity.getPhone());

            save_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    Log.d(TAG, "onClick: "+txt_firstname.getText().toString());
                    employeeDataEntity.setFirstName(txt_firstname.getText().toString());
                    employeeDataEntity.setLastName(txt_lasttname.getText().toString());
                    employeeDataEntity.setDesignation(txt_designation.getText().toString());
                    employeeDataEntity.setPhone(txt_phone_number.getText().toString());

                    employeeDataDao.updateUsers(employeeDataEntity);

                    finish();
                }
            });
            cancel_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
//        }
//        else {
//            Log.d(TAG, "Body: Error");
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: update layout");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: update layout ");
    }

    private void FindViewById()
    {
        txt_firstname = findViewById(R.id.txt_firstname);
        txt_lasttname = findViewById(R.id.txt_lasttname);
        txt_phone_number = findViewById(R.id.txt_phone_number);
        txt_designation = findViewById(R.id.txt_designation);
        save_id = findViewById(R.id.save_id);
        cancel_id = findViewById(R.id.cancel_id);
    }
}