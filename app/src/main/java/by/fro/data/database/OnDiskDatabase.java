package by.fro.data.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import by.fro.data.dao.ApplicantDao;
import by.fro.data.model.ApplicantLocalModel;

@Database(entities = {ApplicantLocalModel.class}, version = 1, exportSchema = false)
public abstract class OnDiskDatabase extends RoomDatabase {

    public abstract ApplicantDao applicantDao();

    public static OnDiskDatabase newInstance(Context context) {
        return Room.databaseBuilder(context, OnDiskDatabase.class, "applicants.db").build();
    }
}
