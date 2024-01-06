package oleg.trushanin.graz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface SpecialLightOptionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<SpecialLightOptions> specialLightOptions);

    @Query("DELETE FROM special_light_options")
    Completable deleteAll();


    @Query("SELECT * FROM special_light_options")
    LiveData<List<SpecialLightOptions>> getSpecialLight();
}
