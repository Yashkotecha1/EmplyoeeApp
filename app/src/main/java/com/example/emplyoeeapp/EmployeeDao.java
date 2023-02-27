package com.example.emplyoeeapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employeeentity")
    EmployeeEntity[] getAll();

    @Insert
    void insertAll(EmployeeEntity employeeEntities);

    @Delete
    void delete(EmployeeEntity employee);





}
