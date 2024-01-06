package oleg.trushanin.graz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MainOptionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<MainOptions> mainOptions);

    @Query("DELETE FROM main_options")
    Completable deleteAll();


    @Query("SELECT price FROM main_options WHERE name IN (:nameValue)")
    LiveData<List<Float>> getPriceMainOptions(List <String> nameValue);

    @Query("SELECT * FROM main_options WHERE name IN (:nameValue)")
    LiveData<List<MainOptions>> getPriceMainOptions1(List <String> nameValue);


}
