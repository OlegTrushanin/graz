package oleg.trushanin.graz;


import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Price.class, BazaModel.class, MainOptions.class, SpecialDarkOptions.class,
SpecialLightOptions.class, Ko.class}, version = 18, exportSchema = false)// создание БД
public abstract class DataBase extends RoomDatabase {

    public static final String NAME_DB = "price.db";
    private static DataBase instance = null;

    public static DataBase getInstance(Application application) {

        if (instance == null) {

            instance = Room.databaseBuilder(application,
                    DataBase.class,
                    NAME_DB)
                    .fallbackToDestructiveMigration() // при миграции удаляем старые данные
                    .build();
        }
        return instance;
    }


    abstract PriceDao priceDao();
    abstract BazaModelDao bazaModelDao();
    abstract MainOptionsDao mainOptionsDao();
    abstract SpecialDarkOptionsDao specialDarkOptionsDao();
    abstract SpecialLightOptionsDao specialLightOptionsDao();
    abstract KoDao koDao();



}
