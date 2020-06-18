package com.example.todomvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntry user);

    @Query("select * from User order by username")
    List<UserEntry> loadAllUsers();

    @Update
    void update(UserEntry user);

    @Delete
    void deleteUser(UserEntry userEntry);

    @Query("select * from user where id=:id")
    UserEntry loaduserbyid(int id);
}
