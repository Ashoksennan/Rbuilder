package com.example.admin.resumebuilderproject.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.admin.resumebuilderproject.contracts.RoomPrePopulateInterface;
import com.example.admin.resumebuilderproject.views.SplashScreenActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DatabaseClient {
 
    private Context mCtx;
    private static DatabaseClient mInstance;
    
    //our app database object
    private AppDatabase appDatabase;
    private RoomPrePopulateInterface roomPrePopulateInterface;
 
    private DatabaseClient(Context mCtx, final RoomPrePopulateInterface roomPrePopulateInterface) {
        this.mCtx = mCtx;
        this.roomPrePopulateInterface = roomPrePopulateInterface;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "ResumeBuilderDb")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                roomPrePopulateInterface.showProgress();
                                appDatabase.skillsDao().insertAll(SplashScreenActivity.skillsLists);
                                Log.d("data populated", "run: ");
                                roomPrePopulateInterface.hideProgress();
                            }
                        });
                    }
                })
                .build();
    }
    public void populateDb(){
        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
            @Override
            public void run() {
                roomPrePopulateInterface.showProgress();
                appDatabase.skillsDao().insertAll(SplashScreenActivity.skillsLists);
                Log.d("data populated", "run: ");
                roomPrePopulateInterface.hideProgress();
            }
        });
    }
    public static synchronized DatabaseClient getInstance(Context mCtx, RoomPrePopulateInterface roomPrePopulateInterface) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx,roomPrePopulateInterface);
        }
        if(roomPrePopulateInterface != roomPrePopulateInterface)
            mInstance.roomPrePopulateInterface = roomPrePopulateInterface;
        return mInstance;
    }
 
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}