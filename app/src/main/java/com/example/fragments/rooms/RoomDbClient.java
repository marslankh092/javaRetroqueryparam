package com.example.fragments.rooms;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fragments.server.Search;

@Database(entities = { Search.class }, version = 1)
public abstract class RoomDbClient  extends RoomDatabase {

    public abstract MoviesDao getDao();

    private static RoomDbClient noteDB;

    public static RoomDbClient getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static RoomDbClient buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                        RoomDbClient.class,
                        "MoviesDb")
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        noteDB = null;
    }

}
