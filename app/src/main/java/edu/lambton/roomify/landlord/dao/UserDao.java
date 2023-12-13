package edu.lambton.roomify.landlord.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import edu.lambton.roomify.landlord.model.User;

@Dao
public abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void saveUser(User user);

    @Query("SELECT * FROM User WHERE uid = :uid")
    public abstract LiveData<User> searchUserById(String uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void updateUser(User user);

    @Delete
    public abstract void deleteUser(User user);
}
