package com.example.emplyoeeapp;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDataDao
{
    @Insert
    void insertAll(EmployeeDataEntity... dataEntities);

    @Delete
    void delete(EmployeeDataEntity dataEntity);

    @Query("SELECT * FROM EmployeeDataEntity")
    List<EmployeeDataEntity> getAll();

    @Update
    void updateUsers(EmployeeDataEntity dataEntity);

    @Query("SELECT * FROM EmployeeDataEntity WHERE employeeId = :empId ")
     EmployeeDataEntity loadUsers(int empId);

    @Query("SELECT * FROM EmployeeDataEntity WHERE first_name = :firstName ")
    EmployeeDataEntity selectUsers(String firstName);

    @Query("SELECT * FROM EmployeeDataEntity WHERE first_name = :name ")
    List<EmployeeDataEntity> selectUser(String name);

    @Query("SELECT * FROM EmployeeDataEntity WHERE last_name = :name ")
    List<EmployeeDataEntity> selectByLastName(String name);

    @Query("SELECT * FROM EmployeeDataEntity WHERE designation = :name ")
    List<EmployeeDataEntity> selectByDesignation(String name);

    @Query("SELECT * FROM EmployeeDataEntity WHERE phone_number = :name ")
    List<EmployeeDataEntity> selectByPhone(String name);

}
