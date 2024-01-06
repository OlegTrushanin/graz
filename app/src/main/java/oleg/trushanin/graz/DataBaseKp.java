package oleg.trushanin.graz;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {TableDataBaseKp.class}, version = 5, exportSchema = false)
public abstract class DataBaseKp extends RoomDatabase {


    public static final String NAME_DB = "kp.db";

    private static  DataBaseKp instance = null;

    public static DataBaseKp getInstance(Application application){

        if(instance == null){

            instance = Room.databaseBuilder(application,
                            DataBaseKp.class,
                            NAME_DB)
                    .fallbackToDestructiveMigration() // при миграции удаляем старые данные
                    .build();
        }
        return instance;
    }

    abstract TableDataBaseKpDao tableDataBaseKpDao();




}
