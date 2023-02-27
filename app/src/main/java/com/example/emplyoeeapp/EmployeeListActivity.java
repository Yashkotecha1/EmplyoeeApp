package com.example.emplyoeeapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    private EmplyoeeAdapter adapter;

    private EmployeeDataDao employeeDataDao;

    private ImageButton clear_id;

    private EditText edittxt_id;

    List<EmployeeDataEntity> list = new ArrayList<>();

    private RecyclerView rview;

    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);


        //-----build table
        EmployeeDb employeeDb = Room.databaseBuilder(getApplicationContext(),
                EmployeeDb.class, "database-name-employee").allowMainThreadQueries().build();

        employeeDataDao = employeeDb.employeeDataDao();



        //------DropDown List -------------
        Spinner spinner = (Spinner) findViewById(R.id.spinner_id);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id = spinner.getSelectedItemId();
                if (id == 3)
                {
                    edittxt_id.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_PHONE);
                }
                else {
                    edittxt_id.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                Log.d(TAG, "onItemSelected: "+spinner.getSelectedItemId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        FindViewById();


    }

    private void FindViewById() {
        edittxt_id = findViewById(R.id.edittxt_id);
        rview = findViewById(R.id.recycle_id);
        clear_id = findViewById(R.id.clear_id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: list layout");
        Body();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: list layout");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: list layout");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: list layout start");

        edittxt_id.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    list.clear();
                    if (id == 0) {
                        list.addAll(employeeDataDao.selectUser(edittxt_id.getText().toString()));
                    } else if (id == 1) {
                        list.addAll(employeeDataDao.selectByLastName(edittxt_id.getText().toString()));
                    } else if (id == 2) {
                        list.addAll(employeeDataDao.selectByDesignation(edittxt_id.getText().toString()));
                    } else if (id == 3 ) {

                        list.addAll(employeeDataDao.selectByPhone(edittxt_id.getText().toString()));
                    }
//                    list = employeeDataDao.selectUser(edittxt_id.getText().toString());
                    Log.d(TAG, "List: "+list.size());
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "onKey: "+adapter.getItemCount());

                    return true;
                }
                return false;
            }
        });

        list.clear();
        clear_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                edittxt_id.setText("");
                list.addAll(employeeDataDao.getAll());
                
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void Body() {


        //---------get All Data--------
        edittxt_id.setText("");

        list.addAll(employeeDataDao.getAll());

        adapter = new EmplyoeeAdapter(list);
        // adapter.notifyDataSetChanged();

        Log.d(TAG, "Body: " + adapter.getItemCount());

        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rview.setAdapter(adapter);


    }


}