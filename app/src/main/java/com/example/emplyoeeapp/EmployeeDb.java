package com.example.emplyoeeapp;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EmployeeDataEntity.class},version = 1)
public abstract class EmployeeDb extends RoomDatabase
{
    public abstract EmployeeDataDao employeeDataDao();
}
