package com.example.emplyoeeapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EmployeeEntity.class},version = 1)
public abstract class AppDb extends RoomDatabase
{
        public abstract EmployeeDao employeeDao();


}
