package com.example.emplyoeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button add_id,show_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
           
        FindviewById();
        Body();
    }

    private void Body()
    {
        add_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(HomeActivity.this,EmployeeAddActivity.class);
                startActivity(i);
            }
        });
        show_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(HomeActivity.this,EmployeeListActivity.class);
                startActivity(i);

            }
        });
    }

    private void FindviewById()
    {
        add_id = findViewById(R.id.add_id);
        show_id = findViewById(R.id.show_id);

    }
}